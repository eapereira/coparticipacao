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

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class MarjanTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(MarjanTest.class);

	private static final String MARJAN_ISENTOS_201806 = "marjan/output/MARJAN.ISENTO.201806.003.xlsx";

	private static final String MARJAN_MECSAS_201806 = "marjan/output/MARJAN.MECSAS.201806.001.csv";

	private static final String MARJAN_MECSAS2_201806 = "marjan/output/MARJAN.MECSAS2.201806.002.xlsx";
	private static final String MARJAN_MECSAS2_201803 = "marjan/output/MARJAN.MECSAS2.201803.002.xlsx";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201806 = "marjan/output/MARJAN.8C5Z8.201806.004.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201806 = "marjan/output/MARJAN.8C7XX.201806.004.TXT";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201802 = "marjan/output/MARJAN.8C5Z8.201802.004.TXT";
	private static final String MARJAN_LANCAMENTOS_8C5Z9_201802 = "marjan/output/MARJAN.8C5Z9.201802.004.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201802 = "marjan/output/MARJAN.8C7XX.201802.004.TXT";

	private static final String MARJAN_NAO_LOCALIZADO_201806 = "marjan/output/MARJAN.NAO-LOCALIZADO.201806.003.xlsx";

	private static final String MARJAN_MECSAS2_201807 = "marjan/output/MARJAN.MECSAS2.201807.002.xlsx";

	private static final String MARJAN_ISENTOS_201807 = "marjan/output/MARJAN.ISENTO.201807.003.xlsx";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201807 = "marjan/output/MARJAN.8C5Z8.201807.004.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201807 = "marjan/output/MARJAN.8C7XX.201807.004.TXT";

	private static final String MECSAS_201808 = "marjan/output/MARJAN.MECSAS.201808.001.csv";
	private static final String MECSAS2_201808 = "marjan/output/MARJAN.MECSAS2.201808.002.xlsx";
	private static final String ISENTOS_201808 = "marjan/output/MARJAN.ISENTO.201808.003.xlsx";
	private static final String FATUCOPA_8C5Z8_201808 = "marjan/output/MARJAN.8C5Z8.201808.004.TXT";
	private static final String FATUCOPA_8C5Z9_201808 = "marjan/output/MARJAN.8C5Z9.201808.005.TXT";
	private static final String FATUCOPA_8C7XX_201808 = "marjan/output/MARJAN.8C7XX.201808.006.TXT";
	private static final String NAO_LOCALIZADO_201808 = "marjan/output/MARJAN.NAO-LOCALIZADO.201808.004.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201802 = 467;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201802 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201802 = 33;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201802 = 129;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201802 = 2580;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 467;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 8;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806 = 166;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201806 = 3320;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_RETURN = 473;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_RETURN = 567;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806_AFTER_USER_RETURN = 174;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_201806_FATUCOPA_AFTER_USER_RETURN = 3480;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 467;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 4;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 153;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807 = 3060;

	private static final int NUM_TOTAL_TITULARES_201808 = 501;
	private static final int NUM_TOTAL_DEPENDENTES_201808 = 614;
	private static final int NUM_TOTAL_DESCONHECIDOS_201808 = 17;
	private static final int NUM_TOTAL_LANCAMENTOS_201808 = 150;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_201808 = 3000;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201808 = 19;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201808 = 18;

	private static final int NUM_TOTAL_TITULARES_201808_AFTER_USER_RETURN = 513;
	private static final int NUM_TOTAL_DEPENDENTES_201808_AFTER_USER_RETURN = 619;
	private static final int NUM_TOTAL_DESCONHECIDOS_201808_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201808_AFTER_USER_RETURN = 167;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_201808_AFTER_USER_RETURN = 3340;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201808_AFTER_USER_RETURN = 19;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201808_AFTER_USER_RETURN = 18;

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
	public void testCoparticipacao201806() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		processFile(MARJAN_MECSAS_201806);
		processFile(MARJAN_MECSAS2_201806);

		processFile(MARJAN_ISENTOS_201806);

		processFile(MARJAN_LANCAMENTOS_8C5Z8_201806);
		processFile(MARJAN_LANCAMENTOS_8C7XX_201806);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201806, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201806, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201806, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201806WithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		testCoparticipacao201806();

		processFile(MARJAN_NAO_LOCALIZADO_201806);

		processFile(MARJAN_LANCAMENTOS_8C5Z8_201806);
		processFile(MARJAN_LANCAMENTOS_8C7XX_201806);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("Resultados depois das correções do usuário:", titularUis.size());

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_201806_FATUCOPA_AFTER_USER_RETURN, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201802() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		processFile(MARJAN_MECSAS_201806);
		processFile(MARJAN_MECSAS2_201803);

		processFile(MARJAN_ISENTOS_201806);

		processFile(MARJAN_LANCAMENTOS_8C5Z8_201802);
		processFile(MARJAN_LANCAMENTOS_8C5Z9_201802);
		processFile(MARJAN_LANCAMENTOS_8C7XX_201802);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201802, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201802, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201802, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201802, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201802, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201807() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		processFile(MARJAN_MECSAS_201806);
		processFile(MARJAN_MECSAS2_201807);

		processFile(MARJAN_ISENTOS_201807);

		processFile(MARJAN_LANCAMENTOS_8C5Z8_201807);
		processFile(MARJAN_LANCAMENTOS_8C7XX_201807);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201808() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		processFile(MECSAS_201808);
		processFile(MECSAS2_201808);

		processFile(ISENTOS_201808);

		processFile(FATUCOPA_8C5Z8_201808);
		processFile(FATUCOPA_8C5Z9_201808);
		processFile(FATUCOPA_8C7XX_201808);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201808, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201808, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201808, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201808, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_201808, lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201808, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201808, dependenteIsentoUis.size());
	}

	@Test
	public void testCoparticipacao201808AfterUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		testCoparticipacao201808();

		processFile(NAO_LOCALIZADO_201808);

		processFile(ISENTOS_201808);
		
		processFile(FATUCOPA_8C5Z8_201808);
		processFile(FATUCOPA_8C5Z9_201808);
		processFile(FATUCOPA_8C7XX_201808);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201808_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201808_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201808_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201808_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_201808_AFTER_USER_RETURN, lancamentoDetailUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201808_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201808_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

}
