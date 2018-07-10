package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraValorEntity;

/**
 * The persistent class for the tb_regra_valor database table.
 * 
 */
public class RegraValorUi extends RegraValor implements DomainUi {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876685210625847005L;

	public RegraValorUi() {
	}

	public RegraValorUi(RegraValorEntity entity) {
		super(entity);
	}

}