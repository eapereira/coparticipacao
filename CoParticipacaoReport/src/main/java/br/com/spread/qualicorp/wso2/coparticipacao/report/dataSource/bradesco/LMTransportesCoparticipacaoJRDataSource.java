package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LMTransportesCoparticipacaoJRDataSource
		extends CoParticipacaoDataSource<LMTransportesCoparticipacaoViewUi> {

	private static final String FIELD_SUBFATURA = "cdEmpresa";
	private static final String FIELD_NR_CERTIFICADO = "certificado";
	private static final String FIELD_NR_MATRICULA = "matricula";
	private static final String FIELD_NM_TITULAR = "nomeTitular";
	private static final String FIELD_VL_FATOR_MODERADOR = "fatorModerador";
	private static final String FIELD_NR_MATRICULA_ESPECIAL = "matriculaEspecial";
	private static final String FIELD_DESCR_PROFISSAO = "profissao";
	private static final String FIELD_VL_FATOR_MODERADOR_INSS = "fatorModeradorInss";

	public LMTransportesCoparticipacaoJRDataSource() {
		super();
	}

	protected List<LMTransportesCoparticipacaoViewUi> buildData() {
		List<LMTransportesCoparticipacaoViewUi> lmTransportesCoparticipacaoViewUis = new ArrayList<>();
		LMTransportesCoparticipacaoViewUi lmTransportesCoparticipacaoViewUi = new LMTransportesCoparticipacaoViewUi();

		lmTransportesCoparticipacaoViewUi.setCdContrato("074210");
		lmTransportesCoparticipacaoViewUi.setCdEmpresa("AUTOMIND");
		lmTransportesCoparticipacaoViewUi.setCertificado(8345l);

		lmTransportesCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		lmTransportesCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		lmTransportesCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		lmTransportesCoparticipacaoViewUi.setMatricula(75001l);
		lmTransportesCoparticipacaoViewUi.setProfissao("Costureira");

		lmTransportesCoparticipacaoViewUis.add(lmTransportesCoparticipacaoViewUi);

		return lmTransportesCoparticipacaoViewUis;
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
			} else if (FIELD_VL_FATOR_MODERADOR_INSS.equals(jrField.getName())) {
				return getRegister().getFatorModeradorInss();
			}
		}

		return null;
	}

	public static JRDataSource getInstance() {
		return new LMTransportesCoparticipacaoJRDataSource();
	}

}
