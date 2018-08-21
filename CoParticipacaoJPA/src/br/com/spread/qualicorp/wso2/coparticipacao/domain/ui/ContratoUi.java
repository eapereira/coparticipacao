package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;

/**
 * The persistent class for the tb_contrato database table.
 * 
 */
public class ContratoUi extends Contrato {
	private static final long serialVersionUID = 1L;

	public ContratoUi() {
	}

	public ContratoUi(ContratoEntity entity) {
		super(entity);
	}

}