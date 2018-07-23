package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularColsDefUi;

/**
 * The persistent class for the tb_titular_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_TITULAR_COLS_DEF")
@NamedQuery(
		name = "TitularColsDefEntity.findAll",
		query = "SELECT t FROM TitularColsDefEntity t")
public class TitularColsDefEntity extends TitularColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8540012444942632250L;

	public TitularColsDefEntity() {
	}

	public TitularColsDefEntity(TitularColsDefUi ui) {
		super(ui);
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
	public int getLength() {
		return super.getLength();
	}

	// bi-directional many-to-one association to InputTitular
	@OneToMany(
			mappedBy = "titularColsDef",
			targetEntity = InputTitularEntity.class)
	@OrderColumn(name="INDEX")
	public List<InputTitular> getInputTitulars() {
		return super.getInputTitulars();
	}

}