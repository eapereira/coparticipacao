package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.BeneficiarioIsentoColTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ISENTO_INPUT_SHEET_COLS")
@NamedQuery(name = "IsentoInputSheetColsEntity.findAll", query = "SELECT a FROM IsentoInputSheetColsEntity a")
public class IsentoInputSheetColsEntity extends IsentoInputSheetCols implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1402294827417106007L;

	public IsentoInputSheetColsEntity() {

	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET_COLS_DEF")
	@Override
	public RegisterColumn getRegisterColumn() {
		// TODO Auto-generated method stub
		return super.getRegisterColumn();
	}

	@Convert(converter = BeneficiarioIsentoColTypeConverter.class)
	@Column(name = "CD_BENEFICIARIO_ISENTO_COLS_DEF")
	@Override
	public BeneficiarioIsentoColType getBeneficiarioIsentoColType() {
		// TODO Auto-generated method stub
		return super.getBeneficiarioIsentoColType();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = IsentoInputSheetEntity.class)
	@JoinColumn(name = "ID_ISENTO_INPUT_SHEET")
	@Override
	public IsentoInputSheet getIsentoInputSheet() {
		// TODO Auto-generated method stub
		return super.getIsentoInputSheet();
	}
}
