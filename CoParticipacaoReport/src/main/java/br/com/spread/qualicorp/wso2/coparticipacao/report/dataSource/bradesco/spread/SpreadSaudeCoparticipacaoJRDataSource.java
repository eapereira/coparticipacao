package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.spread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SpreadSaudeCoparticipacaoJRDataSource extends CoParticipacaoJRDataSource<SpreadSaudeCoparticipacaoViewUi> {

	private static final Logger LOGGER = LogManager.getLogger(SpreadSaudeCoparticipacaoJRDataSource.class);
	private static final int TOTAL_RECORDS = 1000;

	public SpreadSaudeCoparticipacaoJRDataSource() throws JRException {
		super();
	}

	public SpreadSaudeCoparticipacaoJRDataSource(List<SpreadSaudeCoparticipacaoViewUi> data) {
		super(data);
	}

	@Override
	protected List<SpreadSaudeCoparticipacaoViewUi> buildData() throws JRException {
		List<SpreadSaudeCoparticipacaoViewUi> spreadSaudeCoparticipacaoViewUis;
		SpreadSaudeCoparticipacaoViewUi spreadSaudeCoparticipacaoViewUi;

		try {
			spreadSaudeCoparticipacaoViewUis = new ArrayList<>();

			for (int i = 0; i < TOTAL_RECORDS; i++) {
				spreadSaudeCoparticipacaoViewUi = new SpreadSaudeCoparticipacaoViewUi();
				spreadSaudeCoparticipacaoViewUi.setMatriculaEspecial(147623l);
				spreadSaudeCoparticipacaoViewUi.setNameEmpresa("030 - Spread Teleinformática (CEG)");
				spreadSaudeCoparticipacaoViewUi.setNameTitular("ANDREIA SANTOS DO NASCIMENTO SILVA");
				spreadSaudeCoparticipacaoViewUi.setNameBeneficiario("ANDREIA SANTOS DO NASCIMENTO SILVA");
				spreadSaudeCoparticipacaoViewUi.setTpIsento("TITULAR");
				spreadSaudeCoparticipacaoViewUi.setDtUtilizacao(DateUtils.stringToDate("30/07/2018", "dd/MM/yyyy"));
				spreadSaudeCoparticipacaoViewUi.setDescrUtilizacao("ATENDIMENTO AMBULATORIAL");
				spreadSaudeCoparticipacaoViewUi.setPlano("TNQ2");
				spreadSaudeCoparticipacaoViewUi.setDescrUtilizacao("CONSULTA CONSULTORIO (HOR");
				spreadSaudeCoparticipacaoViewUi.setValorPrincipal(new BigDecimal("26.88"));
				spreadSaudeCoparticipacaoViewUi.setValorParticipacao(new BigDecimal("0.00"));
				spreadSaudeCoparticipacaoViewUi.setValorIsento(new BigDecimal("0.00"));
				spreadSaudeCoparticipacaoViewUi.setTpIsento("");
				spreadSaudeCoparticipacaoViewUis.add(spreadSaudeCoparticipacaoViewUi);

				spreadSaudeCoparticipacaoViewUi = new SpreadSaudeCoparticipacaoViewUi();
				spreadSaudeCoparticipacaoViewUi.setMatriculaEspecial(300013l);
				spreadSaudeCoparticipacaoViewUi.setNameEmpresa("030 - Spread Teleinformática (CEG)");
				spreadSaudeCoparticipacaoViewUi.setNameTitular("GILSON MELO MOREIRA");
				spreadSaudeCoparticipacaoViewUi.setNameBeneficiario("ROSANGELA CATANNI MELO MOREIRA");
				spreadSaudeCoparticipacaoViewUi.setTpIsento("DEPENDENTE");
				spreadSaudeCoparticipacaoViewUi.setDtUtilizacao(DateUtils.stringToDate("13/06/2018", "dd/MM/yyyy"));
				spreadSaudeCoparticipacaoViewUi.setDescrUtilizacao("CONSULTA CONSULTORIO (HOR");
				spreadSaudeCoparticipacaoViewUi.setPlano("TNQ2");
				spreadSaudeCoparticipacaoViewUi.setDescrUtilizacao("MEDICAMENTOS");
				spreadSaudeCoparticipacaoViewUi.setValorPrincipal(new BigDecimal("10.61"));
				spreadSaudeCoparticipacaoViewUi.setValorParticipacao(new BigDecimal("8.50"));
				spreadSaudeCoparticipacaoViewUi.setValorIsento(new BigDecimal("8.50"));
				spreadSaudeCoparticipacaoViewUi.setTpIsento("Sim");
				spreadSaudeCoparticipacaoViewUis.add(spreadSaudeCoparticipacaoViewUi);
			}

			return spreadSaudeCoparticipacaoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new JRException(e);
		}
	}

	public static SpreadSaudeCoparticipacaoJRDataSource getInstance() throws JRException {
		return new SpreadSaudeCoparticipacaoJRDataSource();
	}
}
