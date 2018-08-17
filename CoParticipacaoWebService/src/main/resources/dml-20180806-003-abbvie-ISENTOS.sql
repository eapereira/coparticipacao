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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default 'dml-20180805-002-abbvie-8B1LR.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default 'dml-20180806-003-abbvie-ISENTOS.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 3;
	
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
	
	declare VAR_ID_COLUMN_01_NM_BENEFICIARIO 		bigint( 17 );
	declare VAR_ID_COLUMN_02_NR_CPF 				bigint( 17 );
	declare VAR_ID_COLUMN_03_NM_TP_ISENTO 			bigint( 17 );
	
	declare VAR_ID_CONTRATO							bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XY		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XX		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
				
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO	bigint( 17 );
	
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_MATRICULA  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_BENEFICIARIO  	bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_TITULAR  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_CPF  			bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL  		bigint( 17 );
	
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	
	declare VAR_COL_LANCAMENTO_ID_DEPENDENTE							bigint( 17 );
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 );
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 );
		
	declare CD_BENEFICIARIO_COLS_DEF_NM_CONTRATO						bigint( 17 ) default 0;
	
	declare VAR_CD_ISENTO_COLS_DEF_TP_ISENTO							bigint( 17 ) default 1;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA							bigint( 17 ) default 2;
	declare VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO						bigint( 17 ) default 3;
	declare VAR_CD_ISENTO_COLS_DEF_DT_NASCIMENTO						bigint( 17 ) default 4;
	declare VAR_CD_ISENTO_COLS_DEF_NR_CPF								bigint( 17 ) default 5;

	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;

	declare VAR_COLUMN_01_NM_BENEFICIARIO 								bigint( 17 );
	declare VAR_COLUMN_02_NR_CPF 										bigint( 17 );
	declare VAR_COLUMN_03_NM_TP_ISENTO 									bigint( 17 );

	declare VAR_COL_INDEX												int( 5 ) default 0;
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
	
	#call PROC_VALIDATE_SCRIPT( VAR_NM_SCRIPT_REQUIRED, VAR_NM_SCRIPT );	
	/***********************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 115');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'Abbvie';
        
    call PROC_LOG_MESSAGE('LINHA - 123');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where NM_CONTRATO = 'ISENTOS Abbvie';
	
	call PROC_LOG_MESSAGE('LINHA - 360');
	/***********************************************************************************************************************/	
	/***********************************************************************************************************************/
	/* Isentos */
		
	call PROC_LOG_MESSAGE('LINHA - 1341');
	insert into TB_ARQUIVO_INPUT(
		ID_CONTRATO,
		NM_ARQUIVO_REGEXP,
		DESCR_ARQUIVO,	
		TP_ARQUIVO,
		TP_USE,
		NUM_SKIP_LINES,
		NUM_DEFAULT_LINE_LENGTH,
		
		CD_REGEXP_CONTRATO,
		CD_REGEXP_MES,
		CD_REGEXP_ANO,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_CONTRATO,
		'^ISENTOS\\WABBVIE\\WINTERFACE\\.(xlsx|XLSX)$',
		'Arquivo de carga de isentos',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_ISENTO,
		1,
		null, /* Não é usado para arquivo CSV */
		
		null, /* REGEXP_CONTRATO */
		null, /* REGEXP_MES */
		null, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	
	call PROC_LOG_MESSAGE('LINHA - 1379');
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
		'NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_01_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;

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
		'NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_02_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
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
		'NM_TP_ISENTO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_COLUMN_03_NM_TP_ISENTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
		
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	/* BeneficiarioIsento */
	
	call PROC_LOG_MESSAGE('LINHA - 4948');
	/* MECSAS */
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		null,
		0,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_COLUMN_01_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		0,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_COLUMN_02_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,
		1,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_COLUMN_03_NM_TP_ISENTO,
		VAR_CD_ISENTO_COLS_DEF_TP_ISENTO,
		2,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
