/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_COELBA_SAUDE;
drop view if exists VW_DESCONHECIDO_COELBA_SAUDE;
drop view if exists VW_COPARTICIPACAO_LEVEL01_COELBA_SAUDE;
drop view if exists VW_COPARTICIPACAO_COELBA_SAUDE;
drop view if exists VW_RATEIO_COELBA_SAUDE;
drop view if exists VW_RESUMO_LEVEL01_COELBA_SAUDE;
drop view if exists VW_RESUMO_COELBA_SAUDE;
drop view if exists VW_RESUMO_DETAIL_COELBA_SAUDE;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_COELBA_SAUDE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    contrato.CD_CONTRATO,
	desconhecido.NR_MATRICULA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
	desconhecido.NR_SUBFATURA,
    desconhecido.CD_PLANO,
    desconhecido.NR_CARTEIRA_IDENT,
    desconhecido.CD_USUARIO,
    desconhecido.NR_MATRICULA_ESPECIAL
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '071769'
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
	titular.NR_MATRICULA,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.NR_CPF,
	titular.NM_TITULAR,
	titular.NR_SUBFATURA,
    titular.CD_PLANO,
    titular.NR_CARTEIRA_IDENT,
    titular.CD_USUARIO,
    titular.NR_MATRICULA_ESPECIAL	
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = '071769'
and		lancamento.ID_DEPENDENTE is null
and		( 
	titular.NR_SUBFATURA is null or
	titular.CD_PLANO is null or
	titular.CD_USUARIO is null or
	titular.NR_MATRICULA_ESPECIAL is null )
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
	dependente.NR_MATRICULA,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NR_CPF,
	titular.NM_TITULAR,
	dependente.NR_SUBFATURA,
    dependente.CD_PLANO,
    dependente.NR_CARTEIRA_IDENT,
    dependente.CD_USUARIO,
    dependente.NR_MATRICULA_ESPECIAL	
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE		
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = '071769'
and		lancamento.ID_DEPENDENTE is not null
and		( 
	dependente.NR_SUBFATURA is null or
	dependente.NR_CARTEIRA_IDENT is null or
	dependente.CD_USUARIO is null or
	dependente.NR_MATRICULA_ESPECIAL is null );

	
create view VW_DESCONHECIDO_COELBA_SAUDE as
select distinct
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.CD_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.CD_PLANO,
    desconhecido.NR_CARTEIRA_IDENT,
    desconhecido.CD_USUARIO,
    desconhecido.NR_MATRICULA_ESPECIAL    
from VW_DESCONHECIDO_LEVEL01_COELBA_SAUDE desconhecido
order by 
	desconhecido.NM_TITULAR, 
	desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_COELBA_SAUDE as
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
	FUNC_GET_CARTAO_IDENTIFICACAO_CELPE( contrato.CD_CONTRATO, titular.NR_MATRICULA, titular.CD_USUARIO) NR_CARTEIRA_IDENT,
	lpad( titular.NR_CPF, 11, '0' ) NR_CPF_BENEFICIARIO,
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
where	empresa.CD_EMPRESA			= '071769'
and		titular.NR_SUBFATURA not in ( 110, 220, 221, 320, 321, 420, 500, 510, 851, 852 )
and		lancamento.ID_DEPENDENTE is null
and		titular.NR_MATRICULA not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_COELBA_SAUDE desconhecido
	where desconhecido.NR_MATRICULA = titular.NR_MATRICULA )
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
	lpad( dependente.NR_CPF, 11, '0' ) NR_CPF_BENEFICIARIO,
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
	join TB_DEPENDENTE dependente on
		lancamento.ID_DEPENDENTE = dependente.ID
where	empresa.CD_EMPRESA			= '071769'
and		titular.NR_SUBFATURA not in ( 110, 220, 221, 320, 321, 420, 500, 510, 851, 852 )
and		lancamento.ID_DEPENDENTE is not null
and		dependente.NR_MATRICULA not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_COELBA_SAUDE desconhecido
	where desconhecido.NR_MATRICULA = dependente.NR_MATRICULA );

create view VW_COPARTICIPACAO_COELBA_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.ID_CONTRATO,
    coelba.CD_CONTRATO,
    coelba.NR_CERTIFICADO,
	coelba.NR_MATRICULA,
	coelba.NM_TITULAR,
	coelba.NM_BENEFICIARIO,
	coelba.NR_SUBFATURA,
	coelba.VL_FATOR_MODERADOR,
	coelba.NR_MATRICULA_ESPECIAL,
	coelba.DESCR_PROFISSAO,
	coelba.CD_USUARIO,
	coelba.NR_CARTEIRA_IDENT,
	coelba.NR_CPF_BENEFICIARIO,
	coelba.CD_PLANO,	
	sum( coelba.VL_PRINCIPAL ) VL_PRINCIPAL,
	coelba.CD_EMPRESA,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED
