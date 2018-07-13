package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LocalDateConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;

/**
 * The persistent class for the tb_dependente database table.
 * 
 */
@Entity
@Table(name = "TB_DEPENDENTE")
@NamedQuery(
		name = "DependenteEntity.findAll",
		query = "SELECT d FROM DependenteEntity d")
public class DependenteEntity extends Dependente {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7672369366126151161L;

	public DependenteEntity() {
	}

	public DependenteEntity(DependenteUi ui) {
		super(ui);
	}

	@Convert(converter = LocalDateConverter.class)
	@Column(name = "DT_NASCIMENTO")
	public LocalDate getDtNascimento() {
		return super.getDtNascimento();
	}

	@Column(name = "NM_DEPENDENTE")
	public String getNameDependente() {
		return super.getNameDependente();
	}

	@Column(name = "NR_CPF")
	public String getCpf() {
		return super.getCpf();
	}

	@Convert(converter = DependenteTypeConverter.class)
	@Column(name = "TP_DEPENDENTE")
	public BeneficiarioType getTpDependente() {
		return super.getTpDependente();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(
			mappedBy = "dependente",
			targetEntity = DependenteIsentoEntity.class)
	public List<DependenteIsento> getDependenteIsentos() {
		return super.getDependenteIsentos();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "dependente", targetEntity = LancamentoEntity.class)
	public List<Lancamento> getLancamentos() {
		return super.getLancamentos();
	}

	// bi-directional many-to-one association to RegraOperation
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TitularEntity.class)
	@JoinColumn(name = "ID_TITULAR")
	@Override
	public Titular getTitular() {
		return super.getTitular();
	}

	@Column(name = "NR_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

}