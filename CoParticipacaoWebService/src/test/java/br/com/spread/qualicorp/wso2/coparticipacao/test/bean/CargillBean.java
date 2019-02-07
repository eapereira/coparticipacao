package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
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
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.SpreadsheetReader;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl.sulamerica.CargillData;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.impl.sulamerica.CargillSpreadsheetReaderListerner;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class CargillBean {

	private static final Logger LOGGER = LogManager.getLogger(CargillBean.class);

	private static final String MECSAS_201807 = "cargill/input/CARGILL.MECSAS.201807.001.csv";
	private static final String MECSAS2_201807 = "cargill/input/CARGILL.MECSAS2.201807.002.xlsx";
	private static final String FATUCOPA_00192_201807 = "cargill/input/CARGILL.00192.201807.003.CSV";
	private static final String FATUCOPA_00196_201807 = "cargill/input/CARGILL.00196.201807.004.CSV";
	private static final String FATUCOPA_00197_201807 = "cargill/input/CARGILL.00197.201807.005.CSV";
	private static final String NAO_LOCALIZADO_201807 = "cargill/input/CARGILL.NAO-LOCALIZADO.201807.003.xlsx";

	private static final int NUM_TOTAL_TITULARES_201807 = 1582;
	private static final int NUM_TOTAL_DEPENDENTES_201807 = 1181;
	private static final int NUM_TOTAL_DESCONHECIDOS_201807 = 3;
	private static final int NUM_TOTAL_LANCAMENTOS_201807 = 107;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807 = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807 = 0;

	private static final int NUM_TOTAL_TITULARES_201807_AFTER_USER_RETURN = 1586;
	private static final int NUM_TOTAL_DEPENDENTES_201807_AFTER_USER_RETURN = 1183;
	private static final int NUM_TOTAL_DESCONHECIDOS_201807_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201807_AFTER_USER_RETURN = 110;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_RETURN = 0;

	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_MECSAS2 = "MECSAS2";
	private static final String CD_CONTRATO_00192 = "00192";
	private static final String CD_CONTRATO_00196 = "00196";
	private static final String CD_CONTRATO_00197 = "00197";
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";
	private static final String CD_CONTRATO_ISENTO = "ISENTO";
	private static final String CD_CONTRATO_INATIVO = "INATIVO";

	private static final String MECSAS_201810 = "cargill/input/CARGILL.MECSAS.201810.001.csv";
	private static final String MECSAS2_201810 = "cargill/input/CARGILL.MECSAS2.201810.002.xlsx";
	private static final String FATUCOPA_00192_201810 = "cargill/input/CARGILL.00192.201810.004.CSV";
	private static final String FATUCOPA_00196_201810 = "cargill/input/CARGILL.00196.201810.005.CSV";
	private static final String FATUCOPA_00197_201810 = "cargill/input/CARGILL.00197.201810.006.CSV";
	private static final String NAO_LOCALIZADO_201810 = "cargill/input/CARGILL.NAO-LOCALIZADO.201810.003.xlsx";

	private static final String MECSAS_201811 = "cargill/input/CARGILL.MECSAS.201811.001.csv";
	private static final String MECSAS2_201811 = "cargill/input/CARGILL.MECSAS2.201811.002.xlsx";
	private static final String FATUCOPA_00192_201811 = "cargill/input/CARGILL.00192.201811.004.CSV";
	private static final String FATUCOPA_00196_201811 = "cargill/input/CARGILL.00196.201811.005.CSV";
	private static final String FATUCOPA_00197_201811 = "cargill/input/CARGILL.00197.201811.006.CSV";
	private static final String NAO_LOCALIZADO_201811 = "cargill/input/CARGILL.NAO-LOCALIZADO.201811.003.xlsx";
	private static final String ISENTO_201811 = "cargill/input/CARGILL.ISENTO.201811.003.xlsx";
	private static final String INATIVO_201811 = "cargill/input/CARGILL.INATIVO.201811.004.xlsx";

	private static final String MECSAS_201901 = "cargill/input/201901/CARGILL.MECSAS.201901.001.csv";
	private static final String MECSAS2_201901 = "cargill/input/201901/CARGILL.MECSAS2.201901.002.xlsx";
	private static final String ISENTO_201901 = "cargill/input/201901/CARGILL.ISENTO.201901.003.xlsx";
	private static final String INATIVO_201901 = "cargill/input/201901/CARGILL.INATIVO.201901.004.xlsx";
	private static final String FATUCOPA_00192_201901 = "cargill/input/201901/CARGILL.00192.201901.005.csv";
	private static final String FATUCOPA_00196_201901 = "cargill/input/201901/CARGILL.00196.201901.006.csv";
	private static final String FATUCOPA_00197_201901 = "cargill/input/201901/CARGILL.00197.201901.007.csv";
	private static final String NAO_LOCALIZADO_201901 = "cargill/input/201901/CARGILL.NAO-LOCALIZADO.201901.003.xlsx";

	private static final String MECSAS_201812 = "cargill/input/201812/CARGILL.MECSAS.201812.001.csv";
	private static final String MECSAS2_201812 = "cargill/input/201812/CARGILL.MECSAS2.201812.002.xlsx";
	private static final String ISENTO_201812 = "cargill/input/201812/CARGILL.ISENTO.201812.003.xlsx";
	private static final String INATIVO_201812 = "cargill/input/201812/CARGILL.INATIVO.201812.004.xlsx";
	private static final String FATUCOPA_00192_201812 = "cargill/input/201812/CARGILL.00192.201812.005.csv";
	private static final String FATUCOPA_00196_201812 = "cargill/input/201812/CARGILL.00196.201812.006.csv";
	private static final String FATUCOPA_00197_201812 = "cargill/input/201812/CARGILL.00197.201812.007.csv";
	private static final String NAO_LOCALIZADO_201812 = "cargill/input/201812/CARGILL.NAO-LOCALIZADO.201812.003.xlsx";

	private static final int NUM_TOTAL_TITULARES_201810 = 1616;
	private static final int NUM_TOTAL_DEPENDENTES_201810 = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810 = 2;
	private static final int NUM_TOTAL_LANCAMENTOS_201810 = 131;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810 = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810 = 0;

	private static final int NUM_TOTAL_TITULARES_201810_AFTER_USER_RETURN = 1618;
	private static final int NUM_TOTAL_DEPENDENTES_201810_AFTER_USER_RETURN = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201810_AFTER_USER_RETURN = 133;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_RETURN = 0;

	private static final int NUM_TOTAL_TITULARES_201811 = 1616;
	private static final int NUM_TOTAL_DEPENDENTES_201811 = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201811 = 2;
	private static final int NUM_TOTAL_LANCAMENTOS_201811 = 131;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201811 = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201811 = 6;

	private static final int NUM_TOTAL_TITULARES_201811_AFTER_USER_RETURN = 1618;
	private static final int NUM_TOTAL_DEPENDENTES_201811_AFTER_USER_RETURN = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201811_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201811_AFTER_USER_RETURN = 133;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201811_AFTER_USER_RETURN = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201811_AFTER_USER_RETURN = 6;

	private static final int NUM_TOTAL_TITULARES_201901 = 1702;
	private static final int NUM_TOTAL_DEPENDENTES_201901 = 1235;
	private static final int NUM_TOTAL_DESCONHECIDOS_201901 = 3;
	private static final int NUM_TOTAL_LANCAMENTOS_201901 = 86;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201901 = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201901 = 6;

	private static final int NUM_TOTAL_TITULARES_201901_AFTER_USER_RETURN = 1703;
	private static final int NUM_TOTAL_DEPENDENTES_201901_AFTER_USER_RETURN = 1235;
	private static final int NUM_TOTAL_DESCONHECIDOS_201901_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201901_AFTER_USER_RETURN = 89;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201901_AFTER_USER_RETURN = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201901_AFTER_USER_RETURN = 6;

	private static final int NUM_TOTAL_TITULARES_201812 = 1702;
	private static final int NUM_TOTAL_DEPENDENTES_201812 = 1235;
	private static final int NUM_TOTAL_DESCONHECIDOS_201812 = 3;
	private static final int NUM_TOTAL_LANCAMENTOS_201812 = 86;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201812 = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201812 = 5;

	private static final int NUM_TOTAL_TITULARES_201812_AFTER_USER_RETURN = 1705;
	private static final int NUM_TOTAL_DEPENDENTES_201812_AFTER_USER_RETURN = 1238;
	private static final int NUM_TOTAL_DESCONHECIDOS_201812_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201812_AFTER_USER_RETURN = 89;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201812_AFTER_USER_RETURN = 6;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201812_AFTER_USER_RETURN = 5;

	private static final int NUM_TOTAL_REGISTROS_201812 = 51;
	private static final BigDecimal NUM_TOTAL_VL_PRINCIPAL_201812 = new BigDecimal("2679.55");

	private static final int NUM_TOTAL_REGISTROS_201812_AFTER_USER_RETURN = 65;
	private static final BigDecimal NUM_TOTAL_VL_PRINCIPAL_201812_AFTER_USER_RETURN = new BigDecimal("4037.78");

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

	@Autowired
	private SpreadsheetReader spreadsheetReader;

	public void testCoparticipacao201807(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201807);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201807, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201807, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201807, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201807, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201807AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201807(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201807);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201807);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201807_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201807_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201807_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201807_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201810(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201810);

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
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201810, lancamentoUis.size());
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
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201810(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201810);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201810);

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
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201810_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201811(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201811);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201811, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201811, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201811, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201811, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201811, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201811, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201811AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201811(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201811);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201811);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201811);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201811);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201811_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201811_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201811_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201811_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201811_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201811_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201901(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201901);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201901);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201901, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201901, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201901, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201901, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201901, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201901, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201901AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201901(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201901);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201901);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201901);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_201901_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201901_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201901_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201901_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201901_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201901_AFTER_USER_RETURN, dependenteIsentoUis.size());
	}

	public void testCoparticipacao201812(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();
		TestInfo testInfo;

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201812);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201812);

		execucaoUi = coParticipacaoTest.processFile(execucaoUi);

		testInfo = createTestInfo(coParticipacaoTest, empresaUi, execucaoUi);

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

		LOGGER.info("Total registros ............... [{}]:", testInfo.getTotalRegistros());
		LOGGER.info("Total valor ................... [{}]:", testInfo.getTotalValorPrincipal());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201812, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201812, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201812, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201812, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201812, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201812, dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_REGISTROS_201812, testInfo.getTotalRegistros());
		Assert.assertEquals(NUM_TOTAL_VL_PRINCIPAL_201812, testInfo.getTotalValorPrincipal());
	}

	public void testCoparticipacao201812AfterUserReturn(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();
		TestInfo testInfo;

		testCoparticipacao201812(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201812);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, ISENTO_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_INATIVO, INATIVO_201812);

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201812);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201812);

		execucaoUi = coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		testInfo = createTestInfo(coParticipacaoTest, empresaUi, execucaoUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		LOGGER.info("Total registros ............... [{}]:", testInfo.getTotalRegistros());
		LOGGER.info("Total valor ................... [{}]:", testInfo.getTotalValorPrincipal());

		Assert.assertEquals(NUM_TOTAL_TITULARES_201812_AFTER_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_201812_AFTER_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_201812_AFTER_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_201812_AFTER_USER_RETURN, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201812_AFTER_USER_RETURN, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201812_AFTER_USER_RETURN, dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_REGISTROS_201812_AFTER_USER_RETURN, testInfo.getTotalRegistros());
		Assert.assertEquals(NUM_TOTAL_VL_PRINCIPAL_201812_AFTER_USER_RETURN, testInfo.getTotalValorPrincipal());
	}

	private TestInfo createTestInfo(CoParticipacaoTest coParticipacaoTest, EmpresaUi empresaUi, ExecucaoUi execucaoUi)
			throws Exception {
		TestInfo testInfo = new TestInfo();
		int totalRegistros = NumberUtils.INTEGER_ZERO;
		BigDecimal totalValorPrincipal = BigDecimal.ZERO;
		List<CargillData> cargillDatas;
		Map<String, List<CargillData>> map;

		map = spreadsheetReader.loadData(
				coParticipacaoTest.getCoparticipacaoReport(execucaoUi),
				new CargillSpreadsheetReaderListerner());

		cargillDatas = map.get(empresaUi.getCdEmpresa());

		for (CargillData cargillData : cargillDatas) {
			totalValorPrincipal = totalValorPrincipal.add(cargillData.getValorPrincipal());

			totalRegistros++;
		}

		testInfo.setTotalRegistros(totalRegistros);
		testInfo.setTotalValorPrincipal(totalValorPrincipal);

		return testInfo;
	}

}
