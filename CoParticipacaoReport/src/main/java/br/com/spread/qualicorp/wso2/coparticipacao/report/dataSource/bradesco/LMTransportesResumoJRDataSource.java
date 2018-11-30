package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesResumoJRDataSource extends CoParticipacaoDataSource<LMTransportesResumoViewUi> {

	public LMTransportesResumoJRDataSource() throws JRException {
		super();
	}

	public LMTransportesResumoJRDataSource(List<LMTransportesResumoViewUi> lmTransportesResumoViewUis) {
		super(lmTransportesResumoViewUis);
	}

	protected List<LMTransportesResumoViewUi> buildData() throws JRException {
		List<LMTransportesResumoViewUi> lmTransportesResumoViewUis = new ArrayList<>();
		LMTransportesResumoViewUi lmTransportesResumoViewUi = new LMTransportesResumoViewUi();

		lmTransportesResumoViewUi = new LMTransportesResumoViewUi();
		lmTransportesResumoViewUi.setSubFatura("001");
		lmTransportesResumoViewUi.setCdEmpresa("AUTOMIND");
		lmTransportesResumoViewUi.setQtdeSegurados(56);
		lmTransportesResumoViewUi.setValorProporcao(new BigDecimal("73"));
		lmTransportesResumoViewUi.setValorAlocacao(new BigDecimal("3126"));
		lmTransportesResumoViewUis.add(lmTransportesResumoViewUi);

		lmTransportesResumoViewUi = new LMTransportesResumoViewUi();
		lmTransportesResumoViewUi.setSubFatura("002");
		lmTransportesResumoViewUi.setCdEmpresa("AUTOMIND");
		lmTransportesResumoViewUi.setQtdeSegurados(20);
		lmTransportesResumoViewUi.setValorProporcao(new BigDecimal("26"));
		lmTransportesResumoViewUi.setValorAlocacao(new BigDecimal("1500"));
		lmTransportesResumoViewUis.add(lmTransportesResumoViewUi);

		return lmTransportesResumoViewUis;
	}

	public static JRDataSource getInstance() throws JRException {
		return new LMTransportesResumoJRDataSource();
	}
}
