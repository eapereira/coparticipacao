package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoInputServiceImpl extends AbstractServiceImpl<ArquivoInputUi, ArquivoInputEntity, ArquivoInput>
		implements ArquivoInputService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputServiceImpl.class);

	@Autowired
	private ArquivoInputDao arquivoInputDao;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private ArquivoInputUiMapper uiMapper;

	@Autowired
	private ArquivoInputEntityMapper entityMapper;

	private static final Pattern REGEXP_INPUT_FILE = Pattern
			.compile("^(.+)\\.(.+)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.((txt|csv|xlsx|xls)|(TXT|CSV|XLSX|XLS))$");

	private static final int GROUP_EMPRESA = 1;
	private static final int GROUP_CONTRATO = 2;
	private static final int GROUP_ANO = 3;
	private static final int GROUP_MES = 4;

	public CoParticipacaoContext findByName(String fileName) throws ServiceException {
		Matcher matcher;
		CoParticipacaoContext coParticipacaoContext = null;
		int mes;
		int ano;
		EmpresaUi empresaUi;
		String cdContrato;

		try {
			LOGGER.info("BEGIN");

			matcher = REGEXP_INPUT_FILE.matcher(fileName);

			if (matcher.find()) {
				empresaUi = empresaService.findByCdEmpresa(matcher.group(GROUP_EMPRESA));

				if (empresaUi != null) {
					cdContrato = matcher.group(GROUP_CONTRATO);
					ano = Integer.parseInt(matcher.group(GROUP_ANO));
					mes = Integer.parseInt(matcher.group(GROUP_MES));

					for (Contrato contrato : empresaUi.getContratos()) {
						if (contrato.getCdContrato().equals(cdContrato)) {
							LOGGER.info("Found ArquivoInput for the file [{}]:", fileName);

							if (contrato.getArquivoInput() == null) {
								throw new ServiceException(
										"ContratoUi[%s] has no ArquivoInputUi defined:",
										contrato.getCdContrato());
							} else {
								coParticipacaoContext = new CoParticipacaoContext();
								coParticipacaoContext.setArquivoInputUi(findByContratoId(contrato.getId()));
								coParticipacaoContext.setAno(ano);
								coParticipacaoContext.setMes(mes);
								coParticipacaoContext.setEmpresaUi(empresaUi);
							}

							break;
						}
					}
				}
			} else {
				throw new ServiceException("Empresa not found for ArquivoInput[%s]:", fileName);
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

	public ArquivoInputUi findByContratoId(Long contratoId) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");
			arquivoInputUi = entityToUi(arquivoInputDao.findByContratoId(contratoId));

			LOGGER.info("END");
			return arquivoInputUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ArquivoInputUi findByContratoName(String contratoName) throws ServiceException {
		ArquivoInputUi arquivoInputUi;
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");
			contratoUi = contratoService.findByCdContrato(contratoName);

			if (contratoUi != null) {
				arquivoInputUi = entityToUi(arquivoInputDao.findByContratoId(contratoUi.getId()));
			} else {
				throw new ServiceException("Contrato [%s] não encontrado.", contratoName);
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

	public List<ArquivoInputUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<ArquivoInputUi> arquivoInputUis;

		try {
			LOGGER.info("BEGIN");
			arquivoInputUis = entityToUi(arquivoInputDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return arquivoInputUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ArquivoInputUi findByCdContrato(String cdContrato) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = entityToUi(arquivoInputDao.findByCdContrato(cdContrato));

			LOGGER.info("END");
			return arquivoInputUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ArquivoInputUi findByEmpresaAndCdContrato(EmpresaUi empresaUi, String cdContrato) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = entityToUi(arquivoInputDao.findByEmpresaCdContrato(empresaUi.getId(), cdContrato));

			LOGGER.info("END");
			return arquivoInputUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
