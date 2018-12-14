/**
 * Edson - 06/08/2018
 */

/*****************************************************************************************************************/
drop view if exists VW_ISENTOS_ABBVIE;
drop view if exists VW_DELLOITE_ABBVIE;
drop view if exists VW_COPARTICIPACAO_ABBVIE;
drop view if exists VW_COPARTICIPACAO_ABBVIE_LEVEL01;

drop view if exists VW_DESCONHECIDO_ABBVIE;
drop view if exists VW_DESCONHECIDO_LEVEL01_ABBVIE;
drop view if exists VW_DESCONHECIDO_LEVEL02_ABBVIE;
drop view if exists VW_MENORES_12_MESES_ABBVIE;
/*****************************************************************************************************************/

create view VW_COPARTICIPACAO_ABBVIE_LEVEL01 as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    concat( lancamento.CD_ANO, lpad( lancamento.CD_MES, 2, '0' )) COMPETENCIA,
    titular.ID ID_TITULAR,
	titular.NM_TITULAR,
    titular.NR_MATRICULA NR_MATRICULA_TITULAR,
	titular.DT_ADMISSAO,    
    titular.ID ID_DEPENDENTE,
	titular.NM_TITULAR NM_DEPENDENTE,
    titular.NR_MATRICULA NR_MATRICULA_DEPENDENTE,    
    titular.NR_MATRICULA_EMPRESA NR_UPI,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	lancamento.ID_DEPENDENTE is null
and		empresa.CD_EMPRESA = 'ABBVIE'
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    concat( lancamento.CD_ANO, lpad( lancamento.CD_MES, 2, '0' )) COMPETENCIA,
    titular.ID ID_TITULAR,
	titular.NM_TITULAR,
    titular.NR_MATRICULA NR_MATRICULA_TITULAR,
    titular.DT_ADMISSAO,
    dependente.ID ID_DEPENDENTE,
	dependente.NM_DEPENDENTE,
    dependente.NR_MATRICULA NR_MATRICULA_DEPENDENTE,    
    titular.NR_MATRICULA_EMPRESA NR_UPI,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	lancamento.ID_DEPENDENTE is not null
and 	titular.NR_MATRICULA_EMPRESA is not null
and		empresa.CD_EMPRESA = 'ABBVIE';

create view VW_MENORES_12_MESES_ABBVIE as
select
	dependente.ID ID_DEPENDENTE,
	dependente.NM_DEPENDENTE
from TB_DEPENDENTE dependente
where datediff( current_date(), dependente.DT_NASCIMENTO ) <= 365;

/*****************************************************************************************************************/
/*****************************************************************************************************************/

create view VW_COPARTICIPACAO_ABBVIE as
select
	copa.CD_MES,
    copa.CD_ANO,
    copa.ID_CONTRATO,
    copa.COMPETENCIA,
    copa.ID_TITULAR,
	copa.NM_TITULAR,
    copa.NR_MATRICULA_TITULAR,
    copa.DT_ADMISSAO,
    copa.ID_DEPENDENTE,
	copa.NM_DEPENDENTE,
    copa.NR_MATRICULA_DEPENDENTE,    
    copa.NR_UPI,
    FUNC_DOUBLE_TO_LONG( sum( copa.VL_PRINCIPAL )) VL_PRINCIPAL
from VW_COPARTICIPACAO_ABBVIE_LEVEL01 copa
where copa.ID_TITULAR not in (
	select isento.ID_TITULAR
	from TB_TITULAR_ISENTO isento
	where isento.ID_TITULAR = copa.ID_TITULAR )
and copa.ID_DEPENDENTE not in (
	select isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO isento
	where isento.ID_DEPENDENTE = copa.ID_DEPENDENTE )
group by 	
	copa.CD_MES,
    copa.CD_ANO,
    copa.ID_CONTRATO,
    copa.COMPETENCIA,
    copa.ID_TITULAR,
	copa.NM_TITULAR,
    copa.NR_MATRICULA_TITULAR,
    copa.DT_ADMISSAO,
    copa.ID_DEPENDENTE,
	copa.NM_DEPENDENTE,
    copa.NR_MATRICULA_DEPENDENTE,    
    copa.NR_UPI
order by copa.NM_DEPENDENTE, copa.NM_TITULAR;

