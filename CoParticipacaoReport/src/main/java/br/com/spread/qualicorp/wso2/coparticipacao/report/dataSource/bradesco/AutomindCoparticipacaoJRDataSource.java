package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindCoparticipacaoViewUi;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindCoparticipacaoJRDataSource extends CoParticipacaoDataSource<AutomindCoparticipacaoViewUi> {

	private static final String FIELD_SUBFATURA = "cdEmpresa";
	private static final String FIELD_NR_CERTIFICADO = "certificado";
	private static final String FIELD_NR_MATRICULA = "matricula";
	private static final String FIELD_NM_TITULAR = "nomeTitular";
	private static final String FIELD_VL_FATOR_MODERADOR = "fatorModerador";
	private static final String FIELD_NR_MATRICULA_ESPECIAL = "matriculaEspecial";
	private static final String FIELD_DESCR_PROFISSAO = "profissao";

	public AutomindCoparticipacaoJRDataSource() {
		super();
	}

	protected List<AutomindCoparticipacaoViewUi> buildData() {
		List<AutomindCoparticipacaoViewUi> automindCoparticipacaoViewUis = new ArrayList<>();
		AutomindCoparticipacaoViewUi automindCoparticipacaoViewUi = new AutomindCoparticipacaoViewUi();

		automindCoparticipacaoViewUi.setCdContrato("074210");
		automindCoparticipacaoViewUi.setCdEmpresa("AUTOMIND");
		automindCoparticipacaoViewUi.setCertificado(8345l);

		automindCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		automindCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		automindCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		automindCoparticipacaoViewUi.setMatricula(75001l);
		automindCoparticipacaoViewUi.setProfissao("Costureira");

		automindCoparticipacaoViewUis.add(automindCoparticipacaoViewUi);

		return automindCoparticipacaoViewUis;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if (getRegister() != null) {
			if (FIELD_SUBFATURA.equals(jrField.getName())) {
				return getRegister().getCdContrato();
			} else if (FIELD_NR_CERTIFICADO.equals(jrField.getName())) {
				return getRegister().getCertificado();
			} else if (FIELD_NR_MATRICULA.equals(jrField.getName())) {
				return getRegister().getMatricula();
			} else if (FIELD_NM_TITULAR.equals(jrField.getName())) {
				return getRegister().getNomeTitular();
			} else if (FIELD_VL_FATOR_MODERADOR.equals(jrField.getName())) {
				return getRegister().getFatorModerador();
			} else if (FIELD_NR_MATRICULA_ESPECIAL.equals(jrField.getName())) {
				return getRegister().getMatriculaEspecial();
			} else if (FIELD_DESCR_PROFISSAO.equals(jrField.getName())) {
				return getRegister().getProfissao();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new AutomindCoparticipacaoJRDataSource();
	}

}
