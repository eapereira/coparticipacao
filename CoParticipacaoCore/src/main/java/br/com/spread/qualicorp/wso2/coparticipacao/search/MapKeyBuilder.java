package br.com.spread.qualicorp.wso2.coparticipacao.search;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface MapKeyBuilder<T extends AbstractDomain> {

	MapKey getMapKey(T data);
}
