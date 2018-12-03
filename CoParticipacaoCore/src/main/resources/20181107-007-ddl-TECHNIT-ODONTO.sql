/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_TECHNIT_ODONTO;
drop view if exists VW_DESCONHECIDO_TECHNIT_ODONTO;
drop view if exists VW_COPARTICIPACAO_LEVEL01_TECHNIT_ODONTO;
drop view if exists VW_COPARTICIPACAO_TECHNIT_ODONTO;
drop view if exists VW_COPARTICIPACAO_RESUMO_TECHNIT_ODONTO;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_TECHNIT_ODONTO as
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
where empresa.CD_EMPRESA = '091707'
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
where	empresa.CD_EMPRESA = 'TECHNIT_ODONTO'
and		(( titular.VL_FATOR_MODERADOR is null or titular.VL_FATOR_MODERADOR <= 0 ) or 
		( titular.VL_FATOR_MODERADOR_INSS is null or titular.VL_FATOR_MODERADOR_INSS <= 0 ));
	
create view VW_DESCONHECIDO_TECHNIT_ODONTO as
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
from VW_DESCONHECIDO_LEVEL01_TECHNIT_ODONTO desconhecido
order by desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_TECHNIT_ODONTO as
select
	month(current_date()) CD_MES,
    year(current_date()) CD_ANO,
    titular.ID_CONTRATO,
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
from TB_TITULAR titular
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA			= '091707'
and		titular.NR_SUBFATURA in ( 1, 3, 5, 6 );

create view VW_COPARTICIPACAO_TECHNIT_ODONTO as
select
	FUNC_GET_ROWNUM() ID,
	technit.CD_MES,
    technit.CD_ANO,
    technit.TP_REGISTRO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.NR_CERTIFICADO,
	technit.NR_MATRICULA,
	technit.NM_TITULAR,
	technit.NR_SUBFATURA,
	technit.VL_FATOR_MODERADOR,
	technit.VL_FATOR_MODERADOR_INSS,
	technit.NR_MATRICULA_ESPECIAL,
	technit.DESCR_PROFISSAO,
	technit.CD_EMPRESA,
	technit.VL_INSS,
	technit.VL_LIQUIDO_SINISTRO,
	technit.IND_EVENTO,
	technit.VL_ALIQUOTA_INSS,
	technit.VERSION,
	technit.USER_CREATED,
	technit.USER_ALTERED,
	technit.DT_CREATED,
	technit.DT_ALTERED			
from	VW_COPARTICIPACAO_LEVEL01_TECHNIT_ODONTO technit
group by
	technit.CD_MES,
    technit.CD_ANO,
    technit.TP_REGISTRO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.NR_CERTIFICADO,
	technit.NR_MATRICULA,
	technit.NM_TITULAR,
	technit.NR_SUBFATURA,
	technit.VL_FATOR_MODERADOR,
	technit.VL_FATOR_MODERADOR_INSS,
	technit.NR_MATRICULA_ESPECIAL,
	technit.DESCR_PROFISSAO,
	technit.CD_EMPRESA,
	technit.VL_INSS,
	technit.VL_LIQUIDO_SINISTRO,
	technit.IND_EVENTO,
	technit.VL_ALIQUOTA_INSS,
	technit.VERSION,
	technit.USER_CREATED,
	technit.USER_ALTERED,
	technit.DT_CREATED,
	technit.DT_ALTERED				
order by
	technit.NR_SUBFATURA, technit.NM_TITULAR;
	
create view VW_COPARTICIPACAO_RESUMO_TECHNIT_ODONTO as
select
	FUNC_GET_ROWNUM() ID,
	technit.CD_MES,
    technit.CD_ANO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.CD_EMPRESA,
    technit.NR_SUBFATURA,
	count(1) QTDE_SEGURADOS,
	sum( technit.VL_FATOR_MODERADOR ) VL_ALOCACAO,
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_TECHNIT_ODONTO ) VL_PROPORCAO,	
	technit.VERSION,
	technit.USER_CREATED,
	technit.USER_ALTERED,
	technit.DT_CREATED,
	technit.DT_ALTERED			
from	VW_COPARTICIPACAO_TECHNIT_ODONTO technit
group by
	technit.CD_MES,
    technit.CD_ANO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.CD_EMPRESA,
    technit.NR_SUBFATURA,
	technit.VERSION,
	technit.USER_CREATED,
	technit.USER_ALTERED,
	technit.DT_CREATED,
	technit.DT_ALTERED;
