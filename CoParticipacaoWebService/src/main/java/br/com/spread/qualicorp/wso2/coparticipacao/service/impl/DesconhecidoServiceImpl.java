package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DesconhecidoServiceImpl extends
		AbstractServiceImpl<DesconhecidoUi, DesconhecidoEntity, Desconhecido>
		implements DesconhecidoService {

	private static final Logger LOGGER = LogManager
			.getLogger(DesconhecidoServiceImpl.class);

	@Autowired
	private DesconhecidoUiMapper desconhecidoUiMapper;

	@Autowired
	private DesconhecidoEntityMapper desconhecidoEntityMapper;

	@Autowired
	private DesconhecidoDao desconhecidoDao;

	@Autowired
	private DesconhecidoDetailService desconhecidoDetailService;

	public DesconhecidoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DesconhecidoUi createUi() {
		return new DesconhecidoUi();
	}

	@Override
	protected DesconhecidoEntity createEntity() {
		return new DesconhecidoEntity();
	}

	@Override
	protected AbstractDao<DesconhecidoEntity> getDao() {
		return desconhecidoDao;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoUi> getUiMapper() {
		return desconhecidoUiMapper;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoEntity> getEntityMapper() {
		return desconhecidoEntityMapper;
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi
					.setArquivoInput(coParticipacaoContext.getArquivoInputUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			desconhecidoDetailService.createDesconhecidoDetail(
					desconhecidoUi,
					coParticipacaoContext);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(int mes, int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoDao.deleteByMesAndAno(mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
