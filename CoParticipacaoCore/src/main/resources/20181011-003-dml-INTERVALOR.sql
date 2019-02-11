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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180921-001-dml-CARGILL-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181011-003-dml-INTERVALOR.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 2;	
	declare VAR_USE_TYPE_NAO_LOCALIZADO		int( 3 ) default 3;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 4;
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 5;
	declare VAR_USE_TYPE_EXTRA_FILE			int( 3 ) default 6;
	
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO		int( 3 ) default 0;
	declare VAR_TP_REPORT_QUERY_BY_PERIODO					int( 3 ) default 1;
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO					int( 3 ) default 2;
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	
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

	declare VAR_ID_COLUMN_01_NUM_LINHA				bigint( 17 );
	declare VAR_ID_COLUMN_02_CD_PREFIXO_EMPRESA		bigint( 17 );
	declare VAR_ID_COLUMN_03						bigint( 17 );
	declare VAR_ID_COLUMN_04						bigint( 17 );
	declare VAR_ID_COLUMN_05						bigint( 17 );
	declare VAR_ID_COLUMN_06						bigint( 17 );
	declare VAR_ID_COLUMN_07_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08						bigint( 17 );
	declare VAR_ID_COLUMN_09_NR_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_10_CD_MES					bigint( 17 );
	declare VAR_ID_COLUMN_11_EMPTY					bigint( 17 );
	declare VAR_ID_COLUMN_12_CD_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_13_TP_VALOR				bigint( 17 );
	declare VAR_ID_COLUMN_14_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_15_CD_CONTRATO			bigint( 17 );
	declare VAR_ID_COLUMN_16_DT_NASCIMENTO			bigint( 17 );
    declare VAR_ID_COLUMN_17						bigint( 17 );
	declare VAR_ID_COLUMN_18						bigint( 17 );
	declare VAR_ID_COLUMN_19						bigint( 17 );
	declare VAR_ID_COLUMN_20						bigint( 17 );
	declare VAR_ID_COLUMN_21_NM_TITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_22_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_23_EMPTY					bigint( 17 );

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
	
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA							int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_CD_EMPRESA								int( 3 ) default 15;
	declare VAR_COL_VIEW_LENGTH_CD_PREFIXO_EMPRESA						int( 3 ) default 15;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 30;
	declare VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO							int( 3 ) default 30;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 15;
	declare VAR_COL_VIEW_LENGTH_CD_UNIDADE								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_DT_NASCIMENTO							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_CPF_BENEFICIARIO						int( 3 ) default 20;

	declare VAR_COL_LABEL_NM_TITULAR									varchar( 60 ) default 'NOME TÍTULAR';
	declare VAR_COL_LABEL_NM_BENEFICIARIO								varchar( 60 ) default 'NOME DEPENDENTE';
	declare VAR_COL_LABEL_VL_PRINCIPAL									varchar( 60 ) default 'VALOR COPART';
	declare VAR_COL_LABEL_CD_EMPRESA									varchar( 60 ) default 'CÓDIGO EMPRESA';
	declare VAR_COL_LABEL_CD_PREFIXO_EMPRESA							varchar( 60 ) default 'PREFIXO EMPRESA';
	declare VAR_COL_LABEL_CD_UNIDADE									varchar( 60 ) default 'UNIDADE';
	declare VAR_COL_LABEL_DT_NASCIMENTO									varchar( 60 ) default 'DATA NASCIMENTO';
	declare VAR_COL_LABEL_NR_CPF_BENEFICIARIO							varchar( 60 ) default 'CPF DEPENDENTE';
	declare VAR_COL_LABEL_NR_MATRICULA									varchar( 60 ) default 'MATRICULA';
	
	declare VAR_COL_LANCAMENTO_NR_MATRICULA_DEPENDENTE					bigint( 17 ) default 1;
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 ) default 2;
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 ) default 3;
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 ) default 4;
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 ) default 5;
	declare VAR_COL_LANCAMENTO_DT_MOVIMENTO								bigint( 17 ) default 6;
	declare VAR_COL_LANCAMENTO_TP_VALOR									bigint( 17 ) default 7;
	declare VAR_COL_LANCAMENTO_NR_MATRICULA_TITULAR						bigint( 17 ) default 8;
	declare VAR_COL_LANCAMENTO_NR_CPF									bigint( 17 ) default 9;	
	declare VAR_COL_LANCAMENTO_NM_BENEFICIARIO							bigint( 17 ) default 10;

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
	
	declare VAR_NR_MATRICULA_BASE										bigint( 17 ) default 19200000000000;
	
	declare VAR_NM_CONTRATO_PRN											varchar( 400 ) default 'Arquivo PRN';
	
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
    call PROC_LOG_MESSAGE('LINHA - 149');
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,	
		CD_EMPRESA,
		CD_AUTOMATIC_CREATE_BENEFICIARIO,
		CD_OUTPUT_REPORT_DIR,		
        CD_INPUT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,
		TP_EXTERNAL_PROCESS,
		TP_REPORT_QUERY,
		CD_ACCEPT_TITULAR_WITHOUT_CPF,
		TP_REPORT_LAYOUT_TYPE,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA,
		'Intervalor',
		'INTERVALOR',
		VAR_FALSE,
		'/home/eapereira/desenv/work/coparticipacao/intervalor/output-reports',
        '/home/eapereira/desenv/work/coparticipacao/intervalor/input/',
        '/home/eapereira/desenv/work/coparticipacao/intervalor/failure/',
        '/home/eapereira/desenv/work/coparticipacao/intervalor/output/',
        VAR_FALSE,
        VAR_FALSE,		
        VAR_FALSE,
        VAR_TP_REPORT_QUERY_BY_CONTRATO,
        VAR_TRUE,
        VAR_TP_REPORT_LAYOUT_TYPE_SINGLE_CONTRATO,
		
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
		'8EAPM',
	    '8EAPM',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;

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
		'8EAQG',
	    '8EAQG',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
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
		'8EAQR',
	    '8EAQR',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
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
		'8EAQS',
	    '8EAQS',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
	call PROC_LOG_MESSAGE('LINHA - 284');	
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
    call PROC_LOG_MESSAGE('LINHA - 173');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = 'INTERVALOR';
        
    call PROC_LOG_MESSAGE('LINHA - 177');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA 	= VAR_ID_EMPRESA
	and		CD_CONTRATO = '8EAPM';
	
	call PROC_LOG_MESSAGE('LINHA - 182');
	update TB_EMPRESA
	set
		CD_AUTOMATIC_CREATE_BENEFICIARIO	= 1,
		CD_INPUT_DIR						= '/home/eapereira/desenv/work/coparticipacao/input/',
		CD_OUTPUT_REPORT_DIR				= '/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/intervalor/',
		TP_EXTERNAL_PROCESS					= 0,
		TP_REPORT_QUERY						= 2,
		CD_ACCEPT_TITULAR_WITHOUT_CPF		= VAR_TRUE
	where	ID	= VAR_ID_EMPRESA;

	call PROC_LOG_MESSAGE('LINHA - 192');
	/*****************************************************************************************************************/
	/*****************************************************************************************************************/		
	call PROC_LOG_MESSAGE('LINHA - 292');
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
	
	call PROC_LOG_MESSAGE('LINHA - 192');
	/*****************************************************************************************************************/
	/*****************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 197');
	/* VIEW-DESTINATION */

	call PROC_LOG_MESSAGE('LINHA - 230');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_COPARTICIPACAO_INTERVALOR',
		'INTERVALOR',
		
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
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'CD_EMPRESA',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_CD_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'CD_PREFIXO_EMPRESA',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_CD_PREFIXO_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_PREFIXO_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 537');
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
		VAR_COL_LABEL_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 563');
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
		'NR_CPF_BENEFICIARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_CPF_BENEFICIARIO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CPF_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 589');
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
		'CD_UNIDADE',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_CD_UNIDADE,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_UNIDADE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 589');
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
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 797');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	/* NAO-LOCALIZADOS */
	
	call PROC_LOG_MESSAGE('LINHA - 801');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_DESCONHECIDO_INTERVALOR',
		'Não Localizados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 820');
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
		VAR_COL_LABEL_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 846');
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
		VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 846');
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
		VAR_COL_VIEW_LENGTH_NR_CPF_BENEFICIARIO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CPF_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 846');
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
		VAR_COL_LABEL_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 872');	
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
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 1030');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
