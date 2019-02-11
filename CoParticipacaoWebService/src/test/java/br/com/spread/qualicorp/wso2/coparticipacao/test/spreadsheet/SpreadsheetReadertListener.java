package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadsheetReadertListener<T> {

	T createData(Row row) throws ServiceException;

	List<String> getSheetNames() throws ServiceException;;
}
