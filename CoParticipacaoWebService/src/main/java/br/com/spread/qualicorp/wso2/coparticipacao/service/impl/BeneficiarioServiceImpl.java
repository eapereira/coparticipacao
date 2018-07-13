package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.BeneficiarioDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Beneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.BeneficiarioEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.BeneficiarioUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputBeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.InputBeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class BeneficiarioServiceImpl extends
		AbstractServiceImpl<BeneficiarioUi, BeneficiarioEntity, Beneficiario>
		implements BeneficiarioService {

	private static final Logger LOGGER = LogManager
			.getLogger(BeneficiarioServiceImpl.class);

	@Autowired
	private BeneficiarioDao beneficiarioDao;

	@Autowired
	private BeneficiarioUiMapper uiMapper;

	@Autowired
	private BeneficiarioEntityMapper entityMapper;

	@Autowired
	private InputBeneficiarioService inputBeneficiarioService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Override
	protected BeneficiarioUi createUi() {
		return new BeneficiarioUi();
	}

	@Override
	protected BeneficiarioEntity createEntity() {
		return new BeneficiarioEntity();
	}

	@Override
	protected AbstractDao<BeneficiarioEntity> getDao() {
		return beneficiarioDao;
	}

	@Override
	protected AbstractMapper<Beneficiario, BeneficiarioUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Beneficiario, BeneficiarioEntity> getEntityMapper() {
		return entityMapper;
	}

	public boolean validateBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi) throws ServiceException {
		InputBeneficiarioUi inputBeneficiarioUi;
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			if (lancamentoUi.getTitular() == null) {
				inputBeneficiarioUi = inputBeneficiarioService
						.findByArquivoInputId(
								coParticipacaoContext.getArquivoInputUi());

				if (inputBeneficiarioUi != null) {
					beneficiarioUi = createBeneficiario(
							inputBeneficiarioUi,
							lancamentoUi);

					if (validateBeneficiario(beneficiarioUi)) {
						if (BeneficiarioType.TITULAR
								.equals(beneficiarioUi.getType())) {
							lancamentoUi.setTitular(
									createTitular(
											coParticipacaoContext,
											beneficiarioUi));
						} else {
							dependenteUi = createDependente(
									coParticipacaoContext,
									beneficiarioUi);

							lancamentoUi.setTitular(dependenteUi.getTitular());
							lancamentoUi.setDependente(dependenteUi);
						}

						return true;
					}
				}
			} else {
				return true;
			}

			LOGGER.info(
					"Não foram encontrados beneficiários para a linha [{}]",
					coParticipacaoContext.getCurrentLine());

			LOGGER.info("END");
			return false;
		} catch (TitularNotFoundException e) {
			LOGGER.info(e.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private DependenteUi createDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioUi beneficiarioUi)
			throws ServiceException, TitularNotFoundException {
		DependenteUi dependenteUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = new DependenteUi();
			dependenteUi.setNameDependente(beneficiarioUi.getNameDependente());
			dependenteUi.setCpf(beneficiarioUi.getCpf().toString());
			dependenteUi.setTpDependente(beneficiarioUi.getType());
			dependenteUi.setMatricula(beneficiarioUi.getMatricula());

			titularUi = coParticipacaoContext
					.findTitularByName(beneficiarioUi.getNameTitular());

			if (titularUi != null) {
				titularUi.addDependente(dependenteUi);
			} else {
				LOGGER.info(
						"Dependente [{}] does not have any Titular:",
						dependenteUi.getNameDependente());

				throw new TitularNotFoundException(
						"Dependente [%s] does not have any Titular:",
						dependenteUi.getNameDependente());
			}

			dependenteUi.setAltered(LocalDateTime.now());
			dependenteUi.setCreated(LocalDateTime.now());
			dependenteUi.setUserAltered(coParticipacaoContext.getUser());
			dependenteUi.setUserCreated(coParticipacaoContext.getUser());

			LOGGER.info("END");
			return dependenteUi;
		} catch (TitularNotFoundException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private Titular createTitular(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioUi beneficiarioUi) throws ServiceException {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = new TitularUi();
			titularUi.setNameTitular(beneficiarioUi.getNameTitular());
			titularUi.setCpf(beneficiarioUi.getCpf().toString());
			titularUi.setMatricula(beneficiarioUi.getMatricula());
			titularUi.setEmpresa(coParticipacaoContext.getEmpresaUi());

			titularUi.setAltered(LocalDateTime.now());
			titularUi.setCreated(LocalDateTime.now());
			titularUi.setUserAltered(coParticipacaoContext.getUser());
			titularUi.setUserCreated(coParticipacaoContext.getUser());

			coParticipacaoContext.addTitular(titularUi);

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateBeneficiario(BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getCpf() != null
					&& StringUtils
							.isNotBlank(beneficiarioUi.getNameDependente())
					&& StringUtils.isNotBlank(beneficiarioUi.getNameTitular())
					&& beneficiarioUi.getMatricula() != null) {
				LOGGER.info(
						"All field need to create a Beneficiario are provided for [{}] with CPF[{}]:",
						beneficiarioUi.getNameDependente(),
						beneficiarioUi.getCpf());

				if (beneficiarioUi.getNameTitular()
						.equals(beneficiarioUi.getNameDependente())) {
					beneficiarioUi.setType(BeneficiarioType.TITULAR);
				} else {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);
				}

				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private BeneficiarioUi createBeneficiario(
			InputBeneficiarioUi inputBeneficiarioUi,
			LancamentoUi lancamentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();

			for (InputBeneficiarioBind inputBeneficiarioBind : inputBeneficiarioUi
					.getInputBeneficiarioBinds()) {
				value = lancamentoDetailService.getFieldValue(
						inputBeneficiarioBind.getArquivoInputColsDef(),
						lancamentoUi);

				if (value != null) {
					setValueField(
							inputBeneficiarioBind.getBeneficiarioColsDef(),
							beneficiarioUi,
							value);
				}
			}

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void setValueField(
			BeneficiarioColsDef beneficiarioColsDef,
			BeneficiarioUi beneficiarioUi,
			Object value) throws ServiceException {
		BeneficiarioColType beneficiarioColType;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColType = BeneficiarioColType
					.parse(beneficiarioColsDef.getNameColumn());

			LOGGER.info(
					"Storing value[{}] to Beneficiario property [{}]",
					value,
					beneficiarioColType.getColumnName());
			if (BeneficiarioColType.NR_MATRICULA.equals(beneficiarioColType)) {
				beneficiarioUi.setMatricula((Long) value);
			} else if (BeneficiarioColType.NR_CPF.equals(beneficiarioColType)) {
				beneficiarioUi.setCpf((Long) value);
			} else if (BeneficiarioColType.NM_TITULAR
					.equals(beneficiarioColType)) {
				beneficiarioUi.setNameTitular((String) value);
			} else if (BeneficiarioColType.NM_BENEFICIARIO
					.equals(beneficiarioColType)) {
				beneficiarioUi.setNameDependente((String) value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
