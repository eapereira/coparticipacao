package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DynamicService {
	List<DynamicEntity> listByEmpresaAndMesAndAno(String sql, int mes, int ano)
			throws ServiceException;

}
