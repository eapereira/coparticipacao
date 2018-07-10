package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF")
@NamedQuery(
		name = "ArquivoOutputDesconhecidoColsDefEntity.findAll",
		query = "SELECT a FROM ArquivoOutputDesconhecidoColsDefEntity a")
public class ArquivoOutputDesconhecidoColsDefEntity
		extends ArquivoOutputDesconhecidoColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5777301581299350578L;

	public ArquivoOutputDesconhecidoColsDefEntity() {
		super();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = ArquivoOutputDesconhecidoEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT_DESCONHECIDO")
	@Override
	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecido();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

}
