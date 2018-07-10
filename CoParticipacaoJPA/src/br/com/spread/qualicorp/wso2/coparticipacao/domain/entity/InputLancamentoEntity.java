package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;

/**
 * The persistent class for the tb_input_lancamento database table.
 * 
 */
@Entity
@Table(name = "TB_INPUT_LANCAMENTO")
@NamedQuery(
		name = "InputLancamentoEntity.findAll",
		query = "SELECT i FROM InputLancamentoEntity i")
public class InputLancamentoEntity extends InputLancamento
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893000258975029145L;

	public InputLancamentoEntity() {
	}

	public InputLancamentoEntity(InputLancamentoUi ui) {
		super(ui);
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	public ArquivoInputColsDef getArquivoInputColsDef() {
		return super.getArquivoInputColsDef();
	}

	// bi-directional many-to-one association to LancamentoColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = LancamentoColsDefEntity.class)
	@JoinColumn(name = "ID_LANCAMENTO_COLS_DEF")
	public LancamentoColsDef getLancamentoColsDef() {
		return super.getLancamentoColsDef();
	}

}