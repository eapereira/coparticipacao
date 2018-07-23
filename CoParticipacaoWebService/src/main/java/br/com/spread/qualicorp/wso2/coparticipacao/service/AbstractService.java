package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface AbstractService<UI extends AbstractDomain> {
	UI findById(Long id) throws ServiceException;

	List<UI> listAll() throws ServiceException;

	void delete(UI ui) throws ServiceException;

	void save(UI ui) throws ServiceException;

	void save(List<UI> ui) throws ServiceException;

	void saveBatch(List<UI> uis) throws ServiceException;

	void saveBatch(UI ui) throws ServiceException;
}
