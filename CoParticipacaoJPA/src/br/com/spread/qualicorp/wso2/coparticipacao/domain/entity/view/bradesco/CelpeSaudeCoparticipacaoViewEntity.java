package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_COPARTICIPACAO_CELPE_SAUDE")
@NamedQuery(
		name = "CelpeSaudeCoparticipacaoViewEntity.findAll",
		query = "SELECT a FROM CelpeSaudeCoparticipacaoViewEntity a")
public class CelpeSaudeCoparticipacaoViewEntity extends CelpeSaudeCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1873447059046877235L;

	public CelpeSaudeCoparticipacaoViewEntity() {
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

	@Column(name = "CD_CONTRATO")
	@Override
	public String getCdContrato() {
		// TODO Auto-generated method stub
		return super.getCdContrato();
	}

	@Column(name = "CD_USUARIO")
	@Override
	public String getCdUsuario() {
		// TODO Auto-generated method stub
		return super.getCdUsuario();
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

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return super.getValor();
	}

	@Column(name = "NR_CERTIFICADO")
	@Override
	public String getCertificado() {
		// TODO Auto-generated method stub
		return super.getCertificado();
	}

	@Column(name = "NR_MATRICULA_ESPECIAL")
	@Override
	public String getMatriculaEspecial() {
		// TODO Auto-generated method stub
		return super.getMatriculaEspecial();
	}

	@Column(name = "CD_PLANO")
	@Override
	public String getPlano() {
		// TODO Auto-generated method stub
		return super.getPlano();
	}

	@Column(name = "NR_SUBFATURA")
	@Override
	public String getSubFatura() {
		// TODO Auto-generated method stub
		return super.getSubFatura();
	}

	@Column(name = "NR_CARTEIRA_IDENT")
	@Override
	public String getCarteiraIdentificacao() {
		// TODO Auto-generated method stub
		return super.getCarteiraIdentificacao();
	}

	@Column(name = "NR_CPF_BENEFICIARIO")
	@Override
	public String getCpfBeneficiario() {
		// TODO Auto-generated method stub
		return super.getCpfBeneficiario();
	}
}
