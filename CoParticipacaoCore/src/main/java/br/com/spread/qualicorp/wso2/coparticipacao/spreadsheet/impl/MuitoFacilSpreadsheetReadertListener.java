package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.SpreadsheetReadertListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MuitoFacilSpreadsheetReadertListener implements SpreadsheetReadertListener<MuitoFacilData> {

	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilSpreadsheetReadertListener.class);

	private static final int COL_NM_BENEFICIARIO = 0;
	private static final int COL_NR_CPF = 1;
	private static final int COL_NR_MATRICULA = 2;
	private static final int COL_VL_PRINCIPAL = 3;

	public MuitoFacilData createData(Row row) throws ServiceException {
		MuitoFacilData data;
		int columnIndex = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");

			data = new MuitoFacilData();

			for (Cell cell : row) {
				if (columnIndex == COL_NM_BENEFICIARIO) {
					data.setNameBeneficiario(cell.getStringCellValue());
				} else if (columnIndex == COL_NR_CPF) {
					data.setCpf(Long.valueOf(cell.getStringCellValue()));
				} else if (columnIndex == COL_NR_MATRICULA) {
					data.setMatricula((long) cell.getNumericCellValue());
				} else if (columnIndex == COL_VL_PRINCIPAL) {
					data.setValorPrincipal(BigDecimal.valueOf(cell.getNumericCellValue()));
				}

				columnIndex++;
			}

			LOGGER.info("END");
			return data;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<String> getSheetNames() throws ServiceException {
		List<String> contratos = new ArrayList<String>();

		contratos.add("8CH5Y");
		contratos.add("8CHE8");

		return contratos;
	}

}
