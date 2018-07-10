package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteEntity;

/**
 * The persistent class for the tb_input_dependente database table.
 * 
 */
public class InputDependenteUi extends InputDependente implements DomainUi {
	private static final long serialVersionUID = 1L;

	public InputDependenteUi() {
	}

	public InputDependenteUi(InputDependenteEntity entity) {
		super(entity);
	}

}