/*****************************************************************************************************************************************************/
/*****************************************************************************************************************************************************/

#Se precisar execute essa linha como o ROOT:
#set persist log_bin_trust_function_creators = 1;

drop procedure if exists PROC_LOG_MESSAGE;
drop procedure if exists PROC_SHOW_LOG_MESSAGE;
drop procedure if exists PROC_VALIDATE_SCRIPT;
drop procedure if exists PROC_UPDATE_SCRIPT;
drop procedure if exists PROC_CLEAR_COPARTICIPACAO;
drop procedure if exists PROC_UPDATE_OUTPUT_SHEET_ORDEM;

drop function if exists FUNC_DOUBLE_TO_LONG;
drop function if exists FUNC_GET_MATRICULA_HOC;
drop function if exists FUNC_GET_CPF;
drop function if exists FUNC_GET_ROWNUM;
drop function if exists FUNC_GET_CARTAO_IDENTIFICACAO_CELPE;
drop function if exists FUNC_CREATE_DATA_COMPETENCIA;
drop function if exists FUNC_FIND_ARQUIVO_OUTPUT;
drop function if exists FUNC_FIND_ARQUIVO_INPUT;

delimiter $$

create procedure PROC_LOG_MESSAGE(PARAM_MESSAGE varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	if PARAM_MESSAGE is null then
		set PARAM_MESSAGE = '';
	end if;
	
	insert into TB_LOG(
		MSG,
		DT_CREATED
	) values (
		PARAM_MESSAGE,
		current_timestamp()
	);
END
$$

delimiter $$

create procedure PROC_SHOW_LOG_MESSAGE( )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	select MSG from TB_LOG
	order by ID desc;
	
	delete from TB_LOG
	where ID >= 1;
END
$$

delimiter $$

create procedure PROC_VALIDATE_SCRIPT( 
	PARAM_NM_SCRIPT varchar( 400 ), 
	PARAM_NM_CURRENT_SCRIPT varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	declare VAR_LAST_SCRIPT varchar( 400 );
    declare VAR_ERROR_MSG varchar( 400 ) default null;
    declare ERROR_SCRIPT CONDITION FOR SQLSTATE '45000';
	
	select script.NM_LAST_SCRIPT into VAR_LAST_SCRIPT from TB_SCRIPT script
	where script.ID = (
		select max( script_last.ID ) from TB_SCRIPT script_last );

	if VAR_LAST_SCRIPT = PARAM_NM_CURRENT_SCRIPT then
		set VAR_ERROR_MSG = concat( 'O script [ ', PARAM_NM_CURRENT_SCRIPT, ' ] já foi executado.' );		
	elseif VAR_LAST_SCRIPT <> PARAM_NM_SCRIPT then
		set VAR_ERROR_MSG = concat( 'É necessário executar o script [ ', PARAM_NM_SCRIPT, ' ] antes.' );		
	end if;

	if VAR_ERROR_MSG is not null then
		call PROC_LOG_MESSAGE( VAR_ERROR_MSG );
    
		signal ERROR_SCRIPT
			set MESSAGE_TEXT = VAR_ERROR_MSG; 	
	end if;
	
END
$$

delimiter $$

create procedure PROC_UPDATE_SCRIPT( PARAM_NM_SCRIPT varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	
	insert into TB_SCRIPT(
		NM_LAST_SCRIPT,
		DT_CREATED
	) values (
		PARAM_NM_SCRIPT,
		current_timestamp()
	);
	
END
$$

delimiter $$

create procedure PROC_CLEAR_COPARTICIPACAO( PARAM_ID_EMPRESA bigint( 17 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para apagar todos os registros de uma Empresa no Coparticipação:'
BEGIN
	START TRANSACTION;
	
	IF param_id_empresa IS NULL then		
		delete from TB_LANCAMENTO
		WHERE ID > 0;
		
		delete from TB_DEPENDENTE_ISENTO
		WHERE ID > 0;
				
		delete from TB_DEPENDENTE
		WHERE ID > 0;
				
		delete from TB_TITULAR_ISENTO
		WHERE ID > 0;
		
		delete from TB_TITULAR
		where ID > 0;
		
		delete from TB_DESCONHECIDO
		WHERE ID > 0;
	else		
		delete lancamento from TB_LANCAMENTO lancamento
		WHERE lancamento.ID_CONTRATO in (
			select contrato.ID
			from TB_CONTRATO contrato
			where	contrato.ID_EMPRESA = PARAM_ID_EMPRESA
			and		contrato.ID = lancamento.ID_CONTRATO );
		
		delete isento from TB_DEPENDENTE_ISENTO isento
		WHERE isento.ID_DEPENDENTE in (
			select	dependente.ID
			from 	TB_DEPENDENTE dependente
			where	dependente.ID =  isento.ID_DEPENDENTE
			and		dependente.ID_TITULAR in (
				select	titular.ID
				from 	TB_TITULAR titular
				where 	titular.ID = dependente.ID_TITULAR
				and		titular.ID_EMPRESA = PARAM_ID_EMPRESA
			)
		);
		
		delete isento from TB_TITULAR_ISENTO isento
		where isento.ID_TITULAR in (
			select	titular.ID
			from 	TB_TITULAR titular
			where 	titular.ID 			= isento.ID_TITULAR
			and		titular.ID_EMPRESA 	= PARAM_ID_EMPRESA			
		);
				
		delete dependente from TB_DEPENDENTE dependente
		where dependente.ID_TITULAR in (
			select	titular.ID
			from 	TB_TITULAR titular 
				join TB_CONTRATO contrato on
					contrato.ID = titular.ID_CONTRATO
			where 	titular.ID 			= dependente.ID_TITULAR
			and		contrato.ID_EMPRESA = PARAM_ID_EMPRESA					
		);
				
		delete 	titular 
		from	TB_TITULAR titular
		where 	titular.ID_CONTRATO in (
			select	contrato.ID
			from 	TB_CONTRATO contrato
			where	contrato.ID 		= titular.ID_CONTRATO
			and		contrato.ID_EMPRESA = PARAM_ID_EMPRESA					
		);		
				
		delete 	desconhecido 
		from 	TB_DESCONHECIDO desconhecido
		where 	desconhecido.ID > 0;	
	end if;
	
	commit;
END
$$

delimiter $$

create function FUNC_DOUBLE_TO_LONG( PARAM_VALUE numeric( 17, 2 ))
returns bigint( 17 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para apagar todos os registros de uma Empresa no Coparticipação:'
BEGIN
	declare VAR_RESULT bigint( 17 ) default 0;
	
	if PARAM_VALUE  is not null then
		set VAR_RESULT = convert( replace( cast( PARAM_VALUE as char( 20 )), '.', '' ), signed integer );
	end if;
	
	return VAR_RESULT;
END
$$

delimiter $$

create function FUNC_GET_MATRICULA_HOC( PARAM_NR_MATRICULA bigint( 17 ), PARAM_CD_RDP int( 3 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 				varchar( 20 ) default '';
	declare VAR_NR_MATRICULA_BASE	bigint( 17 ) default 44400000000;
    declare VAR_NR_MATRICULA_HOC 	bigint( 17 ) default 0;
	
	if PARAM_NR_MATRICULA  is not null then
		set VAR_NR_MATRICULA_HOC = (( VAR_NR_MATRICULA_BASE + PARAM_NR_MATRICULA ) * 1000 );
		
		if PARAM_CD_RDP is not null then
			set VAR_NR_MATRICULA_HOC = VAR_NR_MATRICULA_HOC + PARAM_CD_RDP; 
		else
			set VAR_NR_MATRICULA_HOC = VAR_NR_MATRICULA_HOC + 1;
		end if;
		
		set VAR_RESULT = lpad(cast( VAR_NR_MATRICULA_HOC as char ), 15, '0' );
		
		#Deve-se somar ao campo RDP do MECSAS:
	end if;
	
	return VAR_RESULT;
END
$$

delimiter $$

create function FUNC_GET_CPF( PARAM_NR_CPF bigint( 17 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 	varchar( 20 ) default '';
	
	if PARAM_NR_CPF is not null then
		set VAR_RESULT = lpad(cast( PARAM_NR_CPF as char ), 11, '0' );
		
		#Deve-se somar ao campo RDP do MECSAS:
	end if;
	
	return VAR_RESULT;
END
$$

delimiter $$

create function FUNC_GET_ROWNUM( )
returns int( 10 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT int( 10 ) default 0;
	
	set @VAR_ROWNUM = ifnull( @VAR_ROWNUM, 0 ) + 1;
		
	return @VAR_ROWNUM;
END
$$

delimiter $$

create function FUNC_GET_CARTAO_IDENTIFICACAO_CELPE( 
	PARAM_CD_CONTRATO varchar( 60 ), 
	PARAM_NR_CERTIFICADO bigint( 17 ), 
	PARAM_CD_USUARIO varchar( 20 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 				varchar( 20 ) default '';
	declare VAR_NR_MATRICULA_BASE	bigint( 17 ) default 700000000000000;
    declare VAR_NR_MATRICULA_CELPE 	bigint( 17 ) default 0;
	
	if PARAM_NR_CERTIFICADO  is not null then
		set VAR_NR_MATRICULA_CELPE = VAR_NR_MATRICULA_BASE + (( convert( PARAM_CD_CONTRATO, unsigned integer ) * 1000000 ) + PARAM_NR_CERTIFICADO ) * 1000; 
		
		if PARAM_CD_USUARIO is not null then
			set VAR_NR_MATRICULA_CELPE = VAR_NR_MATRICULA_CELPE + convert( PARAM_CD_USUARIO, unsigned integer ); 
		end if;
		
		set VAR_RESULT = lpad(cast( VAR_NR_MATRICULA_CELPE as char ), 15, '0' );
		
		#Deve-se somar ao campo RDP do MECSAS:
	end if;
	
	return VAR_RESULT;
END
$$

create function FUNC_CREATE_DATA_COMPETENCIA(
	PARAM_CD_MES int( 2 ),
	PARAM_CD_ANO int( 4 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 				varchar( 20 ) default '';
	declare VAR_DATE 				varchar( 20 ) default '';

	set VAR_DATE	= concat( '01/',PARAM_CD_MES, '/', PARAM_CD_ANO );	
	set VAR_RESULT 	= concat( MONTHNAME(STR_TO_DATE( VAR_DATE, '%d/%m/%Y')), ' / ', PARAM_CD_ANO ); 	
	
	return VAR_RESULT;
END	
$$

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

#call PROC_CLEAR_COPARTICIPACAO( 1010 ); 
/*****************************************************************************************************************************************************/
/*****************************************************************************************************************************************************/
