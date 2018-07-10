package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularEntity;

/**
 * The persistent class for the tb_input_titular database table.
 * 
 */
public class InputTitularUi extends InputTitular implements DomainUi {
	private static final long serialVersionUID = 1L;

	public InputTitularUi() {
	}

	public InputTitularUi(InputTitularEntity entity) {
		super(entity);
	}

}