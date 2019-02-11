package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoCoparticipacaoJRDataSource
		extends CoParticipacaoJRDataSource<TechnitOdontoCoparticipacaoViewUi> {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoCoparticipacaoJRDataSource.class);

	public TechnitOdontoCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public TechnitOdontoCoparticipacaoJRDataSource(
			List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis) {
		super(technitOdontoCoparticipacaoViewUis);
	}

	protected List<TechnitOdontoCoparticipacaoViewUi> buildData() throws JRException {
		List<TechnitOdontoCoparticipacaoViewUi> technitOdontoCoparticipacaoViewUis = new ArrayList<>();
		TechnitOdontoCoparticipacaoViewUi technitOdontoCoparticipacaoViewUi;

		technitOdontoCoparticipacaoViewUi = new TechnitOdontoCoparticipacaoViewUi();
		technitOdontoCoparticipacaoViewUi.setTipoRegistro(2);
		technitOdontoCoparticipacaoViewUi.setSubFatura("001");
		technitOdontoCoparticipacaoViewUi.setCdContrato("091707");
		technitOdontoCoparticipacaoViewUi.setCdEmpresa("091707");
		technitOdontoCoparticipacaoViewUi.setCertificado("8345");

		technitOdontoCoparticipacaoViewUi.setNomeTitular("JULIA MC MILLAN CARVALHO CAMPOS");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula("75001");
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
		technitOdontoCoparticipacaoViewUi.setCertificado("8345");

		technitOdontoCoparticipacaoViewUi.setNomeTitular("MARCEL SIDNEI CAVITTE");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula("75001");
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
		technitOdontoCoparticipacaoViewUi.setCertificado("1147");

		technitOdontoCoparticipacaoViewUi.setNomeTitular("FATIMA CRISTINA TAKAHASHI");
		technitOdontoCoparticipacaoViewUi.setFatorModerador(new BigDecimal("34.89"));
		technitOdontoCoparticipacaoViewUi.setMatriculaEspecial(950001l);
		technitOdontoCoparticipacaoViewUi.setMatricula("75001");
		technitOdontoCoparticipacaoViewUi.setProfissao("Costureira");

		technitOdontoCoparticipacaoViewUi.setFatorModeradorInss(new BigDecimal("12.36"));
		technitOdontoCoparticipacaoViewUi.setIndicadorEvento(4);
		technitOdontoCoparticipacaoViewUi.setValorAliquotaInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorInss(BigDecimal.ZERO);
		technitOdontoCoparticipacaoViewUi.setValorLiquidoSinistro(new BigDecimal("258.02"));

		technitOdontoCoparticipacaoViewUis.add(technitOdontoCoparticipacaoViewUi);

		return technitOdontoCoparticipacaoViewUis;
	}

	public static JRDataSource getInstance() throws JRException {
		return new TechnitOdontoCoparticipacaoJRDataSource();
	}

}
