package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class IsentoInputSheetCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1799537054712694938L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private BeneficiarioIsentoColType beneficiarioIsentoColType;

	private IsentoInputSheet isentoInputSheet;
	
	private Integer ordem;

	public IsentoInputSheetCols() {

	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public BeneficiarioIsentoColType getBeneficiarioIsentoColType() {
		return beneficiarioIsentoColType;
	}

	public void setBeneficiarioIsentoColType(BeneficiarioIsentoColType beneficiarioIsentoColType) {
		this.beneficiarioIsentoColType = beneficiarioIsentoColType;
	}

	public IsentoInputSheet getIsentoInputSheet() {
		return isentoInputSheet;
	}

	public void setIsentoInputSheet(IsentoInputSheet isentoInputSheet) {
		this.isentoInputSheet = isentoInputSheet;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputColsDef == null) ? 0 : arquivoInputColsDef.hashCode());
		result = prime * result + ((beneficiarioIsentoColType == null) ? 0 : beneficiarioIsentoColType.hashCode());
		result = prime * result + ((isentoInputSheet == null) ? 0 : isentoInputSheet.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
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
		IsentoInputSheetCols other = (IsentoInputSheetCols) obj;
		if (arquivoInputColsDef == null) {
			if (other.arquivoInputColsDef != null)
				return false;
		} else if (!arquivoInputColsDef.equals(other.arquivoInputColsDef))
			return false;
		if (beneficiarioIsentoColType != other.beneficiarioIsentoColType)
			return false;
		if (isentoInputSheet == null) {
			if (other.isentoInputSheet != null)
				return false;
		} else if (!isentoInputSheet.equals(other.isentoInputSheet))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}
}
