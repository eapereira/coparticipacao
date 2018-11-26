package br.com.spread.qualicorp.wso2.coparticipacao.report.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco.CelpeSaudeResumoViewEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco.CelpeSaudeResumoViewUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoView;
import br.com.spread.qualicorp.wso2.coparticipacao.report.dao.CelpeSaudeResumoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeResumoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.report.service.CelpeSaudeResumoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.AbstractServiceImpl;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class CelpeSaudeResumoServiceImpl
		extends AbstractServiceImpl<CelpeSaudeResumoViewUi, CelpeSaudeResumoViewEntity, CelpeSaudeResumoView>
		implements CelpeSaudeResumoService {

	private static final Logger LOGGER = LogManager.getLogger(CelpeSaudeResumoServiceImpl.class);

	@Autowired
	private CelpeSaudeResumoViewUiMapper uiMapper;

	@Autowired
	private CelpeSaudeResumoViewEntityMapper entityMapper;

	@Autowired
	private CelpeSaudeResumoDao celpeSaudeResumoDao;

	@Autowired
	private CelpeSaudeResumoDetailService celpeSaudeResumoDetailService;

	public List<CelpeSaudeResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis;
		LocalDate date;

		try {
			LOGGER.info("BEGIN");

			celpeSaudeResumoViewUis = entityToUi(celpeSaudeResumoDao.listByMesAndAno(mes, ano));

			date = DateUtils.stringToDate(String.format("01/%d/%d", mes, ano), "dd/MM/yyyy");

			for (CelpeSaudeResumoViewUi celpeSaudeResumoViewUi : celpeSaudeResumoViewUis) {
				// Existe apenas um elemento:
				celpeSaudeResumoViewUi
						.setCelpeSaudeResumoDetailViewUis(celpeSaudeResumoDetailService.listByMesAndAno(mes, ano));

				celpeSaudeResumoViewUi.setValorAnterior(findValorPrincipalAnteriorByMesAndAno(mes, ano));
				celpeSaudeResumoViewUi.setCompetencia(DateUtils.dateToString(date, "MMMMM/yyyy"));

				date = date.minusMonths(1);
				celpeSaudeResumoViewUi.setCompetenciaAnterior(DateUtils.dateToString(date, "MMMMM/yyyy"));
			}

			LOGGER.info("END");
			return celpeSaudeResumoViewUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private BigDecimal findValorPrincipalAnteriorByMesAndAno(Integer mes, Integer ano) throws ServiceException {
		List<CelpeSaudeResumoViewUi> celpeSaudeResumoViewUis;
		BigDecimal valorPrincipalAnterior = BigDecimal.ZERO;

		try {
			LOGGER.info("BEGIN");

			celpeSaudeResumoViewUis = entityToUi(celpeSaudeResumoDao.listByMesAndAno(mes - 1, ano));

			for (CelpeSaudeResumoViewUi celpeSaudeResumoViewUi : celpeSaudeResumoViewUis) {
				valorPrincipalAnterior = valorPrincipalAnterior.add(celpeSaudeResumoViewUi.getValor());
			}

			LOGGER.info("END");
			return valorPrincipalAnterior;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	@Override
	protected CelpeSaudeResumoViewUi createUi() {
		return new CelpeSaudeResumoViewUi();
	}

	@Override
	protected CelpeSaudeResumoViewEntity createEntity() {
		return new CelpeSaudeResumoViewEntity();
	}

	@Override
	protected AbstractDao<CelpeSaudeResumoViewEntity> getDao() {
		return celpeSaudeResumoDao;
	}

	@Override
	protected AbstractMapper<CelpeSaudeResumoView, CelpeSaudeResumoViewUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<CelpeSaudeResumoView, CelpeSaudeResumoViewEntity> getEntityMapper() {
		return entityMapper;
	}
}
