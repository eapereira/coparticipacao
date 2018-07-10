package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteUi;

/**
 * The persistent class for the tb_input_dependente database table.
 * 
 */
@Entity
@Table(name = "TB_INPUT_DEPENDENTE")
@NamedQuery(
		name = "InputDependenteEntity.findAll",
		query = "SELECT i FROM InputDependenteEntity i")
public class InputDependenteEntity extends InputDependente
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7968614312342460106L;

	public InputDependenteEntity() {
	}

	public InputDependenteEntity(InputDependenteUi ui) {
		super(ui);
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	public ArquivoInputColsDef getArquivoInputColsDef() {
		return super.getArquivoInputColsDef();
	}

	// bi-directional many-to-one association to DependenteColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = DependenteColsDefEntity.class)
	@JoinColumn(name = "ID_DEPENDENTE_COLS_DEF")
	public DependenteColsDef getDependenteColsDef() {
		return super.getDependenteColsDef();
	}

}