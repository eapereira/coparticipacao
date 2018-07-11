package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
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
			desconhecidoUi
					.setArquivoInput(coParticipacaoContext.getArquivoInputUi());
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

	public void deleteByMesAndAno(
			ArquivoInputUi arquivoInputUi,
			int mes,
			int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoDao.deleteByMesAndAno(arquivoInputUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> listByMesAndAno(
			ArquivoInputUi arquivoInputUi,
			int mes,
			int ano) throws ServiceException {
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			desconhecidoUis = entityToUi(
					desconhecidoDao
							.listByMesAndAno(arquivoInputUi.getId(), mes, ano));

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

		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Using ArquivoInput [{}] to find ArquivoOutputDesconhecido need data:");

			arquivoOutputDesconhecidoUi = arquivoOutputDesconhecidoService
					.findByArquivoInputId(
							coParticipacaoContext.getArquivoInputUi().getId());

			if (arquivoOutputDesconhecidoUi != null) {
				arquivoInputOutputDesconhecidoUis = arquivoInputOutputDesconhecidoService
						.listByArquivoInputId(
								coParticipacaoContext.getArquivoInputUi()
										.getId());

				desconhecidoUis = listByMesAndAno(
						coParticipacaoContext.getArquivoInputUi(),
						coParticipacaoContext.getMes(),
						coParticipacaoContext.getAno());

				spreadsheetBuilder = new SpreadsheetBuilder<DesconhecidoUi>(
						arquivoOutputDesconhecidoUi.getNameArquivoFormat());
				spreadsheetBuilder.addSpreadsheetListener(
						new DesconhecidoSpreadsheetListener(
								desconhecidoDetailService,
								arquivoInputOutputDesconhecidoUis,
								desconhecidoUis,
								coParticipacaoContext));

				LOGGER.info("Writing spreadsheet to filesystem:");
				spreadsheetBuilder.writeSpreadsheet(coParticipacaoContext);
			} else {
				throw new ServiceException(
						"The ArquivoInput[{}] does not have a ArquivoOutput defined to it:",
						coParticipacaoContext.getArquivoInputUi()
								.getDescrArquivo());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
