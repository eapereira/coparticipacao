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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180810-001-dml-HOC.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180810-002-dml-HOC-0444.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
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
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	
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

	declare VAR_ID_COLUMN_01_COD 					bigint( 17 );
	declare VAR_ID_COLUMN_02_COD_REF 				bigint( 17 );
	declare VAR_ID_COLUMN_03_PREF 					bigint( 17 );
	declare VAR_ID_COLUMN_04_COD_TITULAR 			bigint( 17 );
	declare VAR_ID_COLUMN_05_COD_DEPENDENTE 		bigint( 17 );
	declare VAR_ID_COLUMN_06_NM_DEPENDENTE 			bigint( 17 );
	declare VAR_ID_COLUMN_07_VL_PRINCIPAL 			bigint( 17 );
	declare VAR_ID_COLUMN_08_SETOR 					bigint( 17 );
	declare VAR_ID_COLUMN_09_LOCAL 					bigint( 17 );
	declare VAR_ID_COLUMN_10_DT_NASCIMENTO 			bigint( 17 );
	declare VAR_ID_COLUMN_11 						bigint( 17 );
	declare VAR_ID_COLUMN_12_CPF_DEPENDENTE 		bigint( 17 );
	declare VAR_ID_COLUMN_13_EMPRESA 				bigint( 17 );
	declare VAR_ID_COLUMN_14 						bigint( 17 );
	declare VAR_ID_COLUMN_15 						bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_RESUMO_HOC 							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ORIGINAL_HOC 						bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ISENCAO_GESTANTES_HOC 				bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ISENCAO_CONSELHEIROS_HOC 			bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_AFASTADOS_HOC		 				bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_AGREGADOS_HOC 						bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_PLANO_EXTENSAO_HOC 					bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_DESLIGADOS_HOC 						bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_PRN_HOC 							bigint( 17 );
	
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
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 12;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 13;
	
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
	
	declare VAR_NR_MATRICULA_BASE											bigint( 17 ) default 44400000000000;
	
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
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = '0444';
    
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = '0444'; 

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* FATU-COPA */
	
	call PROC_LOG_MESSAGE('LINHA - 162');
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
		'^(0444)\\.(0444)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(csv|CSV)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_CSV,
		VAR_USE_TYPE_FATUCOPA,
		3,
		null,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	
	call PROC_LOG_MESSAGE('LINHA - 200');
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
		'COLUMN_01_COD',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_COD from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 224');
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
		'COLUMN_02_COD_REF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_COD_REF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 248');
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
		'COLUMN_03_PREF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
	select max( ID ) into VAR_ID_COLUMN_03_PREF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 272');
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
		'COLUMN_04_COD_TITULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
	select max( ID ) into VAR_ID_COLUMN_04_COD_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 296');
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
		'COLUMN_05_COD_DEPENDENTE',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_COD_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 320');
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
		'COLUMN_06_NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_NM_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 344');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_07_VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		null,
		'#0.00',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 370');
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
		'COLUMN_08_SETOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_SETOR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 394');
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
		'COLUMN_09_LOCAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 418');
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
		'COLUMN_10_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 442');
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
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11 from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 466');
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
		'COLUMN_12_CPF_DEPENDENTE',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_CPF_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 489');
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
		'COLUMN_13_EMPRESA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 514');
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
		'COLUMN_14',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14 from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 538');
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
		'COLUMN_15',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15 from TB_ARQUIVO_INPUT_COLS_DEF;	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
	
	call PROC_LOG_MESSAGE('LINHA - 588');
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
		VAR_ID_COLUMN_12_CPF_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
    call PROC_LOG_MESSAGE('LINHA - 607');
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
		 VAR_ID_COLUMN_13_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);

	call PROC_LOG_MESSAGE('LINHA - 626');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_VL_PRINCIPAL,
		VAR_ID_COLUMN_07_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 225');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	/* BENEFICIARIO */
	
    call PROC_LOG_MESSAGE('LINHA - 649');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_05_COD_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 681');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_06_NM_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 728');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_12_CPF_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 743');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_10_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 759');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/		
	/* NAO-LOCALIZADOS */
	
    call PROC_LOG_MESSAGE('LINHA - 780');
	select ID into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DESCONHECIDO_HOC';
	
	call PROC_LOG_MESSAGE('LINHA - 784');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		NM_DESCR_ARQUIVO,	
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'NAO-LOCALIZADO-HOC-{YYYY}{MM}.xlsx',
		'Arquivo com os beneficiários não localizados HOC',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
		
	call PROC_LOG_MESSAGE('LINHA - 804');
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
	
	call PROC_LOG_MESSAGE('LINHA - 808');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	/* REGRAS */
	
	call PROC_LOG_MESSAGE('LINHA - 820');
	insert into TB_REGRA(
		NM_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_ARQUIVO_INPUT,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'HOC.8C5Z8 - Regra para subtrair o NR_MATRICULA_TITULAR do beneficiário por 44400000000000',
		VAR_TP_REGRA_SIMPLES,
		0,
		VAR_ID_ARQUIVO_INPUT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 842');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_TP_REGRA_OPERATION_SUBSTRACT,
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
		VAR_ID_COLUMN_04_COD_TITULAR,
		
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
		VAR_NR_MATRICULA_BASE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 919');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_TP_REGRA_OPERATION_DIVIDE,
		1,
		
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
		VAR_ID_COLUMN_04_COD_TITULAR,
		
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
		1000,
		
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
		VAR_ID_COLUMN_04_COD_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
				
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 911');
	insert into TB_REGRA(
		NM_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_ARQUIVO_INPUT,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'HOC.8C5Z8 - Regra para subtrair o NR_MATRICULA_DEPENDENTE do beneficiário por 44400000000000',
		VAR_TP_REGRA_SIMPLES,
		0,
		VAR_ID_ARQUIVO_INPUT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 842');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_TP_REGRA_OPERATION_SUBSTRACT,
		0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 853');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_05_COD_DEPENDENTE,
		
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
		VAR_NR_MATRICULA_BASE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 919');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_TP_REGRA_OPERATION_DIVIDE,
		1,
		
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
		VAR_ID_COLUMN_05_COD_DEPENDENTE,
		
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
		1000,
		
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
		VAR_ID_COLUMN_05_COD_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
			
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* OUTPUT-FILE*/
	
	call PROC_LOG_MESSAGE('LINHA - 1150');
	select ID into VAR_ID_VIEW_DESTINATION_RESUMO_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_RESUMO_HOC';
	
	select ID into VAR_ID_VIEW_DESTINATION_ORIGINAL_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_LANCAMENTO_ORIGINAL_HOC';

	select ID into VAR_ID_VIEW_DESTINATION_ISENCAO_GESTANTES_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_ISENCAO_GESTANTES_HOC';

	select ID into VAR_ID_VIEW_DESTINATION_ISENCAO_CONSELHEIROS_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_ISENCAO_CONSELHEIROS_HOC';

	select ID into VAR_ID_VIEW_DESTINATION_AFASTADOS_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_AFASTADOS_HOC';
	
	select ID into VAR_ID_VIEW_DESTINATION_AGREGADOS_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_AGREGADOS_HOC';
                
	select ID into VAR_ID_VIEW_DESTINATION_PLANO_EXTENSAO_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_PLANO_EXTENSAO_HOC';
                
	select ID into VAR_ID_VIEW_DESTINATION_DESLIGADOS_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_DESLIGADOS_HOC';
                
	select ID into VAR_ID_VIEW_DESTINATION_PRN_HOC from TB_VIEW_DESTINATION
	where NM_VIEW = 'VW_PRN_HOC';
	
	/*********************************************************************************************************************************************/
	
	call PROC_LOG_MESSAGE('LINHA - 1179');
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'HOC-SAS_(Saúde)_Coparticipacao_({YYYY}_{MM}).xlsx',
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
		VAR_ID_VIEW_DESTINATION_RESUMO_HOC,
		'ESPELHO',
		
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
		VAR_ID_VIEW_DESTINATION_ORIGINAL_HOC,
		'COPART ORIGINAL',
		
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
		VAR_ID_VIEW_DESTINATION_ISENCAO_GESTANTES_HOC,
		'ISENÇÃO GESTANTES',
		
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
		VAR_ID_VIEW_DESTINATION_ISENCAO_CONSELHEIROS_HOC,
		'ISENÇÃO CONSELHO',
		
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
		VAR_ID_VIEW_DESTINATION_AFASTADOS_HOC,
		'AFASTADOS',
		
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
		VAR_ID_VIEW_DESTINATION_AGREGADOS_HOC,
		'AGREGADOS',
		
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
		VAR_ID_VIEW_DESTINATION_PLANO_EXTENSAO_HOC,
		'PLANO EXTENSÃO',
		
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
		VAR_ID_VIEW_DESTINATION_DESLIGADOS_HOC,
		'DESLIGADOS',
		
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
		VAR_ID_VIEW_DESTINATION_PRN_HOC,
		'PRN',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 1225');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
