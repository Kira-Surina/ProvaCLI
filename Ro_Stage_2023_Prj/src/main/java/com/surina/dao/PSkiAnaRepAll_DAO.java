package com.surina.dao;

public class PSkiAnaRepAll_DAO {
	
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

}
