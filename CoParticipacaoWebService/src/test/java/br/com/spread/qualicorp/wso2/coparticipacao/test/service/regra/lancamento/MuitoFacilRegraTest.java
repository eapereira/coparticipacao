package br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra.lancamento;

import java.math.BigDecimal;

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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra.RegraServiceTest;

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
public class MuitoFacilRegraTest extends RegraServiceTest {

	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilRegraTest.class);

	private static final BigDecimal VL_PRINCIPAL_01 = new BigDecimal("1505240");
	private static final BigDecimal VL_PRINCIPAL_02 = new BigDecimal("3418");

	private static final BigDecimal VL_PRINCIPAL_01_EXPECTED = new BigDecimal("15052.40");
	private static final BigDecimal VL_PRINCIPAL_02_EXPECTED = new BigDecimal("-34.18");

	@Autowired
	private RegraService regraService;

	@Test
	public void testFatucopa8CHE8() throws Exception {
		CoParticipacaoContext coParticipacaoContext = createCoParticipacaoContext("MUITO-FACIL", "8CHE8");
		LancamentoDetailUi lancamentoDetailUi;

		lancamentoDetailUi = new LancamentoDetailUi();
		lancamentoDetailUi.setValorType(ValorType.POSITIVO);
		lancamentoDetailUi.setValorPrincipal(VL_PRINCIPAL_01);

		regraService.applyRegras(coParticipacaoContext);

		Assert.assertEquals(VL_PRINCIPAL_01_EXPECTED, lancamentoDetailUi.getValorPrincipal());

		lancamentoDetailUi = new LancamentoDetailUi();
		lancamentoDetailUi.setValorType(ValorType.NEGATIVO);
		lancamentoDetailUi.setValorPrincipal(VL_PRINCIPAL_02);

		regraService.applyRegras(coParticipacaoContext);

		Assert.assertEquals(VL_PRINCIPAL_02_EXPECTED, lancamentoDetailUi.getValorPrincipal());
	}

	@Test
	public void testFatucopa8CH5Y() throws Exception {
		CoParticipacaoContext coParticipacaoContext = createCoParticipacaoContext("MUITO-FACIL", "8CH5Y");
		LancamentoDetailUi lancamentoDetailUi;

		lancamentoDetailUi = new LancamentoDetailUi();
		lancamentoDetailUi.setValorType(ValorType.POSITIVO);
		lancamentoDetailUi.setValorPrincipal(VL_PRINCIPAL_01);

		regraService.applyRegras(coParticipacaoContext);

		Assert.assertEquals(VL_PRINCIPAL_01_EXPECTED, lancamentoDetailUi.getValorPrincipal());

		lancamentoDetailUi = new LancamentoDetailUi();
		lancamentoDetailUi.setValorType(ValorType.NEGATIVO);
		lancamentoDetailUi.setValorPrincipal(VL_PRINCIPAL_02);

		regraService.applyRegras(coParticipacaoContext);

		Assert.assertEquals(VL_PRINCIPAL_02_EXPECTED, lancamentoDetailUi.getValorPrincipal());
	}

}
