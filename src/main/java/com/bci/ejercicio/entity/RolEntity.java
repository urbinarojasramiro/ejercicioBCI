package com.bci.ejercicio.entity;

import com.bci.ejercicio.util.RolName;
import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name="rol")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name")
    private RolName rolName;

    public RolEntity() {
    }

    public RolEntity(@NotNull RolName rolName) {
        this.rolName = rolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }

	@Override
	public String toString() {
		return "RolEntity [id=" + id + ", rolName=" + rolName + "]";
	}
    
    
}
