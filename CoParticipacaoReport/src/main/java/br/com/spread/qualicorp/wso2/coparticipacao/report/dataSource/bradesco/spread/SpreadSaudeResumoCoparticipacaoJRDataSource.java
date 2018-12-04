package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeResumoCoparticipacaoJRDataSource extends CoParticipacaoJRDataSource<SpreadSaudeResumoViewUi> {

	public SpreadSaudeResumoCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeResumoCoparticipacaoJRDataSource(List<SpreadSaudeResumoViewUi> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeResumoViewUi> buildData() throws JRException {
		List<SpreadSaudeResumoViewUi> spreadSaudeResumoViewUis = new ArrayList<>();
		SpreadSaudeResumoViewUi spreadSaudeResumoViewUi;

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("001 - Spread Teleinformática");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("11218.17"));
		spreadSaudeResumoViewUi.setValorIsento(new BigDecimal("11218.17"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("008 - Spread Teleinformática (Petrobrás)");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("8627.17"));
		spreadSaudeResumoViewUi.setValorIsento(new BigDecimal("8627.17"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("030 - Spread Teleinformática (CEG)");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("456.06"));
		spreadSaudeResumoViewUi.setValorIsento(new BigDecimal("456.06"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("146 - Spread Sistemas e Automação");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("19633.90"));
		spreadSaudeResumoViewUi.setValorIsento(new BigDecimal("14221.46"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("180 - Spread Sistemas e Automação (Petrobrás)");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("644.36"));
		spreadSaudeResumoViewUi.setValorIsento(new BigDecimal("644.36"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		return spreadSaudeResumoViewUis;
	}

	public static SpreadSaudeResumoCoparticipacaoJRDataSource getInstance() throws JRException {
		return new SpreadSaudeResumoCoparticipacaoJRDataSource();
	}
}
