package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindResumoViewUi;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindResumoJRDataSource implements JRDataSource {

	private static final String FIELD_NM_SUBFATURA = "subFatura";

	private static final String FIELD_QTDE_SEGURADOS = "qtdeSegurados";

	private static final String FIELD_VL_PROPORCAO = "valorProporcao";

	private static final String FIELD_VL_ALOCACAO = "valorAlocacao";

	private List<AutomindResumoViewUi> automindResumoViewUis;

	private AutomindResumoViewUi automindResumoViewUi;

	private int cursor;

	public AutomindResumoJRDataSource() {
		this.cursor = NumberUtils.INTEGER_ZERO;

		automindResumoViewUis = new ArrayList<>();

		buildData();
	}

	private void buildData() {
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
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (automindResumoViewUi != null) {
			if (FIELD_NM_SUBFATURA.equals(jrField.getName())) {
				return automindResumoViewUi.getSubFatura();
			} else if (FIELD_QTDE_SEGURADOS.equals(jrField.getName())) {
				return automindResumoViewUi.getQtdeSegurados();
			} else if (FIELD_VL_PROPORCAO.equals(jrField.getName())) {
				return automindResumoViewUi.getValorProporcao();
			} else if (FIELD_VL_ALOCACAO.equals(jrField.getName())) {
				return automindResumoViewUi.getValorAlocacao();
			}
		}

		return null;
	}

	@Override
	public boolean next() throws JRException {
		int pos = NumberUtils.INTEGER_ZERO;

		if (!automindResumoViewUis.isEmpty()) {
			if (cursor < automindResumoViewUis.size()) {
				for (AutomindResumoViewUi view : automindResumoViewUis) {
					if (pos == cursor) {
						automindResumoViewUi = view;
						cursor++;
						return true;
					}

					pos++;
				}
			}
		}

		return false;
	}

	public static JRDataSource getInstance() {
		return new AutomindResumoJRDataSource();
	}
}
