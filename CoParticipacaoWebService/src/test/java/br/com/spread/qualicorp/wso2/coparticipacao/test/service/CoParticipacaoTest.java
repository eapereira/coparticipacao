package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.CoParticipacaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.util.StopWatchAdapter;

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
public abstract class CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoTest.class);

	@Autowired
	private CoParticipacaoService coParticipacaoService;

	@Autowired
	private UserService userService;

	@Autowired
	private ExecucaoService execucaoService;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	private StopWatchAdapter stopWatch;

	// public static final String TEST_PATH =
	// "/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/";
	// public static final String TEST_PATH =
	// "/desenv/git-home/coparticipacao/CoParticipacaoWebService/src/test/resources/";

	private static final Long ADMIN_USER_ID = 1l;

	private static final String LINUX_SEPARATOR = "/";

	private static final String WINDOWS_SEPARATOR = "\\\\";

	private Properties properties;

	@Before
	public void beforeTestClearCoparticipacao() throws Exception {
		LOGGER.info("BEGIN");
		LOGGER.info("Cleanning coparticipacao data to perform test:");

		stopWatch = new StopWatchAdapter();
		stopWatch.start();

		coParticipacaoService.clearCoparticipacao(null);
		LOGGER.info("END");
	}

	@After
	public void afterTestCleanUp() throws Exception {
		LOGGER.info("BEGIN");
		stopWatch.stop();

		LOGGER.info("Test completed with [{}] min:", stopWatch.getTotalTimeMinutes());
		LOGGER.info("END");
	}

	public void processFile(ExecucaoUi execucaoUi) throws Exception {
		StringBuilder sb;
		UserUi userUi = getTestUser();
		LocalDate currentDate = LocalDate.now();
		String tmp;

		/*
		 * Vamos criar um Spy do serviço, para evitar que ele mova os arquivos
		 * de teste para a pasta output:
		 */
		CoParticipacaoService coParticipacaoServiceSpy = Mockito.spy(coParticipacaoService);
		Mockito.doNothing().when(coParticipacaoServiceSpy).moveExecucaoToOutput(
				ArgumentMatchers.any(CoParticipacaoContext.class),
				ArgumentMatchers.any(ArquivoExecucaoUi.class));

		Mockito.doNothing().when(coParticipacaoServiceSpy).moveExecucaoToFailure(
				ArgumentMatchers.any(CoParticipacaoContext.class),
				ArgumentMatchers.any(ArquivoExecucaoUi.class));

		for (ArquivoExecucao arquivoExecucao : execucaoUi.getArquivoExecucaos()) {
			sb = new StringBuilder();

			sb.append(getProperty("test.path"));
			sb.append(File.separator);
			sb.append(arquivoExecucao.getNameArquivoInput());

			if (File.separator.equals("\\")) {
				tmp = StringUtils.replaceAll(sb.toString(), LINUX_SEPARATOR, WINDOWS_SEPARATOR);
			} else {
				tmp = sb.toString();
			}

			arquivoExecucao.setNameArquivoInput(tmp);
		}

		execucaoUi.getEmpresa().setUserCreated(userUi);
		execucaoUi.setExecucaoType(ExecucaoType.OPEN);
		execucaoUi.setUserCreated(userUi);

		arquivoExecucaoService.deleteByEmpresaIdAndMesAndAno(
				(EmpresaUi) execucaoUi.getEmpresa(),
				currentDate.getMonthValue(),
				currentDate.getYear());

		execucaoUi = execucaoService.save(execucaoUi);

		coParticipacaoServiceSpy.processExecucaoId(execucaoUi.getId());
	}

	protected UserUi getTestUser() throws Exception {
		return userService.findById(ADMIN_USER_ID);
	}

	public void createArquivoExecucao(
			ExecucaoUi execucaoUi,
			EmpresaUi empresaUi,
			String cdContrato,
			String arquivoInput) throws Exception {
		ArquivoExecucaoUi arquivoExecucaoUi = createArquivoExecucao(empresaUi, cdContrato, arquivoInput);
		execucaoUi.setEmpresa(empresaUi);
		execucaoUi.addArquivoExecucao(arquivoExecucaoUi);
	}

	protected ArquivoExecucaoUi createArquivoExecucao(EmpresaUi empresaUi, String cdContrato, String arquivoInput)
			throws Exception {
		LocalDate currentDate = LocalDate.now();
		UserUi userUi = getTestUser();
		ContratoUi contratoUi = null;
		ArquivoExecucaoUi arquivoExecucaoUi = new ArquivoExecucaoUi();
		arquivoExecucaoUi.setNameArquivoInput(arquivoInput);
		arquivoExecucaoUi.setAno(currentDate.getYear());
		arquivoExecucaoUi.setMes(currentDate.getMonthValue());
		arquivoExecucaoUi.setUserCreated(userUi);
		arquivoExecucaoUi.setStatusExecucaoType(StatusExecucaoType.PENDING);

		for (Contrato contrato : empresaUi.getContratos()) {
			if (contrato.getCdContrato().equals(cdContrato)) {
				contratoUi = (ContratoUi) contrato;
				break;
			}
		}

		if (contratoUi == null) {
			throw new CoParticipacaoException(
					"Não foi possível encontrar o ContratoUI.CD_CONTRATO[%s] para executar o test:",
					cdContrato);
		}

		arquivoExecucaoUi.setContrato(contratoUi);

		return arquivoExecucaoUi;
	}

	public String getProperty(String propertyName) throws Exception {
		File file;
		StringBuilder sb;

		if (properties == null) {
			sb = new StringBuilder();
			sb.append(System.getProperty("user.home"));
			sb.append(File.separator);
			sb.append(".coparticipacao");
			sb.append(File.separator);
			sb.append("coparticipacao.properties");

			file = new File(sb.toString());

			properties = new Properties();

			if (file.exists()) {
				properties.load(new FileInputStream(file));
			} else {
				properties.store(new FileOutputStream(file), "Arquivo para as propriedades de teste do projeto:");

				throw new CoParticipacaoException(
						"É necessário preencher as propriedades em coparticipacao.properties para realizar os testes.");
			}
		}

		return properties.getProperty(propertyName);
	}
}
