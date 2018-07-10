package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;

/**
 * The persistent class for the tb_arquivo_output database table.
 * 
 */
public class ArquivoOutputUi extends ArquivoOutput implements DomainUi {
	private static final long serialVersionUID = 1L;

	public ArquivoOutputUi() {
	}

	public ArquivoOutputUi(ArquivoOutputEntity entity) {
		super(entity);
	}

}