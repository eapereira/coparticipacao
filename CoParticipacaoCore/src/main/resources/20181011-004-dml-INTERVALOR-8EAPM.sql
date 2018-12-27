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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20181011-003-dml-INTERVALOR.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181011-004-dml-INTERVALOR-8EAPM.sql';
	
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
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	declare VAR_DT_FORMAT					varchar( 10 ) default 'dd/MM/yyyy';
	
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
	declare VAR_ID_ARQUIVO_INPUT_SHEET				bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01_NUM_LINHA				bigint( 17 );
	declare VAR_ID_COLUMN_02_CD_PREFIXO_EMPRESA		bigint( 17 );
	declare VAR_ID_COLUMN_03						bigint( 17 );
	declare VAR_ID_COLUMN_04_NR_MATRICULA			bigint( 17 );
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
	declare VAR_ID_COLUMN_18_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_19						bigint( 17 );
	declare VAR_ID_COLUMN_20						bigint( 17 );
	declare VAR_ID_COLUMN_21_NM_TITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_22						bigint( 17 );
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
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;

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
	declare VAR_COL_LANCAMENTO_NM_TITULAR								bigint( 17 ) default 11;
	declare VAR_COL_LANCAMENTO_DT_NASCIMENTO							bigint( 17 ) default 12;

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
	
	declare VAR_CD_SHEET												bigint( 17 ) default 0;
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
    call PROC_LOG_MESSAGE('LINHA - 150');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = 'INTERVALOR';
        
    call PROC_LOG_MESSAGE('LINHA - 162');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA 	= VAR_ID_EMPRESA
	and		CD_CONTRATO = '8EAPM';
	
	call PROC_LOG_MESSAGE('LINHA - 167');
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
		'^(INTERVALOR)\\.(8EAPM)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(txt|TXT)$',
		VAR_NM_CONTRATO_COPARTICIPACAO,
		VAR_ARQUIVO_TYPE_FLATFILE,
		VAR_USE_TYPE_FATUCOPA,
		1,
		182,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	/*****************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************/
	/* FATUCOPA  */
	call PROC_LOG_MESSAGE('LINHA - 234');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;	

	/*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 197');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_01_NUM_LINHA',
		VAR_COL_INT,
		2,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_NUM_LINHA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 223');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_02_CD_PREFIXO_EMPRESA',
		VAR_COL_INT,
		1,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_CD_PREFIXO_EMPRESA
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 249');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_03',
		VAR_COL_INT,
		4,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 275');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_04_NR_MATRICULA',
		VAR_COL_LONG,
		8,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 301');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_05',
		VAR_COL_INT,
		2,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 327');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_06',
		VAR_COL_INT,
		1,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 353');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_07_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		32,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 379');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_08',
		VAR_COL_VARCHAR,
		6,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 405');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_09_NR_CPF',
		VAR_COL_LONG,
		12,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_NR_CPF 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 431');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_10_CD_MES',
		VAR_COL_INT,
		2,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_CD_MES 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 457');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_11_EMPTY',
		VAR_COL_VARCHAR,
		1,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_EMPTY 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 483');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_12_CD_ANO',
		VAR_COL_INT,
		4,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_CD_ANO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 509');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_13_TP_VALOR',
		VAR_COL_VARCHAR,
		1,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_TP_VALOR 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 535');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_14_VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		15,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_VL_PRINCIPAL 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 561');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_15_CD_CONTRATO',
		VAR_COL_VARCHAR,
		5,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_CD_CONTRATO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 507');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_16_DT_NASCIMENTO',
		VAR_COL_DATE,
		10,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_DT_NASCIMENTO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 613');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_17',
		VAR_COL_INT,
		4,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 639');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_18_NR_MATRICULA',
		VAR_COL_LONG,
		8,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 665');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_19',
		VAR_COL_INT,
		2,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 691');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_20',
		VAR_COL_INT,
		1,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 717');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_21_NM_TITULAR',
		VAR_COL_VARCHAR,
		32,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21_NM_TITULAR 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	
	
	call PROC_LOG_MESSAGE('LINHA - 743');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_22',
		VAR_COL_LONG,
		9,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 769');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_23_EMPTY',
		VAR_COL_VARCHAR,
		20,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_EMPTY 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 	

	call PROC_LOG_MESSAGE('LINHA - 795');
	/*****************************************************************************************************************/
	/*****************************************************************************************************************/
	/* FATU-COPA.1 */
	    
    call PROC_LOG_MESSAGE('LINHA - 800');
    insert into TB_LANCAMENTO_INPUT (
        ID_ARQUIVO_INPUT_SHEET,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_INPUT_SHEET,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );
    
    select max( ID ) into VAR_ID_LANCAMENTO_INPUT from TB_LANCAMENTO_INPUT;
	
	call PROC_LOG_MESSAGE('LINHA - 816');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_NR_MATRICULA_DEPENDENTE,
		VAR_ID_COLUMN_18_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
    call PROC_LOG_MESSAGE('LINHA - 835');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_TP_VALOR,
		VAR_ID_COLUMN_13_TP_VALOR,		
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
	
	call PROC_LOG_MESSAGE('LINHA - 853');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_VL_PRINCIPAL,
		VAR_ID_COLUMN_14_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 871');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_ID_CONTRATO,
		VAR_ID_COLUMN_15_CD_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 889');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_CD_MES,
		VAR_ID_COLUMN_10_CD_MES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 907');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_CD_ANO,
		VAR_ID_COLUMN_12_CD_ANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 925');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_NR_CPF,
		VAR_ID_COLUMN_09_NR_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 943');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_NM_BENEFICIARIO,
		VAR_ID_COLUMN_07_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 962');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_NM_TITULAR,
		VAR_ID_COLUMN_21_NM_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 961');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_NR_MATRICULA_TITULAR,
		VAR_ID_COLUMN_18_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 961');
	insert into TB_LANCAMENTO_INPUT_COLS (
		ID_LANCAMENTO_INPUT,
		CD_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT,
		VAR_COL_LANCAMENTO_DT_NASCIMENTO,
		VAR_ID_COLUMN_16_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 979');

	/*****************************************************************************************************************/
	/*****************************************************************************************************************/
	/* REGRA */
	
	call PROC_LOG_MESSAGE('LINHA - 773');
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_ARQUIVO_INPUT_SHEET,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'INTERVALOR.8EAPM.01',
		'INTERVALOR.8EAPM - Regra para Valor do Arquivo FATUCOPA INTERVALOR dividido por 100',
		1, /* SIMPLES */
		0,
		VAR_ID_ARQUIVO_INPUT_SHEET,
		
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_14_VL_PRINCIPAL,
		
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA,
		VAR_ID_COLUMN_14_VL_PRINCIPAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4020');	
	/*****************************************************************************************************************/
	/*****************************************************************************************************************/
    /* ARQUIVO_OUTPUT */
	
    call PROC_LOG_MESSAGE('LINHA - 984');
	select ID into VAR_ID_VIEW_DESTINATION_COPART from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_COPARTICIPACAO_INTERVALOR';
	
	/* FASTU-COPA.1 */	
    
	call PROC_LOG_MESSAGE('LINHA - 1159');
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		TP_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'Intervalor-SAS(Saude)_Coparticipacao_({YYYY}{MM})_{CC}.xlsx',
		'Arquivo de saída para a carga dos lançamentos FATU COPA',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT;
	
    call PROC_LOG_MESSAGE('LINHA - 1012');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION_COPART,
		'%s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1030');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
