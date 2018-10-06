package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoExecucaoDao extends AbstractDao<ArquivoExecucaoEntity> {

	List<ArquivoExecucaoEntity> listByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano)
			throws DaoException;

	ArquivoExecucaoEntity findByArquivoInputIdAndMesAndAno(Long arquivoInputId, Integer mes, Integer ano)
			throws DaoException;

	ArquivoExecucaoEntity findByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano) throws DaoException;

	void deleteByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano) throws DaoException;

	void deleteByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException;

	void deleteByEmpresaAndUseTypeAndMesAndAno(Long empresaId, UseType useType, Integer mes, Integer ano) throws DaoException;

}
