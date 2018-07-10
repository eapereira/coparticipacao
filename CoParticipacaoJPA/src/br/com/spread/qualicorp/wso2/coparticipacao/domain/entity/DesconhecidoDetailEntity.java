package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_DESCONHECIDO_DETAIL")
@NamedQuery(
		name = "DesconhecidoDetailEntity.findAll",
		query = "SELECT a FROM DesconhecidoDetailEntity a")
public class DesconhecidoDetailEntity extends DesconhecidoDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702414533685970297L;

	public DesconhecidoDetailEntity() {
		super();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = DesconhecidoEntity.class)
	@JoinColumn(name = "ID_DESCONHECIDO")
	@Override
	public Desconhecido getDesconhecido() {
		// TODO Auto-generated method stub
		return super.getDesconhecido();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
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
