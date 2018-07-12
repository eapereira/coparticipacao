package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetColsDefUi;

/**
 * The persistent class for the tb_arquivo_output_sheet_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF")
@NamedQuery(
		name = "ArquivoOutputSheetColsDefEntity.findAll",
		query = "SELECT a FROM ArquivoOutputSheetColsDefEntity a")
public class ArquivoOutputSheetColsDefEntity extends ArquivoOutputSheetColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068834290665575374L;

	public ArquivoOutputSheetColsDefEntity() {
	}

	public ArquivoOutputSheetColsDefEntity(ArquivoOutputSheetColsDefUi ui) {
		super(ui);
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ViewDestinationColsDefEntity.class)
	@JoinColumn(name = "ID_VIEW_DESTINATION_COLS_DEF")
	public ViewDestinationColsDef getViewDestinationColsDef() {
		return super.getViewDestinationColsDef();
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoOutputSheetEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT_SHEET")
	@Override
	public ArquivoOutputSheet getArquivoOutputSheet() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputSheet();
	}

}