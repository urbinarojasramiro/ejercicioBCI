package com.bci.ejercicio.jwt;

import com.bci.ejercicio.util.Constants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final static Logger LOG = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(String email){
    	
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId(Constants.SECRET_ID)
				.setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                .compact();

		return Constants.PREFIX + token;
    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
    	LOG.info("se recibe token: " + token);
        try {
            Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            LOG.error("token mal formado");
        }catch (UnsupportedJwtException e){
            LOG.error("token no soportado");
        }catch (ExpiredJwtException e){
            LOG.error("token expirado");
        }catch (IllegalArgumentException e){
            LOG.error("token vac�o");
        }catch (SignatureException e){
            LOG.error("fail en la firma");
        }
        return false;
    }
}
