package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;

/**
 * The persistent class for the tb_dependente database table.
 * 
 */
public class DependenteUi extends Dependente {
	private static final long serialVersionUID = 1L;

	public DependenteUi() {
	}

	public DependenteUi(DependenteEntity entity) {
		super(entity);
	}

}