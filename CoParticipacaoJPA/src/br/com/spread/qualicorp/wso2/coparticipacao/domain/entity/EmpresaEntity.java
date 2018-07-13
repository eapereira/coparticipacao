package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */
@Entity
@Table(name = "TB_EMPRESA")
@NamedQuery(
		name = "EmpresaEntity.findAll",
		query = "SELECT e FROM EmpresaEntity e")
public class EmpresaEntity extends Empresa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157383238358452982L;

	public EmpresaEntity() {
	}

	public EmpresaEntity(EmpresaUi ui) {
		super(ui);
	}

	@Column(name = "NM_EMPRESA")
	public String getNameEmpresa() {
		return super.getNameEmpresa();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "empresa", targetEntity = ContratoEntity.class)
	public List<Contrato> getContratos() {
		return super.getContratos();
	}

	// bi-directional many-to-one association to Parameter
	@OneToMany(mappedBy = "empresa", targetEntity = ParameterEntity.class)
	public List<Parameter> getParameters() {
		return super.getParameters();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "empresa", targetEntity = TitularEntity.class)
	@Override
	public List<Titular> getTitulars() {
		// TODO Auto-generated method stub
		return super.getTitulars();
	}

	// bi-directional many-to-one association to Contrato
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = OperadoraEntity.class)
	@JoinColumn(name = "ID_OPERADORA")
	@Override
	public Operadora getOperadora() {
		// TODO Auto-generated method stub
		return super.getOperadora();
	}

}