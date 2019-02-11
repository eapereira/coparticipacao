package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.SpreadsheetContext;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoContext {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoContext.class);

	private UserUi user;

	private EmpresaUi empresaUi;

	private FileInputStream fileInputStream;

	private String fileName;

	private Map<String, Object> mapLine;

	private ArquivoInputUi arquivoInputUi;

	private String line;

	private List<TitularUi> titularUis;

	private List<DependenteUi> dependenteUis;

	private Bunker bunker;

	private Integer currentLine;

	private Integer currentSheet;

	private FormulaEvaluator formulaEvaluator;

	private TitularUi titularUi;

	private int dia;
	private int mes;
	private int ano;

	private ArquivoExecucaoUi arquivoExecucaoUi;

	private boolean firstLineProcecessed;

	private ExecucaoUi execucaoUi;

	private LancamentoDetailUi lancamentoDetailUi;

	private List<DesconhecidoUi> desconhecidoUis;

	private List<LancamentoInputSheetUi> lancamentoInputSheetUis;

	private Map<Integer, List<BeneficiarioColsUi>> mapBeneficiarioCols;

	private Map<Integer, ArquivoInputSheetUi> mapArquivoInputSheetUi;

	private SpreadsheetContext spreadsheetContext;

	private List<ArquivoOutputUi> arquivoOutputUis;

	private ContratoUi contratoUi;

	private BeneficiarioUi beneficiarioUi;

	public CoParticipacaoContext() {
		titularUis = new ArrayList<TitularUi>();
		dependenteUis = new ArrayList<DependenteUi>();

		desconhecidoUis = new ArrayList<DesconhecidoUi>();
		lancamentoInputSheetUis = new ArrayList<LancamentoInputSheetUi>();

		mapLine = new HashMap<String, Object>();
		bunker = new Bunker();

		mapBeneficiarioCols = new HashMap<Integer, List<BeneficiarioColsUi>>();
		mapArquivoInputSheetUi = new HashMap<Integer, ArquivoInputSheetUi>();

		arquivoOutputUis = new ArrayList<ArquivoOutputUi>();

		currentLine = NumberUtils.INTEGER_ONE;

		firstLineProcecessed = false;
	}

	public UserUi getUser() {
		return user;
	}

	public void setUser(UserUi user) {
		this.user = user;
	}

	public List<RegraUi> getRegraUis() {
		List<RegraUi> regraUis = new ArrayList<RegraUi>();
		ArquivoInputSheetUi arquivoInputSheetUi = getArquivoInputSheet();

		for (Regra regra : arquivoInputSheetUi.getRegras()) {
			regraUis.add((RegraUi) regra);
		}

		return regraUis;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Map<String, Object> getMapLine() {
		return mapLine;
	}

	public void setMapLine(Map<String, Object> mapLine) {
		this.mapLine = mapLine;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public ArquivoInputUi getArquivoInputUi() {
		return arquivoInputUi;
	}

	public void setArquivoInputUi(ArquivoInputUi arquivoInputUi) {
		this.arquivoInputUi = arquivoInputUi;
	}

	public List<TitularUi> getTitularUis() {
		return titularUis;
	}

	public void setTitularUis(List<TitularUi> titularUis) {
		this.titularUis = titularUis;
	}

	public List<DependenteUi> getDependenteUis() {
		return dependenteUis;
	}

	public void setDependenteUis(List<DependenteUi> dependenteUis) {
		this.dependenteUis = dependenteUis;
	}

	public Bunker getBunker() {
		return bunker;
	}

	public void setBunker(Bunker bunker) {
		this.bunker = bunker;
	}

	public Integer getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(Integer currentLine) {
		this.currentLine = currentLine;
	}

	public void addTitular(TitularUi titularUi) throws CoParticipacaoException {
		getBunker().getTitularUis().add(titularUi);

		getTitularUis().add(titularUi);
	}

	public void addDependente(DependenteUi dependenteUi) throws CoParticipacaoException {
		getBunker().getDependenteUis().add(dependenteUi);

		getDependenteUis().add(dependenteUi);
	}

	public void addDesconhecido(DesconhecidoUi desconhecidoUi) {
		getBunker().getDesconhecidoUis().add(desconhecidoUi);
	}

	public void addLancamento(LancamentoUi lancamentoUi) {
		getBunker().getLancamentoUis().add(lancamentoUi);
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Object getColumnValue(RegisterColumnUi RegisterColumnUi) {
		return getMapLine().get(RegisterColumnUi.getNameColumn());
	}

	public void setColumnValue(RegisterColumn RegisterColumn, Object value) {
		getMapLine().put(RegisterColumn.getNameColumn(), value);
	}

	public List<RegraConditionalUi> getRegraConditionalUis() {
		List<RegraConditionalUi> regraConditionalUis = new ArrayList<RegraConditionalUi>();

		ArquivoInputSheetUi arquivoInputSheetUi = getArquivoInputSheet();

		for (RegraConditional regraConditional : arquivoInputSheetUi.getRegraConditionals()) {
			regraConditionalUis.add((RegraConditionalUi) regraConditional);
		}

		return regraConditionalUis;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void addTitularIsento(TitularIsentoUi titularIsentoUi) {
		getBunker().getTitularIsentoUis().add(titularIsentoUi);
	}

	public void addDependenteIsento(DependenteIsentoUi dependenteIsentoUi) {
		getBunker().getDependenteIsentoUis().add(dependenteIsentoUi);
	}

	public Integer getCurrentSheet() {
		return currentSheet;
	}

	public void setCurrentSheet(Integer currentSheet) {
		this.currentSheet = currentSheet;
	}

	public FormulaEvaluator getFormulaEvaluator() {
		return formulaEvaluator;
	}

	public void setFormulaEvaluator(FormulaEvaluator formulaEvaluator) {
		this.formulaEvaluator = formulaEvaluator;
	}

	public TitularUi getTitularUi() {
		return titularUi;
	}

	public void setTitularUi(TitularUi titularUi) {
		this.titularUi = titularUi;
	}

	public TitularUi findTitularByCpfAndName(Long cpf, String name) {
		LOGGER.info("BEGIN");

		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getCpf().equals(cpf)) {
				if (titularUi.getNameTitular().equals(name)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());

					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatriculaAndName(Long matricula, String name) {
		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getMatricula().equals(matricula)) {
				if (titularUi.getNameTitular().equals(name)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());

					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatriculaEmpresaAndName(Long matriculaEmpresa, String name) {
		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getMatriculaEmpresa() != null && titularUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
				if (titularUi.getNameTitular().equals(name)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());

					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByCpfAndName(Long cpf, String nameDependente) {
		for (DependenteUi dependenteUi : getDependenteUis()) {
			if (dependenteUi.getCpf() != null) {
				LOGGER.trace(
						"Comparing with Dependente [{}] with Matricula [{}]:",
						dependenteUi.getNameDependente(),
						dependenteUi.getMatricula());

				if (dependenteUi.getCpf().equals(cpf)) {
					if (dependenteUi.getNameDependente().equals(nameDependente)) {
						LOGGER.info(
								"Dependente [{}] with CPF [{}] found:",
								dependenteUi.getNameDependente(),
								dependenteUi.getCpf());

						LOGGER.info("END");
						return dependenteUi;
					}
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByMatriculaAndName(Long matricula, String nameDependente) {
		for (DependenteUi dependenteUi : getDependenteUis()) {
			LOGGER.trace(
					"Comparing with Dependente [{}] with Matricula [{}]:",
					dependenteUi.getNameDependente(),
					dependenteUi.getMatricula());

			if (dependenteUi.getMatricula().equals(matricula)) {
				if (dependenteUi.getNameDependente().equals(nameDependente)) {
					LOGGER.info(
							"Dependente [{}] with CPF [{}] found:",
							dependenteUi.getNameDependente(),
							dependenteUi.getCpf());

					LOGGER.info("END");
					return dependenteUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByMatriculaEmpresaAndName(Long matricula, String nameDependente) {
		for (DependenteUi dependenteUi : getDependenteUis()) {
			LOGGER.trace(
					"Comparing with Dependente [{}] with Matricula [{}]:",
					dependenteUi.getNameDependente(),
					dependenteUi.getMatricula());

			if (dependenteUi.getMatriculaEmpresa() != null && dependenteUi.getMatriculaEmpresa().equals(matricula)) {
				if (dependenteUi.getNameDependente().equals(nameDependente)) {
					LOGGER.info(
							"Dependente [{}] with CPF [{}] found:",
							dependenteUi.getNameDependente(),
							dependenteUi.getCpf());

					LOGGER.info("END");
					return dependenteUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatricula(Long matricula) {
		LOGGER.info("BEGIN");

		if (matricula != null) {
			for (TitularUi titularUi : getTitularUis()) {

				LOGGER.trace(
						"Comparing with Titular [{}] with CPF [{}]:",
						titularUi.getNameTitular(),
						titularUi.getCpf());

				if (titularUi.getMatricula().equals(matricula)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());
					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public ArquivoExecucaoUi getArquivoExecucaoUi() {
		return arquivoExecucaoUi;
	}

	public void setArquivoExecucaoUi(ArquivoExecucaoUi arquivoExecucaoUi) {
		this.arquivoExecucaoUi = arquivoExecucaoUi;
	}

	public boolean isFirstLineProcecessed() {
		return firstLineProcecessed;
	}

	public void setFirstLineProcecessed(boolean firstLineProcecessed) {
		this.firstLineProcecessed = firstLineProcecessed;
	}

	public ExecucaoUi getExecucaoUi() {
		return execucaoUi;
	}

	public void setExecucaoUi(ExecucaoUi execucaoUi) {
		this.execucaoUi = execucaoUi;
	}

	public LancamentoDetailUi getLancamentoDetailUi() {
		return lancamentoDetailUi;
	}

	public void setLancamentoDetailUi(LancamentoDetailUi lancamentoDetailUi) {
		this.lancamentoDetailUi = lancamentoDetailUi;
	}

	public List<DesconhecidoUi> getDesconhecidoUis() {
		return desconhecidoUis;
	}

	public void setDesconhecidoUis(List<DesconhecidoUi> desconhecidoUis) {
		this.desconhecidoUis = desconhecidoUis;
	}

	public DesconhecidoUi findDesconhecidoByBeneficiario(BeneficiarioUi beneficiarioUi) {
		LOGGER.info("BEGIN");

		for (DesconhecidoUi desconhecidoUi : getDesconhecidoUis()) {
			if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario())) {
				if (beneficiarioUi.getNameBeneficiario().equals(desconhecidoUi.getNameBeneficiario())) {
					if (UseType.FATUCOPA.equals(desconhecidoUi.getContrato().getUseType())) {
						return desconhecidoUi;
					}
				}
			} else if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
				if (beneficiarioUi.getNameTitular().equals(desconhecidoUi.getNameTitular())) {
					if (UseType.FATUCOPA.equals(desconhecidoUi.getContrato().getUseType())) {
						return desconhecidoUi;
					}
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatriculaAndCdContrato(Long matricula, String cdContrato) {
		LOGGER.info("BEGIN");

		if (matricula != null) {
			for (TitularUi titularUi : getTitularUis()) {

				LOGGER.trace(
						"Comparing with Titular [{}] with CPF [{}]:",
						titularUi.getNameTitular(),
						titularUi.getCpf());

				if (titularUi.getMatricula().equals(matricula)
						&& titularUi.getContrato().getCdContrato().equals(cdContrato)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());
					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public List<LancamentoInputSheetUi> getLancamentoInputSheetUis() {
		return lancamentoInputSheetUis;
	}

	public void setLancamentoInputSheetUis(List<LancamentoInputSheetUi> lancamentoInputSheetUis) {
		this.lancamentoInputSheetUis = lancamentoInputSheetUis;
	}

	public Map<Integer, List<BeneficiarioColsUi>> getMapBeneficiarioCols() {
		return mapBeneficiarioCols;
	}

	public void setMapBeneficiarioCols(Map<Integer, List<BeneficiarioColsUi>> mapBeneficiarioCols) {
		this.mapBeneficiarioCols = mapBeneficiarioCols;
	}

	public Map<Integer, ArquivoInputSheetUi> getMapArquivoInputSheetUi() {
		return mapArquivoInputSheetUi;
	}

	public void setMapArquivoInputSheetUi(Map<Integer, ArquivoInputSheetUi> mapArquivoInputSheetUi) {
		this.mapArquivoInputSheetUi = mapArquivoInputSheetUi;
	}

	public SpreadsheetContext getSpreadsheetContext() {
		return spreadsheetContext;
	}

	public void setSpreadsheetContext(SpreadsheetContext spreadsheetContext) {
		this.spreadsheetContext = spreadsheetContext;
	}

	public DependenteUi findDependenteByCpf(Long cpf) {
		DependenteUi dependenteUiTmp = null;
		LOGGER.info("BEGIN");

		if (cpf != null) {
			for (DependenteUi dependenteUi : getDependenteUis()) {
				if (dependenteUi.getCpf() != null) {
					LOGGER.trace(
							"Comparing with Dependente [{}] with Matricula [{}]:",
							dependenteUi.getNameDependente(),
							dependenteUi.getMatricula());

					if (dependenteUi.getCpf().equals(cpf)) {
						LOGGER.info(
								"Dependente [{}] with CPF [{}] found:",
								dependenteUi.getNameDependente(),
								dependenteUi.getCpf());

						dependenteUiTmp = dependenteUi;
						break;
					}
				}
			}
		}

		LOGGER.info("END");
		return dependenteUiTmp;
	}

	public DependenteUi findDependenteByMatricula(Long matricula, Long matriculaEmpresa) {
		DependenteUi dependenteUiTmp = null;

		LOGGER.info("BEGIN");

		if (matricula != null) {
			for (DependenteUi dependenteUi : getDependenteUis()) {
				LOGGER.trace(
						"Comparing with Dependente [{}] with Matricula [{}]:",
						dependenteUi.getNameDependente(),
						dependenteUi.getMatricula());

				if (dependenteUi.getMatricula().equals(matricula)) {
					if (dependenteUi.getMatriculaEmpresa() != null
							&& dependenteUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
						LOGGER.info(
								"Dependente [{}] with CPF [{}] found:",
								dependenteUi.getNameDependente(),
								dependenteUi.getCpf());

						dependenteUiTmp = dependenteUi;
						break;
					}
				}
			}
		}

		LOGGER.info("END");
		return dependenteUiTmp;
	}

	public ContratoUi findContratoById(Long contratoId) {
		LOGGER.info("BEGIN");

		for (Contrato contrato : getEmpresaUi().getContratos()) {
			if (contrato.getId().equals(contratoId)) {
				return (ContratoUi) contrato;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public List<ArquivoOutputUi> getArquivoOutputUis() {
		return arquivoOutputUis;
	}

	public void setArquivoOutputUis(List<ArquivoOutputUi> arquivoOutputUis) {
		this.arquivoOutputUis = arquivoOutputUis;
	}

	public ContratoUi getContratoUi() {
		return contratoUi;
	}

	public void setContratoUi(ContratoUi contratoUi) {
		this.contratoUi = contratoUi;
	}

	public List<BeneficiarioColsUi> listBeneficiarioColsBySheetId(Integer sheetId) {
		List<BeneficiarioColsUi> beneficiarioColsUis = null;
		LOGGER.info("BEGIN");

		if (getMapBeneficiarioCols().containsKey(sheetId)) {
			beneficiarioColsUis = getMapBeneficiarioCols().get(sheetId);
		} else {
			LOGGER.debug("Using mapped columns for BeneficiarioCols as Sheet[0]:");
			beneficiarioColsUis = getMapBeneficiarioCols().get(NumberUtils.INTEGER_ZERO);
		}

		LOGGER.info("END");
		return beneficiarioColsUis;
	}

	public BeneficiarioUi getBeneficiarioUi() {
		return beneficiarioUi;
	}

	public void setBeneficiarioUi(BeneficiarioUi beneficiarioUi) {
		this.beneficiarioUi = beneficiarioUi;
	}

	public ArquivoInputSheetUi findArquivoInputSheetById(Integer sheetId) {
		ArquivoInputSheetUi arquivoInputSheetUi = null;
		LOGGER.info("BEGIN");

		if (getMapArquivoInputSheetUi().containsKey(sheetId)) {
			arquivoInputSheetUi = getMapArquivoInputSheetUi().get(sheetId);
		} else {
			if (getContratoUi().isSpreadsheetAllPages()) {
				arquivoInputSheetUi = getMapArquivoInputSheetUi().get(NumberUtils.INTEGER_ZERO);
			}
		}

		LOGGER.info("END");
		return arquivoInputSheetUi;
	}

	public List<LancamentoInputSheetCols> listLancamentoInputSheetColsBySheetId(Integer sheetId) {
		List<LancamentoInputSheetCols> lancamentoInputSheetCols = null;
		ArquivoInputSheetUi arquivoInputSheetUi;
		LOGGER.info("BEGIN");

		arquivoInputSheetUi = findArquivoInputSheetById(sheetId);
		lancamentoInputSheetCols = arquivoInputSheetUi.getLancamentoInputSheet().getLancamentoInputSheetCols();

		LOGGER.info("END");
		return lancamentoInputSheetCols;
	}

	public List<IsentoInputSheetCols> listIsentoInputSheetColsBySheetId(Integer sheetId) {
		List<IsentoInputSheetCols> isentoInputSheetCols = null;
		ArquivoInputSheetUi arquivoInputSheetUi;
		LOGGER.info("BEGIN");

		arquivoInputSheetUi = findArquivoInputSheetById(sheetId);
		isentoInputSheetCols = arquivoInputSheetUi.getIsentoInputSheet().getIsentoInputSheetCols();

		LOGGER.info("END");
		return isentoInputSheetCols;
	}

	public ArquivoInputSheetUi getArquivoInputSheet() {
		ArquivoInputSheetUi arquivoInputSheetUi;

		LOGGER.info("BEGIN");

		arquivoInputSheetUi = findArquivoInputSheetById(getCurrentSheet());

		LOGGER.info("END");
		return arquivoInputSheetUi;
	}

	private TitularIsentoUi findTitularIsentoByNameAndCpf(TitularUi titularUi) {
		LOGGER.info("BEGIN");

		for (TitularIsentoUi titularIsentoUi : getBunker().getTitularIsentoUis()) {
			if (titularIsentoUi.getTitular().getCpf().equals(titularUi.getCpf())
					&& titularIsentoUi.getTitular().getNameTitular().equals(titularUi.getNameTitular())) {
				return titularIsentoUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	private TitularIsentoUi findTitularIsentoByNameAndMatricula(TitularUi titularUi) {
		LOGGER.info("BEGIN");

		for (TitularIsentoUi titularIsentoUi : getBunker().getTitularIsentoUis()) {
			if (titularIsentoUi.getTitular().getMatricula().equals(titularUi.getMatricula())
					&& titularIsentoUi.getTitular().getNameTitular().equals(titularUi.getNameTitular())) {
				return titularIsentoUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	private DependenteIsentoUi findDependenteIsentoByNameAndCpf(DependenteUi dependenteUi) {
		LOGGER.info("BEGIN");

		for (DependenteIsentoUi dependenteIsentoUi : getBunker().getDependenteIsentoUis()) {
			if (dependenteIsentoUi.getDependente().getCpf() != null) {
				if (dependenteIsentoUi.getDependente().getCpf().equals(dependenteUi.getCpf()) && dependenteIsentoUi
						.getDependente().getNameDependente().equals(dependenteUi.getNameDependente())) {
					return dependenteIsentoUi;
				}
			} else {
				if (dependenteIsentoUi.getDependente().getNameDependente().equals(dependenteUi.getNameDependente())) {
					return dependenteIsentoUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public List<RegisterColumnUi> getRegisterColumns() {
		List<RegisterColumnUi> registerColumnUis = new ArrayList<RegisterColumnUi>();
		ArquivoInputSheetUi arquivoInputSheetUi = getArquivoInputSheet();

		LOGGER.info("BEGIN");

		for (Register register : arquivoInputSheetUi.getRegisters()) {
			if (getSpreadsheetContext() != null && getSpreadsheetContext().getCdRegister() != null) {
				if (register.getCdRegister().equals(getSpreadsheetContext().getCdRegister())) {
					for (RegisterColumn registerColumn : register.getRegisterColumns()) {
						registerColumnUis.add((RegisterColumnUi) registerColumn);
					}

					break;
				}
			} else {
				for (RegisterColumn registerColumn : register.getRegisterColumns()) {
					registerColumnUis.add((RegisterColumnUi) registerColumn);
				}
			}
		}

		LOGGER.info("END");
		return registerColumnUis;
	}

	private DependenteIsentoUi findDependenteIsentoByNameAndMatricula(DependenteUi dependenteUi) {
		LOGGER.info("BEGIN");

		for (DependenteIsentoUi dependenteIsentoUi : getBunker().getDependenteIsentoUis()) {
			if (dependenteIsentoUi.getDependente().getMatricula().equals(dependenteUi.getMatricula())
					&& dependenteIsentoUi.getDependente().getNameDependente()
							.equals(dependenteUi.getNameDependente())) {
				return dependenteIsentoUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteIsentoUi findDependenteIsento(DependenteUi dependenteUi) {
		DependenteIsentoUi dependenteIsentoUi;

		if (dependenteUi.getCpf() != null) {
			dependenteIsentoUi = findDependenteIsentoByNameAndCpf(dependenteUi);
		} else {
			dependenteIsentoUi = findDependenteIsentoByNameAndMatricula(dependenteUi);
		}

		return dependenteIsentoUi;
	}

	public TitularIsentoUi findTitularIsento(TitularUi titularUi) {
		TitularIsentoUi titularIsentoUi;

		if (titularUi.getCpf() != null) {
			titularIsentoUi = findTitularIsentoByNameAndCpf(titularUi);
		} else {
			titularIsentoUi = findTitularIsentoByNameAndMatricula(titularUi);
		}

		return titularIsentoUi;

	}

	public TitularUi findTitularByCpf(Long cpf) {
		LOGGER.info("BEGIN");

		if (cpf != null) {
			for (TitularUi titularUi : getTitularUis()) {

				LOGGER.trace(
						"Comparing with Titular [{}] with CPF [{}]:",
						titularUi.getNameTitular(),
						titularUi.getCpf());

				if (titularUi.getCpf().equals(cpf)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());
					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatriculaAndMatriculaEmpresa(Long matricula, Long matriculaEmpresa) {
		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getMatricula().equals(matricula)) {
				if (titularUi.getMatriculaEmpresa() != null
						&& titularUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
					LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());

					LOGGER.info("END");
					return titularUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByMatriculaAndMatriculaEmpresa(Long matricula, Long matriculaEmpresa) {
		for (DependenteUi dependenteUi : getDependenteUis()) {

			LOGGER.trace(
					"Comparing with Dependente [{}] with CPF [{}]:",
					dependenteUi.getNameDependente(),
					dependenteUi.getCpf());

			if (dependenteUi.getMatricula().equals(matricula)) {
				if (dependenteUi.getMatriculaEmpresa() != null
						&& dependenteUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
					LOGGER.info(
							"Dependente [{}] with CPF [{}] found:",
							dependenteUi.getNameDependente(),
							dependenteUi.getCpf());

					LOGGER.info("END");
					return dependenteUi;
				}
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByMatriculaEmpresa(Long matriculaEmpresa) {
		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getMatriculaEmpresa() != null && titularUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
				LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());

				LOGGER.info("END");
				return titularUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByMatriculaEmpresa(Long matriculaEmpresa) {
		for (DependenteUi dependenteUi : getDependenteUis()) {

			LOGGER.trace(
					"Comparing with Dependente [{}] with CPF [{}]:",
					dependenteUi.getNameDependente(),
					dependenteUi.getCpf());

			if (dependenteUi.getMatriculaEmpresa() != null
					&& dependenteUi.getMatriculaEmpresa().equals(matriculaEmpresa)) {
				LOGGER.info(
						"Dependente [{}] with CPF [{}] found:",
						dependenteUi.getNameDependente(),
						dependenteUi.getCpf());

				LOGGER.info("END");
				return dependenteUi;
			}
		}

		LOGGER.info("END");
		return null;
	}
}
