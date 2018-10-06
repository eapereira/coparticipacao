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
	titular.ID_EMPRESA,
	titular.NM_TITULAR,
	
	titular.VERSION,
	titular.USER_CREATED,
	titular.USER_ALTERED,
	titular.DT_CREATED,
	titular.DT_ALTERED
from TB_TITULAR titular;

create view VW_DEPENDENTE_RESUMO as
select
	dependente.ID,
	dependente.NR_MATRICULA,
	dependente.NR_CPF,
	titular.ID_EMPRESA,
	dependente.NM_DEPENDENTE,

	dependente.VERSION,
	dependente.USER_CREATED,
	dependente.USER_ALTERED,
	dependente.DT_CREATED,
	dependente.DT_ALTERED	
from TB_DEPENDENTE dependente
	join TB_TITULAR titular on
	titular.ID = dependente.ID_TITULAR;

/**********************************************************************************************************************************************/
/**********************************************************************************************************************************************/

drop table if exists TB_EXECUCAO;

create table TB_EXECUCAO(
	ID					bigint( 17 ) auto_increment,
	ID_EMPRESA			bigint( 17 ) not null,
	TP_STATUS			int( 3 ) not null,
	
	VERSION				bigint( 17 ) null,	
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EXECUCAO primary key( ID ),
	
	constraint FK_EXECUCAO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EXECUCAO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EXECUCAO_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
);

alter table TB_ARQUIVO_EXECUCAO
add(
	ID_EXECUCAO bigint( 17 ) not null,
	constraint FK_ARQUIVO_EXECUCAO_04 foreign key( ID_EXECUCAO ) references TB_EXECUCAO( ID ));

	
/**********************************************************************************************************************************************/
	