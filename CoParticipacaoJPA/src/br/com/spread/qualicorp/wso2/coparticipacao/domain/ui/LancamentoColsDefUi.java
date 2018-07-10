package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoColsDefEntity;

/**
 * The persistent class for the tb_lancamento_cols_def database table.
 * 
 */
public class LancamentoColsDefUi extends LancamentoColsDef implements DomainUi {
	private static final long serialVersionUID = 1L;

	public LancamentoColsDefUi() {
	}

	public LancamentoColsDefUi(LancamentoColsDefEntity entity) {
		super(entity);
	}

}