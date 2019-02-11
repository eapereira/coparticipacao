package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.util.StopWatch;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class StopWatchAdapter extends StopWatch {
	public StopWatchAdapter() {
		super();
	}

	public BigDecimal getTotalTimeMinutes() {
		BigDecimal min = new BigDecimal(getTotalTimeSeconds());
		min = min.divide(BigDecimal.valueOf(60.0), 4, RoundingMode.HALF_UP);

		return min;
	}
}
