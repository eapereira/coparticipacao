package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface IsentoService extends SpreadsheetProcessorListener {

	void saveIsentos(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException;

	Object getBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException;

	void setBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			Object value) throws ServiceException;
}
