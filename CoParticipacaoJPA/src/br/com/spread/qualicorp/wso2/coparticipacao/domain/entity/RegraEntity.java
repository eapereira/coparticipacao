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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.RegraTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * The persistent class for the tb_regra database table.
 * 
 */
@Entity
@Table(name = "TB_REGRA")
@NamedQuery(name = "RegraEntity.findAll", query = "SELECT r FROM RegraEntity r")
public class RegraEntity extends Regra {

	/**
	 * 
	 */
	private static final long serialVersionUID = 650641467469264264L;

	public RegraEntity() {
	}

	public RegraEntity(RegraUi ui) {
		super(ui);
	}

	@Column(name = "CD_ORDEM")
	public Integer getOrdem() {
		return super.getOrdem();
	}

	@Column(name = "NM_REGRA")
	public String getNameRegra() {
		return super.getNameRegra();
	}

	@Convert(converter = RegraTypeConverter.class)
	@Column(name = "TP_REGRA")
	public RegraType getTpRegra() {
		return super.getTpRegra();
	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	public ArquivoInput getArquivoInput() {
		return super.getArquivoInput();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regra",
			targetEntity = RegraOperationEntity.class)
	public List<RegraOperation> getRegraOperations() {
		return super.getRegraOperations();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regra",
			targetEntity = RegraResultEntity.class)
	@Override
	public List<RegraResult> getRegraResults() {
		return super.getRegraResults();
	}

}