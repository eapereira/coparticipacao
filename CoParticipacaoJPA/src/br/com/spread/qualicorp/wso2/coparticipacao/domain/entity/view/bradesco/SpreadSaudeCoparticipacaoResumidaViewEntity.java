package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoResumidaView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_RESUMIDA_SPREAD_SAUDE")
@NamedQuery(
		name = "SpreadSaudeCoparticipacaoResumidaViewEntity.findAll",
		query = "SELECT a FROM SpreadSaudeCoparticipacaoResumidaViewEntity a")
public class SpreadSaudeCoparticipacaoResumidaViewEntity extends SpreadSaudeCoparticipacaoResumidaView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7293567688284875397L;

	public SpreadSaudeCoparticipacaoResumidaViewEntity() {
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

	@Column(name = "CD_PLANO")
	@Override
	public String getPlano() {
		// TODO Auto-generated method stub
		return super.getPlano();
	}
}
