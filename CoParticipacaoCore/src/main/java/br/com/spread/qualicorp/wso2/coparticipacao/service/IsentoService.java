package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetColsUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface IsentoService {

	boolean hasIsento(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void processIsento(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void saveIsentos(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException;

	BigDecimal getFieldValueAsBigDecimal(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException;

	Object getBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException;

	void setBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			Object value) throws ServiceException;

	void setFieldValueAsBigDecimal(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			BigDecimal value) throws ServiceException;

}
