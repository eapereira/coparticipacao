/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_CELPE_SAUDE;
drop view if exists VW_DESCONHECIDO_CELPE_SAUDE;
drop view if exists VW_COPARTICIPACAO_LEVEL01_CELPE_SAUDE;
drop view if exists VW_COPARTICIPACAO_CELPE_SAUDE;
drop view if exists VW_RATEIO_CELPE_SAUDE;
drop view if exists VW_RESUMO_DETAIL_CELPE_SAUDE;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_CELPE_SAUDE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.VL_FATOR_MODERADOR,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '071421'
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	titular.NR_MATRICULA,
	titular.NR_SUBFATURA,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.NR_CPF,
	titular.NM_TITULAR,
	titular.VL_FATOR_MODERADOR,
    titular.DESCR_PROFISSAO,
    titular.NR_MATRICULA_ESPECIAL	
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = '071421'
and		( titular.VL_FATOR_MODERADOR is null or 
	(	titular.VL_FATOR_MODERADOR is not null and 
		titular.VL_FATOR_MODERADOR <= 0 ));
	
create view VW_DESCONHECIDO_CELPE_SAUDE as
select distinct
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.VL_FATOR_MODERADOR,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL    
from VW_DESCONHECIDO_LEVEL01_CELPE_SAUDE desconhecido
order by desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_CELPE_SAUDE as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    contrato.CD_CONTRATO,
	lpad( titular.NR_MATRICULA, 7, '0' ) NR_CERTIFICADO,
	lpad( titular.NR_MATRICULA_EMPRESA, 10, '0' ) NR_MATRICULA,	
	titular.NR_SUBFATURA,
	titular.NM_TITULAR,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.VL_FATOR_MODERADOR,
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	titular.CD_USUARIO,
	titular.NR_CARTEIRA_IDENT,
	titular.NR_CPF NR_CPF_BENEFICIARIO,
	titular.CD_PLANO,
	lancamento.VL_PRINCIPAL,
	empresa.CD_EMPRESA,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
where	empresa.CD_EMPRESA			= '071421'
and		titular.VL_FATOR_MODERADOR 	> 0
and		titular.NR_SUBFATURA not in ( 110, 220, 221, 320, 321, 420, 500, 510, 851, 852 )
and		lancamento.ID_DEPENDENTE is null
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    contrato.CD_CONTRATO,
	lpad( titular.NR_MATRICULA, 7, '0' ) NR_CERTIFICADO,
	lpad( titular.NR_MATRICULA_EMPRESA, 10, '0' ) NR_MATRICULA,	
	titular.NR_SUBFATURA,
	titular.NM_TITULAR,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	titular.VL_FATOR_MODERADOR,
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	dependente.CD_USUARIO,
	dependente.NR_CARTEIRA_IDENT,
	dependente.NR_CPF NR_CPF_BENEFICIARIO,
	dependente.CD_PLANO,
	lancamento.VL_PRINCIPAL,
	empresa.CD_EMPRESA,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		lancamento.ID_DEPENDENTE = dependente.ID
where	empresa.CD_EMPRESA			= '071421'
and		titular.VL_FATOR_MODERADOR 	> 0
and		titular.NR_SUBFATURA not in ( 110, 220, 221, 320, 321, 420, 500, 510, 851, 852 )
and		lancamento.ID_DEPENDENTE is not null;



create view VW_COPARTICIPACAO_CELPE_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.ID_CONTRATO,
    celpe.CD_CONTRATO,
    celpe.NR_CERTIFICADO,
	celpe.NR_MATRICULA,
	celpe.NM_TITULAR,
	celpe.NR_SUBFATURA,
	celpe.VL_FATOR_MODERADOR,
	celpe.NR_MATRICULA_ESPECIAL,
	celpe.DESCR_PROFISSAO,
	celpe.CD_USUARIO,
	celpe.NR_CARTEIRA_IDENT,
	celpe.CD_PLANO,	
	sum( celpe.VL_PRINCIPAL ) VL_PRINCIPAL,
	celpe.CD_EMPRESA,
	celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED
from	VW_COPARTICIPACAO_LEVEL01_CELPE_SAUDE celpe
group by
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.ID_CONTRATO,
    celpe.CD_CONTRATO,
    celpe.NR_CERTIFICADO,
	celpe.NR_MATRICULA,
	celpe.NM_TITULAR,
	celpe.NR_SUBFATURA,
	celpe.VL_FATOR_MODERADOR,
	celpe.NR_MATRICULA_ESPECIAL,
	celpe.DESCR_PROFISSAO,
	celpe.CD_USUARIO,
	celpe.NR_CARTEIRA_IDENT,
	celpe.CD_PLANO,		
	celpe.CD_EMPRESA,
	celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED	
order by
	celpe.NM_TITULAR;
	
/****************************************************************************************************************************************************/
	
create view VW_RESUMO_DETAIL_CELPE_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.ID_CONTRATO,
    celpe.CD_CONTRATO,
    celpe.CD_EMPRESA,
    celpe.NR_SUBFATURA,
	count(1) QTDE_SEGURADOS,
	sum( celpe.VL_FATOR_MODERADOR ) VL_ALOCACAO,
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_CELPE_SAUDE ) VL_PROPORCAO,
	celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED	
from	VW_COPARTICIPACAO_CELPE_SAUDE celpe
group by
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.ID_CONTRATO,
    celpe.CD_CONTRATO,
    celpe.CD_EMPRESA,
    celpe.NR_SUBFATURA,
    celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED;
	
/****************************************************************************************************************************************************/
	
create view VW_RATEIO_CELPE_SAUDE as
select
	FUNC_GET_ROWNUM() ID,     
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.NM_TITULAR,
    sum( celpe.VL_PRINCIPAL ) VL_PRINCIPAL,
	celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED	    
from	VW_COPARTICIPACAO_CELPE_SAUDE celpe
group by
	celpe.CD_MES,
    celpe.CD_ANO,
    celpe.NM_TITULAR,
	celpe.VERSION,
	celpe.USER_CREATED,
	celpe.USER_ALTERED,
	celpe.DT_CREATED,
	celpe.DT_ALTERED	    
order by
	celpe.NM_TITULAR;
