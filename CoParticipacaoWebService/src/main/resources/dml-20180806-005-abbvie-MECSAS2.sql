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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default 'dml-20180806-004-abbvie-MECSAS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default 'dml-20180806-005-abbvie-MECSAS2.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 3;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 4;
	
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

	declare VAR_ID_COLUMN_01_NM_EMPRESA 			bigint( 17 ); 
	declare VAR_ID_COLUMN_02_NR_MATRICULA 			bigint( 17 ); 
	declare VAR_ID_COLUMN_03_UPI 					bigint( 17 ); 
	declare VAR_ID_COLUMN_04_NM_BENEFICIARIO 		bigint( 17 ); 
	declare VAR_ID_COLUMN_05_NM_ORGAO_RESPONSAVEL 	bigint( 17 ); 
	declare VAR_ID_COLUMN_06_DT_ADMISSAO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_07_DT_NASCIMENTO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_08_ESTADO_CIVIL 			bigint( 17 ); 
	declare VAR_ID_COLUMN_09_NACIONALIDADE 			bigint( 17 ); 
	declare VAR_ID_COLUMN_10_NATURALIDADE 			bigint( 17 ); 
	declare VAR_ID_COLUMN_11_NM_MAE 				bigint( 17 ); 
	declare VAR_ID_COLUMN_12_NM_PAI 				bigint( 17 ); 
	declare VAR_ID_COLUMN_13_ENDERECO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_14_BAIRRO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_15_CEP 					bigint( 17 ); 
	declare VAR_ID_COLUMN_16_MUNICIPIO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_17_TELEFONE 				bigint( 17 ); 
	declare VAR_ID_COLUMN_18_CELULAR 				bigint( 17 ); 
	declare VAR_ID_COLUMN_19_EMAIL 					bigint( 17 ); 
	declare VAR_ID_COLUMN_20_NR_CPF 				bigint( 17 ); 
	declare VAR_ID_COLUMN_21_NR_RG 					bigint( 17 ); 
	declare VAR_ID_COLUMN_22_NR_TITULO_ELEITOR 		bigint( 17 ); 
	declare VAR_ID_COLUMN_23_CTPS 					bigint( 17 ); 
	declare VAR_ID_COLUMN_24_CNH 					bigint( 17 ); 
	declare VAR_ID_COLUMN_25_CERT_MILITAR 			bigint( 17 ); 
	declare VAR_ID_COLUMN_26_PIS 					bigint( 17 ); 
	declare VAR_ID_COLUMN_27_LOTACAO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_28_CENTRO_CUSTO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_29_BANCO_PAGTO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_31_TP_CONTA_CORRENTE 		bigint( 17 ); 
	declare VAR_ID_COLUMN_32_CONTA_CORRENTE_PAGTO 	bigint( 17 ); 
	declare VAR_ID_COLUMN_33_VINCULO_EMPREGATICIO 	bigint( 17 ); 
	declare VAR_ID_COLUMN_34_CARGO 					bigint( 17 ); 
	declare VAR_ID_COLUMN_35_SALARIO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_36_NM_SINDICATO 			bigint( 17 ); 
	declare VAR_ID_COLUMN_37_CBO 					bigint( 17 ); 
	declare VAR_ID_COLUMN_38_HORARIO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_39_SITUACAO 				bigint( 17 ); 
	declare VAR_ID_COLUMN_40_NM_GESTOR 				bigint( 17 ); 	
		
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );	
	
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
	
	declare VAR_COL_INDEX												int( 5 ) default 0;
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
    where NM_EMPRESA = 'Abbvie';
        
    call PROC_LOG_MESSAGE('LINHA - 123');	
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where CD_CONTRATO = 'MECSAS.2.abbvie';
	
	call PROC_LOG_MESSAGE('LINHA - 139');
	insert into TB_ARQUIVO_INPUT(
		ID_CONTRATO,
		NM_ARQUIVO_REGEXP,
		DESCR_ARQUIVO,	
		TP_ARQUIVO,
		TP_USE,
		NUM_SKIP_LINES,
		NUM_DEFAULT_LINE_LENGTH,
		
		CD_REGEXP_CONTRATO,
		CD_REGEXP_DIA,
		CD_REGEXP_MES,
		CD_REGEXP_ANO,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_CONTRATO,
		'^Ativos\\W([0-9]{2})\\.([0-9]{4})\\.(xlsx|XLSX)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_MECSAS2,
		1,
		182,
		
		null, 	/* REGEXP_CONTRATO */
		null, 	/* REGEXP_DIA */
		1, 		/* REGEXP_MES */
		2, 		/* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;

	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_01_NM_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_NM_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;

	call PROC_LOG_MESSAGE('LINHA - 172');
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
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_03_UPI',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_UPI from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_04_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_05_NM_ORGAO_RESPONSAVEL',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_NM_ORGAO_RESPONSAVEL from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_06_DT_ADMISSAO',
		VAR_COL_DATE,
		null,
		VAR_COL_INDEX,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_DT_ADMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_07_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		VAR_COL_INDEX,
		'dd/MM/yyyy',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_08_ESTADO_CIVIL',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_ESTADO_CIVIL from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_09_NACIONALIDADE',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_NACIONALIDADE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_10_NATURALIDADE',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_NATURALIDADE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_11_NM_MAE',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_NM_MAE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_12_NM_PAI',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_NM_PAI from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_13-ENDERECO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_ENDERECO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_14_BAIRRO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_BAIRRO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_15_CEP',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_CEP from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_16_MUNICIPIO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_MUNICIPIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_17_TELEFONE',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_TELEFONE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_18_CELULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_CELULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_19_EMAIL',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_EMAIL from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_20_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_21_NR_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21_NR_RG from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_22_NR_TITULO_ELEITOR',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22_NR_TITULO_ELEITOR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_23_CTPS',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_CTPS from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_24_CNH',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_24_CNH from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_25_CERT_MILITAR',
		VAR_COL_LONG,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_25_CERT_MILITAR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_26_PIS',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_26_PIS from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_27_LOTACAO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_27_LOTACAO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_28_CENTRO_CUSTO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_28_CENTRO_CUSTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_29_BANCO_PAGTO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_29_BANCO_PAGTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_31_TP_CONTA_CORRENTE',
		VAR_COL_INT,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_31_TP_CONTA_CORRENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_32_CONTA_CORRENTE_PAGTO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_32_CONTA_CORRENTE_PAGTO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_33_VINCULO_EMPREGATICIO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_33_VINCULO_EMPREGATICIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_34_CARGO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_34_CARGO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_35_SALARIO',
		VAR_COL_DOUBLE,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_35_SALARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_36_NM_SINDICATO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_36_NM_SINDICATO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_37_CBO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_37_CBO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_38_HORARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_38_HORARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_39_SITUACAO',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_39_SITUACAO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 172');
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
		'COLUMN_40_NM_GESTOR',
		VAR_COL_VARCHAR,
		null,
		VAR_COL_INDEX,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_40_NM_GESTOR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_COL_INDEX = VAR_COL_INDEX + 1;
	
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
		VAR_ID_COLUMN_04_NM_BENEFICIARIO,
		
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
		VAR_ID_COLUMN_20_NR_CPF,
		
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
		VAR_ID_COLUMN_07_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 1228');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA,
		VAR_ID_COLUMN_03_UPI,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1269');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_ID_COLUMN_06_DT_ADMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1285');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
