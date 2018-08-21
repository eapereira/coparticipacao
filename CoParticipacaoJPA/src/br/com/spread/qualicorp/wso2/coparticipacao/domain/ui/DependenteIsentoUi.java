package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * The persistent class for the tb_dependente_isento database table.
 * 
 */
public class DependenteIsentoUi extends DependenteIsento {
	private static final long serialVersionUID = 1L;

	public DependenteIsentoUi() {
	}

	public DependenteIsentoUi(DependenteIsentoEntity entity) {
		super(entity);
	}

}