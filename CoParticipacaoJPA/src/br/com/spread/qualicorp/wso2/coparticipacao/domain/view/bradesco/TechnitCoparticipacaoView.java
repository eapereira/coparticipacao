package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@MappedSuperclass
public class TechnitCoparticipacaoView extends BradescoCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186746652074846785L;

	private BigDecimal fatorModeradorInss;

	private Integer tipoRegistro;

	private String cdContrato;

	private BigDecimal valorInss;
	
	private BigDecimal valorAliquotaInss;

	private BigDecimal valorLiquidoSinistro;
	
	private String subFatura;
	
	private Integer indicadorEvento;

	public TechnitCoparticipacaoView() {
		super();
	}

	@Column(name = "VL_FATOR_MODERADOR_INSS")
	public BigDecimal getFatorModeradorInss() {
		return fatorModeradorInss;
	}

	public void setFatorModeradorInss(BigDecimal fatorModeradorInss) {
		this.fatorModeradorInss = fatorModeradorInss;
	}

	@Column(name = "TP_REGISTRO")
	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	@Column(name = "CD_CONTRATO")
	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	@Column(name = "VL_INSS")
	public BigDecimal getValorInss() {
		return valorInss;
	}

	public void setValorInss(BigDecimal valorInss) {
		this.valorInss = valorInss;
	}

	@Column(name = "VL_LIQUIDO_SINISTRO")
	public BigDecimal getValorLiquidoSinistro() {
		return valorLiquidoSinistro;
	}

	public void setValorLiquidoSinistro(BigDecimal valorLiquidoSinistro) {
		this.valorLiquidoSinistro = valorLiquidoSinistro;
	}

	@Column(name = "VL_ALIQUOTA_INSS")
	public BigDecimal getValorAliquotaInss() {
		return valorAliquotaInss;
	}

	public void setValorAliquotaInss(BigDecimal valorAliquotaInss) {
		this.valorAliquotaInss = valorAliquotaInss;
	}

	@Column(name = "NR_SUBFATURA")
	public String getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(String subFatura) {
		this.subFatura = subFatura;
	}

	@Column(name = "IND_EVENTO")
	public Integer getIndicadorEvento() {
		return indicadorEvento;
	}

	public void setIndicadorEvento(Integer indicadorEvento) {
		this.indicadorEvento = indicadorEvento;
	}
}
