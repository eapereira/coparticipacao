package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputLancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserStatusType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.UserStatusTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * The persistent class for the tb_user database table.
 * 
 */
@Entity
@Table(name = "TB_USER")
@NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u")
public class UserEntity extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505383075975068019L;

	public UserEntity() {
	}

	public UserEntity(UserUi ui) {
		super(ui);
	}

	@Column(name = "DESCR_NAME")
	public String getDescrName() {
		return super.getDescrName();
	}

	@Column(name = "DESCR_PASSWD")
	public String getPasswd() {
		return super.getPasswd();
	}

	@Column(name = "NM_LOGIN")
	public String getNameLogin() {
		return super.getNameLogin();
	}

	@Convert(converter = UserStatusTypeConverter.class)
	@Column(name = "TP_STATUS")
	public UserStatusType getTpStatus() {
		return super.getTpStatus();
	}

	// bi-directional many-to-one association to ArquivoInput
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoInputEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoInput> getArquivoInputsUserCreated() {
		return super.getArquivoInputsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoInput
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoInputEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoInput> getArquivoInputsUserAltered() {
		return super.getArquivoInputsUserAltered();
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoInputColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoInputColsDef> getArquivoInputColsDefUserCreated() {
		return super.getArquivoInputColsDefUserCreated();
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoInputColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoInputColsDef> getArquivoInputColsDefUserAltered() {
		return super.getArquivoInputColsDefUserAltered();
	}

	// bi-directional many-to-one association to ArquivoOutput
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoOutputEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoOutput> getArquivoOutputsUserCreated() {
		return super.getArquivoOutputsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoOutput
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoOutputEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoOutput> getArquivoOutputsUserAltered() {
		return super.getArquivoOutputsUserAltered();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoOutputSheetEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserCreated() {
		return super.getArquivoOutputSheetsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoOutputSheetEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserAltered() {
		return super.getArquivoOutputSheetsUserAltered();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "userCreated", targetEntity = ContratoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Contrato> getContratosUserCreated() {
		return super.getContratosUserCreated();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "userAltered", targetEntity = ContratoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Contrato> getContratosUserAltered() {
		return super.getContratosUserAltered();
	}

	// bi-directional many-to-one association to Dependente
	@OneToMany(mappedBy = "userCreated", targetEntity = DependenteEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Dependente> getDependentesUserCreated() {
		return super.getDependentesUserCreated();
	}

	// bi-directional many-to-one association to Dependente
	@OneToMany(mappedBy = "userAltered", targetEntity = DependenteEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Dependente> getDependentesUserAltered() {
		return super.getDependentesUserAltered();
	}

	// bi-directional many-to-one association to DependenteColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = DependenteColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<DependenteColsDef> getDependenteColsDefsUserCreated() {
		return super.getDependenteColsDefsUserCreated();
	}

	// bi-directional many-to-one association to DependenteColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = DependenteColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<DependenteColsDef> getDependenteColsDefsUserAltered() {
		return super.getDependenteColsDefsUserAltered();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(mappedBy = "userCreated", targetEntity = DependenteIsentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<DependenteIsento> getDependenteIsentosUserCreated() {
		return super.getDependenteIsentosUserCreated();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(mappedBy = "userAltered", targetEntity = DependenteIsentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<DependenteIsento> getDependenteIsentosUserAltered() {
		return super.getDependenteIsentosUserAltered();
	}

	// bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy = "userCreated", targetEntity = EmpresaEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Empresa> getEmpresasUserCreated() {
		return super.getEmpresasUserCreated();
	}

	// bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy = "userAltered", targetEntity = EmpresaEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Empresa> getEmpresasUserAltered() {
		return super.getEmpresasUserAltered();
	}

	// bi-directional many-to-one association to InputDependente
	@OneToMany(mappedBy = "userCreated", targetEntity = InputDependenteEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputDependente> getInputDependentesUserCreated() {
		return super.getInputDependentesUserCreated();
	}

	// bi-directional many-to-one association to InputDependente
	@OneToMany(mappedBy = "userAltered", targetEntity = InputDependenteEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputDependente> getInputDependentesUserAltered() {
		return super.getInputDependentesUserAltered();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(mappedBy = "userCreated", targetEntity = InputLancamentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputLancamento> getInputLancamentosUserCreated() {
		return super.getInputLancamentosUserCreated();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(mappedBy = "userAltered", targetEntity = InputLancamentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputLancamento> getInputLancamentosUserAltered() {
		return super.getInputLancamentosUserAltered();
	}

	// bi-directional many-to-one association to InputTitular
	@OneToMany(mappedBy = "userCreated", targetEntity = InputTitularEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputTitular> getInputTitularsUserCreated() {
		return super.getInputTitularsUserCreated();
	}

	// bi-directional many-to-one association to InputTitular
	@OneToMany(mappedBy = "userAltered", targetEntity = InputTitularEntity.class)
	@OrderColumn(name = "INDEX")
	public List<InputTitular> getInputTitularsUserAltered() {
		return super.getInputTitularsUserAltered();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Lancamento> getLancamentosUserCreated() {
		return super.getLancamentosUserCreated();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Lancamento> getLancamentosUserAltered() {
		return super.getLancamentosUserAltered();
	}

	// bi-directional many-to-one association to LancamentoColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<LancamentoColsDef> getLancamentoColsDefsUserCreated() {
		return super.getLancamentoColsDefsUserCreated();
	}

	// bi-directional many-to-one association to LancamentoColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<LancamentoColsDef> getLancamentoColsDefsUserAltered() {
		return super.getLancamentoColsDefsUserAltered();
	}

	// bi-directional many-to-one association to Operadora
	@OneToMany(mappedBy = "userCreated", targetEntity = OperadoraEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Operadora> getOperadorasUserCreated() {
		return super.getOperadorasUserCreated();
	}

	// bi-directional many-to-one association to Operadora
	@OneToMany(mappedBy = "userAltered", targetEntity = OperadoraEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Operadora> getOperadorasUserAltered() {
		return super.getOperadorasUserAltered();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Regra> getRegrasUserCreated() {
		return super.getRegrasUserCreated();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Regra> getRegrasUserAltered() {
		return super.getRegrasUserAltered();
	}

	// bi-directional many-to-one association to RegraField
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraFieldEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraField> getRegraFieldsUserCreated() {
		return super.getRegraFieldsUserCreated();
	}

	// bi-directional many-to-one association to RegraField
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraFieldEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraField> getRegraFieldsUserAltered() {
		return super.getRegraFieldsUserAltered();
	}

	// bi-directional many-to-one association to RegraOperation
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraOperationEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraOperation> getRegraOperationsUserCreated() {
		return super.getRegraOperationsUserCreated();
	}

	// bi-directional many-to-one association to RegraOperation
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraOperationEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraOperation> getRegraOperationsUserAltered() {
		return super.getRegraOperationsUserAltered();
	}

	// bi-directional many-to-one association to RegraValor
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraValorEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraValor> getRegraValorsUserCreated() {
		return super.getRegraValorsUserCreated();
	}

	// bi-directional many-to-one association to RegraValor
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraValorEntity.class)
	@OrderColumn(name = "INDEX")
	public List<RegraValor> getRegraValorsUserAltered() {
		return super.getRegraValorsUserAltered();
	}

	// bi-directional many-to-one association to Titular
	@OneToMany(mappedBy = "userCreated", targetEntity = TitularEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Titular> getTitularsUserCreated() {
		return super.getTitularsUserCreated();
	}

	// bi-directional many-to-one association to Titular
	@OneToMany(mappedBy = "userAltered", targetEntity = TitularEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Titular> getTitularsUserAltered() {
		return super.getTitularsUserAltered();
	}

	// bi-directional many-to-one association to TitularColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = TitularColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<TitularColsDef> getTitularColsDefsUserCreated() {
		return super.getTitularColsDefsUserCreated();
	}

	// bi-directional many-to-one association to TitularColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = TitularColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<TitularColsDef> getTitularColsDefsUserAltered() {
		return super.getTitularColsDefsUserAltered();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "userCreated", targetEntity = TitularIsentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<TitularIsento> getTitularIsentosUserCreated() {
		return super.getTitularIsentosUserCreated();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "userAltered", targetEntity = TitularIsentoEntity.class)
	@OrderColumn(name = "INDEX")
	public List<TitularIsento> getTitularIsentosUserAltered() {
		return super.getTitularIsentosUserAltered();
	}

	// bi-directional many-to-one association to ViewDestination
	@OneToMany(mappedBy = "userCreated", targetEntity = ViewDestinationEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ViewDestination> getViewDestinationsUserCreated() {
		return super.getViewDestinationsUserCreated();
	}

	// bi-directional many-to-one association to ViewDestination
	@OneToMany(mappedBy = "userAltered", targetEntity = ViewDestinationEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ViewDestination> getViewDestinationsUserAltered() {
		return super.getViewDestinationsUserAltered();
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = ViewDestinationColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserCreated() {
		return super.getViewDestinationColsDefsUserCreated();
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = ViewDestinationColsDefEntity.class)
	@OrderColumn(name = "INDEX")
	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserAltered() {
		return super.getViewDestinationColsDefsUserAltered();
	}

	// bi-directional many-to-one association to Parameter
	@OneToMany(mappedBy = "userCreated", targetEntity = ParameterEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Parameter> getUserCreatedParameter() {
		return super.getUserCreatedParameter();
	}

	// bi-directional many-to-one association to Parameter
	@OneToMany(mappedBy = "userAltered", targetEntity = ParameterEntity.class)
	@OrderColumn(name = "INDEX")
	public List<Parameter> getUserAlteredParameter() {
		return super.getUserAlteredParameter();
	}

}