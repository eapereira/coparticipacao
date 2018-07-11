package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO")
@NamedQuery(
		name = "ArquivoInputOutputDesconhecidoEntity.findAll",
		query = "SELECT a FROM ArquivoInputOutputDesconhecidoEntity a")
public class ArquivoInputOutputDesconhecidoEntity
		extends ArquivoInputOutputDesconhecido {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706506292320455272L;

	public ArquivoInputOutputDesconhecidoEntity() {

	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoOutputDesconhecidoColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF")
	@Override
	public ArquivoOutputDesconhecidoColsDef getArquivoOutputDesconhecidoColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecidoColsDef();
	}
}
