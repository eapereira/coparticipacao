package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;

/**
 * The persistent class for the tb_titular_isento database table.
 * 
 */
@Entity
@Table(name = "TB_TITULAR_ISENTO")
@NamedQuery(name = "TitularIsentoEntity.findAll", query = "SELECT t FROM TitularIsentoEntity t")
public class TitularIsentoEntity extends TitularIsento implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721656804558459067L;

	public TitularIsentoEntity() {
	}

	// bi-directional many-to-one association to Titular
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TitularEntity.class)
	@JoinColumn(name = "ID_TITULAR")
	public Titular getTitular() {
		return super.getTitular();
	}

}