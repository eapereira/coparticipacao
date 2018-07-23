
/**
 * Edson - 18/07/2018
 * Script para criar as tabelas usadas peloprocesso OSWALDO CRUZ:
 */
drop view if exists VW_LANCAMENTO_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_LOCAL_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC;
drop view if exists VW_LANCAMENTO_DETAIL_TITULAR_HOC;
drop view if exists VW_LANCAMENTO_ORIGINAL_HOC;
drop view if exists VW_ISENCAO_GESTANTES_HOC;
drop view if exists VW_ISENCAO_CONSELHEIROS_HOC;
drop view if exists VW_DEPENDENTE_LOCAL_HOC;
drop view if exists VW_AFASTADOS_HOC;
drop view if exists VW_AGREGADOS_HOC;
drop view if exists VW_PLANO_EXTENSAO_HOC;
drop view if exists VW_PRN_HOC;
drop view if exists VW_DESCONHECIDO_HOC;
drop view if exists VW_DESLIGADOS_HOC;
drop view if exists VW_RESUMO_01_HOC;
drop view if exists VW_RESUMO_HOC;

create view VW_DESCONHECIDO_HOC as
select 
	desconhecido.ID ID_DESCONHECIDO,
	desconhecido.CD_MES,
	desconhecido.CD_ANO,
    detail.VL_LONG NR_MATRICULA
from TB_DESCONHECIDO desconhecido
join TB_DESCONHECIDO_DETAIL detail on
	detail.ID_DESCONHECIDO = desconhecido.ID
where detail.ID_ARQUIVO_INPUT_COLS_DEF = 150;

create view VW_LANCAMENTO_HOC as
select 
	titular.ID ID_TITULAR,
	titular.NR_MATRICULA COD_TITULAR,
	empresa.ID ID_EMPRESA,
    lancamento.ID ID_LANCAMENTO,
    dependente.ID ID_DEPENDENTE,
    dependente.NM_DEPENDENTE,
    dependente.NR_MATRICULA COD_DEPENDENTE,
    lancamento.CD_MES,
    lancamento.CD_ANO,
	dependente.DT_NASCIMENTO,	
	dependente.NR_CPF CPF_DEPENDENTE,
	dependente.NR_MATRICULA NR_MATRICULA_DEPENDENTE
from TB_TITULAR titular
join TB_LANCAMENTO lancamento on 
	lancamento.ID_TITULAR = titular.id
join TB_CONTRATO contrato on
	contrato.ID = lancamento.ID_CONTRATO
join TB_EMPRESA empresa on
	empresa.ID = contrato.ID_EMPRESA
join TB_DEPENDENTE dependente on
	dependente.ID_TITULAR = titular.ID;
	
create view VW_LANCAMENTO_DETAIL_LOCAL_HOC as
select
	detail.ID_LANCAMENTO,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	detail.VL_INT LOCAL
from TB_LANCAMENTO_DETAIL detail
join VW_LANCAMENTO_HOC lanc_hoc on
	detail.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
where detail.ID_ARQUIVO_INPUT_COLS_DEF = 155;
	
create view VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC as
select
	detail.ID_LANCAMENTO,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	detail.VL_DOUBLE VL_PRINCIPAL
from TB_LANCAMENTO_DETAIL detail
join VW_LANCAMENTO_HOC lanc_hoc on
	detail.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
where detail.ID_ARQUIVO_INPUT_COLS_DEF = 153;

create view VW_DEPENDENTE_LOCAL_HOC as
select
	lanc_hoc.ID_LANCAMENTO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.COD_TITULAR,
	lanc_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_DEPENDENTE NM_USUARIO,
	principal_hoc.VL_PRINCIPAL,
	local_hoc.LOCAL,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	principal_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_LOCAL_HOC local_hoc on
	local_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO;

/**********************************************************************************************************************/
	
create view VW_ISENCAO_GESTANTES_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA,
	lanc_hoc.NM_DEPENDENTE NM_USUARIO,
	sum( principal_hoc.VL_PRINCIPAL ) TOTAL_COPART
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	lanc_hoc.ID_LANCAMENTO = principal_hoc.ID_LANCAMENTO
where lanc_hoc.ID_TITULAR in (
	select
		titular_isento.ID_TITULAR
	from TB_TITULAR_ISENTO titular_isento
	where titular_isento.ID_TITULAR = lanc_hoc.ID_TITULAR 
    and titular_isento.TP_ISENTO = 1 )
or lanc_hoc.ID_DEPENDENTE in (
	select
		dependente_isento.ID_DEPENDENTE
	from TB_DEPENDENTE_ISENTO dependente_isento
	where dependente_isento.ID_DEPENDENTE = lanc_hoc.ID_DEPENDENTE
    and dependente_isento.TP_ISENTO = 1 )
