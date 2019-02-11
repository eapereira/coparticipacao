package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_RESUMO_SPREAD_SAUDE")
@NamedQuery(name = "SpreadSaudeResumoViewEntity.findAll", query = "SELECT a FROM SpreadSaudeResumoViewEntity a")
public class SpreadSaudeResumoViewEntity extends SpreadSaudeResumoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4202046795341131632L;

	public SpreadSaudeResumoViewEntity() {
		super();
	}

	@Column(name="CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
	}

	@Column(name="CD_ANO")
	@Override
	public Integer getAno() {
		// TODO Auto-generated method stub
		return super.getAno();
	}

	@Column(name="ID_CONTRATO")
	@Override
	public Long getIdContrato() {
		// TODO Auto-generated method stub
		return super.getIdContrato();
	}

	@Column(name="NM_EMPRESA")
	@Override
	public String getNameEmpresa() {
		// TODO Auto-generated method stub
		return super.getNameEmpresa();
	}

	@Column(name="VL_PRINCIPAL")
	@Override
	public BigDecimal getValorPrincipal() {
		// TODO Auto-generated method stub
		return super.getValorPrincipal();
	}

	@Column(name="VL_ISENTO")
	@Override
	public BigDecimal getValorIsento() {
		// TODO Auto-generated method stub
		return super.getValorIsento();
	}

	@Column(name="NR_SUBFATURA")
	@Override
	public String getSubFatura() {
		// TODO Auto-generated method stub
		return super.getSubFatura();
	}

	@Column(name="VL_PARTICIPACAO")
	@Override
	public BigDecimal getValorParticipacao() {
		// TODO Auto-generated method stub
		return super.getValorParticipacao();
	}
}
