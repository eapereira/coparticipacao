package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteColsDefUi;

/**
 * The persistent class for the tb_dependente_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_DEPENDENTE_COLS_DEF")
@NamedQuery(
		name = "DependenteColsDefEntity.findAll",
		query = "SELECT d FROM DependenteColsDefEntity d")
public class DependenteColsDefEntity extends DependenteColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8255736615036608632L;

	public DependenteColsDefEntity() {
	}

	public DependenteColsDefEntity(DependenteColsDefUi ui) {
		super(ui);
	}

	@Convert(converter = ColDefTypeConverter.class)
	@Column(name = "CD_TYPE")
	public ColDefType getCdType() {
		return super.getCdType();
	}

	@Column(name = "NM_COLUMN")
	public String getNameColumn() {
		return super.getNameColumn();
	}

	@Column(name = "VL_LENGTH")
	public Integer getLength() {
		return super.getLength();
	}

	// bi-directional many-to-one association to InputDependente
	@OneToMany(
			mappedBy = "dependenteColsDef",
			targetEntity = InputDependenteEntity.class)
	public List<InputDependente> getInputDependentes() {
		return super.getInputDependentes();
	}

}