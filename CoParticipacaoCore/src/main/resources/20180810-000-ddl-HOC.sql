/**
 * Edson - 18/07/2018
 * Script para criar as tabelas usadas peloprocesso OSWALDO CRUZ:
 */

drop view if exists VW_LANCAMENTO_LEVEL01_HOC;
drop view if exists VW_LANCAMENTO_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_TITULAR_HOC;
drop view if exists VW_LANCAMENTO_ORIGINAL_HOC;

drop view if exists VW_ISENCAO_VALOR_LEVEL01_HOC;
drop view if exists VW_ISENCAO_VALOR_HOC;

drop view if exists VW_ISENCAO_GESTANTES_LEVEL01_HOC;
drop view if exists VW_ISENCAO_GESTANTES_HOC;

drop view if exists VW_ISENCAO_CONSELHEIROS_HOC;
drop view if exists VW_DEPENDENTE_LOCAL_HOC;
drop view if exists VW_AFASTADOS_HOC;
drop view if exists VW_AGREGADOS_HOC;
drop view if exists VW_PLANO_EXTENSAO_HOC;
drop view if exists VW_PRN_HOC;
drop view if exists VW_DESCONHECIDO_HOC;

drop view if exists VW_DESLIGADOS_LEVEL01_HOC;
drop view if exists VW_DESLIGADOS_HOC;

drop view if exists VW_MESES_ANO;
drop view if exists VW_RESUMO_EMPTY_HOC;
drop view if exists VW_RESUMO_LEVEL01_HOC;
drop view if exists VW_RESUMO_HOC;

drop view if exists VW_DESCONHECIDO_LEVEL01_HOC ;
drop view if exists VW_DESCONHECIDO_HOC ;

drop view if exists VW_DEPENDENTE_RDP_HOC;
drop view if exists VW_TITULAR_RDP_HOC;

drop view if exists VW_TOTAL_LEVEL02_HOC;
drop view if exists VW_TOTAL_LEVEL01_HOC;
drop view if exists VW_TOTAL_DIFF_HOC;

/**********************************************************************************************************************/
create view VW_DESCONHECIDO_LEVEL01_HOC as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	empresa.ID ID_EMPRESA,
	titular.NR_MATRICULA,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
	titular.NR_MATRICULA_EMPRESA COD_DEPENDENTE,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.NM_ALIAS_01,
	titular.NM_ALIAS_02,
	FUNC_GET_CPF( titular.NR_CPF ) NR_CPF,
	titular.NR_LOCAL,
	titular.NR_RDP,
	titular.DT_NASCIMENTO,
	titular.DT_ADMISSAO,
	titular.DT_DEMISSAO,
	lancamento.VL_PRINCIPAL
from	TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where 	lancamento.ID_DEPENDENTE is null
and 	empresa.CD_EMPRESA = '0444'
and (	titular.NR_MATRICULA is null or
		titular.NR_MATRICULA_EMPRESA is null or
		titular.NR_CPF is null or
		titular.NR_CPF = 0 or 
		titular.NR_LOCAL is null or
		titular.NR_RDP is null )
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	empresa.ID ID_EMPRESA,
	dependente.NR_MATRICULA,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
	dependente.NR_MATRICULA_EMPRESA COD_DEPENDENTE,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NM_ALIAS_01,
	dependente.NM_ALIAS_02,
	FUNC_GET_CPF( dependente.NR_CPF ) NR_CPF,
	dependente.NR_LOCAL,
	dependente.NR_RDP,
	dependente.DT_NASCIMENTO,
	titular.DT_ADMISSAO,
	titular.DT_DEMISSAO,
	lancamento.VL_PRINCIPAL
from	TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID 			= lancamento.ID_DEPENDENTE and
        dependente.ID_TITULAR 	= titular.ID
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where 	lancamento.ID_DEPENDENTE is not null
and 	empresa.CD_EMPRESA = '0444'
and (	dependente.NR_MATRICULA is null or
		dependente.NR_MATRICULA_EMPRESA is null or
		dependente.NR_CPF is null or
		dependente.NR_CPF = 0 or 
		dependente.NR_LOCAL is null or
		dependente.NR_RDP is null );
		
