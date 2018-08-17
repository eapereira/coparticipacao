package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_TITULAR_DETAIL")
@NamedQuery(name = "TitularDetailEntity.findAll", query = "SELECT l FROM TitularDetailEntity l")
public class TitularDetailEntity extends TitularDetail implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2680825663724393712L;

	public TitularDetailEntity() {
		super();
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TitularEntity.class)
	@JoinColumn(name = "ID_TITULAR")
	@Override
	public Titular getTitular() {
		// TODO Auto-generated method stub
		return super.getTitular();
	}
}
