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

	private ArquivoInputSheetColsDef arquivoInputSheetColsDef;

	private BeneficiarioIsentoColType beneficiarioIsentoColType;

	private IsentoInputSheet isentoInputSheet;

	public IsentoInputSheetCols() {

	}

	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		return arquivoInputSheetColsDef;
	}

	public void setArquivoInputSheetColsDef(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		this.arquivoInputSheetColsDef = arquivoInputSheetColsDef;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoInputSheetColsDef == null) ? 0 : arquivoInputSheetColsDef.hashCode());
		result = prime * result + ((beneficiarioIsentoColType == null) ? 0 : beneficiarioIsentoColType.hashCode());
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
		IsentoInputSheetCols other = (IsentoInputSheetCols) obj;
		if (arquivoInputSheetColsDef == null) {
			if (other.arquivoInputSheetColsDef != null)
				return false;
		} else if (!arquivoInputSheetColsDef.equals(other.arquivoInputSheetColsDef))
			return false;
		if (beneficiarioIsentoColType != other.beneficiarioIsentoColType)
			return false;
		return true;
	}

}
