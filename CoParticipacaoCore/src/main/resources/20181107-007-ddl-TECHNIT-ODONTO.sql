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
	desconhecido.VL_LIQUIDO_SINISTRO
from VW_DESCONHECIDO_LEVEL01_TECHNIT_ODONTO desconhecido
order by desconhecido.NM_BENEFICIARIO;

/****************************************************************************************************************************************************/

create view VW_COPARTICIPACAO_LEVEL01_TECHNIT_ODONTO as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    2 TP_REGISTRO,
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
	titular.VL_INSS,
	titular.VL_LIQUIDO_SINISTRO
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
where	empresa.CD_EMPRESA			= '091707'
and		titular.VL_FATOR_MODERADOR 	> 0;

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
	technit.VL_LIQUIDO_SINISTRO
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
	technit.VL_LIQUIDO_SINISTRO	
order by
	technit.NM_TITULAR;
	
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
	count( 1 ) / ( select count( 1 ) from VW_COPARTICIPACAO_TECHNIT_ODONTO ) VL_PROPORCAO
from	VW_COPARTICIPACAO_TECHNIT_ODONTO technit
group by
	technit.CD_MES,
    technit.CD_ANO,
    technit.ID_CONTRATO,
    technit.CD_CONTRATO,
    technit.CD_EMPRESA,
    technit.NR_SUBFATURA;
	