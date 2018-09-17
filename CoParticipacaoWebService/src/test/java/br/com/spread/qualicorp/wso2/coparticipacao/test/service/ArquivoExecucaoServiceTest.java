package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import org.apache.commons.lang3.math.NumberUtils;
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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
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
public class ArquivoExecucaoServiceTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoServiceTest.class);

	private static final String HOC_0444_ACTUAL = "00444.0000000.ARQCOPART_CSV_M_20180707.CSV";
	private static final String HOC_0444_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/input/0444.0444.201809.001.csv";

	private static final String HOC_MECSAS_ACTUAL = "00444.0000000.ARQCADBENEF_CSV_M_20180701.CSV";
	private static final String HOC_MECSAS_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/input/0444.MECSAS.201809.001.csv";

	private static final String HOC_ISENTO_ACTUAL = "Isenção Cop Julho 2018.xlsx";
	private static final String HOC_ISENTO_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/input/0444.ISENTO.201809.001.xlsx";

	private static final String HOC_ISENTO_VALOR_ACTUAL = "ISENÇÃO CENTAVOS.xlsx";
	private static final String HOC_ISENTO_VALOR_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/input/0444.ISENTO-VALOR.201809.001.xlsx";

	private static final String HOC_NAO_LOCALIZADO_ACTUAL = "NAO-LOCALIZADO-HOC-201809.xlsx";
	private static final Object HOC_NAO_LOCALIZADO_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/input/0444.NAO-LOCALIZADO.201809.001.xlsx";

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Test
	public void testRenameToProcess0444() throws Exception {
		ContratoUi contratoUi = contratoService.findByCdEmpresaAndCdContrato("0444", "0444");

		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoUi);
		arquivoExecucaoUi.setNameArquivoInput(HOC_0444_ACTUAL);
		arquivoExecucaoUi.setOrdem(NumberUtils.INTEGER_ONE);

		arquivoExecucaoService.renameToProcess(arquivoExecucaoUi);

		LOGGER.info("Testing rename file [{}] to [{}]:", HOC_0444_ACTUAL, HOC_0444_EXPECTED);
		Assert.assertEquals(HOC_0444_EXPECTED, arquivoExecucaoUi.getNameArquivoInput());
	}

	@Test
	public void testRenameToProcessMECSAS() throws Exception {
		ContratoUi contratoUi = contratoService.findByCdEmpresaAndCdContrato("0444", "MECSAS");

		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoUi);
		arquivoExecucaoUi.setNameArquivoInput(HOC_MECSAS_ACTUAL);
		arquivoExecucaoUi.setOrdem(NumberUtils.INTEGER_ONE);

		arquivoExecucaoService.renameToProcess(arquivoExecucaoUi);

		LOGGER.info("Testing rename file [{}] to [{}]:", HOC_MECSAS_ACTUAL, HOC_MECSAS_EXPECTED);
		Assert.assertEquals(HOC_MECSAS_EXPECTED, arquivoExecucaoUi.getNameArquivoInput());

	}

	@Test
	public void testRenameToProcessISENTO() throws Exception {
		ContratoUi contratoUi = contratoService.findByCdEmpresaAndCdContrato("0444", "ISENTO");

		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoUi);
		arquivoExecucaoUi.setNameArquivoInput(HOC_ISENTO_ACTUAL);
		arquivoExecucaoUi.setOrdem(NumberUtils.INTEGER_ONE);

		arquivoExecucaoService.renameToProcess(arquivoExecucaoUi);

		LOGGER.info("Testing rename file [{}] to [{}]:", HOC_ISENTO_ACTUAL, HOC_ISENTO_EXPECTED);
		Assert.assertEquals(HOC_ISENTO_EXPECTED, arquivoExecucaoUi.getNameArquivoInput());
	}

	@Test
	public void testRenameToProcessISENTO_VALOR() throws Exception {
		ContratoUi contratoUi = contratoService.findByCdEmpresaAndCdContrato("0444", "ISENTO-VALOR");

		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoUi);
		arquivoExecucaoUi.setNameArquivoInput(HOC_ISENTO_VALOR_ACTUAL);
		arquivoExecucaoUi.setOrdem(NumberUtils.INTEGER_ONE);

		arquivoExecucaoService.renameToProcess(arquivoExecucaoUi);

		LOGGER.info("Testing rename file [{}] to [{}]:", HOC_ISENTO_VALOR_ACTUAL, HOC_ISENTO_VALOR_EXPECTED);
		Assert.assertEquals(HOC_ISENTO_VALOR_EXPECTED, arquivoExecucaoUi.getNameArquivoInput());
	}

	@Test
	public void testRenameToProcessNAO_LOCALIZADO() throws Exception {
		ContratoUi contratoUi = contratoService.findByCdEmpresaAndCdContrato("0444", "NAO-LOCALIZADO");

		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoUi);
		arquivoExecucaoUi.setNameArquivoInput(HOC_NAO_LOCALIZADO_ACTUAL);
		arquivoExecucaoUi.setOrdem(NumberUtils.INTEGER_ONE);

		arquivoExecucaoService.renameToProcess(arquivoExecucaoUi);

		LOGGER.info("Testing rename file [{}] to [{}]:", HOC_NAO_LOCALIZADO_ACTUAL, HOC_NAO_LOCALIZADO_EXPECTED);
		Assert.assertEquals(HOC_NAO_LOCALIZADO_EXPECTED, arquivoExecucaoUi.getNameArquivoInput());
	}
	
}