create view VW_ISENTOS_ABBVIE as
select
	copa.CD_MES,
    copa.CD_ANO,
    copa.ID_CONTRATO,
    copa.COMPETENCIA,
    copa.ID_TITULAR,
	copa.NM_TITULAR,
    copa.NR_MATRICULA_TITULAR,
    copa.DT_ADMISSAO,
    copa.ID_DEPENDENTE,
	copa.NM_DEPENDENTE,
    copa.NR_MATRICULA_DEPENDENTE,    
    copa.NR_UPI,
    FUNC_DOUBLE_TO_LONG( sum( copa.VL_PRINCIPAL )) VL_PRINCIPAL
from VW_COPARTICIPACAO_ABBVIE_LEVEL01 copa
where copa.ID_TITULAR in (
	select isento.ID_TITULAR
	from TB_TITULAR_ISENTO isento
	where isento.ID_TITULAR = copa.ID_TITULAR )
or copa.ID_DEPENDENTE in (
	select isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO isento
	where isento.ID_DEPENDENTE = copa.ID_DEPENDENTE )
or  copa.ID_DEPENDENTE in (
	select isento.ID_DEPENDENTE
	from VW_MENORES_12_MESES_ABBVIE isento
	where isento.ID_DEPENDENTE = copa.ID_DEPENDENTE )
group by 	
	copa.CD_MES,
    copa.CD_ANO,
    copa.ID_CONTRATO,
    copa.COMPETENCIA,
    copa.ID_TITULAR,
	copa.NM_TITULAR,
    copa.NR_MATRICULA_TITULAR,
    copa.DT_ADMISSAO,
    copa.ID_DEPENDENTE,
	copa.NM_DEPENDENTE,
    copa.NR_MATRICULA_DEPENDENTE,    
    copa.NR_UPI
order by copa.NM_DEPENDENTE, copa.NM_TITULAR;

create view VW_DELLOITE_ABBVIE as
select
	copa.CD_MES,
    copa.CD_ANO,
    copa.ID_CONTRATO,
    copa.COMPETENCIA,
    1 CD_EMPRESA,
    date_format( copa.DT_ADMISSAO, '%Y%m%d' ) DT_ADMISSAO,
    8105 CD_EVENTO,
    copa.COMPETENCIA DT_INICIAL,
    copa.COMPETENCIA DT_FINAL,
    0 QTDE_TOTAL,
    0 QTDE_REALIZADA,
	copa.NM_TITULAR,
    copa.NR_MATRICULA_TITULAR,
	copa.NM_DEPENDENTE,
    copa.NR_MATRICULA_DEPENDENTE,    
    copa.NR_UPI,
    copa.VL_PRINCIPAL
from VW_COPARTICIPACAO_ABBVIE copa;

/*****************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_ABBVIE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.NR_MATRICULA,
    desconhecido.NR_CPF,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NM_TITULAR,
    desconhecido.DT_ADMISSAO,
    desconhecido.NR_MATRICULA_EMPRESA
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA	
where	empresa.CD_EMPRESA = 'ABBVIE';


create view VW_DESCONHECIDO_LEVEL02_ABBVIE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.NR_MATRICULA,
    desconhecido.NR_CPF,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NM_TITULAR,
    desconhecido.DT_ADMISSAO,
    desconhecido.NR_MATRICULA_EMPRESA
from VW_DESCONHECIDO_LEVEL01_ABBVIE desconhecido
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    titular.NR_MATRICULA,
    titular.NR_CPF,
    titular.NM_TITULAR NM_BENEFICIARIO,
    titular.NM_TITULAR,
    titular.DT_ADMISSAO,
    titular.NR_MATRICULA_EMPRESA
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA	
where	( titular.NR_MATRICULA_EMPRESA is null or
		  titular.DT_ADMISSAO is null )
and 	lancamento.ID_DEPENDENTE is null
and		empresa.CD_EMPRESA = 'ABBVIE'
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    dependente.NR_MATRICULA,
    dependente.NR_CPF,
    dependente.NM_DEPENDENTE NM_BENEFICIARIO,
    titular.NM_TITULAR,
    null DT_ADMISSAO,
    dependente.NR_MATRICULA_EMPRESA
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID 			= lancamento.ID_DEPENDENTE and
		dependente.ID_TITULAR	= lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	dependente.NR_MATRICULA_EMPRESA is null
and		empresa.CD_EMPRESA = 'ABBVIE';	

create view VW_DESCONHECIDO_ABBVIE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.NR_MATRICULA,
    desconhecido.NR_CPF,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NM_TITULAR,
    desconhecido.DT_ADMISSAO,
    desconhecido.NR_MATRICULA_EMPRESA
from VW_DESCONHECIDO_LEVEL02_ABBVIE desconhecido
order by desconhecido.NM_BENEFICIARIO;

/*****************************************************************************************************************/
