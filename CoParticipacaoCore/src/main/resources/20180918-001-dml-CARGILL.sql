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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180915-007-dml-HOC-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180918-001-dml-CARGILL.sql';
	
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
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO				bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	
    declare VAR_COL_VIEW_LENGTH_CD_EMPRESA								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA					int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_PLANO								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_NR_CPF									int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_ADMISSAO								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_TP_SINAL								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_CD_VERBA								int( 3 ) default 10;

    declare VAR_COL_LABEL_CD_EMPRESA									varchar( 40 ) default 'EMPRESA';
	declare VAR_COL_LABEL_NR_MATRICULA_EMPRESA							varchar( 40 ) default 'MATRICULA EMPRESA';
	declare VAR_COL_LABEL_NR_MATRICULA									varchar( 40 ) default 'MATRICULA';
	declare VAR_COL_LABEL_CD_PLANO										varchar( 40 ) default 'PLANO';
	declare VAR_COL_LABEL_NR_CPF										varchar( 40 ) default 'CPF';
	declare VAR_COL_LABEL_NM_TITULAR									varchar( 40 ) default 'NOME TÍTULAR';
	declare VAR_COL_LABEL_NM_BENEFICIARIO								varchar( 40 ) default 'BENEFICIÁRIO';
	declare VAR_COL_LABEL_VL_PRINCIPAL									varchar( 40 ) default 'TOTAL';
	declare VAR_COL_LABEL_DT_ADMISSAO									varchar( 40 ) default 'DT ADMISSÃO';
	declare VAR_COL_LABEL_TP_SINAL										varchar( 40 ) default 'SINAL';
	declare VAR_COL_LABEL_CD_VERBA										varchar( 40 ) default 'COD VERBA';
	
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
	declare VAR_NM_CONTRATO_PRN											varchar( 400 ) default 'Arquivo PRN';
	
	declare VAR_ID_CONTRATO_00192										bigint( 17 );
	declare VAR_ID_CONTRATO_00196										bigint( 17 );
	declare VAR_ID_CONTRATO_00197										bigint( 17 );
	
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
    call PROC_LOG_MESSAGE('LINHA - 232');
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
		TP_REPORT_QUERY,			
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA,
		'CARGILL',
		'CARGILL',
		VAR_FALSE,
		'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/cargill/',
        '/home/eapereira/desenv/work/coparticipacao/input/',
        '/home/eapereira/desenv/work/coparticipacao/failure/',
        '/home/eapereira/desenv/work/coparticipacao/output/',
        VAR_FALSE,
        VAR_FALSE,
        VAR_TP_REPORT_QUERY_BY_PERIODO_ONLY,		
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00192',
	    '00192',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_00192
	from TB_CONTRATO;

	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00196',
	    '00196',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_00196
	from TB_CONTRATO;

	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00195',
	    '00195',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO
	from TB_CONTRATO;
	
	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00197',
	    '00197',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_00197 
	from TB_CONTRATO;

	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00270',
	    '00270',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'00297',
	    '00297',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
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

	call PROC_LOG_MESSAGE('LINHA - 268');
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
	    'MECSAS2',
	    VAR_NM_CONTRATO_MECSAS2,
	    VAR_USE_TYPE_MECSAS2,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;
	
	call PROC_LOG_MESSAGE('LINHA - 364');
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

	call PROC_LOG_MESSAGE('LINHA - 364');
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
		'INATIVO',
		'INATIVO',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_USE_TYPE_ISENTO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
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
	    VAR_TRUE,
	    VAR_USE_TYPE_NAO_LOCALIZADO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
		
	call PROC_LOG_MESSAGE('LINHA - 290');
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
		'00192-PRN',
	    '00192-PRN',
	    VAR_NM_CONTRATO_PRN,
	    VAR_FALSE,
	    VAR_USE_TYPE_EXTRA_FILE,
	    VAR_ID_CONTRATO_00192,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	

	call PROC_LOG_MESSAGE('LINHA - 340');
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
		'00196-PRN',
	    '00196-PRN',
	    VAR_NM_CONTRATO_PRN,
	    VAR_FALSE,
	    VAR_USE_TYPE_EXTRA_FILE,
	    VAR_ID_CONTRATO_00196,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 364');
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
		'00197-PRN',
	    '00197-PRN',
	    VAR_NM_CONTRATO_PRN,
	    VAR_FALSE,
	    VAR_USE_TYPE_EXTRA_FILE,
	    VAR_ID_CONTRATO_00197,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 314');	
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
		'VW_COPARTICIPACAO_CARGILL',
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
		'CD_CONTRATO',
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
		'NR_MATRICULA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA_EMPRESA,
		
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
		'NR_CPF',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CPF,
		
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
		'CD_VERBA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_VERBA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_VERBA,
		
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
		'CD_PLANO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_PLANO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_PLANO,
		
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
		'TP_SINAL',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_TP_SINAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_TP_SINAL,
		
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
		'DT_ADMISSAO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_ADMISSAO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_DT_ADMISSAO,
		
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
		'VL_DESCONTO',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 459');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_PRN_CARGILL',
		'Coparticipação',
		
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
		'CD_CONTRATO',
		VAR_COL_INT,
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
		'NR_MATRICULA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA_EMPRESA,
		
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
		'CD_PLANO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_PLANO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_PLANO,
		
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
		'CD_VERBA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_VERBA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_VERBA,
		
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
		'VL_DESCONTO',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_PRINCIPAL,
		
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
		'TP_SINAL',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_TP_SINAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_TP_SINAL,
		
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
		'VW_DESCONHECIDO_CARGILL',
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
		'CD_CONTRATO',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_CD_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_EMPRESA,
		
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
		'NR_CPF',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CPF,
		
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
	
	call PROC_LOG_MESSAGE('LINHA - 898');
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
		'CD_VERBA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_VERBA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_VERBA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 924');
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
		'CD_PLANO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_PLANO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_PLANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 950');
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
		'DT_ADMISSAO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_ADMISSAO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_DT_ADMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1172');
	/*********************************************************************************************************************************************/
	    		
	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17257',
		1,					
		'BASICO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17258',
		2,					
		'ESPECIAL 1',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17259',
		3,					
		'ESPECIAL 2',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17260',
		4,					
		'EXECUTIVO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
		
	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17261',
		1,					
		'BASICO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17262',
		2,					
		'ESPECIAL 1',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17263',
		3,					
		'ESPECIAL 2',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17264',
		4,					
		'EXECUTIVO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1174');
	insert into TB_PLANO_CARGILL(
		CD_PLANO,
		CD_CARGILL,
		NM_PLANO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'17268',
		4,					
		'EXECUTIVO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);		
			
	call PROC_LOG_MESSAGE('LINHA - 950');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
