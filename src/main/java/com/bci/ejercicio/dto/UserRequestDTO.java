package com.bci.ejercicio.dto;

import com.bci.ejercicio.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserRequestDTO implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 42311121446477966L;
	private UUID id;
   	private String name;
   	private String email;
   	private String password;
	private Date created;
   	private String modified;
   	private String last_login;
   	private String token;
   	private boolean isactive;
   	private List<PhoneDTO> phones;
   	private Collection<? extends GrantedAuthority> authorities;
   	
   	public UserRequestDTO() {
   		
   	}
   	
	public UserRequestDTO(String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		
	}
	
	public static UserRequestDTO build(UserEntity usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority("ROLE_USER")).collect(Collectors.toList());
        return new UserRequestDTO(usuario.getName(), usuario.getEmail(), usuario.getPassword(), authorities);
    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public List<PhoneDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isactive;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", created=" + created + ", modified=" + modified + ", last_login=" + last_login + ", token=" + token
				+ ", isactive=" + isactive + ", phones=" + phones + ", authorities=" + authorities + "]";
	}
	
	
   	
   	
}
