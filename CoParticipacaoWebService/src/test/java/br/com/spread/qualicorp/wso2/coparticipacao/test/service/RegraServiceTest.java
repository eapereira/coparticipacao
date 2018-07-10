package br.com.spread.qualicorp.wso2.coparticipacao.test.service;

import java.math.BigDecimal;
import java.util.List;

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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(
		classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
public class RegraServiceTest {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraServiceTest.class);

	private static final long ARQUIVO_INPUT_FATUCOPA_ID = 1l;

	private static final long COL_POSITIVO_NEGATIVO_ID = 13l;

	private static final long COL_VL_PRINCIPAL_ID = 14l;

	private static final BigDecimal VL_PRINCIPAL_01 = new BigDecimal("1505240");
	private static final BigDecimal VL_PRINCIPAL_02 = new BigDecimal("3418");

	private static final BigDecimal VL_PRINCIPAL_01_EXPECTED = new BigDecimal(
			"15052.4");
	private static final BigDecimal VL_PRINCIPAL_02_EXPECTED = new BigDecimal(
			"-34.18");

	private static final Long USER_ADMIN_ID = 1l;

	@Autowired
	private RegraService regraService;

	@Autowired
	private RegraConditionalService regraConditionalService;

	@Autowired
	private ArquivoInputColsDefService arquivoInputColsDefService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private UserService userService;

	@Test
	public void testRegraSoma() throws Exception {
		CoParticipacaoContext coParticipacaoContext = createCoParticipacaoContext();
		LancamentoUi lancamentoUi = new LancamentoUi();
		int pos = 0;

		addValue(lancamentoUi, VL_PRINCIPAL_01);
		addValue(lancamentoUi, VL_PRINCIPAL_02);

		regraService.applyRegras(lancamentoUi, coParticipacaoContext);

		Assert.assertEquals(4, lancamentoUi.getLancamentoDetails().size());

		for (LancamentoDetail lancamentoDetail : lancamentoUi
				.getLancamentoDetails()) {
			if (pos == 1) {
				Assert.assertEquals(
						VL_PRINCIPAL_01_EXPECTED,
						lancamentoDetail.getBigDecimalValue());
			} else if (pos == 4) {
				Assert.assertEquals(
						VL_PRINCIPAL_02_EXPECTED,
						lancamentoDetail.getBigDecimalValue());
			}

			pos++;
		}
	}

	private CoParticipacaoContext createCoParticipacaoContext()
			throws Exception {
		CoParticipacaoContext coParticipacaoContext = new CoParticipacaoContext();

		coParticipacaoContext.setRegraUis(
				regraService
						.listRegrasByArquivoInputId(ARQUIVO_INPUT_FATUCOPA_ID));

		coParticipacaoContext.setRegraConditionalUis(
				regraConditionalService
						.listRegrasByArquivoInputId(ARQUIVO_INPUT_FATUCOPA_ID));

		return coParticipacaoContext;
	}

	private LancamentoUi addValue(LancamentoUi lancamentoUi, BigDecimal value)
			throws Exception {
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis = arquivoInputColsDefService
				.listByArquivoInputId(ARQUIVO_INPUT_FATUCOPA_ID);
		UserUi userUi = userService.findById(USER_ADMIN_ID);

		for (ArquivoInputColsDefUi arquivoInputColsDefUi : arquivoInputColsDefUis) {
			if (COL_VL_PRINCIPAL_ID == arquivoInputColsDefUi.getId()) {
				lancamentoDetailService.storeLancamentoDetail(
						lancamentoUi,
						arquivoInputColsDefUi,
						value,
						userUi);
				// Depois de dividir por 100, deve ser igual à 15052.40:
			} else if (COL_POSITIVO_NEGATIVO_ID == arquivoInputColsDefUi
					.getId()) {
				if (value.doubleValue() > 0) {
					lancamentoDetailService.storeLancamentoDetail(
							lancamentoUi,
							arquivoInputColsDefUi,
							"+",
							userUi);

				} else {
					lancamentoDetailService.storeLancamentoDetail(
							lancamentoUi,
							arquivoInputColsDefUi,
							"-",
							userUi);
				}
			}
		}

		return lancamentoUi;
	}

}
