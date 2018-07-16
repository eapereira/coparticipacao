package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.IsentoTypeConverter;
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

	@Convert(converter = IsentoTypeConverter.class)
	@Column(name = "TP_ISENTO")
	@Override
	public IsentoType getIsentoType() {
		// TODO Auto-generated method stub
		return super.getIsentoType();
	}

}