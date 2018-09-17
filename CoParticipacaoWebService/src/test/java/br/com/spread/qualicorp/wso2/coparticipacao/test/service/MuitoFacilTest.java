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

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
// @Transactional
@ActiveProfiles("test")
public class MuitoFacilTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilTest.class);

	// private static final String MUITO_FACIL_8CH5YFATUCOPA__201802001F
	// ="/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201802001F.TXT";
	private static final String MUITO_FACIL_8CH5Y_FATUCOPA__201806001F = "muito-facil/input/MUITO-FACIL.8CH5Y.201806.TXT";

	private static final String MECSAS_EXPORT_DADOS_1781_20180227_111813 = "muito-facil/input/MUITO-FACIL.MECSAS.201802.csv";
	private static final String MECSAS_EXPORT_DADOS_1781_20180810_111813 = "muito-facil/input/MUITO-FACIL.MECSAS.201808.csv";

	private static final String MUITO_FACIL_8CHE8_FATUCOPA__201806001F = "muito-facil/input/MUITO-FACIL.8CHE8.201806.TXT";

	private static final String MUITO_FACIL_8CH5Y_FATUCOPA__201807 = "muito-facil/input/MUITO-FACIL.8CH5Y.201807.TXT";
	private static final String MUITO_FACIL_8CHE8_FATUCOPA__201807 = "muito-facil/input/MUITO-FACIL.8CHE8.201807.TXT";

	private static final String NAO_LOCALIZADO_201808 = "muito-facil/input/MUITO-FACIL.NAO-LOCALIZADO.201808.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 336;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 112;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 93;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807 = 2139;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807_USER_RETURN = 336;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_USER_RETURN = 113;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_USER_RETURN = 93;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807_USER_RETURN = 2139;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 312;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 110;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806 = 98;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201806 = 2254;

	private static final String MECSAS_201809 = "muito-facil/input/MUITO-FACIL.MECSAS.201809.csv";

	private static final String FATUCOPA_8CHE8_201809 = "muito-facil/input/MUITO-FACIL.8CHE8.201809.TXT";
	private static final String FATUCOPA_8CH5Y_201809 = "muito-facil/input/MUITO-FACIL.8CH5Y.201809.TXT";

	private static final String NAO_LOCALIZADO_201809 = "muito-facil/input/MUITO-FACIL.NAO-LOCALIZADO.201809.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201809 = 263;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201809 = 82;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809 = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809 = 82;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201809 = 1886;

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
	public void testCoparticipacao201806() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");

		processFile(MECSAS_EXPORT_DADOS_1781_20180227_111813);

		processFile(MUITO_FACIL_8CH5Y_FATUCOPA__201806001F);
		processFile(MUITO_FACIL_8CHE8_FATUCOPA__201806001F);

		titularUis = titularService.listAll();
		dependenteUis = dependenteService.listAll();
		desconhecidoUis = desconhecidoService.listAll();
		lancamentoUis = lancamentoService.listAll();
		lancamentoDetailUis = lancamentoDetailService.listAll();

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
	public void testCoparticipacao201807() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");

		processFile(MECSAS_EXPORT_DADOS_1781_20180227_111813);
		processFile(MECSAS_EXPORT_DADOS_1781_20180810_111813);

		processFile(MUITO_FACIL_8CH5Y_FATUCOPA__201807);
		processFile(MUITO_FACIL_8CHE8_FATUCOPA__201807);

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
	public void testCoparticipacao201807WithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");

		testCoparticipacao201807();

		processFile(NAO_LOCALIZADO_201808);

		processFile(MUITO_FACIL_8CH5Y_FATUCOPA__201807);
		processFile(MUITO_FACIL_8CHE8_FATUCOPA__201807);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807_USER_RETURN, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201809() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");

		processFile(MECSAS_201809);

		processFile(FATUCOPA_8CH5Y_201809);
		processFile(FATUCOPA_8CHE8_201809);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201809, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201809, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201809, lancamentoDetailUis.size());
	}

}
