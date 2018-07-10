package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Isento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;

/**
 * The persistent class for the tb_dependente_isento database table.
 * 
 */
@Entity
@Table(name = "TB_DEPENDENTE_ISENTO")
@NamedQuery(
		name = "DependenteIsentoEntity.findAll",
		query = "SELECT d FROM DependenteIsentoEntity d")
public class DependenteIsentoEntity extends DependenteIsento
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2907518260771512367L;

	public DependenteIsentoEntity() {
	}

	public DependenteIsentoEntity(DependenteIsentoUi ui) {
		super(ui);
	}

	// bi-directional many-to-one association to Dependente
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DependenteEntity.class)
	@JoinColumn(name = "ID_DEPENDENTE")
	public Dependente getDependente() {
		return super.getDependente();
	}

	// bi-directional many-to-one association to Isento
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = IsentoEntity.class)
	@JoinColumn(name = "ID_ISENTO")
	public Isento getIsento() {
		return super.getIsento();
	}

}