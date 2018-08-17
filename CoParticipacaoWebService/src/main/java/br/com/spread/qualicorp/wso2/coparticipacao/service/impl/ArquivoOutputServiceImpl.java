package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoOutputEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoOutputUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.LancamentoSpreadsheetListener;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.SpreadsheetBuilder;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoOutputServiceImpl extends AbstractServiceImpl<ArquivoOutputUi, ArquivoOutputEntity, ArquivoOutput>
		implements ArquivoOutputService {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoOutputServiceImpl.class);

	@Autowired
	private ArquivoOutputDao arquivoOutputDao;

	@Autowired
	private ArquivoOutputUiMapper uiMapper;

	@Autowired
	private ArquivoOutputEntityMapper entityMapper;

	@Autowired
	private ViewDestinationService viewDestinationService;

	@Autowired
	private ViewDestinationColsDefService viewDestinationColsDefService;

	@Autowired
	private ArquivoOutputSheetService arquivoOutputSheetService;

	@Override
	protected ArquivoOutputUi createUi() {
		return new ArquivoOutputUi();
	}

	@Override
	protected ArquivoOutputEntity createEntity() {
		return new ArquivoOutputEntity();
	}

	@Override
	protected AbstractDao<ArquivoOutputEntity> getDao() {
		return arquivoOutputDao;
	}

	@Override
	protected AbstractMapper<ArquivoOutput, ArquivoOutputUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoOutput, ArquivoOutputEntity> getEntityMapper() {
		return entityMapper;
	}

	public void writeOutputFile(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		ArquivoOutputUi arquivoOutputUi;
		List<DynamicEntity> dynamicEntities;
		ViewDestinationUi viewDestinationUi;
		SpreadsheetBuilder<DynamicEntity> spreadsheetBuilder;
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;
		List<ArquivoOutputSheetUi> arquivoOutputSheetUis;

		try {
			LOGGER.info("BEGIN");

			arquivoOutputUi = entityToUi(
					arquivoOutputDao.findByArquivoInputId(coParticipacaoContext.getArquivoInputUi().getId()));

			if (arquivoOutputUi != null) {
				LOGGER.info("Exists an ArquivoOutput configured to use as [{}]:", arquivoOutputUi.getDescrArquivo());

				spreadsheetBuilder = new SpreadsheetBuilder<DynamicEntity>(arquivoOutputUi.getNameArquivoFormat());

				arquivoOutputSheetUis = arquivoOutputSheetService.listByArquivoOutputId(arquivoOutputUi);

				for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
					LOGGER.info(
							"Validating Contrato [{}] if is a FATUCOPA to generate reports:",
							contrato.getCdContrato());

					if (UseType.FATUCOPA.equals(contrato.getUseType())) {
						for (ArquivoOutputSheet arquivoOutputSheet : arquivoOutputSheetUis) {

							viewDestinationUi = (ViewDestinationUi) arquivoOutputSheet.getViewDestination();
							viewDestinationColsDefUis = viewDestinationColsDefService
									.listByViewDestinationId(viewDestinationUi);

							LOGGER.info(
									"Creating the report for the ViewDestination [{}]:",
									viewDestinationUi.getNameView());
							dynamicEntities = viewDestinationService.listByContratoAndMesAndAno(
									viewDestinationUi,
									(ContratoUi) contrato,
									coParticipacaoContext.getMes(),
									coParticipacaoContext.getAno());

							// Criando um listener para cada pasta da planilha:
							spreadsheetBuilder.addSpreadsheetListener(
									new LancamentoSpreadsheetListener(
											coParticipacaoContext,
											(ArquivoOutputSheetUi) arquivoOutputSheet,
											viewDestinationColsDefUis,
											dynamicEntities,
											(ContratoUi) contrato));
						}
					}
				}

				// Escrevendo a planilha:
				spreadsheetBuilder.writeSpreadsheet(coParticipacaoContext);
			} else {
				LOGGER.info(
						"No ArquivoOutput defined for ArquivoInput [{}]:",
						coParticipacaoContext.getArquivoInputUi().getDescrArquivo());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