and principal_hoc.VL_PRINCIPAL >= 0
group by 	lanc_hoc.CD_MES,
			lanc_hoc.CD_ANO,
			lanc_hoc.ID_EMPRESA,
			lanc_hoc.NR_MATRICULA_DEPENDENTE,
			lanc_hoc.NM_DEPENDENTE
order by lanc_hoc.NM_DEPENDENTE;

create view VW_ISENCAO_CONSELHEIROS_HOC as
select
	lanc_hoc.ID_LANCAMENTO,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.ID_EMPRESA,
	lanc_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_DEPENDENTE NM_USUARIO,
	sum( principal_hoc.VL_PRINCIPAL ) TOTAL_COPART,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	principal_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
where principal_hoc.VL_PRINCIPAL >= 0
and lanc_hoc.COD_DEPENDENTE < 1000
group by 	lanc_hoc.ID_LANCAMENTO,
			lanc_hoc.CD_MES,			
			lanc_hoc.CD_ANO,
			lanc_hoc.ID_EMPRESA,
			lanc_hoc.COD_DEPENDENTE,
			lanc_hoc.NM_DEPENDENTE,
			lanc_hoc.DT_NASCIMENTO,
			lanc_hoc.CPF_DEPENDENTE,
			lanc_hoc.NR_MATRICULA_DEPENDENTE
order by lanc_hoc.NM_DEPENDENTE;

create view VW_AFASTADOS_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	count(dependente_local.VL_PRINCIPAL) TOTAL_COPART,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL_HOC dependente_local
where dependente_local.LOCAL = 100
and dependente_local.VL_PRINCIPAL >= 0
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NM_USUARIO;

create view VW_AGREGADOS_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	count(dependente_local.VL_PRINCIPAL) TOTAL_COPART,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL_HOC dependente_local
where dependente_local.LOCAL = 101
and dependente_local.VL_PRINCIPAL >= 0
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NM_USUARIO;

create view VW_PLANO_EXTENSAO_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	count(dependente_local.VL_PRINCIPAL) TOTAL_COPART,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL_HOC dependente_local
where dependente_local.LOCAL = 102
and dependente_local.VL_PRINCIPAL >= 0
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NM_USUARIO;

create view VW_DESLIGADOS_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	count(dependente_local.VL_PRINCIPAL) TOTAL_COPART,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL_HOC dependente_local
where dependente_local.VL_PRINCIPAL >= 0
and dependente_local.COD_TITULAR not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_HOC desconhecido
    where desconhecido.NR_MATRICULA = dependente_local.COD_TITULAR
    and desconhecido.CD_MES 		= dependente_local.CD_MES
    and desconhecido.CD_ANO 		= dependente_local.CD_ANO )
and dependente_local.COD_DEPENDENTE not in (
	select 
		desconhecido.NR_MATRICULA
	from VW_DESCONHECIDO_HOC desconhecido
    where desconhecido.NR_MATRICULA = dependente_local.COD_DEPENDENTE
    and desconhecido.CD_MES 		= dependente_local.CD_MES
    and desconhecido.CD_ANO 		= dependente_local.CD_ANO )
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.ID_EMPRESA,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NM_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NM_USUARIO;

create view VW_LANCAMENTO_ORIGINAL_HOC as
select
	lanc_hoc.COD_TITULAR,
    lanc_hoc.CD_MES,
    lanc_hoc.CD_ANO,
    lanc_hoc.ID_EMPRESA,
	lanc_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_DEPENDENTE,
	sum( principal_hoc.VL_PRINCIPAL ) TOTAL_COPART,
	local_hoc.LOCAL,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_LOCAL_HOC local_hoc on
	lanc_hoc.ID_LANCAMENTO = local_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	lanc_hoc.ID_LANCAMENTO = principal_hoc.ID_LANCAMENTO
where principal_hoc.VL_PRINCIPAL >= 0 
and	lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
			isencao_gestantes.NR_MATRICULA
	from VW_ISENCAO_GESTANTES_HOC isencao_gestantes
	where isencao_gestantes.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
and lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
		conselheiros.NR_MATRICULA
	from VW_ISENCAO_CONSELHEIROS_HOC conselheiros
	where conselheiros.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
and lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
		afastados.NR_MATRICULA
	from VW_AFASTADOS_HOC afastados
	where afastados.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
and lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
		agregados.NR_MATRICULA
	from VW_AGREGADOS_HOC agregados
	where agregados.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
and lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
		desligados.NR_MATRICULA
	from VW_DESLIGADOS_HOC desligados
	where desligados.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
and lanc_hoc.NR_MATRICULA_DEPENDENTE not in (
	select
		plano_extensao.NR_MATRICULA
	from VW_PLANO_EXTENSAO_HOC plano_extensao
	where plano_extensao.NR_MATRICULA = lanc_hoc.NR_MATRICULA_DEPENDENTE )
