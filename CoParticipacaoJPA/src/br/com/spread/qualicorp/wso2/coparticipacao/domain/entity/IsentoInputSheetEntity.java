package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.IsentoTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ISENTO_INPUT_SHEET")
@NamedQuery(name = "IsentoInputSheetEntity.findAll", query = "SELECT a FROM IsentoInputSheetEntity a")
public class IsentoInputSheetEntity extends IsentoInputSheet implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 824787857355942319L;

	public IsentoInputSheetEntity() {
		super();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "isentoInputSheet",
			targetEntity = IsentoInputSheetColsEntity.class)
	@Override
	public List<IsentoInputSheetCols> getIsentoInputSheetCols() {
		// TODO Auto-generated method stub
		return super.getIsentoInputSheetCols();
	}

	@Convert(converter = IsentoTypeConverter.class)
	@Column(name = "TP_ISENTO")
	@Override
	public IsentoType getIsentoType() {
		// TODO Auto-generated method stub
		return super.getIsentoType();
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputSheetEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET")
	@Override
	public ArquivoInputSheet getArquivoInputSheet() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheet();
	}
}
