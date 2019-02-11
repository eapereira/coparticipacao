package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.TechnitOdontoCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_TECHNIT_ODONTO")
@NamedQuery(
		name = "TechnitOdontoCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM TechnitOdontoCoparticipacaoViewEntity a")
public class TechnitOdontoCoparticipacaoViewEntity extends TechnitOdontoCoparticipacaoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846677504858955639L;

	public TechnitOdontoCoparticipacaoViewEntity() {
		super();
	}

}
