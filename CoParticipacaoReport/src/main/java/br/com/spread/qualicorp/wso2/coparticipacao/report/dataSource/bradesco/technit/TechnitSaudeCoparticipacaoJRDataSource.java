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

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitSaudeCoparticipacaoJRDataSource
		extends CoParticipacaoJRDataSource<TechnitSaudeCoparticipacaoViewUi> {

	private static final Logger LOGGER = LogManager.getLogger(TechnitSaudeCoparticipacaoJRDataSource.class);

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
		technitSaudeCoparticipacaoViewUi.setCertificado("8345");

		technitSaudeCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		technitSaudeCoparticipacaoViewUi.setNameBeneficiario("");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorPrincipal(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorParticipacao(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula("75001");
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
		technitSaudeCoparticipacaoViewUi.setCertificado("8345");

		technitSaudeCoparticipacaoViewUi.setNomeTitular("MARCEL SIDNEI CAVITTE");
		technitSaudeCoparticipacaoViewUi.setNameBeneficiario("");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorPrincipal(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorParticipacao(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula("75001");
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
		technitSaudeCoparticipacaoViewUi.setCertificado("1147");

		technitSaudeCoparticipacaoViewUi.setNomeTitular("FATIMA CRISTINA TAKAHASHI");
		technitSaudeCoparticipacaoViewUi.setNameBeneficiario("");
		technitSaudeCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorPrincipal(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setValorParticipacao(new BigDecimal("34.89"));
		technitSaudeCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitSaudeCoparticipacaoViewUi.setMatricula("75001");
		technitSaudeCoparticipacaoViewUi.setProfissao("Costureira");

		technitSaudeCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitSaudeCoparticipacaoViewUi.setIndicadorEvento(4);
		technitSaudeCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitSaudeCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitSaudeCoparticipacaoViewUis.add(technitSaudeCoparticipacaoViewUi);

		return technitSaudeCoparticipacaoViewUis;
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitSaudeCoparticipacaoJRDataSource();
	}

}
