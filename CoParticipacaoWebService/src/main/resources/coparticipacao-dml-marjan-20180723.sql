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
	DECLARE VAR_ID_CONTRATO_GESTANTES 		bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z8	bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z9	bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XY	bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XX	bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9		bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_GESTANTES 			bigint( 17 );
	
	declare VAR_ID_COLUMN_01_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_02_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_03_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_04_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_05_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_06_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_07_8C5Z8_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08_8C5Z8_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_09_8C5Z8_MES					bigint( 17 );
	declare VAR_ID_COLUMN_10_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_11_8C5Z8_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_12_8C5Z8_POSITIVO_NEGATIVO	bigint( 17 );
	declare VAR_ID_COLUMN_13_8C5Z8_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_14_8C5Z8_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_15_8C5Z8_DT_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_16_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_17_8C5Z8_NM_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_18_8C5Z8_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_19_8C5Z8						bigint( 17 );
	
	declare VAR_ID_COLUMN_01_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_02_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_03_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_04_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_05_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_06_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_07_8C5Z9_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08_8C5Z9_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_09_8C5Z9_MES					bigint( 17 );
	declare VAR_ID_COLUMN_10_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_11_8C5Z9_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_12_8C5Z9_POSITIVO_NEGATIVO	bigint( 17 );
	declare VAR_ID_COLUMN_13_8C5Z9_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_14_8C5Z9_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_15_8C5Z9_DT_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_16_8C5Z9						bigint( 17 );
	declare VAR_ID_COLUMN_17_8C5Z9_NM_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_18_8C5Z9_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_19_8C5Z9						bigint( 17 );
	

	/***********************************************************************************************************************/
	
	DECLARE exit handler for sqlexception
	BEGIN
		-- ERROR
		select 'Erro ao executar procedure:';
		GET DIAGNOSTICS CONDITION 1
        	VAR_CODE = RETURNED_SQLSTATE, VAR_MSG = MESSAGE_TEXT;
    
       	select VAR_CODE, VAR_MSG;
		ROLLBACK;
	END;

	START TRANSACTION;
	
	/***********************************************************************************************************************/	
	
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,	
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADOR_SULAMERICA, /* Sulamerica */
		'Marjan',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8C5Z8',
	    'Contrato Marjan',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_FATUCOPA_8C5Z8 from TB_CONTRATO;

	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8C5Z9',
	    'Contrato Marjan',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_FATUCOPA_8C5Z9 from TB_CONTRATO;
		
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8C7XX',
	    'Contrato Marjan',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_FATUCOPA_8C7XX from TB_CONTRATO;

	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8C7XY',
	    'Contrato Marjan',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_FATUCOPA_8C7XY from TB_CONTRATO;
	
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'MECSAS',
	    'MECSAS Marjan',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_MECSAS from TB_CONTRATO;
	
	/***********************************************************************************************************************/	
	/* FATU-COPA */
	
	insert into TB_ARQUIVO_INPUT_LAYOUT(
		NM_LAYOUT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		'FATUCOPA-MARJAN',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
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
	    VAR_ID_CONTRATO_FATUCOPA,
		'^(8C5Z8)(FATUCOPA)\\.([0-9]{4})([0-9]{2})([0-9]{3})F\\.(txt|TXT)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_FLATFILE,
		VAR_USE_TYPE_FATUCOPA,
		3,
		null,
		
		1, /* REGEXP_CONTRATO */
		5, /* REGEXP_DIA */
		4, /* REGEXP_MES */
		3, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8 from TB_ARQUIVO_INPUT;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_01',
		VAR_COL_VARCHAR,
		2,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_02',
		VAR_COL_VARCHAR,
		1,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_03',
		VAR_COL_VARCHAR,
		4,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_04',
		VAR_COL_VARCHAR,
		8,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_05',
		VAR_COL_VARCHAR,
		2,
		5,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_06',
		VAR_COL_VARCHAR,
		1,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_07_NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		38,
		7,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_8C5Z8_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_08_CPF',
		VAR_COL_VARCHAR,
		11,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_8C5Z8_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_09_MES',
		VAR_COL_VARCHAR,
		2,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_8C5Z8_MES from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_10',
		VAR_COL_VARCHAR,
		1,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_11_ANO',
		VAR_COL_VARCHAR,
		4,
		11,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_8C5Z8_ANO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_12_POSITIVO_NEGATIVO',
		VAR_COL_VARCHAR,
		1,
		12,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_8C5Z8_POSITIVO_NEGATIVO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_13_VL_PRINCIPAL',
		VAR_COL_VARCHAR,
		15,
		13,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_8C5Z8_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_14_CONTRATO',
		VAR_COL_VARCHAR,
		5,
		14,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_8C5Z8_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_15_DT_NASCIMENTO',
		VAR_COL_VARCHAR,
		10,
		15,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_8C5Z8_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_16',
		VAR_COL_VARCHAR,
		15,
		16,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_17_NM_TITULAR',
		VAR_COL_VARCHAR,
		32,
		17,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_8C5Z8_NM_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_18_NR_MATRICULA',
		VAR_COL_VARCHAR,
		8,
		18,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_8C5Z8_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8,
		'COLUMN_19',
		VAR_COL_VARCHAR,
		1,
		19,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_8C5Z8 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
	    VAR_ID_CONTRATO_FATUCOPA,
		'^(8C5Z9)(FATUCOPA)\.([0-9]{4})[0-9]{2}([0-9]{3})F\.(txt|TXT)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_FLATFILE,
		VAR_USE_TYPE_FATUCOPA,
		3,
		null,
		
		1, /* REGEXP_CONTRATO */
		5, /* REGEXP_DIA */
		4, /* REGEXP_MES */
		3, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9 from TB_ARQUIVO_INPUT;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_01',
		VAR_COL_VARCHAR,
		2,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_02',
		VAR_COL_VARCHAR,
		1,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_03',
		VAR_COL_VARCHAR,
		4,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_04',
		VAR_COL_VARCHAR,
		8,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_05',
		VAR_COL_VARCHAR,
		2,
		5,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_06',
		VAR_COL_VARCHAR,
		1,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_07_NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		38,
		7,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_8C5Z9_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_08_CPF',
		VAR_COL_VARCHAR,
		11,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_8C5Z9_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_09_MES',
		VAR_COL_VARCHAR,
		2,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_8C5Z9_MES from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_10',
		VAR_COL_VARCHAR,
		1,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_11_ANO',
		VAR_COL_VARCHAR,
		4,
		11,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_8C5Z9_ANO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_12_POSITIVO_NEGATIVO',
		VAR_COL_VARCHAR,
		1,
		12,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_8C5Z9_POSITIVO_NEGATIVO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_13_VL_PRINCIPAL',
		VAR_COL_VARCHAR,
		15,
		13,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_8C5Z9_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_14_CONTRATO',
		VAR_COL_VARCHAR,
		5,
		14,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_8C5Z9_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_15_DT_NASCIMENTO',
		VAR_COL_VARCHAR,
		10,
		15,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_8C5Z9_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_16',
		VAR_COL_VARCHAR,
		15,
		16,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_17_NM_TITULAR',
		VAR_COL_VARCHAR,
		32,
		17,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_8C5Z9_NM_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_18_NR_MATRICULA',
		VAR_COL_VARCHAR,
		8,
		18,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_8C5Z9_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9,
		'COLUMN_19',
		VAR_COL_VARCHAR,
		1,
		19,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_8C5Z9 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	/***********************************************************************************************************************/
	/* FATU-COPA.1 */
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		1, /* ID_BENEFICIARIO */
		VAR_ID_COLUMN_08_8C5Z8_CPF,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		3, /* ID_CONTRATO */
		VAR_ID_COLUMN_14_8C5Z8_CONTRATO,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		4, /* CD_MES */
		VAR_ID_COLUMN_09_8C5Z8_MES,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		5, /* CD_ANO */
		VAR_ID_COLUMN_11_8C5Z8_ANO,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	/*****************************************************************************************/
	/* FATU-COPA.2 */
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		1, /* ID_BENEFICIARIO */
		VAR_ID_COLUMN_08_8C5Z9_CPF,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		3, /* ID_CONTRATO */
		VAR_ID_COLUMN_14_8C5Z9_CONTRATO,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		4, /* CD_MES */
		VAR_ID_COLUMN_09_8C5Z9_MES,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		5, /* CD_ANO */
		VAR_ID_COLUMN_11_8C5Z9_ANO,
		
		1,
		current_timestamp(),
		current_timestamp()	
	);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***********************************************************************************************************************/
	
	COMMIT;
	
	select 'Alterações executadas com sucesso.';
END
$$

call PROC_CREATE_HOC(); 
