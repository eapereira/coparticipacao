/**
 * Edson - 16/09/2018
 * Script para criar as tabelas usadas pelo processo CARGILL:
 * 
 * Desconto de 20% de coparticipação somente para admitidos até 01/06/2013. 
 * A partir de 02/06/2017 retira-se o limitador de consumo por grupo familiar. 
 * Logins 00437 e 00193 são apólices inativas e não possuem coparticipação. 
 * Devemos informar a coparticipação apenas para os planos Básico e Especial, o Executivo não possui coparticipação.
 */

drop view if exists VW_COPARTICIPACAO_LEVEL01_CARGILL;
drop view if exists VW_COPARTICIPACAO_LEVEL02_CARGILL;

drop view if exists VW_COPARTICIPACAO_CARGILL;
drop view if exists VW_PRN_CARGILL;
drop view if exists VW_DESCONHECIDO_CARGILL;

/**************************************************************************************************************************/
/**************************************************************************************************************************/

create view VW_DESCONHECIDO_CARGILL as
	select
		empresa.CD_EMPRESA,
		desconhecido.CD_MES,
		desconhecido.CD_ANO,
		desconhecido.ID_CONTRATO,
		contrato.CD_CONTRATO,
	    desconhecido.NR_MATRICULA,
	    desconhecido.NR_MATRICULA_EMPRESA,
	    desconhecido.NM_TITULAR,
	    desconhecido.NR_CPF,
	    desconhecido.NM_BENEFICIARIO,
	    'CP32' CD_VERBA,
	    desconhecido.DT_ADMISSAO,
	    desconhecido.NR_REF_CODE CD_PLANO
	from TB_DESCONHECIDO desconhecido
		join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	    join TB_EMPRESA empresa on
	    empresa.ID = contrato.ID_EMPRESA
	where	empresa.CD_EMPRESA	= 'CARGILL'	    
	union all
	select distinct
		empresa.CD_EMPRESA,
		lancamento.CD_MES,
		lancamento.CD_ANO,
		lancamento.ID_CONTRATO,
		contrato.CD_CONTRATO,
	    titular.NR_MATRICULA,
	    titular.NR_MATRICULA_EMPRESA,
	    titular.NM_TITULAR,
	    titular.NR_CPF,
	    titular.NM_TITULAR NM_BENEFICIARIO,
	    'CP32' CD_VERBA,
	    titular.DT_ADMISSAO,
	    titular.NR_REF_CODE CD_PLANO
	from TB_TITULAR titular
	    join TB_EMPRESA empresa on
	    empresa.ID = titular.ID_EMPRESA
	    join TB_LANCAMENTO lancamento on
	    lancamento.ID_TITULAR = titular.ID
	    join TB_CONTRATO contrato on
	    contrato.ID = lancamento.ID_CONTRATO	    
	where	empresa.CD_EMPRESA	= 'CARGILL'
	and		titular.DT_ADMISSAO is null;

/**************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_CARGILL as
select
		empresa.CD_EMPRESA,
		lancamento.CD_MES,
		lancamento.CD_ANO,
		lancamento.ID_CONTRATO,
		contrato.CD_CONTRATO,
	    titular.NR_CPF,
	    titular.NM_TITULAR,
	    titular.NR_MATRICULA,
	    titular.NR_MATRICULA_EMPRESA,
	    'CP32' CD_VERBA,
	    lpad( titular.NR_REF_CODE, 5, '0' ) CD_PLANO,
	    titular.DT_ADMISSAO,
	    case
	    	when lancamento.VL_PRINCIPAL >= 0 then '+'
	    	else '-'
	    end TP_SINAL,	    
	   	lancamento.VL_PRINCIPAL VL_PRINCIPAL,
	   	case
	   		when titular.DT_ADMISSAO <= str_to_date( '01/06/2013', '%d/%M/%Y' ) then lancamento.VL_PRINCIPAL - ( lancamento.VL_PRINCIPAL * 0.20 )
	   	else
	   		lancamento.VL_PRINCIPAL
	   	end VL_DESCONTO
	from TB_LANCAMENTO lancamento
		join TB_TITULAR titular on
	    titular.ID = lancamento.ID_TITULAR
	    join TB_EMPRESA empresa on
	    empresa.ID = titular.ID_EMPRESA
	    join TB_CONTRATO contrato on
	    contrato.ID = lancamento.ID_CONTRATO
	where titular.NR_MATRICULA not in (
		select desconhecido.NR_MATRICULA
		from VW_DESCONHECIDO_CARGILL desconhecido
		where desconhecido.NR_MATRICULA = titular.NR_MATRICULA );

create view VW_COPARTICIPACAO_LEVEL02_CARGILL as
	select
		cargill.CD_EMPRESA,
		cargill.CD_MES,
		cargill.CD_ANO,
		cargill.ID_CONTRATO,
		cargill.CD_CONTRATO,
	    cargill.NR_CPF,
	    cargill.NR_MATRICULA,
	    cargill.NR_MATRICULA_EMPRESA,
	    cargill.NM_TITULAR,
	    cargill.CD_VERBA,
	    cargill.CD_PLANO,
	    cargill.TP_SINAL,
	    cargill.DT_ADMISSAO,    
	    sum( cargill.VL_PRINCIPAL ) VL_PRINCIPAL,
	    sum( cargill.VL_DESCONTO ) VL_DESCONTO
	from VW_COPARTICIPACAO_LEVEL01_CARGILL cargill
	group by 
		cargill.CD_EMPRESA,
		cargill.CD_MES,
		cargill.CD_ANO,
		cargill.ID_CONTRATO,
		cargill.CD_CONTRATO,
	    cargill.NR_CPF,
	    cargill.NR_MATRICULA,
	    cargill.NR_MATRICULA_EMPRESA,
	    cargill.NM_TITULAR,
	    cargill.CD_VERBA,
	    cargill.CD_PLANO,
	    cargill.TP_SINAL,
	    cargill.DT_ADMISSAO    
	order by cargill.NM_TITULAR;

create view VW_COPARTICIPACAO_CARGILL as
	select
		cargill.CD_EMPRESA,
		cargill.CD_MES,
		cargill.CD_ANO,
		cargill.ID_CONTRATO,
		cargill.CD_CONTRATO,
	    cargill.NR_CPF,
	    cargill.NR_MATRICULA,
	    cargill.NR_MATRICULA_EMPRESA,
	    cargill.NM_TITULAR,
	    cargill.CD_VERBA,
	    cargill.CD_PLANO,
	    cargill.TP_SINAL,
	    cargill.DT_ADMISSAO,    
	    cargill.VL_PRINCIPAL,
	    cargill.VL_DESCONTO
	from VW_COPARTICIPACAO_LEVEL02_CARGILL cargill;
	
/**************************************************************************************************************************/
	
create view VW_PRN_CARGILL as
	select
		cargill.CD_EMPRESA,
		cargill.CD_MES,
		cargill.CD_ANO,
		cargill.ID_CONTRATO,		
		cargill.CD_CONTRATO,
	    cargill.NR_CPF,	    
	    cargill.NR_MATRICULA,
	    cargill.NR_MATRICULA_EMPRESA,
	    cargill.CD_VERBA,
	    cargill.CD_PLANO,
	    cargill.TP_SINAL,
	    cargill.VL_PRINCIPAL,
	    cargill.VL_DESCONTO
	from VW_COPARTICIPACAO_LEVEL02_CARGILL cargill;
	
	
	