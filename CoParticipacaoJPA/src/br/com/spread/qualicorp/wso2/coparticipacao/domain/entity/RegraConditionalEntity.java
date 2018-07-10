package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalResult;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGRA_CONDITIONAL")
@NamedQuery(
		name = "RegraConditionalEntity.findAll",
		query = "SELECT r FROM RegraConditionalEntity r")
public class RegraConditionalEntity extends RegraConditional {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8274415405265173790L;

	public RegraConditionalEntity() {
		super();
	}

	@Column(name = "CD_ORDEM")
	public Integer getOrdem() {
		return super.getOrdem();
	}

	@Column(name = "NM_REGRA_CONDITIONAL")
	public String getNameRegra() {
		return super.getNameRegra();
	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	public ArquivoInput getArquivoInput() {
		return super.getArquivoInput();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regraConditional",
			targetEntity = RegraConditionalOperationEntity.class)
	public List<RegraConditionalOperation> getRegraConditionalOperations() {
		return super.getRegraConditionalOperations();
	}

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "regraConditional",
			targetEntity = RegraConditionalResultEntity.class)
	@Override
	public List<RegraConditionalResult> getRegraConditionalResults() {
		return super.getRegraConditionalResults();
	}
}
