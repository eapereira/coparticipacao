package br.com.spread.qualicorp.wso2.coparticipacao.report.service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface ReportWriter {

	String getCdEmpresa() throws ServiceException;

	String getReportFile() throws ServiceException;

	JRDataSource getDataSource(CoParticipacaoContext coParticipacaoContext, Integer mes, Integer ano)
			throws ServiceException;
}
