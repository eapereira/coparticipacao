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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180703-004-dml-muito-facil-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180724-001-dml-marjan.sql';
	
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
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO		int( 3 ) default 0;
	declare VAR_TP_REPORT_QUERY_BY_PERIODO_ONLY				int( 3 ) default 1;
	declare VAR_TP_REPORT_QUERY_BY_SINGLE_CONTRATO			int( 3 ) default 2;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA		bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 					bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	DECLARE VAR_ID_CONTRATO_FATUCOPA 		bigint( 17 );
	DECLARE VAR_ID_CONTRATO_MECSAS 			bigint( 17 );
	DECLARE VAR_ID_CONTRATO_ISENTOS 		bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z8			bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_02_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_03_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_04_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_05_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_06_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_07_8C5Z8_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_09_8C5Z8_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_10_8C5Z8_MES					bigint( 17 );
	declare VAR_ID_COLUMN_11_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_12_8C5Z8_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_13_8C5Z8_POSITIVO_NEGATIVO	bigint( 17 );
	declare VAR_ID_COLUMN_14_8C5Z8_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_15_8C5Z8_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_16_8C5Z8_DT_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_17_8C5Z8						bigint( 17 );
	declare VAR_ID_COLUMN_18_8C5Z8_NM_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_19_8C5Z8_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_20_8C5Z8						bigint( 17 );


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
	declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_DIRETORIA							bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION_DEMITIDO							bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_NR_CPF_TITULAR							int( 3 ) default 20;
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
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 12;
	
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'Base de Ativos do Cliente';
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção';
	
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
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,
        CD_EMPRESA,
		CD_AUTOMATIC_CREATE_BENEFICIARIO,
		CD_AUTOMATIC_CREATE_TITULAR,
		CD_SEARCH_BENEFICIARIO_NONAME,
		CD_OUTPUT_REPORT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        CD_INPUT_DIR,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,
		TP_REPORT_QUERY,	
		CD_CREATE_BENEFICIARIO_FROM_MECSAS2,	
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA, /* Sulamerica */
		'Marjan',
        'MARJAN',
		VAR_FALSE,
		VAR_TRUE,
		VAR_TRUE,
		'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/marjan/',
        '/home/eapereira/desenv/work/coparticipacao/input/',
        '/home/eapereira/desenv/work/coparticipacao/failure/',
        '/home/eapereira/desenv/work/coparticipacao/output/',
        VAR_FALSE,
        VAR_FALSE,		
        VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO,
        VAR_TRUE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	call PROC_LOG_MESSAGE('LINHA - 246');
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

	call PROC_LOG_MESSAGE('LINHA - 194');
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
		'MECSAS2',
		'MECSAS.2',
	    VAR_NM_CONTRATO_MECSAS2,
	    VAR_USE_TYPE_MECSAS2,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	call PROC_LOG_MESSAGE('LINHA - 214');
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
		'ISENTO',
		'ISENTO',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_USE_TYPE_ISENTO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
    call PROC_LOG_MESSAGE('LINHA - 234');
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
		'8C5Z8',
	    'DROXTER',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
        VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
    call PROC_LOG_MESSAGE('LINHA - 254');
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
		'8C5Z9',
	    'INSTITUTO',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
        VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
    call PROC_LOG_MESSAGE('LINHA - 274');
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
		'8C7XX',
	    'MARJAN',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
        VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
    call PROC_LOG_MESSAGE('LINHA - 294');
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
		'8C7XY',
	    'Sérgio Vasconcelos',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 315');
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
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_NAO_LOCALIZADO,
	    VAR_TRUE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/    
    call PROC_LOG_MESSAGE('LINHA - 153');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_COPARTICIPACAO_TELA_MARJAN',
		'copart',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_COPART from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION_COPART,
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
		VAR_ID_VIEW_DESTINATION_COPART,
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
		VAR_ID_VIEW_DESTINATION_COPART,
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
		VAR_ID_VIEW_DESTINATION_COPART,
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
		VAR_ID_VIEW_DESTINATION_COPART,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENTOS_FILHOS_MARJAN',
		'Isentos filhos menores de 12 meses',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_FILHOS from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION_FILHOS,
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
		VAR_ID_VIEW_DESTINATION_FILHOS,
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
		VAR_ID_VIEW_DESTINATION_FILHOS,
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
		VAR_ID_VIEW_DESTINATION_FILHOS,
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
		VAR_ID_VIEW_DESTINATION_FILHOS,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
		
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENTOS_GESTANTE_MARJAN',
		'Isentos Gestante',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_GESTANTE from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION_GESTANTE,
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
		VAR_ID_VIEW_DESTINATION_GESTANTE,
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
		VAR_ID_VIEW_DESTINATION_GESTANTE,
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
		VAR_ID_VIEW_DESTINATION_GESTANTE,
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
		VAR_ID_VIEW_DESTINATION_GESTANTE,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_ISENTOS_ESTAGIARIO_MARJAN',
		'Isentos Estagiário',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION_ESTAGIARIO from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
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
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
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
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
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
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
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
		VAR_ID_VIEW_DESTINATION_ESTAGIARIO,
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
		    	
	call PROC_LOG_MESSAGE('LINHA - 1174');
    /***********************************************************************************************************************************************/
    /* NAO LOCALIZADOS */
	
	set VAR_CD_ORDEM = 0;
	
    call PROC_LOG_MESSAGE('LINHA - 851');
    insert into TB_VIEW_DESTINATION(
        NM_VIEW,
        NM_TITLE_LABEL,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'VW_DESCONHECIDO_MARJAN',
        'NAO-LOCALIZADOS',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
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
        'NR_MATRICULA_TITULAR',
        VAR_COL_LONG,
        VAR_COL_VIEW_LENGTH_ID_TITULAR,
        VAR_CD_ORDEM,
        'ID TITULAR',
        
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
        'NR_CPF_TITULAR',
        VAR_COL_LONG,
        VAR_COL_VIEW_LENGTH_NR_CPF_TITULAR,
        VAR_CD_ORDEM,
        'CPF TITULAR',
        
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
        'NOME TITULAR',
        
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
        'NR_MATRICULA_DEPENDENTE',
        VAR_COL_LONG,
        VAR_COL_VIEW_LENGTH_ID_DEPENDENTE,
        VAR_CD_ORDEM,
        'ID DEPENDENTE',
        
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
        'NM_DEPENDENTE',
        VAR_COL_VARCHAR,
        VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
        VAR_CD_ORDEM,
        'NOME DEPENDENTE',
        
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
        'DT_DEMISSAO',
        VAR_COL_VARCHAR,
        VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
        VAR_CD_ORDEM,
        'DEMITIDOS',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
	call PROC_LOG_MESSAGE('LINHA - 1076');    
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
