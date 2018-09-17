/**
 * Edson - 13/09/2018
 */

alter table TB_ARQUIVO_OUTPUT_SHEET
	add CD_ORDEM int( 3 ) null;

drop procedure if exists PROC_UPDATE_OUTPUT_SHEET_ORDEM;
drop function if exists FUNC_FIND_ARQUIVO_OUTPUT;

delimiter $$

create function FUNC_FIND_ARQUIVO_OUTPUT( 
	PARAM_CD_EMPRESA varchar( 60 ), 
	PARAM_CD_CONTRATO varchar( 60 ), 
	PARAM_TP_ARQUIVO int( 3 ))
returns bigint( 17 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_ID_ARQUIVO_OUTPUT bigint( 17 ) default 0;
	declare VAR_ID_EMPRESA bigint( 17 );
	declare VAR_ID_CONTRATO bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT bigint( 17 );
	
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
    call PROC_LOG_MESSAGE('LINHA - 27');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = PARAM_CD_EMPRESA;
    
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = PARAM_CD_CONTRATO;

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* FATU-COPA */
	
	call PROC_LOG_MESSAGE('LINHA - 39');
	select	ID into VAR_ID_ARQUIVO_INPUT 
	from 	TB_ARQUIVO_INPUT
	where	ID = VAR_ID_CONTRATO;
				
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* VIEW-DESTINATION */

	call PROC_LOG_MESSAGE('LINHA - 184');
	
	select	ID into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT
	where 	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
	and		TP_ARQUIVO 			= PARAM_TP_ARQUIVO;
	
	return VAR_ID_ARQUIVO_OUTPUT;
END
$$

delimiter $$

create procedure PROC_UPDATE_OUTPUT_SHEET_ORDEM( PARAM_ID_ARQUIVO_OUTPUT bigint( 17 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Script para configurar o Hospital Oswaldo Cruz'
BEGIN
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
									
	declare VAR_ID_ARQUIVO_OUTPUT_SHEET		bigint( 17 );
	declare VAR_CD_ORDEM					int( 3 ) default 0;		
	declare VAR_NOT_FOUND 					int( 3 ) default 0;
	
	declare CURSOR_ARQUIVO_OUTPUT_SHEET cursor for 
		select 	ID  from TB_ARQUIVO_OUTPUT_SHEET 
        where	ID_ARQUIVO_OUTPUT = PARAM_ID_ARQUIVO_OUTPUT 
        order by ID;
    
	declare continue handler for not found set VAR_NOT_FOUND = 1;  
	/***********************************************************************************************************************/
	
	DECLARE exit handler for sqlexception
	BEGIN		
		GET DIAGNOSTICS CONDITION 1
        	VAR_CODE = RETURNED_SQLSTATE, VAR_MSG = MESSAGE_TEXT;
    
		-- ERROR
		call PROC_LOG_MESSAGE( 'Erro ao executar procedure:' );        	
       	call PROC_LOG_MESSAGE( concat( VAR_CODE, ' - ', VAR_MSG ));
       	
       	call PROC_SHOW_LOG_MESSAGE();
		ROLLBACK;
	END;

	START TRANSACTION;	

	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_LOG_MESSAGE('LINHA - 190');
		
	open CURSOR_ARQUIVO_OUTPUT_SHEET;
	
	updateSheet: loop
	
		fetch CURSOR_ARQUIVO_OUTPUT_SHEET into VAR_ID_ARQUIVO_OUTPUT_SHEET;

		if VAR_NOT_FOUND = 1 then
			leave updateSheet;
		end if;
		
		update	TB_ARQUIVO_OUTPUT_SHEET
		set 	CD_ORDEM	= VAR_CD_ORDEM
		where 	ID 			= VAR_ID_ARQUIVO_OUTPUT_SHEET;
		
		set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	end loop updateSheet;
	
	close CURSOR_ARQUIVO_OUTPUT_SHEET;			
		
	call PROC_LOG_MESSAGE('LINHA - 228');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

