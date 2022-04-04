package com.bci.ejercicio.entity;

import com.bci.ejercicio.dto.UserRequestDTO;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="user")
public class UserEntity {
	   	@Id
	    @GeneratedValue(generator = "uuid2")
	    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	    @Column(name = "id", columnDefinition = "VARCHAR(255)")
	    private UUID id;
	   	@Column(name="name", columnDefinition = "VARCHAR(255)")
	   	private String name;
	   	@Column(name="email", columnDefinition = "VARCHAR(255)")
	   	private String email;
	   	@Column(name="password", columnDefinition = "VARCHAR(255)")
	   	private String password;
	   	@Column(name="created", columnDefinition = "DATE")
		private Date created;
	   	@Column(name="modified", columnDefinition = "DATE")
	   	private Date modified;
	   	@Column(name="last_login", columnDefinition = "DATE")
	   	private Date last_login;
	   	@Column(name="isactive", columnDefinition = "BOOLEAN")
	   	private boolean isactive;
	    @NotNull
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	    inverseJoinColumns = @JoinColumn(name = "rol_id"))
	    private Set<RolEntity> roles = new HashSet<>();
	   	
	   	
	   	
		public UserEntity(UserRequestDTO user) {
			this.name = user.getName();
			this.password = user.getPassword();
			this.email = user.getEmail();
		}
		public UserEntity() {
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
		public Date getModified() {
			return modified;
		}
		public void setModified(Date modified) {
			this.modified = modified;
		}
		public Date getLast_login() {
			return last_login;
		}
		public void setLast_login(Date last_login) {
			this.last_login = last_login;
		}

		public boolean isIsactive() {
			return isactive;
		}
		public void setIsactive(boolean isactive) {
			this.isactive = isactive;
		}
		
		public Set<RolEntity> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<RolEntity> roles) {
	        this.roles = roles;
	    }
		
		@Override
		public String toString() {
			return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", created=" + created + ", modified=" + modified + ", last_login=" + last_login + ", isactive="
					+ isactive + "]";
		}
	   	
}
