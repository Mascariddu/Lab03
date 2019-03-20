package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String testo;
	private boolean esattezza;
	
	public RichWord(String testo, boolean esattezza) {
		this.testo = testo;
		this.esattezza = esattezza;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public boolean isEsattezza() {
		return esattezza;
	}
	
	public void setEsattezza(boolean esattezza) {
		this.esattezza = esattezza;
	}
	
}
