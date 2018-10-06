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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180921-002-dml-HOC.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180921-003-dml-HOC.sql';
	
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
    
	declare VAR_VIEW_DESTINATION_RESUMO_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_ORIGINAL_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_ISENCAO_GESTANTES_HOC 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_CONSELHEIROS_HOC 						bigint( 17 );
	declare VAR_VIEW_DESTINATION_AFASTADOS_HOC		 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_AGREGADOS_HOC 							bigint( 17 );
	declare VAR_VIEW_DESTINATION_PLANO_EXTENSAO_HOC 					bigint( 17 );
	declare VAR_VIEW_DESTINATION_DESLIGADOS_HOC 						bigint( 17 );
	declare VAR_VIEW_DESTINATION_PRN_HOC 								bigint( 17 );
	
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
    
	declare VAR_CD_ISENTO_COLS_DEF_TP_ISENTO							bigint( 17 ) default 1;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA							bigint( 17 ) default 2;
	declare VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO						bigint( 17 ) default 3;
	declare VAR_CD_ISENTO_COLS_DEF_DT_NASCIMENTO						bigint( 17 ) default 4;
	declare VAR_CD_ISENTO_COLS_DEF_NR_CPF								bigint( 17 ) default 5;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR					bigint( 17 ) default 6;
	declare VAR_CD_ISENTO_COLS_DEF_NM_TITULAR							bigint( 17 ) default 7;
	declare VAR_CD_ISENTO_COLS_DEF_VL_ISENCAO							bigint( 17 ) default 8;
	
	declare VAR_TP_REGRA_SIMPLES										int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL									int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD									int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT							int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_DIVIDE								int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY								int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS								int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS							int( 3 )  default 6;
		
	declare VAR_TP_ISENTO_GRAVIDA										int( 3 ) default 1;
	declare VAR_TP_ISENTO_VALOR											int( 3 ) default 7;
	declare VAR_TP_ISENTO_VALOR_CENTAVO									int( 3 ) default 8;
	
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
	
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção por valor';
	declare VAR_NR_MATRICULA_BASE										bigint( 17 ) default 44400000000000;
	
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
	call PROC_LOG_MESSAGE('LINHA - 153');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = '0444';
    
    call PROC_LOG_MESSAGE('LINHA - 157');
    select ID into VAR_ID_CONTRATO from TB_CONTRATO
    where	ID_EMPRESA	= VAR_ID_EMPRESA
    and		CD_CONTRATO = '0444';
    
    call PROC_LOG_MESSAGE('LINHA - 162');
    select	ID into VAR_ID_ARQUIVO_INPUT
    from	TB_ARQUIVO_INPUT
    where	ID_CONTRATO = VAR_ID_CONTRATO;
    
    call PROC_LOG_MESSAGE('LINHA - 167');
    select	ID into VAR_ID_COLUMN_06_NM_DEPENDENTE
    from	TB_ARQUIVO_INPUT_COLS_DEF
    where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
    and		NM_COLUMN			= 'COLUMN_06_NM_DEPENDENTE';

    call PROC_LOG_MESSAGE('LINHA - 167');
    select	ID into VAR_ID_COLUMN_04_COD_TITULAR 
    from	TB_ARQUIVO_INPUT_COLS_DEF
    where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
    and		NM_COLUMN			= 'COLUMN_04_COD_TITULAR';

    call PROC_LOG_MESSAGE('LINHA - 167');
    select	ID into VAR_ID_COLUMN_05_COD_DEPENDENTE
    from	TB_ARQUIVO_INPUT_COLS_DEF
    where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
    and		NM_COLUMN			= 'COLUMN_05_COD_DEPENDENTE';
    
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 175');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_TITULAR,
		VAR_ID_COLUMN_04_COD_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 213');

	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 

