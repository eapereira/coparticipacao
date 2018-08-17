package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao.DesconhecidoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.DesconhecidoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.impl.DesconhecidoServiceImpl;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DesconhecidoBatchServiceImpl extends
		AbstractBatchServiceImpl<DesconhecidoUi, DesconhecidoEntity, Desconhecido> implements DesconhecidoBatchService {

	private static final Logger LOGGER = LogManager.getLogger(DesconhecidoServiceImpl.class);

	@Autowired
	private DesconhecidoJdbcDao desconhecidoJdbcDao;

	@Autowired
	private DesconhecidoUiMapper desconhecidoUiMapper;

	@Autowired
	private DesconhecidoEntityMapper desconhecidoEntityMapper;

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
		return null;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoUi> getUiMapper() {
		return desconhecidoUiMapper;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoEntity> getEntityMapper() {
		return desconhecidoEntityMapper;
	}

	@Override
	protected AbstractJdbcDao<DesconhecidoEntity> getJdbcDao() {
		return desconhecidoJdbcDao;
	}

	@Override
	protected void logBatchInfo(DesconhecidoUi desconhecidoUi) throws ServiceException {
		LOGGER.debug("ID:........................... [{}]", desconhecidoUi.getId());
		LOGGER.debug("CD_CONTRATO:.................. [{}]", desconhecidoUi.getContrato().getCdContrato());
		LOGGER.debug("CD_MES:....................... [{}]", desconhecidoUi.getMes());
		LOGGER.debug("CD_ANO:....................... [{}]", desconhecidoUi.getAno());

		LOGGER.debug("DESCONHECIDO.NR_CPF:.......... [{}]", desconhecidoUi.getCpf());
		LOGGER.debug("DESCONHECIDO.NAME:............ [{}]", desconhecidoUi.getNameBeneficiario());
		LOGGER.debug("DESCONHECIDO.DT_NASCIMENTO:... [{}]", desconhecidoUi.getDtNascimento());
		LOGGER.debug("DESCONHECIDO.NR_MATRICULA:.... [{}]", desconhecidoUi.getMatricula());

		LOGGER.debug("DESCONHECIDO.DT_ADMISSAO:..... [{}]", desconhecidoUi.getDtAdmissao());
		LOGGER.debug("DESCONHECIDO.NR_CODE_REF:..... [{}]", desconhecidoUi.getReferenceCode());
	}

}
