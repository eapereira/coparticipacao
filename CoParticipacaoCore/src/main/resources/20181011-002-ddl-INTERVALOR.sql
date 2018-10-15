/**
 * edson - 11/10/2018
 */

drop view if exists VW_DESCONHECIDO_INTERVALOR;
drop view if exists VW_COPARTICIPACAO_LEVEL01_INTERVALOR;
drop view if exists VW_COPARTICIPACAO_INTERVALOR;

/****************************************************************************************************************************************************/
/****************************************************************************************************************************************************/

create view VW_DESCONHECIDO_INTERVALOR as
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
where empresa.CD_EMPRESA = 'INTERVALOR';

create view VW_COPARTICIPACAO_LEVEL01_INTERVALOR as
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
	titular.NR_MATRICULA,
	8888 CD_EMPRESA,
	8 CD_PREFIXO_EMPRESA,
	titular.NM_TITULAR,
	dependente.NM_DEPENDENTE NM_BENEFICIARIO,
	dependente.DT_NASCIMENTO,
	lpad( dependente.NR_CPF, 11, '0' ) NR_CPF_BENEFICIARIO,
	contrato.CD_CONTRATO CD_UNIDADE,
	lancamento.VL_PRINCIPAL
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
	join TB_DEPENDENTE dependente on
		dependente.ID = lancamento.ID_DEPENDENTE	
where	lancamento.ID_DEPENDENTE is not null
and		empresa.CD_EMPRESA	= 'INTERVALOR'
union all
select
	lancamento.CD_MES,
    lancamento.CD_ANO,
    lancamento.ID_CONTRATO,
	titular.NR_MATRICULA,
	8888 CD_EMPRESA,
	8 CD_PREFIXO_EMPRESA,
	titular.NM_TITULAR,
	titular.NM_TITULAR NM_BENEFICIARIO,
	titular.DT_NASCIMENTO,
	lpad( titular.NR_CPF, 11, '0' ) NR_CPF_BENEFICIARIO,
	contrato.CD_CONTRATO CD_UNIDADE,
	lancamento.VL_PRINCIPAL
from	TB_LANCAMENTO lancamento
	join TB_CONTRATO contrato on
		contrato.ID = lancamento.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA
	join TB_TITULAR titular on
		titular.ID = lancamento.ID_TITULAR
where	lancamento.ID_DEPENDENTE is null
and		empresa.CD_EMPRESA	= 'INTERVALOR';

create view VW_COPARTICIPACAO_INTERVALOR as
select
	intervalor.CD_MES,
    intervalor.CD_ANO,
    intervalor.ID_CONTRATO,
	intervalor.NR_MATRICULA,
	intervalor.CD_EMPRESA,
	intervalor.CD_PREFIXO_EMPRESA,
	intervalor.NM_TITULAR,
	intervalor.NM_BENEFICIARIO,
	intervalor.DT_NASCIMENTO,
	intervalor.NR_CPF_BENEFICIARIO,
	intervalor.CD_UNIDADE,
	sum( intervalor.VL_PRINCIPAL ) VL_PRINCIPAL
from	VW_COPARTICIPACAO_LEVEL01_INTERVALOR intervalor
where intervalor.VL_PRINCIPAL > 0
group by
	intervalor.CD_MES,
    intervalor.CD_ANO,
    intervalor.ID_CONTRATO,
	intervalor.NR_MATRICULA,
	intervalor.CD_EMPRESA,
	intervalor.CD_PREFIXO_EMPRESA,
	intervalor.NM_TITULAR,
	intervalor.NM_BENEFICIARIO,
	intervalor.DT_NASCIMENTO,
	intervalor.NR_CPF_BENEFICIARIO,
	intervalor.CD_UNIDADE
order by
	intervalor.NM_TITULAR,
	intervalor.NM_BENEFICIARIO;
