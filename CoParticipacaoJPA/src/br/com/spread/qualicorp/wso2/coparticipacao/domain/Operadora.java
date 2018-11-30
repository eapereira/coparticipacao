package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_operadora database table.
 * 
 */
public abstract class Operadora extends AbstractDomain {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3001730248920877550L;

	private String nameOperadora;
	
	private String cdOperadora;

	private List<Empresa> empresas;
	
	private boolean enabled;

	public Operadora() {
		empresas = new ArrayList<>();
	}

	public Operadora(Operadora entity) {
		super(entity);
	}

	public String getNameOperadora() {
		return this.nameOperadora;
	}

	public void setNameOperadora(String nmOperadora) {
		this.nameOperadora = nmOperadora;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void addEmpresa(Empresa empresa) {
		getEmpresas().add(empresa);
		empresa.setOperadora(this);
	}

	public void removeEmpresa(Empresa empresa) {
		getEmpresas().remove(empresa);
		empresa.setOperadora(null);
	}

	public String getCdOperadora() {
		return cdOperadora;
	}

	public void setCdOperadora(String cdOperadora) {
		this.cdOperadora = cdOperadora;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cdOperadora == null) ? 0 : cdOperadora.hashCode());
		result = prime * result + ((empresas == null) ? 0 : empresas.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((nameOperadora == null) ? 0 : nameOperadora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operadora other = (Operadora) obj;
		if (cdOperadora == null) {
			if (other.cdOperadora != null)
				return false;
		} else if (!cdOperadora.equals(other.cdOperadora))
			return false;
		if (empresas == null) {
			if (other.empresas != null)
				return false;
		} else if (!empresas.equals(other.empresas))
			return false;
		if (enabled != other.enabled)
			return false;
		if (nameOperadora == null) {
			if (other.nameOperadora != null)
				return false;
		} else if (!nameOperadora.equals(other.nameOperadora))
			return false;
		return true;
	}
}