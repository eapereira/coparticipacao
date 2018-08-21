package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class OswaldoCruzTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(OswaldoCruzTest.class);

	private static final String OSWALDO_CRUZ_GESTANTES_201803 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/ISENTO-HOC-20180301.xlsx";

	private static final String OSWALDO_CRUZ_BENEFICIARIOS_201803 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/MECSAS-HOC-20180301.csv";

	private static final String OSWALDO_CRUZ_LANCAMENTOS_201803 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/FATUCOPA-HOC-20180301.csv";

	private static final String NAO_LOCALIZADOS_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/NAO-LOCALIZADO-HOC-201808.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803 = 3005;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803 = 3861;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803 = 62;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803 = 2327;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803 = 34905;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION = 3035;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION = 3882;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION = 11;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION = 2378;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803_AFTER_VALIDATION = 35670;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private EmpresaService empresaService;

	@Test
	public void testCoparticipacao201803() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("HOC");

		processFile(OSWALDO_CRUZ_BENEFICIARIOS_201803);
		processFile(OSWALDO_CRUZ_GESTANTES_201803);

		processFile(OSWALDO_CRUZ_LANCAMENTOS_201803);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803, lancamentoDetailUis.size());

		// List ............Test completed with [16.4412] min:
		// TreeMap ........ Test completed with [14.8239] min:
		// PartitionMap ... Test completed with [16.9364] min:
	}

	@Test
	public void testCoparticipacao201803AfterUserValidation() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("HOC");

		testCoparticipacao201803();

		processFile(NAO_LOCALIZADOS_201808);
		processFile(OSWALDO_CRUZ_LANCAMENTOS_201803);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803_AFTER_VALIDATION, lancamentoDetailUis.size());

		// List ............Test completed with [16.4412] min:
		// TreeMap ........ Test completed with [14.8239] min:
		// PartitionMap ... Test completed with [16.9364] min:
	}

}
