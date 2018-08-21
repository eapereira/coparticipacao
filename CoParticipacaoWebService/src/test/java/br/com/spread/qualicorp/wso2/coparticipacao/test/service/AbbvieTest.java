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
public class AbbvieTest extends CoParticipacaoTest {
	private static final Logger LOGGER = LogManager.getLogger(MarjanTest.class);

	private static final String ABBVIE_ISENTOS_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/abbvie/output/ISENTOS ABBVIE INTERFACE.xlsx";

	private static final String ABBVIE_MECSAS_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/abbvie/output/Ativos 8B1LR.XLS";
	private static final String ABBVIE_MECSAS2_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/abbvie/output/Ativos 02.2018.xlsx";

	private static final String ABBVIE_LANCAMENTOS_8B1LR_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/abbvie/output/8B1LRFATUCOPA.201802001F.TXT";

	private static final String ABBVIE_NAO_LOCALIZADO_201808 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/abbvie/output/NAO-LOCALIZADO-ABBVIE-201808.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 302;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 449;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 5;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 129;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA = 3096;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN = 306;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN = 449;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN = 133;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_AFTER_USER_RETURN = 3192;

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
	public void testCoparticipacao201808() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("ABBVIE");

		processFile(ABBVIE_MECSAS_201808);
		processFile(ABBVIE_MECSAS2_201808);

		processFile(ABBVIE_ISENTOS_201808);

		processFile(ABBVIE_LANCAMENTOS_8B1LR_201808);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA, lancamentoDetailUis.size());
	}

	@Test
	public void testCoparticipacao201808WithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("ABBVIE");

		testCoparticipacao201808();

		processFile(ABBVIE_NAO_LOCALIZADO_201808);
		processFile(ABBVIE_LANCAMENTOS_8B1LR_201808);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("Return after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total lançamentos details ..... [{}]:", lancamentoDetailUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_AFTER_USER_RETURN, lancamentoDetailUis.size());
	}

}
