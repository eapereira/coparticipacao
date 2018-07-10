package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.math.BigDecimal;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoDetailService
		extends AbstractService<LancamentoDetailUi> {

	void storeLancamentoDetail(
			LancamentoUi lancamentoUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value,
			UserUi userUi) throws ServiceException;

	void updateLancamentoDetail(
			LancamentoDetailUi lancamentoDetailUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi,
			Object value) throws ServiceException;

	Object getFieldValue(
			ArquivoInputColsDef arquivoInputColsDef,
			LancamentoDetail lancamentoDetail) throws ServiceException;

	BigDecimal getFieldValueAsBigDecimal(
			ArquivoInputColsDef arquivoInputColsDef,
			LancamentoDetail lancamentoDetail) throws ServiceException;

	LancamentoDetailUi findByArquivoInputColsDefId(
			LancamentoUi lancamentoUi,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException;

}
