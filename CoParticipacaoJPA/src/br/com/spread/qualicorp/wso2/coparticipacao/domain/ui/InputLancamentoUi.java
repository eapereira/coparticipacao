package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputLancamentoEntity;

/**
 * The persistent class for the tb_input_lancamento database table.
 * 
 */
public class InputLancamentoUi extends InputLancamento implements DomainUi {
	private static final long serialVersionUID = 1L;

	public InputLancamentoUi() {
	}

	public InputLancamentoUi(InputLancamentoEntity entity) {
		super(entity);
	}

}