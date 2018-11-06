package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class MarjanBean {

	private static final Logger LOGGER = LogManager.getLogger(MarjanBean.class);

	private static final String ISENTOS_201806 = "marjan/input/MARJAN.ISENTO.201806.003.xlsx";

	private static final String MECSAS_201806 = "marjan/input/MARJAN.MECSAS.201806.001.csv";

	private static final String MECSAS2_201806 = "marjan/input/MARJAN.MECSAS2.201806.002.xlsx";
	private static final String MECSAS2_201803 = "marjan/input/MARJAN.MECSAS2.201803.002.xlsx";

	private static final String FATUCOPA_8C5Z8_201806 = "marjan/input/MARJAN.8C5Z8.201806.004.TXT";
	private static final String FATUCOPA_8C7XX_201806 = "marjan/input/MARJAN.8C7XX.201806.004.TXT";

	private static final String FATUCOPA_8C5Z8_201802 = "marjan/input/MARJAN.8C5Z8.201802.004.TXT";
	private static final String FATUCOPA_8C5Z9_201802 = "marjan/input/MARJAN.8C5Z9.201802.004.TXT";
	private static final String FATUCOPA_8C7XX_201802 = "marjan/input/MARJAN.8C7XX.201802.004.TXT";

	private static final String NAO_LOCALIZADO_201806 = "marjan/input/MARJAN.NAO-LOCALIZADO.201806.003.xlsx";

	private static final String MECSAS2_201807 = "marjan/input/MARJAN.MECSAS2.201807.002.xlsx";

	private static final String ISENTOS_201807 = "marjan/input/MARJAN.ISENTO.201807.003.xlsx";

	private static final String FATUCOPA_8C5Z8_201807 = "marjan/input/MARJAN.8C5Z8.201807.004.TXT";
	private static final String FATUCOPA_8C7XX_201807 = "marjan/input/MARJAN.8C7XX.201807.004.TXT";

	private static final String MECSAS_201808 = "marjan/input/MARJAN.MECSAS.201808.001.csv";
	private static final String MECSAS2_201808 = "marjan/input/MARJAN.MECSAS2.201808.002.xlsx";
	private static final String ISENTOS_201808 = "marjan/input/MARJAN.ISENTO.201808.003.xlsx";
	private static final String FATUCOPA_8C5Z8_201808 = "marjan/input/MARJAN.8C5Z8.201808.004.TXT";
	private static final String FATUCOPA_8C5Z9_201808 = "marjan/input/MARJAN.8C5Z9.201808.005.TXT";
	private static final String FATUCOPA_8C7XX_201808 = "marjan/input/MARJAN.8C7XX.201808.006.TXT";
	private static final String NAO_LOCALIZADO_201808 = "marjan/input/MARJAN.NAO-LOCALIZADO.201808.004.xlsx";

	private static final String MECSAS_201810 = "marjan/input/MARJAN.MECSAS.201810.001.csv";
	private static final String MECSAS2_201810 = "marjan/input/MARJAN.MECSAS2.201810.002.xlsx";
	private static final String ISENTOS_201810 = "marjan/input/MARJAN.ISENTO.201810.003.xlsx";
	private static final String FATUCOPA_8C5Z8_201810 = "marjan/input/MARJAN.8C5Z8.201810.004.TXT";
	private static final String FATUCOPA_8C5Z9_201810 = "marjan/input/MARJAN.8C5Z9.201810.005.TXT";
	private static final String FATUCOPA_8C7XX_201810 = "marjan/input/MARJAN.8C7XX.201810.006.TXT";
	private static final String NAO_LOCALIZADO_201810 = "marjan/input/MARJAN.NAO-LOCALIZADO.201810.004.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201802 = 481;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201802 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201802 = 18;
	private static final int NUM_TOTAL_FATUCOPA_FATUCOPA_201802 = 143;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 471;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 4;
	private static final int NUM_TOTAL_FATUCOPA_FATUCOPA_201806 = 170;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_RETURN = 473;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_RETURN = 569;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_FATUCOPA_FATUCOPA_201806_AFTER_USER_RETURN = 174;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 468;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 565;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 3;
	private static final int NUM_TOTAL_FATUCOPA_FATUCOPA_201807 = 154;

	private static final int NUM_TOTAL_TITULARES_201808 = 511;
	private static final int NUM_TOTAL_DEPENDENTES_201808 = 614;
	private static final int NUM_TOTAL_DESCONHECIDOS_201808 = 7;
	private static final int NUM_TOTAL_FATUCOPA_201808 = 160;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201808 = 29;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201808 = 14;

	private static final int NUM_TOTAL_TITULARES_201808_AFTER_USER_RETURN = 513;
	private static final int NUM_TOTAL_DEPENDENTES_201808_AFTER_USER_RETURN = 621;
	private static final int NUM_TOTAL_DESCONHECIDOS_201808_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_FATUCOPA_201808_AFTER_USER_RETURN = 167;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201808_AFTER_USER_RETURN = 29;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201808_AFTER_USER_RETURN = 14;

	private static final int NUM_TOTAL_TITULARES_201810 = 511;
	private static final int NUM_TOTAL_DEPENDENTES_201810 = 614;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810 = 7;
	private static final int NUM_TOTAL_FATUCOPA_201810 = 160;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810 = 29;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810 = 14;

	private static final int NUM_TOTAL_TITULARES_201810_AFTER_USER_RETURN = 513;
	private static final int NUM_TOTAL_DEPENDENTES_201810_AFTER_USER_RETURN = 621;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_FATUCOPA_201810_AFTER_USER_RETURN = 167;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_RETURN = 29;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_RETURN = 14;

	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_MECSAS2 = "MECSAS2";
	private static final String CD_CONTRATO_8C5Z8 = "8C5Z8";
	private static final String CD_CONTRATO_8C5Z9 = "8C5Z9";
	private static final String CD_CONTRATO_8C7XX = "8C7XX";
	private static final String CD_CONTRATO_8C7XY = "8C7XY";
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";
	private static final String CD_CONTRATO_ISENTO = "ISENTO";

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

	@Autowired
	private TitularIsentoService titularIsentoService;

	@Autowired
	private DependenteIsentoService dependenteIsentoService;

	public void testCoparticipacao201806(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201806);

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
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_FATUCOPA_201806, lancamentoUis.size());
	}

	public void testCoparticipacao201806WithUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201806(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201806);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Resultados depois das correções do usuário:", titularUis.size());

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201806_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201806_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_FATUCOPA_201806_AFTER_USER_RETURN, lancamentoUis.size());
	}

	public void testCoparticipacao201802(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201803);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201802);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z9, FATUCOPA_8C5Z9_201802);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201802);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201802, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201802, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201802, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_FATUCOPA_201802, lancamentoUis.size());
	}

	public void testCoparticipacao201807(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201806);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201807);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_FATUCOPA_201807, lancamentoUis.size());
	}

	public void testCoparticipacao201808(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z9, FATUCOPA_8C5Z9_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201808);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201808, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201808, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201808, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_201808, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201808, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201808, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201808AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201808(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z9, FATUCOPA_8C5Z9_201808);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201808);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201808_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201808_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201808_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_201808_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201808_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201808_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201810(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z9, FATUCOPA_8C5Z9_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201810);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201810, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201810, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201810, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_201810, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201810, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201810, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201810AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("Marjan");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201810(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTOS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z8, FATUCOPA_8C5Z8_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C5Z9, FATUCOPA_8C5Z9_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8C7XX, FATUCOPA_8C7XX_201810);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201810_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201810_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201810_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_FATUCOPA_201810_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

}
