package br.com.spread.qualicorp.wso2.coparticipacao.test.service.bradesco;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class AutomindTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(AutomindTest.class);

	private static final String MECSAS_201810 = "automind/input/074210.MECSAS.201810.001.xlsx";
	private static final String MECSAS2_201810 = "automind/input/074210.MECSAS2.201810.002.xlsx";
	private static final String FATUCOPA_201810 = "automind/input/074210.074210.201810.004.xlsx";
	private static final String NAO_LOCALIZADO_201808 = "automind/input/074210.NAO-LOCALIZADO.201810.003.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 137;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 102;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 691;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN = 137;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN = 102;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN = 691;

	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_MECSAS2 = "MECSAS2";
	private static final String CD_CONTRATO_FATUCOPA = "074210";
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

	@Test
	public void testCoparticipacao201810() throws Exception {
		LOGGER.info("BEGIN");

		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("AUTOMIND");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201810);

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

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201810AftrUserValidation() throws Exception {
		LOGGER.info("BEGIN");

		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("AUTOMIND");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201810();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201810);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user´s validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN, lancamentoUis.size());

		LOGGER.info("END");
	}

}
