package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeCoparticipacaoJRDataSource extends CoParticipacaoJRDataSource<TechnitSaudeCoparticipacaoViewUi> {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeCoparticipacaoJRDataSource.class);
	
	private static final String FIELD_SUBFATURA = "cdEmpresa";
	private static final String FIELD_NR_CERTIFICADO = "certificado";
	private static final String FIELD_NR_MATRICULA = "matricula";
	private static final String FIELD_NM_TITULAR = "nomeTitular";
	private static final String FIELD_VL_FATOR_MODERADOR = "fatorModerador";
	private static final String FIELD_NR_MATRICULA_ESPECIAL = "matriculaEspecial";
	private static final String FIELD_DESCR_PROFISSAO = "profissao";
	private static final String FIELD_VL_FATOR_MODERADOR_INSS = "fatorModeradorInss";
	private static final String FIELD_NR_SUBFATURA = "subFatura";
	private static final String FIELD_VL_ALIQUOTA_INSS = "valorAliquotaInss";
	private static final String FIELD_VL_INSS = "valorInss";
	private static final String FIELD_VL_LIQUIDO_SINISTRO = "valorLiquidoSinistro";
	private static final String FIELD_INDICADOR_EVENTO = "indicadorEvento";
	private static final String FIELD_TP_REGISTRO = "tipoRegistro";
	private static final String FIELD_CD_CONTRATO = "cdContrato";

	public TechnitSaudeCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public TechnitSaudeCoparticipacaoJRDataSource(
			List<TechnitSaudeCoparticipacaoViewUi> technitSaudeCoparticipacaoViewUis) {
		super(technitSaudeCoparticipacaoViewUis);
	}

	protected List<TechnitSaudeCoparticipacaoViewUi> buildData() throws JRException {
		List<TechnitSaudeCoparticipacaoViewUi> technitSaudeCoparticipacaoViewUis = new ArrayList<>();
		TechnitSaudeCoparticipacaoViewUi technitSaudeCoparticipacaoViewUi;

		technitSaudeCoparticipacaoViewUi = new TechnitSaudeCoparticipacaoViewUi();
		technitSaudeCoparticipacaoViewUi.setTipoRegistro(2);
		technitSaudeCoparticipacaoViewUi.setSubFatura("001");
		technitSaudeCoparticipacaoViewUi.setCdContrato("091707");
		technitSaudeCoparticipacaoViewUi.setCdEmpresa("091707");
		technitSaudeCoparticipacaoViewUi.setCertificado(8345l);

		technitSaudeCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula(75001l);
		technitSaudeCoparticipacaoViewUi.setProfissao("Costureira");

		technitSaudeCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitSaudeCoparticipacaoViewUi.setIndicadorEvento(4);
		technitSaudeCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitSaudeCoparticipacaoViewUis.add(technitSaudeCoparticipacaoViewUi);

		technitSaudeCoparticipacaoViewUi = new TechnitSaudeCoparticipacaoViewUi();
		technitSaudeCoparticipacaoViewUi.setTipoRegistro(2);
		technitSaudeCoparticipacaoViewUi.setSubFatura("001");
		technitSaudeCoparticipacaoViewUi.setCdContrato("091707");
		technitSaudeCoparticipacaoViewUi.setCdEmpresa("091707");
		technitSaudeCoparticipacaoViewUi.setCertificado(8345l);

		technitSaudeCoparticipacaoViewUi.setNomeTitular("MARCEL SIDNEI CAVITTE");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula(75001l);
		technitSaudeCoparticipacaoViewUi.setProfissao("Costureira");

		technitSaudeCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitSaudeCoparticipacaoViewUi.setIndicadorEvento(4);
		technitSaudeCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitSaudeCoparticipacaoViewUis.add(technitSaudeCoparticipacaoViewUi);

		technitSaudeCoparticipacaoViewUi = new TechnitSaudeCoparticipacaoViewUi();
		technitSaudeCoparticipacaoViewUi.setTipoRegistro(2);
		technitSaudeCoparticipacaoViewUi.setSubFatura("001");
		technitSaudeCoparticipacaoViewUi.setCdContrato("091707");
		technitSaudeCoparticipacaoViewUi.setCdEmpresa("091707");
		technitSaudeCoparticipacaoViewUi.setCertificado(1147l);

		technitSaudeCoparticipacaoViewUi.setNomeTitular("FATIMA CRISTINA TAKAHASHI");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula(75001l);
		technitSaudeCoparticipacaoViewUi.setProfissao("Costureira");

		technitSaudeCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitSaudeCoparticipacaoViewUi.setIndicadorEvento(4);
		technitSaudeCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

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
			} else if (FIELD_NR_SUBFATURA.equals(jrField.getName())) {
				return getRegister().getSubFatura();
			} else if (FIELD_VL_ALIQUOTA_INSS.equals(jrField.getName())) {
				return getRegister().getValorAliquotaInss();
			} else if (FIELD_VL_INSS.equals(jrField.getName())) {
				return getRegister().getValorInss();
			} else if (FIELD_VL_LIQUIDO_SINISTRO.equals(jrField.getName())) {
				return getRegister().getValorLiquidoSinistro();
			} else if (FIELD_INDICADOR_EVENTO.equals(jrField.getName())) {
				return getRegister().getIndicadorEvento();
			} else if (FIELD_TP_REGISTRO.equals(jrField.getName())) {
				return getRegister().getTipoRegistro();
			} else if (FIELD_CD_CONTRATO.equals(jrField.getName())) {
				return getRegister().getCdContrato();
			}
		}

		LOGGER.info("Report´s field[{}] not found:", jrField.getName());
		return null;
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitSaudeCoparticipacaoJRDataSource();
	}

}
