package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeRateioView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_RATEIO_CELPE_SAUDE")
@NamedQuery(name = "CelpeSaudeRateioViewEntity.findAll", query = "SELECT a FROM CelpeSaudeRateioViewEntity a")
public class CelpeSaudeRateioViewEntity extends CelpeSaudeRateioView implements DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5830038279750394899L;

	public CelpeSaudeRateioViewEntity() {
		super();
	}

	@Column(name = "NM_TITULAR")
	@Override
	public String getNameTitular() {
		// TODO Auto-generated method stub
		return super.getNameTitular();
	}

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return super.getValor();
	}
}
