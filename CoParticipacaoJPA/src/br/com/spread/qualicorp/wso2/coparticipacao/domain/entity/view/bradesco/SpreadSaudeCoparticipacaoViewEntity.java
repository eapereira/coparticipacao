package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_SPREAD_SAUDE")
@NamedQuery(
		name = "SpreadSaudeCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM SpreadSaudeCoparticipacaoViewEntity a")
public class SpreadSaudeCoparticipacaoViewEntity extends SpreadSaudeCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327846898763093804L;

	public SpreadSaudeCoparticipacaoViewEntity() {
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

	@Column(name = "NR_MATRICULA_ESPECIAL")
	@Override
	public Long getMatriculaEspecial() {
		// TODO Auto-generated method stub
		return super.getMatriculaEspecial();
	}

	@Column(name = "NR_SUBFATURA")
	@Override
	public String getSubFatura() {
		// TODO Auto-generated method stub
		return super.getSubFatura();
	}

	@Column(name = "NM_EMPRESA")
	@Override
	public String getNameEmpresa() {
		// TODO Auto-generated method stub
		return super.getNameEmpresa();
	}

	@Column(name = "NM_TITULAR")
	@Override
	public String getNameTitular() {
		// TODO Auto-generated method stub
		return super.getNameTitular();
	}

	@Column(name = "NM_BENEFICIARIO")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
	}

	@Column(name = "TP_UTILIZACAO")
	@Override
	public String getTpUtilizacao() {
		// TODO Auto-generated method stub
		return super.getTpUtilizacao();
	}

	@Column(name = "DT_UTILIZACAO")
	@Override
	public LocalDate getDtUtilizacao() {
		// TODO Auto-generated method stub
		return super.getDtUtilizacao();
	}

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValorPrincipal() {
		// TODO Auto-generated method stub
		return super.getValorPrincipal();
	}

	@Column(name = "VL_ISENTO")
	@Override
	public BigDecimal getValorIsento() {
		// TODO Auto-generated method stub
		return super.getValorIsento();
	}

	@Column(name = "TP_ISENTO")
	@Override
	public String getTpIsento() {
		// TODO Auto-generated method stub
		return super.getTpIsento();
	}

	@Column(name = "CD_PLANO")
	@Override
	public String getPlano() {
		// TODO Auto-generated method stub
		return super.getPlano();
	}
	
	@Column(name="VL_PARTICIPACAO")
	@Override
	public BigDecimal getValorParticipacao() {
		// TODO Auto-generated method stub
		return super.getValorParticipacao();
	}

	@Column(name = "DESCR_UTILIZACAO")
	@Override
	public String getDescrUtilizacao() {
		// TODO Auto-generated method stub
		return super.getDescrUtilizacao();
	}
	
}
