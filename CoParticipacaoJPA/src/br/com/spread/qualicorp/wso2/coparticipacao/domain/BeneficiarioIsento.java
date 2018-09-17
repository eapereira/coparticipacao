package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class BeneficiarioIsento extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5963228193026141320L;

	private Long matricula;
	
	private String name;
	
	private LocalDate dtNascimento;
	
	private Long cpf;
	
	private IsentoType isentoType;
	
	private Titular titular;
	
	private Dependente dependente;	
	
	private Long matriculaTitular;
	
	private String nameTitular;
	
	private BigDecimal valorIsencao;
	
	public BeneficiarioIsento() {
		
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public IsentoType getIsentoType() {
		return isentoType;
	}

	public void setIsentoType(IsentoType isentoType) {
		this.isentoType = isentoType;
	}

	public Long getMatriculaTitular() {
		return matriculaTitular;
	}

	public void setMatriculaTitular(Long matriculaTitular) {
		this.matriculaTitular = matriculaTitular;
	}

	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}

	public BigDecimal getValorIsencao() {
		return valorIsencao;
	}

	public void setValorIsencao(BigDecimal valorIsencao) {
		this.valorIsencao = valorIsencao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dependente == null) ? 0 : dependente.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((isentoType == null) ? 0 : isentoType.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((matriculaTitular == null) ? 0 : matriculaTitular.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		result = prime * result + ((valorIsencao == null) ? 0 : valorIsencao.hashCode());
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
		BeneficiarioIsento other = (BeneficiarioIsento) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dependente == null) {
			if (other.dependente != null)
				return false;
		} else if (!dependente.equals(other.dependente))
			return false;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (isentoType != other.isentoType)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (matriculaTitular == null) {
			if (other.matriculaTitular != null)
				return false;
		} else if (!matriculaTitular.equals(other.matriculaTitular))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameTitular == null) {
			if (other.nameTitular != null)
				return false;
		} else if (!nameTitular.equals(other.nameTitular))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		if (valorIsencao == null) {
			if (other.valorIsencao != null)
				return false;
		} else if (!valorIsencao.equals(other.valorIsencao))
			return false;
		return true;
	}
}
