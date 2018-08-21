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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default 'dml-20180808-006-abbvie-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default 'dml-20180810-001-HOC.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
  	declare VAR_CD_ORDEM					int( 3 ) default 0;
  	
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
	declare VAR_COL_VIEW_LENGTH_NM_LOCAL								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_NASCIMENTO							int( 3 ) default 20;
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
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA,
		'Hospital Oswaldo Cruz',
		'HOC',
		VAR_FALSE,
		'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/hoc/',
		
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
        TP_USO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'FATUCOPA-HOC',
	    'Hospital Oswaldo Cruz',
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
	    TP_USO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'MECSAS-HOC',
	    'Hospital Oswaldo Cruz',
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
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ISENTO-HOC',
	    'Hospital Oswaldo Cruz',
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
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'naoLocalizados.HOC',
	    'NAO-LOCALIZADO',
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
		1,
		'DESCRIÇÃO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
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
		2,
		'VALOR',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
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
		1,
		'COD. TITULAR',
		
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		2,
		'COD. DEPENDENTE',
		
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		3,
		'NOME DO USUARIO',
		
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		4,
		'TOTAL COPARTICIPAÇAO',
		
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		5,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		6,
		'CPF_DEPENDENTE',
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		7,
		'NR_MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
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
		1,
		'NR_MATRICULA',
		
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
		'NM_USUARIO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'SEGURADA',
		
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
		'TOTAL_COPART',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		3,
		'VALOR_ISENTO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
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
		1,
		'COD_DEPENDENTE',
		
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NOME DO USUÁRIO',
		
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		3,
		'TOTAL COPART.',
		
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		4,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		5,
		'CPF_DEPENDENTE',
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		6,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			
		
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
		1,
		'COD_TITULAR',
		
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		2,
		'COD_DEPENDENTE',
		
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		3,
		'NONE USUÁRIO',
		
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		4,
		'TOTAL COPART',
		
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
		'LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NM_LOCAL,
		5,
		'LOCAL',
		
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
		'DT_NASCIMENTO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		6,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		7,
		'CPF_DEPENDENTE',
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		8,
		'MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NONE USUÁRIO',
		
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		3,
		'TOTAL COPART',
		
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
		'LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NM_LOCAL,
		4,
		'LOCAL',
		
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		5,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		6,
		'CPF',
		
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
		'VW_PLANO_EXTENSAO_HOC',
		'Plano de Extenção',
		
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
		'COD_TITULAR',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		1,
		'COD. TITULAR',
		
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
		'COD_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		2,
		'COD_DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
		
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
		3,
		'NONE_USUARIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
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
		4,
		'TOTAL_COPART',
		
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
		'LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NM_LOCAL,
		5,
		'LOCAL',
		
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		6,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		7,
		'CPF_DEPENDENTE',
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		8,
		'MATRICULA',
		
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
		'VW_DESLIGADOS_HOC',
		'Desligados',
		
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
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
		'NM_USUARIO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NONE USUÁRIO',
		
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
		'TOTAL_COPART',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		3,
		'TOTAL COPART',
		
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
		'LOCAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NM_LOCAL,
		4,
		'LOCAL',
		
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
		'DT_NASCIMENTO',
		VAR_COL_DATE,
		VAR_COL_VIEW_LENGTH_DT_NASCIMENTO,
		5,
		'DT_NASCIMENTO',
		
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
		'CPF_DEPENDENTE',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_CPF,
		6,
		'CPF',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
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
		1,
		'NR_MATRICULA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
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
		2,
		'VL_PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);			

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
		'Desligados',
		
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
	
	call PROC_LOG_MESSAGE('LINHA - 1642');
	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
