package com.bci.ejercicio.entity;

import com.bci.ejercicio.dto.PhoneDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="phone")
public class PhoneEntity {
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
	@Column(name = "number", columnDefinition = "VARCHAR(255)")
	private String number;
	@Column(name = "citycode", columnDefinition = "INTEGER")
	private Integer citycode;
	@Column(name = "contrycode", columnDefinition = "INTEGER")
	private Integer contrycode;
	
	
	
	public PhoneEntity(PhoneDTO phone) {
		this.number = phone.getNumber();
		this.citycode = phone.getCitycode();
		this.contrycode = phone.getContrycode();
	}
	public PhoneEntity() {
		super();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getCitycode() {
		return citycode;
	}
	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}
	public Integer getContrycode() {
		return contrycode;
	}
	public void setContrycode(Integer contrycode) {
		this.contrycode = contrycode;
	}
	@Override
	public String toString() {
		return "PhoneEntity [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode
				+ "]";
	}
	
	
	
	
	
}
