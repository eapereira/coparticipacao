package br.com.spread.qualicorp.wso2.coparticipacao.search;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TitularByMatriculaAndNameMapKeyBuilder implements MapKeyBuilder<TitularUi> {

	public MapKey getMapKey(TitularUi titularUi) {
		return new MapKey(titularUi.getMatricula(), titularUi.getNameTitular());
	}

}
