package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CreateBeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Execucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExternalProcess;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ReportLayoutType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ReportQueryType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.CreateBeneficiarioTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ReportLayoutTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ReportQueryTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener.EmpresaEntityListener;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */
@Entity
@Table(name = "TB_EMPRESA")
@NamedQuery(name = "EmpresaEntity.findAll", query = "SELECT e FROM EmpresaEntity e")
@EntityListeners(EmpresaEntityListener.class)
public class EmpresaEntity extends Empresa implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157383238358452982L;

	public EmpresaEntity() {
	}

	public EmpresaEntity(EmpresaUi ui) {
		super(ui);
	}

	@Column(name = "NM_EMPRESA")
	public String getNameEmpresa() {
		return super.getNameEmpresa();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(
			mappedBy = "empresa",
			targetEntity = ContratoEntity.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	public List<Contrato> getContratos() {
		return super.getContratos();
	}

	// bi-directional many-to-one association to Contrato
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OperadoraEntity.class)
	@JoinColumn(name = "ID_OPERADORA")
	@Override
	public Operadora getOperadora() {
		// TODO Auto-generated method stub
		return super.getOperadora();
	}

	@Column(name = "CD_AUTOMATIC_CREATE_BENEFICIARIO")
	@Override
	public boolean isAutomaticCreateBeneficiario() {
		// TODO Auto-generated method stub
		return super.isAutomaticCreateBeneficiario();
	}

	@Column(name = "CD_OUTPUT_REPORT_DIR")
	@Override
	public String getOutputReportDir() {
		// TODO Auto-generated method stub
		return super.getOutputReportDir();
	}

	@Column(name = "CD_EMPRESA")
	@Override
	public String getCdEmpresa() {
		// TODO Auto-generated method stub
		return super.getCdEmpresa();
	}

	@Column(name = "CD_INPUT_DIR")
	@Override
	public String getInputDir() {
		// TODO Auto-generated method stub
		return super.getInputDir();
	}

	@Column(name = "TP_SAVE_MECSAS_DETAIL")
	@Override
	public boolean isSaveMecsasDetails() {
		// TODO Auto-generated method stub
		return super.isSaveMecsasDetails();
	}

	@Column(name = "TP_SAVE_BENEFICIARIO_DETAIL")
	@Override
	public boolean isSaveBeneficiarioDetails() {
		// TODO Auto-generated method stub
		return super.isSaveBeneficiarioDetails();
	}

	@Column(name = "TP_EXTERNAL_PROCESS")
	@Override
	public boolean isEnabledExternalProcess() {
		// TODO Auto-generated method stub
		return super.isEnabledExternalProcess();
	}

	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "empresa",
			targetEntity = ExternalProcessEntity.class)
	@Override
	public ExternalProcess getExternalProcess() {
		// TODO Auto-generated method stub
		return super.getExternalProcess();
	}

	@OneToMany(
			mappedBy = "empresa",
			targetEntity = ExecucaoEntity.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@Override
	public List<Execucao> getExecucaos() {
		// TODO Auto-generated method stub
		return super.getExecucaos();
	}

	@Column(name = "CD_OUTPUT_DIR")
	@Override
	public String getOutputDir() {
		// TODO Auto-generated method stub
		return super.getOutputDir();
	}

	@Column(name = "CD_FAILURE_DIR")
	@Override
	public String getFailureDir() {
		// TODO Auto-generated method stub
		return super.getFailureDir();
	}

	@Convert(converter = ReportQueryTypeConverter.class)
	@Column(name = "TP_REPORT_QUERY")
	@Override
	public ReportQueryType getReportQueryType() {
		// TODO Auto-generated method stub
		return super.getReportQueryType();
	}

	@Column(name = "CD_AUTOMATIC_CREATE_TITULAR")
	@Override
	public boolean isAutomaticCreateTitular() {
		// TODO Auto-generated method stub
		return super.isAutomaticCreateTitular();
	}

	@Column(name = "CD_SEARCH_BENEFICIARIO_NONAME")
	@Override
	public boolean isSearchBeneficiarioWithoutName() {
		// TODO Auto-generated method stub
		return super.isSearchBeneficiarioWithoutName();
	}

	@Column(name = "CD_ACCEPT_TITULAR_WITHOUT_CPF")
	@Override
	public boolean isAcceptTitularWithoutCpf() {
		// TODO Auto-generated method stub
		return super.isAcceptTitularWithoutCpf();
	}

	@Column(name = "CD_GENERATE_OUTPUT_FILE_NOFATUCOPA")
	@Override
	public boolean isGenerateOutputFileWithoutFatucopa() {
		// TODO Auto-generated method stub
		return super.isGenerateOutputFileWithoutFatucopa();
	}

	@Column(name = "CD_CREATE_BENEFICIARIO_FROM_MECSAS2")
	@Override
	public boolean isCreateBeneficiarioFromMecsas2() {
		// TODO Auto-generated method stub
		return super.isCreateBeneficiarioFromMecsas2();
	}

	@Column(name = "CD_USE_JASPER_REPORTS")
	@Override
	public boolean isUseJasperReports() {
		// TODO Auto-generated method stub
		return super.isUseJasperReports();
	}

	@Column(name = "CD_UPDATE_BENEFICIARIO_FROM_FATUCOPA")
	@Override
	public boolean isUpdateBeneficiarioFromFatucopa() {
		// TODO Auto-generated method stub
		return super.isUpdateBeneficiarioFromFatucopa();
	}

	@Column(name = "CD_ENABLED")
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.isEnabled();
	}

	@Convert(converter = ReportLayoutTypeConverter.class)
	@Column(name = "TP_REPORT_LAYOUT_TYPE")
	@Override
	public ReportLayoutType getReportLayoutType() {
		// TODO Auto-generated method stub
		return super.getReportLayoutType();
	}

	@Column(name = "CD_UPDATE_BENEFICIARIO_FROM_ISENTO")
	@Override
	public boolean isUpdateBeneficiarioFromIsento() {
		// TODO Auto-generated method stub
		return super.isUpdateBeneficiarioFromIsento();
	}

	@Column(name = "TP_CREATE_BENEFICIARIO")
	@Convert(converter = CreateBeneficiarioTypeConverter.class)
	@Override
	public CreateBeneficiarioType getCreateBeneficiarioType() {
		// TODO Auto-generated method stub
		return super.getCreateBeneficiarioType();
	}

	@Column(name = "CD_CREATE_BENEFICIARIO_FROM_FATUCOPA")
	@Override
	public boolean isCreateBeneficiarioFromFatucopa() {
		// TODO Auto-generated method stub
		return super.isCreateBeneficiarioFromFatucopa();
	}

	@Column(name = "CD_SEARCH_BENEFICIARIO_MATRICULA_EMPRESA")
	@Override
	public boolean isSearchBeneficiarioByMatriculaEmpresa() {
		// TODO Auto-generated method stub
		return super.isSearchBeneficiarioByMatriculaEmpresa();
	}

}