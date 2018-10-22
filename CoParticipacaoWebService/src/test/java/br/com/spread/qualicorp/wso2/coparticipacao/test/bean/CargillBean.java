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

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class CargillBean extends CoparticipacaoBean {

	private static final Logger LOGGER = LogManager.getLogger(CargillBean.class);

	private static final String MECSAS_201807 = "cargill/input/CARGILL.MECSAS.201807.001.csv";
	private static final String MECSAS2_201807 = "cargill/input/CARGILL.MECSAS2.201807.002.xlsx";
	private static final String FATUCOPA_00192_201807 = "cargill/input/CARGILL.00192.201807.003.CSV";
	private static final String FATUCOPA_00196_201807 = "cargill/input/CARGILL.00196.201807.004.CSV";
	private static final String FATUCOPA_00197_201807 = "cargill/input/CARGILL.00197.201807.005.CSV";
	private static final String NAO_LOCALIZADO_201807 = "cargill/input/CARGILL.NAO-LOCALIZADO.201807.003.xlsx";

	private static final int NUM_TOTAL_TITULARES_201807 = 975;
	private static final int NUM_TOTAL_DEPENDENTES_201807 = 1182;
	private static final int NUM_TOTAL_DESCONHECIDOS_201807 = 5;
	private static final int NUM_TOTAL_LANCAMENTOS_201807 = 105;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201807 = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201807 = 0;

	private static final int NUM_TOTAL_TITULARES_201807_AFTER_USER_RETURN = 978;
	private static final int NUM_TOTAL_DEPENDENTES_201807_AFTER_USER_RETURN = 1184;
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

	private static final String MECSAS_201810 = "cargill/input/CARGILL.MECSAS.201810.001.csv";
	private static final String MECSAS2_201810 = "cargill/input/CARGILL.MECSAS2.201810.002.xlsx";
	private static final String FATUCOPA_00192_201810 = "cargill/input/CARGILL.00192.201810.004.CSV";
	private static final String FATUCOPA_00196_201810 = "cargill/input/CARGILL.00196.201810.005.CSV";
	private static final String FATUCOPA_00197_201810 = "cargill/input/CARGILL.00197.201810.006.CSV";
	private static final String NAO_LOCALIZADO_201810 = "cargill/input/CARGILL.NAO-LOCALIZADO.201810.003.xlsx";

	private static final int NUM_TOTAL_TITULARES_201810 = 995;
	private static final int NUM_TOTAL_DEPENDENTES_201810 = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810 = 2;
	private static final int NUM_TOTAL_LANCAMENTOS_201810 = 131;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810 = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810 = 0;

	private static final int NUM_TOTAL_TITULARES_201810_AFTER_USER_RETURN = 997;
	private static final int NUM_TOTAL_DEPENDENTES_201810_AFTER_USER_RETURN = 1196;
	private static final int NUM_TOTAL_DESCONHECIDOS_201810_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_201810_AFTER_USER_RETURN = 133;
	private static final int NUM_TOTAL_TITULARES_ISENTOS_201810_AFTER_USER_RETURN = 0;
	private static final int NUM_TOTAL_DEPENDENTES_ISENTOS_201810_AFTER_USER_RETURN = 0;
	
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

	public void testCoparticipacao201807() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201807);

		processFile(execucaoUi);

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

	public void testCoparticipacao201807AfterUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201807();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201807);

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201807);

		processFile(execucaoUi);

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
	
	public void testCoparticipacao201810() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS2, MECSAS2_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201810);

		processFile(execucaoUi);

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

	public void testCoparticipacao201810AfterUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		List<TitularIsentoUi> titularIsentoUis;
		List<DependenteIsentoUi> dependenteIsentoUis;
		EmpresaUi empresaUi = empresaService.findByName("CARGILL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201810();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201810);

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00192, FATUCOPA_00192_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00196, FATUCOPA_00196_201810);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_00197, FATUCOPA_00197_201810);

		processFile(execucaoUi);

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
}