create view VW_DESCONHECIDO_HOC as
select distinct
	desconhecido.CD_MES,
	desconhecido.CD_ANO,
	desconhecido.ID_CONTRATO,
	desconhecido.ID_EMPRESA,
	desconhecido.NR_MATRICULA,
	desconhecido.COD_TITULAR,
	desconhecido.COD_DEPENDENTE,
	desconhecido.NM_BENEFICIARIO,
	desconhecido.NM_ALIAS_01,
	desconhecido.NM_ALIAS_02,
	desconhecido.NR_CPF,
	desconhecido.NR_LOCAL,
	desconhecido.NR_RDP,
	desconhecido.DT_NASCIMENTO,
	desconhecido.DT_ADMISSAO,
	desconhecido.DT_DEMISSAO,
	desconhecido.VL_PRINCIPAL
from	VW_DESCONHECIDO_LEVEL01_HOC desconhecido
order by desconhecido.NM_BENEFICIARIO;

/**********************************************************************************************************************/
/**********************************************************************************************************************/

create view VW_LANCAMENTO_LEVEL01_HOC as
select 
	titular.ID ID_TITULAR,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
	empresa.ID ID_EMPRESA,
    lancamento.ID ID_LANCAMENTO,
    dependente.ID ID_DEPENDENTE,
    titular.NM_TITULAR,
    dependente.NM_DEPENDENTE NM_USUARIO,
    lpad( dependente.NR_MATRICULA_EMPRESA, 15, '0' ) COD_DEPENDENTE,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    lancamento.VL_PRINCIPAL,
	dependente.DT_NASCIMENTO,	
	lpad( dependente.NR_CPF, 11, '0' ) CPF_DEPENDENTE,
    dependente.NR_MATRICULA,
	dependente.NR_MATRICULA_EMPRESA NR_MATRICULA_DEPENDENTE,
	dependente.NR_LOCAL
from TB_TITULAR titular
join TB_LANCAMENTO lancamento on 
	lancamento.ID_TITULAR = titular.id
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
join TB_DEPENDENTE dependente on
	dependente.ID = lancamento.ID_DEPENDENTE
where	lancamento.ID_DEPENDENTE is not null
and		empresa.CD_EMPRESA = '0444'
union all
select 
	titular.ID ID_TITULAR,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
	empresa.ID ID_EMPRESA,
    lancamento.ID ID_LANCAMENTO,
    null ID_DEPENDENTE,
    titular.NM_TITULAR,
    titular.NM_TITULAR NM_USUARIO,
    lpad( titular.NR_MATRICULA_EMPRESA, 15, '0' ) COD_DEPENDENTE,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    lancamento.VL_PRINCIPAL,
	titular.DT_NASCIMENTO,	
	lpad( titular.NR_CPF, 11, '0' ) CPF_DEPENDENTE,
    titular.NR_MATRICULA,
	titular.NR_MATRICULA_EMPRESA NR_MATRICULA_DEPENDENTE,
	titular.NR_LOCAL
from TB_TITULAR titular
join TB_LANCAMENTO lancamento on 
	lancamento.ID_TITULAR = titular.id
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
where 	lancamento.ID_DEPENDENTE is null
and		empresa.CD_EMPRESA = '0444';

create view VW_LANCAMENTO_HOC as
select 
	lancamento.ID_TITULAR,
	lancamento.COD_TITULAR,
	lancamento.ID_EMPRESA,
    lancamento.ID_LANCAMENTO,
    lancamento.ID_DEPENDENTE,
    lancamento.NM_TITULAR,
    lancamento.NM_USUARIO,
    lancamento.COD_DEPENDENTE,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    lancamento.VL_PRINCIPAL,
	lancamento.DT_NASCIMENTO,	
	lancamento.CPF_DEPENDENTE,
    lancamento.NR_MATRICULA,
	lancamento.NR_MATRICULA_DEPENDENTE,
	lancamento.NR_LOCAL
from VW_LANCAMENTO_LEVEL01_HOC lancamento
where lancamento.VL_PRINCIPAL > 0;

create view VW_ISENCAO_GESTANTES_LEVEL01_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA,
	lanc_hoc.NR_MATRICULA_DEPENDENTE,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NM_TITULAR,
	lanc_hoc.VL_PRINCIPAL
from VW_LANCAMENTO_HOC lanc_hoc
where lanc_hoc.ID_TITULAR in (
	select
		titular_isento.ID_TITULAR
	from TB_TITULAR_ISENTO titular_isento
	where titular_isento.ID_TITULAR = lanc_hoc.ID_TITULAR 
    and titular_isento.TP_ISENTO = 1 )
and lanc_hoc.ID_DEPENDENTE is null
union all
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA,
	lanc_hoc.NR_MATRICULA_DEPENDENTE,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NM_TITULAR,
	lanc_hoc.VL_PRINCIPAL
