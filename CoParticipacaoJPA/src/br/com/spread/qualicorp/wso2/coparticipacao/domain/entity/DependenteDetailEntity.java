package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteDetail;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_DEPENDENTE_DETAIL")
@NamedQuery(name = "DependenteDetailEntity.findAll", query = "SELECT l FROM DependenteDetailEntity l")
public class DependenteDetailEntity extends DependenteDetail implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6877876637147865696L;

	public DependenteDetailEntity() {
		super();
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DependenteEntity.class)
	@JoinColumn(name = "ID_DEPENDENTE")
	@Override
	public Dependente getDependente() {
		// TODO Auto-generated method stub
		return super.getDependente();
	}
}
