/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE;
drop view if exists VW_DESCONHECIDO_SPREAD_SAUDE;
drop view if exists VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE;

drop view if exists VW_TITULAR_ISENTO_SPREAD_SAUDE;
drop view if exists VW_DEPENDENTE_ISENTO_SPREAD_SAUDE;

drop view if exists VW_COPARTICIPACAO_SPREAD_SAUDE;
drop view if exists VW_COPARTICIPACAO_RESUMIDA_SPREAD_SAUDE;
drop view if exists VW_RESUMO_SPREAD_SAUDE;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    contrato.CD_CONTRATO,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.CD_PLANO,
    desconhecido.NR_MATRICULA,
    desconhecido.NR_MATRICULA_ESPECIAL
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '073828'
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
	titular.NR_SUBFATURA,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.NR_CPF,
	titular.NM_TITULAR,
    titular.CD_PLANO,
    titular.NR_MATRICULA,
    titular.NR_MATRICULA_ESPECIAL
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = '073828'
and		lancamento.ID_DEPENDENTE is null
and		( titular.NR_MATRICULA_ESPECIAL is null or
		  titular.CD_PLANO is null )
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
	titular.NR_SUBFATURA,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NR_CPF,
	titular.NM_TITULAR,
    dependente.CD_PLANO,
    dependente.NR_MATRICULA,
    dependente.NR_MATRICULA_ESPECIAL    
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE
where	empresa.CD_EMPRESA = '073828'
and		lancamento.ID_DEPENDENTE is not null
and		( dependente.NR_MATRICULA_ESPECIAL is null or
		  dependente.CD_PLANO is null );
	
create view VW_DESCONHECIDO_SPREAD_SAUDE as
select distinct
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.CD_CONTRATO,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.CD_PLANO,
    desconhecido.NR_MATRICULA,
    desconhecido.NR_MATRICULA_ESPECIAL
from VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE desconhecido
order by 
	desconhecido.NM_TITULAR,
	desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/
create view VW_TITULAR_ISENTO_SPREAD_SAUDE as
select
	titular.ID ID_TITULAR,
    titular.NM_TITULAR,
	'Sim' TP_ISENTO
from TB_TITULAR_ISENTO isento
	join TB_TITULAR titular on
		titular.ID = isento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA		
where	empresa.CD_EMPRESA = '073828';
		
create view VW_DEPENDENTE_ISENTO_SPREAD_SAUDE as
select
	dependente.ID ID_DEPENDENTE,
    dependente.NM_DEPENDENTE,
	'Sim' TP_ISENTO
from TB_DEPENDENTE_ISENTO isento
	join TB_DEPENDENTE dependente on
		dependente.ID = isento.ID_DEPENDENTE
	join TB_TITULAR titular on
		titular.ID = dependente.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA		
where	empresa.CD_EMPRESA = '073828';
		
/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,	
	titular.NR_MATRICULA_ESPECIAL,
	lpad( subfatura.NR_SUBFATURA, 3, '0' ) NR_SUBFATURA,
    concat( lpad( subfatura.NR_SUBFATURA, 3, '0' ), ' - ', subfatura.NM_SUBFATURA ) NM_EMPRESA,
    titular.NM_TITULAR,
    titular.NM_TITULAR NM_BENEFICIARIO,
    'Títular' TP_UTILIZACAO,
    lancamento.DT_UTILIZACAO,
    titular.CD_PLANO,
    lancamento.DESCR_UTILIZACAO,    
    lancamento.VL_PRINCIPAL,
    ifnull( isento.TP_ISENTO, '' ) TP_ISENTO,
    case
		when isento.TP_ISENTO is not null then lancamento.VL_PRINCIPAL 
        else 0.0
    end VL_ISENTO,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED		    
from TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_SUBFATURA subfatura on
		subfatura.NR_SUBFATURA = titular.NR_SUBFATURA
	left outer join VW_TITULAR_ISENTO_SPREAD_SAUDE isento on
		isento.ID_TITULAR = titular.ID
