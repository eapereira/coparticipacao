package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl.sulamerica;

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
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CargillSpreadsheetReaderListerner implements SpreadsheetReadertListener<CargillData> {

	private static final Logger LOGGER = LogManager.getLogger(CargillSpreadsheetReaderListerner.class);

	private static final int COL_CD_CONTRATO = 0;
	private static final int COL_NR_MATRICULA_EMPRESA = 1;
	private static final int COL_NR_CPF = 2;
	private static final int COL_NM_BENEFICIARIO = 3;
	private static final int COL_CD_VERBA = 4;
	private static final int COL_CD_PLANO = 5;
	private static final int COL_SINAL = 6;
	private static final int COL_DT_ADMISSAO = 7;
	private static final int COL_VL_PRINCIPAL = 8;

	public CargillSpreadsheetReaderListerner() {

	}

	public CargillData createData(Row row) throws ServiceException {
		CargillData cargillData;
		int columnIndex = NumberUtils.INTEGER_ZERO;

		try {
			LOGGER.info("BEGIN");

			cargillData = new CargillData();

			for (Cell cell : row) {
				if (columnIndex == COL_CD_CONTRATO) {
					cargillData.setCdContrato(cell.getStringCellValue());
				} else if (columnIndex == COL_NR_MATRICULA_EMPRESA) {
					cargillData.setMatriculaEmpresa((long) cell.getNumericCellValue());
				} else if (columnIndex == COL_NR_CPF) {
					cargillData.setCpf((long) cell.getNumericCellValue());
				} else if (columnIndex == COL_NM_BENEFICIARIO) {
					cargillData.setNameBeneficiario(cell.getStringCellValue());
				} else if (columnIndex == COL_CD_VERBA) {
					cargillData.setCdVerba(cell.getStringCellValue());
				} else if (columnIndex == COL_CD_PLANO) {
					cargillData.setCdPlano((int)cell.getNumericCellValue());
				} else if (columnIndex == COL_SINAL) {
					cargillData.setSinal(cell.getStringCellValue());
				} else if (columnIndex == COL_DT_ADMISSAO) {
					cargillData.setDtAdmissao(DateUtils.dateToLocalDate(cell.getDateCellValue()));
				} else if (columnIndex == COL_VL_PRINCIPAL) {
					cargillData.setValorPrincipal(BigDecimal.valueOf(cell.getNumericCellValue()));
				}

				columnIndex++;
			}

			LOGGER.info("END");
			return cargillData;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<String> getSheetNames() throws ServiceException {
		List<String> list = new ArrayList<String>();

		list.add("CARGILL");

		return list;
	}
}
