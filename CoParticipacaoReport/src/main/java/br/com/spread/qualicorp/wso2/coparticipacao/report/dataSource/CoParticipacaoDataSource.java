package br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class CoParticipacaoDataSource<T> implements JRDataSource {

	private List<T> data;

	private T register;

	private int cursor;

	public CoParticipacaoDataSource() throws JRException {
		data = buildData();
		cursor = NumberUtils.INTEGER_ZERO;
	}

	public CoParticipacaoDataSource(List<T> data) {
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
