/*****************************************************************************************************************************************************/
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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180608-dml.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180608-dml.sql';
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_FALSE						int( 3 ) default 0;
	declare VAR_TRUE						int( 3 ) default 1;
    
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	
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
	
	declare VAR_ID_ROLE_USER 				bigint( 17 );
	declare VAR_ID_ROLE_ADMIN 				bigint( 17 );
	
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
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_DEPENDENTE		bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO_DEPENDENTE		bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_TITULAR					bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 9;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 10;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 11;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO_TITULAR			bigint( 17 ) default 12;
	
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
	
	#call PROC_VALIDATE_SCRIPT( VAR_NM_SCRIPT_REQUIRED, VAR_NM_SCRIPT );
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
    call PROC_LOG_MESSAGE('LINHA - 143');
    
	insert into TB_USER ( 
		NM_LOGIN, 
		DESCR_NAME, 
		CD_PASSWD, 
		TP_STATUS,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (
		'admin',
		'Administrador do Sistema',
		'$2a$10$rdWvrWVwi1I2tCYhSOtVYuom9wK99XMElw1nM1KsF78ijiHw6TAMC', /* admin */
		1, /* ATIVO */
		null,
		current_timestamp(),
		current_timestamp()
	);
	
	update 	TB_USER
	set 	USER_CREATED = 1
	where 	ID = 1;
	
    call PROC_LOG_MESSAGE('LINHA - 166');
	insert into TB_OPERADORA (
		NM_OPERADORA,	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		'Sulamerica',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
    call PROC_LOG_MESSAGE('LINHA - 178');
    
    insert into TB_ROLE(
    	NM_ROLE,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (
		'USER',

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select max( ID ) into VAR_ID_ROLE_USER from TB_ROLE;
	
    call PROC_LOG_MESSAGE('LINHA - 201');
    insert into TB_ROLE(
    	NM_ROLE,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (
		'ADMIN',

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ROLE_ADMIN from TB_ROLE;
	
	insert into TB_USER_ROLE(
		ID_USER,
		ID_ROLE,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_USER,
		VAR_ID_ROLE_USER,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 232');
	insert into TB_USER_ROLE(
		ID_USER,
		ID_ROLE,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_USER,
		VAR_ID_ROLE_ADMIN,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
    call PROC_LOG_MESSAGE('LINHA - 248');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 

/******************************************************************************************************************************************/
