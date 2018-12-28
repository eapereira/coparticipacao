package br.com.spread.qualicorp.wso2.coparticipacao.test.service.regra;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoInputSheetColsService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
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
	private ArquivoInputSheetService arquivoInputSheetService;

	protected CoParticipacaoContext createCoParticipacaoContext(String cdEmpresa, String cdContrato) throws Exception {
		LOGGER.info("BEGIN");
		EmpresaUi empresaUi = empresaService.findByCdEmpresa(cdEmpresa);
		CoParticipacaoContext coParticipacaoContext = new CoParticipacaoContext();
		ArquivoInputUi arquivoInputUi = arquivoInputService.findByEmpresaAndCdContrato(empresaUi, cdContrato);
		List<ArquivoInputSheetUi> arquivoInputSheetUis = arquivoInputSheetService.listByArquivoInput(arquivoInputUi);

		for (ArquivoInputSheetUi arquivoInputSheetUi : arquivoInputSheetUis) {
			coParticipacaoContext.getMapArquivoInputSheetUi()
					.put(arquivoInputSheetUi.getSheetId(), arquivoInputSheetUi);
		}

		coParticipacaoContext.setRegraUis(regraService.listByArquivoInputId(arquivoInputUi));
		coParticipacaoContext.setRegraConditionalUis(regraConditionalService.listByArquivoInputId(arquivoInputUi));

		LOGGER.info("END");
		return coParticipacaoContext;
	}
}
