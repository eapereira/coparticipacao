package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The persistent class for the tb_contrato database table.
 * 
 */
public abstract class Contrato extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3625459088418269840L;

	private String cdContrato;

	private String nameContrato;

	private String description;

	private Empresa empresa;

	private ArquivoInput arquivoInput;

	private UseType useType;

	private List<Lancamento> lancamentos;

	private Boolean spreadsheetAllPages;

	private List<ArquivoExecucao> arquivoExecucaos;

	private List<TitularIsento> titularIsentos;

	private List<DependenteIsento> dependenteIsentos;

	private Contrato parent;

	private List<Contrato> children;

	private List<Titular> titulars;

	private List<ArquivoInputSheet> arquivoInputSheets;
	
	private boolean displayOutputResult;
	
	private boolean enabled;

	public Contrato() {
		lancamentos = new ArrayList<>();
		arquivoExecucaos = new ArrayList<>();
		titularIsentos = new ArrayList<>();
		dependenteIsentos = new ArrayList<>();
		children = new ArrayList<>();
		titulars = new ArrayList<>();
		arquivoInputSheets = new ArrayList<>();

		spreadsheetAllPages = Boolean.FALSE;
	}

	public Contrato(Contrato entity) {
		super(entity);
	}

	@Column(name = "CD_CONTRATO")
	public String getCdContrato() {
		return this.cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	@Column(name = "NM_CONTRATO")
	public String getNameContrato() {
		return this.nameContrato;
	}

	public void setNameContrato(String nameContrato) {
		this.nameContrato = nameContrato;
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "contrato")
	public List<Lancamento> getLancamentos() {
		return this.lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento addLancamento(Lancamento lancamento) {
		getLancamentos().add(lancamento);
		lancamento.setContrato(this);

		return lancamento;
	}

	public Lancamento removeLancamento(Lancamento lancamento) {
		getLancamentos().remove(lancamento);
		lancamento.setContrato(null);

		return lancamento;
	}

	// bi-directional many-to-one association to Lancamento
	@OneToOne(mappedBy = "contrato")
	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public UseType getUseType() {
		return useType;
	}

	public void setUseType(UseType useType) {
		this.useType = useType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean isSpreadsheetAllPages() {
		return spreadsheetAllPages;
	}

	public void setSpreadsheetAllPages(Boolean spreadsheetAllPages) {
		this.spreadsheetAllPages = spreadsheetAllPages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ArquivoExecucao> getArquivoExecucaos() {
		return arquivoExecucaos;
	}

	public void setArquivoExecucaos(List<ArquivoExecucao> arquivoExecucaos) {
		this.arquivoExecucaos = arquivoExecucaos;
	}

	public void addArquivoExecucao(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaos().add(arquivoExecucao);
		arquivoExecucao.setContrato(this);
	}

	public void removeArquivoExecucao(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaos().remove(arquivoExecucao);
		arquivoExecucao.setContrato(null);
	}

	public List<TitularIsento> getTitularIsentos() {
		return titularIsentos;
	}

	public void setTitularIsentos(List<TitularIsento> titularIsentos) {
		this.titularIsentos = titularIsentos;
	}

	public void addTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().add(titularIsento);
		titularIsento.setContrato(this);
	}

	public void removeTitularIsento(TitularIsento titularIsento) {
		getTitularIsentos().remove(titularIsento);
		titularIsento.setContrato(null);
	}

	public void addDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().add(dependenteIsento);
		dependenteIsento.setContrato(this);
	}

	public void removeDependenteIsento(DependenteIsento dependenteIsento) {
		getDependenteIsentos().remove(dependenteIsento);
		dependenteIsento.setContrato(null);
	}

	public Boolean getSpreadsheetAllPages() {
		return spreadsheetAllPages;
	}

	public List<DependenteIsento> getDependenteIsentos() {
		return dependenteIsentos;
	}

	public void setDependenteIsentos(List<DependenteIsento> dependenteIsentos) {
		this.dependenteIsentos = dependenteIsentos;
	}

	public Contrato getParent() {
		return parent;
	}

	public void setParent(Contrato parent) {
		this.parent = parent;
	}

	public List<Contrato> getChildren() {
		return children;
	}

	public void setChildren(List<Contrato> children) {
		this.children = children;
	}

	public void addChildren(Contrato contrato) {
		getChildren().add(contrato);
		contrato.setParent(this);
	}

	public void removeChildren(Contrato contrato) {
		getChildren().remove(contrato);
		contrato.setParent(null);
	}

	public List<Titular> getTitulars() {
		return titulars;
	}

	public void setTitulars(List<Titular> titulars) {
		this.titulars = titulars;
	}

	public void addTitular(Titular titular) {
		getTitulars().add(titular);
		titular.setContrato(this);
	}

	public void removeTitular(Titular titular) {
		getTitulars().remove(titular);
		titular.setContrato(null);
	}

	public List<ArquivoInputSheet> getArquivoInputSheets() {
		return arquivoInputSheets;
	}

	public void setArquivoInputSheets(List<ArquivoInputSheet> arquivoInputSheets) {
		this.arquivoInputSheets = arquivoInputSheets;
	}

	public void addArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		getArquivoInputSheets().add(arquivoInputSheet);
		arquivoInputSheet.setContrato(this);
	}

	public void removeArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		getArquivoInputSheets().remove(arquivoInputSheet);
		arquivoInputSheet.setContrato(null);
	}

	public boolean isDisplayOutputResult() {
		return displayOutputResult;
	}

	public void setDisplayOutputResult(boolean displayOutputResult) {
		this.displayOutputResult = displayOutputResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoExecucaos == null) ? 0 : arquivoExecucaos.hashCode());
		result = prime * result + ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((arquivoInputSheets == null) ? 0 : arquivoInputSheets.hashCode());
		result = prime * result + ((cdContrato == null) ? 0 : cdContrato.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((dependenteIsentos == null) ? 0 : dependenteIsentos.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (displayOutputResult ? 1231 : 1237);
		result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + ((nameContrato == null) ? 0 : nameContrato.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((spreadsheetAllPages == null) ? 0 : spreadsheetAllPages.hashCode());
		result = prime * result + ((titularIsentos == null) ? 0 : titularIsentos.hashCode());
		result = prime * result + ((titulars == null) ? 0 : titulars.hashCode());
		result = prime * result + ((useType == null) ? 0 : useType.hashCode());
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
		Contrato other = (Contrato) obj;
		if (arquivoExecucaos == null) {
			if (other.arquivoExecucaos != null)
				return false;
		} else if (!arquivoExecucaos.equals(other.arquivoExecucaos))
			return false;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (arquivoInputSheets == null) {
			if (other.arquivoInputSheets != null)
				return false;
		} else if (!arquivoInputSheets.equals(other.arquivoInputSheets))
			return false;
		if (cdContrato == null) {
			if (other.cdContrato != null)
				return false;
		} else if (!cdContrato.equals(other.cdContrato))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (dependenteIsentos == null) {
			if (other.dependenteIsentos != null)
				return false;
		} else if (!dependenteIsentos.equals(other.dependenteIsentos))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (displayOutputResult != other.displayOutputResult)
			return false;
		if (lancamentos == null) {
			if (other.lancamentos != null)
				return false;
		} else if (!lancamentos.equals(other.lancamentos))
			return false;
		if (nameContrato == null) {
			if (other.nameContrato != null)
				return false;
		} else if (!nameContrato.equals(other.nameContrato))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (spreadsheetAllPages == null) {
			if (other.spreadsheetAllPages != null)
				return false;
		} else if (!spreadsheetAllPages.equals(other.spreadsheetAllPages))
			return false;
		if (titularIsentos == null) {
			if (other.titularIsentos != null)
				return false;
		} else if (!titularIsentos.equals(other.titularIsentos))
			return false;
		if (titulars == null) {
			if (other.titulars != null)
				return false;
		} else if (!titulars.equals(other.titulars))
			return false;
		if (useType != other.useType)
			return false;
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}