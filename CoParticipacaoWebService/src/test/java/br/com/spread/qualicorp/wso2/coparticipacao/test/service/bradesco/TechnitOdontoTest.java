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
public class TechnitOdontoTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoTest.class);

	private static final String MECSAS_201810 = "technit-odonto/input/091707.MECSAS.201810.001.xlsx";
	private static final String MECSAS2_201810 = "technit-odonto/input/091707.MECSAS2.201810.002.xlsx";
	private static final String FATUCOPA_201810 = "technit-odonto/input/091707.091707.201810.004.xlsx";
	private static final String NAO_LOCALIZADO_201808 = "technit-odonto/input/091707.NAO-LOCALIZADO.201810.002.xlsx";

	private static final String MECSAS_201812 = "technit-odonto/input/091707.MECSAS.201812.001.xlsx";
	private static final String MECSAS2_201812 = "technit-odonto/input/091707.MECSAS2.201812.002.xlsx";
	private static final String FATUCOPA_201812 = "technit-odonto/input/091707.091707.201812.004.xlsx";
	private static final String NAO_LOCALIZADO_201812 = "technit-odonto/input/091707.NAO-LOCALIZADO.201812.002.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201810 = 24;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201810 = 0;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810 = 0;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201812 = 280;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201812 = 323;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201812 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201812 = 0;

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
		EmpresaUi empresaUi = empresaService.findByName("TECHINT-ODONTO");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		// createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS,
		// MECSAS_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201810);
		// createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA,
		// FATUCOPA_201810);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201810, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201810, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810, lancamentoUis.size());

		LOGGER.info("END");
	}

	@Test
	public void testCoparticipacao201812() throws Exception {
		LOGGER.info("BEGIN");

		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("TECHNIT-ODONTO");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201812);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201812);
		// createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA,
		// FATUCOPA_201810);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201812, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201812, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201812, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201812, lancamentoUis.size());

		LOGGER.info("END");
	}

}
