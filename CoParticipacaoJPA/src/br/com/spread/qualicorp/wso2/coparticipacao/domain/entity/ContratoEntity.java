package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.UseTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * The persistent class for the tb_contrato database table.
 * 
 */
@Entity
@Table(name = "TB_CONTRATO")
@NamedQuery(name = "ContratoEntity.findAll", query = "SELECT c FROM ContratoEntity c")
public class ContratoEntity extends Contrato implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5756666657376194901L;

	public ContratoEntity() {
	}

	public ContratoEntity(ContratoUi ui) {
		super(ui);
	}

	@Column(name = "CD_CONTRATO")
	public String getCdContrato() {
		return super.getCdContrato();
	}

	@Column(name = "NM_CONTRATO")
	public String getNameContrato() {
		return super.getNameContrato();
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getEmpresa() {
		return super.getEmpresa();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToMany(mappedBy = "contrato", targetEntity = LancamentoEntity.class)
	public List<Lancamento> getLancamentos() {
		return super.getLancamentos();
	}

	// bi-directional many-to-one association to Lancamento
	@OneToOne(mappedBy = "contrato", targetEntity = ArquivoInputEntity.class)
	public ArquivoInput getArquivoInput() {
		return super.getArquivoInput();
	}

	@Column(name = "TP_USO")
	@Convert(converter = UseTypeConverter.class)
	@Override
	public UseType getUseType() {
		// TODO Auto-generated method stub
		return super.getUseType();
	}

	@Column(name = "CD_SPREADSHEET_ALL_PAGES")
	@Override
	public Boolean isSpreadsheetAllPages() {
		// TODO Auto-generated method stub
		return super.isSpreadsheetAllPages();
	}
}