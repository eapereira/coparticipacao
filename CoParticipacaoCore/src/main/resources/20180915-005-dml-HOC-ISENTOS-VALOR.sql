/**
 * Edson - 23/07/2018
 * Script para criar processo Abbvie
 */
drop procedure if exists PROC_CREATE_HOC;

delimiter $$

create procedure PROC_CREATE_HOC()
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Script para configurar o Hospital Oswaldo Cruz'
BEGIN
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180815-008-dml-HOC-0444-PRN.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180915-005-dml-HOC-ISENTOS-VALOR.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 2;
	declare VAR_USE_TYPE_NAO_LOCALIZADO		int( 3 ) default 3;	
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 4;
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 5;
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA				bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 							bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 							bigint( 17 );
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT						bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_CENTAVOS	bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_UTILIZACAO	bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 				bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS				bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT					bigint( 17 );

	declare VAR_SHEET01_ID_COLUMN_01_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_SHEET01_ID_COLUMN_02_NR_CPF					bigint( 17 );
	declare VAR_SHEET01_ID_COLUMN_03_CD_LOCAL				bigint( 17 );
	declare VAR_SHEET01_ID_COLUMN_04_NR_MATRICULA			bigint( 17 );
	declare VAR_SHEET01_ID_COLUMN_05_VL_ISENCAO				bigint( 17 );
	declare VAR_SHEET01_ID_COLUMN_06_NR_MATRICULA_EMPRESA	bigint( 17 );
	
	declare VAR_SHEET02_ID_COLUMN_01_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_SHEET02_ID_COLUMN_02_NR_CPF					bigint( 17 );
	declare VAR_SHEET02_ID_COLUMN_03_CD_LOCAL				bigint( 17 );
	declare VAR_SHEET02_ID_COLUMN_04_NR_MATRICULA			bigint( 17 );
	declare VAR_SHEET02_ID_COLUMN_05_VL_ISENCAO				bigint( 17 );
	declare VAR_SHEET02_ID_COLUMN_06_NR_MATRICULA_EMPRESA	bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
    
	declare VAR_VIEW_DESTINATION_RESUMO_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_ORIGINAL_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_ISENCAO_GESTANTES_HOC 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_CONSELHEIROS_HOC 						bigint( 17 );
	declare VAR_VIEW_DESTINATION_AFASTADOS_HOC		 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_AGREGADOS_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_PLANO_EXTENSAO_HOC 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_DESLIGADOS_HOC 						bigint( 17 );
	declare VAR_VIEW_DESTINATION_PRN_HOC 								bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;

	declare VAR_COL_LANCAMENTO_ID_DEPENDENTE							bigint( 17 ) default 1;
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 ) default 2;
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 ) default 3;
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 ) default 4;
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 ) default 5;

	declare VAR_ID_LANCAMENTO_INPUT										bigint( 17 );
    
	declare VAR_CD_ISENTO_COLS_DEF_TP_ISENTO							bigint( 17 ) default 1;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA							bigint( 17 ) default 2;
	declare VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO						bigint( 17 ) default 3;
	declare VAR_CD_ISENTO_COLS_DEF_DT_NASCIMENTO						bigint( 17 ) default 4;
	declare VAR_CD_ISENTO_COLS_DEF_NR_CPF								bigint( 17 ) default 5;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR					bigint( 17 ) default 6;
	declare VAR_CD_ISENTO_COLS_DEF_NM_TITULAR							bigint( 17 ) default 7;
	declare VAR_CD_ISENTO_COLS_DEF_VL_ISENCAO							bigint( 17 ) default 8;
	
	declare VAR_TP_REGRA_SIMPLES										int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL									int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD									int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT							int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY								int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_DIVIDE								int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS								int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS							int( 3 )  default 6;
		
	declare VAR_TP_ISENTO_GRAVIDA										int( 3 ) default 1;
	declare VAR_TP_ISENTO_VALOR											int( 3 ) default 7;
	declare VAR_TP_ISENTO_VALOR_CENTAVO									int( 3 ) default 8;
	
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA			bigint( 17 ) default 9;
	
	declare VAR_CD_SHEET_VALOR_CENTAVOS									int( 3 ) default 0;
	declare VAR_CD_SHEET_VALOR_UTILIZACAO								int( 3 ) default 1;
	
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção por valor';
	declare VAR_NR_MATRICULA_BASE										bigint( 17 ) default 44400000000000;
	declare	VAR_ID_REGISTER												bigint( 17 );
	declare	VAR_CD_REGISTER_REG01										bigint( 17 ) default 0;
	
	declare VAR_REGEXP_VALUE_CD_MATRICULA								varchar( 800 ) default '^(0444)([0-9]{8})([0-9]{3})$';
	declare VAR_REGEXP_GROUP_VALUE_CD_MATRICULA							int( 3 ) default 2;
	
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
	/***********************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 147');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = '0444';
    
    call PROC_LOG_MESSAGE('LINHA - 175');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = 'ISENTO-VALOR'; 

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* FATU-COPA */
	
	call PROC_LOG_MESSAGE('LINHA - 184');
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
		'^(0444)\\.(ISENTO\\-VALOR)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de carga de isentos HOC',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_ISENTO,
		1,
		null,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 211');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_VALOR_CENTAVOS,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_CENTAVOS
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;	

	/*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 235');
	insert into TB_REGISTER(
		ID_ARQUIVO_INPUT_SHEET,
		NM_REGISTER,
		CD_REGISTER,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_CENTAVOS,
		'REG_01',		
		VAR_CD_REGISTER_REG01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_REGISTER
	from TB_REGISTER; 
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 198');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_01_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_01_NM_BENEFICIARIO from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 272');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_02_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_02_NR_CPF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 248');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_03_CD_LOCAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_03_CD_LOCAL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 248');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		REGEXP_VALUE,
		REGEXP_GROUP_VALUE,							
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_04_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_REGEXP_VALUE_CD_MATRICULA,
		VAR_REGEXP_GROUP_VALUE_CD_MATRICULA,				
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_04_NR_MATRICULA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 263');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_05_VL_ISENCAO',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_05_VL_ISENCAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 270');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_06_NR_MATRICULA_EMPRESA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_ID_COLUMN_06_NR_MATRICULA_EMPRESA
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 413');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 416');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_VALOR_UTILIZACAO,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_UTILIZACAO
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;	

	call PROC_LOG_MESSAGE('LINHA - 439');
	/*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 441');
	insert into TB_REGISTER(
		ID_ARQUIVO_INPUT_SHEET,
		NM_REGISTER,
		CD_REGISTER,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_UTILIZACAO,
		'REG_02',		
		VAR_CD_REGISTER_REG01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_REGISTER
	from TB_REGISTER; 
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 195');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_01_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_01_NM_BENEFICIARIO from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 220');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_02_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_02_NR_CPF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 245');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_03_CD_LOCAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_03_CD_LOCAL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 270');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		REGEXP_VALUE,
		REGEXP_GROUP_VALUE,							
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_04_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_REGEXP_VALUE_CD_MATRICULA,
		VAR_REGEXP_GROUP_VALUE_CD_MATRICULA,				
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_04_NR_MATRICULA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 263');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_05_VL_ISENCAO',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_05_VL_ISENCAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 270');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_06_NR_MATRICULA_EMPRESA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_ID_COLUMN_06_NR_MATRICULA_EMPRESA
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 288');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* ISENTOS */
	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 295');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_CENTAVOS,
		VAR_TP_ISENTO_VALOR_CENTAVO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET
	from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 318');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_01_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 323');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_02_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 406');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_04_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 379');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_06_NR_MATRICULA_EMPRESA,
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 379');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_04_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 401');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_ID_COLUMN_05_VL_ISENCAO,
		VAR_CD_ISENTO_COLS_DEF_VL_ISENCAO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 648');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* ISENTOS */		
	call PROC_LOG_MESSAGE('LINHA - 295');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_VALOR_UTILIZACAO,
		VAR_TP_ISENTO_VALOR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET 
	from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 318');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_01_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 323');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_02_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 406');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_04_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 379');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_04_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 379');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_06_NR_MATRICULA_EMPRESA,
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 401');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_REGISTER_COLUMN,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_ID_COLUMN_05_VL_ISENCAO,
		VAR_CD_ISENTO_COLS_DEF_VL_ISENCAO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 428');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
