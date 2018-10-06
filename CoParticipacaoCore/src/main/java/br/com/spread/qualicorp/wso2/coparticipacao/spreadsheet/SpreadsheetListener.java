package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadsheetListener<UI extends AbstractDomain> {
	String getSheetName(int sheetId) throws ServiceException;

	List<ColumnInfo> createColumnTitles() throws ServiceException;

	List<UI> createData() throws ServiceException;

	CellInfo createCellContent(UI data, int column) throws ServiceException;
}
