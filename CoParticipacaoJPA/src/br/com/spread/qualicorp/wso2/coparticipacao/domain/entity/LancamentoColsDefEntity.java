package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoColsDefUi;

/**
 * The persistent class for the tb_lancamento_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_LANCAMENTO_COLS_DEF")
@NamedQuery(
		name = "LancamentoColsDefEntity.findAll",
		query = "SELECT l FROM LancamentoColsDefEntity l")
public class LancamentoColsDefEntity extends LancamentoColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6203725845793858400L;

	public LancamentoColsDefEntity() {
	}

	public LancamentoColsDefEntity(LancamentoColsDefUi ui) {
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
	public Integer getLength() {
		return super.getLength();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(
			mappedBy = "lancamentoColsDef",
			targetEntity = InputLancamentoEntity.class)
	public List<InputLancamento> getInputLancamentos() {
		return super.getInputLancamentos();
	}

}