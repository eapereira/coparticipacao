package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_RESUMO_CELPE_SAUDE")
@NamedQuery(name = "CelpeSaudeResumoViewEntity.findAll", query = "SELECT a FROM CelpeSaudeResumoViewEntity a")
public class CelpeSaudeResumoViewEntity extends CelpeSaudeResumoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3421734403084327449L;

	public CelpeSaudeResumoViewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "NM_RAMO")
	@Override
	public String getRamo() {
		// TODO Auto-generated method stub
		return super.getRamo();
	}

	@Column(name = "NM_OPERADORA")
	@Override
	public String getOperadora() {
		// TODO Auto-generated method stub
		return super.getOperadora();
	}

	@Column(name = "DESCR_COMPETENCIA")
	@Override
	public String getCompetencia() {
		// TODO Auto-generated method stub
		return super.getCompetencia();
	}

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return super.getValor();
	}

	@Column(name = "DESCR_COMPETENCIA_ANTERIOR")
	@Override
	public String getCompetenciaAnterior() {
		// TODO Auto-generated method stub
		return super.getCompetenciaAnterior();
	}

	@Column(name = "VL_PRINCIPAL_ANTERIOR")
	@Override
	public BigDecimal getValorAnterior() {
		// TODO Auto-generated method stub
		return super.getValorAnterior();
	}

	@Column(name = "CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
	}

	@Column(name = "CD_ANO")
	@Override
	public Integer getAno() {
		// TODO Auto-generated method stub
		return super.getAno();
	}

}
