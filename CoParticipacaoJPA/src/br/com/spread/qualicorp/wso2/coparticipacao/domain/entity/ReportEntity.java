package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Report;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REPORT")
@NamedQuery(name = "ReportEntity.findAll", query = "SELECT r FROM ReportEntity r")
public class ReportEntity extends Report implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5579434570584681418L;

	public ReportEntity() {
		super();
	}

	@Column(name = "NM_REPORT")
	@Override
	public String getNameReport() {
		// TODO Auto-generated method stub
		return super.getNameReport();
	}

	@Column(name = "DESCR_REPORT")
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

	@Column(name = "NM_OUTPUT_FORMAT")
	@Override
	public String getOutputNameFormat() {
		// TODO Auto-generated method stub
		return super.getOutputNameFormat();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	@Override
	public Empresa getEmpresa() {
		// TODO Auto-generated method stub
		return super.getEmpresa();
	}
}
