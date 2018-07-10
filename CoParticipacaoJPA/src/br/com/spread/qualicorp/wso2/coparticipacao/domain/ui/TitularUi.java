package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * The persistent class for the tb_titular database table.
 * 
 */
public class TitularUi extends Titular implements DomainUi {
	private static final long serialVersionUID = 1L;

	public TitularUi() {
	}

	public TitularUi(TitularEntity entity) {
		super(entity);
	}

}