package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;

/**
 * The persistent class for the tb_view_destination database table.
 * 
 */
@Entity
@Table(name = "TB_VIEW_DESTINATION")
@NamedQuery(
		name = "ViewDestinationEntity.findAll",
		query = "SELECT v FROM ViewDestinationEntity v")
public class ViewDestinationEntity extends ViewDestination {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1712156923321618863L;

	public ViewDestinationEntity() {
	}

	public ViewDestinationEntity(ViewDestinationUi ui) {
		super(ui);
	}

	@Column(name = "NM_VIEW")
	public String getNameView() {
		return super.getNameView();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(
			mappedBy = "viewDestination",
			targetEntity = ArquivoOutputSheetEntity.class)
	public List<ArquivoOutputSheet> getArquivoOutputSheets() {
		return super.getArquivoOutputSheets();
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@OneToMany(
			mappedBy = "viewDestination",
			targetEntity = ViewDestinationColsDefEntity.class)
	public List<ViewDestinationColsDef> getViewDestinationColsDefs() {
		return super.getViewDestinationColsDefs();
	}

}