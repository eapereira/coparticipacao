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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180806-005-dml-abbvie-MECSAS2.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180808-006-dml-abbvie-NAO-LOCALIZADOS.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
  	declare VAR_CD_ORDEM					int( 3 ) default 0;
  	
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 3;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 4;
	declare VAR_USE_TYPE_NAO_LOCALIZADO		int( 3 ) default 5;
	
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
			
	declare VAR_ID_ARQUIVO_INPUT 					bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
			
	declare VAR_COLUMN_01_NR_MATRICULA				bigint( 17 );
	declare VAR_COLUMN_02_NR_CPF					bigint( 17 );
	declare VAR_COLUMN_03_NM_DEPENDENTE				bigint( 17 );
	declare VAR_COLUMN_04_DT_ADMISSAO				bigint( 17 );
	declare VAR_COLUMN_05_NR_UPI					bigint( 17 );
					
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA			bigint( 17 ) default 10;
	
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
    where CD_EMPRESA = 'ABBVIE';
        
 	call PROC_LOG_MESSAGE('LINHA - 120');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA 	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'NAO-LOCALIZADO';
	
	/***********************************************************************************************************************/
	/* MECSAS */	
 	call PROC_LOG_MESSAGE('LINHA - 117');
	insert into TB_ARQUIVO_INPUT(
		ID_CONTRATO,
		NM_ARQUIVO_REGEXP,
		DESCR_ARQUIVO,	
		TP_ARQUIVO,
		TP_USE,
		NUM_SKIP_LINES,
		NUM_DEFAULT_LINE_LENGTH,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_CONTRATO,
		'^(ABBVIE)\\.(NAO\\-LOCALIZADO)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo para corrigir os beneficiários',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_NAO_LOCALIZADO,
		1,
		null, /* Não é usado para arquivo CSV */
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;	
	
	call PROC_LOG_MESSAGE('LINHA - 153');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_01_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_01_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 177');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_02_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select max( ID ) into VAR_COLUMN_02_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 201');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_03_NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_03_NM_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 249');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_04_DT_ADMISSAO',
		VAR_COL_DATE,
		null,
		'dd/MM/yyyy',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_04_DT_ADMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 273');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_05_NR_UPI',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_05_NR_UPI from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*****************************************************************************************************************************************************/
	/***********************************************************************************************************************/
	/* Beneficiário */	
	call PROC_LOG_MESSAGE('LINHA - 369');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_COLUMN_01_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 332');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_COLUMN_03_NM_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 364');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_COLUMN_02_NR_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 428');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_COLUMN_04_DT_ADMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 444');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA,
		VAR_COLUMN_05_NR_UPI,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 460');
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/

	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
