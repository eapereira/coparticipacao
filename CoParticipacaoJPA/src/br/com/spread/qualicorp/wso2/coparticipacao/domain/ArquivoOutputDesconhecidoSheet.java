package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoOutputDesconhecidoSheet extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1439779108912565782L;
	private String nmSheet;
	
	private Integer ordem;
	
	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;

	private ViewDestination viewDestination;

	public ArquivoOutputDesconhecidoSheet() {
		super();
	}

	public String getNmSheet() {
		return nmSheet;
	}

	public void setNmSheet(String nmSheet) {
		this.nmSheet = nmSheet;
	}

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
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
		int result = 1;
		result = prime * result + ((arquivoOutputDesconhecido == null) ? 0 : arquivoOutputDesconhecido.hashCode());
		result = prime * result + ((nmSheet == null) ? 0 : nmSheet.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((viewDestination == null) ? 0 : viewDestination.hashCode());
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
		ArquivoOutputDesconhecidoSheet other = (ArquivoOutputDesconhecidoSheet) obj;
		if (arquivoOutputDesconhecido == null) {
			if (other.arquivoOutputDesconhecido != null)
				return false;
		} else if (!arquivoOutputDesconhecido.equals(other.arquivoOutputDesconhecido))
			return false;
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
		if (viewDestination == null) {
			if (other.viewDestination != null)
				return false;
		} else if (!viewDestination.equals(other.viewDestination))
			return false;
		return true;
	}
}
