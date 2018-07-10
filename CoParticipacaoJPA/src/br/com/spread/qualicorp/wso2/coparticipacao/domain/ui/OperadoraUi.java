package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;

/**
 * The persistent class for the tb_operadora database table.
 * 
 */
public class OperadoraUi extends Operadora implements DomainUi {
	private static final long serialVersionUID = 1L;

	public OperadoraUi() {
	}

	public OperadoraUi(OperadoraEntity entity) {
		super(entity);
	}

}