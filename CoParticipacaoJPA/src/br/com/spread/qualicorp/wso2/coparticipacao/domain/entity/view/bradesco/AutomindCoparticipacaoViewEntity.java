package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.AutomindCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_AUTOMIND")
@NamedQuery(
		name = "AutomindCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM AutomindCoparticipacaoViewEntity a")
public class AutomindCoparticipacaoViewEntity extends AutomindCoparticipacaoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846677504858955639L;

	public AutomindCoparticipacaoViewEntity() {
		super();
	}

	@Column(name = "NR_SUBFATURA")
	@Override
	public String getSubFatura() {
		// TODO Auto-generated method stub
		return super.getSubFatura();
	}

}
