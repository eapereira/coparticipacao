package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ParameterName;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LancamentoSpreadsheetListener implements SpreadsheetListener<DynamicEntity> {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoSpreadsheetListener.class);

	private List<DynamicEntity> dynamicEntities;

	private List<ViewDestinationColsDefUi> viewDestinationColsDefUis;

	private CoParticipacaoContext coParticipacaoContext;

	private ArquivoOutputSheetUi arquivoOutputSheetUi;

	private ContratoUi contratoUi;

	private int columnIndex;

	private int numColumns;

	public LancamentoSpreadsheetListener(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputSheetUi arquivoOutputSheetUi,
			List<ViewDestinationColsDefUi> viewDestinationColsDefUis,
			List<DynamicEntity> dynamicEntities,
			ContratoUi contratoUi) {
		this.coParticipacaoContext = coParticipacaoContext;
		this.dynamicEntities = dynamicEntities;
		this.arquivoOutputSheetUi = arquivoOutputSheetUi;
		this.viewDestinationColsDefUis = viewDestinationColsDefUis;
		this.contratoUi = contratoUi;

		numColumns = NumberUtils.INTEGER_ZERO;
	}

	public String getSheetName(int sheetId) throws ServiceException {
		return String.format(arquivoOutputSheetUi.getNmSheet(), contratoUi.getNameContrato());
	}

	public List<ColumnInfo> createColumnTitles() throws ServiceException {
		List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
		ColumnInfo columnInfo;
		String columnName;

		for (ViewDestinationColsDef viewDestinationColsDef : viewDestinationColsDefUis) {

			columnInfo = new ColumnInfo();

			if (StringUtils.isNotBlank(viewDestinationColsDef.getColumnTitleLabel())) {
				columnName = viewDestinationColsDef.getColumnTitleLabel();
			} else {
				columnName = viewDestinationColsDef.getNameColumn();
			}

			columnInfo.setName(columnName);
			columnInfo.setWidth(viewDestinationColsDef.getLength());

			LOGGER.info(
					"Defining spreadsheet columnName [{}] with length [{}]",
					columnName,
					viewDestinationColsDef.getLength());

			columnInfos.add(columnInfo);

			numColumns++;
		}

		return columnInfos;
	}

	public List<DynamicEntity> createData() throws ServiceException {
		return dynamicEntities;
	}

	public CellInfo createCellContent(DynamicEntity dynamicEntity, int column) throws ServiceException {
		CellInfo cellInfo;
		int col = NumberUtils.INTEGER_ZERO;
		Object value;

		try {
			LOGGER.info("BEGIN");
			cellInfo = new CellInfo();

			if (columnIndex < numColumns) {

				for (ViewDestinationColsDef viewDestinationColsDef : viewDestinationColsDefUis) {

					if (col >= columnIndex) {
						LOGGER.info("Value for column [{}]:", viewDestinationColsDef.getNameColumn());

						value = dynamicEntity.getColumnValue(viewDestinationColsDef.getNameColumn());

						LOGGER.info(
								"ViewDestination [{}] has column [{}] with value [{}]:",
								viewDestinationColsDef.getViewDestination().getNameView(),
								viewDestinationColsDef.getNameColumn(),
								value);

						cellInfo.setValue(value);

						columnIndex++;
						break;
					}

					col++;
				}

				if (col < numColumns) {
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

	public String getOutputFilePath() throws ServiceException {
		return coParticipacaoContext.findParameterByName(ParameterName.OUTPUT_FILE_PATH).getValue();
	}
}
