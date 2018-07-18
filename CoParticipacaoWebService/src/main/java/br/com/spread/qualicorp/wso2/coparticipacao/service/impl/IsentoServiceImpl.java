package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class IsentoServiceImpl implements IsentoService {

	private static final Logger LOGGER = LogManager
			.getLogger(IsentoServiceImpl.class);

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private DependenteIsentoService dependenteIsentoService;

	@Autowired
	private TitularIsentoService titularIsentoService;

	public boolean hasIsento(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			if (arquivoInputUi.getInputTitularIsento() != null
					|| arquivoInputUi.getInputDependenteIsento() != null) {
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void processIsento(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			processTitularesIsentos(coParticipacaoContext);

			processDependentesIsentos(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void processDependentesIsentos(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		InputDependenteIsentoUi inputDependenteIsentoUi;
		DependenteIsentoColType dependenteIsentoColType;
		DependenteIsentoUi dependenteIsentoUi = null;
		DependenteUi DependenteUi;
		Integer tpIsento;
		List<InputDependenteIsentoColsUi> inputDependenteIsentoColsUis;
		Long cpf;

		try {
			LOGGER.info("BEGIN");

			inputDependenteIsentoUi = (InputDependenteIsentoUi) coParticipacaoContext
					.getArquivoInputUi().getInputDependenteIsento();

			if (inputDependenteIsentoUi != null) {

				inputDependenteIsentoColsUis = coParticipacaoContext
						.getInputDependenteIsentoColsUis();

				for (InputDependenteIsentoColsUi inputDependenteIsentoColsUi : inputDependenteIsentoColsUis) {

					dependenteIsentoColType = DependenteIsentoColType.parse(
							inputDependenteIsentoColsUi
									.getDependenteIsentoColsDef()
									.getNameColumn());

					if (DependenteIsentoColType.ID_DEPENDENTE
							.equals(dependenteIsentoColType)) {
						cpf = (Long) coParticipacaoContext.getColumnValue(
								(ArquivoInputColsDefUi) inputDependenteIsentoColsUi
										.getArquivoInputColsDef());

						DependenteUi = coParticipacaoContext
								.findDependenteByCpf(cpf.toString());

						if (DependenteUi != null) {
							dependenteIsentoUi = new DependenteIsentoUi();
							dependenteIsentoUi.setDependente(DependenteUi);
						} else {
							LOGGER.info(
									"Beneficiário user of CPF[{}] is not a Dependente:",
									cpf);
							break;
						}
					} else if (DependenteIsentoColType.TP_ISENTO
							.equals(dependenteIsentoColType)) {
						if (inputDependenteIsentoColsUi.getTpIsento() != null) {
							tpIsento = inputDependenteIsentoColsUi
									.getTpIsento();

						} else {
							tpIsento = (Integer) coParticipacaoContext
									.getColumnValue(
											(ArquivoInputColsDefUi) inputDependenteIsentoColsUi
													.getArquivoInputColsDef());
						}

						if (dependenteIsentoUi != null) {
							dependenteIsentoUi
									.setMes(coParticipacaoContext.getMes());
							dependenteIsentoUi
									.setAno(coParticipacaoContext.getAno());
							dependenteIsentoUi
									.setIsentoType(IsentoType.parse(tpIsento));
							dependenteIsentoUi.setUserCreated(
									coParticipacaoContext.getUser());
							dependenteIsentoUi.setUserAltered(
									coParticipacaoContext.getUser());

							coParticipacaoContext
									.addDependenteIsento(dependenteIsentoUi);
						} else {
							throw new ServiceException(
									"There's no InputDependenteIsentoColsDef mapped to ID_Dependente in InputDependenteIsento:");
						}
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void processTitularesIsentos(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		InputTitularIsentoUi inputTitularIsentoUi;
		TitularIsentoColType titularIsentoColType;
		TitularIsentoUi titularIsentoUi = null;
		TitularUi titularUi;
		Long cpf;
		Integer tpIsento;
		List<InputTitularIsentoColsUi> inputTitularIsentoColsUis;

		try {
			LOGGER.info("BEGIN");

			inputTitularIsentoUi = (InputTitularIsentoUi) coParticipacaoContext
					.getArquivoInputUi().getInputTitularIsento();

			if (inputTitularIsentoUi != null) {

				inputTitularIsentoColsUis = coParticipacaoContext
						.getInputTitularIsentoColsUis();

				for (InputTitularIsentoCols inputTitularIsentoCols : inputTitularIsentoColsUis) {

					titularIsentoColType = TitularIsentoColType.parse(
							inputTitularIsentoCols.getTitularIsentoColsDef()
									.getNameColumn());

					if (TitularIsentoColType.ID_TITULAR
							.equals(titularIsentoColType)) {
						cpf = (Long) coParticipacaoContext.getColumnValue(
								(ArquivoInputColsDefUi) inputTitularIsentoCols
										.getArquivoInputColsDef());

						titularUi = coParticipacaoContext
								.findTitularByCpf(cpf.toString());

						if (titularUi != null) {
							titularIsentoUi = new TitularIsentoUi();
							titularIsentoUi.setTitular(titularUi);
						} else {
							LOGGER.info(
									"Beneficiário user of CPF[{}] is not a titular:",
									cpf);
							break;
						}
					} else if (TitularIsentoColType.TP_ISENTO
							.equals(titularIsentoColType)) {
						if (inputTitularIsentoCols.getTpIsento() != null) {
							tpIsento = inputTitularIsentoCols.getTpIsento();

						} else {
							tpIsento = (Integer) coParticipacaoContext
									.getColumnValue(
											(ArquivoInputColsDefUi) inputTitularIsentoCols
													.getArquivoInputColsDef());
						}

						if (titularIsentoUi != null) {
							titularIsentoUi
									.setMes(coParticipacaoContext.getMes());
							titularIsentoUi
									.setAno(coParticipacaoContext.getAno());
							titularIsentoUi
									.setIsentoType(IsentoType.parse(tpIsento));
							titularIsentoUi.setUserCreated(
									coParticipacaoContext.getUser());
							titularIsentoUi.setUserAltered(
									coParticipacaoContext.getUser());

							coParticipacaoContext
									.addTitularIsento(titularIsentoUi);
						} else {
							throw new ServiceException(
									"There's no InputTitularIsentoColsDef mapped to ID_TITULAR in InputTitularIsento:");
						}
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void saveIsentos(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Saving [{}] registers of TitularIsento:",
					coParticipacaoContext.getBunker().getTitularIsentoUis()
							.size());
			titularIsentoService.save(
					coParticipacaoContext.getBunker().getTitularIsentoUis());

			LOGGER.info(
					"Saving [{}] registers of DependenteIsento:",
					coParticipacaoContext.getBunker().getDependenteIsentoUis()
							.size());
			dependenteIsentoService.save(
					coParticipacaoContext.getBunker().getDependenteIsentoUis());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
