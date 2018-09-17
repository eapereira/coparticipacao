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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180805-001-dml-abbvie.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180805-002-dml-abbvie-8B1LR.sql';
	
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
	
	declare VAR_ID_OPERADORA_SULAMERICA				bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 							bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 							bigint( 17 );
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01_TP_REGISTRO				bigint( 17 );
	declare VAR_ID_COLUMN_02_PREFIXO_EMPRESA			bigint( 17 );
	declare VAR_ID_COLUMN_03_CD_EMPRESA					bigint( 17 );
	declare VAR_ID_COLUMN_04_NR_MATRICULA_DEPENDENTE	bigint( 17 );
	declare VAR_ID_COLUMN_05							bigint( 17 );
	declare VAR_ID_COLUMN_06_NUM_DEPENDENTE				bigint( 17 );
	declare VAR_ID_COLUMN_07_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_ID_COLUMN_08_NM_EMPRESA					bigint( 17 );
	declare VAR_ID_COLUMN_09_DIGITO						bigint( 17 );
	declare VAR_ID_COLUMN_10_CPF_DEPENDENTE				bigint( 17 );
	declare VAR_ID_COLUMN_11_MES						bigint( 17 );
	declare VAR_ID_COLUMN_12							bigint( 17 );
	declare VAR_ID_COLUMN_13_ANO						bigint( 17 );
	declare VAR_ID_COLUMN_14_POSITIVO_NEGATIVO			bigint( 17 );
	declare VAR_ID_COLUMN_15_VL_PRINCIPAL				bigint( 17 );
	declare VAR_ID_COLUMN_16_CD_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_17_DT_NASCIMENTO				bigint( 17 );
	declare VAR_ID_COLUMN_18_CD_EMPRESA					bigint( 17 );
	declare VAR_ID_COLUMN_19_NR_MATRICULA				bigint( 17 );
	declare VAR_ID_COLUMN_20							bigint( 17 );
	declare VAR_ID_COLUMN_21							bigint( 17 );
	declare VAR_ID_COLUMN_22_NM_TITULAR					bigint( 17 );
	declare VAR_ID_COLUMN_23_NR_MATRICULA				bigint( 17 );
	declare VAR_ID_COLUMN_24							bigint( 17 );

	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ISENTOS								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_DELLOITE							bigint( 17 );
	
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
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_TITULAR					bigint( 17 ) default 7;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 10;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CODE_REF					bigint( 17 ) default 11;

	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA			bigint( 17 ) default 10;
	
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
    where NM_EMPRESA = 'Abbvie';
        
    call PROC_LOG_MESSAGE('LINHA - 123');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA 	= VAR_ID_EMPRESA
	and		CD_CONTRATO = '8B1LR';
	
	call PROC_LOG_MESSAGE('LINHA - 139');
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
		'^(ABBVIE)\\.(8B1LR)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(txt|TXT)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_FLATFILE,
		VAR_USE_TYPE_FATUCOPA,
		1,
		182,
				
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
		'COLUMN_01_TP_REGISTRO',
		VAR_COL_VARCHAR,
		2,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_TP_REGISTRO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_02_PREFIXO_EMPRESA',
		VAR_COL_VARCHAR,
		1,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_PREFIXO_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_03_CD_EMPRESA',
		VAR_COL_VARCHAR,
		4,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_CD_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_04_NR_MATRICULA_DEPENDENTE',
		VAR_COL_LONG,
		8,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_NR_MATRICULA_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
	
	select max( ID ) into VAR_ID_COLUMN_05 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_06_NUM_DEPENDENTE',
		VAR_COL_VARCHAR,
		1,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_NUM_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
	
	select max( ID ) into VAR_ID_COLUMN_07_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		'COLUMN_08_NM_EMPRESA',
		VAR_COL_VARCHAR,
		6,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_NM_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		'COLUMN_09_DIGITO',
		VAR_COL_VARCHAR,
		1,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_DIGITO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_10_CPF_DEPENDENTE',
		VAR_COL_LONG,
		11,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_CPF_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_11_MES',
		VAR_COL_INT,
		2,
		11,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_MES from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		'COLUMN_12',
		VAR_COL_VARCHAR,
		1,
		12,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_13_ANO',
		VAR_COL_INT,
		4,
		13,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_ANO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_14_POSITIVO_NEGATIVO',
		VAR_COL_VARCHAR,
		1,
		14,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_POSITIVO_NEGATIVO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_15_VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		15,
		15,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_16_CD_CONTRATO',
		VAR_COL_VARCHAR,
		5,
		16,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_CD_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_17_DT_NASCIMENTO',
		VAR_COL_DATE,
		10,
		17,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_18_CD_EMPRESA',
		VAR_COL_VARCHAR,
		4,
		18,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_CD_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		VAR_COL_VARCHAR,
		8,
		19,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		2,
		20,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_21',
		VAR_COL_VARCHAR,
		1,
		21,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_22_NM_TITULAR',
		VAR_COL_VARCHAR,
		32,
		22,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22_NM_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_23_NR_MATRICULA_TITULAR',
		VAR_COL_LONG,
		8,
		23,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
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
		'COLUMN_24',
		VAR_COL_VARCHAR,
		1,
		24,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_24 from TB_ARQUIVO_INPUT_COLS_DEF; 


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
		VAR_ID_COLUMN_10_CPF_DEPENDENTE,
		
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
		VAR_ID_COLUMN_16_CD_CONTRATO,
		
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
		VAR_ID_COLUMN_11_MES,
		
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
		VAR_ID_COLUMN_13_ANO,
		
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
		VAR_ID_COLUMN_15_VL_PRINCIPAL,
		
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
		'ABBVIE.8C5Z8 - Regra para Valor do Arquivo FATUCOPA ABBVIE dividido por 100',
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
		VAR_ID_COLUMN_15_VL_PRINCIPAL,
		
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
		VAR_ID_COLUMN_15_VL_PRINCIPAL,
		
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
		'ABBVIE.8B1LR - Regra para multiplicar o VL_PRINCIPAL por ( -1 ) se o valor da coluna COLUMN_13_POSITIVO_NEGATIVO for igual a ( - )',
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
		VAR_ID_COLUMN_15_VL_PRINCIPAL,
		
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
		VAR_ID_COLUMN_15_VL_PRINCIPAL,
		
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
		'ABBVIE.8B1LR - Regra para mudar o sinal do campo VL_PRINCIPAL dependendo do valor (+/-) do campo VL_NEGATIVO_POSITIVO',
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
		VAR_ID_COLUMN_14_POSITIVO_NEGATIVO,
		
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
	
    call PROC_LOG_MESSAGE('LINHA - 1149');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_23_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1181');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_07_NM_BENEFICIARIO,
		
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
		VAR_ID_COLUMN_10_CPF_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1192');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_17_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 1288');
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/		
	/* NAO-LOCALIZADOS */
	
    call PROC_LOG_MESSAGE('LINHA - 1264');
	select ID into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DESCONHECIDO_ABBVIE';
	
	call PROC_LOG_MESSAGE('LINHA - 1268');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		NM_DESCR_ARQUIVO,	
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'NAO-LOCALIZADO-ABBVIE-{YYYY}{MM}.xlsx',
		'Arquivo com os beneficiários não localizados pelo processo',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
		
	call PROC_LOG_MESSAGE('LINHA - 1288');
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
    
    call PROC_LOG_MESSAGE('LINHA - 1387');
	select ID into VAR_ID_VIEW_DESTINATION_COPART from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_COPARTICIPACAO_ABBVIE';

	select ID into VAR_ID_VIEW_DESTINATION_ISENTOS from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_ISENTOS_ABBVIE';

	select ID into VAR_ID_VIEW_DESTINATION_DELLOITE from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DELLOITE_ABBVIE';    
	
	/* FASTU-COPA.1 */	
    
	call PROC_LOG_MESSAGE('LINHA - 1399');
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'Abbvie-SAS (Saúde)_Coparticipacao_({YYYY}_{MM}).xlsx',
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
		'Coparticipação',
		
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
		VAR_ID_VIEW_DESTINATION_ISENTOS,
		'Isentos',
		
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
		VAR_ID_VIEW_DESTINATION_DELLOITE,
		'Delloite',
		
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
