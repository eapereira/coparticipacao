package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.LMTransportesCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_LM_TRANSPORTES")
@NamedQuery(
		name = "LMTransportesCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM LMTransportesCoparticipacaoViewEntity a")
public class LMTransportesCoparticipacaoViewEntity extends LMTransportesCoparticipacaoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846677504858955639L;

	public LMTransportesCoparticipacaoViewEntity() {
		super();
	}
	
	@Column(name = "VL_FATOR_MODERADOR_INSS")
	@Override
	public BigDecimal getFatorModeradorInss() {
		// TODO Auto-generated method stub
		return super.getFatorModeradorInss();
	}
	
}
