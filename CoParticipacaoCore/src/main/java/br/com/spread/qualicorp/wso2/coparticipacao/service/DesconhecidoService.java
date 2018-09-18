package br.com.spread.qualicorp.wso2.coparticipacao.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoService extends AbstractService<DesconhecidoUi> {
	void createDesconhecido(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi)
			throws ServiceException;

	void createDesconhecido(CoParticipacaoContext coParticipacaoContext, TitularUi titularUi) throws ServiceException;

	void createDesconhecido(CoParticipacaoContext coParticipacaoContext, DependenteUi dependenteUi)
			throws ServiceException;

	void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException;

	List<DesconhecidoUi> listByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException;

	void writeDesconhecidosFile(CoParticipacaoContext coParticipacaoContext) throws ServiceException;

	void createDesconhecido(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException;

	Object getValueFromField(DesconhecidoUi desconhecidoUi, DesconhecidoColType desconhecidoColType)
			throws ServiceException;

	List<DesconhecidoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException;

	List<DesconhecidoUi> listByEmpresaIdAndUseType(EmpresaUi empresaUi, UseType useType) throws ServiceException;

}
