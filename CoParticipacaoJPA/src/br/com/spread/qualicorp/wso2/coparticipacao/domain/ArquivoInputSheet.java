package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
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

	private List<Register> registers;

	private LancamentoInputSheet lancamentoInputSheet;

	private List<Regra> regras;
	private List<RegraConditional> regraConditionals;

	private IsentoInputSheet isentoInputSheet;

	public ArquivoInputSheet() {
		super();

		regras = new ArrayList<>();
		regraConditionals = new ArrayList<>();
		registers = new ArrayList<>();
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

	public LancamentoInputSheet getLancamentoInputSheet() {
		return lancamentoInputSheet;
	}

	public void setLancamentoInputSheet(LancamentoInputSheet lancamentoInputSheet) {
		this.lancamentoInputSheet = lancamentoInputSheet;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public void addRegra(Regra regra) {
		getRegras().add(regra);
		regra.setArquivoInputSheet(this);
	}

	public void removeRegra(Regra regra) {
		getRegras().remove(regra);
		regra.setArquivoInputSheet(null);
	}

	public IsentoInputSheet getIsentoInputSheet() {
		return isentoInputSheet;
	}

	public void setIsentoInputSheet(IsentoInputSheet isentoInputSheet) {
		this.isentoInputSheet = isentoInputSheet;
	}

	public List<RegraConditional> getRegraConditionals() {
		return regraConditionals;
	}

	public void setRegraConditionals(List<RegraConditional> regraConditionals) {
		this.regraConditionals = regraConditionals;
	}

	public void addRegraConditional(RegraConditional regraConditional) {
		getRegraConditionals().add(regraConditional);
		regraConditional.setArquivoInputSheet(this);
	}

	public void removeRegraConditional(RegraConditional regraConditional) {
		getRegraConditionals().remove(regraConditional);
		regraConditional.setArquivoInputSheet(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((isentoInputSheet == null) ? 0 : isentoInputSheet.hashCode());
		result = prime * result + ((lancamentoInputSheet == null) ? 0 : lancamentoInputSheet.hashCode());
		result = prime * result + ((registers == null) ? 0 : registers.hashCode());
		result = prime * result + ((regraConditionals == null) ? 0 : regraConditionals.hashCode());
		result = prime * result + ((regras == null) ? 0 : regras.hashCode());
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
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (isentoInputSheet == null) {
			if (other.isentoInputSheet != null)
				return false;
		} else if (!isentoInputSheet.equals(other.isentoInputSheet))
			return false;
		if (lancamentoInputSheet == null) {
			if (other.lancamentoInputSheet != null)
				return false;
		} else if (!lancamentoInputSheet.equals(other.lancamentoInputSheet))
			return false;
		if (registers == null) {
			if (other.registers != null)
				return false;
		} else if (!registers.equals(other.registers))
			return false;
		if (regraConditionals == null) {
			if (other.regraConditionals != null)
				return false;
		} else if (!regraConditionals.equals(other.regraConditionals))
			return false;
		if (regras == null) {
			if (other.regras != null)
				return false;
		} else if (!regras.equals(other.regras))
			return false;
		if (sheetId == null) {
			if (other.sheetId != null)
				return false;
		} else if (!sheetId.equals(other.sheetId))
			return false;
		return true;
	}

	public List<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(List<Register> registers) {
		this.registers = registers;
	}

	public void addRegister(Register register) {
		getRegisters().add(register);
		register.setArquivoInputSheet(this);
	}

	public void removeRegister(Register register) {
		getRegisters().remove(register);
		register.setArquivoInputSheet(null);
	}

}
