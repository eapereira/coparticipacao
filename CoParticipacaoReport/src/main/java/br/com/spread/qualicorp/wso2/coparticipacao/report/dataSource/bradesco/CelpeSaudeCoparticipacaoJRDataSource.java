package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.CoParticipacaoDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CelpeSaudeCoparticipacaoJRDataSource extends CoParticipacaoDataSource<CelpeSaudeCoparticipacaoViewUi> {

	public CelpeSaudeCoparticipacaoJRDataSource() throws JRException {
		super();
		// TODO Auto-generated constructor stub
	}

	public CelpeSaudeCoparticipacaoJRDataSource(List<CelpeSaudeCoparticipacaoViewUi> celpeSaudeCoparticipacaoViewUis) {
		super(celpeSaudeCoparticipacaoViewUis);
	}

	@Override
	protected List<CelpeSaudeCoparticipacaoViewUi> buildData() throws JRException {
		List<CelpeSaudeCoparticipacaoViewUi> celpeSaudeCoparticipacaoViewUis = new ArrayList<>();
		CelpeSaudeCoparticipacaoViewUi celpeSaudeCoparticipacaoViewUi = new CelpeSaudeCoparticipacaoViewUi();
		celpeSaudeCoparticipacaoViewUi.setCdContrato("071421");
		celpeSaudeCoparticipacaoViewUi.setCdUsuario("01");
		celpeSaudeCoparticipacaoViewUi.setNameTitular("DAYVISSON JOSE DA SILVA");
		celpeSaudeCoparticipacaoViewUi.setNameBeneficiario("WILMA ALVES DE SOUZA SILVA");
		celpeSaudeCoparticipacaoViewUi.setValor(new BigDecimal("35.89"));
		celpeSaudeCoparticipacaoViewUi.setCertificado("0036161");
		celpeSaudeCoparticipacaoViewUi.setMatriculaEspecial("1124787");
		celpeSaudeCoparticipacaoViewUi.setPlano("TNE1");
		celpeSaudeCoparticipacaoViewUi.setSubFatura("116");
		celpeSaudeCoparticipacaoViewUi.setCarteiraIdentificacao("771421036161013");
		celpeSaudeCoparticipacaoViewUi.setCpfBeneficiario("07373054447");

		celpeSaudeCoparticipacaoViewUis.add(celpeSaudeCoparticipacaoViewUi);

		celpeSaudeCoparticipacaoViewUi = new CelpeSaudeCoparticipacaoViewUi();
		celpeSaudeCoparticipacaoViewUi.setCdContrato("071421");
		celpeSaudeCoparticipacaoViewUi.setCdUsuario("01");
		celpeSaudeCoparticipacaoViewUi.setNameTitular("DAYVISSON JOSE DA SILVA");
		celpeSaudeCoparticipacaoViewUi.setNameBeneficiario("WILMA ALVES DE SOUZA SILVA");
		celpeSaudeCoparticipacaoViewUi.setValor(new BigDecimal("17.05"));
		celpeSaudeCoparticipacaoViewUi.setCertificado("0036161");
		celpeSaudeCoparticipacaoViewUi.setMatriculaEspecial("1124787");
		celpeSaudeCoparticipacaoViewUi.setPlano("TNE1");
		celpeSaudeCoparticipacaoViewUi.setSubFatura("116");
		celpeSaudeCoparticipacaoViewUi.setCarteiraIdentificacao("771421036161013");
		celpeSaudeCoparticipacaoViewUi.setCpfBeneficiario("07373054447");

		celpeSaudeCoparticipacaoViewUis.add(celpeSaudeCoparticipacaoViewUi);

		celpeSaudeCoparticipacaoViewUi = new CelpeSaudeCoparticipacaoViewUi();
		celpeSaudeCoparticipacaoViewUi.setCdContrato("071421");
		celpeSaudeCoparticipacaoViewUi.setCdUsuario("01");
		celpeSaudeCoparticipacaoViewUi.setNameTitular("DAYVISSON JOSE DA SILVA");
		celpeSaudeCoparticipacaoViewUi.setNameBeneficiario("WILMA ALVES DE SOUZA SILVA");
		celpeSaudeCoparticipacaoViewUi.setValor(new BigDecimal("4.35"));
		celpeSaudeCoparticipacaoViewUi.setCertificado("0036161");
		celpeSaudeCoparticipacaoViewUi.setMatriculaEspecial("1124787");
		celpeSaudeCoparticipacaoViewUi.setPlano("TNE1");
		celpeSaudeCoparticipacaoViewUi.setSubFatura("116");
		celpeSaudeCoparticipacaoViewUi.setCarteiraIdentificacao("771421036161013");
		celpeSaudeCoparticipacaoViewUi.setCpfBeneficiario("07373054447");

		celpeSaudeCoparticipacaoViewUis.add(celpeSaudeCoparticipacaoViewUi);

		return celpeSaudeCoparticipacaoViewUis;
	}

	public static CelpeSaudeCoparticipacaoJRDataSource getInstance() throws JRException {
		return new CelpeSaudeCoparticipacaoJRDataSource();
	}
}
