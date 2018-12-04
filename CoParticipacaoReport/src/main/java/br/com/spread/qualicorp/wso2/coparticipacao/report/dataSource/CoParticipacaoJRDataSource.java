package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CoParticipacaoJRDataSource<T> implements JRDataSource {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoJRDataSource.class);

	private List<T> data;

	private T register;

	private int cursor;

	public CoParticipacaoJRDataSource() throws JRException {
		data = buildData();
		cursor = NumberUtils.INTEGER_ZERO;
	}

	public CoParticipacaoJRDataSource(List<T> data) {
		this.data = data;
		cursor = NumberUtils.INTEGER_ZERO;
	}

	protected abstract List<T> buildData() throws JRException;

	@Override
	public boolean next() throws JRException {
		int pos = NumberUtils.INTEGER_ZERO;

		if (!data.isEmpty()) {
			if (cursor < data.size()) {
				for (T view : data) {
					if (pos == cursor) {
						register = view;
						cursor++;
						return true;
					}

					pos++;
				}
			}
		}

		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object value;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Searching for field's name[{}]:", jrField.getName());

			value = PropertyUtils.getProperty(getRegister(), jrField.getName());

			LOGGER.info("Field's name[{}] has value[{}]:", jrField.getName(), value);

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new JRException(e);
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	protected T getRegister() {
		return register;
	}

	protected void setRegister(T register) {
		this.register = register;
	}

}
