package com.jac.javadb.domain.model;
	
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
	
@Entity
@Table(name = "contatti")
public class Contatto extends BaseEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome", length = 50)
	private String nome;
	
	@Column(name = "cognome", length = 50)
	private String cognome;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "indirizzo", length = 200)
	private String indirizzo;
	
	@Column(name = "telefono", length = 15)
	private String telefono;
	
	@Column(name = "foto", length = 10000)
	private String foto;
	
	@Column(name = "user_id", length = 10000)
	private int userId;
	
	
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
	
	public int getIdUser() {
	return userId;
	}
	
	public void setIdUser(int userId) {
	this.userId = userId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((foto == null) ? 0 : foto.hashCode());
	result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
	
	return result;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj)
	return true;
	if (obj == null)
	return false;
	if (getClass() != obj.getClass())
	return false;
	Contatto other = (Contatto) obj;
	if (cognome == null) {
	if (other.cognome != null)
	return false;
	} else if (!cognome.equals(other.cognome))
	return false;
	if (email == null) {
	if (other.email != null)
	return false;
	} else if (!email.equals(other.email))
	return false;
	if (foto == null) {
	if (other.foto != null)
	return false;
	} else if (!foto.equals(other.foto))
	return false;
	if (indirizzo == null) {
	if (other.indirizzo != null)
	return false;
	} else if (!indirizzo.equals(other.indirizzo))
	return false;
	if (nome == null) {
	if (other.nome != null)
	return false;
	} else if (!nome.equals(other.nome))
	return false;
	if (telefono == null) {
	if (other.telefono != null)
	return false;
	} else if (!telefono.equals(other.telefono))
	return false;
	return true;
	}
	
	
	
	@Override
	public String toString() {
	return "Contatto [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", indirizzo=" + indirizzo
	+ ", telefono=" + telefono + ", foto=" + foto + "]";
	}
	
	
	
	}