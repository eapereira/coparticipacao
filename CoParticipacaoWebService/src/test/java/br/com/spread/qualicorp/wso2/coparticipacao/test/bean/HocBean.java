package br.com.spread.qualicorp.wso2.coparticipacao.test.bean;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
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
public class HocBean {

	private static final Logger LOGGER = LogManager.getLogger(HocBean.class);

	private static final String MECSAS_201803 = "haoc/input/0444.MECSAS.201803.001.csv";
	private static final String GESTANTES_201803 = "haoc/input/0444.ISENTO.201803.002.xlsx";
	private static final String ISENTO_VALOR_CENTAVOS_201804 = "haoc/input/0444.ISENTO-CENTAVOS.201804.003.xlsx";
	private static final String ISENTO_VALOR_201804 = "haoc/input/0444.ISENTO-UTILIZACAO.201804.004.xlsx";
	private static final String FATUCOPA_201803 = "haoc/input/0444.0444.201803.005.csv";
	private static final String NAO_LOCALIZADOS_201803 = "haoc/input/0444.NAO-LOCALIZADO.201803.005.xlsx";

	private static final String MECSAS_201807 = "haoc/input/0444.MECSAS.201807.001.csv";
	private static final String GESTANTES_201807 = "haoc/input/0444.ISENTO.201807.002.xlsx";
	private static final String ISENTO_VALOR_CENTAVOS_201807 = "haoc/input/0444.ISENTO-CENTAVOS.201807.003.xlsx";
	private static final String ISENTO_VALOR_UTILIZACAO_201807 = "haoc/input/0444.ISENTO-UTILIZACAO.201807.004.xlsx";
	private static final String FATUCOPA_201807 = "haoc/input/0444.0444.201807.005.csv";
	private static final String NAO_LOCALIZADOS_201807 = "haoc/input/0444.NAO-LOCALIZADO.201807.005.xlsx";

	private static final String MECSAS_201810 = "haoc/input/0444.MECSAS.201810.001.csv";
	private static final String GESTANTES_201810 = "haoc/input/0444.ISENTO.201810.002.xlsx";
	private static final String ISENTO_VALOR_CENTAVOS_201810 = "haoc/input/0444.ISENTO-CENTAVOS.201810.003.xlsx";
	private static final String ISENTO_VALOR_UTILIZACAO_201810 = "haoc/input/0444.ISENTO-UTILIZACAO.201810.004.xlsx";
	private static final String FATUCOPA_201810 = "haoc/input/0444.0444.201810.005.csv";
	private static final String NAO_LOCALIZADOS_201810 = "haoc/input/0444.NAO-LOCALIZADO.201810.005.xlsx";

	private static final String MECSAS_201901 = "haoc/input/201901/0444.MECSAS.201901.001.csv";
	private static final String GESTANTES_201901 = "haoc/input/201901/0444.ISENTO.201901.002.xlsx";
	private static final String ISENTO_VALOR_CENTAVOS_201901 = "haoc/input/201901/0444.ISENTO-CENTAVOS.201901.003.xlsx";
	private static final String ISENTO_VALOR_UTILIZACAO_201901 = "haoc/input/201901/0444.ISENTO-UTILIZACAO.201901.004.xlsx";
	private static final String FATUCOPA_201901 = "haoc/input/201901/0444.0444.201901.005.csv";
	private static final String NAO_LOCALIZADOS_201901 = "haoc/input/201901/0444.NAO-LOCALIZADO.201901.005.xlsx";
	
	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803 = 4948;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803 = 3909;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803 = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803 = 2377;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201803 = 310;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201803 = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION = 4948;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION = 3910;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION = 2378;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201803_AFTER_USER_VALIDATION = 309;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201803_AFTER_USER_VALIDATION = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 4875;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 3864;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 2117;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807 = 321;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807 = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807_AFTER_USER_VALIDATION = 4875;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_AFTER_USER_VALIDATION = 3864;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_AFTER_USER_VALIDATION = 2118;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_VALIDATION = 321;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_VALIDATION = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201810 = 4749;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201810 = 3789;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810 = 9;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810 = 2506;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810 = 520;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810 = 5;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201810_AFTER_USER_VALIDATION = 4749;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201810_AFTER_USER_VALIDATION = 3791;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810_AFTER_USER_VALIDATION = 2515;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_VALIDATION = 524;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_VALIDATION = 6;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201901 = 4742;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201901 = 3759;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201901 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201901 = 2515;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201901 = 545;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201901 = 12;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201901_AFTER_USER_VALIDATION = 4749;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201901_AFTER_USER_VALIDATION = 3791;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201901_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201901_AFTER_USER_VALIDATION = 2515;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201901_AFTER_USER_VALIDATION = 524;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201901_AFTER_USER_VALIDATION = 6;
	
