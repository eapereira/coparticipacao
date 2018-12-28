package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
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
	private List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefUserCreated;
	private List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefUserAltered;
	private List<ArquivoOutput> arquivoOutputsUserCreated;
	private List<ArquivoOutput> arquivoOutputsUserAltered;
	private List<ArquivoOutputSheet> arquivoOutputSheetsUserCreated;
	private List<ArquivoOutputSheet> arquivoOutputSheetsUserAltered;
	private List<Contrato> contratosUserCreated;
	private List<Contrato> contratosUserAltered;
	private List<Dependente> dependentesUserCreated;
	private List<Dependente> dependentesUserAltered;
	private List<DependenteIsento> dependenteIsentosUserCreated;
	private List<DependenteIsento> dependenteIsentosUserAltered;
	private List<Empresa> empresasUserCreated;
	private List<Empresa> empresasUserAltered;
	private List<Lancamento> lancamentosUserCreated;
	private List<Lancamento> lancamentosUserAltered;

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
	private List<TitularIsento> titularIsentosUserCreated;
	private List<TitularIsento> titularIsentosUserAltered;
	private List<ViewDestination> viewDestinationsUserCreated;
	private List<ViewDestination> viewDestinationsUserAltered;
	private List<ViewDestinationColsDef> viewDestinationColsDefsUserCreated;
	private List<ViewDestinationColsDef> viewDestinationColsDefsUserAltered;

	private List<UserRole> userRoles;

	private List<ArquivoExecucao> arquivoExecucaoUserCreated;
	private List<ArquivoExecucao> arquivoExecucaoUserAltered;

	private List<Execucao> execucaoUserCreated;
	private List<Execucao> execucaoUserAltered;

	public User() {
		userRoles = new ArrayList<>();

		execucaoUserAltered = new ArrayList<>();
		execucaoUserCreated = new ArrayList<>();
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

	public void setArquivoInputsUserCreated(List<ArquivoInput> arquivoInputsUserCreated) {
		this.arquivoInputsUserCreated = arquivoInputsUserCreated;
	}

	public ArquivoInput addArquivoInputsUserCreated(ArquivoInput arquivoInputsUserCreated) {
		getArquivoInputsUserCreated().add(arquivoInputsUserCreated);
		arquivoInputsUserCreated.setUserCreated(this);

		return arquivoInputsUserCreated;
	}

	public ArquivoInput removeArquivoInputsUserCreated(ArquivoInput arquivoInputsUserCreated) {
		getArquivoInputsUserCreated().remove(arquivoInputsUserCreated);
		arquivoInputsUserCreated.setUserCreated(null);

		return arquivoInputsUserCreated;
	}

	public List<ArquivoInput> getArquivoInputsUserAltered() {
		return this.arquivoInputsUserAltered;
	}

	public void setArquivoInputsUserAltered(List<ArquivoInput> arquivoInputsUserAltered) {
		this.arquivoInputsUserAltered = arquivoInputsUserAltered;
	}

	public ArquivoInput addArquivoInputsUserAltered(ArquivoInput arquivoInputsUserAltered) {
		getArquivoInputsUserAltered().add(arquivoInputsUserAltered);
		arquivoInputsUserAltered.setUserAltered(this);

		return arquivoInputsUserAltered;
	}

	public ArquivoInput removeArquivoInputsUserAltered(ArquivoInput arquivoInputsUserAltered) {
		getArquivoInputsUserAltered().remove(arquivoInputsUserAltered);
		arquivoInputsUserAltered.setUserAltered(null);

		return arquivoInputsUserAltered;
	}

	public List<ArquivoInputSheetColsDef> getArquivoInputSheetColsDefUserCreated() {
		return this.arquivoInputSheetColsDefUserCreated;
	}

	public void setArquivoInputSheetColsDefUserCreated(
			List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefUserCreated) {
		this.arquivoInputSheetColsDefUserCreated = arquivoInputSheetColsDefUserCreated;
	}

	public ArquivoInputSheetColsDef addArquivoInputSheetColsDefUserCreated(
			ArquivoInputSheetColsDef arquivoInputSheetColsDefUserCreated) {
		getArquivoInputSheetColsDefUserCreated().add(arquivoInputSheetColsDefUserCreated);
		arquivoInputSheetColsDefUserCreated.setUserCreated(this);

		return arquivoInputSheetColsDefUserCreated;
	}

	public ArquivoInputSheetColsDef removeArquivoInputSheetColsDefUserCreated(
			ArquivoInputSheetColsDef arquivoInputSheetColsDefUserCreated) {
		getArquivoInputSheetColsDefUserCreated().remove(arquivoInputSheetColsDefUserCreated);
		arquivoInputSheetColsDefUserCreated.setUserCreated(null);

		return arquivoInputSheetColsDefUserCreated;
	}

	public List<ArquivoInputSheetColsDef> getArquivoInputSheetColsDefUserAltered() {
		return this.arquivoInputSheetColsDefUserAltered;
	}

	public void setArquivoInputSheetColsDefUserAltered(
			List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefUserAltered) {
		this.arquivoInputSheetColsDefUserAltered = arquivoInputSheetColsDefUserAltered;
	}

	public ArquivoInputSheetColsDef addArquivoInputSheetColsDefUserAltered(
			ArquivoInputSheetColsDef arquivoInputSheetColsDefUserAltered) {
		getArquivoInputSheetColsDefUserAltered().add(arquivoInputSheetColsDefUserAltered);
		arquivoInputSheetColsDefUserAltered.setUserAltered(this);

		return arquivoInputSheetColsDefUserAltered;
	}

	public ArquivoInputSheetColsDef removeArquivoInputSheetColsDefUserAltered(
			ArquivoInputSheetColsDef arquivoInputSheetColsDefUserAltered) {
		getArquivoInputSheetColsDefUserAltered().remove(arquivoInputSheetColsDefUserAltered);
		arquivoInputSheetColsDefUserAltered.setUserAltered(null);

		return arquivoInputSheetColsDefUserAltered;
	}

	public List<ArquivoOutput> getArquivoOutputsUserCreated() {
		return this.arquivoOutputsUserCreated;
	}

	public void setArquivoOutputsUserCreated(List<ArquivoOutput> arquivoOutputsUserCreated) {
		this.arquivoOutputsUserCreated = arquivoOutputsUserCreated;
	}

	public ArquivoOutput addArquivoOutputsUserCreated(ArquivoOutput arquivoOutputsUserCreated) {
		getArquivoOutputsUserCreated().add(arquivoOutputsUserCreated);
		arquivoOutputsUserCreated.setUserCreated(this);

		return arquivoOutputsUserCreated;
	}

	public ArquivoOutput removeArquivoOutputsUserCreated(ArquivoOutput arquivoOutputsUserCreated) {
		getArquivoOutputsUserCreated().remove(arquivoOutputsUserCreated);
		arquivoOutputsUserCreated.setUserCreated(null);

		return arquivoOutputsUserCreated;
	}

	public List<ArquivoOutput> getArquivoOutputsUserAltered() {
		return this.arquivoOutputsUserAltered;
	}

	public void setArquivoOutputsUserAltered(List<ArquivoOutput> arquivoOutputsUserAltered) {
		this.arquivoOutputsUserAltered = arquivoOutputsUserAltered;
	}

	public ArquivoOutput addArquivoOutputsUserAltered(ArquivoOutput arquivoOutputsUserAltered) {
		getArquivoOutputsUserAltered().add(arquivoOutputsUserAltered);
		arquivoOutputsUserAltered.setUserAltered(this);

		return arquivoOutputsUserAltered;
	}

	public ArquivoOutput removeArquivoOutputsUserAltered(ArquivoOutput arquivoOutputsUserAltered) {
		getArquivoOutputsUserAltered().remove(arquivoOutputsUserAltered);
		arquivoOutputsUserAltered.setUserAltered(null);

		return arquivoOutputsUserAltered;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserCreated() {
		return this.arquivoOutputSheetsUserCreated;
	}

	public void setArquivoOutputSheetsUserCreated(List<ArquivoOutputSheet> arquivoOutputSheetsUserCreated) {
		this.arquivoOutputSheetsUserCreated = arquivoOutputSheetsUserCreated;
	}

	public ArquivoOutputSheet addArquivoOutputSheetsUserCreated(ArquivoOutputSheet arquivoOutputSheetsUserCreated) {
		getArquivoOutputSheetsUserCreated().add(arquivoOutputSheetsUserCreated);
		arquivoOutputSheetsUserCreated.setUserCreated(this);

		return arquivoOutputSheetsUserCreated;
	}

	public ArquivoOutputSheet removeArquivoOutputSheetsUserCreated(ArquivoOutputSheet arquivoOutputSheetsUserCreated) {
		getArquivoOutputSheetsUserCreated().remove(arquivoOutputSheetsUserCreated);
		arquivoOutputSheetsUserCreated.setUserCreated(null);

		return arquivoOutputSheetsUserCreated;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserAltered() {
		return this.arquivoOutputSheetsUserAltered;
	}

	public void setArquivoOutputSheetsUserAltered(List<ArquivoOutputSheet> arquivoOutputSheetsUserAltered) {
		this.arquivoOutputSheetsUserAltered = arquivoOutputSheetsUserAltered;
	}

	public ArquivoOutputSheet addArquivoOutputSheetsUserAltered(ArquivoOutputSheet arquivoOutputSheetsUserAltered) {
		getArquivoOutputSheetsUserAltered().add(arquivoOutputSheetsUserAltered);
		arquivoOutputSheetsUserAltered.setUserAltered(this);

		return arquivoOutputSheetsUserAltered;
	}

	public ArquivoOutputSheet removeArquivoOutputSheetsUserAltered(ArquivoOutputSheet arquivoOutputSheetsUserAltered) {
		getArquivoOutputSheetsUserAltered().remove(arquivoOutputSheetsUserAltered);
		arquivoOutputSheetsUserAltered.setUserAltered(null);

		return arquivoOutputSheetsUserAltered;
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

	public void setDependentesUserCreated(List<Dependente> dependentesUserCreated) {
		this.dependentesUserCreated = dependentesUserCreated;
	}

	public Dependente addDependentesUserCreated(Dependente dependentesUserCreated) {
		getDependentesUserCreated().add(dependentesUserCreated);
		dependentesUserCreated.setUserCreated(this);

		return dependentesUserCreated;
	}

	public Dependente removeDependentesUserCreated(Dependente dependentesUserCreated) {
		getDependentesUserCreated().remove(dependentesUserCreated);
		dependentesUserCreated.setUserCreated(null);

		return dependentesUserCreated;
	}

	public List<Dependente> getDependentesUserAltered() {
		return this.dependentesUserAltered;
	}

	public void setDependentesUserAltered(List<Dependente> dependentesUserAltered) {
		this.dependentesUserAltered = dependentesUserAltered;
	}

	public Dependente addDependentesUserAltered(Dependente dependentesUserAltered) {
		getDependentesUserAltered().add(dependentesUserAltered);
		dependentesUserAltered.setUserAltered(this);

		return dependentesUserAltered;
	}

	public Dependente removeDependentesUserAltered(Dependente dependentesUserAltered) {
		getDependentesUserAltered().remove(dependentesUserAltered);
		dependentesUserAltered.setUserAltered(null);

		return dependentesUserAltered;
	}

	public List<DependenteIsento> getDependenteIsentosUserCreated() {
		return this.dependenteIsentosUserCreated;
	}

	public void setDependenteIsentosUserCreated(List<DependenteIsento> dependenteIsentosUserCreated) {
		this.dependenteIsentosUserCreated = dependenteIsentosUserCreated;
	}

	public DependenteIsento addDependenteIsentosUserCreated(DependenteIsento dependenteIsentosUserCreated) {
		getDependenteIsentosUserCreated().add(dependenteIsentosUserCreated);
		dependenteIsentosUserCreated.setUserCreated(this);

		return dependenteIsentosUserCreated;
	}

	public DependenteIsento removeDependenteIsentosUserCreated(DependenteIsento dependenteIsentosUserCreated) {
		getDependenteIsentosUserCreated().remove(dependenteIsentosUserCreated);
		dependenteIsentosUserCreated.setUserCreated(null);

		return dependenteIsentosUserCreated;
	}

	public List<DependenteIsento> getDependenteIsentosUserAltered() {
		return this.dependenteIsentosUserAltered;
	}

	public void setDependenteIsentosUserAltered(List<DependenteIsento> dependenteIsentosUserAltered) {
		this.dependenteIsentosUserAltered = dependenteIsentosUserAltered;
	}

	public DependenteIsento addDependenteIsentosUserAltered(DependenteIsento dependenteIsentosUserAltered) {
		getDependenteIsentosUserAltered().add(dependenteIsentosUserAltered);
		dependenteIsentosUserAltered.setUserAltered(this);

		return dependenteIsentosUserAltered;
	}

	public DependenteIsento removeDependenteIsentosUserAltered(DependenteIsento dependenteIsentosUserAltered) {
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

	public List<Lancamento> getLancamentosUserCreated() {
		return this.lancamentosUserCreated;
	}

	public void setLancamentosUserCreated(List<Lancamento> lancamentosUserCreated) {
		this.lancamentosUserCreated = lancamentosUserCreated;
	}

	public Lancamento addLancamentosUserCreated(Lancamento lancamentosUserCreated) {
		getLancamentosUserCreated().add(lancamentosUserCreated);
		lancamentosUserCreated.setUserCreated(this);

		return lancamentosUserCreated;
	}

	public Lancamento removeLancamentosUserCreated(Lancamento lancamentosUserCreated) {
		getLancamentosUserCreated().remove(lancamentosUserCreated);
		lancamentosUserCreated.setUserCreated(null);

		return lancamentosUserCreated;
	}

	public List<Lancamento> getLancamentosUserAltered() {
		return this.lancamentosUserAltered;
	}

	public void setLancamentosUserAltered(List<Lancamento> lancamentosUserAltered) {
		this.lancamentosUserAltered = lancamentosUserAltered;
	}

	public Lancamento addLancamentosUserAltered(Lancamento lancamentosUserAltered) {
		getLancamentosUserAltered().add(lancamentosUserAltered);
		lancamentosUserAltered.setUserAltered(this);

		return lancamentosUserAltered;
	}

	public Lancamento removeLancamentosUserAltered(Lancamento lancamentosUserAltered) {
		getLancamentosUserAltered().remove(lancamentosUserAltered);
		lancamentosUserAltered.setUserAltered(null);

		return lancamentosUserAltered;
	}

	public List<Operadora> getOperadorasUserCreated() {
		return this.operadorasUserCreated;
	}

	public void setOperadorasUserCreated(List<Operadora> operadorasUserCreated) {
		this.operadorasUserCreated = operadorasUserCreated;
	}

	public Operadora addOperadorasUserCreated(Operadora operadorasUserCreated) {
		getOperadorasUserCreated().add(operadorasUserCreated);
		operadorasUserCreated.setUserCreated(this);

		return operadorasUserCreated;
	}

	public Operadora removeOperadorasUserCreated(Operadora operadorasUserCreated) {
		getOperadorasUserCreated().remove(operadorasUserCreated);
		operadorasUserCreated.setUserCreated(null);

		return operadorasUserCreated;
	}

	public List<Operadora> getOperadorasUserAltered() {
		return this.operadorasUserAltered;
	}

	public void setOperadorasUserAltered(List<Operadora> operadorasUserAltered) {
		this.operadorasUserAltered = operadorasUserAltered;
	}

	public Operadora addOperadorasUserAltered(Operadora operadorasUserAltered) {
		getOperadorasUserAltered().add(operadorasUserAltered);
		operadorasUserAltered.setUserAltered(this);

		return operadorasUserAltered;
	}

	public Operadora removeOperadorasUserAltered(Operadora operadorasUserAltered) {
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

	public void setRegraFieldsUserCreated(List<RegraField> regraFieldsUserCreated) {
		this.regraFieldsUserCreated = regraFieldsUserCreated;
	}

	public RegraField addRegraFieldsUserCreated(RegraField regraFieldsUserCreated) {
		getRegraFieldsUserCreated().add(regraFieldsUserCreated);
		regraFieldsUserCreated.setUserCreated(this);

		return regraFieldsUserCreated;
	}

	public RegraField removeRegraFieldsUserCreated(RegraField regraFieldsUserCreated) {
		getRegraFieldsUserCreated().remove(regraFieldsUserCreated);
		regraFieldsUserCreated.setUserCreated(null);

		return regraFieldsUserCreated;
	}

	public List<RegraField> getRegraFieldsUserAltered() {
		return this.regraFieldsUserAltered;
	}

	public void setRegraFieldsUserAltered(List<RegraField> regraFieldsUserAltered) {
		this.regraFieldsUserAltered = regraFieldsUserAltered;
	}

	public RegraField addRegraFieldsUserAltered(RegraField regraFieldsUserAltered) {
		getRegraFieldsUserAltered().add(regraFieldsUserAltered);
		regraFieldsUserAltered.setUserAltered(this);

		return regraFieldsUserAltered;
	}

	public RegraField removeRegraFieldsUserAltered(RegraField regraFieldsUserAltered) {
		getRegraFieldsUserAltered().remove(regraFieldsUserAltered);
		regraFieldsUserAltered.setUserAltered(null);

		return regraFieldsUserAltered;
	}

	public List<RegraOperation> getRegraOperationsUserCreated() {
		return this.regraOperationsUserCreated;
	}

	public void setRegraOperationsUserCreated(List<RegraOperation> regraOperationsUserCreated) {
		this.regraOperationsUserCreated = regraOperationsUserCreated;
	}

	public RegraOperation addRegraOperationsUserCreated(RegraOperation regraOperationsUserCreated) {
		getRegraOperationsUserCreated().add(regraOperationsUserCreated);
		regraOperationsUserCreated.setUserCreated(this);

		return regraOperationsUserCreated;
	}

	public RegraOperation removeRegraOperationsUserCreated(RegraOperation regraOperationsUserCreated) {
		getRegraOperationsUserCreated().remove(regraOperationsUserCreated);
		regraOperationsUserCreated.setUserCreated(null);

		return regraOperationsUserCreated;
	}

	public List<RegraOperation> getRegraOperationsUserAltered() {
		return this.regraOperationsUserAltered;
	}

	public void setRegraOperationsUserAltered(List<RegraOperation> regraOperationsUserAltered) {
		this.regraOperationsUserAltered = regraOperationsUserAltered;
	}

	public RegraOperation addRegraOperationsUserAltered(RegraOperation regraOperationsUserAltered) {
		getRegraOperationsUserAltered().add(regraOperationsUserAltered);
		regraOperationsUserAltered.setUserAltered(this);

		return regraOperationsUserAltered;
	}

	public RegraOperation removeRegraOperationsUserAltered(RegraOperation regraOperationsUserAltered) {
		getRegraOperationsUserAltered().remove(regraOperationsUserAltered);
		regraOperationsUserAltered.setUserAltered(null);

		return regraOperationsUserAltered;
	}

	public List<RegraValor> getRegraValorsUserCreated() {
		return this.regraValorsUserCreated;
	}

	public void setRegraValorsUserCreated(List<RegraValor> regraValorsUserCreated) {
		this.regraValorsUserCreated = regraValorsUserCreated;
	}

	public RegraValor addRegraValorsUserCreated(RegraValor regraValorsUserCreated) {
		getRegraValorsUserCreated().add(regraValorsUserCreated);
		regraValorsUserCreated.setUserCreated(this);

		return regraValorsUserCreated;
	}

	public RegraValor removeRegraValorsUserCreated(RegraValor regraValorsUserCreated) {
		getRegraValorsUserCreated().remove(regraValorsUserCreated);
		regraValorsUserCreated.setUserCreated(null);

		return regraValorsUserCreated;
	}

	public List<RegraValor> getRegraValorsUserAltered() {
		return this.regraValorsUserAltered;
	}

	public void setRegraValorsUserAltered(List<RegraValor> regraValorsUserAltered) {
		this.regraValorsUserAltered = regraValorsUserAltered;
	}

	public RegraValor addRegraValorsUserAltered(RegraValor regraValorsUserAltered) {
		getRegraValorsUserAltered().add(regraValorsUserAltered);
		regraValorsUserAltered.setUserAltered(this);

		return regraValorsUserAltered;
	}

	public RegraValor removeRegraValorsUserAltered(RegraValor regraValorsUserAltered) {
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

	public List<TitularIsento> getTitularIsentosUserCreated() {
		return this.titularIsentosUserCreated;
	}

	public void setTitularIsentosUserCreated(List<TitularIsento> titularIsentosUserCreated) {
		this.titularIsentosUserCreated = titularIsentosUserCreated;
	}

	public TitularIsento addTitularIsentosUserCreated(TitularIsento titularIsentosUserCreated) {
		getTitularIsentosUserCreated().add(titularIsentosUserCreated);
		titularIsentosUserCreated.setUserCreated(this);

		return titularIsentosUserCreated;
	}

	public TitularIsento removeTitularIsentosUserCreated(TitularIsento titularIsentosUserCreated) {
		getTitularIsentosUserCreated().remove(titularIsentosUserCreated);
		titularIsentosUserCreated.setUserCreated(null);

		return titularIsentosUserCreated;
	}

	public List<TitularIsento> getTitularIsentosUserAltered() {
		return this.titularIsentosUserAltered;
	}

	public void setTitularIsentosUserAltered(List<TitularIsento> titularIsentosUserAltered) {
		this.titularIsentosUserAltered = titularIsentosUserAltered;
	}

	public TitularIsento addTitularIsentosUserAltered(TitularIsento titularIsentosUserAltered) {
		getTitularIsentosUserAltered().add(titularIsentosUserAltered);
		titularIsentosUserAltered.setUserAltered(this);

		return titularIsentosUserAltered;
	}

	public TitularIsento removeTitularIsentosUserAltered(TitularIsento titularIsentosUserAltered) {
		getTitularIsentosUserAltered().remove(titularIsentosUserAltered);
		titularIsentosUserAltered.setUserAltered(null);

		return titularIsentosUserAltered;
	}

	public List<ViewDestination> getViewDestinationsUserCreated() {
		return this.viewDestinationsUserCreated;
	}

	public void setViewDestinationsUserCreated(List<ViewDestination> viewDestinationsUserCreated) {
		this.viewDestinationsUserCreated = viewDestinationsUserCreated;
	}

	public ViewDestination addViewDestinationsUserCreated(ViewDestination viewDestinationsUserCreated) {
		getViewDestinationsUserCreated().add(viewDestinationsUserCreated);
		viewDestinationsUserCreated.setUserCreated(this);

		return viewDestinationsUserCreated;
	}

	public ViewDestination removeViewDestinationsUserCreated(ViewDestination viewDestinationsUserCreated) {
		getViewDestinationsUserCreated().remove(viewDestinationsUserCreated);
		viewDestinationsUserCreated.setUserCreated(null);

		return viewDestinationsUserCreated;
	}

	public List<ViewDestination> getViewDestinationsUserAltered() {
		return this.viewDestinationsUserAltered;
	}

	public void setViewDestinationsUserAltered(List<ViewDestination> viewDestinationsUserAltered) {
		this.viewDestinationsUserAltered = viewDestinationsUserAltered;
	}

	public ViewDestination addViewDestinationsUserAltered(ViewDestination viewDestinationsUserAltered) {
		getViewDestinationsUserAltered().add(viewDestinationsUserAltered);
		viewDestinationsUserAltered.setUserAltered(this);

		return viewDestinationsUserAltered;
	}

	public ViewDestination removeViewDestinationsUserAltered(ViewDestination viewDestinationsUserAltered) {
		getViewDestinationsUserAltered().remove(viewDestinationsUserAltered);
		viewDestinationsUserAltered.setUserAltered(null);

		return viewDestinationsUserAltered;
	}

	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserCreated() {
		return this.viewDestinationColsDefsUserCreated;
	}

	public void setViewDestinationColsDefsUserCreated(List<ViewDestinationColsDef> viewDestinationColsDefsUserCreated) {
		this.viewDestinationColsDefsUserCreated = viewDestinationColsDefsUserCreated;
	}

	public ViewDestinationColsDef addViewDestinationColsDefsUserCreated(
			ViewDestinationColsDef viewDestinationColsDefsUserCreated) {
		getViewDestinationColsDefsUserCreated().add(viewDestinationColsDefsUserCreated);
		viewDestinationColsDefsUserCreated.setUserCreated(this);

		return viewDestinationColsDefsUserCreated;
	}

	public ViewDestinationColsDef removeViewDestinationColsDefsUserCreated(
			ViewDestinationColsDef viewDestinationColsDefsUserCreated) {
		getViewDestinationColsDefsUserCreated().remove(viewDestinationColsDefsUserCreated);
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
		getViewDestinationColsDefsUserAltered().add(tbViewDestinationColsDefsUserAltered);
		tbViewDestinationColsDefsUserAltered.setUserAltered(this);

		return tbViewDestinationColsDefsUserAltered;
	}

	public ViewDestinationColsDef removeTbViewDestinationColsDefsUserAltered(
			ViewDestinationColsDef tbViewDestinationColsDefsUserAltered) {
		getViewDestinationColsDefsUserAltered().remove(tbViewDestinationColsDefsUserAltered);
		tbViewDestinationColsDefsUserAltered.setUserAltered(null);

		return tbViewDestinationColsDefsUserAltered;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);
	}

	public void removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);
	}

	public List<ArquivoExecucao> getArquivoExecucaoUserCreated() {
		return arquivoExecucaoUserCreated;
	}

	public void setArquivoExecucaoUserCreated(List<ArquivoExecucao> arquivoExecucaoUserCreated) {
		this.arquivoExecucaoUserCreated = arquivoExecucaoUserCreated;
	}

	public List<ArquivoExecucao> getArquivoExecucaoUserAltered() {
		return arquivoExecucaoUserAltered;
	}

	public void setArquivoExecucaoUserAltered(List<ArquivoExecucao> arquivoExecucaoUserAltered) {
		this.arquivoExecucaoUserAltered = arquivoExecucaoUserAltered;
	}

	public void addArquivoExecucaoUserCreated(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaoUserCreated().add(arquivoExecucao);
		arquivoExecucao.setUserCreated(this);
	}

	public void removeArquivoExecucaoUserCreated(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaoUserCreated().remove(arquivoExecucao);
		arquivoExecucao.setUserCreated(null);
	}

	public void addArquivoExecucaoUserAltered(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaoUserAltered().add(arquivoExecucao);
		arquivoExecucao.setUserAltered(this);
	}

	public void removeArquivoExecucaoUserAltered(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaoUserAltered().remove(arquivoExecucao);
		arquivoExecucao.setUserAltered(null);
	}

	public List<Execucao> getExecucaoUserCreated() {
		return execucaoUserCreated;
	}

	public void setExecucaoUserCreated(List<Execucao> execucaoUserCreated) {
		this.execucaoUserCreated = execucaoUserCreated;
	}

	public List<Execucao> getExecucaoUserAltered() {
		return execucaoUserAltered;
	}

	public void setExecucaoUserAltered(List<Execucao> execucaoUserAltered) {
		this.execucaoUserAltered = execucaoUserAltered;
	}

	public void addExecucaoUserCreated(Execucao execucaoUserCreated) {
		getExecucaoUserCreated().add(execucaoUserCreated);
		execucaoUserCreated.setUserCreated(this);
	}

	public void removeExecucaoUserCreated(Execucao execucaoUserCreated) {
		getExecucaoUserCreated().remove(execucaoUserCreated);
		execucaoUserCreated.setUserCreated(null);
	}

	public void addExecucaoUserAltered(Execucao execucaoUserAltered) {
		getExecucaoUserAltered().add(execucaoUserAltered);
		execucaoUserAltered.setUserAltered(this);
	}

	public void removeExecucaoUserAltered(Execucao execucaoUserAltered) {
		getExecucaoUserAltered().remove(execucaoUserAltered);
		execucaoUserAltered.setUserAltered(null);
	}

}