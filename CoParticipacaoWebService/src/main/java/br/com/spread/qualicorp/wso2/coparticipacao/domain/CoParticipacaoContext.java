package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ParameterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoContext {
	private static final Logger LOGGER = LogManager
			.getLogger(CoParticipacaoContext.class);

	private UserUi user;

	private EmpresaUi empresaUi;

	private FileInputStream fileInputStream;

	private String fileName;

	private List<ArquivoInputColsDefUi> arquivoInputColsDefUis;

	private List<InputTitularUi> inputTitularUis;

	private List<InputLancamentoUi> inputLancamentoUis;

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

	private int dia;
	private int mes;
	private int ano;

	public CoParticipacaoContext() {
		arquivoInputColsDefUis = new ArrayList<ArquivoInputColsDefUi>();
		inputTitularUis = new ArrayList<InputTitularUi>();
		inputLancamentoUis = new ArrayList<InputLancamentoUi>();
		titularUis = new ArrayList<TitularUi>();
		dependenteUis = new ArrayList<DependenteUi>();

		regraConditionalUis = new ArrayList<RegraConditionalUi>();
		regraUis = new ArrayList<RegraUi>();
		parameterUis = new ArrayList<ParameterUi>();

		inputTitularIsentoColsUis = new ArrayList<InputTitularIsentoColsUi>();
		inputDependenteIsentoColsUis = new ArrayList<InputDependenteIsentoColsUi>();

		beneficiarioColsUis = new ArrayList<BeneficiarioColsUi>();

		mapLine = new HashMap<String, Object>();

		bunker = new Bunker();

		currentLine = NumberUtils.INTEGER_ONE;
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

	public void setArquivoInputColsDefUis(
			List<ArquivoInputColsDefUi> arquivoInputColsDefUis) {
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

	public List<InputTitularUi> getInputTitularUis() {
		return inputTitularUis;
	}

	public void setInputTitularUis(List<InputTitularUi> inputTitularUis) {
		this.inputTitularUis = inputTitularUis;
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

	public TitularUi findTitularByCpf(String cpf) {
		LOGGER.info("BEGIN");

		for (TitularUi titularUi : getTitularUis()) {
			if (titularUi.getCpf().equals(cpf)) {
				LOGGER.info(
						"Titular [{}] with CPF [{}] found:",
						titularUi.getNameTitular(),
						titularUi.getCpf());
				LOGGER.info("END");
				return titularUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByCpf(String cpf) {
		LOGGER.info("BEGIN");

		for (DependenteUi dependenteUi : getDependenteUis()) {
			if (dependenteUi.getCpf().equals(cpf)) {
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

	public ContratoUi getContratoUi() {
		return (ContratoUi) getArquivoInputUi().getContrato();
	}

	public Bunker getBunker() {
		return bunker;
	}

	public void setBunker(Bunker bunker) {
		this.bunker = bunker;
	}

	public List<InputLancamentoUi> getInputLancamentoUis() {
		return inputLancamentoUis;
	}

	public void setInputLancamentoUis(
			List<InputLancamentoUi> inputLancamentoUis) {
		this.inputLancamentoUis = inputLancamentoUis;
	}

	public Integer getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(Integer currentLine) {
		this.currentLine = currentLine;
	}

	public void addTitular(TitularUi titularUi) {
		getBunker().getTitularUis().add(titularUi);
	}

	public void addDependente(DependenteUi dependenteUi) {
		getBunker().getDependenteUis().add(dependenteUi);
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

	public void setColumnValue(
			ArquivoInputColsDef arquivoInputColsDef,
			Object value) {

		getMapLine().put(arquivoInputColsDef.getNameColumn(), value);
	}

	public void addDesconhecido(DesconhecidoUi desconhecidoUi) {
		getBunker().getDesconhecidoUis().add(desconhecidoUi);
	}

	public void addLancamento(LancamentoUi lancamentoUi) {
		getBunker().getLancamentoUis().add(lancamentoUi);
	}

	public List<RegraConditionalUi> getRegraConditionalUis() {
		return regraConditionalUis;
	}

	public void setRegraConditionalUis(
			List<RegraConditionalUi> regraConditionalUis) {
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
			if (parameterUi.getNameParameter()
					.equals(parameterName.getName())) {
				return parameterUi;
			}
		}

		return null;
	}

	public TitularUi findTitularByName(String nameTitular) {
		LOGGER.info("BEGIN");

		for (TitularUi titularUi : getTitularUis()) {
			if (titularUi.getNameTitular().equals(nameTitular)) {
				LOGGER.info(
						"Titular [{}] with CPF [{}] found:",
						titularUi.getNameTitular(),
						titularUi.getCpf());
				LOGGER.info("END");
				return titularUi;
			}
		}

		LOGGER.info("END");
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

	public void setInputTitularIsentoColsUis(
			List<InputTitularIsentoColsUi> inputTitularIsentoColsUis) {
		this.inputTitularIsentoColsUis = inputTitularIsentoColsUis;
	}

	public List<InputDependenteIsentoColsUi> getInputDependenteIsentoColsUis() {
		return inputDependenteIsentoColsUis;
	}

	public void setInputDependenteIsentoColsUis(
			List<InputDependenteIsentoColsUi> inputDependenteIsentoColsUis) {
		this.inputDependenteIsentoColsUis = inputDependenteIsentoColsUis;
	}

	public DependenteUi findDependenteByMatricula(Long matricula) {
		LOGGER.info("BEGIN");

		for (DependenteUi dependenteUi : getDependenteUis()) {
			if (dependenteUi.getMatricula().equals(matricula)) {
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

	public TitularUi findTitularByMatricula(Long matricula) {
		LOGGER.info("BEGIN");

		for (TitularUi TitularUi : getTitularUis()) {
			if (TitularUi.getMatricula().equals(matricula)) {
				LOGGER.info(
						"Titular [{}] with CPF [{}] found:",
						TitularUi.getNameTitular(),
						TitularUi.getCpf());

				LOGGER.info("END");
				return TitularUi;
			}
		}

		LOGGER.info("END");
		return null;
	}

	public DependenteUi findDependenteByName(String nameDependente) {
		LOGGER.info("BEGIN");

		for (DependenteUi dependenteUi : getDependenteUis()) {
			if (dependenteUi.getNameDependente().equals(nameDependente)) {
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

	public List<BeneficiarioColsUi> getBeneficiarioColsUis() {
		return beneficiarioColsUis;
	}

	public void setBeneficiarioColsUis(
			List<BeneficiarioColsUi> beneficiarioColsUis) {
		this.beneficiarioColsUis = beneficiarioColsUis;
	}

}
