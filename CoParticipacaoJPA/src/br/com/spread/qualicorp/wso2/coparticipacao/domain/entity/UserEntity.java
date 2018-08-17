package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraField;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
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
public class UserEntity extends User implements DomainEntity {

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
	public List<ArquivoInput> getArquivoInputsUserCreated() {
		return super.getArquivoInputsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoInput
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoInputEntity.class)
	public List<ArquivoInput> getArquivoInputsUserAltered() {
		return super.getArquivoInputsUserAltered();
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoInputColsDefEntity.class)
	public List<ArquivoInputColsDef> getArquivoInputColsDefUserCreated() {
		return super.getArquivoInputColsDefUserCreated();
	}

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoInputColsDefEntity.class)
	public List<ArquivoInputColsDef> getArquivoInputColsDefUserAltered() {
		return super.getArquivoInputColsDefUserAltered();
	}

	// bi-directional many-to-one association to ArquivoOutput
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoOutputEntity.class)
	public List<ArquivoOutput> getArquivoOutputsUserCreated() {
		return super.getArquivoOutputsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoOutput
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoOutputEntity.class)
	public List<ArquivoOutput> getArquivoOutputsUserAltered() {
		return super.getArquivoOutputsUserAltered();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(mappedBy = "userCreated", targetEntity = ArquivoOutputSheetEntity.class)
	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserCreated() {
		return super.getArquivoOutputSheetsUserCreated();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(mappedBy = "userAltered", targetEntity = ArquivoOutputSheetEntity.class)
	public List<ArquivoOutputSheet> getArquivoOutputSheetsUserAltered() {
		return super.getArquivoOutputSheetsUserAltered();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "userCreated", targetEntity = ContratoEntity.class)
	public List<Contrato> getContratosUserCreated() {
		return super.getContratosUserCreated();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "userAltered", targetEntity = ContratoEntity.class)
	public List<Contrato> getContratosUserAltered() {
		return super.getContratosUserAltered();
	}

	// bi-directional many-to-one association to Dependente
	@OneToMany(mappedBy = "userCreated", targetEntity = DependenteEntity.class)
	public List<Dependente> getDependentesUserCreated() {
		return super.getDependentesUserCreated();
	}

	// bi-directional many-to-one association to Dependente
	@OneToMany(mappedBy = "userAltered", targetEntity = DependenteEntity.class)
	public List<Dependente> getDependentesUserAltered() {
		return super.getDependentesUserAltered();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(mappedBy = "userCreated", targetEntity = DependenteIsentoEntity.class)
	public List<DependenteIsento> getDependenteIsentosUserCreated() {
		return super.getDependenteIsentosUserCreated();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(mappedBy = "userAltered", targetEntity = DependenteIsentoEntity.class)
	public List<DependenteIsento> getDependenteIsentosUserAltered() {
		return super.getDependenteIsentosUserAltered();
	}

	// bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy = "userCreated", targetEntity = EmpresaEntity.class)
	public List<Empresa> getEmpresasUserCreated() {
		return super.getEmpresasUserCreated();
	}

	// bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy = "userAltered", targetEntity = EmpresaEntity.class)
	public List<Empresa> getEmpresasUserAltered() {
		return super.getEmpresasUserAltered();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoInputEntity.class)
	public List<LancamentoInput> getInputLancamentosUserCreated() {
		return super.getInputLancamentosUserCreated();
	}

	// bi-directional many-to-one association to InputLancamento
	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoInputEntity.class)
	public List<LancamentoInput> getInputLancamentosUserAltered() {
		return super.getInputLancamentosUserAltered();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoEntity.class)
	public List<Lancamento> getLancamentosUserCreated() {
		return super.getLancamentosUserCreated();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoEntity.class)
	public List<Lancamento> getLancamentosUserAltered() {
		return super.getLancamentosUserAltered();
	}

	// bi-directional many-to-one association to Operadora
	@OneToMany(mappedBy = "userCreated", targetEntity = OperadoraEntity.class)
	public List<Operadora> getOperadorasUserCreated() {
		return super.getOperadorasUserCreated();
	}

	// bi-directional many-to-one association to Operadora
	@OneToMany(mappedBy = "userAltered", targetEntity = OperadoraEntity.class)
	public List<Operadora> getOperadorasUserAltered() {
		return super.getOperadorasUserAltered();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraEntity.class)
	public List<Regra> getRegrasUserCreated() {
		return super.getRegrasUserCreated();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraEntity.class)
	public List<Regra> getRegrasUserAltered() {
		return super.getRegrasUserAltered();
	}

	// bi-directional many-to-one association to RegraField
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraFieldEntity.class)

	public List<RegraField> getRegraFieldsUserCreated() {
		return super.getRegraFieldsUserCreated();
	}

	// bi-directional many-to-one association to RegraField
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraFieldEntity.class)
	public List<RegraField> getRegraFieldsUserAltered() {
		return super.getRegraFieldsUserAltered();
	}

	// bi-directional many-to-one association to RegraOperation
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraOperationEntity.class)

	public List<RegraOperation> getRegraOperationsUserCreated() {
		return super.getRegraOperationsUserCreated();
	}

	// bi-directional many-to-one association to RegraOperation
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraOperationEntity.class)
	public List<RegraOperation> getRegraOperationsUserAltered() {
		return super.getRegraOperationsUserAltered();
	}

	// bi-directional many-to-one association to RegraValor
	@OneToMany(mappedBy = "userCreated", targetEntity = RegraValorEntity.class)
	public List<RegraValor> getRegraValorsUserCreated() {
		return super.getRegraValorsUserCreated();
	}

	// bi-directional many-to-one association to RegraValor
	@OneToMany(mappedBy = "userAltered", targetEntity = RegraValorEntity.class)
	public List<RegraValor> getRegraValorsUserAltered() {
		return super.getRegraValorsUserAltered();
	}

	// bi-directional many-to-one association to Titular
	@OneToMany(mappedBy = "userCreated", targetEntity = TitularEntity.class)
	public List<Titular> getTitularsUserCreated() {
		return super.getTitularsUserCreated();
	}

	// bi-directional many-to-one association to Titular
	@OneToMany(mappedBy = "userAltered", targetEntity = TitularEntity.class)
	public List<Titular> getTitularsUserAltered() {
		return super.getTitularsUserAltered();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "userCreated", targetEntity = TitularIsentoEntity.class)
	public List<TitularIsento> getTitularIsentosUserCreated() {
		return super.getTitularIsentosUserCreated();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "userAltered", targetEntity = TitularIsentoEntity.class)
	public List<TitularIsento> getTitularIsentosUserAltered() {
		return super.getTitularIsentosUserAltered();
	}

	// bi-directional many-to-one association to ViewDestination
	@OneToMany(mappedBy = "userCreated", targetEntity = ViewDestinationEntity.class)
	public List<ViewDestination> getViewDestinationsUserCreated() {
		return super.getViewDestinationsUserCreated();
	}

	// bi-directional many-to-one association to ViewDestination
	@OneToMany(mappedBy = "userAltered", targetEntity = ViewDestinationEntity.class)
	public List<ViewDestination> getViewDestinationsUserAltered() {
		return super.getViewDestinationsUserAltered();
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@OneToMany(mappedBy = "userCreated", targetEntity = ViewDestinationColsDefEntity.class)
	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserCreated() {
		return super.getViewDestinationColsDefsUserCreated();
	}

	// bi-directional many-to-one association to ViewDestinationColsDef
	@OneToMany(mappedBy = "userAltered", targetEntity = ViewDestinationColsDefEntity.class)
	public List<ViewDestinationColsDef> getViewDestinationColsDefsUserAltered() {
		return super.getViewDestinationColsDefsUserAltered();
	}

	// bi-directional many-to-one association to Parameter
	@OneToMany(mappedBy = "userCreated", targetEntity = ParameterEntity.class)
	public List<Parameter> getUserCreatedParameter() {
		return super.getUserCreatedParameter();
	}

	// bi-directional many-to-one association to Parameter
	@OneToMany(mappedBy = "userAltered", targetEntity = ParameterEntity.class)
	public List<Parameter> getUserAlteredParameter() {
		return super.getUserAlteredParameter();
	}

	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoInputEntity.class)
	@Override
	public List<LancamentoInput> getLancamentoInputUserCreated() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputUserCreated();
	}

	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoInputEntity.class)
	@Override
	public List<LancamentoInput> getLancamentoInputUserAltered() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputUserAltered();
	}

	@OneToMany(mappedBy = "userCreated", targetEntity = LancamentoInputColsEntity.class)
	@Override
	public List<LancamentoInputCols> getLancamentoInputColsUserCreated() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputColsUserCreated();
	}

	@OneToMany(mappedBy = "userAltered", targetEntity = LancamentoInputColsEntity.class)
	@Override
	public List<LancamentoInputCols> getLancamentoInputColsUserAltered() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputColsUserAltered();
	}

}