/**
 * edson - 08/10/218
 */

drop table if exists TB_TITULAR_DETAIL;
drop table if exists TB_DEPENDENTE_DETAIL;
drop table if exists TB_LANCAMENTO_DETAIL;

drop function if exists FUNC_FIND_ARQUIVO_INPUT;

alter table TB_EMPRESA
	modify column CD_EMPRESA varchar( 40 ) not null;
/*****************************************************************************************************************************************************************/

delimiter $$

create function FUNC_FIND_ARQUIVO_INPUT( 
	PARAM_CD_EMPRESA varchar( 40 ), 
	PARAM_CD_CONTRATO varchar( 40 ))
returns bigint( 17 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para informar o ID_ARQUIVO_INPUT'
BEGIN
	declare VAR_ID_ARQUIVO_INPUT 	bigint( 17 ) default 0;
	declare	VAR_ID_EMPRESA			bigint( 17 ) default 0;
	declare	VAR_ID_CONTRATO			bigint( 17 ) default 0;
	
	select ID into VAR_ID_EMPRESA
	from 	TB_EMPRESA
	where	CD_EMPRESA = PARAM_CD_EMPRESA;
	
	select	ID into VAR_ID_CONTRATO
	from 	TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO	= PARAM_CD_CONTRATO;
	
	select	ID into VAR_ID_ARQUIVO_INPUT
	from 	TB_ARQUIVO_INPUT
	where	ID_CONTRATO = VAR_ID_CONTRATO;
	
	return VAR_ID_ARQUIVO_INPUT;
END
$$
