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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default 'dml-20180724-004-marjan-8C7XX.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default 'dml-20180726-005-marjan-8C7XY.sql';

	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	
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
	DECLARE VAR_ID_CONTRATO_FATUCOPA 		bigint( 17 );
	DECLARE VAR_ID_CONTRATO_MECSAS 			bigint( 17 );
	DECLARE VAR_ID_CONTRATO_ISENTOS 		bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XY			bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_02_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_03_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_04_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_05_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_06_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_07_8C7XY_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_09_8C7XY_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_10_8C7XY_MES					bigint( 17 );
	declare VAR_ID_COLUMN_11_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_12_8C7XY_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_13_8C7XY_POSITIVO_NEGATIVO	bigint( 17 );
	declare VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_15_8C7XY_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_16_8C7XY_DT_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_17_8C7XY						bigint( 17 );
	declare VAR_ID_COLUMN_18_8C7XY_NM_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_19_8C7XY_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_20_8C7XY						bigint( 17 );


	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
	
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
	declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	
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

	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	
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
    call PROC_LOG_MESSAGE('LINHA - 115');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'Marjan';
        
    call PROC_LOG_MESSAGE('LINHA - 123');
	select ID into VAR_ID_CONTRATO_FATUCOPA_8C7XY from TB_CONTRATO
	where CD_CONTRATO = '8C7XY'; 
	
	call PROC_LOG_MESSAGE('LINHA - 159');
	insert into TB_ARQUIVO_INPUT(
		ID_CONTRATO,
		NM_ARQUIVO_REGEXP,
		DESCR_ARQUIVO,	
		TP_ARQUIVO,
		TP_USE,
		NUM_SKIP_LINES,
		NUM_DEFAULT_LINE_LENGTH,
		
		CD_REGEXP_CONTRATO,
		CD_REGEXP_DIA,
		CD_REGEXP_MES,
		CD_REGEXP_ANO,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_CONTRATO_FATUCOPA_8C7XY,
		'^(8C7XY)(FATUCOPA)\\.([0-9]{4})([0-9]{2})([0-9]{3})F\\.(txt|TXT)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_FLATFILE,
		VAR_USE_TYPE_FATUCOPA,
		1,
		182,
		
		1, /* REGEXP_CONTRATO */
		5, /* REGEXP_DIA */
		4, /* REGEXP_MES */
		3, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;

	call PROC_LOG_MESSAGE('LINHA - 176');
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
		'COLUMN_01',
		VAR_COL_VARCHAR,
		2,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_02',
		VAR_COL_VARCHAR,
		1,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 223');
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
		'COLUMN_03',
		VAR_COL_VARCHAR,
		4,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
    call PROC_LOG_MESSAGE('LINHA - 246');
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
		'COLUMN_04',
		VAR_COL_VARCHAR,
		8,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_05',
		VAR_COL_VARCHAR,
		2,
		5,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 536');
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
		'COLUMN_06',
		VAR_COL_VARCHAR,
		1,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
    call PROC_LOG_MESSAGE('LINHA - 318');
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
		'COLUMN_07_NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		32,
		7,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_8C7XY_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		'COLUMN_08',
		VAR_COL_VARCHAR,
		6,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_09_CPF',
		VAR_COL_LONG,
		12,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_8C7XY_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 686');
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
		'COLUMN_10_MES',
		VAR_COL_INT,
		2,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_8C7XY_MES from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		'COLUMN_11',
		VAR_COL_VARCHAR,
		1,
		11,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 653');
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
		'COLUMN_12_ANO',
		VAR_COL_INT,
		4,
		12,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_8C7XY_ANO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_13_POSITIVO_NEGATIVO',
		VAR_COL_VARCHAR,
		1,
		13,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_8C7XY_POSITIVO_NEGATIVO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 780');
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
		'COLUMN_14_VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		15,
		14,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_15_CONTRATO',
		VAR_COL_VARCHAR,
		5,
		15,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_8C7XY_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_16_DT_NASCIMENTO',
		VAR_COL_DATE,
		10,
		16,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_8C7XY_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_17',
		VAR_COL_VARCHAR,
		15,
		17,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_18_NM_TITULAR',
		VAR_COL_VARCHAR,
		32,
		18,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_8C7XY_NM_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 790');
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
		'COLUMN_19_NR_MATRICULA',
		VAR_COL_LONG,
		8,
		19,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_8C7XY_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 638');
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
		'COLUMN_20',
		VAR_COL_VARCHAR,
		1,
		20,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20_8C7XY from TB_ARQUIVO_INPUT_COLS_DEF; 


	/***********************************************************************************************************************/
	/***********************************************************************************************************************/
	/* FATU-COPA.1 */

    call PROC_LOG_MESSAGE('LINHA - 760');
    insert into TB_LANCAMENTO_INPUT (
        ID_ARQUIVO_INPUT,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_INPUT,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );
    
    select max( ID ) into VAR_ID_LANCAMENTO_INPUT from TB_LANCAMENTO_INPUT;
	
	call PROC_LOG_MESSAGE('LINHA - 701');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_ID_DEPENDENTE,
		VAR_ID_COLUMN_09_8C7XY_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
    call PROC_LOG_MESSAGE('LINHA - 718');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_ID_CONTRATO,
		VAR_ID_COLUMN_15_8C7XY_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
    call PROC_LOG_MESSAGE('LINHA - 735');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_CD_MES,
		VAR_ID_COLUMN_10_8C7XY_MES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
    call PROC_LOG_MESSAGE('LINHA - 738');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_CD_ANO,
		VAR_ID_COLUMN_12_8C7XY_ANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);

	call PROC_LOG_MESSAGE('LINHA - 754');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_VL_PRINCIPAL,
		VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	
	
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/

	call PROC_LOG_MESSAGE('LINHA - 773');
	insert into TB_REGRA(
		NM_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_ARQUIVO_INPUT,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'MARJAN.8C7XY - Regra para Valor do Arquivo FATUCOPA MARJAN dividido por 100',
		1, /* SIMPLES */
		0,
		VAR_ID_ARQUIVO_INPUT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 795');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		4, /* DIVIDIR */
		0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 815');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 3896');
	insert into TB_REGRA_VALOR(
		ID_REGRA_OPERATION,
		VL_REGRA_VALOR,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		100.0, /* 100 */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 3912');
	insert into TB_REGRA_RESULT(
		ID_REGRA,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	/* Regra Condicional */
	
	call PROC_LOG_MESSAGE('LINHA - 3930');
	insert into TB_REGRA(
		NM_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_ARQUIVO_INPUT,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'MARJAN.8C7XY - Regra para multiplicar o VL_PRINCIPAL por ( -1 ) se o valor da coluna COLUMN_13_POSITIVO_NEGATIVO for igual a ( - )',
		2, /* CONDICIONAL */
		0,
		VAR_ID_ARQUIVO_INPUT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_TO_EXECUTE from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 3952');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_TO_EXECUTE,
		3, /* MULTIPLICAR */
		0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 3972');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 3988');
	insert into TB_REGRA_VALOR(
		ID_REGRA_OPERATION,
		VL_REGRA_VALOR,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		-1.0, /* -1 */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4004');
	insert into TB_REGRA_RESULT(
		ID_REGRA,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_TO_EXECUTE,
		VAR_ID_COLUMN_14_8C7XY_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4020');
	insert into TB_REGRA_CONDITIONAL(
		NM_REGRA_CONDITIONAL,
		CD_ORDEM,
		ID_ARQUIVO_INPUT,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'MARJAN.8C7XY - Regra para mudar o sinal do campo VL_PRINCIPAL dependendo do valor (+/-) do campo VL_NEGATIVO_POSITIVO',
		0,
		VAR_ID_ARQUIVO_INPUT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
	
	call PROC_LOG_MESSAGE('LINHA - 4040');
	insert into TB_REGRA_CONDITIONAL_OPERATION(
		ID_REGRA_CONDITIONAL,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_CONDITIONAL,
		5, /* EQUALS */
		0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 4060');
	insert into TB_REGRA_CONDITIONAL_FIELD(
		ID_REGRA_CONDITIONAL_OPERATION,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_CONDITIONAL_OPERATION,
		VAR_ID_COLUMN_13_8C7XY_POSITIVO_NEGATIVO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4076');
	insert into TB_REGRA_CONDITIONAL_VALOR(
		ID_REGRA_CONDITIONAL_OPERATION,
		VL_STRING,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_CONDITIONAL_OPERATION,
		'-', /* VALOR */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4092');
	insert into TB_REGRA_CONDITIONAL_RESULT(
		ID_REGRA_CONDITIONAL,
		ID_REGRA_EXECUTION,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_CONDITIONAL,
		VAR_ID_REGRA_TO_EXECUTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);



	/***********************************************************************************************************************/
	/* Beneficiário */
	
    call PROC_LOG_MESSAGE('LINHA - 1848');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_19_8C7XY_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1879');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_07_8C7XY_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4834');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_09_8C7XY_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4850');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_16_8C7XY_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		

	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/		
	/* NAO LOCALIZADOS */		
	
    call PROC_LOG_MESSAGE('LINHA - 1174');
	select ID into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DESCONHECIDO_MARJAN';
	
	call PROC_LOG_MESSAGE('LINHA - 1178');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		NM_DESCR_ARQUIVO,	
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'NAO-LOCALIZADO-MARJAN-{YYYY}{MM}.xlsx',
		'Arquivo com os beneficiários não localizados pelo processo',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
	
	call PROC_LOG_MESSAGE('LINHA - 1198');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_SHEET(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		VAR_ID_VIEW_DESTINATION,
		'%s',
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	/*****************************************************************************************************************/
	/*****************************************************************************************************************/
    
    call PROC_LOG_MESSAGE('LINHA - 1383');
	select ID into VAR_ID_VIEW_DESTINATION_COPART from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_COPARTICIPACAO_TELA_MARJAN';

	select ID into VAR_ID_VIEW_DESTINATION_GESTANTE from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_ISENTOS_GESTANTE_MARJAN';

	select ID into VAR_ID_VIEW_DESTINATION_ESTAGIARIO from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_ISENTOS_ESTAGIARIO_MARJAN';

	select ID into VAR_ID_VIEW_DESTINATION_FILHOS from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_ISENTOS_FILHOS_MARJAN';
    
	
	/* FASTU-COPA.1 */	
    
	call PROC_LOG_MESSAGE('LINHA - 1400');
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'Marjan-SAS (Saúde)_Coparticipacao_({YYYY}_{MM}).xlsx',
		'Arquivo de saída para a carga dos lançamentos FATU COPA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT;
	
    call PROC_LOG_MESSAGE('LINHA - 1420');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_COPART,
		'Copart %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1422');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_GESTANTE,
		'Gestante %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
    call PROC_LOG_MESSAGE('LINHA - 1420');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_FILHOS,
		'Filhos %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
    call PROC_LOG_MESSAGE('LINHA - 1458');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
		'Estagiário %s',
		
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
