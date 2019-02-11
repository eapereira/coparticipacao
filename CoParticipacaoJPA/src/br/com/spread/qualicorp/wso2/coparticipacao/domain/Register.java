package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Register extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6253093043395609171L;

	private Integer cdRegister;

	private String registerName;

	private List<RegisterColumn> registerColumns;

	private ArquivoInputSheet arquivoInputSheet;

	private Integer ordem;

	public Register() {
		super();

		registerColumns = new ArrayList<>();
	}

	public Integer getCdRegister() {
		return cdRegister;
	}

	public void setCdRegister(Integer cdRegister) {
		this.cdRegister = cdRegister;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public List<RegisterColumn> getRegisterColumns() {
		return registerColumns;
	}

	public void setRegisterColumns(List<RegisterColumn> registerColumns) {
		this.registerColumns = registerColumns;
	}

	public void addRegisterColumn(RegisterColumn registerColumn) {
		getRegisterColumns().add(registerColumn);
		registerColumn.setRegister(this);
	}

	public void removeRegisterColumn(RegisterColumn registerColumn) {
		getRegisterColumns().remove(registerColumn);
		registerColumn.setRegister(null);
	}

	public ArquivoInputSheet getArquivoInputSheet() {
		return arquivoInputSheet;
	}

	public void setArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		this.arquivoInputSheet = arquivoInputSheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((registerColumns == null) ? 0 : registerColumns.hashCode());
		result = prime * result + ((cdRegister == null) ? 0 : cdRegister.hashCode());
		result = prime * result + ((registerName == null) ? 0 : registerName.hashCode());
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
		Register other = (Register) obj;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (registerColumns == null) {
			if (other.registerColumns != null)
				return false;
		} else if (!registerColumns.equals(other.registerColumns))
			return false;
		if (cdRegister == null) {
			if (other.cdRegister != null)
				return false;
		} else if (!cdRegister.equals(other.cdRegister))
			return false;
		if (registerName == null) {
			if (other.registerName != null)
				return false;
		} else if (!registerName.equals(other.registerName))
			return false;
		return true;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

}
