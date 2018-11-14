package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoCoparticipacaoJRDataSource
		extends CoParticipacaoDataSource<TechnitOdontoCoparticipacaoViewUi> {

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

	public TechnitOdontoCoparticipacaoJRDataSource() {
		super();
	}

	public TechnitOdontoCoparticipacaoJRDataSource(
			List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis) {
		super(technitOdontoCoparticipacaoViewUis);
	}

	protected List<TechnitOdontoCoparticipacaoViewUi> buildData() {
		List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis = new ArrayList<>();
		TechnitOdontoCoparticipacaoViewUi technitOdontoCoparticipacaoViewUi;

		technitOdontoCoparticipacaoViewUi = new TechnitOdontoCoparticipacaoViewUi();
		technitOdontoCoparticipacaoViewUi.setTipoRegistro(2);
		technitOdontoCoparticipacaoViewUi.setSubFatura("001");
		technitOdontoCoparticipacaoViewUi.setCdContrato("091707");
		technitOdontoCoparticipacaoViewUi.setCdEmpresa("091707");
		technitOdontoCoparticipacaoViewUi.setCertificado(8345l);

		technitOdontoCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula(75001l);
		technitOdontoCoparticipacaoViewUi.setProfissao("Costureira");

		technitOdontoCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitOdontoCoparticipacaoViewUi.setIndicadorEvento(4);
		technitOdontoCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitOdontoCoparticipacaoViewUis.add(technitOdontoCoparticipacaoViewUi);

		technitOdontoCoparticipacaoViewUi = new TechnitOdontoCoparticipacaoViewUi();
		technitOdontoCoparticipacaoViewUi.setTipoRegistro(2);
		technitOdontoCoparticipacaoViewUi.setSubFatura("001");
		technitOdontoCoparticipacaoViewUi.setCdContrato("091707");
		technitOdontoCoparticipacaoViewUi.setCdEmpresa("091707");
		technitOdontoCoparticipacaoViewUi.setCertificado(8345l);

		technitOdontoCoparticipacaoViewUi.setNomeTitular("MARCEL SIDNEI CAVITTE");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula(75001l);
		technitOdontoCoparticipacaoViewUi.setProfissao("Costureira");

		technitOdontoCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitOdontoCoparticipacaoViewUi.setIndicadorEvento(4);
		technitOdontoCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitOdontoCoparticipacaoViewUis.add(technitOdontoCoparticipacaoViewUi);

		technitOdontoCoparticipacaoViewUi = new TechnitOdontoCoparticipacaoViewUi();
		technitOdontoCoparticipacaoViewUi.setTipoRegistro(2);
		technitOdontoCoparticipacaoViewUi.setSubFatura("001");
		technitOdontoCoparticipacaoViewUi.setCdContrato("091707");
		technitOdontoCoparticipacaoViewUi.setCdEmpresa("091707");
		technitOdontoCoparticipacaoViewUi.setCertificado(1147l);

		technitOdontoCoparticipacaoViewUi.setNomeTitular("FATIMA CRISTINA TAKAHASHI");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula(75001l);
		technitOdontoCoparticipacaoViewUi.setProfissao("Costureira");

		technitOdontoCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitOdontoCoparticipacaoViewUi.setIndicadorEvento(4);
		technitOdontoCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitOdontoCoparticipacaoViewUis.add(technitOdontoCoparticipacaoViewUi);

		return technitOdontoCoparticipacaoViewUis;
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

		return null;
	}

	public static JRDataSource getInstance() {
		return new TechnitOdontoCoparticipacaoJRDataSource();
	}

}
