package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeCoparticipacaoResumidaJRDataSource
		extends CoParticipacaoJRDataSource<SpreadSaudeCoparticipacaoResumidaViewUi> {

	public SpreadSaudeCoparticipacaoResumidaJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeCoparticipacaoResumidaJRDataSource(List<SpreadSaudeCoparticipacaoResumidaViewUi> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeCoparticipacaoResumidaViewUi> buildData() throws JRException {
		List<SpreadSaudeCoparticipacaoResumidaViewUi> spreadSaudeCoparticipacaoResumidaViewUis = new ArrayList<>();
		SpreadSaudeCoparticipacaoResumidaViewUi spreadSaudeCoparticipacaoResumidaViewUi;

		spreadSaudeCoparticipacaoResumidaViewUi = new SpreadSaudeCoparticipacaoResumidaViewUi();
		spreadSaudeCoparticipacaoResumidaViewUi.setMes(9);
		spreadSaudeCoparticipacaoResumidaViewUi.setAno(2018);
		spreadSaudeCoparticipacaoResumidaViewUi.setSubFatura("146");
		spreadSaudeCoparticipacaoResumidaViewUi.setMatriculaEspecial(100798l);
		spreadSaudeCoparticipacaoResumidaViewUi.setNameEmpresa("146 - Spread Sistemas e Automação");
		spreadSaudeCoparticipacaoResumidaViewUi.setNameTitular("GLAUCO AIRES FERREIRA");
		spreadSaudeCoparticipacaoResumidaViewUi.setPlano("FXQ2");
		spreadSaudeCoparticipacaoResumidaViewUi.setValorPrincipal(new BigDecimal("389.49"));
		spreadSaudeCoparticipacaoResumidaViewUis.add(spreadSaudeCoparticipacaoResumidaViewUi);

		spreadSaudeCoparticipacaoResumidaViewUi = new SpreadSaudeCoparticipacaoResumidaViewUi();
		spreadSaudeCoparticipacaoResumidaViewUi.setMes(9);
		spreadSaudeCoparticipacaoResumidaViewUi.setAno(2018);
		spreadSaudeCoparticipacaoResumidaViewUi.setSubFatura("146");
		spreadSaudeCoparticipacaoResumidaViewUi.setMatriculaEspecial(100810l);
		spreadSaudeCoparticipacaoResumidaViewUi.setNameEmpresa("146 - Spread Sistemas e Automação");
		spreadSaudeCoparticipacaoResumidaViewUi.setNameTitular("MARCOS FABIO COSSO GOMES");
		spreadSaudeCoparticipacaoResumidaViewUi.setPlano("FXE1");
		spreadSaudeCoparticipacaoResumidaViewUi.setValorPrincipal(new BigDecimal("26.88"));
		spreadSaudeCoparticipacaoResumidaViewUis.add(spreadSaudeCoparticipacaoResumidaViewUi);

		spreadSaudeCoparticipacaoResumidaViewUi = new SpreadSaudeCoparticipacaoResumidaViewUi();
		spreadSaudeCoparticipacaoResumidaViewUi.setMes(9);
		spreadSaudeCoparticipacaoResumidaViewUi.setAno(2018);
		spreadSaudeCoparticipacaoResumidaViewUi.setSubFatura("146");
		spreadSaudeCoparticipacaoResumidaViewUi.setMatriculaEspecial(200098l);
		spreadSaudeCoparticipacaoResumidaViewUi.setNameEmpresa("146 - Spread Sistemas e Automação");
		spreadSaudeCoparticipacaoResumidaViewUi.setNameTitular("KENIA CEZAR ALVES BARBOSA");
		spreadSaudeCoparticipacaoResumidaViewUi.setPlano("FXQ2");
		spreadSaudeCoparticipacaoResumidaViewUi.setValorPrincipal(new BigDecimal("297.22"));
		spreadSaudeCoparticipacaoResumidaViewUis.add(spreadSaudeCoparticipacaoResumidaViewUi);

		spreadSaudeCoparticipacaoResumidaViewUi = new SpreadSaudeCoparticipacaoResumidaViewUi();
		spreadSaudeCoparticipacaoResumidaViewUi.setMes(9);
		spreadSaudeCoparticipacaoResumidaViewUi.setAno(2018);
		spreadSaudeCoparticipacaoResumidaViewUi.setSubFatura("146");
		spreadSaudeCoparticipacaoResumidaViewUi.setMatriculaEspecial(200098l);
		spreadSaudeCoparticipacaoResumidaViewUi.setNameEmpresa("146 - Spread Sistemas e Automação");
		spreadSaudeCoparticipacaoResumidaViewUi.setNameTitular("ALEXANDRE FAGUNDES DE SOUZA");
		spreadSaudeCoparticipacaoResumidaViewUi.setPlano("TNQ2");
		spreadSaudeCoparticipacaoResumidaViewUi.setValorPrincipal(new BigDecimal("403.03"));
		spreadSaudeCoparticipacaoResumidaViewUis.add(spreadSaudeCoparticipacaoResumidaViewUi);

		return spreadSaudeCoparticipacaoResumidaViewUis;
	}

	public static SpreadSaudeCoparticipacaoResumidaJRDataSource getInstance() throws JRException {
		return new SpreadSaudeCoparticipacaoResumidaJRDataSource();
	}
}
