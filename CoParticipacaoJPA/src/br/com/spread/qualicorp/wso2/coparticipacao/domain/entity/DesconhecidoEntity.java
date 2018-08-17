package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LocalDateConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_DESCONHECIDO")
@NamedQuery(name = "DesconhecidoEntity.findAll", query = "SELECT a FROM DesconhecidoEntity a")
public class DesconhecidoEntity extends Desconhecido implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2860052398629057173L;

	public DesconhecidoEntity() {
		super();
	}

	@Column(name = "CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
	}

	@Column(name = "CD_ANO")
	@Override
	public Integer getAno() {
		// TODO Auto-generated method stub
		return super.getAno();
	}

	@Column(name = "NM_BENEFICIARIO")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
	}

	@Column(name = "NR_CPF")
	@Override
	public Long getCpf() {
		// TODO Auto-generated method stub
		return super.getCpf();
	}

	@Column(name = "NR_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

	@Column(name = "DT_NASCIMENTO")
	@Override
	public LocalDate getDtNascimento() {
		// TODO Auto-generated method stub
		return super.getDtNascimento();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	@Override
	public Contrato getContrato() {
		// TODO Auto-generated method stub
		return super.getContrato();
	}

	@Column(name = "VL_PRINCIPAL")
	@Override
	public BigDecimal getValorPrincipal() {
		// TODO Auto-generated method stub
		return super.getValorPrincipal();
	}

	@Column(name = "DT_ADMISSAO")
	@Convert(converter = LocalDateConverter.class)
	@Override
	public LocalDate getDtAdmissao() {
		// TODO Auto-generated method stub
		return super.getDtAdmissao();
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

}
