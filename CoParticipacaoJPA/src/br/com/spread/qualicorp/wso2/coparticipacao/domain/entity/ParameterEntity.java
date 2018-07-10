package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;

/**
 * The persistent class for the tb_parameter database table.
 * 
 */
@Entity
@Table(name = "TB_PARAMETER")
@NamedQuery(
		name = "ParameterEntity.findAll",
		query = "SELECT p FROM ParameterEntity p")
public class ParameterEntity extends Parameter implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3160534230114447336L;

	public ParameterEntity() {
	}

	public ParameterEntity(ParameterUi ui) {
		super(ui);
	}

	@Column(name = "NM_PARAMETER")
	public String getNameParameter() {
		return super.getNameParameter();
	}

	@Column(name = "VL_PARAMETER")
	public String getValue() {
		return super.getValue();
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getEmpresa() {
		return super.getEmpresa();
	}

}