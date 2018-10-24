/**
 * Edson - 25/09/2018
 */

drop view if exists VW_TITULAR_RESUMO;
drop view if exists VW_DEPENDENTE_RESUMO;

create view VW_TITULAR_RESUMO as
select
	titular.ID,
	titular.NR_MATRICULA,
	titular.NR_CPF,
	contrato.ID_EMPRESA,
	titular.NM_TITULAR,
	
	titular.VERSION,
	titular.USER_CREATED,
	titular.USER_ALTERED,
	titular.DT_CREATED,
	titular.DT_ALTERED
from TB_TITULAR titular
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO;

create view VW_DEPENDENTE_RESUMO as
select
	dependente.ID,
	dependente.NR_MATRICULA,
	dependente.NR_CPF,
	contrato.ID_EMPRESA,
	dependente.NM_DEPENDENTE,

	dependente.VERSION,
	dependente.USER_CREATED,
	dependente.USER_ALTERED,
	dependente.DT_CREATED,
	dependente.DT_ALTERED	
from TB_DEPENDENTE dependente
	join TB_TITULAR titular on
		titular.ID = dependente.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO;


/**********************************************************************************************************************************************/
/**********************************************************************************************************************************************/