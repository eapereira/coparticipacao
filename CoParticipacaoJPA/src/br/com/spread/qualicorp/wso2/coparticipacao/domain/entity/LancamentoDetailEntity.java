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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LocalDateConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_LANCAMENTO_DETAIL")
@NamedQuery(
		name = "LancamentoDetailEntity.findAll",
		query = "SELECT l FROM LancamentoDetailEntity l")
public class LancamentoDetailEntity extends LancamentoDetail
		implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6962258728964981912L;

	public LancamentoDetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LancamentoDetailEntity(LancamentoDetailUi ui) {
		super(ui);
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

	// bi-directional many-to-one association to ViewDestination
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

	@Column(name = "VL_INT")
	@Override
	public Integer getIntValue() {
		// TODO Auto-generated method stub
		return super.getIntValue();
	}

	@Column(name = "VL_LONG")
	@Override
	public Long getLongValue() {
		// TODO Auto-generated method stub
		return super.getLongValue();
	}

	@Column(name = "VL_DOUBLE")
	@Override
	public BigDecimal getBigDecimalValue() {
		// TODO Auto-generated method stub
		return super.getBigDecimalValue();
	}

	@Convert(converter = LocalDateConverter.class)
	@Column(name = "VL_DATE")
	@Override
	public LocalDate getDateValue() {
		// TODO Auto-generated method stub
		return super.getDateValue();
	}

	@Column(name = "VL_STRING")
	@Override
	public String getStringValue() {
		// TODO Auto-generated method stub
		return super.getStringValue();
	}

}
