package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGRA_RESULT")
@NamedQuery(name = "RegraResultEntity.findAll", query = "SELECT r FROM RegraResultEntity r")
public class RegraResultEntity extends RegraResult implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5759733169358381215L;

	public RegraResultEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegraEntity.class)
	@JoinColumn(name = "ID_REGRA")
	@Override
	public Regra getRegra() {
		return super.getRegra();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET_COLS_DEF")
	@Override
	public RegisterColumn getRegisterColumn() {
		// TODO Auto-generated method stub
		return super.getRegisterColumn();
	}

}
