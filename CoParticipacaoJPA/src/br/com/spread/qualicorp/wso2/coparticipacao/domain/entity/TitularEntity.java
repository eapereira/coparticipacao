package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LocalDateConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * The persistent class for the tb_titular database table.
 * 
 */
@Entity
@Table(name = "TB_TITULAR")
@NamedQuery(name = "TitularEntity.findAll", query = "SELECT t FROM TitularEntity t")
public class TitularEntity extends Titular implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8445481525982950847L;

	public TitularEntity() {
	}

	public TitularEntity(TitularUi ui) {
		super(ui);
	}

	@Convert(converter = LocalDateConverter.class)
	@Column(name = "DT_ADMISSAO")
	public LocalDate getDtAdmissao() {
		return super.getDtAdmissao();
	}

	@Convert(converter = LocalDateConverter.class)
	@Column(name = "DT_NASCIMENTO")
	public LocalDate getDtNascimento() {
		return super.getDtNascimento();
	}

	@Column(name = "NM_TITULAR")
	public String getNameTitular() {
		return super.getNameTitular();
	}

	@Column(name = "NR_CPF")
	public Long getCpf() {
		return super.getCpf();
	}

	@Column(name = "NR_MATRICULA")
	public Long getMatricula() {
		return super.getMatricula();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "titular", targetEntity = LancamentoEntity.class, cascade = CascadeType.ALL)
	@OrderColumn(name = "INDEX")
	public List<Lancamento> getLancamentos() {
		return super.getLancamentos();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "titular", targetEntity = TitularIsentoEntity.class, cascade = CascadeType.ALL)
	public List<TitularIsento> getTitularIsentos() {
		return super.getTitularIsentos();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "titular", targetEntity = DependenteEntity.class, cascade = CascadeType.ALL)
	@Override
	public List<Dependente> getDependentes() {
		// TODO Auto-generated method stub
		return super.getDependentes();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	@Override
	public Empresa getEmpresa() {
		// TODO Auto-generated method stub
		return super.getEmpresa();
	}

	@Column(name = "NM_LABEL")
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return super.getLabel();
	}

	@Column(name = "NR_REF_CODE")
	@Override
	public Long getReferenceCode() {
		// TODO Auto-generated method stub
		return super.getReferenceCode();
	}

	@Column(name = "NR_MATRICULA_EMPRESA")
	@Override
	public Long getMatriculaEmpresa() {
		// TODO Auto-generated method stub
		return super.getMatriculaEmpresa();
	}

	@Embedded
	@Override
	public BeneficiarioDetail getBeneficiarioDetail() {
		// TODO Auto-generated method stub
		return super.getBeneficiarioDetail();
	}

}