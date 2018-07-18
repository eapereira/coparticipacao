package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(
		classes = { CoParticipacaoWebServiceConfigurationTest.class })
//@Transactional
public class OswaldoCruzTest {

	private static final String OSWALDO_CRUZ_GESTANTES_201803 = "/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/Isenção Cop março 2018.xlsx";

	private static final String OSWALDO_CRUZ_BENEFICIARIOS_201803 = "/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/00444.0000000.ARQCADBENEF_CSV_M_20180301.CSV";

	private static final String OSWALDO_CRUZ_LANCAMENTOS_201803 = "/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/oswaldo-cruz/input/00444.0000000.ARQCOPART_CSV_M_20180310.CSV";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 310;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 20;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 25;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 94;

	private static final int NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA = 2162;

	@Autowired
	private CoParticipacaoService coParticipacaoService;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private LancamentoService lancamentoService;

	@Test
	public void testGestantes() throws Exception {
		int totalLancamentoDetails = 0;
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;

		processFile(OSWALDO_CRUZ_BENEFICIARIOS_201803);

		processFile(OSWALDO_CRUZ_GESTANTES_201803);

		processFile(OSWALDO_CRUZ_LANCAMENTOS_201803);

		titularUis = titularService.listAll();
		dependenteUis = dependenteService.listAll();
		desconhecidoUis = desconhecidoService.listAll();
		lancamentoUis = lancamentoService.listAll();

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA, titularUis.size());
		Assert.assertEquals(
				NUM_TOTAL_DEPENDENTES_FATUCOPA,
				dependenteUis.size());
		Assert.assertEquals(
				NUM_TOTAL_DESCONHECIDOS_FATUCOPA,
				desconhecidoUis.size());
		Assert.assertEquals(
				NUM_TOTAL_LANCAMENTOS_FATUCOPA,
				lancamentoUis.size());
		Assert.assertEquals(
				NUM_TOTAL_LANCAMENTOS_DETAIL_FATUCOPA,
				totalLancamentoDetails);
	}

	private void processFile(String filePath) throws Exception {
		int pos = filePath.lastIndexOf("/") + 1;

		coParticipacaoService.processFile(filePath.substring(pos), filePath);
	}
}
