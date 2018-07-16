package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoColsDef;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_DEPENDENTE_ISENTO_COLS")
@NamedQuery(name = "InputDependenteIsentoColsEntity.findAll", query = "SELECT a FROM InputDependenteIsentoColsEntity a")
public class InputDependenteIsentoColsEntity extends InputDependenteIsentoCols {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722568460617148206L;

	public InputDependenteIsentoColsEntity() {

	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InputDependenteIsentoEntity.class)
	@JoinColumn(name = "ID_INPUT_DEPENDENTE_ISENTO")
	@Override
	public InputDependenteIsento getInputDependenteIsento() {
		// TODO Auto-generated method stub
		return super.getInputDependenteIsento();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InputDependenteIsentoColsDefEntity.class)
	@JoinColumn(name = "ID_INPUT_DEPENDENTE_ISENTO_COLS_DEF")
	@Override
	public InputDependenteIsentoColsDef getInputDependenteIsentoColsDef() {
		// TODO Auto-generated method stub
		return super.getInputDependenteIsentoColsDef();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}
}
