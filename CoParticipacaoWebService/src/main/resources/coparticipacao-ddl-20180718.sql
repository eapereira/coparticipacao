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
drop view if exists VW_DEPENDENTE_LOCAL;
drop view if exists VW_AFASTADOS;
drop view if exists VW_PRN_HOC;

create view VW_LANCAMENTO_HOC as
select 
	titular.ID ID_TITULAR,
	titular.NR_MATRICULA COD_TITULAR,
    lancamento.ID ID_LANCAMENTO,
    dependente.ID ID_DEPENDENTE,
    dependente.NM_DEPENDENTE,
    lancamento.CD_MES,
    lancamento.CD_ANO,
	dependente.DT_NASCIMENTO,	
	dependente.NR_CPF CPF_DEPENDENTE,
	dependente.NR_MATRICULA NR_MATRICULA_DEPENDENTE
from TB_TITULAR titular
join tb_lancamento lancamento on 
	lancamento.ID_TITULAR = titular.id
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
	
create view VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC as
select
	lancamento.ID ID_LANCAMENTO,
    lancamento.CD_MES,
    lancamento.CD_ANO,
	detail.VL_LONG COD_DEPENDENTE
from tb_lancamento_detail detail
join tb_lancamento lancamento on
	detail.ID_LANCAMENTO = lancamento.ID
where detail.ID_ARQUIVO_INPUT_COLS_DEF = 151;

create view VW_LANCAMENTO_DETAIL_TITULAR_HOC as
select
	lancamento.ID ID_LANCAMENTO,
    lancamento.CD_MES,
    lancamento.CD_ANO,
	detail.VL_LONG COD_TITULAR
from tb_lancamento_detail detail
join tb_lancamento lancamento on
	detail.ID_LANCAMENTO = lancamento.ID
where detail.ID_ARQUIVO_INPUT_COLS_DEF = 150;


create view VW_DEPENDENTE_LOCAL as
select
	lanc_hoc.ID_LANCAMENTO,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	titular_hoc.COD_TITULAR,
	dependente_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_DEPENDENTE NOME_USUARIO,
	principal_hoc.VL_PRINCIPAL,
	local_hoc.LOCAL,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC dependente_hoc on
	dependente_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_TITULAR_HOC titular_hoc on
	titular_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	principal_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_LOCAL_HOC local_hoc on
	local_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO;

/**********************************************************************************************************************/

create view VW_LANCAMENTO_ORIGINAL_HOC as
select
	titular_hoc.COD_TITULAR,
    lanc_hoc.CD_MES,
    lanc_hoc.CD_ANO,
	dependente_hoc.COD_DEPENDENTE,
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
join VW_LANCAMENTO_DETAIL_TITULAR_HOC titular_hoc on
	lanc_hoc.ID_LANCAMENTO= lanc_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC dependente_hoc on
	lanc_hoc.ID_LANCAMENTO = lanc_hoc.ID_LANCAMENTO
group by	titular_hoc.COD_TITULAR,
		    lanc_hoc.CD_MES,
		    lanc_hoc.CD_ANO,
			dependente_hoc.COD_DEPENDENTE,
			lanc_hoc.NM_DEPENDENTE,
			local_hoc.LOCAL,
			lanc_hoc.DT_NASCIMENTO,
			lanc_hoc.CPF_DEPENDENTE,
			lanc_hoc.NR_MATRICULA_DEPENDENTE
order by lanc_hoc.NM_DEPENDENTE;
	
create view VW_ISENCAO_GESTANTES_HOC as
select
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	lanc_hoc.NR_MATRICULA_DEPENDENTE NR_MATRICULA,
	lanc_hoc.NM_DEPENDENTE,
	sum( principal_hoc.VL_PRINCIPAL ) VALOR_ISENTO
from VW_LANCAMENTO_HOC lanc_hoc
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	lanc_hoc.ID_LANCAMENTO = principal_hoc.ID_LANCAMENTO
group by 	lanc_hoc.CD_MES,
			lanc_hoc.CD_ANO,
			lanc_hoc.NR_MATRICULA_DEPENDENTE,
			lanc_hoc.NM_DEPENDENTE
order by lanc_hoc.NM_DEPENDENTE;
	
create view VW_ISENCAO_CONSELHEIROS_HOC as
select
	lanc_hoc.ID_LANCAMENTO,
	lanc_hoc.CD_MES,
	lanc_hoc.CD_ANO,
	dependente_hoc.COD_DEPENDENTE,
	lanc_hoc.NM_DEPENDENTE NM_USUARIO,
	sum( principal_hoc.VL_PRINCIPAL ) TOTAL_COPART,
	lanc_hoc.DT_NASCIMENTO,
	lanc_hoc.CPF_DEPENDENTE,
	lanc_hoc.NR_MATRICULA_DEPENDENTE
from VW_LANCAMENTO_DETAIL_DEPENDENTE_HOC dependente_hoc
join VW_LANCAMENTO_HOC lanc_hoc on
	lanc_hoc.ID_LANCAMENTO = dependente_hoc.ID_LANCAMENTO
join VW_LANCAMENTO_DETAIL_VL_PRINCIPAL_HOC principal_hoc on
	principal_hoc.ID_LANCAMENTO = dependente_hoc.ID_LANCAMENTO
group by 	lanc_hoc.ID_LANCAMENTO,
			lanc_hoc.CD_MES,
			lanc_hoc.CD_ANO,
			dependente_hoc.COD_DEPENDENTE,
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
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	count(dependente_local.VL_PRINCIPAL) VL_PRINCIPAL,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL dependente_local
where dependente_local.LOCAL = 100
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NOME_USUARIO;

create view VW_AGREGADOS_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	count(dependente_local.VL_PRINCIPAL) VL_PRINCIPAL,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL dependente_local
where dependente_local.LOCAL = 101
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NOME_USUARIO;

create view VW_PLANO_EXTENCAO_HOC as
select
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	count(dependente_local.VL_PRINCIPAL) VL_PRINCIPAL,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
from VW_DEPENDENTE_LOCAL dependente_local
where dependente_local.LOCAL = 102
group by
	dependente_local.ID_LANCAMENTO,
	dependente_local.CD_MES,
	dependente_local.CD_ANO,
	dependente_local.COD_TITULAR,
	dependente_local.COD_DEPENDENTE,
	dependente_local.NOME_USUARIO,
	dependente_local.LOCAL,
	dependente_local.DT_NASCIMENTO,
	dependente_local.CPF_DEPENDENTE,
	dependente_local.NR_MATRICULA
order by dependente_local.NOME_USUARIO;

create view VW_PRN_HOC as
select
	original_hoc.CD_MES,
	original_hoc.CD_ANO,
	lpad( original_hoc.NR_MATRICULA, 14, '0' ) NR_MATRICULA,
	replace( lpad( original_hoc.TOTAL_COPART, 12, '0' ), '.', '' ) TOTAL_COPART
from VW_LANCAMENTO_ORIGINAL_HOC original_hoc;

	
/**********************************************************************************************************************/
