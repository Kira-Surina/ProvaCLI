package com.surina.entities;

import org.springframework.ui.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name="vskianarep")

	@NamedStoredProcedureQuery(name="pSkiAnaRepAll", procedureName = "pSkiAnaRepAll")
//	@NamedStoredProcedureQuery(name = "pSkiAnaRep", parameters = {StoredProcedureParameter(mode = ParametrMode.IN, )})


public class ViewSkiAnaRep {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;

	private String codice;
	
	private String categoria;
	
	private String subcategoria;
	
	private String descrizione;
	private short stato;
	private short approvazione;
	private int utenti;
	private int percorsi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public short getStato() {
		return stato;
	}
	public void setStato(short stato) {
		this.stato = stato;
	}
	public short getApprovazione() {
		return approvazione;
	}
	public void setApprovazione(short approvazione) {
		this.approvazione = approvazione;
	}
	public int getUtenti() {
		return utenti;
	}
	public void setUtenti(int utenti) {
		this.utenti = utenti;
	}
	public int getPercorsi() {
		return percorsi;
	}
	public void setPercorsi(int percorsi) {
		this.percorsi = percorsi;
	}
	
	
	@Override
	public String toString() {
		return "vSkiAnaRep [id=" + id + ", codice=" + codice + ", categoria=" + categoria + ", subcategoria="
				+ subcategoria + ", descrizione=" + descrizione + ", stato=" + stato + ", approvazione=" + approvazione
				+ ", utenti=" + utenti + ", percorsi=" + percorsi + "]";
	}
	
//	@Override
//	public String toString2() {
//		return "vSkiAnaRep [codice=" + codice + ", categoria=" + categoria + ", subcategoria="
//				+ subcategoria + "]";
//	}
//	
	
}
