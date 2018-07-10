package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
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
@ContextConfiguration(
		classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
public class CoParticipacaoServiceTest {

	private static final Logger LOGGER = LogManager
			.getLogger(CoParticipacaoServiceTest.class);

	// private static final String MUITO_FACIL_8CH5YFATUCOPA__201802001F
	// ="/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201802001F.TXT";
	private static final String MUITO_FACIL_8CH5YFATUCOPA__201802001F = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/8CH5YFATUCOPA.201802001F.TXT";

	private static final String MUITO_FACIL_MECSAS_EXPORT_DADOS_1781_20180227_111813 = "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/MECSAS-EXPORT-DADOS-1781-20180227-111813.csv";

	private static final int NUM_TOTAL_TITULARES_MECSAS = 310;
	private static final int NUM_TOTAL_DEPENDENTES_MECSAS = 20;
	private static final int NUM_TOTAL_DESCONHECIDOS_MECSAS = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA = 310;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA = 20;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA = 20;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA = 94;

	// private static final String
	// MUITO_FACIL_MECSAS_EXPORT_DADOS_1781_20180227_111813 =
	// "/desenv/git-repo/coparticipacao/CoParticipacaoWebService/src/test/resources/muito-facil/input/MECSAS-EXPORT-DADOS-1781-20180227-111813.csv";

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
	public void testProcessFatoCopa() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		
		int pos = MUITO_FACIL_8CH5YFATUCOPA__201802001F.lastIndexOf("/") + 1;

		loadMecsas(MUITO_FACIL_MECSAS_EXPORT_DADOS_1781_20180227_111813);

		coParticipacaoService.processFile(
				MUITO_FACIL_8CH5YFATUCOPA__201802001F.substring(pos),
				MUITO_FACIL_8CH5YFATUCOPA__201802001F);
		
		titularUis = titularService.listAll();
		dependenteUis = dependenteService.listAll();
		desconhecidoUis = desconhecidoService.listAll();
		lancamentoUis=lancamentoService.listAll();

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA, desconhecidoUis.size());		
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA, lancamentoUis.size());
	}

	@Test
	public void testProcessMecsas() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;

		loadMecsas(MUITO_FACIL_MECSAS_EXPORT_DADOS_1781_20180227_111813);

		titularUis = titularService.listAll();
		dependenteUis = dependenteService.listAll();
		desconhecidoUis = desconhecidoService.listAll();

		Assert.assertEquals(NUM_TOTAL_TITULARES_MECSAS, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_MECSAS, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_MECSAS, desconhecidoUis.size());
	}

	private void loadMecsas(String filePath) throws Exception {
		int pos = filePath.lastIndexOf("/") + 1;

		coParticipacaoService.processFile(filePath.substring(pos), filePath);
	}

	protected InputStream getInputFile(String filePath) throws Exception {
		InputStream is = null;

		try {
			if (StringUtils.isNotBlank(filePath)) {
				is = getClass().getResourceAsStream(filePath);

				if (is == null) {
					throw new CoParticipacaoException(
							"Arquivo de entrada para processamento [%s] não foi encontrado na pasta de testes:",
							filePath);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return is;
	}
}
