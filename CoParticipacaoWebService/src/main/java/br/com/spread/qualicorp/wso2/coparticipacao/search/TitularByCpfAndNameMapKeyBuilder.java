package br.com.spread.qualicorp.wso2.coparticipacao.search;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TitularByCpfAndNameMapKeyBuilder implements MapKeyBuilder<TitularUi> {

	public MapKey getMapKey(TitularUi titularUi) {
		return new MapKey(titularUi.getCpf(), titularUi.getNameTitular());
	}

}
