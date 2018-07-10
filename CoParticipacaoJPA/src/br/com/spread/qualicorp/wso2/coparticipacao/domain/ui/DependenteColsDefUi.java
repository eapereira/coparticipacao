package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteColsDefEntity;

/**
 * The persistent class for the tb_dependente_cols_def database table.
 * 
 */
public class DependenteColsDefUi extends DependenteColsDef implements DomainUi {
	private static final long serialVersionUID = 1L;

	public DependenteColsDefUi() {
	}

	public DependenteColsDefUi(DependenteColsDefEntity entity) {
		super(entity);
	}

}