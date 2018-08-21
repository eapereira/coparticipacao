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
import org.springframework.transaction.annotation.Transactional;

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
//@Transactional
@ActiveProfiles("test")
public class MuitoFacilTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilTest.class);

	// private static final String MUITO_FACIL_8CH5YFATUCOPA__201802001F
	// ="/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201802001F.TXT";
	private static final String MUITO_FACIL_8CH5Y_FATUCOPA__201806001F = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201806001F.TXT";

	private static final String MECSAS_EXPORT_DADOS_1781_20180227_111813 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/MECSAS-EXPORT-DADOS-1781-20180227-111813.csv";
	private static final String MECSAS_EXPORT_DADOS_1781_20180810_111813 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/MECSAS-EXPORT-DADOS-1781-20180810-111813.csv";

	private static final String MUITO_FACIL_8CHE8_FATUCOPA__201806001F = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CHE8FATUCOPA.201806001F.TXT";

	private static final String MUITO_FACIL_8CH5Y_FATUCOPA__201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201807001F.TXT";
	private static final String MUITO_FACIL_8CHE8_FATUCOPA__201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CHE8FATUCOPA.201807001F.TXT";

	private static final String NAO_LOCALIZADO_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/NAO-LOCALIZADO-MUITO-FACIL-201808.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 335;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 25;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 92;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807 = 2116;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807_USER_RETURN = 336;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_USER_RETURN = 25;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_USER_RETURN = 93;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807_USER_RETURN = 2139;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 310;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 25;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 2;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806 = 96;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201806 = 2208;

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

}
