package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoDetailView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_RESUMO_DETAIL_CELPE_SAUDE")
@NamedQuery(
		name = "CelpeSaudeResumoDetailViewEntity.findAll",
		query = "SELECT a FROM CelpeSaudeResumoDetailViewEntity a")
public class CelpeSaudeResumoDetailViewEntity extends CelpeSaudeResumoDetailView implements DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -19822092421867115L;

	public CelpeSaudeResumoDetailViewEntity() {
		super();
	}

	@Column(name = "CD_CONTRATO")
	@Override
	public String getCdContrato() {
		// TODO Auto-generated method stub
		return super.getCdContrato();
	}

	@Column(name = "NM_SUBESTIPULANTE")
	@Override
	public String getSubEstipulante() {
		// TODO Auto-generated method stub
		return super.getSubEstipulante();
	}

	@Column(name = "NUM_VIDAS")
	@Override
	public Integer getVidas() {
		// TODO Auto-generated method stub
		return super.getVidas();
	}

	@Column(name = "VL_VALOR")
	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return super.getValor();
	}

	@Column(name = "PERC_VIDAS")
	@Override
	public Float getPercentualVidas() {
		// TODO Auto-generated method stub
		return super.getPercentualVidas();
	}

	@Column(name = "PERC_VALOR")
	@Override
	public Float getPercentualValor() {
		// TODO Auto-generated method stub
		return super.getPercentualValor();
	}
}
