/**
 * edson - 17/10/2018
 */

drop view if exists VW_DESCONHECIDO_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_LEVEL01_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_AUTOMIND;
drop view if exists VW_COPARTICIPACAO_RESUMO_AUTOMIND;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_AUTOMIND as
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
where empresa.CD_EMPRESA = 'AUTOMIND';

create view VW_COPARTICIPACAO_LEVEL01_AUTOMIND as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
    contrato.CD_CONTRATO,
	titular.NR_MATRICULA NR_CERTIFICADO,
	titular.NR_MATRICULA_EMPRESA NR_MATRICULA,	
	titular.NM_TITULAR,
	titular.VL_FATOR_MODERADOR,
	titular.NR_MATRICULA_ESPECIAL,
	titular.DESCR_PROFISSAO,
	empresa.CD_EMPRESA
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
where	empresa.CD_EMPRESA	= 'AUTOMIND';

create view VW_COPARTICIPACAO_AUTOMIND as
select
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.NR_CERTIFICADO,
	automind.NR_MATRICULA,
	automind.NM_TITULAR,
	automind.VL_FATOR_MODERADOR,
	automind.NR_MATRICULA_ESPECIAL,
	automind.DESCR_PROFISSAO,
	automind.CD_EMPRESA
from	VW_COPARTICIPACAO_LEVEL01_AUTOMIND automind
group by
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    automind.NR_CERTIFICADO,
	automind.NR_MATRICULA,
	automind.NM_TITULAR,
	automind.VL_FATOR_MODERADOR,
	automind.NR_MATRICULA_ESPECIAL,
	automind.DESCR_PROFISSAO,
	automind.CD_EMPRESA
order by
	automind.NM_TITULAR;
	
create view VW_COPARTICIPACAO_RESUMO_AUTOMIND as
select
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO,
    automind.CD_CONTRATO,
    count( 1 ) NUM_SEGURADOS,
    '100%' NM_PROPORCAO,
	sum( automind.VL_FATOR_MODERADOR ) VL_ALOCADO
from	VW_COPARTICIPACAO_LEVEL01_AUTOMIND automind
group by
	automind.CD_MES,
    automind.CD_ANO,
    automind.ID_CONTRATO;
	
