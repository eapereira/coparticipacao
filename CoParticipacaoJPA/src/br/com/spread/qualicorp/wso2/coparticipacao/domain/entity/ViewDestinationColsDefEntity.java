package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;

/**
 * The persistent class for the tb_view_destination_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_VIEW_DESTINATION_COLS_DEF")
@NamedQuery(
		name = "ViewDestinationColsDefEntity.findAll",
		query = "SELECT v FROM ViewDestinationColsDefEntity v")
public class ViewDestinationColsDefEntity extends ViewDestinationColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 723861034810137148L;

	public ViewDestinationColsDefEntity() {
	}

	public ViewDestinationColsDefEntity(ViewDestinationColsDefUi ui) {
		super(ui);
	}

	@Column(name = "CD_ORDEM")
	public Integer getOrdem() {
		return super.getOrdem();
	}

	@Convert(converter = ColDefTypeConverter.class)
	@Column(name = "CD_TYPE")
	public ColDefType getType() {
		return super.getType();
	}

	@Column(name = "NM_COLUMN")
	public String getNameColumn() {
		return super.getNameColumn();
	}

	@Column(name = "VL_LENGTH")
	public Integer getLength() {
		return super.getLength();
	}

	@Column(name = "CD_FORMAT")
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return super.getFormat();
	}

	@Column(name = "NM_COL_TITLE_LABEL")
	@Override
	public String getColumnTitleLabel() {
		// TODO Auto-generated method stub
		return super.getColumnTitleLabel();
	}

	// bi-directional many-to-one association to ArquivoOutputSheetColsDef
	@OneToMany(
			mappedBy = "viewDestinationColsDef",
			targetEntity = ArquivoOutputSheetColsDefEntity.class)
	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefs() {
		return super.getArquivoOutputSheetColsDefs();
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