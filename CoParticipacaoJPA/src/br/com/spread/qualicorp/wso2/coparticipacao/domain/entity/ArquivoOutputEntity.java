package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;

/**
 * The persistent class for the tb_arquivo_output database table.
 * 
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT")
@NamedQuery(
		name = "ArquivoOutputEntity.findAll",
		query = "SELECT a FROM ArquivoOutputEntity a")
public class ArquivoOutputEntity extends ArquivoOutput implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -664494764187857859L;

	public ArquivoOutputEntity() {
	}

	public ArquivoOutputEntity(ArquivoOutputUi ui) {
		super(ui);
	}

	@Column(name = "DESCR_ARQUIVO")
	public String getDescrArquivo() {
		return super.getDescrArquivo();
	}

	@Column(name = "NM_ARQUIVO_FORMAT")
	public String getNameArquivoFormat() {
		return super.getDescrArquivo();
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getEmpresa() {
		return super.getEmpresa();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToMany(
			mappedBy = "arquivoOutput",
			targetEntity = ArquivoOutputSheetEntity.class)
	public List<ArquivoOutputSheet> getArquivoOutputSheets() {
		return super.getArquivoOutputSheets();
	}

	// bi-directional many-to-one association to ArquivoOutputSheet
	@OneToOne(targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

}