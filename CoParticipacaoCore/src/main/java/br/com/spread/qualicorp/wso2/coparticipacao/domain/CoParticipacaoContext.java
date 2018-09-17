package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.search.MapKey;
import br.com.spread.qualicorp.wso2.coparticipacao.search.PartitionMap;

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

	private List<ArquivoInputColsDefUi> arquivoInputColsDefUis;

	private List<LancamentoInputColsUi> lancamentoInputColsUis;

	private List<RegraUi> regraUis;

	private List<RegraConditionalUi> regraConditionalUis;

	private Map<String, Object> mapLine;

	private ArquivoInputUi arquivoInputUi;

	private String line;

	private List<TitularUi> titularUis;

	private List<DependenteUi> dependenteUis;

	private List<ParameterUi> parameterUis;

	private List<InputTitularIsentoColsUi> inputTitularIsentoColsUis;

	private List<InputDependenteIsentoColsUi> inputDependenteIsentoColsUis;

	private List<BeneficiarioColsUi> beneficiarioColsUis;

	private Bunker bunker;

	private Integer currentLine;

	private Integer currentSheet;

	List<IsentoInputSheetUi> isentoInputSheetUis;

	private BeneficiarioUi beneficiarioUi;

	private FormulaEvaluator formulaEvaluator;

	private TitularUi titularUi;

	private int dia;
	private int mes;
	private int ano;

	private PartitionMap<TitularUi> mapTitularUiByCpf;
	private PartitionMap<DependenteUi> mapDependenteUiByCpf;

	private PartitionMap<TitularUi> mapTitularUiByMatricula;
	private PartitionMap<DependenteUi> mapDependenteUiByMatricula;

	private ArquivoExecucaoUi arquivoExecucaoUi;

	private boolean firstLineProcecessed;

	public CoParticipacaoContext() {
		arquivoInputColsDefUis = new ArrayList<ArquivoInputColsDefUi>();
		lancamentoInputColsUis = new ArrayList<LancamentoInputColsUi>();
		titularUis = new ArrayList<TitularUi>();
		dependenteUis = new ArrayList<DependenteUi>();

		regraConditionalUis = new ArrayList<RegraConditionalUi>();
		regraUis = new ArrayList<RegraUi>();
		parameterUis = new ArrayList<ParameterUi>();

		inputTitularIsentoColsUis = new ArrayList<InputTitularIsentoColsUi>();
		inputDependenteIsentoColsUis = new ArrayList<InputDependenteIsentoColsUi>();

		beneficiarioColsUis = new ArrayList<BeneficiarioColsUi>();

		isentoInputSheetUis = new ArrayList<IsentoInputSheetUi>();

		mapLine = new HashMap<String, Object>();

		bunker = new Bunker();

		currentLine = NumberUtils.INTEGER_ONE;

		firstLineProcecessed = false;
	}

	public UserUi getUser() {
		return user;
	}

	public void setUser(UserUi user) {
		this.user = user;
	}

	public List<ArquivoInputColsDefUi> getArquivoInputColsDefUis() {
		return arquivoInputColsDefUis;
	}

	public void setArquivoInputColsDefUis(List<ArquivoInputColsDefUi> arquivoInputColsDefUis) {
		this.arquivoInputColsDefUis = arquivoInputColsDefUis;
	}

	public List<RegraUi> getRegraUis() {
		return regraUis;
	}

	public void setRegraUis(List<RegraUi> regraUis) {
		this.regraUis = regraUis;
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

	public ContratoUi getContratoUi() {
		return (ContratoUi) getArquivoInputUi().getContrato();
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

	public Object getColumnValue(ArquivoInputColsDefUi arquivoInputColsDefUi) {
		return getMapLine().get(arquivoInputColsDefUi.getNameColumn());
	}

	public void setColumnValue(ArquivoInputColsDef arquivoInputColsDef, Object value) {

		getMapLine().put(arquivoInputColsDef.getNameColumn(), value);
	}

	public List<RegraConditionalUi> getRegraConditionalUis() {
		return regraConditionalUis;
	}

	public void setRegraConditionalUis(List<RegraConditionalUi> regraConditionalUis) {
		this.regraConditionalUis = regraConditionalUis;
	}

	public List<ParameterUi> getParameterUis() {
		return parameterUis;
	}

	public void setParameterUis(List<ParameterUi> parameterUis) {
		this.parameterUis = parameterUis;
	}

	public ParameterUi findParameterByName(ParameterName parameterName) {
		for (ParameterUi parameterUi : getParameterUis()) {
			if (parameterUi.getNameParameter().equals(parameterName.getName())) {
				return parameterUi;
			}
		}

		return null;
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

	public List<InputTitularIsentoColsUi> getInputTitularIsentoColsUis() {
		return inputTitularIsentoColsUis;
	}

	public void setInputTitularIsentoColsUis(List<InputTitularIsentoColsUi> inputTitularIsentoColsUis) {
		this.inputTitularIsentoColsUis = inputTitularIsentoColsUis;
	}

	public List<InputDependenteIsentoColsUi> getInputDependenteIsentoColsUis() {
		return inputDependenteIsentoColsUis;
	}

	public void setInputDependenteIsentoColsUis(List<InputDependenteIsentoColsUi> inputDependenteIsentoColsUis) {
		this.inputDependenteIsentoColsUis = inputDependenteIsentoColsUis;
	}

	public List<BeneficiarioColsUi> getBeneficiarioColsUis() {
		return beneficiarioColsUis;
	}

	public void setBeneficiarioColsUis(List<BeneficiarioColsUi> beneficiarioColsUis) {
		this.beneficiarioColsUis = beneficiarioColsUis;
	}

	public Integer getCurrentSheet() {
		return currentSheet;
	}

	public void setCurrentSheet(Integer currentSheet) {
		this.currentSheet = currentSheet;
	}

	public List<IsentoInputSheetUi> getIsentoInputSheetUis() {
		return isentoInputSheetUis;
	}

	public void setIsentoInputSheetUis(List<IsentoInputSheetUi> isentoInputSheetUis) {
		this.isentoInputSheetUis = isentoInputSheetUis;
	}

	public BeneficiarioUi getBeneficiarioUi() {
		return beneficiarioUi;
	}

	public void setBeneficiarioUi(BeneficiarioUi beneficiarioUi) {
		this.beneficiarioUi = beneficiarioUi;
	}

	public List<LancamentoInputColsUi> getLancamentoInputColsUis() {
		return lancamentoInputColsUis;
	}

	public void setLancamentoInputColsUis(List<LancamentoInputColsUi> lancamentoInputColsUis) {
		this.lancamentoInputColsUis = lancamentoInputColsUis;
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
		TitularUi titularUiTmp = null;
		LOGGER.info("BEGIN");

		if (getMapTitularUiByCpf() != null) {
			LOGGER.info("END");
			titularUiTmp = getMapTitularUiByCpf().get(new MapKey(cpf, name));
		}

		if (titularUiTmp == null) {
			for (TitularUi titularUi : getTitularUis()) {

				LOGGER.trace(
						"Comparing with Titular [{}] with CPF [{}]:",
						titularUi.getNameTitular(),
						titularUi.getCpf());

				if (titularUi.getCpf().equals(cpf)) {
					if (titularUi.getNameTitular().equals(name)) {
						LOGGER.info(
								"Titular [{}] with CPF [{}] found:",
								titularUi.getNameTitular(),
								titularUi.getCpf());
						titularUiTmp = titularUi;
						break;
					}
				}
			}
		}

		LOGGER.info("END");
		return titularUiTmp;
	}

	public TitularUi findTitularByMatriculaAndName(Long matricula, String name) {
		TitularUi titularUiTmp = null;
		LOGGER.info("BEGIN");

		if (getMapTitularUiByMatricula() != null) {
			titularUiTmp = getMapTitularUiByMatricula().get(new MapKey(matricula, name));
		}

		if (titularUiTmp == null) {
			for (TitularUi titularUi : getTitularUis()) {

				LOGGER.trace(
						"Comparing with Titular [{}] with CPF [{}]:",
						titularUi.getNameTitular(),
						titularUi.getCpf());

				if (titularUi.getMatricula().equals(matricula)) {
					if (titularUi.getNameTitular().equals(name)) {
						LOGGER.info(
								"Titular [{}] with CPF [{}] found:",
								titularUi.getNameTitular(),
								titularUi.getCpf());
						titularUiTmp = titularUi;
						break;
					}
				}
			}
		}

		LOGGER.info("END");
		return titularUiTmp;
	}

	public DependenteUi findDependenteByCpfAndName(Long cpf, String nameDependente) {
		DependenteUi dependenteUiTmp = null;
		LOGGER.info("BEGIN");

		if (getMapDependenteUiByCpf() != null) {
			LOGGER.info("END");
			dependenteUiTmp = getMapDependenteUiByCpf().get(new MapKey(cpf, nameDependente));
		}

		if (dependenteUiTmp == null) {
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

							dependenteUiTmp = dependenteUi;
							break;
						}
					}
				}
			}
		}

		LOGGER.info("END");
		return dependenteUiTmp;
	}

	public DependenteUi findDependenteByMatriculaAndName(Long matricula, String nameDependente) {
		DependenteUi dependenteUiTmp = null;

		LOGGER.info("BEGIN");

		if (getMapDependenteUiByMatricula() != null) {
			dependenteUiTmp = getMapDependenteUiByMatricula().get(new MapKey(matricula, nameDependente));
		}

		if (dependenteUiTmp == null) {
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

						dependenteUiTmp = dependenteUi;
						break;
					}
				}
			}
		}

		LOGGER.info("END");
		return dependenteUiTmp;
	}

	public TitularUi findTitularByMatricula(Long matricula) {
		LOGGER.info("BEGIN");

		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getMatricula().equals(matricula)) {
				LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());
				LOGGER.info("END");
				return titularUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public TitularUi findTitularByCpf(Long cpf) {
		LOGGER.info("BEGIN");

		for (TitularUi titularUi : getTitularUis()) {

			LOGGER.trace("Comparing with Titular [{}] with CPF [{}]:", titularUi.getNameTitular(), titularUi.getCpf());

			if (titularUi.getCpf().equals(cpf)) {
				LOGGER.info("Titular [{}] with CPF [{}] found:", titularUi.getNameTitular(), titularUi.getCpf());
				LOGGER.info("END");
				return titularUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public PartitionMap<TitularUi> getMapTitularUiByCpf() {
		return mapTitularUiByCpf;
	}

	public void setMapTitularUiByCpf(PartitionMap<TitularUi> mapTitularUiByCpf) {
		this.mapTitularUiByCpf = mapTitularUiByCpf;
	}

	public PartitionMap<DependenteUi> getMapDependenteUiByCpf() {
		return mapDependenteUiByCpf;
	}

	public void setMapDependenteUiByCpf(PartitionMap<DependenteUi> mapDependenteUiByCpf) {
		this.mapDependenteUiByCpf = mapDependenteUiByCpf;
	}

	public PartitionMap<TitularUi> getMapTitularUiByMatricula() {
		return mapTitularUiByMatricula;
	}

	public void setMapTitularUiByMatricula(PartitionMap<TitularUi> mapTitularUiByMatricula) {
		this.mapTitularUiByMatricula = mapTitularUiByMatricula;
	}

	public PartitionMap<DependenteUi> getMapDependenteUiByMatricula() {
		return mapDependenteUiByMatricula;
	}

	public void setMapDependenteUiByMatricula(PartitionMap<DependenteUi> mapDependenteUiByMatricula) {
		this.mapDependenteUiByMatricula = mapDependenteUiByMatricula;
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

}
