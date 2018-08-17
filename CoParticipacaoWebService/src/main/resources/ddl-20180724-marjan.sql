/**
 * Edson - 24/07/2018
 *
 */
drop view if exists VW_LANCAMENTO_DEPENDENTE_MARJAN;
drop view if exists VW_LANCAMENTO_FILHOS_MARJAN;
drop view if exists VW_LANCAMENTO_MARJAN;
drop view if exists VW_LANCAMENTO_DETAIL_MARJAN;
drop view if exists VW_ISENTOS_FILHOS_12_MESES_MARJAN;

drop view if exists VW_COPARTICIPACAO_DEPENDENTE_MARJAN;
drop view if exists VW_COPARTICIPACAO_MARJAN; 
drop view if exists VW_ISENTOS_FILHOS_MARJAN;
drop view if exists VW_ISENTOS_ESTAGIARIO_MARJAN;
drop view if exists VW_ISENTOS_GESTANTE_MARJAN;

drop view if exists VW_ISENTO_MARJAN;
drop view if exists VW_COPARTICIPACAO_TELA_MARJAN;

drop view if exists VW_DESCONHECIDO_MARJAN_LEVEL01;
drop view if exists VW_DESCONHECIDO_MARJAN;
/*********************************************************************************************************************/
/*********************************************************************************************************************/
create view VW_LANCAMENTO_DEPENDENTE_MARJAN as
select
	lancamento.ID ID_LANCAMENTO,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    contrato.ID ID_CONTRATO,
    contrato.CD_CONTRATO,
    empresa.ID ID_EMPRESA,
    titular.NR_MATRICULA NR_MATRICULA_TITULAR,
	titular.ID ID_TITULAR,
	titular.NM_TITULAR,
	dependente.NR_MATRICULA_EMPRESA NR_MATRICULA_DEPENDENTE,
	dependente.ID ID_DEPENDENTE,
	dependente.NM_DEPENDENTE,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
join TB_DEPENDENTE dependente on
	dependente.ID = lancamento.ID_DEPENDENTE
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
where dependente.NR_MATRICULA_EMPRESA is not null;
	
create view VW_LANCAMENTO_MARJAN as
select
	lancamento.ID ID_LANCAMENTO,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    contrato.ID ID_CONTRATO,
    contrato.CD_CONTRATO,
    empresa.ID ID_EMPRESA,
    titular.NR_MATRICULA NR_MATRICULA_TITULAR,
	titular.ID ID_TITULAR,
	titular.NM_TITULAR,
	dependente.NR_MATRICULA_EMPRESA NR_MATRICULA_DEPENDENTE,
	dependente.ID ID_DEPENDENTE,
	dependente.NM_DEPENDENTE,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
join TB_DEPENDENTE dependente on
	dependente.ID = lancamento.ID_DEPENDENTE
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
where dependente.NR_MATRICULA_EMPRESA is not null
union all
select
	lancamento.ID ID_LANCAMENTO,
    lancamento.CD_MES,
    lancamento.CD_ANO,
    contrato.ID ID_CONTRATO,
    contrato.CD_CONTRATO,
    empresa.ID ID_EMPRESA,
    titular.NR_MATRICULA NR_MATRICULA_TITULAR,
	titular.ID ID_TITULAR,
	titular.NM_TITULAR,
	null NR_MATRICULA_DEPENDENTE,
	titular.ID ID_DEPENDENTE,
	titular.NM_TITULAR NM_DEPENDENTE,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
where lancamento.ID_DEPENDENTE is null;

create view VW_ISENTOS_FILHOS_12_MESES_MARJAN as
select
	dependente.ID ID_DEPENDENTE
from TB_DEPENDENTE dependente 
where datediff( current_date(), dependente.DT_NASCIMENTO ) <= 365
and dependente.NR_MATRICULA_EMPRESA is not null
and dependente.TP_DEPENDENTE = 2;

/*********************************************************************************************************************/
create view VW_COPARTICIPACAO_DEPENDENTE_MARJAN as
select 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE,
        sum( marjan.VL_PRINCIPAL ) VL_PRINCIPAL
from VW_LANCAMENTO_DEPENDENTE_MARJAN marjan 
group by 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;          


create view VW_COPARTICIPACAO_MARJAN as
select 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE,
        sum( marjan.VL_PRINCIPAL ) VL_PRINCIPAL
from VW_LANCAMENTO_MARJAN marjan
group by 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;        
	
