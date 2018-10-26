package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoInputSheet extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7409216240103979586L;

	private ArquivoInput arquivoInput;

	private Integer sheetId;

	/**
	 * Se não existir o CD_CONTRATO dentro do arquivo, podesse chumbar um padrão
	 * para todos os registros
	 */
	private Contrato contrato;

	private List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefs;

	public ArquivoInputSheet() {
		super();
	}

	public ArquivoInput getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(ArquivoInput arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public List<ArquivoInputSheetColsDef> getArquivoInputSheetColsDefs() {
		return arquivoInputSheetColsDefs;
	}

	public void setArquivoInputSheetColsDefs(List<ArquivoInputSheetColsDef> arquivoInputSheetColsDefs) {
		this.arquivoInputSheetColsDefs = arquivoInputSheetColsDefs;
	}

	public void addArquivoInputSheetColsDefs(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		getArquivoInputSheetColsDefs().add(arquivoInputSheetColsDef);
		arquivoInputSheetColsDef.setArquivoInputSheet(this);
	}

	public void removeArquivoInputSheetColsDefs(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		getArquivoInputSheetColsDefs().remove(arquivoInputSheetColsDef);
		arquivoInputSheetColsDef.setArquivoInputSheet(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arquivoInputSheetColsDefs == null) ? 0 : arquivoInputSheetColsDefs.hashCode());
		result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
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
		ArquivoInputSheet other = (ArquivoInputSheet) obj;
		if (arquivoInputSheetColsDefs == null) {
			if (other.arquivoInputSheetColsDefs != null)
				return false;
		} else if (!arquivoInputSheetColsDefs.equals(other.arquivoInputSheetColsDefs))
			return false;
		if (sheetId == null) {
			if (other.sheetId != null)
				return false;
		} else if (!sheetId.equals(other.sheetId))
			return false;
		return true;
	}

	/**
	 * Se não existir o CD_CONTRATO dentro do arquivo, podesse chumbar um padrão
	 * para todos os registros
	 * 
	 * @return O {@link Contrato} que deve ser usado como padrão se não existir
	 *         nhuma coluna no arquivo com o <b>CD_CONTRATO</b>.
	 */
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
