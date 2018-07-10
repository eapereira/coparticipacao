package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LancamentoDetail extends AbstractDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486626828155162258L;

	private Lancamento lancamento;
	
	private ArquivoInputColsDef arquivoInputColsDef;
	
	private Integer intValue;
	
	private Long longValue;
	
	private BigDecimal bigDecimalValue;
	
	private LocalDate dateValue;
	
	private String stringValue;
	
	public LancamentoDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LancamentoDetail(AbstractDomain abstractDomain) {
		super(abstractDomain);
		// TODO Auto-generated constructor stub
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

	public LocalDate getDateValue() {
		return dateValue;
	}

	public void setDateValue(LocalDate dateValue) {
		this.dateValue = dateValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
}
