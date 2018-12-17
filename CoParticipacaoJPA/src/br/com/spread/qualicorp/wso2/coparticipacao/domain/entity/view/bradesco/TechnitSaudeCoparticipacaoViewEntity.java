package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.TechnitSaudeCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_TECHNIT_SAUDE")
@NamedQuery(
		name = "TechnitSaudeCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM TechnitSaudeCoparticipacaoViewEntity a")
public class TechnitSaudeCoparticipacaoViewEntity extends TechnitSaudeCoparticipacaoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846677504858955639L;

	public TechnitSaudeCoparticipacaoViewEntity() {
		super();
	}

	@Column(name="NM_BENEFICIARIO")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
	}

	@Column(name="VL_PRINCIPAL")
	@Override
	public BigDecimal getValorPrincipal() {
		// TODO Auto-generated method stub
		return super.getValorPrincipal();
	}

	@Column(name="VL_PARTICIPACAO")
	@Override
	public BigDecimal getValorParticipacao() {
		// TODO Auto-generated method stub
		return super.getValorParticipacao();
	}

}