from VW_LANCAMENTO_HOC lanc_hoc
where lanc_hoc.ID_DEPENDENTE in (
	select
		dependente_isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO dependente_isento
	where dependente_isento.ID_DEPENDENTE = lanc_hoc.ID_DEPENDENTE
    and dependente_isento.TP_ISENTO = 1 )
and lanc_hoc.ID_DEPENDENTE is not null;

create view VW_ISENCAO_VALOR_LEVEL01_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA,
	lanc_hoc.NR_LOCAL,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,		
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,		
	isento.VL_ISENCAO VL_PRINCIPAL
from VW_LANCAMENTO_HOC lanc_hoc
	join TB_TITULAR_ISENTO isento on
	isento.ID_TITULAR = lanc_hoc.ID_TITULAR and
	isento.TP_ISENTO in ( 7, 8 )
where lanc_hoc.ID_DEPENDENTE is null
union all
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA,
	lanc_hoc.NR_LOCAL,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,	
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,		
	isento.VL_ISENCAO VL_PRINCIPAL
from VW_LANCAMENTO_HOC lanc_hoc
	join TB_DEPENDENTE_ISENTO isento on
	isento.ID_DEPENDENTE = lanc_hoc.ID_DEPENDENTE and
	isento.TP_ISENTO in ( 7, 8 )
where lanc_hoc.ID_DEPENDENTE is not null;

create view VW_DESLIGADOS_LEVEL01_HOC as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	empresa.ID ID_EMPRESA,
	lancamento.ID_CONTRATO,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
    FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, titular.NR_RDP ) COD_DEPENDENTE,
	titular.NM_TITULAR NM_USUARIO,
	lancamento.VL_PRINCIPAL TOTAL_COPART,
	titular.NR_LOCAL,
	titular.DT_NASCIMENTO,
	titular.NR_CPF CPF_DEPENDENTE,
	titular.NR_MATRICULA NR_MATRICULA
from TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
where contrato.CD_CONTRATO = '0444'
and	 lancamento.ID_DEPENDENTE is null
and titular.DT_DEMISSAO is not null
and titular.NR_LOCAL <> 100
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	empresa.ID ID_EMPRESA,
	lancamento.ID_CONTRATO,
	FUNC_GET_MATRICULA_HOC( titular.NR_MATRICULA, 1 ) COD_TITULAR,
    FUNC_GET_MATRICULA_HOC( dependente.NR_MATRICULA, dependente.NR_RDP ) COD_DEPENDENTE,
	dependente.NM_DEPENDENTE NM_USUARIO,
	lancamento.VL_PRINCIPAL TOTAL_COPART,
	dependente.NR_LOCAL,
	dependente.DT_NASCIMENTO,
	dependente.NR_CPF CPF_DEPENDENTE,
	dependente.NR_MATRICULA NR_MATRICULA
from TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE
where contrato.CD_CONTRATO = '0444'
and	 lancamento.ID_DEPENDENTE is not null
and titular.NR_LOCAL <> 100
and titular.DT_DEMISSAO is not null;

/**********************************************************************************************************************/
/**********************************************************************************************************************/
	
create view VW_ISENCAO_GESTANTES_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NM_TITULAR,
	sum( lanc_hoc.VL_PRINCIPAL ) TOTAL_COPART
from VW_ISENCAO_GESTANTES_LEVEL01_HOC lanc_hoc
group by 	lanc_hoc.CD_MES,
			lanc_hoc.CD_ANO,
			lanc_hoc.ID_EMPRESA,
			lanc_hoc.ID_CONTRATO,
			lanc_hoc.NR_MATRICULA,
			lanc_hoc.NM_TITULAR,
			lanc_hoc.NM_USUARIO
order by lanc_hoc.NM_USUARIO;

create view VW_ISENCAO_VALOR_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.NR_MATRICULA,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NR_LOCAL,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,	
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,	
	sum( lanc_hoc.VL_PRINCIPAL ) TOTAL_COPART
from VW_ISENCAO_VALOR_LEVEL01_HOC lanc_hoc
group by 	lanc_hoc.CD_MES,
			lanc_hoc.CD_ANO,
			lanc_hoc.ID_EMPRESA,
			lanc_hoc.ID_CONTRATO,
			lanc_hoc.NR_MATRICULA,
			lanc_hoc.NR_LOCAL,
			lanc_hoc.DT_NASCIMENTO,
			lanc_hoc.CPF_DEPENDENTE,
			lanc_hoc.COD_TITULAR,
			lanc_hoc.COD_DEPENDENTE,				
			lanc_hoc.NM_USUARIO
