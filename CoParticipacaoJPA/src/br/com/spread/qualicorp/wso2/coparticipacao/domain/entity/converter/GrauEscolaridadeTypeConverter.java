package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.GrauEscolaridadeType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class GrauEscolaridadeTypeConverter implements AttributeConverter<GrauEscolaridadeType, Integer> {

	private static final Logger LOGGER = LogManager.getLogger(GrauEscolaridadeTypeConverter.class);

	@Override
	public Integer convertToDatabaseColumn(GrauEscolaridadeType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public GrauEscolaridadeType convertToEntityAttribute(Integer value) {
		if (value != null) {
			LOGGER.trace("Value received [{}]:", value);
			return GrauEscolaridadeType.parse(value);
		}

		return null;
	}

}
