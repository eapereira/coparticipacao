package br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

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
public class RegraServiceTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(RegraServiceTest.class);

	@Autowired
	private RegraService regraService;

	@Autowired
	private RegraConditionalService regraConditionalService;

	@Autowired
	private ArquivoInputService arquivoInputService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private LancamentoInputColsService lancamentoInputColsService;

	protected CoParticipacaoContext createCoParticipacaoContext(String cdEmpresa, String cdContrato) throws Exception {
		LOGGER.info("BEGIN");
		EmpresaUi empresaUi = empresaService.findByCdEmpresa(cdEmpresa);
		CoParticipacaoContext coParticipacaoContext = new CoParticipacaoContext();
		ArquivoInputUi arquivoInputUi = arquivoInputService.findByEmpresaAndCdContrato(empresaUi, cdContrato);
		List<LancamentoInputColsUi> lancamentoInputColsUis = lancamentoInputColsService
				.listByArquivoInputId(arquivoInputUi);

		coParticipacaoContext.setLancamentoInputColsUis(lancamentoInputColsUis);
		coParticipacaoContext.setRegraUis(regraService.listByArquivoInputId(arquivoInputUi));
		coParticipacaoContext.setRegraConditionalUis(regraConditionalService.listByArquivoInputId(arquivoInputUi));

		LOGGER.info("END");
		return coParticipacaoContext;
	}
}