order by lanc_hoc.NM_USUARIO;

create view VW_ISENCAO_CONSELHEIROS_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NR_LOCAL,
	sum( lanc_hoc.VL_PRINCIPAL ) TOTAL_COPART,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lanc_hoc
where lanc_hoc.NR_MATRICULA_DEPENDENTE < 1000
group by
	lanc_hoc.CD_MES,			
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.ID_CONTRATO,
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_USUARIO,
	lanc_hoc.NR_LOCAL,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE
order by lanc_hoc.NM_USUARIO;

create view VW_AFASTADOS_HOC as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	sum(lancamento.VL_PRINCIPAL) TOTAL_COPART,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lancamento
where lancamento.NR_LOCAL = 100
group by
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE
order by lancamento.NM_USUARIO;

create view VW_AGREGADOS_HOC as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	sum(lancamento.VL_PRINCIPAL) TOTAL_COPART,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lancamento
where lancamento.NR_LOCAL = 101
group by
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE
order by lancamento.NM_USUARIO;

create view VW_PLANO_EXTENSAO_HOC as
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	sum(lancamento.VL_PRINCIPAL) TOTAL_COPART,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lancamento
where lancamento.NR_LOCAL = 102
group by
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.COD_TITULAR,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE
order by lancamento.NM_USUARIO;

create view VW_DESLIGADOS_HOC as
select
	desligados.CD_MES,
	desligados.CD_ANO,
	desligados.ID_EMPRESA,
	desligados.ID_CONTRATO,
	desligados.COD_TITULAR,
	desligados.COD_DEPENDENTE,
	desligados.NM_USUARIO,
	sum( desligados.TOTAL_COPART ) TOTAL_COPART,
	desligados.NR_LOCAL,
	desligados.DT_NASCIMENTO,
	desligados.CPF_DEPENDENTE,
	desligados.NR_MATRICULA
from VW_DESLIGADOS_LEVEL01_HOC desligados
group by
	desligados.CD_MES,
	desligados.CD_ANO,
	desligados.ID_EMPRESA,
	desligados.ID_CONTRATO,
	desligados.COD_TITULAR,
	desligados.COD_DEPENDENTE,
	desligados.NM_USUARIO,
	desligados.NR_LOCAL,
	desligados.DT_NASCIMENTO,
	desligados.CPF_DEPENDENTE,
	desligados.NR_MATRICULA
order by desligados.NM_USUARIO;

create view VW_LANCAMENTO_ORIGINAL_HOC as
select
	lancamento.COD_TITULAR,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_EMPRESA,
    lancamento.ID_CONTRATO,
	lancamento.COD_DEPENDENTE,
	lancamento.NM_USUARIO,
	sum( lancamento.VL_PRINCIPAL ) TOTAL_COPART,
	lancamento.NR_LOCAL,
	lancamento.DT_NASCIMENTO,
	lancamento.CPF_DEPENDENTE,
	lancamento.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lancamento
group by	lancamento.COD_TITULAR,
		    lancamento.CD_MES,
		    lancamento.CD_ANO,
		    lancamento.ID_EMPRESA,
		    lancamento.ID_CONTRATO,
			lancamento.COD_DEPENDENTE,
			lancamento.NM_USUARIO,
			lancamento.NR_LOCAL,
			lancamento.DT_NASCIMENTO,
			lancamento.CPF_DEPENDENTE,
			lancamento.NR_MATRICULA_DEPENDENTE
order by lancamento.NM_USUARIO;

create view VW_PRN_HOC as
select
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.ID_CONTRATO,
	lpad( original_hoc.NR_MATRICULA, 16, '0' ) NR_MATRICULA,
	lpad( FUNC_DOUBLE_TO_LONG( sum( original_hoc.TOTAL_COPART )), 14, '0' ) TOTAL_COPART
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc
group by
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.ID_CONTRATO,
	original_hoc.NR_MATRICULA;

/**********************************************************************************************************************/

create view VW_MESES_ANO as
select
	1 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	2 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	3 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	4 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	5 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	6 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	7 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	8 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	9 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	10 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	11 CD_MES,
    date_format( current_date(), '%Y') CD_ANO
union all    
select
	12 CD_MES,
    date_format( current_date(), '%Y') CD_ANO;
    
