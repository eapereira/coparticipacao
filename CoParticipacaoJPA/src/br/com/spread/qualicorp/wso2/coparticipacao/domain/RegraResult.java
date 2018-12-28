package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class RegraResult extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5033612908963955438L;

	private Regra regra;

	private ArquivoInputSheetColsDef arquivoInputSheetColsDef;

	public RegraResult() {

	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		return arquivoInputSheetColsDef;
	}

	public void setArquivoInputSheetColsDef(
			ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		this.arquivoInputSheetColsDef = arquivoInputSheetColsDef;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoInputSheetColsDef == null) ? 0
				: arquivoInputSheetColsDef.hashCode());
		result = prime * result + ((regra == null) ? 0 : regra.hashCode());
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
		RegraResult other = (RegraResult) obj;
		if (arquivoInputSheetColsDef == null) {
			if (other.arquivoInputSheetColsDef != null)
				return false;
		} else if (!arquivoInputSheetColsDef.equals(other.arquivoInputSheetColsDef))
			return false;
		if (regra == null) {
			if (other.regra != null)
				return false;
		} else if (!regra.equals(other.regra))
			return false;
		return true;
	}
}
