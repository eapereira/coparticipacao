package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_LANCAMENTO_DETAIL")
@NamedQuery(name = "LancamentoDetailEntity.findAll", query = "SELECT l FROM LancamentoDetailEntity l")
public class LancamentoDetailEntity extends LancamentoDetail implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6962258728964981912L;

	public LancamentoDetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = LancamentoEntity.class)
	@JoinColumn(name = "ID_LANCAMENTO")
	@Override
	public Lancamento getLancamento() {
		// TODO Auto-generated method stub
		return super.getLancamento();
	}

}
