package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoInputServiceImpl extends
		AbstractServiceImpl<ArquivoInputUi, ArquivoInputEntity, ArquivoInput>
		implements ArquivoInputService {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoInputServiceImpl.class);

	@Autowired
	private ArquivoInputDao arquivoInputDao;

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private ArquivoInputUiMapper uiMapper;

	@Autowired
	private ArquivoInputEntityMapper entityMapper;

	public CoParticipacaoContext findByName(String fileName)
			throws ServiceException {
		List<ArquivoInputUi> arquivoInputUis;
		Pattern regexpFileName;
		Matcher matcher;
		CoParticipacaoContext coParticipacaoContext = null;
		int dia;
		int mes;
		int ano;

		try {
			LOGGER.info("BEGIN");
			arquivoInputUis = entityToUi(arquivoInputDao.listAll());

			dia = NumberUtils.INTEGER_ZERO;
			mes = NumberUtils.INTEGER_ZERO;

			for (ArquivoInputUi arquivoInputUi : arquivoInputUis) {
				regexpFileName = Pattern
						.compile(arquivoInputUi.getNameArquivoRegexp());
				matcher = regexpFileName.matcher(fileName);

				if (matcher.find()) {
					LOGGER.info(
							"Found ArquivoInput for the file [{}]:",
							fileName);

					if (arquivoInputUi.getRegexpDia() != null) {
						dia = Integer.parseInt(
								matcher.group(arquivoInputUi.getRegexpDia()));
					} else {
						dia = DateUtils.now().getDayOfMonth();
					}

					if (arquivoInputUi.getRegexpMes() != null) {
						mes = Integer.parseInt(
								matcher.group(arquivoInputUi.getRegexpMes()));
					} else {
						mes = DateUtils.now().getMonthValue();
					}

					ano = Integer.parseInt(
							matcher.group(arquivoInputUi.getRegexpAno()));

					coParticipacaoContext = new CoParticipacaoContext();
					coParticipacaoContext.setArquivoInputUi(arquivoInputUi);
					coParticipacaoContext.setDia(dia);
					coParticipacaoContext.setMes(mes);
					coParticipacaoContext.setAno(ano);

					break;
				}
			}

			LOGGER.info("END");
			return coParticipacaoContext;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected AbstractDao<ArquivoInputEntity> getDao() {
		return arquivoInputDao;
	}

	public ArquivoInputUi findByContrato(Long contratoId)
			throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");
			arquivoInputUi = entityToUi(
					arquivoInputDao.findByContrato(contratoId));

			LOGGER.info("END");
			return arquivoInputUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ArquivoInputUi findByContratoName(String contratoName)
			throws ServiceException {
		ArquivoInputUi arquivoInputUi;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");
			contratoUi = contratoService.findByCdContrato(contratoName);

			if (contratoUi != null) {
				arquivoInputUi = entityToUi(
						arquivoInputDao.findByContrato(contratoUi.getId()));
			} else {
				throw new ServiceException(
						"Contrato [%s] não encontrado.",
						contratoName);
			}

			LOGGER.info("END");
			return arquivoInputUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected ArquivoInputUi createUi() {
		return new ArquivoInputUi();
	}

	@Override
	protected ArquivoInputEntity createEntity() {
		return new ArquivoInputEntity();
	}

	@Override
	protected AbstractMapper<ArquivoInput, ArquivoInputUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoInput, ArquivoInputEntity> getEntityMapper() {
		return entityMapper;
	}
}
