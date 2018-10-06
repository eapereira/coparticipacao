package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Embeddable
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8896580900192845138L;

	private Integer cdEmpresaTransferencia;

	private Long cdMatriculaTransferencia;

	private Integer localTransferecia;

	private Integer categoriaTransferencia;

	private String planoTransferecia;

	private String motivoRemissao;

	private Integer qtdePermanenciaMeses;

	private Long cpfNovoTitular;

	private Integer rdpNovoTitular;

	private LocalDate dtInicioTransferencia;

	private String cdStatus;

	private String cdError;

	private Integer cdDv;

	private String cpt;

	private Integer cdEmpresaTitular;

	private String diferenciadorMatriculaTitular;

	public Transferencia() {

	}

	@Column(name = "CD_EMPRESA_TITULAR")
	public Integer getCdEmpresaTransferencia() {
		return cdEmpresaTransferencia;
	}

	public void setCdEmpresaTransferencia(Integer cdEmpresaTransferencia) {
		this.cdEmpresaTransferencia = cdEmpresaTransferencia;
	}

	@Column(name = "NR_MATRICULA_TRANSF")
	public Long getCdMatriculaTransferencia() {
		return cdMatriculaTransferencia;
	}

	public void setCdMatriculaTransferencia(Long cdMatriculaTransferencia) {
		this.cdMatriculaTransferencia = cdMatriculaTransferencia;
	}

	@Column(name = "NR_LOCAL_TRANSF")
	public Integer getLocalTransferecia() {
		return localTransferecia;
	}

	public void setLocalTransferecia(Integer localTransferecia) {
		this.localTransferecia = localTransferecia;
	}

	@Column(name = "CD_CATEGORIA_TRANSF")
	public Integer getCategoriaTransferencia() {
		return categoriaTransferencia;
	}

	public void setCategoriaTransferencia(Integer categoriaTransferencia) {
		this.categoriaTransferencia = categoriaTransferencia;
	}

	@Column(name = "CD_PLANO_TRANSF")
	public String getPlanoTransferecia() {
		return planoTransferecia;
	}

	public void setPlanoTransferecia(String planoTransferecia) {
		this.planoTransferecia = planoTransferecia;
	}

	@Column(name = "MOTIVO_REMISSAO")
	public String getMotivoRemissao() {
		return motivoRemissao;
	}

	public void setMotivoRemissao(String motivoRemissao) {
		this.motivoRemissao = motivoRemissao;
	}

	@Column(name = "QTDE_PERM_MESES")
	public Integer getQtdePermanenciaMeses() {
		return qtdePermanenciaMeses;
	}

	public void setQtdePermanenciaMeses(Integer qtdePermanenciaMeses) {
		this.qtdePermanenciaMeses = qtdePermanenciaMeses;
	}

	@Column(name = "NR_CPF_NOVO_TITULAR")
	public Long getCpfNovoTitular() {
		return cpfNovoTitular;
	}

	public void setCpfNovoTitular(Long cpfNovoTitular) {
		this.cpfNovoTitular = cpfNovoTitular;
	}

	@Column(name = "RDP_NOVO_TITULAR")
	public Integer getRdpNovoTitular() {
		return rdpNovoTitular;
	}

	public void setRdpNovoTitular(Integer rdpNovoTitular) {
		this.rdpNovoTitular = rdpNovoTitular;
	}

	@Column(name = "DT_INICIO_TRANSF")
	public LocalDate getDtInicioTransferencia() {
		return dtInicioTransferencia;
	}

	public void setDtInicioTransferencia(LocalDate dtInicioTransferencia) {
		this.dtInicioTransferencia = dtInicioTransferencia;
	}

	@Column(name = "CD_STATUS")
	public String getCdStatus() {
		return cdStatus;
	}

	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}

	@Column(name = "CD_ERROR")
	public String getCdError() {
		return cdError;
	}

	public void setCdError(String cdError) {
		this.cdError = cdError;
	}

	@Column(name = "CD_DV")
	public Integer getCdDv() {
		return cdDv;
	}

	public void setCdDv(Integer cdDv) {
		this.cdDv = cdDv;
	}

	@Column(name = "CPT")
	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	@Column(name = "CD_EMPRESA_TITULAR_TRANSF")
	public Integer getCdEmpresaTitular() {
		return cdEmpresaTitular;
	}

	public void setCdEmpresaTitular(Integer cdEmpresaTitular) {
		this.cdEmpresaTitular = cdEmpresaTitular;
	}

	@Column(name = "DIF_MATRICULA_TITULAR")
	public String getDiferenciadorMatriculaTitular() {
		return diferenciadorMatriculaTitular;
	}

	public void setDiferenciadorMatriculaTitular(String diferenciadorMatriculaTitular) {
		this.diferenciadorMatriculaTitular = diferenciadorMatriculaTitular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaTransferencia == null) ? 0 : categoriaTransferencia.hashCode());
		result = prime * result + ((cdDv == null) ? 0 : cdDv.hashCode());
		result = prime * result + ((cdEmpresaTitular == null) ? 0 : cdEmpresaTitular.hashCode());
		result = prime * result + ((cdEmpresaTransferencia == null) ? 0 : cdEmpresaTransferencia.hashCode());
		result = prime * result + ((cdError == null) ? 0 : cdError.hashCode());
		result = prime * result + ((cdMatriculaTransferencia == null) ? 0 : cdMatriculaTransferencia.hashCode());
		result = prime * result + ((cdStatus == null) ? 0 : cdStatus.hashCode());
		result = prime * result + ((cpfNovoTitular == null) ? 0 : cpfNovoTitular.hashCode());
		result = prime * result + ((cpt == null) ? 0 : cpt.hashCode());
		result = prime * result
				+ ((diferenciadorMatriculaTitular == null) ? 0 : diferenciadorMatriculaTitular.hashCode());
		result = prime * result + ((dtInicioTransferencia == null) ? 0 : dtInicioTransferencia.hashCode());
		result = prime * result + ((localTransferecia == null) ? 0 : localTransferecia.hashCode());
		result = prime * result + ((motivoRemissao == null) ? 0 : motivoRemissao.hashCode());
		result = prime * result + ((planoTransferecia == null) ? 0 : planoTransferecia.hashCode());
		result = prime * result + ((qtdePermanenciaMeses == null) ? 0 : qtdePermanenciaMeses.hashCode());
		result = prime * result + ((rdpNovoTitular == null) ? 0 : rdpNovoTitular.hashCode());
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
		Transferencia other = (Transferencia) obj;
		if (categoriaTransferencia == null) {
			if (other.categoriaTransferencia != null)
				return false;
		} else if (!categoriaTransferencia.equals(other.categoriaTransferencia))
			return false;
		if (cdDv == null) {
			if (other.cdDv != null)
				return false;
		} else if (!cdDv.equals(other.cdDv))
			return false;
		if (cdEmpresaTitular == null) {
			if (other.cdEmpresaTitular != null)
				return false;
		} else if (!cdEmpresaTitular.equals(other.cdEmpresaTitular))
			return false;
		if (cdEmpresaTransferencia == null) {
			if (other.cdEmpresaTransferencia != null)
				return false;
		} else if (!cdEmpresaTransferencia.equals(other.cdEmpresaTransferencia))
			return false;
		if (cdError == null) {
			if (other.cdError != null)
				return false;
		} else if (!cdError.equals(other.cdError))
			return false;
		if (cdMatriculaTransferencia == null) {
			if (other.cdMatriculaTransferencia != null)
				return false;
		} else if (!cdMatriculaTransferencia.equals(other.cdMatriculaTransferencia))
			return false;
		if (cdStatus == null) {
			if (other.cdStatus != null)
				return false;
		} else if (!cdStatus.equals(other.cdStatus))
			return false;
		if (cpfNovoTitular == null) {
			if (other.cpfNovoTitular != null)
				return false;
		} else if (!cpfNovoTitular.equals(other.cpfNovoTitular))
			return false;
		if (cpt == null) {
			if (other.cpt != null)
				return false;
		} else if (!cpt.equals(other.cpt))
			return false;
		if (diferenciadorMatriculaTitular == null) {
			if (other.diferenciadorMatriculaTitular != null)
				return false;
		} else if (!diferenciadorMatriculaTitular.equals(other.diferenciadorMatriculaTitular))
			return false;
		if (dtInicioTransferencia == null) {
			if (other.dtInicioTransferencia != null)
				return false;
		} else if (!dtInicioTransferencia.equals(other.dtInicioTransferencia))
			return false;
		if (localTransferecia == null) {
			if (other.localTransferecia != null)
				return false;
		} else if (!localTransferecia.equals(other.localTransferecia))
			return false;
		if (motivoRemissao == null) {
			if (other.motivoRemissao != null)
				return false;
		} else if (!motivoRemissao.equals(other.motivoRemissao))
			return false;
		if (planoTransferecia == null) {
			if (other.planoTransferecia != null)
				return false;
		} else if (!planoTransferecia.equals(other.planoTransferecia))
			return false;
		if (qtdePermanenciaMeses == null) {
			if (other.qtdePermanenciaMeses != null)
				return false;
		} else if (!qtdePermanenciaMeses.equals(other.qtdePermanenciaMeses))
			return false;
		if (rdpNovoTitular == null) {
			if (other.rdpNovoTitular != null)
				return false;
		} else if (!rdpNovoTitular.equals(other.rdpNovoTitular))
			return false;
		return true;
	}

}