	private static final String CD_CONTRATO_NAO_LOCALIZADO = "NAO-LOCALIZADO";
	private static final String CD_CONTRATO_ISENTO = "ISENTO";
	private static final String CD_CONTRATO_ISENTO_VALOR = "ISENTO-UTILIZACAO";
	private static final String CD_CONTRATO_ISENTO_CENTAVO = "ISENTO-CENTAVOS";
	private static final String CD_CONTRATO_FATUCOPA = "0444";
	private static final String CD_CONTRATO_MECSAS = "MECSAS";

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

	@Test
	public void testCoparticipacao201803(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201803);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201803);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_201804);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201804);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201803);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201803, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201803, dependenteIsentoUis.size());

		// PartitionMap ... Test completed with [16.6140] min:
	}

	@Test
	public void testCoparticipacao201803AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201803(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADOS_201803);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201803);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_201804);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201804);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201803);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		LOGGER.info("Results after user's validation:");
		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201803_AFTER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201803_AFTER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201803_AFTER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201803_AFTER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201803_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201803_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
		// PartitionMap ... Test completed with [28.7855] min:
	}

	@Test
	public void testCoparticipacao201807(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201807);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201807);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201807);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807, dependenteIsentoUis.size());

		// PartitionMap ... Test completed with [16.6140] min:
	}

	@Test
	public void testCoparticipacao201807AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201807(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADOS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201807);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201807);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201807);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201807);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_AFTER_USER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201807_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201807_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
	}

	@Test
	public void testCoparticipacao201810(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201810);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201810);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201810);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201810, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201810, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201810, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201810, dependenteIsentoUis.size());
	}

	@Test
	public void testCoparticipacao201810AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201810(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADOS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201810);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201810);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201810);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201810);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201810_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201810_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201810_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201810_AFTER_USER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
	}
	
	@Test
	public void testCoparticipacao201901(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201901);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201901);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201901);

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

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201901, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201901, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201901, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201901, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201901, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201901, dependenteIsentoUis.size());
	}

	@Test
	public void testCoparticipacao201901AfterUserValidation(CoParticipacaoTest coParticipacaoTest) throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("0444");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201901(coParticipacaoTest);

		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADOS_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO, GESTANTES_201901);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_VALOR, ISENTO_VALOR_UTILIZACAO_201901);
		coParticipacaoTest
				.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_ISENTO_CENTAVO, ISENTO_VALOR_CENTAVOS_201901);
		coParticipacaoTest.createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_FATUCOPA, FATUCOPA_201901);

		coParticipacaoTest.processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);
		titularIsentoUis = titularIsentoService.listByEmpresaId(empresaUi);
		dependenteIsentoUis = dependenteIsentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Results after user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());
		LOGGER.info("Total titulares isentos ....... [{}]:", titularIsentoUis.size());
		LOGGER.info("Total dependentes isentos ..... [{}]:", dependenteIsentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201901_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201901_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201901_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201901_AFTER_USER_VALIDATION, lancamentoUis.size());
		Assert.assertEquals(NUM_TOTAL_TITULARES_ISENTOS_201901_AFTER_USER_VALIDATION, titularIsentoUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_ISENTOS_201901_AFTER_USER_VALIDATION, dependenteIsentoUis.size());
	}
	
}
