package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Desconhecido extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4266003429070075774L;
	private Integer mes;
	private Integer ano;

	private ArquivoInput arquivoInput;

	private List<DesconhecidoDetail> desconhecidoDetails;

	public Desconhecido() {
		desconhecidoDetails = new ArrayList<>();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((arquivoInput == null) ? 0 : arquivoInput.hashCode());
		result = prime * result + ((desconhecidoDetails == null) ? 0
				: desconhecidoDetails.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		Desconhecido other = (Desconhecido) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (arquivoInput == null) {
			if (other.arquivoInput != null)
				return false;
		} else if (!arquivoInput.equals(other.arquivoInput))
			return false;
		if (desconhecidoDetails == null) {
			if (other.desconhecidoDetails != null)
				return false;
		} else if (!desconhecidoDetails.equals(other.desconhecidoDetails))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}

	public List<DesconhecidoDetail> getDesconhecidoDetails() {
		return desconhecidoDetails;
	}

	public void setDesconhecidoDetails(
			List<DesconhecidoDetail> desconhecidoDetails) {
		this.desconhecidoDetails = desconhecidoDetails;
	}

	public void addDesconhecidoDetail(DesconhecidoDetail desconhecidoDetail) {
		getDesconhecidoDetails().add(desconhecidoDetail);
		desconhecidoDetail.setDesconhecido(this);
	}

	public void removeDesconhecido(DesconhecidoDetail desconhecidoDetail) {
		getDesconhecidoDetails().remove(desconhecidoDetail);
		desconhecidoDetail.setDesconhecido(null);
	}

}
