package br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra.beneficiario;

import java.math.BigDecimal;
import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra.RegraServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
@ActiveProfiles("test")
public class OswaldoCruzRegraServiceTest extends RegraServiceTest {

	private static final Logger LOGGER = LogManager.getLogger(OswaldoCruzRegraServiceTest.class);

	private static final Long ACTUAL_NR_MATRICULA = 44400009040001l;
	private static final Long EXPECTED_NR_MATRICULA = 9040l;

	@Autowired
	private RegraService regraService;

	@Autowired
	private IsentoInputSheetService isentoInputSheetService;

	@Test
	public void testIsentoUtilizacao() throws Exception {
		LOGGER.info("BEGIN");
		CoParticipacaoContext coParticipacaoContext = createCoParticipacaoContext("0444", "ISENTO-UTILIZACAO");
		List<IsentoInputSheetUi> isentoInputSheetUis = isentoInputSheetService
				.listByArquivoInputId(coParticipacaoContext.getArquivoInputUi());
		boolean foundIsentoInputSheet = false;

		BeneficiarioIsentoUi beneficiarioIsentoUi = new BeneficiarioIsentoUi();
		beneficiarioIsentoUi.setName("ADRIANA APARECIDA SANTANA");
		beneficiarioIsentoUi.setCpf(14909293809l);
		beneficiarioIsentoUi.setMatricula(ACTUAL_NR_MATRICULA);
		beneficiarioIsentoUi.setMatriculaTitular(ACTUAL_NR_MATRICULA);
		beneficiarioIsentoUi.setValorIsencao(new BigDecimal(77.05));

		for (IsentoInputSheetUi isentoInputSheetUi : isentoInputSheetUis) {
			foundIsentoInputSheet = true;

			regraService.applyRegras(
					coParticipacaoContext,
					beneficiarioIsentoUi,
					isentoInputSheetUi.getIsentoInputSheetCols());

			LOGGER.info("NR_MATRICULA_ORIGINAL: ......... [{}]", ACTUAL_NR_MATRICULA);
			LOGGER.info("NR_MATRICULA_REGRA: ............ [{}]", beneficiarioIsentoUi.getMatricula());

			Assert.assertEquals(EXPECTED_NR_MATRICULA, beneficiarioIsentoUi.getMatricula());
			break;
		}

		Assert.assertTrue(foundIsentoInputSheet);
		LOGGER.info("END");
	}
}
