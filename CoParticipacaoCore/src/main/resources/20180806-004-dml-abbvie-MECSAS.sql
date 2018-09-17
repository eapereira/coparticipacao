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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180806-003-dml-abbvie-ISENTOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180806-004-dml-abbvie-MECSAS.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
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
	
	declare VAR_ID_OPERADORA_SULAMERICA				bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 							bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 							bigint( 17 );
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01_CD_CONTRATO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_02_NR_MATRICULA 			bigint( 17 ); 
	declare VAR_ID_COLUMN_03_DV 					bigint( 17 ); 
	declare VAR_ID_COLUMN_04_RDP 					bigint( 17 ); 
	declare VAR_ID_COLUMN_05_DC 					bigint( 17 ); 
	declare VAR_ID_COLUMN_06_LOCAL 					bigint( 17 ); 
	declare VAR_ID_COLUMN_07_CAT_FUNCIONAL 			bigint( 17 ); 
	declare VAR_ID_COLUMN_08_SETOR 					bigint( 17 ); 
	declare VAR_ID_COLUMN_09_ESTADO_CIVIL 			bigint( 17 ); 
	declare VAR_ID_COLUMN_10_CD_PLANO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_11_CD_PLANO_AGREG 		bigint( 17 ); 
	declare VAR_ID_COLUMN_12_DT_INCLUSAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_13_CD_PLANO_COMPL 		bigint( 17 ); 
	declare VAR_ID_COLUMN_14_NR_CPF 				bigint( 17 ); 
	declare VAR_ID_COLUMN_15_DG_CPF 				bigint( 17 ); 
	declare VAR_ID_COLUMN_16_BANCO 					bigint( 17 ); 
	declare VAR_ID_COLUMN_17_AGENCIA 				bigint( 17 ); 
	declare VAR_ID_COLUMN_18_DG_AGENCIA 			bigint( 17 ); 
	declare VAR_ID_COLUMN_19_CONTA 					bigint( 17 ); 
	declare VAR_ID_COLUMN_20 						bigint( 17 ); 
	declare VAR_ID_COLUMN_21_NM_BENEFICIARIO 		bigint( 17 ); 
	declare VAR_ID_COLUMN_22_DT_NASCIMENTO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_23_SEXO 					bigint( 17 ); 
	declare VAR_ID_COLUMN_24_PERMANENCIA 			bigint( 17 ); 
	declare VAR_ID_COLUMN_25_GP 					bigint( 17 ); 
	declare VAR_ID_COLUMN_26_AGREGADO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_27_DT_EXCLUSAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_28_CARENCIA 				bigint( 17 ); 
	declare VAR_ID_COLUMN_29 						bigint( 17 ); 
	declare VAR_ID_COLUMN_30_DT_REF 				bigint( 17 ); 
	declare VAR_ID_COLUMN_31_CD_ORGAO_LOTACAO 		bigint( 17 ); 
	declare VAR_ID_COLUMN_32_NM_ORGAO_LOTACAO 		bigint( 17 ); 
	declare VAR_ID_COLUMN_33 						bigint( 17 ); 
	declare VAR_ID_COLUMN_34_CD_MOTIVO_EXCLUSAO 	bigint( 17 ); 
	declare VAR_ID_COLUMN_35_CEP 					bigint( 17 ); 
	declare VAR_ID_COLUMN_36_TP_LOGRADOURO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_37_LOGRADOURO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_38_NUMERO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_39_COMPL 					bigint( 17 ); 
	declare VAR_ID_COLUMN_40_BAIRRO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_41_MUNICIPIO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_42_UF 					bigint( 17 ); 
	declare VAR_ID_COLUMN_43_DDD_TEL_RES 			bigint( 17 ); 
	declare VAR_ID_COLUMN_44_TEL_RES 				bigint( 17 ); 
	declare VAR_ID_COLUMN_45_DDD_TEL_COM 			bigint( 17 ); 
	declare VAR_ID_COLUMN_46_TEL_COM 				bigint( 17 ); 
	declare VAR_ID_COLUMN_47_DDD_TEL_CEL	 		bigint( 17 ); 
	declare VAR_ID_COLUMN_48_TEL_CEL 				bigint( 17 ); 
	declare VAR_ID_COLUMN_49_NM_MAE 				bigint( 17 ); 
	declare VAR_ID_COLUMN_50_RG 					bigint( 17 ); 
	declare VAR_ID_COLUMN_51_CD_ORGAO_EMISSOR 		bigint( 17 ); 
	declare VAR_ID_COLUMN_52_CD_PAIS 				bigint( 17 ); 
	declare VAR_ID_COLUMN_53_DT_EMISSAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_54_UF_EMISSAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_55_NR_PIS 				bigint( 17 ); 
	declare VAR_ID_COLUMN_56_NR_CNS 				bigint( 17 ); 
	declare VAR_ID_COLUMN_57_EMAIL 					bigint( 17 ); 
	declare VAR_ID_COLUMN_58_CD_ESCOLARIDADE 		bigint( 17 ); 
	declare VAR_ID_COLUMN_59_CD_RENDA_FAMILIAR 		bigint( 17 ); 
	declare VAR_ID_COLUMN_60_CD_PROFISSAO_TIT 		bigint( 17 ); 
	declare VAR_ID_COLUMN_61_CD_PAIS_ORIGEM 		bigint( 17 ); 
	declare VAR_ID_COLUMN_62_CD_IDENTIFICACAO 		bigint( 17 ); 
	declare VAR_ID_COLUMN_63_CD_OPERACAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_64_CRITICAS_01 			bigint( 17 ); 
	declare VAR_ID_COLUMN_65_CRITICAS_02 			bigint( 17 ); 
	declare VAR_ID_COLUMN_66_CRITICAS_03 			bigint( 17 ); 
	declare VAR_ID_COLUMN_67_CRITICAS_04 			bigint( 17 ); 
	declare VAR_ID_COLUMN_68_CRITICAS_05 			bigint( 17 ); 
	declare VAR_ID_COLUMN_69_CRITICAS_06 			bigint( 17 ); 
	declare VAR_ID_COLUMN_70_CRITICAS_07 			bigint( 17 ); 
	declare VAR_ID_COLUMN_71_CRITICAS_08 			bigint( 17 ); 
	declare VAR_ID_COLUMN_72_CRITICAS_09 			bigint( 17 ); 
	declare VAR_ID_COLUMN_73_CRITICAS_10 			bigint( 17 ); 
		
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );	
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
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	
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
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_DIGITO_CPF					bigint( 17 ) default 9;
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
    call PROC_LOG_MESSAGE('LINHA - 115');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = 'ABBVIE';
        
    call PROC_LOG_MESSAGE('LINHA - 123');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA 	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'MECSAS';
	
	call PROC_LOG_MESSAGE('LINHA - 139');
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
		'^(ABBVIE)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de Base de Ativos da Operadora',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_MECSAS,
		1,
		182,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;

	call PROC_LOG_MESSAGE('LINHA - 195');
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
		'COLUMN_01_CD_CONTRATO',
		VAR_COL_VARCHAR,
		null,
		0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_CD_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 219');
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
		'COLUMN_02_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 

	call PROC_LOG_MESSAGE('LINHA - 243');
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
		'COLUMN_03_DV',
		VAR_COL_INT,
		null,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_DV from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 267');
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
		'COLUMN_04_RDP',
		VAR_COL_LONG,
		null,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_RDP from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 291');
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
		'COLUMN_05_DC',
		VAR_COL_LONG,
		null,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_DC from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 219');
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
		'COLUMN_06_LOCAL',
		VAR_COL_INT,
		null,
		5,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 219');
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
		'COLUMN_07_CAT_FUNCIONAL',
		VAR_COL_INT,
		null,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_CAT_FUNCIONAL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 219');
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
		7,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_SETOR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 387');
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
		'COLUMN_09_ESTADO_CIVIL',
		VAR_COL_VARCHAR,
		null,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_ESTADO_CIVIL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_10_CD_PLANO',
		VAR_COL_INT,
		null,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_CD_PLANO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_11_CD_PLANO_AGREG',
		VAR_COL_INT,
		null,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_CD_PLANO_AGREG from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_12_DT_INCLUSAO',
		VAR_COL_DATE,
		null,
		11,
		'yyyyMMdd',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_DT_INCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_13_PLANO_COMPL',
		VAR_COL_INT,
		null,
		12,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_CD_PLANO_COMPL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_14_NR_CPF',
		VAR_COL_LONG,
		null,
		13,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_15_DG_CPF',
		VAR_COL_INT,
		null,
		14,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_DG_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_16_BANCO',
		VAR_COL_INT,
		null,
		15,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_BANCO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_17_AGENCIA',
		VAR_COL_INT,
		null,
		16,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_AGENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_18_DG_AGENCIA',
		VAR_COL_INT,
		null,
		17,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_DG_AGENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_19_CONTA',
		VAR_COL_LONG,
		null,
		18,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_CONTA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 851');
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
		'COLUMN_20',
		VAR_COL_VARCHAR,
		null,
		19,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_21_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		20,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_22_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		21,
		'yyyyMMdd',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_23_SEXO',
		VAR_COL_VARCHAR,
		null,
		22,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_SEXO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_24_PERMANENCIA',
		VAR_COL_VARCHAR,
		null,
		23,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_24_PERMANENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_25_GP',
		VAR_COL_INT,
		null,
		24,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_25_GP from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_26_AGREGADO',
		VAR_COL_VARCHAR,
		null,
		25,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_26_AGREGADO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_27_DT_EXCLUSAO',
		VAR_COL_INT,
		null,
		26,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_27_DT_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_28_CARENCIA',
		VAR_COL_VARCHAR,
		null,
		27,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_28_CARENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_29',
		VAR_COL_INT,
		null,
		28,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_29 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_30_DT_REF',
		VAR_COL_DATE,
		null,
		29,
		'yyyyMMdd',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_30_DT_REF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_31_CD_ORGAO_LOTACAO',
		VAR_COL_INT,
		null,
		30,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_31_CD_ORGAO_LOTACAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_32_NM_ORGAO_LOTACAO',
		VAR_COL_INT,
		null,
		31,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_32_NM_ORGAO_LOTACAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_33',
		VAR_COL_VARCHAR,
		null,
		32,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_33 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_34_CD_MOTIVO_EXCLUSAO',
		VAR_COL_INT,
		null,
		33,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_34_CD_MOTIVO_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_35_CEP',
		VAR_COL_INT,
		null,
		34,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_35_CEP from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_36_TP_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		35,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_36_TP_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_37_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		36,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_37_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_38_NUMERO',
		VAR_COL_INT,
		null,
		37,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_38_NUMERO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_39_COMPL',
		VAR_COL_VARCHAR,
		null,
		38,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_39_COMPL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_40_BAIRRO',
		VAR_COL_VARCHAR,
		null,
		39,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_40_BAIRRO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_41_MUNICIPIO',
		VAR_COL_VARCHAR,
		null,
		40,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_41_MUNICIPIO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 411');
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
		'COLUMN_42_UF',
		VAR_COL_VARCHAR,
		null,
		41,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_42_UF from TB_ARQUIVO_INPUT_COLS_DEF; 

	call PROC_LOG_MESSAGE('LINHA - 1203');
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
		'COLUMN_43_DDD_TEL_RES',
		VAR_COL_INT,
		null,
		42,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_43_DDD_TEL_RES from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1203');
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
		'COLUMN_44_TEL_RES',
		VAR_COL_INT,
		null,
		43,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_44_TEL_RES from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1203');
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
		'COLUMN_45_DDD_TEL_COM',
		VAR_COL_INT,
		null,
		44,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_45_DDD_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_46_TEL_COM',
		VAR_COL_INT,
		null,
		45,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_46_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_47_DDD_TEL_CEL',
		VAR_COL_INT,
		null,
		46,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_47_DDD_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_48_TEL_CEL',
		VAR_COL_INT,
		null,
		47,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_48_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_49_NM_MAE',
		VAR_COL_VARCHAR,
		null,
		48,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_49_NM_MAE from TB_ARQUIVO_INPUT_COLS_DEF; 

	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_50_RG',
		VAR_COL_VARCHAR,
		null,
		49,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_50_RG from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_51_CD_ORGAO_EMISSOR',
		VAR_COL_INT,
		null,
		50,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_51_CD_ORGAO_EMISSOR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_52_CD_PAIS',
		VAR_COL_INT,
		null,
		51,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_52_CD_PAIS from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COLUMN_53_DT_EMISSAO',
		VAR_COL_DATE,
		null,
		52,
		'yyyyMMdd',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_53_DT_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_54_UF_EMISSAO',
		VAR_COL_VARCHAR,
		null,
		53,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_54_UF_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_55_NR_PIS',
		VAR_COL_LONG,
		null,
		54,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_55_NR_PIS from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_56_NR_CNS',
		VAR_COL_VARCHAR,
		null,
		55,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_56_NR_CNS from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_57_EMAIL',
		VAR_COL_VARCHAR,
		null,
		56,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_57_EMAIL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_58_CD_ESCOLARIDADE',
		VAR_COL_INT,
		null,
		57,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_58_CD_ESCOLARIDADE from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_59_CD_RENDA_FAMILIAR',
		VAR_COL_INT,
		null,
		58,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_59_CD_RENDA_FAMILIAR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_60_CD_PROFISSAO_TIT',
		VAR_COL_VARCHAR,
		null,
		59,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_60_CD_PROFISSAO_TIT from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_61_CD_PAIS_ORIGEM',
		VAR_COL_VARCHAR,
		null,
		60,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_61_CD_PAIS_ORIGEM from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_62_CD_IDENTIFICACAO',
		VAR_COL_INT,
		null,
		61,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_62_CD_IDENTIFICACAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1275');
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
		'COLUMN_63_CD_OPERACAO',
		VAR_COL_VARCHAR,
		null,
		62,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_63_CD_OPERACAO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_64_CRITICAS_01',
		VAR_COL_VARCHAR,
		null,
		63,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_64_CRITICAS_01 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_65_CRITICAS_02',
		VAR_COL_VARCHAR,
		null,
		64,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_65_CRITICAS_02 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_66_CRITICAS_03',
		VAR_COL_VARCHAR,
		null,
		65,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_66_CRITICAS_03 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_67_CRITICAS_04',
		VAR_COL_VARCHAR,
		null,
		66,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_67_CRITICAS_04 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_68_CRITICAS_05',
		VAR_COL_VARCHAR,
		null,
		67,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_68_CRITICAS_05 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_69_CRITICAS_06',
		VAR_COL_VARCHAR,
		null,
		68,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_69_CRITICAS_06 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_70_CRITICAS_07',
		VAR_COL_VARCHAR,
		null,
		69,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_70_CRITICAS_07 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_71_CRITICAS_08',
		VAR_COL_VARCHAR,
		null,
		70,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_71_CRITICAS_08 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_72_CRITICAS_09',
		VAR_COL_VARCHAR,
		null,
		71,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_72_CRITICAS_09 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
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
		'COLUMN_73_CRITICAS_10',
		VAR_COL_VARCHAR,
		null,
		72,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_73_CRITICAS_10 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	/***********************************************************************************************************************/
	/***********************************************************************************************************************/
	/* Beneficiário */
	
    call PROC_LOG_MESSAGE('LINHA - 1848');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_02_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1879');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_21_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4834');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_14_NR_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 4850');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_22_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 2131');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
		VAR_ID_COLUMN_25_GP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 2147');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_ID_COLUMN_12_DT_INCLUSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 2147');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_DIGITO_CPF,
		VAR_ID_COLUMN_15_DG_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
