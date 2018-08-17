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

	private static final String MARJAN_ISENTOS_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/Isentos SulAmérica 062018.xlsx";

	private static final String MARJAN_MECSAS_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/MARJAN INDUSTRIA E COMERCIO LTDA_MECSAS-1781-20180614-152849.csv";

	private static final String MARJAN_MECSAS2_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/Benefícios - Contratados_Dep Sulamérica Saúde_sulamerica - Copia.xlsx";
	private static final String MARJAN_MECSAS2_201803 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/Benefícios - Contratados_Dep Sulamérica Saúde_032018_Para Qualicorp.xlsx";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C5Z8FATUCOPA.201806001F.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C7XXFATUCOPA.201806001F.TXT";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201802 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C5Z8FATUCOPA.201802001F.TXT";
	private static final String MARJAN_LANCAMENTOS_8C5Z9_201802 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C5Z9FATUCOPA.201802001F.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201802 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C7XXFATUCOPA.201802001F.TXT";

	private static final String MARJAN_NAO_LOCALIZADO_201806 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/NAO-LOCALIZADO-MARJAN-201806.xlsx";

	private static final String MARJAN_MECSAS2_201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/Benefícios - Contratados_Dep Sulamérica Saúde-201807.xlsx";

	private static final String MARJAN_ISENTOS_201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/Isentos SulAmérica 072018.xlsx";

	private static final String MARJAN_LANCAMENTOS_8C5Z8_201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C5Z8FATUCOPA.201807001F.TXT";
	private static final String MARJAN_LANCAMENTOS_8C7XX_201807 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/marjan/output/8C7XXFATUCOPA.201807001F.TXT";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 754;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 4;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 170;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA = 3400;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_AFTER_USER_RETURN = 754;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_AFTER_USER_RETURN = 569;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_AFTER_USER_RETURN = 174;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_AFTER_USER_RETURN = 3480;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 467;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 4;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 153;
	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA_201807 = 3060;

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
	public void testCoparticipacao() throws Exception {
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

		titularUis = titularService.listByEmpresaId(empresaUi.getId());
		dependenteUis = dependenteService.listByEmpresaId(empresaUi.getId());
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
	public void testCoparticipacaoWithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<LancamentoDetailUi> lancamentoDetailUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");

		testCoparticipacao();

		processFile(MARJAN_NAO_LOCALIZADO_201806);

		processFile(MARJAN_LANCAMENTOS_8C5Z8_201806);
		processFile(MARJAN_LANCAMENTOS_8C7XX_201806);

		titularUis = titularService.listByEmpresaId(empresaUi.getId());
		dependenteUis = dependenteService.listByEmpresaId(empresaUi.getId());
		desconhecidoUis = desconhecidoService.listByEmpresaId(empresaUi);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		lancamentoDetailUis = lancamentoDetailService.listByEmpresaId(empresaUi);

		LOGGER.info("Resultados depois das correções do usuário:", titularUis.size());

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

		titularUis = titularService.listByEmpresaId(empresaUi.getId());
		dependenteUis = dependenteService.listByEmpresaId(empresaUi.getId());
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

		titularUis = titularService.listByEmpresaId(empresaUi.getId());
		dependenteUis = dependenteService.listByEmpresaId(empresaUi.getId());
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

}
