package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * The persistent class for the tb_user database table.
 * 
 */
public abstract class User extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String descrName;
	private String passwd;

	private String nameLogin;
	private UserStatusType tpStatus;

	private List<ArquivoInput> arquivoInputsUserCreated;
	private List<ArquivoInput> arquivoInputsUserAltered;
	private List<ArquivoInputColsDef> arquivoInputColsDefUserCreated;
	private List<ArquivoInputColsDef> arquivoInputColsDefUserAltered;
	private List<ArquivoOutput> arquivoOutputsUserCreated;
	private List<ArquivoOutput> arquivoOutputsUserAltered;
	private List<ArquivoOutputSheet> arquivoOutputSheetsUserCreated;
	private List<ArquivoOutputSheet> arquivoOutputSheetsUserAltered;
	private List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefUserCreated;
	private List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefUserAltered;
	private List<Contrato> contratosUserCreated;
	private List<Contrato> contratosUserAltered;
	private List<Dependente> dependentesUserCreated;
	private List<Dependente> dependentesUserAltered;
	private List<DependenteColsDef> dependenteColsDefsUserCreated;
	private List<DependenteColsDef> dependenteColsDefsUserAltered;
	private List<DependenteIsento> dependenteIsentosUserCreated;
	private List<DependenteIsento> dependenteIsentosUserAltered;
	private List<Empresa> empresasUserCreated;
	private List<Empresa> empresasUserAltered;
	private List<InputDependente> inputDependentesUserCreated;
	private List<InputDependente> inputDependentesUserAltered;
	private List<InputLancamento> inputLancamentosUserCreated;
	private List<InputLancamento> inputLancamentosUserAltered;
	private List<InputTitular> inputTitularsUserCreated;
	private List<InputTitular> inputTitularsUserAltered;
	private List<Lancamento> lancamentosUserCreated;
	private List<Lancamento> lancamentosUserAltered;
	private List<LancamentoColsDef> lancamentoColsDefsUserCreated;
	private List<LancamentoColsDef> lancamentoColsDefsUserAltered;
	private List<Operadora> operadorasUserCreated;
	private List<Operadora> operadorasUserAltered;
	private List<Regra> regrasUserCreated;
	private List<Regra> regrasUserAltered;
	private List<RegraField> regraFieldsUserCreated;
	private List<RegraField> regraFieldsUserAltered;
	private List<RegraOperation> regraOperationsUserCreated;
	private List<RegraOperation> regraOperationsUserAltered;
	private List<RegraValor> regraValorsUserCreated;
	private List<RegraValor> regraValorsUserAltered;
	private List<Titular> titularsUserCreated;
	private List<Titular> titularsUserAltered;
	private List<TitularColsDef> titularColsDefsUserCreated;
	private List<TitularColsDef> titularColsDefsUserAltered;
	private List<TitularIsento> titularIsentosUserCreated;
	private List<TitularIsento> titularIsentosUserAltered;
	private List<ViewDestination> viewDestinationsUserCreated;
	private List<ViewDestination> viewDestinationsUserAltered;
	private List<ViewDestinationColsDef> viewDestinationColsDefsUserCreated;
	private List<ViewDestinationColsDef> viewDestinationColsDefsUserAltered;

	private List<Parameter> userAlteredParameter;

	private List<Parameter> userCreatedParameter;

	public User() {
	}

	public User(User entity) {
		super(entity);
	}

	public String getDescrName() {
		return this.descrName;
	}

	public void setDescrName(String descrName) {
		this.descrName = descrName;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNameLogin() {
		return this.nameLogin;
	}

	public void setNameLogin(String nmLogin) {
		this.nameLogin = nmLogin;
	}

	public UserStatusType getTpStatus() {
		return this.tpStatus;
	}

	public void setTpStatus(UserStatusType tpStatus) {
		this.tpStatus = tpStatus;
	}

	public List<ArquivoInput> getArquivoInputsUserCreated() {
		return this.arquivoInputsUserCreated;
	}

	public void setArquivoInputsUserCreated(
			List<ArquivoInput> arquivoInputsUserCreated) {
		this.arquivoInputsUserCreated = arquivoInputsUserCreated;
	}

	public ArquivoInput addArquivoInputsUserCreated(
			ArquivoInput arquivoInputsUserCreated) {
		getArquivoInputsUserCreated().add(arquivoInputsUserCreated);
		arquivoInputsUserCreated.setUserCreated(this);

		return arquivoInputsUserCreated;
	}

	public ArquivoInput removeArquivoInputsUserCreated(
			ArquivoInput arquivoInputsUserCreated) {
		getArquivoInputsUserCreated().remove(arquivoInputsUserCreated);
		arquivoInputsUserCreated.setUserCreated(null);

		return arquivoInputsUserCreated;
	}

	public List<ArquivoInput> getArquivoInputsUserAltered() {
		return this.arquivoInputsUserAltered;
	}

	public void setArquivoInputsUserAltered(
			List<ArquivoInput> arquivoInputsUserAltered) {
		this.arquivoInputsUserAltered = arquivoInputsUserAltered;
	}

	public ArquivoInput addArquivoInputsUserAltered(
			ArquivoInput arquivoInputsUserAltered) {
		getArquivoInputsUserAltered().add(arquivoInputsUserAltered);
		arquivoInputsUserAltered.setUserAltered(this);

		return arquivoInputsUserAltered;
	}

	public ArquivoInput removeArquivoInputsUserAltered(
			ArquivoInput arquivoInputsUserAltered) {
		getArquivoInputsUserAltered().remove(arquivoInputsUserAltered);
		arquivoInputsUserAltered.setUserAltered(null);

		return arquivoInputsUserAltered;
	}

	public List<ArquivoInputColsDef> getArquivoInputColsDefUserCreated() {
		return this.arquivoInputColsDefUserCreated;
	}

	public void setArquivoInputColsDefUserCreated(
			List<ArquivoInputColsDef> arquivoInputColsDefUserCreated) {
		this.arquivoInputColsDefUserCreated = arquivoInputColsDefUserCreated;
	}

	public ArquivoInputColsDef addArquivoInputColsDefUserCreated(
			ArquivoInputColsDef arquivoInputColsDefUserCreated) {
		getArquivoInputColsDefUserCreated().add(arquivoInputColsDefUserCreated);
		arquivoInputColsDefUserCreated.setUserCreated(this);

		return arquivoInputColsDefUserCreated;
	}

	public ArquivoInputColsDef removeArquivoInputColsDefUserCreated(
			ArquivoInputColsDef arquivoInputColsDefUserCreated) {
		getArquivoInputColsDefUserCreated()
				.remove(arquivoInputColsDefUserCreated);
		arquivoInputColsDefUserCreated.setUserCreated(null);

		return arquivoInputColsDefUserCreated;
	}

	public List<ArquivoInputColsDef> getArquivoInputColsDefUserAltered() {
		return this.arquivoInputColsDefUserAltered;
	}

	public void setArquivoInputColsDefUserAltered(
			List<ArquivoInputColsDef> arquivoInputColsDefUserAltered) {
		this.arquivoInputColsDefUserAltered = arquivoInputColsDefUserAltered;
	}

	public ArquivoInputColsDef addArquivoInputColsDefUserAltered(
			ArquivoInputColsDef arquivoInputColsDefUserAltered) {
		getArquivoInputColsDefUserAltered().add(arquivoInputColsDefUserAltered);
		arquivoInputColsDefUserAltered.setUserAltered(this);

		return arquivoInputColsDefUserAltered;
	}

	public ArquivoInputColsDef removeArquivoInputColsDefUserAltered(
			ArquivoInputColsDef arquivoInputColsDefUserAltered) {
		getArquivoInputColsDefUserAltered()
				.remove(arquivoInputColsDefUserAltered);
		arquivoInputColsDefUserAltered.setUserAltered(null);

		return arquivoInputColsDefUserAltered;
	}

	public List<ArquivoOutput> getArquivoOutputsUserCreated() {
		return this.arquivoOutputsUserCreated;
	}

	public void setArquivoOutputsUserCreated(
			List<ArquivoOutput> arquivoOutputsUserCreated) {
		this.arquivoOutputsUserCreated = arquivoOutputsUserCreated;
	}

	public ArquivoOutput addArquivoOutputsUserCreated(
			ArquivoOutput arquivoOutputsUserCreated) {
		getArquivoOutputsUserCreated().add(arquivoOutputsUserCreated);
		arquivoOutputsUserCreated.setUserCreated(this);

		return arquivoOutputsUserCreated;
	}

	public ArquivoOutput removeArquivoOutputsUserCreated(
			ArquivoOutput arquivoOutputsUserCreated) {
		getArquivoOutputsUserCreated().remove(arquivoOutputsUserCreated);
		arquivoOutputsUserCreated.setUserCreated(null);

		return arquivoOutputsUserCreated;
	}

	public List<ArquivoOutput> getArquivoOutputsUserAltered() {
		return this.arquivoOutputsUserAltered;
	}

	public void setArquivoOutputsUserAltered(
			List<ArquivoOutput> arquivoOutputsUserAltered) {
		this.arquivoOutputsUserAltered = arquivoOutputsUserAltered;
	}

	public ArquivoOutput addArquivoOutputsUserAltered(
			ArquivoOutput arquivoOutputsUserAltered) {
		getArquivoOutputsUserAltered().add(arquivoOutputsUserAltered);
		arquivoOutputsUserAltered.setUserAltered(this);

		return arquivoOutputsUserAltered;
	}

	public ArquivoOutput removeArquivoOutputsUserAltered(
			ArquivoOutput arquivoOutputsUserAltered) {
		getArquivoOutputsUserAltered().remove(arquivoOutputsUserAltered);
		arquivoOutputsUserAltered.setUserAltered(null);

		return arquivoOutputsUserAltered;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserCreated() {
		return this.arquivoOutputSheetsUserCreated;
	}

	public void setArquivoOutputSheetsUserCreated(
			List<ArquivoOutputSheet> arquivoOutputSheetsUserCreated) {
		this.arquivoOutputSheetsUserCreated = arquivoOutputSheetsUserCreated;
	}

	public ArquivoOutputSheet addArquivoOutputSheetsUserCreated(
			ArquivoOutputSheet arquivoOutputSheetsUserCreated) {
		getArquivoOutputSheetsUserCreated().add(arquivoOutputSheetsUserCreated);
		arquivoOutputSheetsUserCreated.setUserCreated(this);

		return arquivoOutputSheetsUserCreated;
	}

	public ArquivoOutputSheet removeArquivoOutputSheetsUserCreated(
			ArquivoOutputSheet arquivoOutputSheetsUserCreated) {
		getArquivoOutputSheetsUserCreated()
				.remove(arquivoOutputSheetsUserCreated);
		arquivoOutputSheetsUserCreated.setUserCreated(null);

		return arquivoOutputSheetsUserCreated;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserAltered() {
		return this.arquivoOutputSheetsUserAltered;
	}

	public void setArquivoOutputSheetsUserAltered(
			List<ArquivoOutputSheet> arquivoOutputSheetsUserAltered) {
		this.arquivoOutputSheetsUserAltered = arquivoOutputSheetsUserAltered;
	}

	public ArquivoOutputSheet addArquivoOutputSheetsUserAltered(
			ArquivoOutputSheet arquivoOutputSheetsUserAltered) {
		getArquivoOutputSheetsUserAltered().add(arquivoOutputSheetsUserAltered);
		arquivoOutputSheetsUserAltered.setUserAltered(this);

		return arquivoOutputSheetsUserAltered;
	}

	public ArquivoOutputSheet removeArquivoOutputSheetsUserAltered(
			ArquivoOutputSheet arquivoOutputSheetsUserAltered) {
		getArquivoOutputSheetsUserAltered()
				.remove(arquivoOutputSheetsUserAltered);
		arquivoOutputSheetsUserAltered.setUserAltered(null);

		return arquivoOutputSheetsUserAltered;
	}

	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefUserCreated() {
		return this.arquivoOutputSheetColsDefUserCreated;
	}

	public void setArquivoOutputSheetColsDefUserCreated(
			List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefUserCreated) {
		this.arquivoOutputSheetColsDefUserCreated = arquivoOutputSheetColsDefUserCreated;
	}

	public ArquivoOutputSheetColsDef addArquivoOutputSheetColsDefUserCreated(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDefUserCreated) {
		getArquivoOutputSheetColsDefUserCreated()
				.add(arquivoOutputSheetColsDefUserCreated);
		arquivoOutputSheetColsDefUserCreated.setUserCreated(this);

		return arquivoOutputSheetColsDefUserCreated;
	}

	public ArquivoOutputSheetColsDef removeArquivoOutputSheetColsDefUserCreated(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDefUserCreated) {
		getArquivoOutputSheetColsDefUserCreated()
				.remove(arquivoOutputSheetColsDefUserCreated);
		arquivoOutputSheetColsDefUserCreated.setUserCreated(null);

		return arquivoOutputSheetColsDefUserCreated;
	}

	public List<ArquivoOutputSheetColsDef> getArquivoOutputSheetColsDefUserAltered() {
		return this.arquivoOutputSheetColsDefUserAltered;
	}

	public void setArquivoOutputSheetColsDefUserAltered(
			List<ArquivoOutputSheetColsDef> arquivoOutputSheetColsDefUserAltered) {
		this.arquivoOutputSheetColsDefUserAltered = arquivoOutputSheetColsDefUserAltered;
	}

	public ArquivoOutputSheetColsDef addArquivoOutputSheetColsDefUserAltered(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDefUserAltered) {
		getArquivoOutputSheetColsDefUserAltered()
				.add(arquivoOutputSheetColsDefUserAltered);
		arquivoOutputSheetColsDefUserAltered.setUserAltered(this);

		return arquivoOutputSheetColsDefUserAltered;
	}

	public ArquivoOutputSheetColsDef removeArquivoOutputSheetColsDefUserAltered(
			ArquivoOutputSheetColsDef arquivoOutputSheetColsDefUserAltered) {
		getArquivoOutputSheetColsDefUserAltered()
				.remove(arquivoOutputSheetColsDefUserAltered);
		arquivoOutputSheetColsDefUserAltered.setUserAltered(null);

		return arquivoOutputSheetColsDefUserAltered;
	}

	public List<Contrato> getContratosUserCreated() {
		return this.contratosUserCreated;
	}

	public void setContratosUserCreated(List<Contrato> contratosUserCreated) {
		this.contratosUserCreated = contratosUserCreated;
	}

	public Contrato addContratosUserCreated(Contrato contratosUserCreated) {
		getContratosUserCreated().add(contratosUserCreated);
		contratosUserCreated.setUserCreated(this);

		return contratosUserCreated;
	}

	public Contrato removeContratosUserCreated(Contrato contratosUserCreated) {
		getContratosUserCreated().remove(contratosUserCreated);
		contratosUserCreated.setUserCreated(null);

		return contratosUserCreated;
	}

	public List<Contrato> getContratosUserAltered() {
		return this.contratosUserAltered;
	}

	public void setContratosUserAltered(List<Contrato> contratosUserAltered) {
		this.contratosUserAltered = contratosUserAltered;
	}

	public Contrato addContratosUserAltered(Contrato contratosUserAltered) {
		getContratosUserAltered().add(contratosUserAltered);
		contratosUserAltered.setUserAltered(this);

		return contratosUserAltered;
	}

	public Contrato removeContratosUserAltered(Contrato contratosUserAltered) {
		getContratosUserAltered().remove(contratosUserAltered);
		contratosUserAltered.setUserAltered(null);

		return contratosUserAltered;
	}

	public List<Dependente> getDependentesUserCreated() {
		return this.dependentesUserCreated;
	}

	public void setDependentesUserCreated(
			List<Dependente> dependentesUserCreated) {
		this.dependentesUserCreated = dependentesUserCreated;
	}

	public Dependente addDependentesUserCreated(
			Dependente dependentesUserCreated) {
		getDependentesUserCreated().add(dependentesUserCreated);
		dependentesUserCreated.setUserCreated(this);

		return dependentesUserCreated;
	}

	public Dependente removeDependentesUserCreated(
			Dependente dependentesUserCreated) {
		getDependentesUserCreated().remove(dependentesUserCreated);
		dependentesUserCreated.setUserCreated(null);

		return dependentesUserCreated;
	}

	public List<Dependente> getDependentesUserAltered() {
		return this.dependentesUserAltered;
	}

	public void setDependentesUserAltered(
			List<Dependente> dependentesUserAltered) {
		this.dependentesUserAltered = dependentesUserAltered;
	}

	public Dependente addDependentesUserAltered(
			Dependente dependentesUserAltered) {
		getDependentesUserAltered().add(dependentesUserAltered);
		dependentesUserAltered.setUserAltered(this);

		return dependentesUserAltered;
	}

	public Dependente removeDependentesUserAltered(
			Dependente dependentesUserAltered) {
		getDependentesUserAltered().remove(dependentesUserAltered);
		dependentesUserAltered.setUserAltered(null);

		return dependentesUserAltered;
	}

	public List<DependenteColsDef> getDependenteColsDefsUserCreated() {
		return this.dependenteColsDefsUserCreated;
	}

	public void setDependenteColsDefsUserCreated(
			List<DependenteColsDef> dependenteColsDefsUserCreated) {
		this.dependenteColsDefsUserCreated = dependenteColsDefsUserCreated;
	}

	public DependenteColsDef addDependenteColsDefsUserCreated(
			DependenteColsDef dependenteColsDefsUserCreated) {
		getDependenteColsDefsUserCreated().add(dependenteColsDefsUserCreated);
		dependenteColsDefsUserCreated.setUserCreated(this);

		return dependenteColsDefsUserCreated;
	}

	public DependenteColsDef removeDependenteColsDefsUserCreated(
			DependenteColsDef dependenteColsDefsUserCreated) {
		getDependenteColsDefsUserCreated()
				.remove(dependenteColsDefsUserCreated);
		dependenteColsDefsUserCreated.setUserCreated(null);

		return dependenteColsDefsUserCreated;
	}

	public List<DependenteColsDef> getDependenteColsDefsUserAltered() {
		return this.dependenteColsDefsUserAltered;
	}

	public void setDependenteColsDefsUserAltered(
			List<DependenteColsDef> dependenteColsDefsUserAltered) {
		this.dependenteColsDefsUserAltered = dependenteColsDefsUserAltered;
	}

	public DependenteColsDef addDependenteColsDefsUserAltered(
			DependenteColsDef dependenteColsDefsUserAltered) {
		getDependenteColsDefsUserAltered().add(dependenteColsDefsUserAltered);
		dependenteColsDefsUserAltered.setUserAltered(this);

		return dependenteColsDefsUserAltered;
	}

	public DependenteColsDef removeDependenteColsDefsUserAltered(
			DependenteColsDef dependenteColsDefsUserAltered) {
		getDependenteColsDefsUserAltered()
				.remove(dependenteColsDefsUserAltered);
		dependenteColsDefsUserAltered.setUserAltered(null);

		return dependenteColsDefsUserAltered;
	}

	public List<DependenteIsento> getDependenteIsentosUserCreated() {
		return this.dependenteIsentosUserCreated;
	}

	public void setDependenteIsentosUserCreated(
			List<DependenteIsento> dependenteIsentosUserCreated) {
		this.dependenteIsentosUserCreated = dependenteIsentosUserCreated;
	}

	public DependenteIsento addDependenteIsentosUserCreated(
			DependenteIsento dependenteIsentosUserCreated) {
		getDependenteIsentosUserCreated().add(dependenteIsentosUserCreated);
		dependenteIsentosUserCreated.setUserCreated(this);

		return dependenteIsentosUserCreated;
	}

	public DependenteIsento removeDependenteIsentosUserCreated(
			DependenteIsento dependenteIsentosUserCreated) {
		getDependenteIsentosUserCreated().remove(dependenteIsentosUserCreated);
		dependenteIsentosUserCreated.setUserCreated(null);

		return dependenteIsentosUserCreated;
	}

	public List<DependenteIsento> getDependenteIsentosUserAltered() {
		return this.dependenteIsentosUserAltered;
	}

	public void setDependenteIsentosUserAltered(
			List<DependenteIsento> dependenteIsentosUserAltered) {
		this.dependenteIsentosUserAltered = dependenteIsentosUserAltered;
	}

	public DependenteIsento addDependenteIsentosUserAltered(
			DependenteIsento dependenteIsentosUserAltered) {
		getDependenteIsentosUserAltered().add(dependenteIsentosUserAltered);
		dependenteIsentosUserAltered.setUserAltered(this);

		return dependenteIsentosUserAltered;
	}

	public DependenteIsento removeDependenteIsentosUserAltered(
			DependenteIsento dependenteIsentosUserAltered) {
		getDependenteIsentosUserAltered().remove(dependenteIsentosUserAltered);
		dependenteIsentosUserAltered.setUserAltered(null);

		return dependenteIsentosUserAltered;
	}

	public List<Empresa> getEmpresasUserCreated() {
		return this.empresasUserCreated;
	}

	public void setEmpresasUserCreated(List<Empresa> empresasUserCreated) {
		this.empresasUserCreated = empresasUserCreated;
	}

	public Empresa addEmpresasUserCreated(Empresa empresasUserCreated) {
		getEmpresasUserCreated().add(empresasUserCreated);
		empresasUserCreated.setUserCreated(this);

		return empresasUserCreated;
	}

	public Empresa removeEmpresasUserCreated(Empresa empresasUserCreated) {
		getEmpresasUserCreated().remove(empresasUserCreated);
		empresasUserCreated.setUserCreated(null);

		return empresasUserCreated;
	}

	public List<Empresa> getEmpresasUserAltered() {
		return this.empresasUserAltered;
	}

	public void setEmpresasUserAltered(List<Empresa> empresasUserAltered) {
		this.empresasUserAltered = empresasUserAltered;
	}

	public Empresa addEmpresasUserAltered(Empresa empresasUserAltered) {
		getEmpresasUserAltered().add(empresasUserAltered);
		empresasUserAltered.setUserAltered(this);

		return empresasUserAltered;
	}

	public Empresa removeEmpresasUserAltered(Empresa empresasUserAltered) {
		getEmpresasUserAltered().remove(empresasUserAltered);
		empresasUserAltered.setUserAltered(null);

		return empresasUserAltered;
	}

	public List<InputDependente> getInputDependentesUserCreated() {
		return this.inputDependentesUserCreated;
	}

	public void setInputDependentesUserCreated(
			List<InputDependente> inputDependentesUserCreated) {
		this.inputDependentesUserCreated = inputDependentesUserCreated;
	}

	public InputDependente addInputDependentesUserCreated(
			InputDependente inputDependentesUserCreated) {
		getInputDependentesUserCreated().add(inputDependentesUserCreated);
		inputDependentesUserCreated.setUserCreated(this);

		return inputDependentesUserCreated;
	}

	public InputDependente removeInputDependentesUserCreated(
			InputDependente inputDependentesUserCreated) {
		getInputDependentesUserCreated().remove(inputDependentesUserCreated);
		inputDependentesUserCreated.setUserCreated(null);

		return inputDependentesUserCreated;
	}

	public List<InputDependente> getInputDependentesUserAltered() {
		return this.inputDependentesUserAltered;
	}

	public void setInputDependentesUserAltered(
			List<InputDependente> inputDependentesUserAltered) {
		this.inputDependentesUserAltered = inputDependentesUserAltered;
	}

	public InputDependente addInputDependentesUserAltered(
			InputDependente inputDependentesUserAltered) {
		getInputDependentesUserAltered().add(inputDependentesUserAltered);
		inputDependentesUserAltered.setUserAltered(this);

		return inputDependentesUserAltered;
	}

	public InputDependente removeInputDependentesUserAltered(
			InputDependente inputDependentesUserAltered) {
		getInputDependentesUserAltered().remove(inputDependentesUserAltered);
		inputDependentesUserAltered.setUserAltered(null);

		return inputDependentesUserAltered;
	}

	public List<InputLancamento> getInputLancamentosUserCreated() {
		return this.inputLancamentosUserCreated;
	}

	public void setInputLancamentosUserCreated(
			List<InputLancamento> inputLancamentosUserCreated) {
		this.inputLancamentosUserCreated = inputLancamentosUserCreated;
	}

	public InputLancamento addInputLancamentosUserCreated(
			InputLancamento inputLancamentosUserCreated) {
		getInputLancamentosUserCreated().add(inputLancamentosUserCreated);
		inputLancamentosUserCreated.setUserCreated(this);

		return inputLancamentosUserCreated;
	}

	public InputLancamento removeInputLancamentosUserCreated(
			InputLancamento inputLancamentosUserCreated) {
		getInputLancamentosUserCreated().remove(inputLancamentosUserCreated);
		inputLancamentosUserCreated.setUserCreated(null);

		return inputLancamentosUserCreated;
	}

	public List<InputLancamento> getInputLancamentosUserAltered() {
		return this.inputLancamentosUserAltered;
	}

	public void setInputLancamentosUserAltered(
			List<InputLancamento> inputLancamentosUserAltered) {
		this.inputLancamentosUserAltered = inputLancamentosUserAltered;
	}

	public InputLancamento addInputLancamentosUserAltered(
			InputLancamento inputLancamentosUserAltered) {
		getInputLancamentosUserAltered().add(inputLancamentosUserAltered);
		inputLancamentosUserAltered.setUserAltered(this);

		return inputLancamentosUserAltered;
	}

	public InputLancamento removeInputLancamentosUserAltered(
			InputLancamento inputLancamentosUserAltered) {
		getInputLancamentosUserAltered().remove(inputLancamentosUserAltered);
		inputLancamentosUserAltered.setUserAltered(null);

		return inputLancamentosUserAltered;
	}

	public List<InputTitular> getInputTitularsUserCreated() {
		return this.inputTitularsUserCreated;
	}

	public void setInputTitularsUserCreated(
			List<InputTitular> inputTitularsUserCreated) {
		this.inputTitularsUserCreated = inputTitularsUserCreated;
	}

	public InputTitular addInputTitularsUserCreated(
			InputTitular inputTitularsUserCreated) {
		getInputTitularsUserCreated().add(inputTitularsUserCreated);
		inputTitularsUserCreated.setUserCreated(this);

		return inputTitularsUserCreated;
	}

	public InputTitular removeInputTitularsUserCreated(
			InputTitular inputTitularsUserCreated) {
		getInputTitularsUserCreated().remove(inputTitularsUserCreated);
		inputTitularsUserCreated.setUserCreated(null);

		return inputTitularsUserCreated;
	}

	public List<InputTitular> getInputTitularsUserAltered() {
		return this.inputTitularsUserAltered;
	}

	public void setInputTitularsUserAltered(
			List<InputTitular> inputTitularsUserAltered) {
		this.inputTitularsUserAltered = inputTitularsUserAltered;
	}

	public InputTitular addInputTitularsUserAltered(
			InputTitular inputTitularsUserAltered) {
		getInputTitularsUserAltered().add(inputTitularsUserAltered);
		inputTitularsUserAltered.setUserAltered(this);

		return inputTitularsUserAltered;
	}

	public InputTitular removeInputTitularsUserAltered(
			InputTitular inputTitularsUserAltered) {
		getInputTitularsUserAltered().remove(inputTitularsUserAltered);
		inputTitularsUserAltered.setUserAltered(null);

		return inputTitularsUserAltered;
	}

	public List<Lancamento> getLancamentosUserCreated() {
		return this.lancamentosUserCreated;
	}

	public void setLancamentosUserCreated(
			List<Lancamento> lancamentosUserCreated) {
		this.lancamentosUserCreated = lancamentosUserCreated;
	}

	public Lancamento addLancamentosUserCreated(
			Lancamento lancamentosUserCreated) {
		getLancamentosUserCreated().add(lancamentosUserCreated);
		lancamentosUserCreated.setUserCreated(this);

		return lancamentosUserCreated;
	}

	public Lancamento removeLancamentosUserCreated(
			Lancamento lancamentosUserCreated) {
		getLancamentosUserCreated().remove(lancamentosUserCreated);
		lancamentosUserCreated.setUserCreated(null);

		return lancamentosUserCreated;
	}

	public List<Lancamento> getLancamentosUserAltered() {
		return this.lancamentosUserAltered;
	}

	public void setLancamentosUserAltered(
			List<Lancamento> lancamentosUserAltered) {
		this.lancamentosUserAltered = lancamentosUserAltered;
	}

	public Lancamento addLancamentosUserAltered(
			Lancamento lancamentosUserAltered) {
		getLancamentosUserAltered().add(lancamentosUserAltered);
		lancamentosUserAltered.setUserAltered(this);

		return lancamentosUserAltered;
	}

	public Lancamento removeLancamentosUserAltered(
			Lancamento lancamentosUserAltered) {
		getLancamentosUserAltered().remove(lancamentosUserAltered);
		lancamentosUserAltered.setUserAltered(null);

		return lancamentosUserAltered;
	}

	public List<LancamentoColsDef> getLancamentoColsDefsUserCreated() {
		return this.lancamentoColsDefsUserCreated;
	}

	public void setLancamentoColsDefsUserCreated(
			List<LancamentoColsDef> lancamentoColsDefsUserCreated) {
		this.lancamentoColsDefsUserCreated = lancamentoColsDefsUserCreated;
	}

	public LancamentoColsDef addLancamentoColsDefsUserCreated(
			LancamentoColsDef lancamentoColsDefsUserCreated) {
		getLancamentoColsDefsUserCreated().add(lancamentoColsDefsUserCreated);
		lancamentoColsDefsUserCreated.setUserCreated(this);

		return lancamentoColsDefsUserCreated;
	}

	public LancamentoColsDef removeLancamentoColsDefsUserCreated(
			LancamentoColsDef lancamentoColsDefsUserCreated) {
		getLancamentoColsDefsUserCreated()
				.remove(lancamentoColsDefsUserCreated);
		lancamentoColsDefsUserCreated.setUserCreated(null);

		return lancamentoColsDefsUserCreated;
	}

	public List<LancamentoColsDef> getLancamentoColsDefsUserAltered() {
		return this.lancamentoColsDefsUserAltered;
	}

	public void setLancamentoColsDefsUserAltered(
			List<LancamentoColsDef> lancamentoColsDefsUserAltered) {
		this.lancamentoColsDefsUserAltered = lancamentoColsDefsUserAltered;
	}

	public LancamentoColsDef addLancamentoColsDefsUserAltered(
			LancamentoColsDef lancamentoColsDefsUserAltered) {
		getLancamentoColsDefsUserAltered().add(lancamentoColsDefsUserAltered);
		lancamentoColsDefsUserAltered.setUserAltered(this);

		return lancamentoColsDefsUserAltered;
	}

	public LancamentoColsDef removeLancamentoColsDefsUserAltered(
			LancamentoColsDef lancamentoColsDefsUserAltered) {
		getLancamentoColsDefsUserAltered()
				.remove(lancamentoColsDefsUserAltered);
		lancamentoColsDefsUserAltered.setUserAltered(null);

		return lancamentoColsDefsUserAltered;
	}

	public List<Operadora> getOperadorasUserCreated() {
		return this.operadorasUserCreated;
	}

	public void setOperadorasUserCreated(
			List<Operadora> operadorasUserCreated) {
		this.operadorasUserCreated = operadorasUserCreated;
	}

	public Operadora addOperadorasUserCreated(Operadora operadorasUserCreated) {
		getOperadorasUserCreated().add(operadorasUserCreated);
		operadorasUserCreated.setUserCreated(this);

		return operadorasUserCreated;
	}

	public Operadora removeOperadorasUserCreated(
			Operadora operadorasUserCreated) {
		getOperadorasUserCreated().remove(operadorasUserCreated);
		operadorasUserCreated.setUserCreated(null);

		return operadorasUserCreated;
	}

	public List<Operadora> getOperadorasUserAltered() {
		return this.operadorasUserAltered;
	}

	public void setOperadorasUserAltered(
			List<Operadora> operadorasUserAltered) {
		this.operadorasUserAltered = operadorasUserAltered;
	}

	public Operadora addOperadorasUserAltered(Operadora operadorasUserAltered) {
		getOperadorasUserAltered().add(operadorasUserAltered);
		operadorasUserAltered.setUserAltered(this);

		return operadorasUserAltered;
	}

	public Operadora removeOperadorasUserAltered(
			Operadora operadorasUserAltered) {
		getOperadorasUserAltered().remove(operadorasUserAltered);
		operadorasUserAltered.setUserAltered(null);

		return operadorasUserAltered;
	}

	public List<Regra> getRegrasUserCreated() {
		return this.regrasUserCreated;
	}

	public void setRegrasUserCreated(List<Regra> regrasUserCreated) {
		this.regrasUserCreated = regrasUserCreated;
	}

	public Regra addRegrasUserCreated(Regra regrasUserCreated) {
		getRegrasUserCreated().add(regrasUserCreated);
		regrasUserCreated.setUserCreated(this);

		return regrasUserCreated;
	}

	public Regra removeRegrasUserCreated(Regra regrasUserCreated) {
		getRegrasUserCreated().remove(regrasUserCreated);
		regrasUserCreated.setUserCreated(null);

		return regrasUserCreated;
	}

	public List<Regra> getRegrasUserAltered() {
		return this.regrasUserAltered;
	}

	public void setRegrasUserAltered(List<Regra> regrasUserAltered) {
		this.regrasUserAltered = regrasUserAltered;
	}

	public Regra addRegrasUserAltered(Regra regrasUserAltered) {
		getRegrasUserAltered().add(regrasUserAltered);
		regrasUserAltered.setUserAltered(this);

		return regrasUserAltered;
	}

	public Regra removeRegrasUserAltered(Regra regrasUserAltered) {
		getRegrasUserAltered().remove(regrasUserAltered);
		regrasUserAltered.setUserAltered(null);

		return regrasUserAltered;
	}

	public List<RegraField> getRegraFieldsUserCreated() {
		return this.regraFieldsUserCreated;
	}

	public void setRegraFieldsUserCreated(
			List<RegraField> regraFieldsUserCreated) {
		this.regraFieldsUserCreated = regraFieldsUserCreated;
	}

	public RegraField addRegraFieldsUserCreated(
			RegraField regraFieldsUserCreated) {
		getRegraFieldsUserCreated().add(regraFieldsUserCreated);
		regraFieldsUserCreated.setUserCreated(this);

		return regraFieldsUserCreated;
	}

	public RegraField removeRegraFieldsUserCreated(
			RegraField regraFieldsUserCreated) {
		getRegraFieldsUserCreated().remove(regraFieldsUserCreated);
		regraFieldsUserCreated.setUserCreated(null);

		return regraFieldsUserCreated;
	}

	public List<RegraField> getRegraFieldsUserAltered() {
		return this.regraFieldsUserAltered;
	}

	public void setRegraFieldsUserAltered(
			List<RegraField> regraFieldsUserAltered) {
		this.regraFieldsUserAltered = regraFieldsUserAltered;
	}

	public RegraField addRegraFieldsUserAltered(
			RegraField regraFieldsUserAltered) {
		getRegraFieldsUserAltered().add(regraFieldsUserAltered);
		regraFieldsUserAltered.setUserAltered(this);

		return regraFieldsUserAltered;
	}

	public RegraField removeRegraFieldsUserAltered(
			RegraField regraFieldsUserAltered) {
		getRegraFieldsUserAltered().remove(regraFieldsUserAltered);
		regraFieldsUserAltered.setUserAltered(null);

		return regraFieldsUserAltered;
	}

	public List<RegraOperation> getRegraOperationsUserCreated() {
		return this.regraOperationsUserCreated;
	}

	public void setRegraOperationsUserCreated(
			List<RegraOperation> regraOperationsUserCreated) {
		this.regraOperationsUserCreated = regraOperationsUserCreated;
	}

	public RegraOperation addRegraOperationsUserCreated(
			RegraOperation regraOperationsUserCreated) {
		getRegraOperationsUserCreated().add(regraOperationsUserCreated);
		regraOperationsUserCreated.setUserCreated(this);

		return regraOperationsUserCreated;
	}

	public RegraOperation removeRegraOperationsUserCreated(
			RegraOperation regraOperationsUserCreated) {
		getRegraOperationsUserCreated().remove(regraOperationsUserCreated);
		regraOperationsUserCreated.setUserCreated(null);

		return regraOperationsUserCreated;
	}

	public List<RegraOperation> getRegraOperationsUserAltered() {
		return this.regraOperationsUserAltered;
	}

	public void setRegraOperationsUserAltered(
			List<RegraOperation> regraOperationsUserAltered) {
		this.regraOperationsUserAltered = regraOperationsUserAltered;
	}

	public RegraOperation addRegraOperationsUserAltered(
			RegraOperation regraOperationsUserAltered) {
		getRegraOperationsUserAltered().add(regraOperationsUserAltered);
		regraOperationsUserAltered.setUserAltered(this);

		return regraOperationsUserAltered;
	}

	public RegraOperation removeRegraOperationsUserAltered(
			RegraOperation regraOperationsUserAltered) {
		getRegraOperationsUserAltered().remove(regraOperationsUserAltered);
		regraOperationsUserAltered.setUserAltered(null);

		return regraOperationsUserAltered;
	}

	public List<RegraValor> getRegraValorsUserCreated() {
		return this.regraValorsUserCreated;
	}

	public void setRegraValorsUserCreated(
			List<RegraValor> regraValorsUserCreated) {
		this.regraValorsUserCreated = regraValorsUserCreated;
	}

	public RegraValor addRegraValorsUserCreated(
			RegraValor regraValorsUserCreated) {
		getRegraValorsUserCreated().add(regraValorsUserCreated);
		regraValorsUserCreated.setUserCreated(this);

		return regraValorsUserCreated;
	}

	public RegraValor removeRegraValorsUserCreated(
			RegraValor regraValorsUserCreated) {
		getRegraValorsUserCreated().remove(regraValorsUserCreated);
		regraValorsUserCreated.setUserCreated(null);

		return regraValorsUserCreated;
	}

	public List<RegraValor> getRegraValorsUserAltered() {
		return this.regraValorsUserAltered;
	}

	public void setRegraValorsUserAltered(
			List<RegraValor> regraValorsUserAltered) {
		this.regraValorsUserAltered = regraValorsUserAltered;
	}

	public RegraValor addRegraValorsUserAltered(
			RegraValor regraValorsUserAltered) {
		getRegraValorsUserAltered().add(regraValorsUserAltered);
		regraValorsUserAltered.setUserAltered(this);

		return regraValorsUserAltered;
	}

	public RegraValor removeRegraValorsUserAltered(
			RegraValor regraValorsUserAltered) {
		getRegraValorsUserAltered().remove(regraValorsUserAltered);
		regraValorsUserAltered.setUserAltered(null);

		return regraValorsUserAltered;
	}

	public List<Titular> getTitularsUserCreated() {
		return this.titularsUserCreated;
	}

	public void setTitularsUserCreated(List<Titular> titularsUserCreated) {
		this.titularsUserCreated = titularsUserCreated;
	}

	public Titular addTitularsUserCreated(Titular titularsUserCreated) {
		getTitularsUserCreated().add(titularsUserCreated);
		titularsUserCreated.setUserCreated(this);

		return titularsUserCreated;
	}

	public Titular removeTitularsUserCreated(Titular titularsUserCreated) {
		getTitularsUserCreated().remove(titularsUserCreated);
		titularsUserCreated.setUserCreated(null);

		return titularsUserCreated;
	}

	public List<Titular> getTitularsUserAltered() {
		return this.titularsUserAltered;
	}

	public void setTitularsUserAltered(List<Titular> titularsUserAltered) {
		this.titularsUserAltered = titularsUserAltered;
	}

	public Titular addTitularsUserAltered(Titular titularsUserAltered) {
		getTitularsUserAltered().add(titularsUserAltered);
		titularsUserAltered.setUserAltered(this);

		return titularsUserAltered;
	}

	public Titular removeTitularsUserAltered(Titular titularsUserAltered) {
		getTitularsUserAltered().remove(titularsUserAltered);
		titularsUserAltered.setUserAltered(null);

		return titularsUserAltered;
	}

	public List<TitularColsDef> getTitularColsDefsUserCreated() {
		return this.titularColsDefsUserCreated;
	}

	public void setTitularColsDefsUserCreated(
			List<TitularColsDef> titularColsDefsUserCreated) {
		this.titularColsDefsUserCreated = titularColsDefsUserCreated;
	}

	public TitularColsDef addTitularColsDefsUserCreated(
			TitularColsDef titularColsDefsUserCreated) {
		getTitularColsDefsUserCreated().add(titularColsDefsUserCreated);
		titularColsDefsUserCreated.setUserCreated(this);

		return titularColsDefsUserCreated;
	}

	public TitularColsDef removeTitularColsDefsUserCreated(
			TitularColsDef titularColsDefsUserCreated) {
		getTitularColsDefsUserCreated().remove(titularColsDefsUserCreated);
		titularColsDefsUserCreated.setUserCreated(null);

		return titularColsDefsUserCreated;
	}

	public List<TitularColsDef> getTitularColsDefsUserAltered() {
		return this.titularColsDefsUserAltered;
	}

	public void setTitularColsDefsUserAltered(
			List<TitularColsDef> titularColsDefsUserAltered) {
		this.titularColsDefsUserAltered = titularColsDefsUserAltered;
	}

	public TitularColsDef addTitularColsDefsUserAltered(
			TitularColsDef titularColsDefsUserAltered) {
		getTitularColsDefsUserAltered().add(titularColsDefsUserAltered);
		titularColsDefsUserAltered.setUserAltered(this);

		return titularColsDefsUserAltered;
	}

	public TitularColsDef removeTitularColsDefsUserAltered(
			TitularColsDef titularColsDefsUserAltered) {
		getTitularColsDefsUserAltered().remove(titularColsDefsUserAltered);
		titularColsDefsUserAltered.setUserAltered(null);

		return titularColsDefsUserAltered;
	}

	public List<TitularIsento> getTitularIsentosUserCreated() {
		return this.titularIsentosUserCreated;
	}

	public void setTitularIsentosUserCreated(
			List<TitularIsento> titularIsentosUserCreated) {
		this.titularIsentosUserCreated = titularIsentosUserCreated;
	}

	public TitularIsento addTitularIsentosUserCreated(
			TitularIsento titularIsentosUserCreated) {
		getTitularIsentosUserCreated().add(titularIsentosUserCreated);
		titularIsentosUserCreated.setUserCreated(this);

		return titularIsentosUserCreated;
	}

	public TitularIsento removeTitularIsentosUserCreated(
			TitularIsento titularIsentosUserCreated) {
		getTitularIsentosUserCreated().remove(titularIsentosUserCreated);
		titularIsentosUserCreated.setUserCreated(null);

		return titularIsentosUserCreated;
	}

	public List<TitularIsento> getTitularIsentosUserAltered() {
		return this.titularIsentosUserAltered;
	}

	public void setTitularIsentosUserAltered(
			List<TitularIsento> titularIsentosUserAltered) {
		this.titularIsentosUserAltered = titularIsentosUserAltered;
	}

	public TitularIsento addTitularIsentosUserAltered(
			TitularIsento titularIsentosUserAltered) {
		getTitularIsentosUserAltered().add(titularIsentosUserAltered);
		titularIsentosUserAltered.setUserAltered(this);

		return titularIsentosUserAltered;
	}

	public TitularIsento removeTitularIsentosUserAltered(
			TitularIsento titularIsentosUserAltered) {
		getTitularIsentosUserAltered().remove(titularIsentosUserAltered);
		titularIsentosUserAltered.setUserAltered(null);

		return titularIsentosUserAltered;
	}

	public List<ViewDestination> getViewDestinationsUserCreated() {
		return this.viewDestinationsUserCreated;
	}

	public void setViewDestinationsUserCreated(
			List<ViewDestination> viewDestinationsUserCreated) {
		this.viewDestinationsUserCreated = viewDestinationsUserCreated;
	}

	public ViewDestination addViewDestinationsUserCreated(
			ViewDestination viewDestinationsUserCreated) {
		getViewDestinationsUserCreated().add(viewDestinationsUserCreated);
		viewDestinationsUserCreated.setUserCreated(this);

		return viewDestinationsUserCreated;
	}

	public ViewDestination removeViewDestinationsUserCreated(
			ViewDestination viewDestinationsUserCreated) {
		getViewDestinationsUserCreated().remove(viewDestinationsUserCreated);
		viewDestinationsUserCreated.setUserCreated(null);

		return viewDestinationsUserCreated;
	}

	public List<ViewDestination> getViewDestinationsUserAltered() {
		return this.viewDestinationsUserAltered;
	}

	public void setViewDestinationsUserAltered(
			List<ViewDestination> viewDestinationsUserAltered) {
		this.viewDestinationsUserAltered = viewDestinationsUserAltered;
	}

	public ViewDestination addViewDestinationsUserAltered(
			ViewDestination viewDestinationsUserAltered) {
		getViewDestinationsUserAltered().add(viewDestinationsUserAltered);
		viewDestinationsUserAltered.setUserAltered(this);

		return viewDestinationsUserAltered;
	}

	public ViewDestination removeViewDestinationsUserAltered(
			ViewDestination viewDestinationsUserAltered) {
		getViewDestinationsUserAltered().remove(viewDestinationsUserAltered);
		viewDestinationsUserAltered.setUserAltered(null);

		return viewDestinationsUserAltered;
	}

	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserCreated() {
		return this.viewDestinationColsDefsUserCreated;
	}

	public void setViewDestinationColsDefsUserCreated(
			List<ViewDestinationColsDef> viewDestinationColsDefsUserCreated) {
		this.viewDestinationColsDefsUserCreated = viewDestinationColsDefsUserCreated;
	}

	public ViewDestinationColsDef addViewDestinationColsDefsUserCreated(
			ViewDestinationColsDef viewDestinationColsDefsUserCreated) {
		getViewDestinationColsDefsUserCreated()
				.add(viewDestinationColsDefsUserCreated);
		viewDestinationColsDefsUserCreated.setUserCreated(this);

		return viewDestinationColsDefsUserCreated;
	}

	public ViewDestinationColsDef removeViewDestinationColsDefsUserCreated(
			ViewDestinationColsDef viewDestinationColsDefsUserCreated) {
		getViewDestinationColsDefsUserCreated()
				.remove(viewDestinationColsDefsUserCreated);
		viewDestinationColsDefsUserCreated.setUserCreated(null);

		return viewDestinationColsDefsUserCreated;
	}

	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserAltered() {
		return this.viewDestinationColsDefsUserAltered;
	}

	public void setViewDestinationColsDefsUserAltered(
			List<ViewDestinationColsDef> tbViewDestinationColsDefsUserAltered) {
		this.viewDestinationColsDefsUserAltered = tbViewDestinationColsDefsUserAltered;
	}

	public ViewDestinationColsDef addTbViewDestinationColsDefsUserAltered(
			ViewDestinationColsDef tbViewDestinationColsDefsUserAltered) {
		getViewDestinationColsDefsUserAltered()
				.add(tbViewDestinationColsDefsUserAltered);
		tbViewDestinationColsDefsUserAltered.setUserAltered(this);

		return tbViewDestinationColsDefsUserAltered;
	}

	public ViewDestinationColsDef removeTbViewDestinationColsDefsUserAltered(
			ViewDestinationColsDef tbViewDestinationColsDefsUserAltered) {
		getViewDestinationColsDefsUserAltered()
				.remove(tbViewDestinationColsDefsUserAltered);
		tbViewDestinationColsDefsUserAltered.setUserAltered(null);

		return tbViewDestinationColsDefsUserAltered;
	}

	public List<Parameter> getUserCreatedParameter() {
		return this.userCreatedParameter;
	}

	public void setUserCreatedParameter(List<Parameter> userCreatedParameter) {
		this.userCreatedParameter = userCreatedParameter;
	}

	public Parameter addUserCreatedParameter(Parameter userCreatedParameter) {
		getUserCreatedParameter().add(userCreatedParameter);
		userCreatedParameter.setUserCreated(this);

		return userCreatedParameter;
	}

	public Parameter removeUserCreatedParameter(
			Parameter userCreatedParameter) {
		getUserCreatedParameter().remove(userCreatedParameter);
		userCreatedParameter.setUserCreated(null);

		return userCreatedParameter;
	}

	public List<Parameter> getUserAlteredParameter() {
		return this.userAlteredParameter;
	}

	public void setUserAlteredParameter(List<Parameter> userAlteredParameter) {
		this.userAlteredParameter = userAlteredParameter;
	}

	public Parameter addUserAlteredParameter(Parameter userAlteredParameter) {
		getUserAlteredParameter().add(userAlteredParameter);
		userAlteredParameter.setUserAltered(this);

		return userAlteredParameter;
	}

	public Parameter removeUserAlteredParameter(
			Parameter userAlteredParameter) {
		getUserAlteredParameter().remove(userAlteredParameter);
		userAlteredParameter.setUserAltered(null);

		return userAlteredParameter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDefUserAltered == null) ? 0
				: arquivoInputColsDefUserAltered.hashCode());
		result = prime * result + ((arquivoInputColsDefUserCreated == null) ? 0
				: arquivoInputColsDefUserCreated.hashCode());
		result = prime * result + ((arquivoInputsUserAltered == null) ? 0
				: arquivoInputsUserAltered.hashCode());
		result = prime * result + ((arquivoInputsUserCreated == null) ? 0
				: arquivoInputsUserCreated.hashCode());
		result = prime * result
				+ ((arquivoOutputSheetColsDefUserAltered == null) ? 0
						: arquivoOutputSheetColsDefUserAltered.hashCode());
		result = prime * result
				+ ((arquivoOutputSheetColsDefUserCreated == null) ? 0
						: arquivoOutputSheetColsDefUserCreated.hashCode());
		result = prime * result + ((arquivoOutputSheetsUserAltered == null) ? 0
				: arquivoOutputSheetsUserAltered.hashCode());
		result = prime * result + ((arquivoOutputSheetsUserCreated == null) ? 0
				: arquivoOutputSheetsUserCreated.hashCode());
		result = prime * result + ((arquivoOutputsUserAltered == null) ? 0
				: arquivoOutputsUserAltered.hashCode());
		result = prime * result + ((arquivoOutputsUserCreated == null) ? 0
				: arquivoOutputsUserCreated.hashCode());
		result = prime * result + ((contratosUserAltered == null) ? 0
				: contratosUserAltered.hashCode());
		result = prime * result + ((contratosUserCreated == null) ? 0
				: contratosUserCreated.hashCode());
		result = prime * result + ((dependenteColsDefsUserAltered == null) ? 0
				: dependenteColsDefsUserAltered.hashCode());
		result = prime * result + ((dependenteColsDefsUserCreated == null) ? 0
				: dependenteColsDefsUserCreated.hashCode());
		result = prime * result + ((dependenteIsentosUserAltered == null) ? 0
				: dependenteIsentosUserAltered.hashCode());
		result = prime * result + ((dependenteIsentosUserCreated == null) ? 0
				: dependenteIsentosUserCreated.hashCode());
		result = prime * result + ((dependentesUserAltered == null) ? 0
				: dependentesUserAltered.hashCode());
		result = prime * result + ((dependentesUserCreated == null) ? 0
				: dependentesUserCreated.hashCode());
		result = prime * result
				+ ((descrName == null) ? 0 : descrName.hashCode());
		result = prime * result + ((empresasUserAltered == null) ? 0
				: empresasUserAltered.hashCode());
		result = prime * result + ((empresasUserCreated == null) ? 0
				: empresasUserCreated.hashCode());
		result = prime * result + ((inputDependentesUserAltered == null) ? 0
				: inputDependentesUserAltered.hashCode());
		result = prime * result + ((inputDependentesUserCreated == null) ? 0
				: inputDependentesUserCreated.hashCode());
		result = prime * result + ((inputLancamentosUserAltered == null) ? 0
				: inputLancamentosUserAltered.hashCode());
		result = prime * result + ((inputLancamentosUserCreated == null) ? 0
				: inputLancamentosUserCreated.hashCode());
		result = prime * result + ((inputTitularsUserAltered == null) ? 0
				: inputTitularsUserAltered.hashCode());
		result = prime * result + ((inputTitularsUserCreated == null) ? 0
				: inputTitularsUserCreated.hashCode());
		result = prime * result + ((lancamentoColsDefsUserAltered == null) ? 0
				: lancamentoColsDefsUserAltered.hashCode());
		result = prime * result + ((lancamentoColsDefsUserCreated == null) ? 0
				: lancamentoColsDefsUserCreated.hashCode());
		result = prime * result + ((lancamentosUserAltered == null) ? 0
				: lancamentosUserAltered.hashCode());
		result = prime * result + ((lancamentosUserCreated == null) ? 0
				: lancamentosUserCreated.hashCode());
		result = prime * result
				+ ((nameLogin == null) ? 0 : nameLogin.hashCode());
		result = prime * result + ((operadorasUserAltered == null) ? 0
				: operadorasUserAltered.hashCode());
		result = prime * result + ((operadorasUserCreated == null) ? 0
				: operadorasUserCreated.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((regraFieldsUserAltered == null) ? 0
				: regraFieldsUserAltered.hashCode());
		result = prime * result + ((regraFieldsUserCreated == null) ? 0
				: regraFieldsUserCreated.hashCode());
		result = prime * result + ((regraOperationsUserAltered == null) ? 0
				: regraOperationsUserAltered.hashCode());
		result = prime * result + ((regraOperationsUserCreated == null) ? 0
				: regraOperationsUserCreated.hashCode());
		result = prime * result + ((regraValorsUserAltered == null) ? 0
				: regraValorsUserAltered.hashCode());
		result = prime * result + ((regraValorsUserCreated == null) ? 0
				: regraValorsUserCreated.hashCode());
		result = prime * result + ((regrasUserAltered == null) ? 0
				: regrasUserAltered.hashCode());
		result = prime * result + ((regrasUserCreated == null) ? 0
				: regrasUserCreated.hashCode());
		result = prime * result + ((titularColsDefsUserAltered == null) ? 0
				: titularColsDefsUserAltered.hashCode());
		result = prime * result + ((titularColsDefsUserCreated == null) ? 0
				: titularColsDefsUserCreated.hashCode());
		result = prime * result + ((titularIsentosUserAltered == null) ? 0
				: titularIsentosUserAltered.hashCode());
		result = prime * result + ((titularIsentosUserCreated == null) ? 0
				: titularIsentosUserCreated.hashCode());
		result = prime * result + ((titularsUserAltered == null) ? 0
				: titularsUserAltered.hashCode());
		result = prime * result + ((titularsUserCreated == null) ? 0
				: titularsUserCreated.hashCode());
		result = prime * result
				+ ((tpStatus == null) ? 0 : tpStatus.hashCode());
		result = prime * result + ((userAlteredParameter == null) ? 0
				: userAlteredParameter.hashCode());
		result = prime * result + ((userCreatedParameter == null) ? 0
				: userCreatedParameter.hashCode());
		result = prime * result
				+ ((viewDestinationColsDefsUserAltered == null) ? 0
						: viewDestinationColsDefsUserAltered.hashCode());
		result = prime * result
				+ ((viewDestinationColsDefsUserCreated == null) ? 0
						: viewDestinationColsDefsUserCreated.hashCode());
		result = prime * result + ((viewDestinationsUserAltered == null) ? 0
				: viewDestinationsUserAltered.hashCode());
		result = prime * result + ((viewDestinationsUserCreated == null) ? 0
				: viewDestinationsUserCreated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (arquivoInputColsDefUserAltered == null) {
			if (other.arquivoInputColsDefUserAltered != null)
				return false;
		} else if (!arquivoInputColsDefUserAltered
				.equals(other.arquivoInputColsDefUserAltered))
			return false;
		if (arquivoInputColsDefUserCreated == null) {
			if (other.arquivoInputColsDefUserCreated != null)
				return false;
		} else if (!arquivoInputColsDefUserCreated
				.equals(other.arquivoInputColsDefUserCreated))
			return false;
		if (arquivoInputsUserAltered == null) {
			if (other.arquivoInputsUserAltered != null)
				return false;
		} else if (!arquivoInputsUserAltered
				.equals(other.arquivoInputsUserAltered))
			return false;
		if (arquivoInputsUserCreated == null) {
			if (other.arquivoInputsUserCreated != null)
				return false;
		} else if (!arquivoInputsUserCreated
				.equals(other.arquivoInputsUserCreated))
			return false;
		if (arquivoOutputSheetColsDefUserAltered == null) {
			if (other.arquivoOutputSheetColsDefUserAltered != null)
				return false;
		} else if (!arquivoOutputSheetColsDefUserAltered
				.equals(other.arquivoOutputSheetColsDefUserAltered))
			return false;
		if (arquivoOutputSheetColsDefUserCreated == null) {
			if (other.arquivoOutputSheetColsDefUserCreated != null)
				return false;
		} else if (!arquivoOutputSheetColsDefUserCreated
				.equals(other.arquivoOutputSheetColsDefUserCreated))
			return false;
		if (arquivoOutputSheetsUserAltered == null) {
			if (other.arquivoOutputSheetsUserAltered != null)
				return false;
		} else if (!arquivoOutputSheetsUserAltered
				.equals(other.arquivoOutputSheetsUserAltered))
			return false;
		if (arquivoOutputSheetsUserCreated == null) {
			if (other.arquivoOutputSheetsUserCreated != null)
				return false;
		} else if (!arquivoOutputSheetsUserCreated
				.equals(other.arquivoOutputSheetsUserCreated))
			return false;
		if (arquivoOutputsUserAltered == null) {
			if (other.arquivoOutputsUserAltered != null)
				return false;
		} else if (!arquivoOutputsUserAltered
				.equals(other.arquivoOutputsUserAltered))
			return false;
		if (arquivoOutputsUserCreated == null) {
			if (other.arquivoOutputsUserCreated != null)
				return false;
		} else if (!arquivoOutputsUserCreated
				.equals(other.arquivoOutputsUserCreated))
			return false;
		if (contratosUserAltered == null) {
			if (other.contratosUserAltered != null)
				return false;
		} else if (!contratosUserAltered.equals(other.contratosUserAltered))
			return false;
		if (contratosUserCreated == null) {
			if (other.contratosUserCreated != null)
				return false;
		} else if (!contratosUserCreated.equals(other.contratosUserCreated))
			return false;
		if (dependenteColsDefsUserAltered == null) {
			if (other.dependenteColsDefsUserAltered != null)
				return false;
		} else if (!dependenteColsDefsUserAltered
				.equals(other.dependenteColsDefsUserAltered))
			return false;
		if (dependenteColsDefsUserCreated == null) {
			if (other.dependenteColsDefsUserCreated != null)
				return false;
		} else if (!dependenteColsDefsUserCreated
				.equals(other.dependenteColsDefsUserCreated))
			return false;
		if (dependenteIsentosUserAltered == null) {
			if (other.dependenteIsentosUserAltered != null)
				return false;
		} else if (!dependenteIsentosUserAltered
				.equals(other.dependenteIsentosUserAltered))
			return false;
		if (dependenteIsentosUserCreated == null) {
			if (other.dependenteIsentosUserCreated != null)
				return false;
		} else if (!dependenteIsentosUserCreated
				.equals(other.dependenteIsentosUserCreated))
			return false;
		if (dependentesUserAltered == null) {
			if (other.dependentesUserAltered != null)
				return false;
		} else if (!dependentesUserAltered.equals(other.dependentesUserAltered))
			return false;
		if (dependentesUserCreated == null) {
			if (other.dependentesUserCreated != null)
				return false;
		} else if (!dependentesUserCreated.equals(other.dependentesUserCreated))
			return false;
		if (descrName == null) {
			if (other.descrName != null)
				return false;
		} else if (!descrName.equals(other.descrName))
			return false;
		if (empresasUserAltered == null) {
			if (other.empresasUserAltered != null)
				return false;
		} else if (!empresasUserAltered.equals(other.empresasUserAltered))
			return false;
		if (empresasUserCreated == null) {
			if (other.empresasUserCreated != null)
				return false;
		} else if (!empresasUserCreated.equals(other.empresasUserCreated))
			return false;
		if (inputDependentesUserAltered == null) {
			if (other.inputDependentesUserAltered != null)
				return false;
		} else if (!inputDependentesUserAltered
				.equals(other.inputDependentesUserAltered))
			return false;
		if (inputDependentesUserCreated == null) {
			if (other.inputDependentesUserCreated != null)
				return false;
		} else if (!inputDependentesUserCreated
				.equals(other.inputDependentesUserCreated))
			return false;
		if (inputLancamentosUserAltered == null) {
			if (other.inputLancamentosUserAltered != null)
				return false;
		} else if (!inputLancamentosUserAltered
				.equals(other.inputLancamentosUserAltered))
			return false;
		if (inputLancamentosUserCreated == null) {
			if (other.inputLancamentosUserCreated != null)
				return false;
		} else if (!inputLancamentosUserCreated
				.equals(other.inputLancamentosUserCreated))
			return false;
		if (inputTitularsUserAltered == null) {
			if (other.inputTitularsUserAltered != null)
				return false;
		} else if (!inputTitularsUserAltered
				.equals(other.inputTitularsUserAltered))
			return false;
		if (inputTitularsUserCreated == null) {
			if (other.inputTitularsUserCreated != null)
				return false;
		} else if (!inputTitularsUserCreated
				.equals(other.inputTitularsUserCreated))
			return false;
		if (lancamentoColsDefsUserAltered == null) {
			if (other.lancamentoColsDefsUserAltered != null)
				return false;
		} else if (!lancamentoColsDefsUserAltered
				.equals(other.lancamentoColsDefsUserAltered))
			return false;
		if (lancamentoColsDefsUserCreated == null) {
			if (other.lancamentoColsDefsUserCreated != null)
				return false;
		} else if (!lancamentoColsDefsUserCreated
				.equals(other.lancamentoColsDefsUserCreated))
			return false;
		if (lancamentosUserAltered == null) {
			if (other.lancamentosUserAltered != null)
				return false;
		} else if (!lancamentosUserAltered.equals(other.lancamentosUserAltered))
			return false;
		if (lancamentosUserCreated == null) {
			if (other.lancamentosUserCreated != null)
				return false;
		} else if (!lancamentosUserCreated.equals(other.lancamentosUserCreated))
			return false;
		if (nameLogin == null) {
			if (other.nameLogin != null)
				return false;
		} else if (!nameLogin.equals(other.nameLogin))
			return false;
		if (operadorasUserAltered == null) {
			if (other.operadorasUserAltered != null)
				return false;
		} else if (!operadorasUserAltered.equals(other.operadorasUserAltered))
			return false;
		if (operadorasUserCreated == null) {
			if (other.operadorasUserCreated != null)
				return false;
		} else if (!operadorasUserCreated.equals(other.operadorasUserCreated))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (regraFieldsUserAltered == null) {
			if (other.regraFieldsUserAltered != null)
				return false;
		} else if (!regraFieldsUserAltered.equals(other.regraFieldsUserAltered))
			return false;
		if (regraFieldsUserCreated == null) {
			if (other.regraFieldsUserCreated != null)
				return false;
		} else if (!regraFieldsUserCreated.equals(other.regraFieldsUserCreated))
			return false;
		if (regraOperationsUserAltered == null) {
			if (other.regraOperationsUserAltered != null)
				return false;
		} else if (!regraOperationsUserAltered
				.equals(other.regraOperationsUserAltered))
			return false;
		if (regraOperationsUserCreated == null) {
			if (other.regraOperationsUserCreated != null)
				return false;
		} else if (!regraOperationsUserCreated
				.equals(other.regraOperationsUserCreated))
			return false;
		if (regraValorsUserAltered == null) {
			if (other.regraValorsUserAltered != null)
				return false;
		} else if (!regraValorsUserAltered.equals(other.regraValorsUserAltered))
			return false;
		if (regraValorsUserCreated == null) {
			if (other.regraValorsUserCreated != null)
				return false;
		} else if (!regraValorsUserCreated.equals(other.regraValorsUserCreated))
			return false;
		if (regrasUserAltered == null) {
			if (other.regrasUserAltered != null)
				return false;
		} else if (!regrasUserAltered.equals(other.regrasUserAltered))
			return false;
		if (regrasUserCreated == null) {
			if (other.regrasUserCreated != null)
				return false;
		} else if (!regrasUserCreated.equals(other.regrasUserCreated))
			return false;
		if (titularColsDefsUserAltered == null) {
			if (other.titularColsDefsUserAltered != null)
				return false;
		} else if (!titularColsDefsUserAltered
				.equals(other.titularColsDefsUserAltered))
			return false;
		if (titularColsDefsUserCreated == null) {
			if (other.titularColsDefsUserCreated != null)
				return false;
		} else if (!titularColsDefsUserCreated
				.equals(other.titularColsDefsUserCreated))
			return false;
		if (titularIsentosUserAltered == null) {
			if (other.titularIsentosUserAltered != null)
				return false;
		} else if (!titularIsentosUserAltered
				.equals(other.titularIsentosUserAltered))
			return false;
		if (titularIsentosUserCreated == null) {
			if (other.titularIsentosUserCreated != null)
				return false;
		} else if (!titularIsentosUserCreated
				.equals(other.titularIsentosUserCreated))
			return false;
		if (titularsUserAltered == null) {
			if (other.titularsUserAltered != null)
				return false;
		} else if (!titularsUserAltered.equals(other.titularsUserAltered))
			return false;
		if (titularsUserCreated == null) {
			if (other.titularsUserCreated != null)
				return false;
		} else if (!titularsUserCreated.equals(other.titularsUserCreated))
			return false;
		if (tpStatus != other.tpStatus)
			return false;
		if (userAlteredParameter == null) {
			if (other.userAlteredParameter != null)
				return false;
		} else if (!userAlteredParameter.equals(other.userAlteredParameter))
			return false;
		if (userCreatedParameter == null) {
			if (other.userCreatedParameter != null)
				return false;
		} else if (!userCreatedParameter.equals(other.userCreatedParameter))
			return false;
		if (viewDestinationColsDefsUserAltered == null) {
			if (other.viewDestinationColsDefsUserAltered != null)
				return false;
		} else if (!viewDestinationColsDefsUserAltered
				.equals(other.viewDestinationColsDefsUserAltered))
			return false;
		if (viewDestinationColsDefsUserCreated == null) {
			if (other.viewDestinationColsDefsUserCreated != null)
				return false;
		} else if (!viewDestinationColsDefsUserCreated
				.equals(other.viewDestinationColsDefsUserCreated))
			return false;
		if (viewDestinationsUserAltered == null) {
			if (other.viewDestinationsUserAltered != null)
				return false;
		} else if (!viewDestinationsUserAltered
				.equals(other.viewDestinationsUserAltered))
			return false;
		if (viewDestinationsUserCreated == null) {
			if (other.viewDestinationsUserCreated != null)
				return false;
		} else if (!viewDestinationsUserCreated
				.equals(other.viewDestinationsUserCreated))
			return false;
		return true;
	}

}