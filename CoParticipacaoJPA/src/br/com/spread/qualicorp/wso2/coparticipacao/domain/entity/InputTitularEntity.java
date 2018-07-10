package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;

/**
 * The persistent class for the tb_input_titular database table.
 * 
 */
@Entity
@Table(name = "TB_INPUT_TITULAR")
@NamedQuery(
		name = "InputTitularEntity.findAll",
		query = "SELECT i FROM InputTitularEntity i")
public class InputTitularEntity extends InputTitular implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360185099737525032L;

	public InputTitularEntity() {
	}

	public InputTitularEntity(InputTitularUi ui) {
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

	// bi-directional many-to-one association to TitularColsDef
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = TitularColsDefEntity.class)
	@JoinColumn(name = "ID_TITULAR_COLS_DEF")
	public TitularColsDef getTitularColsDef() {
		return super.getTitularColsDef();
	}

}