create view VW_ISENTOS_FILHOS_MARJAN as
select 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE,
        marjan.VL_PRINCIPAL
from VW_COPARTICIPACAO_DEPENDENTE_MARJAN marjan
where marjan.ID_DEPENDENTE in (
	select
		isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO isento
    where	isento.ID_DEPENDENTE = marjan.ID_DEPENDENTE
    and		isento.TP_ISENTO = 2 )    
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;

create view VW_ISENTOS_ESTAGIARIO_MARJAN as
select 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE,
        marjan.VL_PRINCIPAL
from VW_COPARTICIPACAO_MARJAN marjan
where marjan.ID_TITULAR in (
	select
		isento.ID_TITULAR
	from TB_TITULAR_ISENTO isento
    where	isento.ID_TITULAR = marjan.ID_TITULAR
    and		isento.TP_ISENTO = 3 ) 
or marjan.ID_DEPENDENTE in (
	select
		isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO isento
    where	isento.ID_DEPENDENTE = marjan.ID_DEPENDENTE
    and		isento.TP_ISENTO = 3 ) 
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;
    
create view VW_ISENTOS_GESTANTE_MARJAN as
select 
		marjan.CD_MES,
        marjan.CD_ANO,
        marjan.ID_CONTRATO,
        marjan.CD_CONTRATO,
        marjan.ID_EMPRESA,
        marjan.NR_MATRICULA_TITULAR,
		marjan.ID_TITULAR,
		marjan.NM_TITULAR,
		marjan.NR_MATRICULA_DEPENDENTE,
        marjan.ID_DEPENDENTE,
        marjan.NM_DEPENDENTE,
        marjan.VL_PRINCIPAL
from VW_COPARTICIPACAO_MARJAN marjan
where marjan.ID_DEPENDENTE in (
	select
		isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO isento
    where	isento.ID_DEPENDENTE = marjan.ID_DEPENDENTE
    and		isento.TP_ISENTO = 1 )
or marjan.ID_TITULAR in (
	select
		isento.ID_TITULAR
	from TB_TITULAR_ISENTO isento
    where	isento.ID_TITULAR = marjan.ID_TITULAR
    and		isento.TP_ISENTO = 1 )
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;    


create view VW_COPARTICIPACAO_TELA_MARJAN as
select 
	marjan.CD_MES,
	marjan.CD_ANO,
	marjan.ID_CONTRATO,
	marjan.CD_CONTRATO,
	marjan.ID_EMPRESA,
	marjan.NR_MATRICULA_TITULAR,
	marjan.ID_TITULAR,
	marjan.NM_TITULAR,
	marjan.NR_MATRICULA_DEPENDENTE,
	marjan.ID_DEPENDENTE,
	marjan.NM_DEPENDENTE,
	marjan.VL_PRINCIPAL
from VW_COPARTICIPACAO_MARJAN marjan
where marjan.ID_TITULAR not in ( 
	select 	isento.ID_TITULAR
	from 	TB_TITULAR_ISENTO isento
	where	isento.ID_TITULAR = marjan.ID_TITULAR )
and marjan.ID_DEPENDENTE not in ( 
	select 	isento.ID_DEPENDENTE
	from 	TB_DEPENDENTE_ISENTO isento
	where	isento.ID_DEPENDENTE = marjan.ID_DEPENDENTE )
order by marjan.NM_TITULAR, marjan.NM_DEPENDENTE;

create view VW_DESCONHECIDO_MARJAN_LEVEL01 as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.NR_MATRICULA,
	desconhecido.NR_MATRICULA_EMPRESA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.VL_PRINCIPAL
from TB_DESCONHECIDO desconhecido
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	dependente.NR_MATRICULA,
	dependente.NR_MATRICULA_EMPRESA,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NR_CPF,
	lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
	dependente.ID = lancamento.ID_DEPENDENTE
where dependente.NR_MATRICULA_EMPRESA is null;

create view VW_DESCONHECIDO_MARJAN as
select
	marjan.CD_MES,
    marjan.CD_ANO,
    marjan.ID_CONTRATO,
    marjan.NR_MATRICULA,
	marjan.NR_MATRICULA_EMPRESA,
    marjan.NM_BENEFICIARIO,
    marjan.NR_CPF,
    marjan.VL_PRINCIPAL
from VW_DESCONHECIDO_MARJAN_LEVEL01 marjan
order by marjan.NM_BENEFICIARIO;

/*********************************************************************************************************************/
/*********************************************************************************************************************/