from	VW_COPARTICIPACAO_LEVEL01_COELBA_SAUDE coelba
group by
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.ID_CONTRATO,
    coelba.CD_CONTRATO,
    coelba.NR_CERTIFICADO,
	coelba.NR_MATRICULA,
	coelba.NM_TITULAR,
	coelba.NM_BENEFICIARIO,
	coelba.NR_SUBFATURA,
	coelba.VL_FATOR_MODERADOR,
	coelba.NR_MATRICULA_ESPECIAL,
	coelba.DESCR_PROFISSAO,
	coelba.CD_USUARIO,
	coelba.NR_CARTEIRA_IDENT,
    coelba.NR_CPF_BENEFICIARIO,
	coelba.CD_PLANO,		
	coelba.CD_EMPRESA,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	
order by
	coelba.NM_TITULAR;
	
/****************************************************************************************************************************************************/
	
create view VW_RESUMO_DETAIL_COELBA_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.ID_CONTRATO,
    coelba.CD_CONTRATO,
    coelba.CD_EMPRESA,
    'Celpe' NM_SUBESTIPULANTE,
	sum( coelba.VL_PRINCIPAL ) VL_PRINCIPAL,
	( sum( coelba.VL_PRINCIPAL ) / ( select sum( total.VL_PRINCIPAL ) from VW_COPARTICIPACAO_COELBA_SAUDE total where total.CD_MES = coelba.CD_MES and total.CD_ANO = coelba.CD_ANO )) * 100 PERC_VL_PRINCIPAL,
	count( 1 ) QTDE_VIDAS,	
	( count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_COELBA_SAUDE total where total.CD_MES = coelba.CD_MES and total.CD_ANO = coelba.CD_ANO )) * 100 PERC_VIDAS,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	
from	VW_COPARTICIPACAO_COELBA_SAUDE coelba
group by
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.ID_CONTRATO,
    coelba.CD_CONTRATO,
    coelba.CD_EMPRESA,
    coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED;
	
/****************************************************************************************************************************************************/

create view VW_RESUMO_LEVEL01_COELBA_SAUDE as
select     
	'Saúde' NM_RAMO,
	'Bradesco' NM_OPERADORA,
	coelba.CD_MES,
	coelba.CD_ANO,
	FUNC_CREATE_DATA_COMPETENCIA( coelba.CD_MES, coelba.CD_ANO ) DESCR_COMPETENCIA,
	sum( ifnull( coelba.VL_PRINCIPAL, 0 ))  VL_PRINCIPAL,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	        
from VW_COPARTICIPACAO_COELBA_SAUDE coelba
group by
	coelba.CD_MES,
	coelba.CD_ANO,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED;    
		
create view VW_RESUMO_COELBA_SAUDE as
select	
	FUNC_GET_ROWNUM() ID,     
	coelba.NM_RAMO,
	coelba.NM_OPERADORA,
	coelba.CD_MES,
	coelba.CD_ANO,
	coelba.DESCR_COMPETENCIA,
	coelba.VL_PRINCIPAL,
	( select 
		coelba_anterior.DESCR_COMPETENCIA
	from VW_RESUMO_LEVEL01_COELBA_SAUDE coelba_anterior
	where	coelba_anterior.CD_MES	= coelba.CD_MES - 1
	and		coelba_anterior.CD_ANO	= coelba.CD_ANO ) DESCR_COMPETENCIA_ANTERIOR,
	ifnull(( select 
		coelba_anterior.VL_PRINCIPAL
	from VW_RESUMO_LEVEL01_COELBA_SAUDE coelba_anterior
	where	coelba_anterior.CD_MES	= coelba.CD_MES - 1
	and		coelba_anterior.CD_ANO	= coelba.CD_ANO ), 0 ) VL_PRINCIPAL_ANTERIOR,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	        
from VW_RESUMO_LEVEL01_COELBA_SAUDE coelba;
		
/****************************************************************************************************************************************************/
	
create view VW_RATEIO_COELBA_SAUDE as
select
	FUNC_GET_ROWNUM() ID,     
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.NM_TITULAR,
    sum( coelba.VL_PRINCIPAL ) VL_PRINCIPAL,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	    
from	VW_COPARTICIPACAO_COELBA_SAUDE coelba
group by
	coelba.CD_MES,
    coelba.CD_ANO,
    coelba.NM_TITULAR,
	coelba.VERSION,
	coelba.USER_CREATED,
	coelba.USER_ALTERED,
	coelba.DT_CREATED,
	coelba.DT_ALTERED	    
order by
	coelba.NM_TITULAR;


