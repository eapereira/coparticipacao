package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoOutputDesconhecidoColsDef extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5744067170574192256L;

	private ArquivoInputSheetColsDef arquivoInputSheetColsDef;

	private DesconhecidoColType desconhecidoColType;

	private Integer ordem;

	private String label;

	public ArquivoOutputDesconhecidoColsDef() {
	}

	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		return arquivoInputSheetColsDef;
	}

	public void setArquivoInputSheetColsDef(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		this.arquivoInputSheetColsDef = arquivoInputSheetColsDef;
	}

	public DesconhecidoColType getDesconhecidoColType() {
		return desconhecidoColType;
	}

	public void setDesconhecidoColType(DesconhecidoColType desconhecidoColType) {
		this.desconhecidoColType = desconhecidoColType;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoInputSheetColsDef == null) ? 0 : arquivoInputSheetColsDef.hashCode());
		result = prime * result + ((desconhecidoColType == null) ? 0 : desconhecidoColType.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
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
		ArquivoOutputDesconhecidoColsDef other = (ArquivoOutputDesconhecidoColsDef) obj;
		if (arquivoInputSheetColsDef == null) {
			if (other.arquivoInputSheetColsDef != null)
				return false;
		} else if (!arquivoInputSheetColsDef.equals(other.arquivoInputSheetColsDef))
			return false;
		if (desconhecidoColType != other.desconhecidoColType)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}

}