where	empresa.CD_EMPRESA = '073828' 
and		lancamento.ID_DEPENDENTE is null
and titular.NR_MATRICULA not in (
	select
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_SPREAD_SAUDE desconhecido
	where desconhecido.NR_MATRICULA = titular.NR_MATRICULA )
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,	
	dependente.NR_MATRICULA_ESPECIAL,
	lpad( subfatura.NR_SUBFATURA, 3, '0' ) NR_SUBFATURA,
    concat( lpad( subfatura.NR_SUBFATURA, 3, '0' ), ' - ', subfatura.NM_SUBFATURA ) NM_EMPRESA,
    titular.NM_TITULAR,
    dependente.NM_DEPENDENTE NM_BENEFICIARIO,
    'Dependente' TP_UTILIZACAO,
    lancamento.DT_UTILIZACAO,
    titular.CD_PLANO,
    lancamento.DESCR_UTILIZACAO,    
    lancamento.VL_PRINCIPAL,
    ifnull( isento.TP_ISENTO, '' ) TP_ISENTO,
    case
		when isento.TP_ISENTO is not null then lancamento.VL_PRINCIPAL 
        else 0.0
    end VL_ISENTO,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED		    
from TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE
	join TB_SUBFATURA subfatura on
		subfatura.NR_SUBFATURA = dependente.NR_SUBFATURA        
	left outer join VW_DEPENDENTE_ISENTO_SPREAD_SAUDE isento on
		isento.ID_DEPENDENTE = dependente.ID
where	empresa.CD_EMPRESA = '073828' 
and		lancamento.ID_DEPENDENTE is not null
and dependente.NR_MATRICULA not in (
	select
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_SPREAD_SAUDE desconhecido
	where desconhecido.NR_MATRICULA = dependente.NR_MATRICULA );

create view VW_COPARTICIPACAO_SPREAD_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
	spread.NR_MATRICULA_ESPECIAL,
	spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
	spread.NM_TITULAR,
	spread.NM_BENEFICIARIO,
	spread.TP_UTILIZACAO,
	spread.DT_UTILIZACAO,
	spread.CD_PLANO,
	spread.DESCR_UTILIZACAO,
	sum( spread.VL_PRINCIPAL ) VL_PRINCIPAL,
	spread.TP_ISENTO,
	sum( spread.VL_ISENTO ) VL_ISENTO,	
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED			
from	VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE spread
group by
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
	spread.NR_MATRICULA_ESPECIAL,
	spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
	spread.NM_TITULAR,
	spread.NM_BENEFICIARIO,
	spread.TP_UTILIZACAO,
	spread.DT_UTILIZACAO,
	spread.CD_PLANO,
	spread.DESCR_UTILIZACAO,
	spread.TP_ISENTO,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED
order by
	spread.NR_SUBFATURA, 
	spread.NM_TITULAR,
	spread.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_RESUMIDA_SPREAD_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
	spread.NR_MATRICULA_ESPECIAL,
	spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
	spread.NM_TITULAR,
	spread.CD_PLANO,
	sum( spread.VL_PRINCIPAL ) VL_PRINCIPAL,
	sum( spread.VL_ISENTO ) VL_ISENTO,	
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED			
from	VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE spread
group by
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
	spread.NR_MATRICULA_ESPECIAL,
	spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
	spread.NM_TITULAR,
	spread.CD_PLANO,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED
order by
	spread.NR_SUBFATURA, 
	spread.NM_EMPRESA,
    spread.NM_TITULAR;

/****************************************************************************************************************************************************/
	
create view VW_RESUMO_SPREAD_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
    spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
    sum( spread.VL_PRINCIPAL ) VL_PRINCIPAL,
    sum( spread.VL_ISENTO ) VL_ISENTO,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED			
from	VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE spread
group by
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
	spread.NR_SUBFATURA,
	spread.NM_EMPRESA,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED;
	
/****************************************************************************************************************************************************/

