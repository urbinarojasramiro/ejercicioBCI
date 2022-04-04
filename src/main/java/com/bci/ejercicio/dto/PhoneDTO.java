package com.bci.ejercicio.dto;

import java.util.UUID;


public class PhoneDTO {
    private UUID id;
	private String number;
	private Integer citycode;
	private Integer contrycode;
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
		return "PhoneDTO [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode
				+ "]";
	}
	
	
}
