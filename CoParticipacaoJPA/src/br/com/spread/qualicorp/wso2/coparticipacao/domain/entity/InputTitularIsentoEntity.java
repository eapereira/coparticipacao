package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_TITULAR_ISENTO")
@NamedQuery(name = "InputTitularIsentoEntity.findAll", query = "SELECT a FROM InputTitularIsentoEntity a")
public class InputTitularIsentoEntity extends InputTitularIsento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8492348823086132060L;

	public InputTitularIsentoEntity() {

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
			mappedBy = "inputTitularIsento",
			targetEntity = InputTitularIsentoColsEntity.class)
	@OrderColumn(name="INDEX")
	@Override
	public List<InputTitularIsentoCols> getInputTitularIsentoCols() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsentoCols();
	}
}
