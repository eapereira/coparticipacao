package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoSheet;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_DESCONHECIDO")
@NamedQuery(name = "ArquivoOutputDesconhecidoEntity.findAll", query = "SELECT a FROM ArquivoOutputDesconhecidoEntity a")
public class ArquivoOutputDesconhecidoEntity extends ArquivoOutputDesconhecido implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3299819874691191438L;

	public ArquivoOutputDesconhecidoEntity() {
		super();
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

	@Column(name = "NM_ARQUIVO_FORMAT")
	@Override
	public String getNameArquivoFormat() {
		// TODO Auto-generated method stub
		return super.getNameArquivoFormat();
	}

	@Column(name = "NM_DESCR_ARQUIVO")
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoOutputDesconhecido",
			targetEntity = ArquivoOutputDesconhecidoSheetEntity.class)
	@Override
	public List<ArquivoOutputDesconhecidoSheet> getArquivoOutputDesconhecidoSheets() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecidoSheets();
	}
}
