package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco.impl;

import java.time.LocalDate;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitHeaderViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class TechnitReportWriter {

	private static final Logger LOGGER = LogManager.getLogger(TechnitReportWriter.class);

	protected TechnitHeaderViewUi createTechnitHeader(
			CoParticipacaoContext coParticipacaoContext,
			Integer mes,
			Integer ano) throws ServiceException {
		TechnitHeaderViewUi technitHeaderViewUi;
		LocalDate dataCompetencia;

		try {
			LOGGER.info("BEGIN");

			dataCompetencia = LocalDate.of(ano, mes, NumberUtils.INTEGER_ONE);

			technitHeaderViewUi = new TechnitHeaderViewUi();
			technitHeaderViewUi.setTipoRegistro(NumberUtils.INTEGER_ONE);
			technitHeaderViewUi.setTipoArquivo("PARTIC.SEGURADO");
			technitHeaderViewUi.setCdContrato(coParticipacaoContext.getContratoUi().getCdContrato());
			technitHeaderViewUi.setDataCompetencia(dataCompetencia);
			technitHeaderViewUi.setDataProcessamento(LocalDate.now());

			LOGGER.info("END");
			return technitHeaderViewUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
