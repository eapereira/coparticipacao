package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_arquivo_output_sheet database table.
 * 
 */
public abstract class ArquivoOutputSheet extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private String nmSheet;
	private ArquivoOutput arquivoOutput;

	private ViewDestination viewDestination;
	
	private Integer ordem;

	public ArquivoOutputSheet() {
	}

	public ArquivoOutputSheet(ArquivoOutputSheet entity) {
		super(entity);
	}

	public String getNmSheet() {
		return nmSheet;
	}

	public void setNmSheet(String nmSheet) {
		this.nmSheet = nmSheet;
	}

	public ArquivoOutput getArquivoOutput() {
		return arquivoOutput;
	}

	public void setArquivoOutput(ArquivoOutput arquivoOutput) {
		this.arquivoOutput = arquivoOutput;
	}

	public ViewDestination getViewDestination() {
		return viewDestination;
	}

	public void setViewDestination(ViewDestination viewDestination) {
		this.viewDestination = viewDestination;
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
		int result = super.hashCode();
		result = prime * result + ((nmSheet == null) ? 0 : nmSheet.hashCode());
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
		ArquivoOutputSheet other = (ArquivoOutputSheet) obj;
		if (nmSheet == null) {
			if (other.nmSheet != null)
				return false;
		} else if (!nmSheet.equals(other.nmSheet))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}


}