create view VW_RESUMO_EMPTY_HOC as
select
	resumo_empty.NM_LABEL,
    resumo_empty.ID_RESUMO,
    meses_ano.CD_MES,
    resumo_empty.CD_ANO,
    resumo_empty.ID_EMPRESA,
    resumo_empty.ID_CONTRATO,
    resumo_empty.TOTAL_COPART 
from (
select
	'Total Coparticipação' NM_LABEL,
	1 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Isenção de Gestantes' NM_LABEL,
	2 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Isenção HOC' NM_LABEL,
	2 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Isenção Conselheiro' NM_LABEL,
	2 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Afastados' NM_LABEL,
	3 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Agregados' NM_LABEL,
	4 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Plano de Extensão' NM_LABEL,
	5 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato
union all
select
	'Desligados' NM_LABEL,
	6 ID_RESUMO,
	date_format( current_date(), '%m') CD_MES,
	date_format( current_date(), '%Y') CD_ANO,
    contrato.ID_EMPRESA,
    contrato.ID ID_CONTRATO,
    0.0 TOTAL_COPART
from TB_CONTRATO contrato ) resumo_empty
	join VW_MESES_ANO meses_ano on
		meses_ano.CD_ANO = resumo_empty.CD_ANO;



create view VW_TOTAL_DIFF_HOC as
select
	'Total' NM_LABEL,
	10 ID_RESUMO,
	isencao_gestantes.CD_MES,
	isencao_gestantes.CD_ANO,
	isencao_gestantes.ID_EMPRESA,
	isencao_gestantes.ID_CONTRATO,
	isencao_gestantes.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_GESTANTES_HOC isencao_gestantes
union all
select
	'Total' NM_LABEL,
	10 ID_RESUMO,
	isencao.CD_MES,
	isencao.CD_ANO,
	isencao.ID_EMPRESA,
	isencao.ID_CONTRATO,
	isencao.TOTAL_COPART
from VW_ISENCAO_VALOR_HOC isencao
union all
select
	'Total' NM_LABEL,
	10 ID_RESUMO,
	isencao_conselheiros.CD_MES,
	isencao_conselheiros.CD_ANO,
	isencao_conselheiros.ID_EMPRESA,
	isencao_conselheiros.ID_CONTRATO,
	isencao_conselheiros.TOTAL_COPART
from VW_ISENCAO_CONSELHEIROS_HOC isencao_conselheiros
union all
select
	'Total' NM_LABEL,
    10 ID_RESUMO,
	afastados.CD_MES,
	afastados.CD_ANO,
	afastados.ID_EMPRESA,
	afastados.ID_CONTRATO,
	afastados.TOTAL_COPART
from VW_AFASTADOS_HOC afastados
union all
select
	'Total' NM_LABEL,
    10 ID_RESUMO,
	agregados.CD_MES,
	agregados.CD_ANO,
	agregados.ID_EMPRESA,
	agregados.ID_CONTRATO,
	agregados.TOTAL_COPART
from VW_AGREGADOS_HOC agregados
union all
select
	'Total' NM_LABEL,
    10 ID_RESUMO,
	plano_extensao.CD_MES,
	plano_extensao.CD_ANO,
	plano_extensao.ID_EMPRESA,
	plano_extensao.ID_CONTRATO,
	plano_extensao.TOTAL_COPART
from VW_PLANO_EXTENSAO_HOC plano_extensao
union all
select
	'Total' NM_LABEL,
    10 ID_RESUMO,
	desligados.CD_MES,
	desligados.CD_ANO,
	desligados.ID_EMPRESA,
	desligados.ID_CONTRATO,
	desligados.TOTAL_COPART
from VW_DESLIGADOS_HOC desligados;

create view VW_TOTAL_LEVEL01_HOC as
select 
	'Total' NM_LABEL,
	10 ID_RESUMO,
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.ID_CONTRATO,
	original_hoc.TOTAL_COPART TOTAL_COPART,
	0.0 TOTAL_DIFF
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc
union all
select 
	'Total' NM_LABEL,
	10 ID_RESUMO,
	total_diff.CD_MES,
	total_diff.CD_ANO,
	total_diff.ID_EMPRESA,
	total_diff.ID_CONTRATO,
	0.0 TOTAL_COPART,
	total_diff.TOTAL_COPART TOTAL_DIFF
from VW_TOTAL_DIFF_HOC total_diff;

create view VW_TOTAL_LEVEL02_HOC as
select 
	total.NM_LABEL,
	total.ID_RESUMO,
	total.CD_MES,
	total.CD_ANO,
	total.ID_EMPRESA,
	total.ID_CONTRATO,
	sum( total.TOTAL_COPART ) - sum( total.TOTAL_DIFF ) TOTAL_COPART
