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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180920-004-dml-CARGILL-00197.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180920-005-dml-CARGILL-ISENTOS.sql';
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
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
	
	declare VAR_DT_FORMAT_DDMMYYYY			varchar( 40 ) default 'dd/MM/yyyy';
	
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
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_SHEET				bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
			
	declare VAR_ID_COLUMN_001_NR_CHAVE									bigint( 17 );
	declare VAR_ID_COLUMN_002_NR_CARTAO_TITULAR							bigint( 17 );
	declare VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE						bigint( 17 );
	declare VAR_ID_COLUMN_004_NM_TITULAR								bigint( 17 );
	declare VAR_ID_COLUMN_005_NM_BENEFICIARIO							bigint( 17 );
	declare VAR_ID_COLUMN_006_NR_CPF									bigint( 17 );
	declare VAR_ID_COLUMN_007_DESCR_MOTIVO								bigint( 17 );
	declare VAR_ID_COLUMN_008_DT_INICIO									bigint( 17 );
	declare VAR_ID_COLUMN_009_DT_FIM									bigint( 17 );	

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
	
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
		
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
	
	declare VAR_CD_SHEET												int( 3 ) default 0;
	
	declare VAR_CD_ORDEM												int( 3 ) default 0;
	declare VAR_NR_MATRICULA_BASE_00192									bigint( 17 ) default 192000000000000;
	declare VAR_NR_MATRICULA_BASE_00196									bigint( 17 ) default 196000000000000;
	declare VAR_NR_MATRICULA_BASE_00197									bigint( 17 ) default 197000000000000;
	
	declare VAR_DIVISOR_MATRICULA										bigint( 17 ) default 100000000000;
	declare VAR_BASE_DIVISOR											bigint( 17 ) default 10000;
	declare VAR_CD_CONTRATO_00192										int( 3 ) default 192;
	declare VAR_CD_CONTRATO_00196										int( 3 ) default 196;
	declare VAR_CD_CONTRATO_00197										int( 3 ) default 197;
	
	declare VAR_ID_REGRA_TITULAR_00192									bigint( 17 );
	declare VAR_ID_REGRA_TITULAR_00196									bigint( 17 );
	declare VAR_ID_REGRA_TITULAR_00197									bigint( 17 );
	declare VAR_ID_REGRA_DEPENDENTE_00192								bigint( 17 );
	declare VAR_ID_REGRA_DEPENDENTE_00196								bigint( 17 );
	declare VAR_ID_REGRA_DEPENDENTE_00197								bigint( 17 );
	declare	VAR_ID_REGISTER												bigint( 17 );
	declare	VAR_CD_REGISTER	_REG01										bigint( 17 ) default 0;

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
    where CD_EMPRESA = 'CARGILL';
        
    call PROC_LOG_MESSAGE('LINHA - 123');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'ISENTO'; 
	
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 246');
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
		'^(CARGILL)\\.(ISENTO)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de carga de isentos',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_ISENTO,
		2,
		null, /* Não é usado para arquivo CSV */
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 199');
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
	call PROC_LOG_MESSAGE('LINHA - 234');
	insert into TB_REGISTER(
		ID_ARQUIVO_INPUT_SHEET,
		NM_REGISTER,
		CD_REGISTER,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,
		'REG_01',		
		VAR_CD_REGISTER_REG01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_REGISTER
	from TB_ARQUIVO_INPUT_SHEET; 
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_001_NR_CHAVE',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_001_NR_CHAVE
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_002_NR_CARTAO_TITULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_002_NR_CARTAO_TITULAR
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_003_NR_CARTAO_DEPENDENTE',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_004_NM_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_004_NM_TITULAR
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_005_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_005_NM_BENEFICIARIO
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_006_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_006_NR_CPF
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_007_DESCR_MOTIVO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_007_DESCR_MOTIVO
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_008_DT_INICIO',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT_DDMMYYYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_008_DT_INICIO
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 279');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_009_DT_FIM',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT_DDMMYYYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_009_DT_FIM
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	/* REGRAS  */		
	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 1360');
    insert into TB_REGRA(
        NM_REGRA,
        DESCR_REGRA,
        TP_REGRA,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'REGRA_MATRICULA.ISENTO.01',
        'Regra para extrair o código do contrato do número composto da matricula',
        VAR_TP_REGRA_SIMPLES,
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
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
        VAR_TP_REGRA_OPERATION_DIVIDE,
        VAR_CD_ORDEM,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
    
    call PROC_LOG_MESSAGE('LINHA - 1403');
    insert into TB_REGRA_FIELD(
        ID_REGRA_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
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
        VAR_DIVISOR_MATRICULA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    call PROC_LOG_MESSAGE('LINHA - 1030');
    insert into TB_REGRA_RESULT(
        ID_REGRA,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
    /*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 820');
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.01',
		'CARGILL.ISENTO.00192 - Regra para subtrair o NR_MATRICULA_TITULAR do beneficiário por 19200000000000',
		VAR_TP_REGRA_CONDITIONAL,
		VAR_CD_ORDEM,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_TITULAR_00192
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 502');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_TITULAR_00192,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_NR_MATRICULA_BASE_00192,
		
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
		VAR_ID_REGRA_TITULAR_00192,
		VAR_TP_REGRA_OPERATION_DIVIDE,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 562');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_TITULAR_00192,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
				
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 911');
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.02',
		'CARGILL.ISENTO.00192 - Regra para subtrair o NR_MATRICULA_DEPENDENTE do beneficiário por 192000000000000',
		VAR_TP_REGRA_CONDITIONAL,
		VAR_CD_ORDEM,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_DEPENDENTE_00192
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 648');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_DEPENDENTE_00192,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_NR_MATRICULA_BASE_00192,
		
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
		VAR_ID_REGRA_DEPENDENTE_00192,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_DEPENDENTE_00192,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	/*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 820');
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.03',
		'CARGILL.ISENTO.00196 - Regra para subtrair o NR_MATRICULA_TITULAR do beneficiário por 19600000000000',
		VAR_TP_REGRA_CONDITIONAL,
		VAR_CD_ORDEM,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_TITULAR_00196
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 794');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_TITULAR_00196,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_NR_MATRICULA_BASE_00196,
		
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
		VAR_ID_REGRA_TITULAR_00196,
		VAR_TP_REGRA_OPERATION_DIVIDE,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 562');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_TITULAR_00196,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
				
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 911');
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.04',
		'CARGILL.ISENTO.00196 - Regra para subtrair o NR_MATRICULA_DEPENDENTE do beneficiário por 196000000000000',
		VAR_TP_REGRA_CONDITIONAL,
		VAR_CD_ORDEM,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_DEPENDENTE_00196
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 940');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_DEPENDENTE_00196,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_NR_MATRICULA_BASE_00196,
		
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
		VAR_ID_REGRA_DEPENDENTE_00196,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_DEPENDENTE_00196,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 4948');	
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 820');
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.05',
		'CARGILL.ISENTO.00197 - Regra para subtrair o NR_MATRICULA_TITULAR do beneficiário por 19700000000000',
		VAR_TP_REGRA_CONDITIONAL,
		0,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_TITULAR_00197
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 1087');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_TITULAR_00197,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_NR_MATRICULA_BASE_00197,
		
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
		VAR_ID_REGRA_TITULAR_00197,
		VAR_TP_REGRA_OPERATION_DIVIDE,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_OPERATION from TB_REGRA_OPERATION;
	
	call PROC_LOG_MESSAGE('LINHA - 562');
	insert into TB_REGRA_FIELD(
		ID_REGRA_OPERATION,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_TITULAR_00197,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
				
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 911');
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_REGRA(
		NM_REGRA,
		DESCR_REGRA,
		TP_REGRA,
		CD_ORDEM,
		ID_REGISTER,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'REGRA.CARGILL.ISENTO.06',
		'CARGILL.ISENTO.00197 - Regra para subtrair o NR_MATRICULA_DEPENDENTE do beneficiário por 197000000000000',
		VAR_TP_REGRA_CONDITIONAL,
		VAR_CD_ORDEM,
		VAR_ID_REGISTER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_REGRA_DEPENDENTE_00197
	from TB_REGRA;
	
	call PROC_LOG_MESSAGE('LINHA - 1233');
	insert into TB_REGRA_OPERATION(
		ID_REGRA,
		TP_OPERATION,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_DEPENDENTE_00197,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_NR_MATRICULA_BASE_00197,
		
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
		VAR_ID_REGRA_DEPENDENTE_00197,
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGRA_OPERATION,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
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
		VAR_BASE_DIVISOR,
		
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
		VAR_ID_REGRA_DEPENDENTE_00197,
		VAR_ID_COLUMN_003_NR_CARTAO_DEPENDENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1353');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	/* REGRA CONDICIONAL */
    
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = 0;
        
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 192 do TITULAR',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00192,
        
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
        VAR_ID_REGRA_TITULAR_00192,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');
  	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 196 do TITULAR',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00196,
        
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
        VAR_ID_REGRA_TITULAR_00196,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');  	
  	
	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 197 do TITULAR',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00197,
        
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
        VAR_ID_REGRA_TITULAR_00197,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');  	
  	
  	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 192 do DEPENDENTE',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00192,
        
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
        VAR_ID_REGRA_DEPENDENTE_00192,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');
  	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 196 do DEPENDENTE',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00196,
        
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
        VAR_ID_REGRA_DEPENDENTE_00196,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');  	
  	
	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1046');
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    insert into TB_REGRA_CONDITIONAL(
        NM_REGRA_CONDITIONAL,
        CD_ORDEM,
        ID_REGISTER,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'Regra para chamar a regra de extração das matriculas que começam com 197 do DEPENDENTE',
        VAR_CD_ORDEM,
        VAR_ID_REGISTER,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL from TB_REGRA_CONDITIONAL;
    
    call PROC_LOG_MESSAGE('LINHA - 1171');
    insert into TB_REGRA_CONDITIONAL_OPERATION(
        ID_REGRA_CONDITIONAL,
        TP_OPERATION,
        CD_ORDEM,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL,
        VAR_TP_REGRA_OPERATION_EQUALS,
        0,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_REGRA_CONDITIONAL_OPERATION from TB_REGRA_CONDITIONAL_OPERATION;
    
    insert into TB_REGRA_CONDITIONAL_FIELD(
        ID_REGRA_CONDITIONAL_OPERATION,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_ID_COLUMN_001_NR_CHAVE,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    insert into TB_REGRA_CONDITIONAL_VALOR(
        ID_REGRA_CONDITIONAL_OPERATION,
        VL_LONG,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_REGRA_CONDITIONAL_OPERATION,
        VAR_CD_CONTRATO_00197,
        
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
        VAR_ID_REGRA_DEPENDENTE_00197,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
  		
  	call PROC_LOG_MESSAGE('LINHA - 1236');   	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/	
	/* BENEFICIARIO ISENTO */
		
	call PROC_LOG_MESSAGE('LINHA - 4948');
	/* MECSAS */
	insert into TB_ISENTO_INPUT_SHEET(
		ID_REGISTER,
		TP_ISENTO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_REGISTER,
		null,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_002_NR_CARTAO_TITULAR,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1; 

	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_004_NM_TITULAR,
		VAR_CD_ISENTO_COLS_DEF_NM_TITULAR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_005_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_006_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_007_DESCR_MOTIVO,
		VAR_CD_ISENTO_COLS_DEF_TP_ISENTO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_008_DT_INICIO,
		VAR_CD_ISENTO_COLS_DEF_DT_INICIO,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
		CD_BENEFICIARIO_ISENTO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ID_COLUMN_009_DT_FIM,
		VAR_CD_ISENTO_COLS_DEF_DT_FIM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	call PROC_LOG_MESSAGE('LINHA - 976');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
