package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadsheetReader {

	<T> Map<String, List<T>> loadData(InputStream inputStream, SpreadsheetReadertListener<T> spreadsheeReadertListener)
			throws ServiceException;
}
