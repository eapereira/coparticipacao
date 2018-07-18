package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_DEPENDENTE_ISENTO")
@NamedQuery(
		name = "InputDependenteIsentoEntity.findAll",
		query = "SELECT a FROM InputDependenteIsentoEntity a")
public class InputDependenteIsentoEntity extends InputDependenteIsento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6688053017410300779L;

	public InputDependenteIsentoEntity() {

	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = InputDependenteIsentoColsEntity.class,
			mappedBy = "inputDependenteIsento")
	@Override
	public List<InputDependenteIsentoCols> getInputDependenteIsentoCols() {
		// TODO Auto-generated method stub
		return super.getInputDependenteIsentoCols();
	}
}
