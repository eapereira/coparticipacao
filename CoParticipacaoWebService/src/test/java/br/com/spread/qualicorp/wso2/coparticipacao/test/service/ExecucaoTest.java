package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.XmlUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao.ExecucaoXml;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao.JobXml;

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
public class ExecucaoTest extends CoParticipacaoTest {

	private static final String MECSAS_201807 = "cargill/output/CARGILL.MECSAS.201807.001.csv";
	private static final String MECSAS2_201807 = "cargill/output/CARGILL.MECSAS2.201807.002.xlsx";
	private static final String FATUCOPA_00192_201807 = "cargill/output/CARGILL.00192.201807.003.CSV";
	private static final String FATUCOPA_00196_201807 = "cargill/output/CARGILL.00196.201807.004.CSV";
	private static final String FATUCOPA_00197_201807 = "cargill/output/CARGILL.00197.201807.005.CSV";
	private static final String NAO_LOCALIZADO_201807 = "cargill/output/CARGILL.NAO-LOCALIZADO.201807.003.xlsx";
	private static final String JOBXML_FILE = "/home/eapereira/desenv/work/coparticipacao/input/CARGILL-20180926.copa.xml";

	private static final Long EXECUCAO_ID = 65412l;
	private static final int NUM_EXECUCAO_LIST = 1;
	
	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_MECSAS2 = "MECSAS2";
	private static final String CD_CONTRATO_00192 = "00192";
	private static final String CD_CONTRATO_00196 = "00196";
	private static final String CD_CONTRATO_00197 = "00197";
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ExecucaoService execucaoService;

	@Test
	public void testSendToProcess() throws Exception {
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("CARGILL");
		List<ArquivoExecucaoUi> arquivoExecucaoUis = new ArrayList<ArquivoExecucaoUi>();
		JobXml jobXml;

		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_MECSAS, MECSAS_201807));
		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201807));
		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201807));
		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201807));
		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201807));
		arquivoExecucaoUis.add(createArquivoExecucao(empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201807));

		execucaoService.sendToProcess(arquivoExecucaoUis, empresaUi, getTestUser());

		jobXml = XmlUtils.readFile(JOBXML_FILE, JobXml.class);

		Assert.assertEquals(NUM_EXECUCAO_LIST, jobXml.getExecucaoXmls().size());

		for (ExecucaoXml execucaoXml : jobXml.getExecucaoXmls()) {
			Assert.assertEquals(EXECUCAO_ID, execucaoXml.getExecucaoId());
		}
	}

}
