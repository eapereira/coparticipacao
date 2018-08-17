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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default 'dml-20180802.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default 'dml-20180703-muito-facil-8CH5Y.sql';
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_FALSE						int( 3 ) default 0;
	declare VAR_TRUE						int( 3 ) default 1;
    
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
	
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01						bigint( 17 );
	declare VAR_ID_COLUMN_02						bigint( 17 );
	declare VAR_ID_COLUMN_03						bigint( 17 );
	declare VAR_ID_COLUMN_04						bigint( 17 );
	declare VAR_ID_COLUMN_05						bigint( 17 );
	declare VAR_ID_COLUMN_06						bigint( 17 );
	declare VAR_ID_COLUMN_07_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08						bigint( 17 );
	declare VAR_ID_COLUMN_09_NR_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_10_CD_MES					bigint( 17 );
	declare VAR_ID_COLUMN_11						bigint( 17 );
	declare VAR_ID_COLUMN_12_CD_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_13_POSITIVO_NEGATIVO		bigint( 17 );
	declare VAR_ID_COLUMN_14_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_15_CD_CONTRATO			bigint( 17 );
	declare VAR_ID_COLUMN_16_DT_NASCIMENTO			bigint( 17 );
	declare VAR_ID_COLUMN_17						bigint( 17 );	
	declare VAR_ID_COLUMN_18						bigint( 17 );
	declare VAR_ID_COLUMN_19						bigint( 17 );
	declare VAR_ID_COLUMN_20						bigint( 17 );
	declare VAR_ID_COLUMN_21_NM_TITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_22_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_23						bigint( 17 );

	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO	bigint( 17 );
	
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_MATRICULA  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_BENEFICIARIO  	bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_TITULAR  		bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_CPF  			bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL  		bigint( 17 );
	
    declare VAR_ID_LANCAMENTO_INPUT										bigint( 17 );
    
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	
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

    call PROC_LOG_MESSAGE('LINHA - 134');

    /**
    * Muito Fácil
    */
    call PROC_LOG_MESSAGE('LINHA - 139');
    insert into TB_EMPRESA (
        NM_EMPRESA,	
        CD_EMPRESA,
        ID_OPERADORA,
        CD_AUTOMATIC_CREATE_BENEFICIARIO,
        CD_OUTPUT_REPORT_DIR,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        'MUITO-FACIL',
        'MUITO-FACIL',
        VAR_ID_OPERADORA_SULAMERICA,
        VAR_TRUE,
        '/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/muito-facil/',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	call PROC_LOG_MESSAGE('LINHA - 157');
    insert into TB_CONTRATO(
        ID_EMPRESA,
        CD_CONTRATO,	
        NM_CONTRATO,
        TP_USO,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        '8CH5Y',
        '8CH5Y',
        VAR_USE_TYPE_FATUCOPA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;    

	call PROC_LOG_MESSAGE('LINHA - 157');
    insert into TB_CONTRATO(
        ID_EMPRESA,
        CD_CONTRATO,	
        NM_CONTRATO,
        TP_USO,
        CD_SPREADSHEET_ALL_PAGES,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        'MECSAS2.muitoFacil.naoLocalizados',
        'NAO-LOCALIZADOS',
        VAR_USE_TYPE_NAO_LOCALIZADO,
        VAR_TRUE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;    
    
    /*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 215');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where CD_CONTRATO = '8CH5Y';    
    
    call PROC_LOG_MESSAGE('LINHA - 219');
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
        VAR_ID_CONTRATO,
        '^(8CH5Y)FATUCOPA\\.([0-9]{4})([0-9]{2})([0-9]{1,})(F)\\.(txt|TXT)',
        'Arquivo de carga de coparticipação',
        VAR_ARQUIVO_TYPE_FLATFILE,
        VAR_USE_TYPE_FATUCOPA,
        1,
        182,
        
        1, /* REGEXP_CONTRATO */
        4, /* REGEXP_DIA */
        3, /* REGEXP_MES */
        2, /* REGEXP_ANO */
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
    
    call PROC_LOG_MESSAGE('LINHA - 242');
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
        VAR_COL_INT,
        2,
        1,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_01 from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 265');
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
        VAR_COL_INT,
        1,
        2,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_02 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        VAR_COL_INT,
        4,
        3,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_03 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        VAR_COL_INT,
        8,
        4,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_04 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        VAR_COL_INT,
        1,
        6,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_06 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_07_NOME_COMPLETO_DEPENDENTE',
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
        'COLUMN_08',
        VAR_COL_VARCHAR,
        6,
        8,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_08 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_09_CPF',
        VAR_COL_LONG,
        12,
        9,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_09_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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

    select max( ID ) into VAR_ID_COLUMN_10_CD_MES from TB_ARQUIVO_INPUT_COLS_DEF;
    
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

    select max( ID ) into VAR_ID_COLUMN_11 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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

    select max( ID ) into VAR_ID_COLUMN_12_CD_ANO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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

    select max( ID ) into VAR_ID_COLUMN_13_POSITIVO_NEGATIVO from TB_ARQUIVO_INPUT_COLS_DEF;
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
        'COLUMN_14_VALOR_PRINCIPAL',
        VAR_COL_DOUBLE,
        15,
        14,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_14_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_15_NR_CONTRATO',
        VAR_COL_VARCHAR,
        5,
        15,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_15_CD_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_16_DATA_NASCIMENTO',
        VAR_COL_DATE,
        10,
        16,
        'dd/MM/yyyy',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_16_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        4,
        17,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_17 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_18',
        VAR_COL_VARCHAR,
        8,
        18,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_18 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_19',
        VAR_COL_VARCHAR,
        2,
        19,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_19 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_21_NOME_COMPLETO_TITULAR',
        VAR_COL_VARCHAR,
        32,
        21,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_21_NM_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_22_MATRICULA',
        VAR_COL_LONG,
        8,
        22,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_22_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COLUMN_23',
        VAR_COL_LONG,
        21,
        23,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_COLUMN_23 from TB_ARQUIVO_INPUT_COLS_DEF;
    
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
	/* LANÇAMENTO */
    
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
    
    call PROC_LOG_MESSAGE('LINHA - 773');
    insert into TB_LANCAMENTO_INPUT_COLS (
        ID_LANCAMENTO_INPUT,
        ID_ARQUIVO_INPUT_COLS_DEF,
        CD_LANCAMENTO_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_LANCAMENTO_INPUT,
        VAR_ID_COLUMN_09_NR_CPF,
        VAR_COL_LANCAMENTO_ID_DEPENDENTE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );

    call PROC_LOG_MESSAGE('LINHA - 791');
    insert into TB_LANCAMENTO_INPUT_COLS (
        ID_LANCAMENTO_INPUT,
        ID_ARQUIVO_INPUT_COLS_DEF,
        CD_LANCAMENTO_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_LANCAMENTO_INPUT,
        VAR_ID_COLUMN_15_CD_CONTRATO,
        VAR_COL_LANCAMENTO_ID_CONTRATO,        
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );

    call PROC_LOG_MESSAGE('LINHA - 809');
    insert into TB_LANCAMENTO_INPUT_COLS (
        ID_LANCAMENTO_INPUT,
        ID_ARQUIVO_INPUT_COLS_DEF,
        CD_LANCAMENTO_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_LANCAMENTO_INPUT,
        VAR_ID_COLUMN_10_CD_MES,
        VAR_COL_LANCAMENTO_CD_MES,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );

    call PROC_LOG_MESSAGE('LINHA - 827');
    insert into TB_LANCAMENTO_INPUT_COLS (
        ID_LANCAMENTO_INPUT,
        ID_ARQUIVO_INPUT_COLS_DEF,
        CD_LANCAMENTO_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_LANCAMENTO_INPUT,
        VAR_ID_COLUMN_12_CD_ANO,
        VAR_COL_LANCAMENTO_CD_ANO,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );

    call PROC_LOG_MESSAGE('LINHA - 845');
    insert into TB_LANCAMENTO_INPUT_COLS (
        ID_LANCAMENTO_INPUT,
        ID_ARQUIVO_INPUT_COLS_DEF,
        CD_LANCAMENTO_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_LANCAMENTO_INPUT,
        VAR_ID_COLUMN_14_VL_PRINCIPAL,
        VAR_COL_LANCAMENTO_VL_PRINCIPAL,
        
       	VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /* Regra */

    call PROC_LOG_MESSAGE('LINHA - 867');
    insert into TB_REGRA(
        NM_REGRA,
        TP_REGRA,
        CD_ORDEM,
        ID_ARQUIVO_INPUT,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para Valor do Arquivo FATUCOPA dividido por 100',
        1, /* SIMPLES */
        0,
        VAR_ID_ARQUIVO_INPUT,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA from TB_REGRA;
    
    call PROC_LOG_MESSAGE('LINHA - 891');
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
    
    call PROC_LOG_MESSAGE('LINHA - 909');
    insert into TB_REGRA_FIELD(
        ID_REGRA_OPERATION,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_OPERATION,
        VAR_ID_COLUMN_14_VL_PRINCIPAL,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

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

    insert into TB_REGRA_RESULT(
        ID_REGRA,
        ID_ARQUIVO_INPUT_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA,
        VAR_ID_COLUMN_14_VL_PRINCIPAL,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
        
    /* Regra Condicional */
    
	call PROC_LOG_MESSAGE('LINHA - 957');
    insert into TB_REGRA(
        NM_REGRA,
        TP_REGRA,
        CD_ORDEM,
        ID_ARQUIVO_INPUT,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para multiplicar o VL_PRINCIPAL por ( -1 ) se o valor da coluna COLUMN_13_POSITIVO_NEGATIVO for igual a ( - )',
        2, /* CONDICIONAL */
        0,
        VAR_ID_ARQUIVO_INPUT,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA from TB_REGRA;
    
    insert into TB_REGRA_OPERATION(
        ID_REGRA,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA,
        3, /* MULTIPLICAR */
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
    
    call PROC_LOG_MESSAGE('LINHA - 998');
    insert into TB_REGRA_FIELD(
        ID_REGRA_OPERATION,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_OPERATION,
        VAR_ID_COLUMN_14_VL_PRINCIPAL,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    call PROC_LOG_MESSAGE('LINHA - 1014');
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

    call PROC_LOG_MESSAGE('LINHA - 1030');
    insert into TB_REGRA_RESULT(
        ID_REGRA,
        ID_ARQUIVO_INPUT_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA,
        VAR_ID_COLUMN_14_VL_PRINCIPAL,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    call PROC_LOG_MESSAGE('LINHA - 1046');
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_ARQUIVO_INPUT,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para mudar o sinal do campo VL_PRINCIPAL dependendo do valor (+/-) do campo VL_NEGATIVO_POSITIVO',
        0,
        VAR_ID_ARQUIVO_INPUT,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1063');
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
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_13_POSITIVO_NEGATIVO,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

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

    insert into TB_REGRA_CONDITIONAL_RESULT(
        ID_REGRA_CONDITIONAL,
        ID_REGRA_EXECUTION,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_ID_REGRA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /* FASTU-COPA.2 */
    call PROC_LOG_MESSAGE('LINHA - 1127');
    insert into TB_VIEW_DESTINATION(
        NM_VIEW,
        NM_TITLE_LABEL,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'VW_LANCAMENTO_MUITO_FACIL',
        '8CHE8',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
    
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
        VAR_ID_VIEW_DESTINATION,
        'NM_TITULAR',
        VAR_COL_VARCHAR,
        32,
        1,
        'Títular',
        
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
        VAR_ID_VIEW_DESTINATION,
        'NR_CPF',
        VAR_COL_LONG,
        12,
        2,
        'CPF',
        
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
        VAR_ID_VIEW_DESTINATION,
        'NR_MATRICULA',
        VAR_COL_LONG,
        8,
        3,
        'Matricula',
        
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
        VAR_ID_VIEW_DESTINATION,
        'VL_PRINCIPAL',
        VAR_COL_DOUBLE,
        15,
        4,
        'Valor Principal',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    /***********************************************************************************************************************************************/
    
    call PROC_LOG_MESSAGE('LINHA - 1139');
    insert into TB_VIEW_DESTINATION(
        NM_VIEW,
        NM_TITLE_LABEL,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'VW_DESCONHECIDO_MUITO_FACIL',
        'NAO-LOCALIZADOS',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
    
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
        VAR_ID_VIEW_DESTINATION,
        'NR_MATRICULA',
        VAR_COL_LONG,
        20,
        1,
        'MATRICULA',
        
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
        VAR_ID_VIEW_DESTINATION,
        'NM_BENEFICIARIO',
        VAR_COL_VARCHAR,
        40,
        2,
        'NOME BENEFICIÁRIO',
        
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
        VAR_ID_VIEW_DESTINATION,
        'NR_CPF',
        VAR_COL_LONG,
        20,
        3,
        'CPF',
        
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
        VAR_ID_VIEW_DESTINATION,
        'VL_PRINCIPAL',
        VAR_COL_LONG,
        20,
        4,
        'VALOR PRINCIPAL',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /* FATU-COPA.1 */

    call PROC_LOG_MESSAGE('LINHA - 1462');
	select ID into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DESCONHECIDO_MUITO_FACIL';
    
    call PROC_LOG_MESSAGE('LINHA - 1131');
    insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
        ID_ARQUIVO_INPUT,
        NM_ARQUIVO_FORMAT,
        NM_DESCR_ARQUIVO,	
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_INPUT,
        'NAO-LOCALIZADO-MUITO-FACIL-{YYYY}{MM}.xlsx',
        'Arquivo com os beneficiários não localizados pelo processo',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
    
	call PROC_LOG_MESSAGE('LINHA - 1151');
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
			    
    /*****************************************************************************************************************************************************/

    /* FASTU-COPA.1 */

    call PROC_LOG_MESSAGE('LINHA - 1462');
	select ID into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_LANCAMENTO_MUITO_FACIL';
    
    call PROC_LOG_MESSAGE('LINHA - 1469');
    insert into TB_ARQUIVO_OUTPUT(
        ID_ARQUIVO_INPUT,
        NM_ARQUIVO_FORMAT,
        DESCR_ARQUIVO,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_INPUT,
        'NAO-LOCALIZADO-MUITO-FACIL-{YYYY}{MM}.xlsx',
        'Arquivo de saída para a carga dos lançamentos FATU COPA',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
    select max( ID ) into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT;

    call PROC_LOG_MESSAGE('LINHA - 1488');
    insert into TB_ARQUIVO_OUTPUT_SHEET(
        ID_ARQUIVO_OUTPUT,
        ID_VIEW_DESTINATION,
        NM_SHEET,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_OUTPUT,
        VAR_ID_VIEW_DESTINATION,
        '%s',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/    
    /* FATO-COPA.1 */

    call PROC_LOG_MESSAGE('LINHA - 1529');
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
        VAR_ID_COLUMN_22_NR_MATRICULA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
        VAR_ID_COLUMN_09_NR_CPF,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
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
    
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 

