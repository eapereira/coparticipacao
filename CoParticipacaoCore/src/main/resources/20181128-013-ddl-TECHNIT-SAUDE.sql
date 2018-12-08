/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_LEVEL01_TECHNIT_SAUDE;
drop view if exists VW_DESCONHECIDO_TECHNIT_SAUDE;
drop view if exists VW_COPARTICIPACAO_LEVEL01_TECHNIT_SAUDE;
drop view if exists VW_COPARTICIPACAO_TECHNIT_SAUDE;
drop view if exists VW_COPARTICIPACAO_RESUMO_TECHNIT_SAUDE;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_LEVEL01_TECHNIT_SAUDE as
select
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    contrato.CD_CONTRATO,
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
where empresa.CD_EMPRESA = '180831'
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
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
where	empresa.CD_EMPRESA = '180831'
and		lancamento.ID_DEPENDENTE is null
and		(( titular.VL_FATOR_MODERADOR is null or titular.VL_FATOR_MODERADOR <= 0 ) or 
		( titular.VL_FATOR_MODERADOR_INSS is null or titular.VL_FATOR_MODERADOR_INSS <= 0 ))
and titular.DESCR_PROFISSAO is null
union all
select
	lancamento.CD_MES,
	lancamento.CD_ANO,
	lancamento.ID_CONTRATO,
	contrato.CD_CONTRATO,
	titular.NR_MATRICULA,
	titular.NR_SUBFATURA,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.NR_CPF,
	titular.NM_TITULAR,
	dependente.VL_FATOR_MODERADOR,
	dependente.VL_FATOR_MODERADOR_INSS,
    titular.DESCR_PROFISSAO,
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
where	empresa.CD_EMPRESA = '180831'
and		lancamento.ID_DEPENDENTE is not null
and		(( dependente.VL_FATOR_MODERADOR is null or dependente.VL_FATOR_MODERADOR <= 0 ) or 
		( dependente.VL_FATOR_MODERADOR_INSS is null or dependente.VL_FATOR_MODERADOR_INSS <= 0 ));
	
create view VW_DESCONHECIDO_TECHNIT_SAUDE as
select distinct
	desconhecido.CD_MES,
    desconhecido.CD_ANO,
    desconhecido.ID_CONTRATO,
    desconhecido.CD_CONTRATO,
	desconhecido.NR_MATRICULA,
	desconhecido.NR_SUBFATURA,
    desconhecido.NM_BENEFICIARIO,
    desconhecido.NR_CPF,
    desconhecido.NM_TITULAR,
    desconhecido.VL_FATOR_MODERADOR,
    desconhecido.VL_FATOR_MODERADOR_INSS,
    desconhecido.DESCR_PROFISSAO,
    desconhecido.NR_MATRICULA_ESPECIAL
from VW_DESCONHECIDO_LEVEL01_TECHNIT_SAUDE desconhecido
order by
	desconhecido.NM_TITULAR, 
	desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_TECHNIT_SAUDE as
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
	titular.NM_TITULAR NM_BENEFICIARIO,
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
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
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

create view VW_COPARTICIPACAO_TECHNIT_SAUDE as
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
	technit.NM_BENEFICIARIO,
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
from	VW_COPARTICIPACAO_LEVEL01_TECHNIT_SAUDE technit
group by
	technit.CD_MES,
    technit.CD_ANO,
    technit.TP_REGISTRO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.NR_CERTIFICADO,
	technit.NR_MATRICULA,
	technit.NM_TITULAR,
	technit.NM_BENEFICIARIO,
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
	
create view VW_COPARTICIPACAO_RESUMO_TECHNIT_SAUDE as
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
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_TECHNIT_SAUDE ) VL_PROPORCAO,	
	technit.VERSION,
	technit.USER_CREATED,
	technit.USER_ALTERED,
	technit.DT_CREATED,
	technit.DT_ALTERED			
from	VW_COPARTICIPACAO_TECHNIT_SAUDE technit
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
