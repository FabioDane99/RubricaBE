package com.jac.javadb.domain.dto.response;


public class UtenteResponseDTO {
	
	private int id;
	
	private String username;

	private String indirizzo;

	private String telefono;

	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public UtenteResponseDTO(int id, String username, String indirizzo, String telefono, String email) {
		super();
		this.id = id;
		this.username = username;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.email = email;
	}
	
	public UtenteResponseDTO() {}
	
	
	
}
