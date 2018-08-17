package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */
public class EmpresaUi extends Empresa {
	private static final long serialVersionUID = 1L;

	public EmpresaUi() {
	}

	public EmpresaUi(EmpresaEntity entity) {
		super(entity);
	}

}