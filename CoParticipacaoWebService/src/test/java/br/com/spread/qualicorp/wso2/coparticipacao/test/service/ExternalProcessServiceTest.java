package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExternalProcessService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
@ActiveProfiles("test")
public class ExternalProcessServiceTest extends CoParticipacaoTest {

	public static final String INTERVALOR_BEAPM_COPA = "/home/edson/tmp/8EAPMFATUCOPA.201808001F.txt";

	public static final String INTERVALOR_BEAPM_COPA_EXPECTED = "/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/intervalor/Intervalor-SAS(Saude)_Coparticipacao_(201808)_8EAPM.xlsx";

	@Autowired
	private ExternalProcessService externalProcessService;

	@Autowired
	private ContratoService contratoService;

	@Test
	public void testCreateNameArquivoOutput() throws Exception {
		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setContrato(contratoService.findByCdEmpresaAndCdContrato("INTERVALOR", "BEAPM"));
		arquivoExecucaoUi.setNameArquivoInput(INTERVALOR_BEAPM_COPA);

		externalProcessService.createNameArquivoOutput(arquivoExecucaoUi);

		Assert.assertEquals(INTERVALOR_BEAPM_COPA_EXPECTED, arquivoExecucaoUi.getNameArquivoOutput());
	}
}
