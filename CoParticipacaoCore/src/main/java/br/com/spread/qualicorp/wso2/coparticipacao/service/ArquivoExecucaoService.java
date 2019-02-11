package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.io.InputStream;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoExecucaoService extends AbstractService<ArquivoExecucaoUi> {

	List<ArquivoExecucaoUi> listByEmpresaIdAndMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano)
			throws ServiceException;

	List<ArquivoExecucaoUi> listByEmpresaIdAndMesAndAnoToUser(EmpresaUi empresaUi, Integer mes, Integer ano)
			throws ServiceException;

	void updateStatus(CoParticipacaoContext coParticipacaoContext, StatusExecucaoType statusExecucaoType)
			throws ServiceException;

	ArquivoExecucaoUi findByArquivoInputIdAndMesAndAno(ArquivoInputUi arquivoInputUi, Integer mes, Integer ano)
			throws ServiceException;

	ArquivoExecucaoUi sendToProcess(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException;

	void sendToProcess(List<ArquivoExecucao> arquivoExecucaos) throws ServiceException;

	ArquivoExecucaoUi saveFile(UserUi userUi, InputStream inputstream, String fileName) throws ServiceException;

	ArquivoExecucaoUi findByContratoIdAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano)
			throws ServiceException;

	ArquivoExecucaoUi createArquivoExecucao(CoParticipacaoContext coParticipacaoContext, UseType useType)
			throws ServiceException;

	void deleteByContratoIdAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano) throws ServiceException;

	boolean isJobDone(ArquivoExecucaoUi arquivoExecucaoUi, UserUi userUi) throws ServiceException;

	void deleteByEmpresaIdAndMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException;

	void renameToProcess(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException;

	void deleteByEmpresaAndUseTypeAndMesAndAno(EmpresaUi empresaUi, UseType useType, Integer mes, Integer ano)
			throws ServiceException;

	ArquivoExecucaoUi createArquivoExecucao(CoParticipacaoContext coParticipacaoContext, ContratoUi contratoUi)
			throws ServiceException;

}
