package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoDetailService {

	void setFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType, Object value)
			throws ServiceException;

	Object getFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType)
			throws ServiceException;

	BigDecimal getFieldValueAsBigDecimal(
			LancamentoInputColsUi lancamentoInputColsUi,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException;

	LancamentoInputColsUi findByArquivoInputColsDefId(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoInputColsDefUi arquivoInputColsDef) throws ServiceException;

	void showLancamentoDetailInfo(LancamentoDetailUi lancamentoDetailUi) throws ServiceException;

	LancamentoDetailUi create(CoParticipacaoContext coParticipacaoContext) throws ServiceException;
}
