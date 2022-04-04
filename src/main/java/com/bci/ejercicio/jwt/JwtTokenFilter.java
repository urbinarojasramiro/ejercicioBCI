package com.bci.ejercicio.jwt;

import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.service.impl.UserServiceImpl;
import com.bci.ejercicio.util.Constants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger LOG = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    UserServiceImpl userService;
    
    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
        	
        	if(jwtProvider==null){
                ServletContext servletContext = req.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                jwtProvider = webApplicationContext.getBean(JwtProvider.class);
            }
        	if(userService==null){
                ServletContext servletContext = req.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                userService = webApplicationContext.getBean(UserServiceImpl.class);
            }
        	
            String token = getToken(req);
            
            LOG.info("Se valida token: " + token);
            
            
            if(token != null){
                String email = jwtProvider.getEmailFromToken(token);
                UserDetails userDetail;
                userDetail = UserRequestDTO.build(userService.findByEmail(email).get());

                Claims claims = jwtProvider.validateToken(token)?
                				Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody() : null;

                @SuppressWarnings("unchecked")
        		List<String> authorities = (List<String>) claims.get("authorities");
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetail, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(auth);
                
            }else {
				SecurityContextHolder.clearContext();
            }
            
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            LOG.error("fail en el metodo doFilter " + e.getMessage());
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) res).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } 
        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader(Constants.HEADER);
        if(header != null && header.startsWith(Constants.PREFIX))
            return header.replace(Constants.PREFIX, "");
        return null;
    }
}
