package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * The persistent class for the tb_lancamento database table.
 * 
 */
public class LancamentoUi extends Lancamento {
	private static final long serialVersionUID = 1L;

	private LocalDate dtMovimento;
	
	public LancamentoUi() {
		setLancamentoDetails(new ArrayList<LancamentoDetail>());
	}

	public LancamentoUi(LancamentoEntity entity) {
		super(entity);
	}

	public LocalDate getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(LocalDate dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

}