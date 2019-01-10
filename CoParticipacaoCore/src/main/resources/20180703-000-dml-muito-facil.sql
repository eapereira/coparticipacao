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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180608-004-dml-VARS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180703-000-dml-muito-facil.sql';
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_FALSE						int( 3 ) default 0;
	declare VAR_TRUE						int( 3 ) default 1;
    
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
	
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO		int( 3 ) default 0;
	declare VAR_TP_REPORT_QUERY_BY_PERIODO_ONLY				int( 3 ) default 1;
	declare VAR_TP_REPORT_QUERY_BY_SINGLE_CONTRATO			int( 3 ) default 2;
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	
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
	
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'Base de Ativos do Cliente';
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção';
	
	declare VAR_TP_REPORT_LAYOUT_TYPE_MULTIPLE_CONTRATO					int( 3 ) default 0;
	declare VAR_TP_REPORT_LAYOUT_TYPE_SINGLE_CONTRATO					int( 3 ) default 1;
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
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        CD_INPUT_DIR,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,
		TP_REPORT_QUERY,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        'MUITO-FACIL',
        'MUITO-FACIL',
        VAR_ID_OPERADORA_SULAMERICA,
        VAR_TRUE,
        '/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/muito-facil/',
        '/home/eapereira/desenv/work/coparticipacao/input/',
        '/home/eapereira/desenv/work/coparticipacao/failure/',
        '/home/eapereira/desenv/work/coparticipacao/output/',
        VAR_FALSE,
        VAR_FALSE,
        VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO,
        
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
        DESCR_CONTRATO,
        TP_USE,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        '8CH5Y',
        '8CH5Y',
        VAR_NM_CONTRATO_COPARTICIPACAO,
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
        DESCR_CONTRATO,
        TP_USE,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        '8CHE8',
        '8CHE8',
        VAR_NM_CONTRATO_COPARTICIPACAO,
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
        DESCR_CONTRATO,
        TP_USE,
        CD_SPREADSHEET_ALL_PAGES,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        'NAO-LOCALIZADO',
        'NAO-LOCALIZADO',
        VAR_NM_CONTRATO_NAO_LOCALIZADO,
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
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO = '8CH5Y';    

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
        'USUÁRIO',
        
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
        'NM_TITULAR',
        VAR_COL_VARCHAR,
        40,
        4,
        'NOME TITULAR',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    call PROC_LOG_MESSAGE('LINHA - 466');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 

