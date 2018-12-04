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
public class SpreadSaudeResumoInativoJRDataSource extends CoParticipacaoJRDataSource<SpreadSaudeResumoViewUi> {

	public SpreadSaudeResumoInativoJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeResumoInativoJRDataSource(List<SpreadSaudeResumoViewUi> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeResumoViewUi> buildData() throws JRException {
		List<SpreadSaudeResumoViewUi> spreadSaudeResumoViewUis = new ArrayList<>();
		SpreadSaudeResumoViewUi spreadSaudeResumoViewUi;

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("851 - Extensão Inativos (Demitidos)");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("2048.03"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		spreadSaudeResumoViewUi = new SpreadSaudeResumoViewUi();
		spreadSaudeResumoViewUi.setMes(9);
		spreadSaudeResumoViewUi.setAno(2018);
		spreadSaudeResumoViewUi.setNameEmpresa("852 - Extensão Inativos (Aposentados)");
		spreadSaudeResumoViewUi.setValorPrincipal(new BigDecimal("304.71"));
		spreadSaudeResumoViewUis.add(spreadSaudeResumoViewUi);

		return spreadSaudeResumoViewUis;
	}

	public static SpreadSaudeResumoInativoJRDataSource getInstance() throws JRException {
		return new SpreadSaudeResumoInativoJRDataSource();
	}
}
