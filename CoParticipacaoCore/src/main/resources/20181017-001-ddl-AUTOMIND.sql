/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_AUTOMIND;
drop view if exists VW_DESCONHECIDO_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_LEVEL01_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_RESUMO_AUTOMIND;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_AUTOMIND as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    0.0 VL_PARTICIPACAO,
    desconhecido.VL_FATOR_MODERADOR,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '074210'
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
	lancamento.VL_PARTICIPACAO,
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
where	empresa.CD_EMPRESA = '074210'
and		lancamento.ID_DEPENDENTE is null
and		(( lancamento.VL_PARTICIPACAO is null or lancamento.VL_PARTICIPACAO <= 0 ) and
		( titular.VL_FATOR_MODERADOR is null or titular.VL_FATOR_MODERADOR <= 0 ))
and		titular.NR_SUBFATURA is null
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	dependente.NR_MATRICULA,
	dependente.NR_SUBFATURA,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NR_CPF,
	titular.NM_TITULAR,
	lancamento.VL_PARTICIPACAO,
	dependente.VL_FATOR_MODERADOR,
    titular.DESCR_PROFISSAO,
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
where	empresa.CD_EMPRESA = '074210'
and		lancamento.ID_DEPENDENTE is not null
and		(( lancamento.VL_PARTICIPACAO is null or lancamento.VL_PARTICIPACAO <= 0 ) and
		( dependente.VL_FATOR_MODERADOR is null or dependente.VL_FATOR_MODERADOR <= 0 ))
and		dependente.NR_SUBFATURA is null;
		
create view VW_DESCONHECIDO_AUTOMIND as
select distinct
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.VL_PARTICIPACAO,
    desconhecido.VL_FATOR_MODERADOR,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL    
from VW_DESCONHECIDO_LEVEL01_AUTOMIND desconhecido
order by desconhecido.NM_BENEFICIARIO;

create view VW_COPARTICIPACAO_LEVEL01_AUTOMIND as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    contrato.CD_CONTRATO,
	lpad( ifnull( titular.NR_MATRICULA, 0 ), 7, '0' ) NR_CERTIFICADO,
	lpad( ifnull( titular.NR_MATRICULA_EMPRESA, 0 ), 10, '0' ) NR_MATRICULA,	
	lpad( titular.NR_SUBFATURA, 3, '0' ) NR_SUBFATURA,
	titular.NM_TITULAR,
    lancamento.VL_PARTICIPACAO,
	titular.VL_FATOR_MODERADOR,
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
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
where	empresa.CD_EMPRESA			= '074210'
and		lancamento.VL_PARTICIPACAO > 0;

create view VW_COPARTICIPACAO_AUTOMIND as
select
	FUNC_GET_ROWNUM() ID,
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.NR_CERTIFICADO,
	automind.NR_MATRICULA,
	automind.NM_TITULAR,
	automind.NR_SUBFATURA,
	automind.VL_FATOR_MODERADOR,
	sum( automind.VL_PARTICIPACAO ) VL_PARTICIPACAO,
	automind.NR_MATRICULA_ESPECIAL,
	automind.DESCR_PROFISSAO,
	automind.CD_EMPRESA,
	automind.VERSION,
	automind.USER_CREATED,
	automind.USER_ALTERED,
	automind.DT_CREATED,
	automind.DT_ALTERED
from	VW_COPARTICIPACAO_LEVEL01_AUTOMIND automind	
group by
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.NR_CERTIFICADO,
	automind.NR_MATRICULA,
	automind.NM_TITULAR,
	automind.NR_SUBFATURA,
	automind.VL_FATOR_MODERADOR,
	automind.NR_MATRICULA_ESPECIAL,
	automind.DESCR_PROFISSAO,
	automind.CD_EMPRESA,
	automind.VERSION,
	automind.USER_CREATED,
	automind.USER_ALTERED,
	automind.DT_CREATED,
	automind.DT_ALTERED	
order by
	automind.NM_TITULAR;
	
create view VW_COPARTICIPACAO_RESUMO_AUTOMIND as
select
	FUNC_GET_ROWNUM() ID,
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.CD_EMPRESA,
    automind.NR_SUBFATURA,
	count(1) QTDE_SEGURADOS,
	sum( automind.VL_PARTICIPACAO ) VL_ALOCACAO,
	( count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_AUTOMIND )) * 100 VL_PROPORCAO,
	automind.VERSION,
	automind.USER_CREATED,
	automind.USER_ALTERED,
	automind.DT_CREATED,
	automind.DT_ALTERED	
from	VW_COPARTICIPACAO_AUTOMIND automind
group by
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.CD_EMPRESA,
    automind.NR_SUBFATURA,
    automind.VERSION,
	automind.USER_CREATED,
	automind.USER_ALTERED,
	automind.DT_CREATED,
	automind.DT_ALTERED;    
	