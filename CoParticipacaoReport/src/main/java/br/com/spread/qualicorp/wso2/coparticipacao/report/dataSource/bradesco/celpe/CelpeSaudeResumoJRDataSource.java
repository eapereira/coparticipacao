package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.celpe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeResumoJRDataSource extends CoParticipacaoJRDataSource<CelpeSaudeResumoViewUi> {

	public CelpeSaudeResumoJRDataSource() throws JRException {
		super();
	}

	public CelpeSaudeResumoJRDataSource(List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis) {
		super(celpeSaudeResumoViewUis);
	}

	@Override
	protected List<CelpeSaudeResumoViewUi> buildData() throws JRException {
		List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis = new ArrayList<>();
		CelpeSaudeResumoViewUi celpeSaudeResumoViewUi = new CelpeSaudeResumoViewUi();
		celpeSaudeResumoViewUi.setRamo("Saúde");
		celpeSaudeResumoViewUi.setOperadora("Bradesco");
		celpeSaudeResumoViewUi.setCompetencia("Agosto/2018");
		celpeSaudeResumoViewUi.setValor(new BigDecimal("139586.21"));
		celpeSaudeResumoViewUi.setCompetenciaAnterior("Julho/2018");
		celpeSaudeResumoViewUi.setValorAnterior(new BigDecimal("158780.22"));

		celpeSaudeResumoViewUi
				.setCelpeSaudeResumoDetailViewUis(CelpeSaudeResumoDetailJRDataSource.getInstance().getData());

		celpeSaudeResumoViewUis.add(celpeSaudeResumoViewUi);

		return celpeSaudeResumoViewUis;
	}

	public static CelpeSaudeResumoJRDataSource getInstance() throws JRException {
		return new CelpeSaudeResumoJRDataSource();
	}

}
