package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ParameterName;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoSpreadsheetListener
		implements SpreadsheetListener<DesconhecidoUi> {
	private static final Logger LOGGER = LogManager
			.getLogger(DesconhecidoSpreadsheetListener.class);

	private List<DesconhecidoUi> desconhecidoUis;

	private CoParticipacaoContext coParticipacaoContext;

	private List<ArquivoInputOutputDesconhecidoUi> arquivoInputOutputDesconhecidoUis;

	private DesconhecidoDetailService desconhecidoDetailService;

	private int columnIndex;

	private int currentSheet;

	public DesconhecidoSpreadsheetListener(
			DesconhecidoDetailService desconhecidoDetailService,
			List<ArquivoInputOutputDesconhecidoUi> arquivoInputOutputDesconhecidoUis,
			List<DesconhecidoUi> desconhecidoUis,
			CoParticipacaoContext coParticipacaoContext) {
		this.desconhecidoDetailService = desconhecidoDetailService;
		this.arquivoInputOutputDesconhecidoUis = arquivoInputOutputDesconhecidoUis;
		this.desconhecidoUis = desconhecidoUis;
		this.coParticipacaoContext = coParticipacaoContext;

		this.columnIndex = NumberUtils.INTEGER_ZERO;
		currentSheet = NumberUtils.INTEGER_ZERO;
	}

	public String getSheetName(int sheetId) throws ServiceException {
		for (Contrato contrato : coParticipacaoContext.getEmpresaUi()
				.getContratos()) {
			if (currentSheet >= sheetId) {
				return contrato.getCdContrato();
			}

			currentSheet++;
		}

		return "Desconhecidos";
	}

	public List<ColumnInfo> createColumnTitles() throws ServiceException {
		List<ColumnInfo> columnInfos;
		ColumnInfo columnInfo;
		ArquivoOutputDesconhecidoColsDef arquivoOutputDesconhecidoColsDef;

		try {
			LOGGER.info("BEGIN");

			columnInfos = new ArrayList<ColumnInfo>();

			LOGGER.info("Creating the columns for the spreadseet:");

			for (ArquivoInputOutputDesconhecidoUi arquivoInputOutputDesconhecidoUi : arquivoInputOutputDesconhecidoUis) {
				columnInfo = new ColumnInfo();

				arquivoOutputDesconhecidoColsDef = arquivoInputOutputDesconhecidoUi
						.getArquivoOutputDesconhecidoColsDef();

				columnInfo.setName(
						arquivoOutputDesconhecidoColsDef.getNameColumn());
				columnInfo
						.setWidth(arquivoOutputDesconhecidoColsDef.getLength());

				LOGGER.info(
						"Column name[{}] with width[]{}:",
						columnInfo.getName(),
						columnInfo.getWidth());

				columnInfos.add(columnInfo);
			}

			LOGGER.info("END");
			return columnInfos;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> createData() throws ServiceException {
		return desconhecidoUis;
	}

	public CellInfo createCellContent(DesconhecidoUi desconhecidoUi, int column)
			throws ServiceException {
		CellInfo cellInfo;
		int col = NumberUtils.INTEGER_ZERO;
		DesconhecidoDetailUi desconhecidoDetail;
		ArquivoInputColsDef arquivoInputColsDef;

		try {
			LOGGER.info("BEGIN");
			cellInfo = new CellInfo();

			if (columnIndex < desconhecidoUi.getDesconhecidoDetails().size()) {

				for (ArquivoInputOutputDesconhecidoUi arquivoInputOutputDesconhecidoUi : arquivoInputOutputDesconhecidoUis) {
					arquivoInputColsDef = arquivoInputOutputDesconhecidoUi
							.getArquivoInputColsDef();

					if (col >= columnIndex) {
						LOGGER.info(
								"Value for column [{}]:",
								arquivoInputColsDef.getNameColumn());

						desconhecidoDetail = findDesconhecidoDetail(
								desconhecidoUi,
								arquivoInputColsDef);

						if (desconhecidoDetail != null) {
							cellInfo.setValue(
									desconhecidoDetailService.getValue(
											(DesconhecidoDetailUi) desconhecidoDetail));
						} else {
							LOGGER.info(
									"No values found for Desconhecido's field [{}]",
									arquivoInputColsDef.getNameColumn());
						}

						columnIndex++;
						break;
					}

					col++;
				}

				if (col < arquivoInputOutputDesconhecidoUis.size()) {
					cellInfo.setCellInfoStatus(CellInfoStatus.KEEP_LINE);
				} else {
					this.columnIndex = NumberUtils.INTEGER_ZERO;
					cellInfo.setCellInfoStatus(CellInfoStatus.END_LINE);
				}
			} else {
				this.columnIndex = NumberUtils.INTEGER_ZERO;
				cellInfo.setCellInfoStatus(CellInfoStatus.END_LINE);
			}

			LOGGER.info("END");
			return cellInfo;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private DesconhecidoDetailUi findDesconhecidoDetail(
			DesconhecidoUi desconhecidoUi,
			ArquivoInputColsDef arquivoInputColsDef) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (DesconhecidoDetail desconhecidoDetail : desconhecidoUi
					.getDesconhecidoDetails()) {
				LOGGER.debug(
						"Comparing ArquivoInput[{}] with DesconhecidoDetail[{}]",
						arquivoInputColsDef.getNameColumn(),
						desconhecidoDetail.getArquivoInputColsDef()
								.getNameColumn());

				if (arquivoInputColsDef.getId().equals(
						desconhecidoDetail.getArquivoInputColsDef().getId())) {
					LOGGER.info(
							"Column [{}] found to to use with ArquivoOutputDesconhecido:",
							arquivoInputColsDef.getNameColumn());
					LOGGER.info("END");
					return (DesconhecidoDetailUi) desconhecidoDetail;
				}
			}

			LOGGER.info(
					"There is no column defined for [{}] in ArquivoInputOutputDesconhecido:",
					arquivoInputColsDef.getNameColumn());
			LOGGER.info("END");
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public String getOutputFilePath() throws ServiceException {
		return coParticipacaoContext
				.findParameterByName(ParameterName.OUTPUT_FILE_PATH).getValue();
	}

}
