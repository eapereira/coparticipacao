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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180726-005-dml-marjan-8C7XY.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180726-006-dml-marjan-ISENTOS.sql';
	
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
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA		bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 					bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	DECLARE VAR_ID_CONTRATO_FATUCOPA 		bigint( 17 );
	DECLARE VAR_ID_CONTRATO 				bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z8			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z9			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XY			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XX			bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XY		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XX		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 					bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS					bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE		bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE	bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS				bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO			bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA			bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT_SHEET						bigint( 17 );
    declare VAR_ID_ARQUIVO_INPUT							bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT						bigint( 17 );
	
	declare VAR_SHEET01_COLUMN_001_CD_EMPRESA				bigint( 17 );
	declare VAR_SHEET01_COLUMN_002_NR_MATRICULA				bigint( 17 );
	declare VAR_SHEET01_COLUMN_003_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_SHEET01_COLUMN_004_NR_CPF					bigint( 17 );
	declare VAR_SHEET01_COLUMN_005_DT_FIM					bigint( 17 );
	declare VAR_SHEET01_COLUMN_006_NM_CONVENIO				bigint( 17 );

	declare VAR_SHEET02_COLUMN_001_CD_EMPRESA				bigint( 17 );
	declare VAR_SHEET02_COLUMN_002_NR_MATRICULA				bigint( 17 );
	declare VAR_SHEET02_COLUMN_003_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_SHEET02_COLUMN_004_NR_CPF					bigint( 17 );
	declare VAR_SHEET02_COLUMN_005_DT_FIM					bigint( 17 );
	declare VAR_SHEET02_COLUMN_006_NM_CONVENIO				bigint( 17 );
	declare VAR_SHEET02_COLUMN_007_NM_TITULAR				bigint( 17 );

	declare VAR_SHEET03_COLUMN_001_CD_EMPRESA				bigint( 17 );
	declare VAR_SHEET03_COLUMN_002_NR_MATRICULA				bigint( 17 );
	declare VAR_SHEET03_COLUMN_003_NM_TITULAR				bigint( 17 );
	declare VAR_SHEET03_COLUMN_004_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_SHEET03_COLUMN_005_NR_MATRICULA_EMPRESA		bigint( 17 );
	declare VAR_SHEET03_COLUMN_006_DT_NASCIMENTO			bigint( 17 );
	declare VAR_SHEET03_COLUMN_007_DT_FIM					bigint( 17 );
	declare VAR_SHEET03_COLUMN_008_NM_CONVENIO				bigint( 17 );
	
	declare VAR_SHEET04_COLUMN_001_CD_EMPRESA				bigint( 17 );
	declare VAR_SHEET04_COLUMN_002_NR_MATRICULA				bigint( 17 );
	declare VAR_SHEET04_COLUMN_003_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_SHEET04_COLUMN_004_NR_CPF					bigint( 17 );
	declare VAR_SHEET04_COLUMN_005_NM_CONVENIO				bigint( 17 );
	declare VAR_SHEET04_COLUMN_006_DT_DEMISSAO				bigint( 17 );	
	
	declare VAR_SHEET05_COLUMN_001_CD_EMPRESA				bigint( 17 );
	declare VAR_SHEET05_COLUMN_002_NR_MATRICULA				bigint( 17 );
	declare VAR_SHEET05_COLUMN_003_NM_TITULAR				bigint( 17 );
	declare VAR_SHEET05_COLUMN_004_NR_CPF_TITULAR			bigint( 17 );
	declare VAR_SHEET05_COLUMN_005_NR_MATRICULA_EMPRESA		bigint( 17 );
	declare VAR_SHEET05_COLUMN_006_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_SHEET05_COLUMN_007_NR_CPF_BENEFICIARIO		bigint( 17 );
	declare VAR_SHEET05_COLUMN_008_NM_CONVENIO				bigint( 17 );

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
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	
	declare VAR_COL_LANCAMENTO_ID_DEPENDENTE							bigint( 17 );
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 );
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 );
		
	declare VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO							bigint( 17 ) default 0; /* fake */
	
	declare VAR_CD_ISENTO_COLS_DEF_TP_ISENTO							bigint( 17 ) default 1;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA							bigint( 17 ) default 2;
	declare VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO						bigint( 17 ) default 3;
	declare VAR_CD_ISENTO_COLS_DEF_DT_NASCIMENTO						bigint( 17 ) default 4;
	declare VAR_CD_ISENTO_COLS_DEF_NR_CPF								bigint( 17 ) default 5;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR					bigint( 17 ) default 6;
	declare VAR_CD_ISENTO_COLS_DEF_NM_TITULAR							bigint( 17 ) default 7;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_EMPRESA					bigint( 17 ) default 9;
	declare VAR_CD_ISENTO_COLS_DEF_DT_INICIO							bigint( 17 ) default 10;
	declare VAR_CD_ISENTO_COLS_DEF_DT_FIM								bigint( 17 ) default 11;

	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	
	declare VAR_CD_SHEET_TITULAR_GESTANTE								int( 3 ) default 0;
	declare VAR_CD_SHEET_DEPENDENTE_GESTANTE							int( 3 ) default 1;
	declare VAR_CD_SHEET_FILHOS											int( 3 ) default 2;
	declare VAR_CD_SHEET_ESTAGIARIO										int( 3 ) default 3;
	declare VAR_CD_SHEET_DIRETORIA										int( 3 ) default 4;
	
	declare VAR_TP_ISENTO_GESTANTE										int( 3 ) default 1;
	declare VAR_TP_ISENTO_FILHOS_ATE_12_MESES							int( 3 ) default 2;
	declare VAR_TP_ISENTO_ESTAGIARIO									int( 3 ) default 3;
	declare VAR_TP_ISENTO_DIRETORIA										int( 3 ) default 4;
	declare VAR_TP_ISENTO_CRONICO										int( 3 ) default 5;
	declare VAR_TP_ISENTO_CRONICO_INATIVO								int( 3 ) default 6;
	declare VAR_TP_ISENTO_VALOR											int( 3 ) default 7;
	declare VAR_TP_ISENTO_VALOR_CENTAVO									int( 3 ) default 8;
	declare VAR_TP_ISENTO_DEMITIDO										int( 3 ) default 9;		
	
	
	declare VAR_CD_FORMAT_DDMMYY										varchar( 20 ) default 'dd/MM/yy';
	declare VAR_CD_FORMAT_DDMMYYYY										varchar( 20 ) default 'dd/MM/yyyy';
	declare VAR_CD_ORDEM												int( 3 ) default 0;

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
    call PROC_LOG_MESSAGE('LINHA - 236');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'Marjan';
        
    call PROC_LOG_MESSAGE('LINHA - 123');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'ISENTO'; 
	
	/***********************************************************************************************************************/
	
	call PROC_LOG_MESSAGE('LINHA - 283');
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
		'^(MARJAN)\\.(ISENTO)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de carga de isentos',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_ISENTO,
		1,
		null, /* Não é usado para arquivo CSV */
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT 
	from TB_ARQUIVO_INPUT;
	
	/*****************************************************************************************************************************************************/
	/* TITULARES GESTANTES  */
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
		VAR_CD_SHEET_TITULAR_GESTANTE,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 267');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_001_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_001_CD_EMPRESA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 288');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_002_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_002_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 314');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_003_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_003_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 340');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_004_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_004_NR_CPF 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 366');
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
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_005_DT_FIM',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_005_DT_FIM 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 394');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		'COLUMN_006_NM_CONVENIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET01_COLUMN_006_NM_CONVENIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 404');
	/*****************************************************************************************************************************************************/
	/* DEPENDENTES GESTANTES  */
	call PROC_LOG_MESSAGE('LINHA - 407');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_DEPENDENTE_GESTANTE,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 446');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_001_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_001_CD_EMPRESA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 472');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_002_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_002_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 498');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_003_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_003_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 524');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_004_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_004_NR_CPF 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 550');
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
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_005_DT_FIM',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_005_DT_FIM 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 578');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_006_NM_CONVENIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_006_NM_CONVENIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 604');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		'COLUMN_007_NM_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET02_COLUMN_007_NM_TITULAR 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 614');
	/*****************************************************************************************************************************************************/
	/* FILHOS */
	call PROC_LOG_MESSAGE('LINHA - 617');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_FILHOS,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 656');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_001_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_001_CD_EMPRESA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 682');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_002_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_002_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 708');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_003_NM_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_003_NM_TITULAR 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;	
	
	call PROC_LOG_MESSAGE('LINHA - 734');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_004_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_004_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 760');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_005_NR_MATRICULA_EMPRESA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_005_NR_MATRICULA_EMPRESA
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 786');
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
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_006_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYYYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_006_DT_NASCIMENTO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 814');
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
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_007_DT_FIM',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_007_DT_FIM 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 842');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		'COLUMN_008_NM_CONVENIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET03_COLUMN_008_NM_CONVENIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 614');
	/*****************************************************************************************************************************************************/
	/* ESTAGIARIO */
	call PROC_LOG_MESSAGE('LINHA - 617');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_ESTAGIARIO,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 894');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_001_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_001_CD_EMPRESA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 920');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_002_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_002_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 946');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_003_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_003_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 972');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_004_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_004_NR_CPF
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 998');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_005_NM_CONVENIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_005_NM_CONVENIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		

	call PROC_LOG_MESSAGE('LINHA - 1024');
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
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		'COLUMN_006_DT_DEMISSAO',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYYYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET04_COLUMN_006_DT_DEMISSAO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 1052');	
	/*****************************************************************************************************************************************************/
	/* DIRETORIA */
	call PROC_LOG_MESSAGE('LINHA - 1055');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,
		ID_CONTRATO,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		VAR_CD_SHEET_DIRETORIA,
		VAR_ID_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 1078');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_001_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_001_CD_EMPRESA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1104');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_002_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_002_NR_MATRICULA 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1130');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_003_NM_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_003_NM_TITULAR 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 1156');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_004_NR_CPF_TITULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_004_NR_CPF_TITULAR
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
		
	call PROC_LOG_MESSAGE('LINHA - 1182');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_006_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_006_NM_BENEFICIARIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 1288');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_007_NR_CPF_BENEFICIARIO',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_007_NR_CPF_BENEFICIARIO
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;		
	
	call PROC_LOG_MESSAGE('LINHA - 1234');
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		'COLUMN_008_NM_CONVENIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_SHEET05_COLUMN_008_NM_CONVENIO 
	from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;			
	
	call PROC_LOG_MESSAGE('LINHA - 1260');	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	/* BENEFICIARIO ISENTO */
	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 1267');
	/* MECSAS */
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_TITULAR_GESTANTE,
		VAR_TP_ISENTO_GESTANTE,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;

	call PROC_LOG_MESSAGE('LINHA - 1286');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_COLUMN_001_CD_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 1305');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_COLUMN_002_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 1324');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_COLUMN_003_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 1343');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_COLUMN_004_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 1362');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET01_COLUMN_005_DT_FIM,
		VAR_CD_ISENTO_COLS_DEF_DT_FIM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1380');
	/*****************************************************************************************************************************************************/
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_DEPENDENTE_GESTANTE,
		VAR_TP_ISENTO_GESTANTE,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET 
	from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 2982');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_COLUMN_001_CD_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3002');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_COLUMN_002_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5042');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_COLUMN_003_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_COLUMN_004_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET02_COLUMN_005_DT_FIM,
		VAR_CD_ISENTO_COLS_DEF_DT_FIM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
		
	/*****************************************************************************************************************************************************/
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 2955');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_FILHOS,
		VAR_TP_ISENTO_FILHOS_ATE_12_MESES,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET 
	from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 3041');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_001_CD_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3062');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_002_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3082');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_003_NM_TITULAR,
		VAR_CD_ISENTO_COLS_DEF_NM_TITULAR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
		
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_004_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_005_NR_MATRICULA_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_EMPRESA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_006_DT_NASCIMENTO,
		VAR_CD_ISENTO_COLS_DEF_DT_INICIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET03_COLUMN_007_DT_FIM,
		VAR_CD_ISENTO_COLS_DEF_DT_FIM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*****************************************************************************************************************************************************/
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 2955');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_ESTAGIARIO,
		VAR_TP_ISENTO_ESTAGIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 3179');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET04_COLUMN_001_CD_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3199');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET04_COLUMN_002_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3218');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET04_COLUMN_003_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET04_COLUMN_004_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET04_COLUMN_006_DT_DEMISSAO,
		VAR_CD_ISENTO_COLS_DEF_DT_FIM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*****************************************************************************************************************************************************/
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5340');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT_SHEET,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_SHEET_DIRETORIA,
		VAR_TP_ISENTO_DIRETORIA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 3278');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET05_COLUMN_001_CD_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3298');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET05_COLUMN_002_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5377');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET05_COLUMN_006_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5396');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_SHEET05_COLUMN_007_NR_CPF_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 1916');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
