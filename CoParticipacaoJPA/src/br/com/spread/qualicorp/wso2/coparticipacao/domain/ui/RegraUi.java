package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;

/**
 * The persistent class for the tb_regra database table.
 * 
 */
public class RegraUi extends Regra implements DomainUi {
	private static final long serialVersionUID = 1L;

	public RegraUi() {
	}

	public RegraUi(RegraEntity entity) {
		super(entity);
	}

}