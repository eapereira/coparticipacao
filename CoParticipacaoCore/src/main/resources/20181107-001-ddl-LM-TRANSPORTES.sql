/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_LM_TRANSPORTES;
drop view if exists VW_DESCONHECIDO_LM_TRANSPORTES;
drop view if exists VW_COPARTICIPACAO_LEVEL01_LM_TRANSPORTES;
drop view if exists VW_COPARTICIPACAO_LM_TRANSPORTES;
drop view if exists VW_COPARTICIPACAO_RESUMO_LM_TRANSPORTES;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_LM_TRANSPORTES as
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
    desconhecido.NR_MATRICULA_ESPECIAL
from TB_DESCONHECIDO desconhecido
	join TB_CONTRATO contrato on
		contrato.ID = desconhecido.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where empresa.CD_EMPRESA = '073179'
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
    titular.NR_MATRICULA_ESPECIAL	
from TB_LANCAMENTO lancamento
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA = 'LM_TRANSPORTES'
and		(( titular.VL_FATOR_MODERADOR is null or titular.VL_FATOR_MODERADOR <= 0 ) or 
		( titular.VL_FATOR_MODERADOR_INSS is null or titular.VL_FATOR_MODERADOR_INSS <= 0 ));
	
create view VW_DESCONHECIDO_LM_TRANSPORTES as
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
    desconhecido.NR_MATRICULA_ESPECIAL    
from VW_DESCONHECIDO_LEVEL01_LM_TRANSPORTES desconhecido
order by desconhecido.NM_BENEFICIARIO;

create view VW_COPARTICIPACAO_LEVEL01_LM_TRANSPORTES as
select	
	month(current_date()) CD_MES,
    year(current_date()) CD_ANO,
    titular.ID_CONTRATO,
    contrato.CD_CONTRATO,
	lpad( titular.NR_MATRICULA, 7, '0' ) NR_CERTIFICADO,
	lpad( titular.NR_MATRICULA_EMPRESA, 10, '0' ) NR_MATRICULA,	
	titular.NR_SUBFATURA,
	titular.NM_TITULAR,
	titular.VL_FATOR_MODERADOR,
	titular.VL_FATOR_MODERADOR_INSS,
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	empresa.CD_EMPRESA,
	0 VERSION,
	1 USER_CREATED,
	1 USER_ALTERED,
	current_date() DT_CREATED,
	current_date() DT_ALTERED	
from	TB_TITULAR titular
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
where	empresa.CD_EMPRESA			= '073179'
and		titular.VL_FATOR_MODERADOR 	> 0;

create view VW_COPARTICIPACAO_LM_TRANSPORTES as
select
	FUNC_GET_ROWNUM() ID,
	lmTransportes.CD_MES,
    lmTransportes.CD_ANO,
    lmTransportes.ID_CONTRATO,
    lmTransportes.CD_CONTRATO,
    lmTransportes.NR_CERTIFICADO,
	lmTransportes.NR_MATRICULA,
	lmTransportes.NM_TITULAR,
	lmTransportes.NR_SUBFATURA,
	lmTransportes.VL_FATOR_MODERADOR,
	lmTransportes.VL_FATOR_MODERADOR_INSS,
	lmTransportes.NR_MATRICULA_ESPECIAL,
	lmTransportes.DESCR_PROFISSAO,
	lmTransportes.CD_EMPRESA,
	lmTransportes.VERSION,
	lmTransportes.USER_CREATED,
	lmTransportes.USER_ALTERED,
	lmTransportes.DT_CREATED,
	lmTransportes.DT_ALTERED		
from	VW_COPARTICIPACAO_LEVEL01_LM_TRANSPORTES lmTransportes
group by
	lmTransportes.CD_MES,
    lmTransportes.CD_ANO,
    lmTransportes.ID_CONTRATO,
    lmTransportes.CD_CONTRATO,
    lmTransportes.NR_CERTIFICADO,
	lmTransportes.NR_MATRICULA,
	lmTransportes.NM_TITULAR,
	lmTransportes.NR_SUBFATURA,
	lmTransportes.VL_FATOR_MODERADOR,
	lmTransportes.VL_FATOR_MODERADOR_INSS,	
	lmTransportes.NR_MATRICULA_ESPECIAL,
	lmTransportes.DESCR_PROFISSAO,
	lmTransportes.CD_EMPRESA,
	lmTransportes.VERSION,
	lmTransportes.USER_CREATED,
	lmTransportes.USER_ALTERED,
	lmTransportes.DT_CREATED,
	lmTransportes.DT_ALTERED		
order by
	lmTransportes.NM_TITULAR;
	
create view VW_COPARTICIPACAO_RESUMO_LM_TRANSPORTES as
select
	FUNC_GET_ROWNUM() ID,
	lmTransportes.CD_MES,
    lmTransportes.CD_ANO,
    lmTransportes.ID_CONTRATO,
    lmTransportes.CD_CONTRATO,
    lmTransportes.CD_EMPRESA,
    lmTransportes.NR_SUBFATURA,
	count(1) QTDE_SEGURADOS,
	sum( lmTransportes.VL_FATOR_MODERADOR ) VL_ALOCACAO,
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_LM_TRANSPORTES ) VL_PROPORCAO,
	lmTransportes.VERSION,
	lmTransportes.USER_CREATED,
	lmTransportes.USER_ALTERED,
	lmTransportes.DT_CREATED,
	lmTransportes.DT_ALTERED		
from	VW_COPARTICIPACAO_LM_TRANSPORTES lmTransportes
group by
	lmTransportes.CD_MES,
    lmTransportes.CD_ANO,
    lmTransportes.ID_CONTRATO,
    lmTransportes.CD_CONTRATO,
    lmTransportes.CD_EMPRESA,
    lmTransportes.NR_SUBFATURA,
	lmTransportes.VERSION,
	lmTransportes.USER_CREATED,
	lmTransportes.USER_ALTERED,
	lmTransportes.DT_CREATED,
	lmTransportes.DT_ALTERED;	
    
	