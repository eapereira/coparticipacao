package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.DesconhecidoColTypeConverter;

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
public class ArquivoOutputDesconhecidoColsDefEntity extends ArquivoOutputDesconhecidoColsDef implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5777301581299350578L;

	public ArquivoOutputDesconhecidoColsDefEntity() {
		super();
	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_COLS_DEF")
	@Override
	public ArquivoInputColsDef getArquivoInputColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputColsDef();
	}

	@Convert(converter = DesconhecidoColTypeConverter.class)
	@Column(name = "CD_DESCONHECIDO_COLS_DEF")
	@Override
	public DesconhecidoColType getDesconhecidoColType() {
		// TODO Auto-generated method stub
		return super.getDesconhecidoColType();
	}

	@Column(name = "CD_ORDEM")
	@Override
	public Integer getOrdem() {
		// TODO Auto-generated method stub
		return super.getOrdem();
	}

	@Column(name = "NM_LABEL")
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return super.getLabel();
	}

}
