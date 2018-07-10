package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetColsDefEntity;

/**
 * The persistent class for the tb_arquivo_output_sheet_cols_def database table.
 * 
 */
public class ArquivoOutputSheetColsDefUi extends ArquivoOutputSheetColsDef implements DomainUi {
	private static final long serialVersionUID = 1L;

	public ArquivoOutputSheetColsDefUi() {
	}

	public ArquivoOutputSheetColsDefUi(ArquivoOutputSheetColsDefEntity entity) {
		super(entity);
	}

}