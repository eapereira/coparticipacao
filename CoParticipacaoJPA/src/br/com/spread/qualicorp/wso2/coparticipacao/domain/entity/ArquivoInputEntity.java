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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
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

	// bi-directional many-to-one association to ArquivoInputColsDef
	@OneToMany(mappedBy = "arquivoInput", targetEntity = ArquivoInputColsDefEntity.class)
	public List<ArquivoInputColsDef> getArquivoInputColsDefs() {
		return super.getArquivoInputColsDefs();
	}

	// bi-directional many-to-one association to Regra
	@OneToMany(mappedBy = "arquivoInput", targetEntity = RegraEntity.class)
	public List<Regra> getRegras() {
		return super.getRegras();
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

	// bi-directional many-to-one association to Empresa
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "arquivoInput", targetEntity = InputTitularIsentoEntity.class)
	@Override
	public InputTitularIsento getInputTitularIsento() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsento();
	}

	// bi-directional many-to-one association to Empresa
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "arquivoInput", targetEntity = InputDependenteIsentoEntity.class)
	@Override
	public InputDependenteIsento getInputDependenteIsento() {
		// TODO Auto-generated method stub
		return super.getInputDependenteIsento();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInput",
			targetEntity = IsentoInputSheetEntity.class)
	@Override
	public List<IsentoInputSheet> getIsentoInputSheets() {
		// TODO Auto-generated method stub
		return super.getIsentoInputSheets();
	}

}