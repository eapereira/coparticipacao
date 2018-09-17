package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ArquivoOutputService extends AbstractService<ArquivoOutputUi> {

	void writeOutputFile(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	String createFileName(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputUi arquivoOutputUi,
			ContratoUi contratoUi) throws ServiceException;

	List<ArquivoOutputUi> listByEmpresaIdAndUseType(EmpresaUi empresaUi, UseType extraFile) throws ServiceException;

}
