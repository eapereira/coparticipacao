package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_isento database table.
 * 
 */
public abstract class Isento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nameIsento;
	private List<DependenteIsento> dependenteIsentos;

	private List<TitularIsento> titularIsentos;

	public Isento() {
		dependenteIsentos=new ArrayList<>();
	}

	public Isento(Isento entity) {
		super(entity);
	}

	public String getNameIsento() {
		return this.nameIsento;
	}

	public void setNameIsento(String nmIsento) {
		this.nameIsento = nmIsento;
	}

	public List<DependenteIsento> getDependenteIsentos() {
		return this.dependenteIsentos;
	}

	public void setDependenteIsentos(List<DependenteIsento> dependenteIsentos) {
		this.dependenteIsentos = dependenteIsentos;
	}

	public DependenteIsento addDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().add(dependenteIsento);
		dependenteIsento.setIsento(this);

		return dependenteIsento;
	}

	public DependenteIsento removeDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().remove(dependenteIsento);
		dependenteIsento.setIsento(null);

		return dependenteIsento;
	}

	public List<TitularIsento> getTitularIsentos() {
		return this.titularIsentos;
	}

	public void setTitularIsentos(List<TitularIsento> titularIsentos) {
		this.titularIsentos = titularIsentos;
	}

	public TitularIsento addTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().add(titularIsento);
		titularIsento.setIsento(this);

		return titularIsento;
	}

	public TitularIsento removeTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().remove(titularIsento);
		titularIsento.setIsento(null);

		return titularIsento;
	}

}