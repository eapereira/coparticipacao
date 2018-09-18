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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class OswaldoCruzTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(OswaldoCruzTest.class);

	private static final String OSWALDO_CRUZ_MECSAS_201803 = "oswaldo-cruz/input/0444.MECSAS.201803.001.csv";
	private static final String OSWALDO_CRUZ_GESTANTES_201803 = "oswaldo-cruz/input/0444.ISENTO.201803.002.xlsx";
	private static final String OSWALDO_CRUZ_ISENTO_VALOR_CENTAVOS_201804 = "oswaldo-cruz/input/0444.ISENTO-VALOR-CENTAVOS.201804.003.xlsx";
	private static final String OSWALDO_CRUZ_ISENTO_VALOR_201804 = "oswaldo-cruz/input/0444.ISENTO-VALOR.201804.004.xlsx";
	private static final String OSWALDO_CRUZ_LANCAMENTOS_201803 = "oswaldo-cruz/input/0444.0444.201803.005.csv";
	private static final String NAO_LOCALIZADOS_201808 = "oswaldo-cruz/input/0444.NAO-LOCALIZADO.201808.005.xlsx";

	private static final String MECSAS_201807 = "oswaldo-cruz/input/0444.MECSAS.201807.001.csv";
	private static final String GESTANTES_201807 = "oswaldo-cruz/input/0444.ISENTO.201807.002.xlsx";
	private static final String ISENTO_VALOR_CENTAVOS_201807 = "oswaldo-cruz/input/0444.ISENTO-VALOR-CENTAVOS.201807.003.xlsx";
	private static final String ISENTO_VALOR_INTEGRAL_201807 = "oswaldo-cruz/input/0444.ISENTO-VALOR.201807.004.xlsx";
	private static final String FATUCOPA_201807 = "oswaldo-cruz/input/0444.0444.201807.005.csv";
	private static final String NAO_LOCALIZADOS_201807 = "oswaldo-cruz/input/0444.NAO-LOCALIZADO.201807.005.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803 = 3016;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803 = 3861;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803 = 49;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803 = 2329;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803 = 34935;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201803 = 311;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201803 = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION = 3044;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION = 3882;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION = 2378;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803_AFTER_VALIDATION = 35670;	
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201803_AFTER_USER_VALIDATION = 311;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201803_AFTER_USER_VALIDATION = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 2958;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 3824;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 41;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 2077;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807 = 31155;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807 = 322;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807 = 4;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807_AFTER_USER_VALIDATION = 2984;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_AFTER_USER_VALIDATION = 3839;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_AFTER_USER_VALIDATION = 2118;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807_AFTER_USER_VALIDATION = 31770;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_VALIDATION = 322;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_VALIDATION = 4;


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

	@Autowired
	private TitularIsentoService titularIsentoService;

	@Autowired
	private DependenteIsentoService dependenteIsentoService;

	@Test
	public void testCoparticipacao201803() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;		

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");

		processFile(OSWALDO_CRUZ_MECSAS_201803);
		processFile(OSWALDO_CRUZ_GESTANTES_201803);
		processFile(OSWALDO_CRUZ_ISENTO_VALOR_CENTAVOS_201804);
		processFile(OSWALDO_CRUZ_ISENTO_VALOR_201804);

		processFile(OSWALDO_CRUZ_LANCAMENTOS_201803);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());		

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803, lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201803, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201803, dependenteIsentoUis.size());

		// PartitionMap ... Test completed with [16.6140] min:
	}

	@Test
	public void testCoparticipacao201803AfterUserValidation() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");

		testCoparticipacao201803();

		processFile(NAO_LOCALIZADOS_201808);
		processFile(OSWALDO_CRUZ_LANCAMENTOS_201803);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());		

		LOGGER.info("Results after user's validation:");
		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201803_AFTER_VALIDATION, lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201803_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201803_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
		// PartitionMap ... Test completed with [28.7855] min:
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");

		processFile(MECSAS_201807);
		processFile(GESTANTES_201807);
		processFile(ISENTO_VALOR_CENTAVOS_201807);
		processFile(ISENTO_VALOR_INTEGRAL_201807);

		processFile(FATUCOPA_201807);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807, lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807, dependenteIsentoUis.size());

		// PartitionMap ... Test completed with [16.6140] min:
	}

	@Test
	public void testCoparticipacao201807AfterUserValidation() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;

		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");

		testCoparticipacao201807();

		processFile(NAO_LOCALIZADOS_201807);

		processFile(GESTANTES_201807);
		processFile(ISENTO_VALOR_CENTAVOS_201807);
		processFile(ISENTO_VALOR_INTEGRAL_201807);

		processFile(FATUCOPA_201807);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_AFTER_USER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(
				NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807_AFTER_USER_VALIDATION,
				lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
	}

}
