package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF")
@NamedQuery(
		name = "ArquivoOutputDesconhecidoColsDefEntity.findAll",
		query = "SELECT a FROM ArquivoOutputDesconhecidoColsDefEntity a")
public class ArquivoOutputDesconhecidoColsDefEntity
		extends ArquivoOutputDesconhecidoColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5777301581299350578L;

	public ArquivoOutputDesconhecidoColsDefEntity() {
		super();
	}

	@Column(name = "CD_ORDEM")
	public Integer getOrdem() {
		return super.getOrdem();
	}

	@Convert(converter = ColDefTypeConverter.class)
	@Column(name = "CD_TYPE")
	public ColDefType getType() {
		return super.getType();
	}

	@Column(name = "NM_COLUMN")
	public String getNameColumn() {
		return super.getNameColumn();
	}

	@Column(name = "VL_LENGTH")
	public Integer getLength() {
		return super.getLength();
	}

	@Column(name = "CD_FORMAT")
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return super.getFormat();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = ArquivoOutputDesconhecidoEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_OUTPUT_DESCONHECIDO")
	@Override
	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecido();
	}

}
