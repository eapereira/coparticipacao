package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_operadora database table.
 * 
 */
public abstract class Operadora extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameOperadora;

	private List<Empresa> empresas;

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
}