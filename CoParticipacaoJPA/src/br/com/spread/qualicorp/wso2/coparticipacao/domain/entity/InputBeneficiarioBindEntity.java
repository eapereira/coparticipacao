package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_BENEFICIARIO_BIND")
@NamedQuery(
		name = "InputBeneficiarioBindEntity.findAll",
		query = "SELECT a FROM InputBeneficiarioBindEntity a")
public class InputBeneficiarioBindEntity extends InputBeneficiarioBind {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2987965007744037960L;

	public InputBeneficiarioBindEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			targetEntity = InputBeneficiarioEntity.class)
	@JoinColumn(name = "ID_INPUT_BENEFICIARIO")
	@Override
	public InputBeneficiario getInputBeneficiario() {
		// TODO Auto-generated method stub
		return super.getInputBeneficiario();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			targetEntity = BeneficiarioColsDefEntity.class)
	@JoinColumn(name = "ID_BENEFICIARIO_COLS_DEF")
	@Override
	public BeneficiarioColsDef getBeneficiarioColsDef() {
		// TODO Auto-generated method stub
		return super.getBeneficiarioColsDef();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

}