group by	lanc_hoc.COD_TITULAR,
		    lanc_hoc.CD_MES,
		    lanc_hoc.CD_ANO,
		    lanc_hoc.ID_EMPRESA,
			lanc_hoc.COD_DEPENDENTE,
			lanc_hoc.NM_DEPENDENTE,
			local_hoc.LOCAL,
			lanc_hoc.DT_NASCIMENTO,
			lanc_hoc.CPF_DEPENDENTE,
			lanc_hoc.NR_MATRICULA_DEPENDENTE
order by lanc_hoc.NM_DEPENDENTE;

create view VW_PRN_HOC as
select
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	lpad( original_hoc.NR_MATRICULA, 14, '0' ) NR_MATRICULA,
	replace( lpad( sum( original_hoc.TOTAL_COPART ), 12, '0' ), '.', '' ) TOTAL_COPART
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc
where original_hoc.TOTAL_COPART >= 0
group by
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.NR_MATRICULA;


	
create view VW_RESUMO_01_HOC as
select 
	'Total Coparticipação' NM_LABEL,
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	original_hoc.ID_EMPRESA,
	original_hoc.TOTAL_COPART TOTAL_COPART
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc
union all
select
	'Isenção de Gestantes' NM_LABEL,
	isencao_gestantes.CD_MES,
	isencao_gestantes.CD_ANO,
	isencao_gestantes.ID_EMPRESA,
	isencao_gestantes.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_GESTANTES_HOC isencao_gestantes
union all
select
	'Isenção Conselheiro' NM_LABEL,
	isencao_conselheiros.CD_MES,
	isencao_conselheiros.CD_ANO,
	isencao_conselheiros.ID_EMPRESA,
	isencao_conselheiros.TOTAL_COPART TOTAL_COPART
from VW_ISENCAO_CONSELHEIROS_HOC isencao_conselheiros
union all
select
	'Afastados' NM_LABEL,
	afastados.CD_MES,
	afastados.CD_ANO,
	afastados.ID_EMPRESA,
	afastados.TOTAL_COPART TOTAL_COPART
from VW_AFASTADOS_HOC afastados
union all
select
	'Afastados' NM_LABEL,
	agregados.CD_MES,
	agregados.CD_ANO,
	agregados.ID_EMPRESA,
	agregados.TOTAL_COPART TOTAL_COPART
from VW_AGREGADOS_HOC agregados
union all
select
	'Plano de Extensão' NM_LABEL,
	plano_extensao.CD_MES,
	plano_extensao.CD_ANO,
	plano_extensao.ID_EMPRESA,
	plano_extensao.TOTAL_COPART TOTAL_COPART
from VW_PLANO_EXTENSAO_HOC plano_extensao
union all
select
	'Desligados' NM_LABEL,
	desligados.CD_MES,
	desligados.CD_ANO,
	desligados.ID_EMPRESA,
	desligados.TOTAL_COPART TOTAL_COPART
from VW_DESLIGADOS_HOC desligados;

create view VW_RESUMO_HOC as
select
	resumo.NM_LABEL,
	resumo.CD_MES,
	resumo.CD_ANO,
	sum( resumo.TOTAL_COPART ) TOTAL_COPART
from VW_RESUMO_01_HOC resumo
group by
	resumo.NM_LABEL,
	resumo.CD_MES,
	resumo.CD_ANO,
	resumo.ID_EMPRESA;
/**********************************************************************************************************************/

create index NDX_LANCAMENTO_01 on TB_LANCAMENTO( CD_MES, CD_ANO );
create index NDX_LANCAMENTO_02 on TB_LANCAMENTO( ID_DEPENDENTE );
create index NDX_LANCAMENTO_03 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_DEPENDENTE );
create index NDX_LANCAMENTO_04 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_TITULAR );
create index NDX_LANCAMENTO_05 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_CONTRATO);

create index NDX_DEPENDENTE_01 on TB_DEPENDENTE( ID_TITULAR );
create index NDX_DEPENDENTE_02 on TB_DEPENDENTE( NM_DEPENDENTE );
create index NDX_DEPENDENTE_03 on TB_DEPENDENTE( NR_MATRICULA );
create index NDX_DEPENDENTE_04 on TB_DEPENDENTE( ID_TITULAR, NR_MATRICULA );
create index NDX_DEPENDENTE_05 on TB_DEPENDENTE( NR_CPF );
create index NDX_DEPENDENTE_06 on TB_DEPENDENTE( ID_TITULAR, ID );

create index NDX_DESCONHECIDO_01 on TB_DESCONHECIDO( ID_CONTRATO, CD_MES, CD_ANO );
create index NDX_DESCONHECIDO_02 on TB_DESCONHECIDO( CD_MES, CD_ANO );

create index NDX_CONTRATO_01 ON TB_CONTRATO( CD_CONTRATO );

create index NDX_TITULAR_01 ON TB_CONTRATO( ID_EMPRESA );


