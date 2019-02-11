package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.LMTransportesResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_RESUMO_LM_TRANSPORTES")
@NamedQuery(name = "LMTransportesResumoViewEntity.findAll", query = "SELECT a FROM LMTransportesResumoViewEntity a")
public class LMTransportesResumoViewEntity extends LMTransportesResumoView implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3771147068544162738L;

	public LMTransportesResumoViewEntity() {
		super();
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

	@Column(name = "ID_CONTRATO")
	@Override
	public Long getIdContrato() {
		// TODO Auto-generated method stub
		return super.getIdContrato();
	}

	@Column(name = "CD_CONTRATO")
	@Override
	public String getCdContrato() {
		// TODO Auto-generated method stub
		return super.getCdContrato();
	}

	@Column(name = "CD_EMPRESA")
	@Override
	public String getCdEmpresa() {
		// TODO Auto-generated method stub
		return super.getCdEmpresa();
	}

	@Column(name = "QTDE_SEGURADOS")
	@Override
	public Integer getQtdeSegurados() {
		// TODO Auto-generated method stub
		return super.getQtdeSegurados();
	}

	@Column(name = "VL_PROPORCAO")
	@Override
	public BigDecimal getValorProporcao() {
		// TODO Auto-generated method stub
		return super.getValorProporcao();
	}

	@Column(name = "NR_SUBFATURA")
	@Override
	public String getSubFatura() {
		// TODO Auto-generated method stub
		return super.getSubFatura();
	}

	@Column(name = "VL_ALOCACAO")
	@Override
	public BigDecimal getValorAlocacao() {
		// TODO Auto-generated method stub
		return super.getValorAlocacao();
	}
}
