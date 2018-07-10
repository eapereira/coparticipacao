package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraOperationEntity;

/**
 * The persistent class for the tb_regra_operation database table.
 * 
 */
public class RegraOperationUi extends RegraOperation {
	private static final long serialVersionUID = 1L;

	public RegraOperationUi() {
	}

	public RegraOperationUi(RegraOperationEntity entity) {
		super(entity);
	}

}