from VW_TOTAL_LEVEL01_HOC total
group by
	total.ID_RESUMO,
	total.NM_LABEL,
	total.CD_MES,
	total.CD_ANO,
	total.ID_CONTRATO,
	total.ID_EMPRESA;

create view VW_RESUMO_LEVEL01_HOC as
select 
	'Total Coparticipação' NM_LABEL,
	1 ID_RESUMO,
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.ID_CONTRATO,
	original_hoc.TOTAL_COPART TOTAL_COPART
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc
union all
select
	'Isenção de Gestantes' NM_LABEL,
	2 ID_RESUMO,
	isencao_gestantes.CD_MES,
	isencao_gestantes.CD_ANO,
	isencao_gestantes.ID_EMPRESA,
	isencao_gestantes.ID_CONTRATO,
	isencao_gestantes.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_GESTANTES_HOC isencao_gestantes
union all
select
	'Isenção HOC' NM_LABEL,
	2 ID_RESUMO,
	isencao.CD_MES,
	isencao.CD_ANO,
	isencao.ID_EMPRESA,
	isencao.ID_CONTRATO,
	isencao.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_VALOR_HOC isencao
union all
select
	'Isenção Conselheiro' NM_LABEL,
	2 ID_RESUMO,
	isencao_conselheiros.CD_MES,
	isencao_conselheiros.CD_ANO,
	isencao_conselheiros.ID_EMPRESA,
	isencao_conselheiros.ID_CONTRATO,
	isencao_conselheiros.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_CONSELHEIROS_HOC isencao_conselheiros
union all
select
	'Afastados' NM_LABEL,
	3 ID_RESUMO,
	afastados.CD_MES,
	afastados.CD_ANO,
	afastados.ID_EMPRESA,
	afastados.ID_CONTRATO,
	afastados.TOTAL_COPART TOTAL_COPART
from VW_AFASTADOS_HOC afastados
union all
select
	'Agregados' NM_LABEL,
	4 ID_RESUMO,
	agregados.CD_MES,
	agregados.CD_ANO,
	agregados.ID_EMPRESA,
	agregados.ID_CONTRATO,
	agregados.TOTAL_COPART TOTAL_COPART
from VW_AGREGADOS_HOC agregados
union all
select
	'Plano de Extensão' NM_LABEL,
	5 ID_RESUMO,
	plano_extensao.CD_MES,
	plano_extensao.CD_ANO,
	plano_extensao.ID_EMPRESA,
	plano_extensao.ID_CONTRATO,
	plano_extensao.TOTAL_COPART TOTAL_COPART
from VW_PLANO_EXTENSAO_HOC plano_extensao
union all
select
	'Desligados' NM_LABEL,
	6 ID_RESUMO,
	desligados.CD_MES,
	desligados.CD_ANO,
	desligados.ID_EMPRESA,
	desligados.ID_CONTRATO,
	desligados.TOTAL_COPART TOTAL_COPART
from VW_DESLIGADOS_HOC desligados
union all
select
	resumo_empty.NM_LABEL,
	resumo_empty.ID_RESUMO,
	resumo_empty.CD_MES,
	resumo_empty.CD_ANO,
	resumo_empty.ID_EMPRESA,
	resumo_empty.ID_CONTRATO,
	resumo_empty.TOTAL_COPART
from VW_RESUMO_EMPTY_HOC resumo_empty
union all
select
	total.NM_LABEL,
	total.ID_RESUMO,
	total.CD_MES,
	total.CD_ANO,
	total.ID_EMPRESA,
	total.ID_CONTRATO,
	total.TOTAL_COPART
from VW_TOTAL_LEVEL02_HOC total;

create view VW_RESUMO_HOC as
select
	resumo.ID_RESUMO,
	resumo.NM_LABEL,
	resumo.CD_MES,
	resumo.CD_ANO,
	resumo.ID_EMPRESA,
	resumo.ID_CONTRATO,
	sum( resumo.TOTAL_COPART ) TOTAL_COPART 
from VW_RESUMO_LEVEL01_HOC resumo
group by
	resumo.ID_RESUMO,
	resumo.NM_LABEL,
	resumo.CD_MES,
	resumo.CD_ANO,
	resumo.ID_CONTRATO,
	resumo.ID_EMPRESA
order by resumo.ID_RESUMO;
/**********************************************************************************************************************/

