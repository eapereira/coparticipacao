package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.impl.sulamerica;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.SpreadsheetReadertListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HaocSpreadsheetReadertListener implements SpreadsheetReadertListener<HaocData> {

	private static final Logger LOGGER = LogManager.getLogger(HaocSpreadsheetReadertListener.class);

	public HaocSpreadsheetReadertListener() {

	}

	public HaocData createData(Row row) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getSheetNames() throws ServiceException {
		List<String> contratos = new ArrayList<String>();

		contratos.add("ESPELHO");
		contratos.add("COPART ORIGINAL");
		contratos.add("ISENÇÃO GESTANTES");
		contratos.add("ISENÇÃO CONSELHO");
		contratos.add("AFASTADOS");
		contratos.add("AGREGADOS");
		contratos.add("PLANO EXTENSÃO");
		contratos.add("DESLIGADOS");
		contratos.add("ISENÇÃO HOC");
		contratos.add("PRN");

		return contratos;
	}
}
