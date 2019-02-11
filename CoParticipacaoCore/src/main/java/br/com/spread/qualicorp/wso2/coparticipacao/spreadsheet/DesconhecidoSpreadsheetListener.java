package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoSpreadsheetListener implements SpreadsheetListener<DynamicEntity> {
	private static final Logger LOGGER = LogManager.getLogger(DesconhecidoSpreadsheetListener.class);

	private List<DynamicEntity> dynamicEntities;

	private CoParticipacaoContext coParticipacaoContext;

	private List<ViewDestinationColsDefUi> viewDestinationColsDefUis;

	private ContratoUi contratoUi;

	private int columnIndex;

	public DesconhecidoSpreadsheetListener(
			List<ViewDestinationColsDefUi> viewDestinationColsDefUis,
			List<DynamicEntity> dynamicEntities,
			ContratoUi contratoUi,
			CoParticipacaoContext coParticipacaoContext) {
		this.viewDestinationColsDefUis = viewDestinationColsDefUis;
		this.dynamicEntities = dynamicEntities;
		this.coParticipacaoContext = coParticipacaoContext;
		this.contratoUi = contratoUi;

		this.columnIndex = NumberUtils.INTEGER_ZERO;
	}

	public String getSheetName(int sheetId) throws ServiceException {
		return contratoUi.getNameContrato();
	}

	public List<ColumnInfo> createColumnTitles() throws ServiceException {
		List<ColumnInfo> columnInfos;
		ColumnInfo columnInfo;

		try {
			LOGGER.info("BEGIN");

			columnInfos = new ArrayList<ColumnInfo>();

			LOGGER.info("Creating the columns for the spreadseet:");

			for (ViewDestinationColsDefUi viewDestinationColsDefUi : viewDestinationColsDefUis) {
				columnInfo = new ColumnInfo();

				if (StringUtils.isNotBlank(viewDestinationColsDefUi.getColumnTitleLabel())) {
					columnInfo.setName(viewDestinationColsDefUi.getColumnTitleLabel());
				} else {
					columnInfo.setName(viewDestinationColsDefUi.getNameColumn());
				}

				columnInfo.setWidth(viewDestinationColsDefUi.getLength());

				LOGGER.info("Column name[{}] with width[]{}:", columnInfo.getName(), columnInfo.getWidth());

				columnInfos.add(columnInfo);
			}

			LOGGER.info("END");
			return columnInfos;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
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

			for (ViewDestinationColsDefUi viewDestinationColsDefUi : viewDestinationColsDefUis) {
				if (col >= columnIndex) {
					LOGGER.info("Name for column [{}]:", viewDestinationColsDefUi.getNameColumn());

					value = dynamicEntity.getColumnValue(viewDestinationColsDefUi.getNameColumn());

					LOGGER.info(
							"ViewDestination [{}] has column [{}] with value [{}]:",
							viewDestinationColsDefUi.getViewDestination().getNameView(),
							viewDestinationColsDefUi.getNameColumn(),
							value);

					cellInfo.setValue(value);

					columnIndex++;
					break;
				}

				col++;
			}

			if (col < viewDestinationColsDefUis.size()) {
				cellInfo.setCellInfoStatus(CellInfoStatus.KEEP_LINE);
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

}
