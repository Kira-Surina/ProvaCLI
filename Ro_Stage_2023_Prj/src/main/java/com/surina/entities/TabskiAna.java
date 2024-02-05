package com.surina.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tskiana")
public class TabskiAna {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String cod;
	private String des;
	private String desExt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDesExt() {
		return desExt;
	}
	public void setDesExt(String desExt) {
		this.desExt = desExt;
	}
	@Override
	public String toString() {
		return "TabskiAna [id=" + id + ", cod=" + cod + ", des=" + des + ", desExt=" + desExt + "]";
	}

	
		
}
