package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesResumoJRDataSource extends CoParticipacaoDataSource<LMTransportesResumoViewUi> {

	private static final String FIELD_NM_SUBFATURA = "subFatura";

	private static final String FIELD_QTDE_SEGURADOS = "qtdeSegurados";

	private static final String FIELD_VL_PROPORCAO = "valorProporcao";

	private static final String FIELD_VL_ALOCACAO = "valorAlocacao";

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
		return new LMTransportesResumoJRDataSource();
	}
}
