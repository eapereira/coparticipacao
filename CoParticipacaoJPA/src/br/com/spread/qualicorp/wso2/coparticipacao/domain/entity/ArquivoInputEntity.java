package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ArquivoTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.UseTypeConverter;

/**
 * The persistent class for the tb_arquivo_input database table.
 * 
 */
@Entity
@Table(name = "TB_ARQUIVO_INPUT")
@NamedQuery(name = "ArquivoInputEntity.findAll", query = "SELECT a FROM ArquivoInputEntity a")
public class ArquivoInputEntity extends ArquivoInput implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6826372027476496530L;

	public ArquivoInputEntity() {
	}

	@Column(name = "DESCR_ARQUIVO")
	public String getDescrArquivo() {
		return super.getDescrArquivo();
	}

	@Column(name = "NM_ARQUIVO_REGEXP")
	public String getNameArquivoRegexp() {
		return super.getNameArquivoRegexp();
	}

	@Convert(converter = ArquivoTypeConverter.class)
	@Column(name = "TP_ARQUIVO")
	public ArquivoType getArquivoType() {
		return super.getArquivoType();
	}

	@Convert(converter = UseTypeConverter.class)
	@Column(name = "TP_USE")
	public UseType getUseType() {
		return super.getUseType();
	}

	// bi-directional many-to-one association to Empresa
	@OneToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	public Contrato getContrato() {
		return super.getContrato();
	}

	@Column(name = "NUM_SKIP_LINES")
	@Override
	public Integer getSkipLines() {
		return super.getSkipLines();
	}

	@Column(name = "NUM_DEFAULT_LINE_LENGTH")
	@Override
	public Integer getDefaultLineLength() {
		// TODO Auto-generated method stub
		return super.getDefaultLineLength();
	}

	// bi-directional many-to-one association to Regra
	@OneToOne(
			mappedBy = "arquivoInput",
			cascade = CascadeType.ALL,
			targetEntity = ArquivoOutputDesconhecidoEntity.class)
	@Override
	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputDesconhecido();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInput",
			cascade = CascadeType.ALL,
			targetEntity = ArquivoOutputEntity.class)
	@Override
	public List<ArquivoOutput> getArquivoOutputs() {
		// TODO Auto-generated method stub
		return super.getArquivoOutputs();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInput",
			targetEntity = ArquivoInputSheetEntity.class)
	@Override
	public List<ArquivoInputSheet> getArquivoInputSheets() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheets();
	}

}