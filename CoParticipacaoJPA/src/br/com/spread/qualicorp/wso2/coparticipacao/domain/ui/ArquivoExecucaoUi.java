package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ArquivoExecucaoUi extends ArquivoExecucao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1073694418340931223L;

	public ArquivoExecucaoUi() {
		super();
	}

	public BigDecimal getExecutionTime() {
		BigDecimal executionTime;

		if (getFinnished() != null) {
			executionTime = new BigDecimal(getStarted().until(getFinnished(), ChronoUnit.MILLIS));
			executionTime = executionTime.divide(BigDecimal.valueOf(1000));// secs

			if (executionTime.doubleValue() > 60) {
				executionTime = executionTime.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP); // min
			} else {
				executionTime = executionTime.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP); // min
			}
		} else {
			executionTime = BigDecimal.ZERO;
		}

		return executionTime;
	}

	public String getSimpleNameArquivoInput() {
		int pos = getNameArquivoInput().lastIndexOf(File.separator);
		String name = getNameArquivoInput().substring(pos + 1);

		return name;
	}

}
