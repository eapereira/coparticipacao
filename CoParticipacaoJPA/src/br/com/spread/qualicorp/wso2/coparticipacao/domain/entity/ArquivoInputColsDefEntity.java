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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;

/**
 * The persistent class for the tb_arquivo_input_cols_def database table.
 * 
 */
@Entity
@Table(name = "TB_ARQUIVO_INPUT_COLS_DEF")
@NamedQuery(
		name = "ArquivoInputColsDefEntity.findAll",
		query = "SELECT a FROM ArquivoInputColsDefEntity a")
public class ArquivoInputColsDefEntity extends ArquivoInputColsDef
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321626170497457640L;

	public ArquivoInputColsDefEntity() {
	}

	public ArquivoInputColsDefEntity(ArquivoInputColsDefUi ui) {
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

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	public ArquivoInput getArquivoInput() {
		return super.getArquivoInput();
	}

	// bi-directional many-to-one association to InputDependente
	@OneToMany(
			mappedBy = "arquivoInputColsDef",
			targetEntity = InputDependenteEntity.class)
	public List<InputDependente> getInputDependentes() {
		return super.getInputDependentes();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(
			mappedBy = "arquivoInputColsDef",
			targetEntity = InputLancamentoEntity.class)
	public List<InputLancamento> getInputLancamentos() {
		return super.getInputLancamentos();
	}

	// bi-directional many-to-one association to InputTitular
	@OneToMany(
			mappedBy = "arquivoInputColsDef",
			targetEntity = InputTitularEntity.class)
	public List<InputTitular> getInputTitulars() {
		return super.getInputTitulars();
	}

	// bi-directional many-to-one association to InputTitular
	@OneToMany(
			mappedBy = "arquivoInputColsDef",
			targetEntity = RegraResultEntity.class)
	@Override
	public List<RegraResult> getRegraResults() {
		// TODO Auto-generated method stub
		return super.getRegraResults();
	}

	@Column(name = "CD_FORMAT")
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return super.getFormat();
	}

}