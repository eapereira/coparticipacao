package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_DESCONHECIDO_SHEET")
@NamedQuery(
		name = "ArquivoOutputDesconhecidoSheetEntity.findAll",
		query = "SELECT a FROM ArquivoOutputDesconhecidoSheetEntity a")
public class ArquivoOutputDesconhecidoSheetEntity extends ArquivoOutputDesconhecidoSheet implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3473106230449633150L;

	public ArquivoOutputDesconhecidoSheetEntity() {
		super();
	}

	@Column(name = "NM_SHEET")
	@Override
	public String getNmSheet() {
		// TODO Auto-generated method stub
		return super.getNmSheet();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoOutputDesconhecidoEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT_DESCONHECIDO")
	@Override
	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecido();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ViewDestinationEntity.class)
	@JoinColumn(name = "ID_VIEW_DESTINATION")
	@Override
	public ViewDestination getViewDestination() {
		// TODO Auto-generated method stub
		return super.getViewDestination();
	}

	@Column(name = "CD_ORDEM")
	@Override
	public Integer getOrdem() {
		// TODO Auto-generated method stub
		return super.getOrdem();
	}
}
