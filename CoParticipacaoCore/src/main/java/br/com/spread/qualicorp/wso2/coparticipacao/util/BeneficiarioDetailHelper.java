package br.com.spread.qualicorp.wso2.coparticipacao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DadosBancarios;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Endereco;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Telefone;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Transferencia;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeneficiarioDetailHelper {

	private static final Logger LOGGER = LogManager.getLogger(BeneficiarioDetailHelper.class);

	public static void copyBeneficiarioDetail(
			BeneficiarioDetail beneficiarioDetailDest,
			BeneficiarioDetail beneficiarioDetailOrigin) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (beneficiarioDetailOrigin.getBloqEmpresaInadimplencia() != null) {
				beneficiarioDetailDest
						.setBloqEmpresaInadimplencia(beneficiarioDetailOrigin.getBloqEmpresaInadimplencia());
			}

			if (beneficiarioDetailOrigin.getCardif() != null) {
				beneficiarioDetailDest.setCardif(beneficiarioDetailOrigin.getCardif());
			}

			if (beneficiarioDetailOrigin.getCarteiraIdentificacao() != null) {
				beneficiarioDetailDest.setCarteiraIdentificacao(beneficiarioDetailOrigin.getCarteiraIdentificacao());
			}

			if (beneficiarioDetailOrigin.getCategoria() != null) {
				beneficiarioDetailDest.setCategoria(beneficiarioDetailOrigin.getCategoria());
			}

			if (beneficiarioDetailOrigin.getCbo() != null) {
				beneficiarioDetailDest.setCbo(beneficiarioDetailOrigin.getCbo());
			}

			if (beneficiarioDetailOrigin.getCdMotivoExclusao() != null) {
				beneficiarioDetailDest.setCdMotivoExclusao(beneficiarioDetailOrigin.getCdMotivoExclusao());
			}

			if (beneficiarioDetailOrigin.getCdOperacao() != null) {
				beneficiarioDetailDest.setCdOperacao(beneficiarioDetailOrigin.getCdOperacao());
			}

			if (beneficiarioDetailOrigin.getCdPaisOrigem() != null) {
				beneficiarioDetailDest.setCdPaisOrigem(beneficiarioDetailOrigin.getCdPaisOrigem());
			}

			if (beneficiarioDetailOrigin.getCdPlanoAnteriorSas() != null) {
				beneficiarioDetailDest.setCdPlanoAnteriorSas(beneficiarioDetailOrigin.getCdPlanoAnteriorSas());
			}

			if (beneficiarioDetailOrigin.getCdProduto() != null) {
				beneficiarioDetailDest.setCdProduto(beneficiarioDetailOrigin.getCdProduto());
			}

			if (beneficiarioDetailOrigin.getCdProfissao() != null) {
				beneficiarioDetailDest.setCdProfissao(beneficiarioDetailOrigin.getCdProfissao());
			}

			if (beneficiarioDetailOrigin.getCertidaoNascimento() != null) {
				beneficiarioDetailDest.setCertidaoNascimento(beneficiarioDetailOrigin.getCertidaoNascimento());
			}

			if (beneficiarioDetailOrigin.getCid01() != null) {
				beneficiarioDetailDest.setCid01(beneficiarioDetailOrigin.getCid01());
			}

			if (beneficiarioDetailOrigin.getCid02() != null) {
				beneficiarioDetailDest.setCid02(beneficiarioDetailOrigin.getCid02());
			}

			if (beneficiarioDetailOrigin.getCid03() != null) {
				beneficiarioDetailDest.setCid03(beneficiarioDetailOrigin.getCid03());
			}

			if (beneficiarioDetailOrigin.getCid04() != null) {
				beneficiarioDetailDest.setCid04(beneficiarioDetailOrigin.getCid04());
			}

			if (beneficiarioDetailOrigin.getCid05() != null) {
				beneficiarioDetailDest.setCid05(beneficiarioDetailOrigin.getCid05());
			}

			if (beneficiarioDetailOrigin.getCid06() != null) {
				beneficiarioDetailDest.setCid06(beneficiarioDetailOrigin.getCid06());
			}

			if (beneficiarioDetailOrigin.getCid07() != null) {
				beneficiarioDetailDest.setCid07(beneficiarioDetailOrigin.getCid07());
			}

			if (beneficiarioDetailOrigin.getCid08() != null) {
				beneficiarioDetailDest.setCid08(beneficiarioDetailOrigin.getCid08());
			}

			if (beneficiarioDetailOrigin.getCid09() != null) {
				beneficiarioDetailDest.setCid09(beneficiarioDetailOrigin.getCid09());
			}

			if (beneficiarioDetailOrigin.getCid10() != null) {
				beneficiarioDetailDest.setCid10(beneficiarioDetailOrigin.getCid10());
			}

			if (beneficiarioDetailOrigin.getCns() != null) {
				beneficiarioDetailDest.setCns(beneficiarioDetailOrigin.getCns());
			}

			if (beneficiarioDetailOrigin.getDf() != null) {
				beneficiarioDetailDest.setDf(beneficiarioDetailOrigin.getDf());
			}

			if (beneficiarioDetailOrigin.getDifTransferencia() != null) {
				beneficiarioDetailDest.setDifTransferencia(beneficiarioDetailOrigin.getDifTransferencia());
			}

			if (beneficiarioDetailOrigin.getDtExclusao() != null) {
				beneficiarioDetailDest.setDtExclusao(beneficiarioDetailOrigin.getDtExclusao());
			}

			if (beneficiarioDetailOrigin.getDtInclusao() != null) {
				beneficiarioDetailDest.setDtInclusao(beneficiarioDetailOrigin.getDtInclusao());
			}

			if (beneficiarioDetailOrigin.getDtReferencia() != null) {
				beneficiarioDetailDest.setDtReferencia(beneficiarioDetailOrigin.getDtReferencia());
			}

			if (beneficiarioDetailOrigin.getEmail() != null) {
				beneficiarioDetailDest.setEmail(beneficiarioDetailOrigin.getEmail());
			}

			if (beneficiarioDetailOrigin.getEmailSeguradora() != null) {
				beneficiarioDetailDest.setEmailSeguradora(beneficiarioDetailOrigin.getEmailSeguradora());
			}

			if (beneficiarioDetailOrigin.getEs() != null) {
				beneficiarioDetailDest.setEs(beneficiarioDetailOrigin.getEs());
			}

			if (beneficiarioDetailOrigin.getFatorModerador() != null) {
				beneficiarioDetailDest.setFatorModerador(beneficiarioDetailOrigin.getFatorModerador());
			}

			if (beneficiarioDetailOrigin.getGrauEscolaridade() != null) {
				beneficiarioDetailDest.setGrauEscolaridade(beneficiarioDetailOrigin.getGrauEscolaridade());
			}

			if (beneficiarioDetailOrigin.getIbge() != null) {
				beneficiarioDetailDest.setIbge(beneficiarioDetailOrigin.getIbge());
			}

			if (beneficiarioDetailOrigin.getIndicadorCarencia() != null) {
				beneficiarioDetailDest.setIndicadorCarencia(beneficiarioDetailOrigin.getIndicadorCarencia());
			}

			if (beneficiarioDetailOrigin.getIndicadorExEmpregado() != null) {
				beneficiarioDetailDest.setIndicadorExEmpregado(beneficiarioDetailOrigin.getIndicadorExEmpregado());
			}

			if (beneficiarioDetailOrigin.getIndicadorPermanenciaPlano() != null) {
				beneficiarioDetailDest
						.setIndicadorPermanenciaPlano(beneficiarioDetailOrigin.getIndicadorPermanenciaPlano());
			}

			if (beneficiarioDetailOrigin.getIndicadorPoretabilidade1() != null) {
				beneficiarioDetailDest
						.setIndicadorPoretabilidade1(beneficiarioDetailOrigin.getIndicadorPoretabilidade1());
			}

			if (beneficiarioDetailOrigin.getIndicadorPoretabilidade2() != null) {
				beneficiarioDetailDest
						.setIndicadorPoretabilidade2(beneficiarioDetailOrigin.getIndicadorPoretabilidade2());
			}

			if (beneficiarioDetailOrigin.getIndicadorSeguradoContributario() != null) {
				beneficiarioDetailDest.setIndicadorSeguradoContributario(
						beneficiarioDetailOrigin.getIndicadorSeguradoContributario());
			}

			if (beneficiarioDetailOrigin.getIndicadorTitularRemido() != null) {
				beneficiarioDetailDest.setIndicadorTitularRemido(beneficiarioDetailOrigin.getIndicadorTitularRemido());
			}

			if (beneficiarioDetailOrigin.getLocal() != null) {
				beneficiarioDetailDest.setLocal(beneficiarioDetailOrigin.getLocal());
			}

			if (beneficiarioDetailOrigin.getMatriculaEspecial() != null) {
				beneficiarioDetailDest.setMatriculaEspecial(beneficiarioDetailOrigin.getMatriculaEspecial());
			}

			if (beneficiarioDetailOrigin.getNameCompletoBeneficiario() != null) {
				beneficiarioDetailDest
						.setNameCompletoBeneficiario(beneficiarioDetailOrigin.getNameCompletoBeneficiario());
			}

			if (beneficiarioDetailOrigin.getPermanencia() != null) {
				beneficiarioDetailDest.setPermanencia(beneficiarioDetailOrigin.getPermanencia());
			}

			if (beneficiarioDetailOrigin.getPis() != null) {
				beneficiarioDetailDest.setPis(beneficiarioDetailOrigin.getPis());
			}

			if (beneficiarioDetailOrigin.getPlano() != null) {
				beneficiarioDetailDest.setPlano(beneficiarioDetailOrigin.getPlano());
			}

			if (beneficiarioDetailOrigin.getProfissao() != null) {
				beneficiarioDetailDest.setProfissao(beneficiarioDetailOrigin.getProfissao());
			}

			if (beneficiarioDetailOrigin.getQtdeMesesContribuicao() != null) {
				beneficiarioDetailDest.setQtdeMesesContribuicao(beneficiarioDetailOrigin.getQtdeMesesContribuicao());
			}

			if (beneficiarioDetailOrigin.getRdp() != null) {
				beneficiarioDetailDest.setRdp(beneficiarioDetailOrigin.getRdp());
			}

			if (beneficiarioDetailOrigin.getRic() != null) {
				beneficiarioDetailDest.setRic(beneficiarioDetailOrigin.getRic());
			}

			if (beneficiarioDetailOrigin.getSetor() != null) {
				beneficiarioDetailDest.setSetor(beneficiarioDetailOrigin.getSetor());
			}

			if (beneficiarioDetailOrigin.getSexo() != null) {
				beneficiarioDetailDest.setSexo(beneficiarioDetailOrigin.getSexo());
			}

			if (beneficiarioDetailOrigin.getTituloEleitor() != null) {
				beneficiarioDetailDest.setTituloEleitor(beneficiarioDetailOrigin.getTituloEleitor());
			}

			copyDadosBancarios(
					beneficiarioDetailDest.getDadosBancarios(),
					beneficiarioDetailOrigin.getDadosBancarios());

			copyEndereco(beneficiarioDetailDest.getEndereco(), beneficiarioDetailOrigin.getEndereco());

			copyteTelefone(beneficiarioDetailDest.getTelefone(), beneficiarioDetailOrigin.getTelefone());

			copyTransferencia(beneficiarioDetailDest.getTransferencia(), beneficiarioDetailOrigin.getTransferencia());

			//////////////////////////////////////////////////////////////////////////////////////
			// Campos do Bradesco:
			if (beneficiarioDetailOrigin.getSubFatura() != null) {
				beneficiarioDetailDest.setSubFatura(beneficiarioDetailOrigin.getSubFatura());
			}

			if (beneficiarioDetailOrigin.getProfissao() != null) {
				beneficiarioDetailDest.setProfissao(beneficiarioDetailOrigin.getProfissao());
			}

			if (beneficiarioDetailOrigin.getMatriculaEspecial() != null) {
				beneficiarioDetailDest.setMatriculaEspecial(beneficiarioDetailOrigin.getMatriculaEspecial());
			}

			if (beneficiarioDetailOrigin.getFatorModerador() != null) {
				beneficiarioDetailDest.setFatorModerador(beneficiarioDetailOrigin.getFatorModerador());
			}

			if (beneficiarioDetailOrigin.getFatorModeradorInss() != null) {
				beneficiarioDetailDest.setFatorModeradorInss(beneficiarioDetailOrigin.getFatorModeradorInss());
			}

			if (beneficiarioDetailOrigin.getValorInss() != null) {
				beneficiarioDetailDest.setValorInss(beneficiarioDetailOrigin.getValorInss());
			}

			if (beneficiarioDetailOrigin.getValorAliquotaInss() != null) {
				beneficiarioDetailDest.setValorAliquotaInss(beneficiarioDetailOrigin.getValorAliquotaInss());
			}

			if (beneficiarioDetailOrigin.getValorLiquidoSinistro() != null) {
				beneficiarioDetailDest.setValorLiquidoSinistro(beneficiarioDetailOrigin.getValorLiquidoSinistro());
			}

			if (beneficiarioDetailOrigin.getIndicadorEvento() != null) {
				beneficiarioDetailDest.setIndicadorEvento(beneficiarioDetailOrigin.getIndicadorEvento());
			}

			if (beneficiarioDetailOrigin.getCdUsuario() != null) {
				beneficiarioDetailDest.setCdUsuario(beneficiarioDetailOrigin.getCdUsuario());
			}

			if (beneficiarioDetailOrigin.getCertificado() != null) {
				beneficiarioDetailDest.setCertificado(beneficiarioDetailOrigin.getCertificado());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static void copyTransferencia(Transferencia transferenciaDest, Transferencia transferenciaOrigin)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (transferenciaOrigin.getCategoriaTransferencia() != null) {
				transferenciaDest.setCategoriaTransferencia(transferenciaOrigin.getCategoriaTransferencia());
			}

			if (transferenciaOrigin.getCdDv() != null) {
				transferenciaDest.setCdDv(transferenciaOrigin.getCdDv());
			}

			if (transferenciaOrigin.getCdEmpresaTitular() != null) {
				transferenciaDest.setCdEmpresaTitular(transferenciaOrigin.getCdEmpresaTitular());
			}

			if (transferenciaOrigin.getCdEmpresaTransferencia() != null) {
				transferenciaDest.setCdEmpresaTransferencia(transferenciaOrigin.getCdEmpresaTransferencia());
			}

			if (transferenciaOrigin.getCdError() != null) {
				transferenciaDest.setCdError(transferenciaOrigin.getCdError());
			}

			if (transferenciaOrigin.getCdMatriculaTransferencia() != null) {
				transferenciaDest.setCdMatriculaTransferencia(transferenciaOrigin.getCdMatriculaTransferencia());
			}

			if (transferenciaOrigin.getCdStatus() != null) {
				transferenciaDest.setCdStatus(transferenciaOrigin.getCdStatus());
			}

			if (transferenciaOrigin.getCpfNovoTitular() != null) {
				transferenciaDest.setCpfNovoTitular(transferenciaOrigin.getCpfNovoTitular());
			}

			if (transferenciaOrigin.getCpt() != null) {
				transferenciaDest.setCpt(transferenciaOrigin.getCpt());
			}

			if (transferenciaOrigin.getDiferenciadorMatriculaTitular() != null) {
				transferenciaDest
						.setDiferenciadorMatriculaTitular(transferenciaOrigin.getDiferenciadorMatriculaTitular());
			}

			if (transferenciaOrigin.getDtInicioTransferencia() != null) {
				transferenciaDest.setDtInicioTransferencia(transferenciaOrigin.getDtInicioTransferencia());
			}

			if (transferenciaOrigin.getLocalTransferecia() != null) {
				transferenciaDest.setLocalTransferecia(transferenciaOrigin.getLocalTransferecia());
			}

			if (transferenciaOrigin.getMotivoRemissao() != null) {
				transferenciaDest.setMotivoRemissao(transferenciaOrigin.getMotivoRemissao());
			}

			if (transferenciaOrigin.getPlanoTransferecia() != null) {
				transferenciaDest.setPlanoTransferecia(transferenciaOrigin.getPlanoTransferecia());
			}

			if (transferenciaOrigin.getQtdePermanenciaMeses() != null) {
				transferenciaDest.setQtdePermanenciaMeses(transferenciaOrigin.getQtdePermanenciaMeses());
			}

			if (transferenciaOrigin.getRdpNovoTitular() != null) {
				transferenciaDest.setRdpNovoTitular(transferenciaOrigin.getRdpNovoTitular());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static void copyteTelefone(Telefone telefoneDest, Telefone telefoneOrigin) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (telefoneOrigin.getCelular() != null) {
				telefoneDest.setCelular(telefoneOrigin.getCelular());
			}

			if (telefoneOrigin.getComercial() != null) {
				telefoneDest.setComercial(telefoneOrigin.getComercial());
			}

			if (telefoneOrigin.getResidencial() != null) {
				telefoneDest.setResidencial(telefoneOrigin.getResidencial());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static void copyEndereco(Endereco enderecoDest, Endereco enderecoOrigin) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (enderecoOrigin.getBairro() != null) {
				enderecoDest.setBairro(enderecoOrigin.getBairro());
			}

			if (enderecoOrigin.getCep() != null) {
				enderecoDest.setCep(enderecoOrigin.getCep());
			}

			if (enderecoOrigin.getComplemento() != null) {
				enderecoDest.setComplemento(enderecoOrigin.getComplemento());
			}

			if (enderecoOrigin.getLogradouro() != null) {
				enderecoDest.setLogradouro(enderecoOrigin.getLogradouro());
			}

			if (enderecoOrigin.getMunicipio() != null) {
				enderecoDest.setMunicipio(enderecoOrigin.getMunicipio());
			}

			if (enderecoOrigin.getNumero() != null) {
				enderecoDest.setNumero(enderecoOrigin.getNumero());
			}

			if (enderecoOrigin.getType() != null) {
				enderecoDest.setType(enderecoOrigin.getType());
			}

			if (enderecoOrigin.getUf() != null) {
				enderecoDest.setUf(enderecoOrigin.getUf());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private static void copyDadosBancarios(DadosBancarios dadosBancariosDest, DadosBancarios dadosBancariosOrigin)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (dadosBancariosOrigin.getAgencia() != null) {
				dadosBancariosDest.setAgencia(dadosBancariosOrigin.getAgencia());
			}

			if (dadosBancariosOrigin.getAgenciaDg() != null) {
				dadosBancariosDest.setAgenciaDg(dadosBancariosOrigin.getAgenciaDg());
			}

			if (dadosBancariosOrigin.getBanco() != null) {
				dadosBancariosDest.setBanco(dadosBancariosOrigin.getBanco());
			}

			if (dadosBancariosOrigin.getContaCorrente() != null) {
				dadosBancariosDest.setContaCorrente(dadosBancariosOrigin.getContaCorrente());
			}

			if (dadosBancariosOrigin.getCpf() != null) {
				dadosBancariosDest.setCpf(dadosBancariosOrigin.getCpf());
			}

			if (dadosBancariosOrigin.getNameTitular() != null) {
				dadosBancariosDest.setNameTitular(dadosBancariosOrigin.getNameTitular());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
