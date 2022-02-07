package com.jac.javadb.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseEntity {

	@Column(name = "data_inserimento")
	protected Date dataInserimento;

	@Column(name = "utente_inserimento", length = 20)
	protected String utenteInserimento;

	@Column(name = "data_modifica")
	protected Date dataModifica;

	@Column(name = "utente_modifca", length = 20)
	protected String utenteModifica;

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(String utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getUtenteModifica() {
		return utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((dataModifica == null) ? 0 : dataModifica.hashCode());
		result = prime * result + ((utenteInserimento == null) ? 0 : utenteInserimento.hashCode());
		result = prime * result + ((utenteModifica == null) ? 0 : utenteModifica.hashCode());
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
		BaseEntity other = (BaseEntity) obj;
		if (dataInserimento == null) {
			if (other.dataInserimento != null)
				return false;
		} else if (!dataInserimento.equals(other.dataInserimento))
			return false;
		if (dataModifica == null) {
			if (other.dataModifica != null)
				return false;
		} else if (!dataModifica.equals(other.dataModifica))
			return false;
		if (utenteInserimento == null) {
			if (other.utenteInserimento != null)
				return false;
		} else if (!utenteInserimento.equals(other.utenteInserimento))
			return false;
		if (utenteModifica == null) {
			if (other.utenteModifica != null)
				return false;
		} else if (!utenteModifica.equals(other.utenteModifica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseEntity [dataInserimento=" + dataInserimento + ", utenteInserimento=" + utenteInserimento
				+ ", dataModifica=" + dataModifica + ", utenteModifica=" + utenteModifica + "]";
	}
	
}
