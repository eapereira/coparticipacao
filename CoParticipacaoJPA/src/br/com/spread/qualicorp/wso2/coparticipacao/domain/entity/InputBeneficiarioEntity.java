package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_BENEFICIARIO")
@NamedQuery(
		name = "InputBeneficiarioEntity.findAll",
		query = "SELECT a FROM InputBeneficiarioEntity a")
public class InputBeneficiarioEntity extends InputBeneficiario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8482956777233827526L;

	public InputBeneficiarioEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "inputBeneficiario",
			targetEntity = InputBeneficiarioBindEntity.class)
	@Override
	public List<InputBeneficiarioBind> getInputBeneficiarioBinds() {
		// TODO Auto-generated method stub
		return super.getInputBeneficiarioBinds();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name="ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

}
