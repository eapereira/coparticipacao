package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;

/**
 * The persistent class for the tb_arquivo_output_sheet database table.
 * 
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_SHEET")
@NamedQuery(
		name = "ArquivoOutputSheetEntity.findAll",
		query = "SELECT a FROM ArquivoOutputSheetEntity a")
public class ArquivoOutputSheetEntity extends ArquivoOutputSheet
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1335059184039233114L;

	public ArquivoOutputSheetEntity() {
	}

	public ArquivoOutputSheetEntity(ArquivoOutputSheetUi ui) {
		super(ui);
	}

	@Column(name = "NM_SHEET")
	public String getNmSheet() {
		return super.getNmSheet();
	}

	// bi-directional many-to-one association to ArquivoOutput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoOutputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT")
	public ArquivoOutput getArquivoOutput() {
		return super.getArquivoOutput();
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ViewDestinationEntity.class)
	@JoinColumn(name = "ID_VIEW_DESTINATION")
	public ViewDestination getViewDestination() {
		return super.getViewDestination();
	}

}