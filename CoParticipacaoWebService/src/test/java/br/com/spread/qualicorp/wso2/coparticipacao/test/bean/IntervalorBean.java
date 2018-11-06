package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class IntervalorBean {

	private static final Logger LOGGER = LogManager.getLogger(IntervalorBean.class);

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 38;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 36;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806 = 59;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_VALIDATION = 38;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_VALIDATION = 36;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806_AFTER_USER_VALIDATION = 59;

	private static final String FATUCOPA_8EAPM_201806 = "intervalor/input/INTERVALOR.8EAPM.201806.003.txt";
	private static final String FATUCOPA_8EAQG_201806 = "intervalor/input/INTERVALOR.8EAQG.201806.002.txt";
	private static final String FATUCOPA_8EAQS_201806 = "intervalor/input/INTERVALOR.8EAQS.201806.001.txt";
	private static final String FATUCOPA_8EAQR_201806 = "intervalor/input/INTERVALOR.8EAQR.201806.000.txt";
	private static final String NAO_LOCALIZADO_201806 = "intervalor/input/INTERVALOR.NAO-LOCALIZADO.201806.000.xlsx";;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201808 = 29;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201808 = 22;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201808 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201808 = 41;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201808_AFTER_USER_VALIDATION = 29;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201808_AFTER_USER_VALIDATION = 22;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201808_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201808_AFTER_USER_VALIDATION = 41;

	private static final String FATUCOPA_8EAPM_201808 = "intervalor/input/INTERVALOR.8EAPM.201808.001.txt";
	private static final String FATUCOPA_8EAQG_201808 = "intervalor/input/INTERVALOR.8EAQG.201808.002.txt";
	private static final String FATUCOPA_8EAQS_201808 = "intervalor/input/INTERVALOR.8EAQS.201808.000.txt";
	private static final String NAO_LOCALIZADO_201808 = "intervalor/input/INTERVALOR.NAO-LOCALIZADO.201808.000.xlsx";;

	private static final String CD_CONTRATO_8EAPM = "8EAPM";
	private static final String CD_CONTRATO_8EAQR = "8EAQR";
	private static final String CD_CONTRATO_8EAQG = "8EAQG";
	private static final String CD_CONTRATO_8EAQS = "8EAQS";
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";

	@Autowired
	private TitularService titularService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private EmpresaService empresaService;

	public void testCoparticipacao201806(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("INTERVALOR");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAPM, FATUCOPA_8EAPM_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQR, FATUCOPA_8EAQR_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQG, FATUCOPA_8EAQG_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQS, FATUCOPA_8EAQS_201806);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201806, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201806, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806, lancamentoUis.size());
	}

	public void testCoparticipacao201806AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("INTERVALOR");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201806(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201806);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAPM, FATUCOPA_8EAPM_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQR, FATUCOPA_8EAQR_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQG, FATUCOPA_8EAQG_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQS, FATUCOPA_8EAQS_201806);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806_AFTER_USER_VALIDATION, lancamentoUis.size());
	}

	public void testCoparticipacao201808(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("INTERVALOR");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAPM, FATUCOPA_8EAPM_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQG, FATUCOPA_8EAQG_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQS, FATUCOPA_8EAQS_201808);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201808, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201808, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201808, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201808, lancamentoUis.size());
	}

	public void testCoparticipacao201886AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("INTERVALOR");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201808(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201808);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAPM, FATUCOPA_8EAPM_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQG, FATUCOPA_8EAQG_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8EAQS, FATUCOPA_8EAQS_201808);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201808_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201808_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201808_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201808_AFTER_USER_VALIDATION, lancamentoUis.size());
	}

}
