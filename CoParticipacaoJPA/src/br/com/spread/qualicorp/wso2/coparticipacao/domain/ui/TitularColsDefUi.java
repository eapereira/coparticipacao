package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularColsDefEntity;

/**
 * The persistent class for the tb_titular_cols_def database table.
 * 
 */
public class TitularColsDefUi extends TitularColsDef implements DomainUi {
	private static final long serialVersionUID = 1L;

	public TitularColsDefUi() {
	}

	public TitularColsDefUi(TitularColsDefEntity entity) {
		super(entity);
	}

}