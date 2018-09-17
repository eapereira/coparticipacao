/**
 * Edson - 23/07/2018
 * Script para criar processo Marjan
 */
drop procedure if exists PROC_CREATE_HOC;

delimiter $$

create procedure PROC_CREATE_HOC()
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Script para configurar o Hospital Oswaldo Cruz'
BEGIN
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180901-002-dml-intervalor.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180905-002-dml-marjan.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 3;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 4;
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA		bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 					bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	DECLARE VAR_ID_CONTRATO 				bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z8			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z9			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XY			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XX			bigint( 17 );
		
	declare VAR_ID_ARQUIVO_INPUT 					bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
			
	declare VAR_COLUMN_01_CD_CONTRATO				bigint( 17 );
	declare VAR_COLUMN_02_NR_MATRICULA_TITULAR		bigint( 17 );
	declare VAR_COLUMN_03_NM_TITULAR				bigint( 17 );
	declare VAR_COLUMN_04_NR_CPF_TITULAR			bigint( 17 );
	declare VAR_COLUMN_05_DT_NASCIMENTO_TITULAR		bigint( 17 );	
	declare VAR_COLUMN_06_NR_MATRICULA_DEPENDENTE	bigint( 17 );
	declare VAR_COLUMN_07_NM_DEPENDENTE				bigint( 17 );
	declare VAR_COLUMN_08_DT_NASCIMENTO_DEPENDENTE	bigint( 17 );
	declare VAR_COLUMN_09_NR_CPF_DEPENDENTE			bigint( 17 );
	declare VAR_COLUMN_10_CD_PLANO					bigint( 17 );
	declare VAR_COLUMN_11_CD_CENTRO_CUSTO			bigint( 17 );
	declare VAR_COLUMN_12_NM_CENTRO_CUSTO			bigint( 17 );	
	declare VAR_COLUMN_13_DT_DEMISSAO				bigint( 17 );
					
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_CONTRATO					bigint( 17 ) default 0;
	
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA			bigint( 17 ) default 10;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_DEMISSAO					bigint( 17 ) default 11;
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
	
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_MATRICULA  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_BENEFICIARIO  	bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_TITULAR  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_CPF  			bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL  		bigint( 17 );
	
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_TITULAR					bigint( 17 ) default 7;
	
	declare VAR_CD_ORDEM												int( 3 ) default 0;
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_DIRETORIA							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_DEMITIDO							bigint( 17 );

	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;

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
	
	call PROC_VALIDATE_SCRIPT( VAR_NM_SCRIPT_REQUIRED, VAR_NM_SCRIPT );
	/***********************************************************************************************************************/		
	call PROC_LOG_MESSAGE('LINHA - 116');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'Marjan';
        
 	call PROC_LOG_MESSAGE('LINHA - 120');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = 'MECSAS2'; 
	
	/***********************************************************************************************************************/
	/* MECSAS.2 */	
 	call PROC_LOG_MESSAGE('LINHA - 128');
 	
 	select ID into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT
 	where	ID_CONTRATO = VAR_ID_CONTRATO; 
 	
 	select ID into VAR_COLUMN_13_DT_DEMISSAO from TB_ARQUIVO_INPUT_COLS_DEF
 	where	ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT
 	and		NM_COLUMN = 'COLUMN_13_CD_DEMISSAO';

 	/***********************************************************************************************************************/ 	
 	
 	call PROC_LOG_MESSAGE('LINHA - 139');
 	update TB_ARQUIVO_INPUT_COLS_DEF
 	set 
 		NM_COLUMN 	= 'COLUMN_13_DT_DEMISSAO',
 		CD_TYPE		= VAR_COL_DATE
 	where ID = VAR_CD_BENEFICIARIO_COLS_DEF_DT_DEMISSAO;
 	
 	call PROC_LOG_MESSAGE('LINHA - 146'); 	
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_DEMISSAO,
		VAR_COLUMN_13_DT_DEMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 162');
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	
	call PROC_LOG_MESSAGE('LINHA - 172');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENTOS_DIRETORIA_MARJAN',
		'Diretória',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_DIRETORIA from TB_VIEW_DESTINATION;
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'NR_MATRICULA_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_ID_TITULAR,
		1,
		'ID TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'NM_TITULAR',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NOME TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'NR_MATRICULA_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_ID_DEPENDENTE,
		3,
		'ID DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
		
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		4,
		'NOME DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	call PROC_LOG_MESSAGE('LINHA - 301');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_DEMITIDO_MARJAN',
		'Demitidos',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_DEMITIDO from TB_VIEW_DESTINATION;
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'NR_MATRICULA_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_ID_TITULAR,
		1,
		'ID TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'NM_TITULAR',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NOME TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'NR_MATRICULA_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_ID_DEPENDENTE,
		3,
		'ID DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
		
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		4,
		'NOME DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	
	call PROC_LOG_MESSAGE('LINHA - 446');
	select ID into VAR_ID_VIEW_DESTINATION_DEMITIDO from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DEMITIDO_MARJAN';

    call PROC_LOG_MESSAGE('LINHA - 450');
	select ID into VAR_ID_VIEW_DESTINATION_DIRETORIA from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_ISENTOS_DIRETORIA_MARJAN';
	
    call PROC_LOG_MESSAGE('LINHA - 454');
    select ID into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT
    where ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT;
    
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/    

    call PROC_LOG_MESSAGE('LINHA - 461');
    select arquivo_input.ID into VAR_ID_ARQUIVO_INPUT 
    from TB_ARQUIVO_INPUT arquivo_input
    	join TB_CONTRATO contrato on
    	contrato.ID = arquivo_input.ID_CONTRATO
    where contrato.CD_CONTRATO = '8C5Z8';     	
        
    call PROC_LOG_MESSAGE('LINHA - 474');
    select ID into VAR_ID_ARQUIVO_OUTPUT
	from TB_ARQUIVO_OUTPUT
    where ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT;
    
    call PROC_LOG_MESSAGE('LINHA - 479');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'Diretória %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 479');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'Demitidos %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
    
	call PROC_LOG_MESSAGE('LINHA - 301');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/

    call PROC_LOG_MESSAGE('LINHA - 514');
    select arquivo_input.ID into VAR_ID_ARQUIVO_INPUT 
    from TB_ARQUIVO_INPUT arquivo_input
    	join TB_CONTRATO contrato on
    	contrato.ID = arquivo_input.ID_CONTRATO
    where contrato.CD_CONTRATO = '8C5Z9';     	

    call PROC_LOG_MESSAGE('LINHA - 526');
    select ID into VAR_ID_ARQUIVO_OUTPUT
	from TB_ARQUIVO_OUTPUT
    where ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT;
    
    call PROC_LOG_MESSAGE('LINHA - 531');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'Diretória %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 479');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'Demitidos %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
    
	call PROC_LOG_MESSAGE('LINHA - 301');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/

    call PROC_LOG_MESSAGE('LINHA - 571');
    select arquivo_input.ID into VAR_ID_ARQUIVO_INPUT 
    from TB_ARQUIVO_INPUT arquivo_input
    	join TB_CONTRATO contrato on
    	contrato.ID = arquivo_input.ID_CONTRATO
    where contrato.CD_CONTRATO = '8C7XX';     	
        
    call PROC_LOG_MESSAGE('LINHA - 578');
    select ID into VAR_ID_ARQUIVO_OUTPUT
	from TB_ARQUIVO_OUTPUT
    where ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT;
    
    call PROC_LOG_MESSAGE('LINHA - 583');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'Diretória %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 479');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'Demitidos %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
    
	call PROC_LOG_MESSAGE('LINHA - 301');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/

    call PROC_LOG_MESSAGE('LINHA - 623');
    select arquivo_input.ID into VAR_ID_ARQUIVO_INPUT 
    from TB_ARQUIVO_INPUT arquivo_input
    	join TB_CONTRATO contrato on
    	contrato.ID = arquivo_input.ID_CONTRATO
    where contrato.CD_CONTRATO = '8C7XY';     	
        
    call PROC_LOG_MESSAGE('LINHA - 638');
    select ID into VAR_ID_ARQUIVO_OUTPUT
	from TB_ARQUIVO_OUTPUT
    where ID_ARQUIVO_INPUT = VAR_ID_ARQUIVO_INPUT;
    
    call PROC_LOG_MESSAGE('LINHA - 635');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DIRETORIA,
		'Diretória %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 479');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_DEMITIDO,
		'Demitidos %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
    
	call PROC_LOG_MESSAGE('LINHA - 671');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
