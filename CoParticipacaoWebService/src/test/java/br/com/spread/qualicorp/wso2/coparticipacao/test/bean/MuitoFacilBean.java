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

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class MuitoFacilBean extends CoparticipacaoBean {
	private static final Logger LOGGER = LogManager.getLogger(MuitoFacilBean.class);

	private static final String MECSAS_201802 = "muito-facil/input/MUITO-FACIL.MECSAS.201802.001.csv";

	private static final String MECSAS_201808 = "muito-facil/input/MUITO-FACIL.MECSAS.201808.001.csv";
	private static final String FATUCOPA_8CH5Y_201806 = "muito-facil/input/MUITO-FACIL.8CH5Y.201806.002.TXT";
	private static final String FATUCOPA_8CHE8_201806 = "muito-facil/input/MUITO-FACIL.8CHE8.201806.002.TXT";

	private static final String FATUCOPA_8CH5Y_201807 = "muito-facil/input/MUITO-FACIL.8CH5Y.201807.002.TXT";
	private static final String FATUCOPA_8CHE8_201807 = "muito-facil/input/MUITO-FACIL.8CHE8.201807.002.TXT";

	private static final String NAO_LOCALIZADO_201808 = "muito-facil/input/MUITO-FACIL.NAO-LOCALIZADO.201808.002.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807 = 276;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807 = 19;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807 = 93;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201807_USER_RETURN = 276;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_USER_RETURN = 19;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_USER_RETURN = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_USER_RETURN = 93;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201806 = 312;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201806 = 25;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201806 = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201806 = 98;

	private static final String MECSAS_201809 = "muito-facil/input/MUITO-FACIL.MECSAS.201809.001.csv";

	private static final String FATUCOPA_8CHE8_201809 = "muito-facil/input/MUITO-FACIL.8CHE8.201809.002.TXT";
	private static final String FATUCOPA_8CH5Y_201809 = "muito-facil/input/MUITO-FACIL.8CH5Y.201809.002.TXT";

	private static final String NAO_LOCALIZADO_201809 = "muito-facil/input/MUITO-FACIL.NAO-LOCALIZADO.201809.002.xlsx";

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201809 = 263;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201809 = 16;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809 = 1;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809 = 82;

	private static final int NUM_TOTAL_TITULARES_FATUCOPA_201809_AFTER_USER_VALIDATION = 264;
	private static final int NUM_TOTAL_DEPENDENTES_FATUCOPA_201809_AFTER_USER_VALIDATION = 17;
	private static final int NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809_AFTER_USER_VALIDATION = 0;
	private static final int NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809_AFTER_USER_VALIDATION = 83;
	
	private static final String CD_CONTRATO_MECSAS = "MECSAS";
	private static final String CD_CONTRATO_8CH5Y = "8CH5Y";
	private static final String CD_CONTRATO_8CHE8 = "8CHE8";
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

	public void testCoparticipacao201806() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByCdEmpresa("MUITO-FACIL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201802);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CH5Y, FATUCOPA_8CH5Y_201806);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CHE8, FATUCOPA_8CHE8_201806);

		processFile(execucaoUi);

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

	public void testCoparticipacao201807() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CH5Y, FATUCOPA_8CH5Y_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CHE8, FATUCOPA_8CHE8_201807);

		processFile(execucaoUi);

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
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807, lancamentoUis.size());
	}

	public void testCoparticipacao201807WithUserReturn() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201807();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201808);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CH5Y, FATUCOPA_8CH5Y_201807);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CHE8, FATUCOPA_8CHE8_201807);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201807_USER_RETURN, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201807_USER_RETURN, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201807_USER_RETURN, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201807_USER_RETURN, lancamentoUis.size());
	}

	public void testCoparticipacao201809() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_MECSAS, MECSAS_201809);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CH5Y, FATUCOPA_8CH5Y_201809);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CHE8, FATUCOPA_8CHE8_201809);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201809, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201809, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809, lancamentoUis.size());
	}

	public void testCoparticipacao201809AfterUserValidation() throws Exception {
		List<TitularUi> titularUis;
		List<DependenteUi> dependenteUis;
		List<DesconhecidoUi> desconhecidoUis;
		List<LancamentoUi> lancamentoUis;
		EmpresaUi empresaUi = empresaService.findByName("MUITO-FACIL");
		ExecucaoUi execucaoUi = new ExecucaoUi();

		testCoparticipacao201809();
		
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_NAO_LOCALIZADO, NAO_LOCALIZADO_201809);		
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CH5Y, FATUCOPA_8CH5Y_201809);
		createArquivoExecucao(execucaoUi, empresaUi, CD_CONTRATO_8CHE8, FATUCOPA_8CHE8_201809);

		processFile(execucaoUi);

		titularUis = titularService.listByEmpresaId(empresaUi);
		dependenteUis = dependenteService.listByEmpresaId(empresaUi);
		desconhecidoUis = desconhecidoService.listByEmpresaIdAndUseType(empresaUi, UseType.FATUCOPA);
		lancamentoUis = lancamentoService.listByEmpresaId(empresaUi);

		LOGGER.info("After user's validation:");
		LOGGER.info("Total titulares ............... [{}]:", titularUis.size());
		LOGGER.info("Total dependentes ............. [{}]:", dependenteUis.size());
		LOGGER.info("Total desconhecidos ........... [{}]:", desconhecidoUis.size());
		LOGGER.info("Total lançamentos ............. [{}]:", lancamentoUis.size());

		Assert.assertEquals(NUM_TOTAL_TITULARES_FATUCOPA_201809_AFTER_USER_VALIDATION, titularUis.size());
		Assert.assertEquals(NUM_TOTAL_DEPENDENTES_FATUCOPA_201809_AFTER_USER_VALIDATION, dependenteUis.size());
		Assert.assertEquals(NUM_TOTAL_DESCONHECIDOS_FATUCOPA_201809_AFTER_USER_VALIDATION, desconhecidoUis.size());
		Assert.assertEquals(NUM_TOTAL_LANCAMENTOS_FATUCOPA_201809_AFTER_USER_VALIDATION, lancamentoUis.size());
	}
	
}
