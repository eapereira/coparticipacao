package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputCols;

/**
 * The persistent class for the tb_input_lancamento database table.
 * 
 */
@Entity
@Table(name = "TB_LANCAMENTO_INPUT")
@NamedQuery(name = "LancamentoInputEntity.findAll", query = "SELECT i FROM LancamentoInputEntity i")
public class LancamentoInputEntity extends LancamentoInput implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893000258975029145L;

	public LancamentoInputEntity() {
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	public ArquivoInput getArquivoInput() {
		return super.getArquivoInput();
	}

	// bi-directional many-to-one association to LancamentoColsDef
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			targetEntity = LancamentoInputColsEntity.class,
			mappedBy = "lancamentoInput")
	@Override
	public List<LancamentoInputCols> getLancamentoInputCols() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputCols();
	}

}