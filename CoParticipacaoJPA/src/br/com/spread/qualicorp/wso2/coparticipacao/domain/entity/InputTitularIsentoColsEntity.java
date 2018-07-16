package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoColsDef;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_TITULAR_ISENTO_COLS")
@NamedQuery(name = "InputTitularIsentoColsEntity.findAll", query = "SELECT a FROM InputTitularIsentoColsEntity a")
public class InputTitularIsentoColsEntity extends InputTitularIsentoCols {

	/**
	 * 
	 */
	private static final long serialVersionUID = -381229651297116410L;

	public InputTitularIsentoColsEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InputTitularIsentoEntity.class)
	@JoinColumn(name = "ID_TITULAR_ISENTO")
	@Override
	public InputTitularIsento getInputTitularIsento() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsento();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InputTitularIsentoColsDefEntity.class)
	@JoinColumn(name = "ID_TITULAR_ISENTO_COLS_DEF")
	@Override
	public InputTitularIsentoColsDef getInputTitularIsentoColsDef() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsentoColsDef();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}
}
