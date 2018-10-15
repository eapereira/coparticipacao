package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LancamentoColTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_LANCAMENTO_INPUT_COLS")
@NamedQuery(name = "LancamentoInputColsEntity.findAll", query = "SELECT i FROM LancamentoInputColsEntity i")
public class LancamentoInputColsEntity extends LancamentoInputCols implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8932515202505840134L;

	public LancamentoInputColsEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = LancamentoInputEntity.class)
	@JoinColumn(name = "ID_LANCAMENTO_INPUT")
	@Override
	public LancamentoInput getLancamentoInput() {
		// TODO Auto-generated method stub
		return super.getLancamentoInput();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

	@Convert(converter = LancamentoColTypeConverter.class)
	@Column(name = "CD_LANCAMENTO_COLS_DEF")
	@Override
	public LancamentoColType getLancamentoColType() {
		// TODO Auto-generated method stub
		return super.getLancamentoColType();
	}

}
