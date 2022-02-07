package com.jac.javadb.domain.dto.request;



public class UtenteReqDTO {
	
	private String username;
	
	private String password;
	
	private String indirizzo;
	
	private String telefono;
	
	private String email;
	
	
	UtenteReqDTO(){ super();}
	
	UtenteReqDTO(String username, String password, String indirizzo, String telefono, String email){
		this.username= username;
		this.password=password;
		this.indirizzo= indirizzo;
		this.telefono= telefono;
		this.email= email;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	
}
