package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LocalDateTimeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.StatusExecucaoTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_EXECUCAO")
@NamedQuery(name = "ArquivoExecucaoEntity.findAll", query = "SELECT a FROM ArquivoExecucaoEntity a")
public class ArquivoExecucaoEntity extends ArquivoExecucao implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5793356281165193611L;

	public ArquivoExecucaoEntity() {
		super();
	}

	@Column(name = "CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
	}

	@Column(name = "CD_ANO")
	@Override
	public Integer getAno() {
		// TODO Auto-generated method stub
		return super.getAno();
	}

	@Convert(converter = StatusExecucaoTypeConverter.class)
	@Column(name = "TP_STATUS")
	@Override
	public StatusExecucaoType getStatusExecucaoType() {
		// TODO Auto-generated method stub
		return super.getStatusExecucaoType();
	}

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DT_STARTED")
	@Override
	public LocalDateTime getStarted() {
		// TODO Auto-generated method stub
		return super.getStarted();
	}

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DT_FINNISHED")
	@Override
	public LocalDateTime getFinnished() {
		// TODO Auto-generated method stub
		return super.getFinnished();
	}

	@Column(name = "NM_ARQUIVO_INPUT")
	@Override
	public String getNameArquivoInput() {
		// TODO Auto-generated method stub
		return super.getNameArquivoInput();
	}

	@Column(name = "NM_ARQUIVO_OUTPUT")
	@Override
	public String getNameArquivoOutput() {
		// TODO Auto-generated method stub
		return super.getNameArquivoOutput();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	@Override
	public Contrato getContrato() {
		// TODO Auto-generated method stub
		return super.getContrato();
	}

	@Column(name = "DESCR_ERROR_MESSAGE")
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return super.getErrorMessage();
	}

}
