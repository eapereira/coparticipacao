package br.com.spread.qualicorp.wso2.coparticipacao.mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AbstractMapper<ENTITY extends AbstractDomain> {

	ENTITY findById(Long id);
}
