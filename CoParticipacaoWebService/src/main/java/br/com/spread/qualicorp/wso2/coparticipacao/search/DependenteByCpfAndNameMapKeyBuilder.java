package br.com.spread.qualicorp.wso2.coparticipacao.search;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DependenteByCpfAndNameMapKeyBuilder implements MapKeyBuilder<DependenteUi> {

	public MapKey getMapKey(DependenteUi dependenteUi) {
		return new MapKey(dependenteUi.getCpf(), dependenteUi.getNameDependente());
	}

}
