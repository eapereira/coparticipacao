/**
 * Edson - 04/08/2018
 */

drop view if exists VW_LANCAMENTO_TITULAR;
drop view if exists VW_LANCAMENTO_MUITO_FACIL;
drop view if exists VW_DESCONHECIDO_MUITO_FACIL;
/**************************************************************************************************************************************/

create view VW_LANCAMENTO_TITULAR as
select 
	lancamento.ID,
	titular.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.CD_MES,
	lancamento.CD_ANO,	
    lancamento.ID_TITULAR,	
    titular.NM_TITULAR,
    LPAD( titular.NR_CPF, 11, '0') NR_CPF,
    titular.NR_MATRICULA,
    lancamento.VL_PRINCIPAL
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
	titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = 'MUITO-FACIL';
    
create view VW_LANCAMENTO_MUITO_FACIL as
select
	lancamento.ID_EMPRESA,
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	lancamento.NM_TITULAR,
	lancamento.NR_CPF,
	lancamento.NR_MATRICULA,    
	sum( lancamento.VL_PRINCIPAL ) VL_PRINCIPAL
from VW_LANCAMENTO_TITULAR lancamento
group by 
	lancamento.ID_EMPRESA,
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	lancamento.NM_TITULAR,
	lancamento.NR_CPF,
	lancamento.NR_MATRICULA
order by lancamento.NM_TITULAR;

create view VW_DESCONHECIDO_MUITO_FACIL as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
	desconhecido.NR_MATRICULA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = 'MUITO-FACIL';

		
/*****************************************************************************************************************************************************/
