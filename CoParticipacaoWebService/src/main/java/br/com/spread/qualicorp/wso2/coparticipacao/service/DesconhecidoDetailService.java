package br.com.spread.qualicorp.wso2.coparticipacao.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoDetailService
		extends AbstractService<DesconhecidoDetailUi> {
	void defineDesconhecidoDetailValue(
			DesconhecidoDetail desconhecidoDetail,
			Object value,
			ArquivoInputColsDefUi arquivoInputColsDefUi)
			throws ServiceException;

	void createDesconhecidoDetail(
			DesconhecidoUi desconhecidoUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException;
}
