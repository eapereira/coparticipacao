package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Isento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoEntity;

/**
 * The persistent class for the tb_isento database table.
 * 
 */
public class IsentoUi extends Isento implements DomainUi {
	private static final long serialVersionUID = 1L;

	public IsentoUi() {
	}

	public IsentoUi(IsentoEntity entity) {
		super(entity);
	}

}