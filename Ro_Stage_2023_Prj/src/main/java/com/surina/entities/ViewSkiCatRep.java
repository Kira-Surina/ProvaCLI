package com.surina.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vskicatrep")

public class ViewSkiCatRep {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cat_id;
	
	private String cat_codice;
	private String cat_descrizione;
	private short cat_stato;
	private short cat_approvazione;
	private int sub_id;
	private String sub_codice;
	private String sub_descrizione;
	private short sub_stato;
	private short sub_approvazione;
	private String associazione;
	private long skills;
	
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_codice() {
		return cat_codice;
	}
	public void setCat_codice(String cat_codice) {
		this.cat_codice = cat_codice;
	}
	public String getCat_descrizione() {
		return cat_descrizione;
	}
	public void setCat_descrizione(String cat_descrizione) {
		this.cat_descrizione = cat_descrizione;
	}
	public short getCat_stato() {
		return cat_stato;
	}
	public void setCat_stato(short cat_stato) {
		this.cat_stato = cat_stato;
	}
	public short getCat_approvazione() {
		return cat_approvazione;
	}
	public void setCat_approvazione(short cat_approvazione) {
		this.cat_approvazione = cat_approvazione;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_codice() {
		return sub_codice;
	}
	public void setSub_codice(String sub_codice) {
		this.sub_codice = sub_codice;
	}
	public String getSub_descrizione() {
		return sub_descrizione;
	}
	public void setSub_descrizione(String sub_descrizione) {
		this.sub_descrizione = sub_descrizione;
	}
	public short getSub_stato() {
		return sub_stato;
	}
	public void setSub_stato(short sub_stato) {
		this.sub_stato = sub_stato;
	}
	public short getSub_approvazione() {
		return sub_approvazione;
	}
	public void setSub_approvazione(short sub_approvazione) {
		this.sub_approvazione = sub_approvazione;
	}
	public String getAssociazione() {
		return associazione;
	}
	public void setAssociazione(String associazione) {
		this.associazione = associazione;
	}
	public long getSkills() {
		return skills;
	}
	public void setSkills(long skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "vSkiCatRep [cat_id=" + cat_id + ", cat_codice=" + cat_codice + ", cat_descrizione=" + cat_descrizione
				+ ", cat_stato=" + cat_stato + ", cat_approvazione=" + cat_approvazione + ", sub_id=" + sub_id
				+ ", sub_codice=" + sub_codice + ", sub_descrizione=" + sub_descrizione + ", sub_stato=" + sub_stato
				+ ", sub_approvazione=" + sub_approvazione + ", associazione=" + associazione + ", skills=" + skills
				+ "]";
	}
	
	
	
	
}
