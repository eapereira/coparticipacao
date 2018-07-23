package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DesconhecidoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.DesconhecidoSpreadsheetListener;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.SpreadsheetBuilder;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DesconhecidoServiceImpl extends
		AbstractServiceImpl<DesconhecidoUi, DesconhecidoEntity, Desconhecido>
		implements DesconhecidoService {

	private static final Logger LOGGER = LogManager
			.getLogger(DesconhecidoServiceImpl.class);

	@Autowired
	private DesconhecidoUiMapper desconhecidoUiMapper;

	@Autowired
	private DesconhecidoEntityMapper desconhecidoEntityMapper;

	@Autowired
	private DesconhecidoDao desconhecidoDao;

	@Autowired
	private DesconhecidoDetailService desconhecidoDetailService;

	@Autowired
	private ArquivoInputOutputDesconhecidoService arquivoInputOutputDesconhecidoService;

	@Autowired
	private ArquivoOutputDesconhecidoService arquivoOutputDesconhecidoService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private DesconhecidoJdbcDao desconhecidoJdbcDao;

	public DesconhecidoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DesconhecidoUi createUi() {
		return new DesconhecidoUi();
	}

	@Override
	protected DesconhecidoEntity createEntity() {
		return new DesconhecidoEntity();
	}

	@Override
	protected AbstractDao<DesconhecidoEntity> getDao() {
		return desconhecidoDao;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoUi> getUiMapper() {
		return desconhecidoUiMapper;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoEntity> getEntityMapper() {
		return desconhecidoEntityMapper;
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			desconhecidoDetailService.createDesconhecidoDetail(
					desconhecidoUi,
					coParticipacaoContext);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createDesconhecido(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi) throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			lancamentoDetailService.showLancamentoDetailInfo(lancamentoUi);

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			desconhecidoDetailService.createDesconhecidoDetail(
					desconhecidoUi,
					coParticipacaoContext,
					lancamentoUi);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoDao.deleteByMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> listByMesAndAno(
			ContratoUi contratoUi,
			int mes,
			int ano) throws ServiceException {
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			desconhecidoUis = entityToUi(
					desconhecidoDao
							.listByMesAndAno(contratoUi.getId(), mes, ano));

			LOGGER.info("END");
			return desconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void writeDesconhecidosFile(
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		SpreadsheetBuilder<DesconhecidoUi> spreadsheetBuilder;
		List<ArquivoInputOutputDesconhecidoUi> arquivoInputOutputDesconhecidoUis;
		List<DesconhecidoUi> desconhecidoUis;
		ArquivoOutputDesconhecidoUi arquivoOutputDesconhecidoUi;
		String nameOutputFile = null;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Using ArquivoInput [{}] to find ArquivoOutputDesconhecido need data:",
					coParticipacaoContext.getArquivoInputUi()
							.getDescrArquivo());

			spreadsheetBuilder = new SpreadsheetBuilder<DesconhecidoUi>();

			for (Contrato contrato : coParticipacaoContext.getEmpresaUi()
					.getContratos()) {

				LOGGER.info(
						"Configuring sheet [{}] for Desconhecidos:",
						contrato.getCdContrato());

				arquivoOutputDesconhecidoUi = arquivoOutputDesconhecidoService
						.findByContratoId((ContratoUi) contrato);

				if (arquivoOutputDesconhecidoUi != null) {
					arquivoInputOutputDesconhecidoUis = arquivoInputOutputDesconhecidoService
							.listByContratoId((ContratoUi) contrato);

					nameOutputFile = arquivoOutputDesconhecidoUi
							.getNameArquivoFormat();

					desconhecidoUis = listByMesAndAno(
							(ContratoUi) contrato,
							coParticipacaoContext.getMes(),
							coParticipacaoContext.getAno());

					if (!desconhecidoUis.isEmpty()) {
						spreadsheetBuilder.addSpreadsheetListener(
								new DesconhecidoSpreadsheetListener(
										desconhecidoDetailService,
										arquivoInputOutputDesconhecidoUis,
										desconhecidoUis,
										coParticipacaoContext));
					} else {
						LOGGER.info(
								"No registers Desconhecidos to write for Contrato [{}]",
								contrato.getCdContrato());
					}
				} else {
					LOGGER.info(
							"The ArquivoInput[{}] and Contrato [{}] does not have a ArquivoOutput defined to it:",
							coParticipacaoContext.getArquivoInputUi()
									.getDescrArquivo(),
							contrato.getCdContrato());
				}
			}

			LOGGER.info("Writing spreadsheet to filesystem:");
			spreadsheetBuilder.setSpreadsheetFileName(nameOutputFile);
			spreadsheetBuilder.writeSpreadsheet(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected AbstractJdbcDao<DesconhecidoEntity> getJdbcDao() {
		return desconhecidoJdbcDao;
	}

	@Override
	public void saveBatch(List<DesconhecidoUi> desconhecidoUis)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			super.saveBatch(desconhecidoUis);

			for (DesconhecidoUi desconhecidoUi : desconhecidoUis) {
				for (DesconhecidoDetail desconhecidoDetail : desconhecidoUi
						.getDesconhecidoDetails()) {
					desconhecidoDetailService.saveBatch(
							(DesconhecidoDetailUi) desconhecidoDetail);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected void logBatchInfo(DesconhecidoUi desconhecidoUi)
			throws ServiceException {
		LOGGER.debug(
				"ID:......................... [{}]",
				desconhecidoUi.getId());
		LOGGER.debug(
				"CD_CONTRATO:................ [{}]",
				desconhecidoUi.getContrato().getCdContrato());
		LOGGER.debug(
				"CD_MES:..................... [{}]",
				desconhecidoUi.getContrato().getCdContrato());
		LOGGER.debug(
				"CD_ANO:..................... [{}]",
				desconhecidoUi.getContrato().getCdContrato());
	}
}
