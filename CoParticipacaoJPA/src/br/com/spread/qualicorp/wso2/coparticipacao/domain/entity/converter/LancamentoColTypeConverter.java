package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LancamentoColTypeConverter implements AttributeConverter<LancamentoColType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LancamentoColType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public LancamentoColType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return LancamentoColType.parse(value);
		}

		return null;
	}

}
