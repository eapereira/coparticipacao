package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

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

	@Column(name = "CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
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

	@Column(name = "NR_CERTIFICADO")
	@Override
	public Long getCertificado() {
		// TODO Auto-generated method stub
		return super.getCertificado();
	}

	@Column(name = "NR_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

	@Column(name = "NM_TITULAR")
	@Override
	public String getNomeTitular() {
		// TODO Auto-generated method stub
		return super.getNomeTitular();
	}

	@Column(name = "VL_FATOR_MODERADOR")
	@Override
	public BigDecimal getFatorModerador() {
		// TODO Auto-generated method stub
		return super.getFatorModerador();
	}

	@Column(name = "NR_MATRICULA_ESPECIAL")
	@Override
	public Long getMatriculaEspecial() {
		// TODO Auto-generated method stub
		return super.getMatriculaEspecial();
	}

	@Column(name = "DESCR_PROFISSAO")
	@Override
	public String getProfissao() {
		// TODO Auto-generated method stub
		return super.getProfissao();
	}

	@Column(name = "CD_EMPRESA")
	@Override
	public String getCdEmpresa() {
		// TODO Auto-generated method stub
		return super.getCdEmpresa();
	}
}
