package com.jac.javadb.domain.dto.request;



public class ContattoReqDTO {
	


	private String nome;
	
	private String cognome;
	
	private String email;
	
	private String indirizzo;
	
	private String telefono;
	
	private String foto;
	
	
	ContattoReqDTO(){
		super();
	}
	
	ContattoReqDTO(String nome, String cognome, String email, String indirizzo, String telefono, String foto){
		
		this.nome= nome;
		this.cognome= cognome;
		this.email= email;
		this.indirizzo= indirizzo;
		this.telefono=telefono;
		this.foto=foto;
	}
	ContattoReqDTO(String nome, String cognome, String email, String indirizzo, String telefono){
		
		this.nome= nome;
		this.cognome= cognome;
		this.email= email;
		this.indirizzo= indirizzo;
		this.telefono=telefono;
	
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
