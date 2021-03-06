package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.IsentoTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@MappedSuperclass
public abstract class Isento extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -858419263514366408L;

	private Integer mes;

	private Integer ano;

	private IsentoType isentoType;

	private BigDecimal valorIsencao;

	private Contrato contrato;

	private LocalDate dtInicio;

	private LocalDate dtFim;

	public Isento() {
		super();
	}

	@Column(name = "CD_MES")
	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	@Column(name = "CD_ANO")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Convert(converter = IsentoTypeConverter.class)
	@Column(name = "TP_ISENTO")
	public IsentoType getIsentoType() {
		return isentoType;
	}

	public void setIsentoType(IsentoType isentoType) {
		this.isentoType = isentoType;
	}

	@Column(name = "VL_ISENCAO")
	public BigDecimal getValorIsencao() {
		return valorIsencao;
	}

	public void setValorIsencao(BigDecimal valorIsencao) {
		this.valorIsencao = valorIsencao;
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@Column(name = "DT_INICIO")
	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	@Column(name = "DT_FIM")
	public LocalDate getDtFim() {
		return dtFim;
	}

	public void setDtFim(LocalDate dtFim) {
		this.dtFim = dtFim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((dtFim == null) ? 0 : dtFim.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((isentoType == null) ? 0 : isentoType.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((valorIsencao == null) ? 0 : valorIsencao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Isento other = (Isento) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (dtFim == null) {
			if (other.dtFim != null)
				return false;
		} else if (!dtFim.equals(other.dtFim))
			return false;
		if (dtInicio == null) {
			if (other.dtInicio != null)
				return false;
		} else if (!dtInicio.equals(other.dtInicio))
			return false;
		if (isentoType != other.isentoType)
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (valorIsencao == null) {
			if (other.valorIsencao != null)
				return false;
		} else if (!valorIsencao.equals(other.valorIsencao))
			return false;
		return true;
	}

}
