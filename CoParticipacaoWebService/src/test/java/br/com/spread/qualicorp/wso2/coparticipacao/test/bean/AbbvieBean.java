package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class AbbvieBean extends CoparticipacaoBean {
	private static final Logger LOGGER = LogManager.getLogger(AbbvieBean.class);

	private static final String MECSAS_201808 = "abbvie/input/ABBVIE.MECSAS.201802.001.xlsx";
	private static final String MECSAS2_201808 = "abbvie/input/ABBVIE.MECSAS2.201802.002.xlsx";
	private static final String ISENTOS_201808 = "abbvie/input/ABBVIE.ISENTO.201802.003.xlsx";
	private static final String FATUCOPA_201808 = "abbvie/input/ABBVIE.8B1LR.201802.004.TXT";
	private static final String NAO_LOCALIZADO_201808 = "abbvie/input/ABBVIE.NAO-LOCALIZADO.201802.004.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 302;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 449;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 19;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 114;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN = 321;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN = 465;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN = 133;

	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_MECSAS2 = "MECSAS2";
	private static final String CD_CONTRATO_ISENTO = "ISENTO";
	private static final String CD_CONTRATO_FATUCOPA = "8B1LR";
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private EmpresaService empresaService;

	public void testCoparticipacao201808() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("ABBVIE");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201808);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA, lancamentoUis.size());
	}

	public void testCoparticipacao201808WithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("ABBVIE");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201808();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201808);

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201808);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Return after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN, lancamentoUis.size());
	}
}