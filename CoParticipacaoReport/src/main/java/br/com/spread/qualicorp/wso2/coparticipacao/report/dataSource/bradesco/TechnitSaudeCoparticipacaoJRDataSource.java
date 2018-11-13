package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeCoparticipacaoJRDataSource extends CoParticipacaoDataSource<TechnitSaudeCoparticipacaoViewUi> {

	private static final String FIELD_SUBFATURA = "cdEmpresa";
	private static final String FIELD_NR_CERTIFICADO = "certificado";
	private static final String FIELD_NR_MATRICULA = "matricula";
	private static final String FIELD_NM_TITULAR = "nomeTitular";
	private static final String FIELD_VL_FATOR_MODERADOR = "fatorModerador";
	private static final String FIELD_NR_MATRICULA_ESPECIAL = "matriculaEspecial";
	private static final String FIELD_DESCR_PROFISSAO = "profissao";
	private static final String FIELD_VL_FATOR_MODERADOR_INSS = "fatorModeradorInss";

	public TechnitSaudeCoparticipacaoJRDataSource() {
		super();
	}

	protected List<TechnitSaudeCoparticipacaoViewUi> buildData() {
		List<TechnitSaudeCoparticipacaoViewUi> technitSaudeCoparticipacaoViewUis = new ArrayList<>();
		TechnitSaudeCoparticipacaoViewUi technitSaudeCoparticipacaoViewUi = new TechnitSaudeCoparticipacaoViewUi();

		technitSaudeCoparticipacaoViewUi.setCdContrato("074210");
		technitSaudeCoparticipacaoViewUi.setCdEmpresa("AUTOMIND");
		technitSaudeCoparticipacaoViewUi.setCertificado(8345l);

		technitSaudeCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula(75001l);
		technitSaudeCoparticipacaoViewUi.setProfissao("Costureira");

		technitSaudeCoparticipacaoViewUis.add(technitSaudeCoparticipacaoViewUi);

		return technitSaudeCoparticipacaoViewUis;
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
		return new TechnitSaudeCoparticipacaoJRDataSource();
	}

}
