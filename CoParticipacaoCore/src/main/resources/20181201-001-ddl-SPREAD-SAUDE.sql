/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE;
drop view if exists VW_DESCONHECIDO_SPREAD_SAUDE;
drop view if exists VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE;
drop view if exists VW_COPARTICIPACAO_SPREAD_SAUDE;
drop view if exists VW_COPARTICIPACAO_RESUMO_SPREAD_SAUDE;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE as
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
    desconhecido.VL_FATOR_MODERADOR_INSS,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL,
    desconhecido.VL_INSS,
    desconhecido.VL_ALIQUOTA_INSS,
    desconhecido.VL_LIQUIDO_SINISTRO
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '180831'
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
	titular.VL_FATOR_MODERADOR_INSS,
    titular.DESCR_PROFISSAO,
    titular.NR_MATRICULA_ESPECIAL,
	titular.VL_INSS,
	titular.VL_ALIQUOTA_INSS,
    titular.VL_LIQUIDO_SINISTRO
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = '180831'
and		(( titular.VL_FATOR_MODERADOR is null or titular.VL_FATOR_MODERADOR <= 0 ) or 
		( titular.VL_FATOR_MODERADOR_INSS is null or titular.VL_FATOR_MODERADOR_INSS <= 0 ))
and titular.DESCR_PROFISSAO is null;
	
create view VW_DESCONHECIDO_SPREAD_SAUDE as
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
    desconhecido.VL_FATOR_MODERADOR_INSS,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL,
	desconhecido.VL_INSS,
	desconhecido.VL_ALIQUOTA_INSS,
	desconhecido.VL_LIQUIDO_SINISTRO
from VW_DESCONHECIDO_LEVEL01_SPREAD_SAUDE desconhecido
order by desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    2 TP_REGISTRO,
    contrato.CD_CONTRATO,
	lpad( titular.NR_MATRICULA, 7, '0' ) NR_CERTIFICADO,
	lpad( titular.NR_MATRICULA_EMPRESA, 10, '0' ) NR_MATRICULA,	
	lpad( titular.NR_SUBFATURA, 3, '0' ) NR_SUBFATURA,
	titular.NM_TITULAR,
	ifnull( titular.VL_FATOR_MODERADOR, 0.0 ) VL_FATOR_MODERADOR,
	ifnull( titular.VL_FATOR_MODERADOR_INSS, 0.0 ) VL_FATOR_MODERADOR_INSS, 
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	empresa.CD_EMPRESA,
	ifnull( titular.VL_INSS, 0.0 ) VL_INSS,
	ifnull( titular.VL_LIQUIDO_SINISTRO, 0.0 ) VL_LIQUIDO_SINISTRO,
	titular.IND_EVENTO,
	ifnull( titular.VL_ALIQUOTA_INSS, 0.0 ) VL_ALIQUOTA_INSS,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED		
from TB_LANCAMENTO lancamento 
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA			= '180831'
and		lancamento.ID_DEPENDENTE is null
and		lancamento.VL_PRINCIPAL 	> 0
and		titular.NR_SUBFATURA in ( 1, 3, 5, 7, 9 )
and		titular.NR_MATRICULA not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_COELBA_ODONTO desconhecido
	where desconhecido.NR_MATRICULA = titular.NR_MATRICULA )
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    2 TP_REGISTRO,
    contrato.CD_CONTRATO,
	lpad( titular.NR_MATRICULA, 7, '0' ) NR_CERTIFICADO,
	lpad( titular.NR_MATRICULA_EMPRESA, 10, '0' ) NR_MATRICULA,	
	lpad( titular.NR_SUBFATURA, 3, '0' ) NR_SUBFATURA,
	titular.NM_TITULAR,
	ifnull( titular.VL_FATOR_MODERADOR, 0.0 ) VL_FATOR_MODERADOR,
	ifnull( titular.VL_FATOR_MODERADOR_INSS, 0.0 ) VL_FATOR_MODERADOR_INSS, 
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	empresa.CD_EMPRESA,
	ifnull( titular.VL_INSS, 0.0 ) VL_INSS,
	ifnull( titular.VL_LIQUIDO_SINISTRO, 0.0 ) VL_LIQUIDO_SINISTRO,
	titular.IND_EVENTO,
	ifnull( titular.VL_ALIQUOTA_INSS, 0.0 ) VL_ALIQUOTA_INSS,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED		
from TB_LANCAMENTO lancamento 
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE
where	empresa.CD_EMPRESA			= '180831'
and		lancamento.ID_DEPENDENTE is not null
and		lancamento.VL_PRINCIPAL 	> 0
and		titular.NR_SUBFATURA in ( 1, 3, 5, 7, 9 )
and		dependente.NR_MATRICULA not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_COELBA_ODONTO desconhecido
	where desconhecido.NR_MATRICULA = dependente.NR_MATRICULA );

create view VW_COPARTICIPACAO_SPREAD_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	spread.CD_MES,
    spread.CD_ANO,
    spread.TP_REGISTRO,
    spread.ID_CONTRATO,
    spread.CD_CONTRATO,
    spread.NR_CERTIFICADO,
	spread.NR_MATRICULA,
	spread.NM_TITULAR,
	spread.NR_SUBFATURA,
	spread.VL_FATOR_MODERADOR,
	spread.VL_FATOR_MODERADOR_INSS,
	spread.NR_MATRICULA_ESPECIAL,
	spread.DESCR_PROFISSAO,
	spread.CD_EMPRESA,
	spread.VL_INSS,
	spread.VL_LIQUIDO_SINISTRO,
	spread.IND_EVENTO,
	spread.VL_ALIQUOTA_INSS,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED			
from	VW_COPARTICIPACAO_LEVEL01_SPREAD_SAUDE spread
group by
	spread.CD_MES,
    spread.CD_ANO,
    spread.TP_REGISTRO,
    spread.ID_CONTRATO,
    spread.CD_CONTRATO,
    spread.NR_CERTIFICADO,
	spread.NR_MATRICULA,
	spread.NM_TITULAR,
	spread.NR_SUBFATURA,
	spread.VL_FATOR_MODERADOR,
	spread.VL_FATOR_MODERADOR_INSS,
	spread.NR_MATRICULA_ESPECIAL,
	spread.DESCR_PROFISSAO,
	spread.CD_EMPRESA,
	spread.VL_INSS,
	spread.VL_LIQUIDO_SINISTRO,
	spread.IND_EVENTO,
	spread.VL_ALIQUOTA_INSS,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED				
order by
	spread.NR_SUBFATURA, spread.NM_TITULAR;
	
create view VW_COPARTICIPACAO_RESUMO_SPREAD_SAUDE as
select
	FUNC_GET_ROWNUM() ID,
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
    spread.CD_CONTRATO,
    spread.CD_EMPRESA,
    spread.NR_SUBFATURA,
	count(1) QTDE_SEGURADOS,
	sum( spread.VL_FATOR_MODERADOR ) VL_ALOCACAO,
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_SPREAD_SAUDE ) VL_PROPORCAO,	
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED			
from	VW_COPARTICIPACAO_SPREAD_SAUDE spread
group by
	spread.CD_MES,
    spread.CD_ANO,
    spread.ID_CONTRATO,
    spread.CD_CONTRATO,
    spread.CD_EMPRESA,
    spread.NR_SUBFATURA,
	spread.VERSION,
	spread.USER_CREATED,
	spread.USER_ALTERED,
	spread.DT_CREATED,
	spread.DT_ALTERED;
