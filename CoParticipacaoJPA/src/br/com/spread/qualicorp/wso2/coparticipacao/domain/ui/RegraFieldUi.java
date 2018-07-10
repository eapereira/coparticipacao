package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraFieldEntity;

/**
 * The persistent class for the tb_regra_field database table.
 * 
 */

public class RegraFieldUi extends RegraField implements DomainUi {
	private static final long serialVersionUID = 1L;

	public RegraFieldUi() {
	}

	public RegraFieldUi(RegraFieldEntity entity) {
		super(entity);
	}

}