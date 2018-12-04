package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.automind;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindResumoJRDataSource extends CoParticipacaoJRDataSource<AutomindResumoViewUi> {

	private static final String FIELD_NM_SUBFATURA = "subFatura";

	private static final String FIELD_QTDE_SEGURADOS = "qtdeSegurados";

	private static final String FIELD_VL_PROPORCAO = "valorProporcao";

	private static final String FIELD_VL_ALOCACAO = "valorAlocacao";

	public AutomindResumoJRDataSource() throws JRException {
		super();
	}

	public AutomindResumoJRDataSource(List<AutomindResumoViewUi> automindResumoViewUis) throws JRException {
		super(automindResumoViewUis);
	}

	protected List<AutomindResumoViewUi> buildData() throws JRException {
		List<AutomindResumoViewUi> automindResumoViewUis = new ArrayList<>();
		AutomindResumoViewUi automindResumoViewUi = new AutomindResumoViewUi();

		automindResumoViewUi = new AutomindResumoViewUi();
		automindResumoViewUi.setSubFatura("001");
		automindResumoViewUi.setCdEmpresa("AUTOMIND");
		automindResumoViewUi.setQtdeSegurados(56);
		automindResumoViewUi.setValorProporcao(new BigDecimal("73"));
		automindResumoViewUi.setValorAlocacao(new BigDecimal("3126"));
		automindResumoViewUis.add(automindResumoViewUi);

		automindResumoViewUi = new AutomindResumoViewUi();
		automindResumoViewUi.setSubFatura("002");
		automindResumoViewUi.setCdEmpresa("AUTOMIND");
		automindResumoViewUi.setQtdeSegurados(20);
		automindResumoViewUi.setValorProporcao(new BigDecimal("26"));
		automindResumoViewUi.setValorAlocacao(new BigDecimal("1500"));
		automindResumoViewUis.add(automindResumoViewUi);

		return automindResumoViewUis;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_NM_SUBFATURA.equals(jrField.getName())) {
				return getRegister().getSubFatura();
			} else if (FIELD_QTDE_SEGURADOS.equals(jrField.getName())) {
				return getRegister().getQtdeSegurados();
			} else if (FIELD_VL_PROPORCAO.equals(jrField.getName())) {
				return getRegister().getValorProporcao();
			} else if (FIELD_VL_ALOCACAO.equals(jrField.getName())) {
				return getRegister().getValorAlocacao();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() throws JRException {
		return new AutomindResumoJRDataSource();
	}
}
