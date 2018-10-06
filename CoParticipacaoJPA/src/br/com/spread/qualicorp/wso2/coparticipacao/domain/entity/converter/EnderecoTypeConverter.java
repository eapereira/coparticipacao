package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.EnderecoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class EnderecoTypeConverter implements AttributeConverter<EnderecoType, String> {

	private static final Logger LOGGER = LogManager.getLogger(EnderecoTypeConverter.class);

	@Override
	public String convertToDatabaseColumn(EnderecoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public EnderecoType convertToEntityAttribute(String value) {
		if (value != null) {
			LOGGER.trace("Value received [{}]:", value);
			return EnderecoType.parse(value);
		}

		return null;
	}

}
