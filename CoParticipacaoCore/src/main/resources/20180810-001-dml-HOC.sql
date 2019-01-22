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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180808-006-dml-abbvie-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180810-001-dml-HOC.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
  	declare VAR_CD_ORDEM					int( 3 ) default 0;
  	
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 2;
	declare VAR_USE_TYPE_NAO_LOCALIZADO		int( 3 ) default 3;	
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 4;
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 5;
	declare VAR_USE_TYPE_EXTRA_FILE			int( 3 ) default 6;
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO		int( 3 ) default 0;
	declare VAR_TP_REPORT_QUERY_BY_PERIODO_ONLY				int( 3 ) default 1;
	declare VAR_TP_REPORT_QUERY_BY_CD_CONTRATO				int( 3 ) default 2;
	
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
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_CPF									int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_LOCAL								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_NR_RDP									int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_DT_NASCIMENTO							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_ADMISSAO								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_DEMISSAO								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_LABEL								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA							int( 3 ) default 20;

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
	
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'Base de Ativos do Cliente';
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção';
	
    declare VAR_NM_CONTRATO_PRN											varchar( 400 ) default '0444-PRN';
    
    declare VAR_TP_CREATE_BENEFICIARIO_ADD								int( 3 ) default 0;
    declare VAR_TP_CREATE_BENEFICIARIO_ADD_AS_DEMITIDO					int( 3 ) default 1;
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
    call PROC_LOG_MESSAGE('LINHA - 149');
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,	
		CD_EMPRESA,
		CD_AUTOMATIC_CREATE_BENEFICIARIO,
		CD_CREATE_BENEFICIARIO_FROM_FATUCOPA,
		TP_CREATE_BENEFICIARIO,
		CD_ACCEPT_TITULAR_WITHOUT_CPF,
		CD_OUTPUT_REPORT_DIR,		
        CD_INPUT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,			
		TP_REPORT_QUERY,
		CD_SEARCH_BENEFICIARIO_NONAME,	
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA,
		'Hospital Oswaldo Cruz',
		'0444',
		VAR_TRUE,
		VAR_TRUE,
		VAR_TP_CREATE_BENEFICIARIO_ADD_AS_DEMITIDO,
		VAR_TRUE,
		'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/hoc/',
        '/home/eapereira/desenv/work/coparticipacao/input/',
        '/home/eapereira/desenv/work/coparticipacao/failure/',
        '/home/eapereira/desenv/work/coparticipacao/output/',
        VAR_FALSE,
        VAR_FALSE,		
        VAR_TP_REPORT_QUERY_BY_CD_CONTRATO,
        VAR_TRUE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	call PROC_LOG_MESSAGE('LINHA - 167');
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
		'0444',
	    '0444',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO 
	from TB_CONTRATO;

	call PROC_LOG_MESSAGE('LINHA - 238');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    ID_CONTRATO_PARENT,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'0444-PRN',
	    '0444-PRN',
	    VAR_NM_CONTRATO_PRN,
	    VAR_FALSE,
	    VAR_USE_TYPE_FATUCOPA,
	    VAR_ID_CONTRATO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 187');
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
		'MECSAS',
	    'MECSAS',
	    VAR_NM_CONTRATO_MECSAS,
	    VAR_USE_TYPE_MECSAS,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
	call PROC_LOG_MESSAGE('LINHA - 206');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ISENTO',
	    'ISENTO',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_FALSE,
	    VAR_USE_TYPE_ISENTO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 151');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ISENTO-UTILIZACAO',
	    'ISENTO-UTILIZACAO',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_FALSE,
	    VAR_USE_TYPE_ISENTO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	

	call PROC_LOG_MESSAGE('LINHA - 151');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ISENTO-CENTAVOS',
	    'ISENTO-CENTAVOS',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_FALSE,
	    VAR_USE_TYPE_ISENTO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
		
	call PROC_LOG_MESSAGE('LINHA - 238');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'NAO-LOCALIZADO',
	    'NAO-LOCALIZADO',
	    VAR_NM_CONTRATO_NAO_LOCALIZADO,
	    VAR_FALSE,
	    VAR_USE_TYPE_NAO_LOCALIZADO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 260');	
	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* VIEW-DESTINATION */

	call PROC_LOG_MESSAGE('LINHA - 230');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_RESUMO_HOC',
		'Resumo',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		'NM_LABEL',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_LABEL,
		VAR_CD_ORDEM,
		'DESCRIÇÃO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'VALOR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 225');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_LANCAMENTO_ORIGINAL_HOC',
		'Coparticipação',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;
	
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NOME DO USUARIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPARTICIPAÇAO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 496');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENCAO_GESTANTES_HOC',
		'Isenção de Gestantes',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'NR_MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NM_USUARIO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'SEGURADA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'VALOR_ISENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NOME TÍTULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	set VAR_CD_ORDEM = 0;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 583');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENCAO_CONSELHEIROS_HOC',
		'Isenção de Conselheiros',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;		
	set VAR_CD_ORDEM = 0;
	
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NOME DO USUÁRIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART.',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 225');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_AFASTADOS_HOC',
		'Afastados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;
	
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NONE USUÁRIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 225');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_AGREGADOS_HOC',
		'Agregados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;
	
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
			
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NONE USUÁRIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_PLANO_EXTENSAO_HOC',
		'Plano de Extenção',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;	
	set VAR_CD_ORDEM = 0;
	
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		NM_COL_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NONE USUARIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 225');	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_DESLIGADOS_HOC',
		'Desligados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;

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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
			
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NONE USUÁRIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'NR_LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
		
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 225');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_PRN_HOC',
		'PRN',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'NR_MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		'TOTAL_COPART',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'VL_PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 182');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENCAO_VALOR_HOC',
		'Isenção HOC',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;

	call PROC_LOG_MESSAGE('LINHA - 205');
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD TÍTULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 229');
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 283');
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		'NONE USUÁRIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 303');	
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		'TOTAL COPART',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
		
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 335');	
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
		'NR_LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		'LOCAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 361');	
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		'DT NASCIMENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 387');	
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		'CPF DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 257');
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	/* NAO-LOCALIZADOS */
	
	call PROC_LOG_MESSAGE('LINHA - 1522');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_DESCONHECIDO_HOC',
		'Não Localizados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 1540');
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD-TITULAR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1540');
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		'COD-DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;	
	call PROC_LOG_MESSAGE('LINHA - 1566');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'NR_CPF',
		'CPF',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;	
	call PROC_LOG_MESSAGE('LINHA - 1566');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'NR_LOCAL',
		'LOCAL',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_LOCAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;	
	call PROC_LOG_MESSAGE('LINHA - 2313');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'NR_RDP',
		'RDP',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_RDP,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1592');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'NM_BENEFICIARIO',
		'NOME BENEFICIÁRIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1592');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'DT_NASCIMENTO',
		'DT NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1592');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'DT_ADMISSAO',
		'DT ADMISSAO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_ADMISSAO,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1592');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'DT_DEMISSAO',
		'DT DEMISSAO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_DEMISSAO,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1618');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
		ID_VIEW_DESTINATION	,
		NM_COLUMN,
		NM_COL_TITLE_LABEL,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_VIEW_DESTINATION,
		'VL_PRINCIPAL',
		'VALOR PRINCIPAL',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
	
	call PROC_LOG_MESSAGE('LINHA - 2248');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
