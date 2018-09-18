package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.NaoLocalizadoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Qualifier("NaoLocalizadoService")
@Service
@Transactional(value = AbstractService.JDBC_TX)
public class NaoLocalizadoServiceImpl extends MecsasServiceImpl implements NaoLocalizadoService {

	private static final Logger LOGGER = LogManager.getLogger(NaoLocalizadoServiceImpl.class);

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Override
	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = beneficiarioService.createBeneficiarioFromMecsas(coParticipacaoContext);

			if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
				LOGGER.info("Processing titular:");
				LOGGER.info(
						"Beneficiario [{}] with Matricula [{}] was identifyed as Titular:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula());

				titularUi = beneficiarioService.createTitular(coParticipacaoContext, beneficiarioUi);

				if (titularUi == null) {
					desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);
				}
			} else {
				LOGGER.info("Processing beneficiario:");
				LOGGER.info(
						"Beneficiario [{}] with Matricula [{}] was identifyed as Dependente:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula());

				dependenteUi = beneficiarioService.createDependente(coParticipacaoContext, beneficiarioUi);

				if (dependenteUi == null) {
					desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public boolean validateSheet(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		return true;
	}

}