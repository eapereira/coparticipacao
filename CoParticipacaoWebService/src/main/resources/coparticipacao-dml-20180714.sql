/**
 * Edson - 14/07/2018
 * Script para configurar o Hospital Oswaldo Cruz
 */

drop procedure if exists PROC_CREATE_HOC;

delimiter $$

create procedure PROC_CREATE_HOC()
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Script para configurar o Hospital Oswaldo Cruz'
BEGIN
	DECLARE VAR_ID_USER 					bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	DECLARE VAR_ID_CONTRATO_FATUCOPA 		bigint( 17 );
	DECLARE VAR_ID_CONTRATO_MECSAS 			bigint( 17 );
	DECLARE VAR_ID_CONTRATO_GESTANTES 		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA 	bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 	bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_GESTANTES 	bigint( 17 );

	declare VAR_ID_INPUT_TITULAR_ISENTO_GESTANTE			bigint( 17 );
	declare VAR_ID_INPUT_DEPENDENTE_ISENTO_GESTANTE			bigint( 17 );
	
	declare VAR_ID_TITULAR_ISENTO_COLS_DEF_ID_TITULAR		bigint( 17 ) default 1;
	declare VAR_ID_TITULAR_ISENTO_COLS_DEF_TP_ISENTO		bigint( 17 ) default 2;
	declare VAR_ID_DEPENDENTE_ISENTO_COLS_DEF_ID_DEPENDENTE	bigint( 17 ) default 1;
	declare VAR_ID_DEPENDENTE_ISENTO_COLS_DEF_TP_ISENTO		bigint( 17 ) default 2;

	declare VAR_ID_COLUMN_01_COD 			bigint( 17 );
	declare VAR_ID_COLUMN_02_COD_REF 		bigint( 17 );
	declare VAR_ID_COLUMN_03_PREF 			bigint( 17 );
	declare VAR_ID_COLUMN_04_COD_TITULAR	bigint( 17 );
	declare VAR_ID_COLUMN_05_COD_DEPENDENTE bigint( 17 );
	declare VAR_ID_COLUMN_06_NM_DEPENDENTE	bigint( 17 );
	declare VAR_ID_COLUMN_07_VL_PRINCIPAL	bigint( 17 );
	declare VAR_ID_COLUMN_08_SETOR 			bigint( 17 );
	declare VAR_ID_COLUMN_09_LOCAL 			bigint( 17 );
	declare VAR_ID_COLUMN_10_DT_NASCIMENTO	bigint( 17 );
	declare VAR_ID_COLUMN_11 				bigint( 17 );
	declare VAR_ID_COLUMN_12_CPF_DEPENDENTE	bigint( 17 );
	declare VAR_ID_COLUMN_13_EMPRESA		bigint( 17 );
	declare VAR_ID_COLUMN_14 				bigint( 17 );
	declare VAR_ID_COLUMN_15				bigint( 17 );

	declare VAR_ID_COLUMN_01_PREFIXO_EMPRESA 		bigint( 17 );
	declare VAR_ID_COLUMN_02_EMPRESA 				bigint( 17 );
	declare VAR_ID_COLUMN_03_NR_MATRICULA 			bigint( 17 );
	declare VAR_ID_COLUMN_04_DV 					bigint( 17 );
	declare VAR_ID_COLUMN_05_RDP 					bigint( 17 );
	declare VAR_ID_COLUMN_06_DC 					bigint( 17 );
	declare VAR_ID_COLUMN_07_COD_LOCAL 				bigint( 17 );
	declare VAR_ID_COLUMN_08_CAT_FUNCIONAL 			bigint( 17 );
	declare VAR_ID_COLUMN_09_SETOR 					bigint( 17 );
	declare VAR_ID_COLUMN_10_ESTADO_CIVIL 			bigint( 17 );
	declare VAR_ID_COLUMN_11_PLANO 					bigint( 17 );
	declare VAR_ID_COLUMN_12_PLANO_AGREG 			bigint( 17 );
	declare VAR_ID_COLUMN_13_DT_INCLUSAO 			bigint( 17 );
	declare VAR_ID_COLUMN_14_PLANO_COMPL 			bigint( 17 );
	declare VAR_ID_COLUMN_15_CPF 					bigint( 17 );
	declare VAR_ID_COLUMN_16_BANCO 					bigint( 17 );
	declare VAR_ID_COLUMN_17_AGENCIA 				bigint( 17 );
	declare VAR_ID_COLUMN_18_AGENCIA_DV	 			bigint( 17 );
	declare VAR_ID_COLUMN_19_CONTA 					bigint( 17 );
	declare VAR_ID_COLUMN_20 						bigint( 17 );
	declare VAR_ID_COLUMN_21_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_22_DT_NASCIMENTO	 		bigint( 17 );
	declare VAR_ID_COLUMN_23_SEXO 					bigint( 17 );
	declare VAR_ID_COLUMN_24_PERMANENCIA 			bigint( 17 );
	declare VAR_ID_COLUMN_25_GRAU_PARENTESCO 		bigint( 17 );
	declare VAR_ID_COLUMN_26_AGREGADO	 			bigint( 17 );
	declare VAR_ID_COLUMN_27_DT_EXCLUSAO 			bigint( 17 );
	declare VAR_ID_COLUMN_28_CARENCIA 				bigint( 17 );
	declare VAR_ID_COLUMN_29 						bigint( 17 );
	declare VAR_ID_COLUMN_30_DT_REFERENCIA 			bigint( 17 );
	declare VAR_ID_COLUMN_31_CD_ORCAO_LOTACAO 		bigint( 17 );
	declare VAR_ID_COLUMN_32_NM_ORGAO_LOTACAO 		bigint( 17 );
	declare VAR_ID_COLUMN_33	 					bigint( 17 );
	declare VAR_ID_COLUMN_34_CD_MOTIVO_EXCLUSAO 	bigint( 17 );
	declare VAR_ID_COLUMN_35_CEP	 				bigint( 17 );
	declare VAR_ID_COLUMN_36_TP_LOGRADOURO 			bigint( 17 );
	declare VAR_ID_COLUMN_37_LOGRADOURO 			bigint( 17 );
	declare VAR_ID_COLUMN_38_NUM_LOGRADOURO 		bigint( 17 );
	declare VAR_ID_COLUMN_39_COMPL 					bigint( 17 );
	declare VAR_ID_COLUMN_40_BAIRRO	 				bigint( 17 );
	declare VAR_ID_COLUMN_41_MUNICIPIO	 			bigint( 17 );
	declare VAR_ID_COLUMN_42_UF 					bigint( 17 );
	declare VAR_ID_COLUMN_43_DDD_TEL_RES	 		bigint( 17 );
	declare VAR_ID_COLUMN_44_TEL_RES	 			bigint( 17 );
	declare VAR_ID_COLUMN_45_DDD_TEL_COM 			bigint( 17 );
	declare VAR_ID_COLUMN_46_TEL_COM	 			bigint( 17 );
	declare VAR_ID_COLUMN_47_DDD_TEL_CEL			bigint( 17 );
	declare VAR_ID_COLUMN_48_TEL_CEL	 			bigint( 17 );
	declare VAR_ID_COLUMN_49_NM_MAE	 				bigint( 17 );
	declare VAR_ID_COLUMN_50_RG 					bigint( 17 );
	declare VAR_ID_COLUMN_51_ORGAO_EXPEDIDOR		bigint( 17 );
	declare VAR_ID_COLUMN_52_CD_PAIS 				bigint( 17 );
	declare VAR_ID_COLUMN_53_DT_EMISSAO 			bigint( 17 );
	declare VAR_ID_COLUMN_54_DT_EMISSAO	 			bigint( 17 );
	declare VAR_ID_COLUMN_55_NUM_PIS_PASEP	 		bigint( 17 );
	declare VAR_ID_COLUMN_56_NUM_CNS	 			bigint( 17 );
	declare VAR_ID_COLUMN_57_EMAIL	 				bigint( 17 );
	declare VAR_ID_COLUMN_58_CD_ESCOLARIDADE 		bigint( 17 );
	declare VAR_ID_COLUMN_59_CD_RENDA_FAMILIAR		bigint( 17 );
	declare VAR_ID_COLUMN_60_CD_PROFISSAO_TIT		bigint( 17 );
	declare VAR_ID_COLUMN_61_CD_PAIS_ORIGEM	 		bigint( 17 );
	declare VAR_ID_COLUMN_62_CD_IDENTIFICADOR		bigint( 17 );
	declare VAR_ID_COLUMN_63_CD_CPT	 				bigint( 17 );
	declare VAR_ID_COLUMN_64_ID_BLOQ_BENEF	 		bigint( 17 );
	declare VAR_ID_COLUMN_65_EMPRESA_TIT_AGREGADO	bigint( 17 );
	declare VAR_ID_COLUMN_66_MATRICULA_TIT_AGREGADO	bigint( 17 );
	declare VAR_ID_COLUMN_67_DECL_NASC_VIVO 		bigint( 17 );
	declare VAR_ID_COLUMN_68_TITULO_ELEITOR 		bigint( 17 );
	declare VAR_ID_COLUMN_69_NUM_RIC	 			bigint( 17 );
	declare VAR_ID_COLUMN_70_ID_CONTRIBUTARIO 		bigint( 17 );
	declare VAR_ID_COLUMN_71_QTDE_CONTRIB	 		bigint( 17 );
	declare VAR_ID_COLUMN_72_ID_EX_EMPREGADO	 	bigint( 17 );
	declare VAR_ID_COLUMN_73_ID_MANTER_PLANO	 	bigint( 17 );
	declare VAR_ID_COLUMN_74_NM_BENEF_COMPLETO 		bigint( 17 );
	declare VAR_ID_COLUMN_75_CD_OPERACAO	 		bigint( 17 );
	declare VAR_ID_COLUMN_76	 					bigint( 17 );

	declare VAR_ID_COLUMN_01_GESTANTE_MATRICULA		bigint( 17 );
	declare VAR_ID_COLUMN_02_GESTANTE_NM_GESTANTE	bigint( 17 );
	declare VAR_ID_COLUMN_03_GESTANTE_NR_CPF		bigint( 17 );
	
	declare VAR_VIEW_DESTINATION_HOC				bigint( 17 );
	declare VAR_ARQUIVO_OUTPUT_DESCONHECIDO			bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_MATRICULA 		bigint( 17 ); 
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NN_BENEFICIARIO 	bigint( 17 );
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_CPF 				bigint( 17 );
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL 		bigint( 17 );
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NN_TITULAR 			bigint( 17 );
		
	DECLARE exit handler for sqlexception
	BEGIN
		-- ERROR
		select 'Erro ao executar procedure:'
		ROLLBACK;
	END;

	START TRANSACTION;
	
	/***********************************************************************************************************************/
	
	select 'Criando a empresa:';
	
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,	
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		1, /* Sulamerica */
		'Hospital Oswaldo Cruz',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ARQCOPART',
	    'Hospital Oswaldo Cruz',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_FATUCOPA from TB_CONTRATO;

	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ARQCADBENEF',
	    'Hospital Oswaldo Cruz',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_MECSAS from TB_CONTRATO;
	
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'Isenção Corp',
	    'Hospital Oswaldo Cruz',
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO_GESTANTES from TB_CONTRATO;
	
	select 'Criando arquivos de entrada:';
	/***********************************************************************************************************************/
	
	/* FATU-COPA */
	
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
	    VAR_ID_CONTRATO_FATUCOPA,
		'^(00444)\\.([0-9]{7})\\.(ARQCOPART)_CSV_M_([0-9]{4})([0-9]{2})([0-9]{2})\\.(csv|CSV)$',
		'Arquivo de carga de coparticipação',
		2, /* CSV */
		1, /* FATUCOPA */
		3,
		null,
		
		3, /* REGEXP_CONTRATO */
		6, /* REGEXP_DIA */
		5, /* REGEXP_MES */
		4, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_FATUCOPA from TB_ARQUIVO_INPUT;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_01_COD',
		1, /* INT */
		null,
		1,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_COD from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_02_COD_REF',
		1, /* INT */
		null,
		2,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_COD_REF from TB_ARQUIVO_INPUT_COLS_DEF;
		
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_03_PREF',
		1, /* INT */
		null,
		3,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
		
	select max( ID ) into VAR_ID_COLUMN_03_PREF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_04_COD_TITULAR',
		5, /* LONG */
		null,
		4,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
		
	select max( ID ) into VAR_ID_COLUMN_04_COD_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_05_COD_DEPENDENTE',
		5, /* LONG */
		null,
		5,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_COD_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_06_NM_DEPENDENTE',
		3, /* VARCHAR */
		null,
		6,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_NM_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	
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
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_07_VL_PRINCIPAL',
		2, /* DOUBLE */
		null,
		7,
		'#0.00',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_VL_PRINCIPAL from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_08_SETOR',
		3, /* VARCHAR */
		null,
		8,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_SETOR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_09_LOCAL',
		1, /* INT */
		null,
		9,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_10_DT_NASCIMENTO',
		4, /* DATE */
		null,
		10,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_11',
		1, /* INT */
		null,
		11,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_12_CPF_DEPENDENTE',
		5, /* LONG */
		null,
		12,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_CPF_DEPENDENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_13_EMPRESA',
		1, /* INT */
		null,
		13,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_14',
		3, /* VARCHAR */
		null,
		14,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'COLUMN_15',
		3, /* VARCHAR */
		null,
		15,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	select 'Criando arquivo MECSAS:';
	
	/* MECSAS */
	
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
	    VAR_ID_CONTRATO_MECSAS,
		'^(00444)\\.([0-9]{7})\\.(ARQCADBENEF)_CSV_M_([0-9]{4})([0-9]{2})([0-9]{2})\\.(csv|CSV)$',
		'Arquivo de carga de beneficiários',
		2, /* CSV */
		2, /* MECSAS */
		3,
		null,
		
		3, /* REGEXP_CONTRATO */
		6, /* REGEXP_DIA */
		5, /* REGEXP_MES */
		4, /* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_MECSAS from TB_ARQUIVO_INPUT;	

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_01_PREFIXO_EMPRESA',
		1, /* INT */
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_PREFIXO_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_02_EMPRESA',
		1, /* INT */
		null,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_EMPRESA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_03_NR_MATRICULA',
		5, /* LONG */
		null,
		3,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_04_DV',
		1, /* INT */
		null,
		4,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_DV from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_05_RDP',
		1, /* INT */
		null,
		5,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_RDP from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_06_DC',
		1, /* INT */
		null,
		6,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_DC from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_07_COD_LOCAL',
		1, /* INT */
		null,
		7,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_COD_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_08_CAT_FUNCIONAL',
		1, /* INT */
		null,
		8,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_CAT_FUNCIONAL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_09_SETOR',
		3, /* VARCHAR */
		null,
		9,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_SETOR from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_10_ESTADO_CIVIL',
		3, /* VARCHAR */
		null,
		10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_ESTADO_CIVIL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_11_PLANO',
		1, /* INT */
		null,
		11,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_PLANO from TB_ARQUIVO_INPUT_COLS_DEF; 

	select 'Breakpoint 01';
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_12_PLANO_AGREG',
		1, /* INT */
		null,
		12,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_PLANO_AGREG from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_13_DT_INCLUSAO',
		4, /* DATE */
		null,
		13,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_DT_INCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_14_PLANO_COMPL',
		1, /* INT */
		null,
		14,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_PLANO_COMPL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_15_CPF',
		5, /* LONG */
		null,
		15,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_16_BANCO',
		1, /* INT */
		null,
		16,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_BANCO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_17_AGENCIA',
		1, /* INT */
		null,
		17,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_AGENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_18_AGENCIA_DV',
		1, /* INT */
		null,
		18,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_AGENCIA_DV from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_19_CONTA',
		3, /* VARCHAR */
		null,
		19,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_CONTA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_20',
		1, /* INT */
		null,
		20,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20 from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_21_NM_BENEFICIARIO',
		3, /* VARCHAR */
		null,
		21,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_22_DT_NASCIMENTO',
		4, /* DATE */
		null,
		22,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_23_SEXO',
		3, /* VARCHAR */
		null,
		23,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_SEXO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_24_PERMANENCIA',
		3, /* VARCHAR */
		null,
		24,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_24_PERMANENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_25_GRAU_PARENTESCO',
		1, /* INT */
		null,
		25,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_25_GRAU_PARENTESCO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_25_AGREGADO',
		3, /* VARCHAR */
		null,
		26,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_26_AGREGADO from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_27_DT_EXCLUSAO',
		4, /* DATE */
		null,
		27,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_27_DT_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_28_CARENCIA',
		1, /* INT */
		null,
		28,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_28_CARENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_29',
		3, /* VARCHAR */
		null,
		29,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_29 from TB_ARQUIVO_INPUT_COLS_DEF; 

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
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_30_DT_REFERENCIA',
		4, /* DATE */
		null,
		30,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_30_DT_REFERENCIA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_31_CD_ORGAO_LOTACAO',
		1, /* INT */
		null,
		31,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_31_CD_ORCAO_LOTACAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_32_NM_ORGAO_LOCACAO',
		3, /* VARCHAR */
		null,
		32,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_32_NM_ORGAO_LOTACAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_33',
		3, /* VARCHAR */
		null,
		33,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_33 from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_34_CD_MOTIVO_EXCLUSAO',
		1, /* INT */
		null,
		34,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_34_CD_MOTIVO_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_35_CEP',
		1, /* INT */
		null,
		35,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_35_CEP from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_36_TP_LOGRADOURO',
		3, /* VARCHAR */
		null,
		36,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_36_TP_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_37_LOGRADOURO',
		3, /* VARCHAR */
		null,
		37,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_37_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_38_NUM_LOGRADOURO',
		3, /* VARCHAR */
		null,
		38,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_38_NUM_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_39_COMPL',
		3, /* VARCHAR */
		null,
		39,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_39_COMPL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_40_BAIRRO',
		3, /* VARCHAR */
		null,
		40,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_40_BAIRRO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_41_MUNICIPIO',
		3, /* VARCHAR */
		null,
		41,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_41_MUNICIPIO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_42_UF',
		3, /* VARCHAR */
		null,
		42,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_42_UF from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_43_DDD_TEL_RES',
		1, /* INT */
		null,
		43,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_43_DDD_TEL_RES from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_44_TEL_RES',
		1, /* INT */
		null,
		44,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_44_TEL_RES from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_45_DDD_TEL_COM',
		1, /* INT */
		null,
		45,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_45_DDD_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_46_TEL_COM',
		1, /* INT */
		null,
		46,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_46_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_47_DDD_TEL_CEL',
		1, /* INT */
		null,
		47,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_47_DDD_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_48_TEL_CEL',
		1, /* INT */
		null,
		48,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_48_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_49_NM_MAE',
		3, /* VARCHAR */
		null,
		49,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_49_NM_MAE from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_50_RG',
		3, /* VARCHAR */
		null,
		50,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_50_RG from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_51_CD_ORGAO_EXPEDIDOR',
		3, /* VARCHAR */
		null,
		51,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_51_ORGAO_EXPEDIDOR from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_52_CD_PAI',
		1, /* INT */
		null,
		52,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_52_CD_PAIS from TB_ARQUIVO_INPUT_COLS_DEF;

	
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
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_53_DT_EMISSAO',
		4, /* DATE */
		null,
		53,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_53_DT_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_54_UF_EMISSAO',
		3, /* VARCHAR */
		null,
		54,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_54_DT_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_55_NUM_PIS_PASEP',
		5, /* LONG */
		null,
		55,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_55_NUM_PIS_PASEP from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_56_NUM_CNS',
		3, /* VARCHAR */
		null,
		56,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_56_NUM_CNS from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_57_EMAIL',
		3, /* VARCHAR */
		null,
		57,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_57_EMAIL from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_58_CD_ESCOLARIDADE',
		1, /* INT */
		null,
		58,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_58_CD_ESCOLARIDADE from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_59_CD_RENDA_FAMILIAR',
		1, /* INT */
		null,
		59,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_59_CD_RENDA_FAMILIAR from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_60_CD_PROFISSAO_TIT',
		1, /* INT */
		null,
		60,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_60_CD_PROFISSAO_TIT from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_61_CD_PAIS_ORIGEM',
		1, /* INT */
		null,
		61,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_61_CD_PAIS_ORIGEM from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_62_CD_IDENTIFICADOR',
		1, /* INT */
		null,
		62,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_62_CD_IDENTIFICADOR from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_63_CD_CPT',
		3, /* VARCHAR */
		null,
		63,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_63_CD_CPT from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_64_ID_BLOQ_BENEF',
		3, /* VARCHAR */
		null,
		64,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_64_ID_BLOQ_BENEF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_65_EMPRESA_TIT_AGREGADO',
		3, /* VARCHAR */
		null,
		65,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_65_EMPRESA_TIT_AGREGADO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_66_MATRICULA_TIT_AGREGADO',
		3, /* VARCHAR */
		null,
		66,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_66_MATRICULA_TIT_AGREGADO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_67_DECL_NASC_VIVO',
		
		3, /* VARCHAR */
		null,
		67,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_67_DECL_NASC_VIVO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_68_TITULO_ELEITOR',
		3, /* VARCHAR */
		null,
		68,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_68_TITULO_ELEITOR from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_69_NUM_RIC',
		3, /* VARCHAR */
		null,
		69,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_69_NUM_RIC from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_70_ID_CONTRIBUTARIO',
		3, /* VARCHAR */
		null,
		70,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_70_ID_CONTRIBUTARIO from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_71_QTDE_CONTRIB',
		1, /* INT */
		null,
		71,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_71_QTDE_CONTRIB from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_72_ID_EX_EMPREGADO',
		3, /* VARCHAR */
		null,
		72,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_72_ID_EX_EMPREGADO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_73_ID_MANTER_PLANO',
		3, /* VARCHAR */
		null,
		73,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_73_ID_MANTER_PLANO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_74_NM_BENEF_COMPLETO',
		3, /* VARCHAR */
		null,
		74,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_74_NM_BENEF_COMPLETO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_75_CD_OPERACAO',
		1, /* INT */
		null,
		75,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_75_CD_OPERACAO from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_MECSAS,
		'COLUMN_76',
		3, /* VARCHAR */
		null,
		76,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_76 from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	/************************************************************************************************************************************************/
	/* Inputs */
	
	select 'Criando INPUT_LANCAMENTO:';
	
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		1, /* ID_BENEFICIARIO */
		VAR_ID_COLUMN_12_CPF_DEPENDENTE,				
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select 'Criando INPUT_LANCAMENTO_02:';
	insert into TB_INPUT_LANCAMENTO (
		ID_LANCAMENTO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		3, /* ID_CONTRATO */				
		VAR_ID_COLUMN_13_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	/* MECSAS */
	
	select 'Criando INPUT_TITULAR';
	
	insert into TB_INPUT_TITULAR(
		ID_TITULAR_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		1,
		VAR_ID_COLUMN_03_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select 'Criando INPUT_TITULAR_02';
	
	insert into TB_INPUT_TITULAR(
		ID_TITULAR_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		2,
		VAR_ID_COLUMN_21_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select 'Criando INPUT_TITULAR_03';
	
	insert into TB_INPUT_TITULAR(
		ID_TITULAR_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		3,
		VAR_ID_COLUMN_15_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select 'Criando INPUT_TITULAR_04';
	
	insert into TB_INPUT_TITULAR(
		ID_TITULAR_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		4,
		VAR_ID_COLUMN_22_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select 'Criando INPUT_TITULAR_05';
	
	insert into TB_INPUT_TITULAR(
		ID_TITULAR_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		5,
		VAR_ID_COLUMN_13_DT_INCLUSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select 'Criando INPUT_DEPENDENTE';
	
	insert into TB_INPUT_DEPENDENTE(
		ID_DEPENDENTE_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		1, /* TP_DEPENDENTE */
		VAR_ID_COLUMN_25_GRAU_PARENTESCO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
		
	);
	
	insert into TB_INPUT_DEPENDENTE(
		ID_DEPENDENTE_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		2, /* NM_DEPENDENTE */
		VAR_ID_COLUMN_21_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
		
	);
	
	insert into TB_INPUT_DEPENDENTE(
		ID_DEPENDENTE_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		3, /* NR_CPF */
		VAR_ID_COLUMN_15_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
		
	);
	
	insert into TB_INPUT_DEPENDENTE(
		ID_DEPENDENTE_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		4, /* DT_NASCIMENTO */
		VAR_ID_COLUMN_22_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_INPUT_DEPENDENTE(
		ID_DEPENDENTE_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		5, /* MATRICULA */
		VAR_ID_COLUMN_03_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	/************************************************************************************************************************************************/
	/* Isentos */
	
	select 'Criando arquivo de gestantes';
	
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
	    VAR_ID_CONTRATO_GESTANTES,
		'^(Isenção\\WCop)\\W(janeiro|fevereiro|março|abril|maio|junho|julho|agosto|setembro|outubro|novembro|dezembro)\\W([0-9]{4})\\.(xlsx)$',
		'Arquivo de carga ds gestantes do Oswaldo Cruz',
		3, /* SPREADSHEET */
		2, /* MECSAS */
		3,
		null,
		
		1, 		/* REGEXP_CONTRATO */
		null, 	/* REGEXP_DIA */
		null, 	/* REGEXP_MES */
		3,	 	/* REGEXP_ANO */
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_GESTANTES from TB_ARQUIVO_INPUT;

	select 'Criando ARQUIVO_INPUT_COLS_DEF para gestantes';
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_GESTANTES,
		'COLUMN_01_MATRICULA',
		5, /* LONG */
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_GESTANTE_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_GESTANTES,
		'COLUMN_02_NM_GESTANTE',
		3, /* VARCHAR */
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_GESTANTE_NM_GESTANTE from TB_ARQUIVO_INPUT_COLS_DEF; 

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_GESTANTES,
		'COLUMN_03_NR_CPF',
		5, /* LONG */
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_GESTANTE_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF; 
	
	select 'Criando INPUT_TITULAR_ISENTO para gestantes';
	
	insert into TB_INPUT_TITULAR_ISENTO(
		ID_ARQUIVO_INPUT,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_GESTANTES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_INPUT_TITULAR_ISENTO_GESTANTE from TB_INPUT_TITULAR_ISENTO;
	
	select 'Criando INPUT_TITULAR_ISENTO_COLS para gestantes';
	insert into TB_INPUT_TITULAR_ISENTO_COLS(
	 	ID_INPUT_TITULAR_ISENTO,
	 	ID_ARQUIVO_INPUT_COLS_DEF,
	 	ID_TITULAR_ISENTO_COLS_DEF,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (			
		VAR_ID_INPUT_TITULAR_ISENTO_GESTANTE,
		VAR_ID_COLUMN_03_GESTANTE_NR_CPF,
		VAR_ID_TITULAR_ISENTO_COLS_DEF_ID_TITULAR,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select 'Criando INPUT_TITULAR_ISENTO_COLS_02 para gestantes';
	insert into TB_INPUT_TITULAR_ISENTO_COLS(
	 	ID_INPUT_TITULAR_ISENTO,
	 	ID_ARQUIVO_INPUT_COLS_DEF,
	 	ID_TITULAR_ISENTO_COLS_DEF,
	 	TP_ISENTO,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (			
		VAR_ID_INPUT_TITULAR_ISENTO_GESTANTE,
		VAR_ID_COLUMN_01_GESTANTE_MATRICULA,
		VAR_ID_TITULAR_ISENTO_COLS_DEF_TP_ISENTO,
		1, /* GESTANTE */

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select 'Criando INPUT_DEPENDENTE_ISENTO para gestantes';
	
	insert into TB_INPUT_DEPENDENTE_ISENTO(
		ID_ARQUIVO_INPUT,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_GESTANTES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_INPUT_DEPENDENTE_ISENTO_GESTANTE from TB_INPUT_DEPENDENTE_ISENTO;
	
	select 'Criando INPUT_DEPENDENTE_ISENTO_COLS_01 para gestantes';
	
	insert into TB_INPUT_DEPENDENTE_ISENTO_COLS(
	 	ID_INPUT_DEPENDENTE_ISENTO,
	 	ID_ARQUIVO_INPUT_COLS_DEF,
	 	ID_DEPENDENTE_ISENTO_COLS_DEF,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (			
		VAR_ID_INPUT_DEPENDENTE_ISENTO_GESTANTE,
		VAR_ID_COLUMN_03_GESTANTE_NR_CPF,
		VAR_ID_DEPENDENTE_ISENTO_COLS_DEF_ID_DEPENDENTE,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	select 'Criando INPUT_DEPENDENTE_ISENTO_COLS_02 para gestantes';
	
	insert into TB_INPUT_DEPENDENTE_ISENTO_COLS(
	 	ID_INPUT_DEPENDENTE_ISENTO,
	 	ID_ARQUIVO_INPUT_COLS_DEF,
	 	ID_DEPENDENTE_ISENTO_COLS_DEF,
	 	TP_ISENTO,
	
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (			
		VAR_ID_INPUT_DEPENDENTE_ISENTO_GESTANTE,		
		VAR_ID_COLUMN_01_GESTANTE_MATRICULA,
		VAR_ID_DEPENDENTE_ISENTO_COLS_DEF_TP_ISENTO,
		1, /* GESTANTE */

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	/************************************************************************************************************************************************/	
	/* Regra */

	
	/************************************************************************************************************************************************/	
	/* Views Destinations - Reports */
	
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
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;
	
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_TITULAR',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_DEPENDENTE',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NM_USUARIO',
		3, /* VARCHAR */
		60,
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
		VAR_VIEW_DESTINATION_HOC,
		'TOT_COPART',
		2, /* DOUBLE */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'DT_NASCIMENTO',
		4, /* DATE */
		10,
		5,
		'DT_BASCIMENTO',
		
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
		VAR_VIEW_DESTINATION_HOC,
		'CPF_DEPENDENTE',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
		7,
		'NR_MATRICULA',
		
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
		'VW_ISENCAO_GESTANTES_HOC',
		'Isenção de Gestantes',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;	
	
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'SEGURADA',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'VALOR_ISENTO',
		5, /* LONG */
		17,
		3,
		'VALOR_ISENTO',
		
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
		'VW_ISENCAO_CONSELHEIROS_HOC',
		'Isenção de Conselheiros',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;		
	
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_DEPENDENTE',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NOME_USUÁRIO',
		3, /* VARCHAR */
		80,
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
		VAR_VIEW_DESTINATION_HOC,
		'TOT_COPART',
		2, /* DOUBLE */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'DT_NASCIMENTO',
		4, /* DATE */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'CPF_DEPENDENTE',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
		6,
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
		'VW_AFASTADOS_HOC',
		'Afastados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;			
	
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_TITULAR',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_DEPENDENTE',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NN_USUARIO',
		3, /* VARCHAR */
		60,
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
		VAR_VIEW_DESTINATION_HOC,
		'TOTAL_COPART',
		2, /* DOUBLE */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'LOCAL',
		1, /* INT */
		10,
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
		VAR_VIEW_DESTINATION_HOC,
		'DT_NASCIMENTO',
		5, /* LONG */
		10,
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
		VAR_VIEW_DESTINATION_HOC,
		'CPF_DEPENDENTE',
		5, /* LONG */
		15,
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
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
		'VW_AGREGADOS_HOC',
		'Agregados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;	
	
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'NN_USUARIO',
		3, /* VARCHAR */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'TOT_COPART',
		2, /* DOUBLE */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'LOCAL',
		1, /* INT */
		10,
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
		VAR_VIEW_DESTINATION_HOC,
		'DT_NASCIMENTO',
		5, /* DATE */
		15,
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
		VAR_VIEW_DESTINATION_HOC,
		'CPF',
		5, /* LONG */
		15,
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
		'VW_PLANO_EXTENCAO_HOCC',
		'Plano de Extenção',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;	
	
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_TITULAR',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'COD_DEPENDENTEF',
		5, /* LONG */
		17,
		2,
		'COD_DEPENDENTE',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	select 'checkpoint - 0025';
	
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
		VAR_VIEW_DESTINATION_HOC,
		'NOME_USUARIO',
		3, /* VARCHAR */
		80,
		6
		3,
		'NONE_USUARIO',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	
	select 'checkpoint - 0026';
	
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
		VAR_VIEW_DESTINATION_HOC,
		'TOT_COPART',
		2, /* DOUBLE */
		17,
		4,
		'TOT_COPART',
		
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
		VAR_VIEW_DESTINATION_HOC,
		'LOCAL',
		1, /* INT */
		10,
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
		VAR_VIEW_DESTINATION_HOC,
		'DT_NASCIMENTO',
		5, /* DATE */
		15,
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
		VAR_VIEW_DESTINATION_HOC,
		'CPF_DEPENDENTE',
		5, /* LONG */
		15,
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
		VAR_VIEW_DESTINATION_HOC,
		'MATRICULA',
		5, /* LONG */
		17,
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
		'VW_PRN_HOC',
		'PRN',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_VIEW_DESTINATION_HOC from TB_VIEW_DESTINATION;	
	
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
		VAR_VIEW_DESTINATION_HOC,
		'NR_MATRICULA',
		5, /* LONG */
		17,
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
		VAR_VIEW_DESTINATION_HOC,
		'VL_PRINCIPAL',
		5, /* DOUBLE */
		17,
		2,
		'VL_PRINCIPAL',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);							
	
	
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'HOC-coparticipacao-{YYYY}{MM}.xlsx',
		'Arquivo de saída para a carga dos lançamentos HOC',
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	
	select nax( ID ) into VAR_ARQUIVO_OUTPUT_HOC from TB_ARQUIVO_OUTPUT;
	
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		3,
		'Coparticipação',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
		
	
	
	/****************************************************************************************************************************/
	/* Desconhecidos */
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		NM_DESCR_ARQUIVO,	
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_FATUCOPA,
		'HOC-NAO-LOCALIZADOS-{YYYY}{MM}.xlsx',
		'Arquivo com os beneficiários não localizados pelo processo',
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select nax( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ARQUIVO_OUTPUT_DESCONHECIDO
		'NR_MATRICULA',
		5, /* LONG  */
		8,
		1,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_MATRICULA from TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ARQUIVO_OUTPUT_DESCONHECIDO
		'NM_BENEFICIARIO',
		3, /*VARCHAR  */
		32,
		2,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NM_BENEFICIARIO from TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ARQUIVO_OUTPUT_DESCONHECIDO
		'NM_TITULAR',
		3, /*VARCHAR  */
		32,
		3,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_TITULAR from TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ARQUIVO_OUTPUT_DESCONHECIDO
		'NR_CPF',
		5, /* LONG */
		12,
		4,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_NR_CPF from TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
	
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ARQUIVO_OUTPUT_DESCONHECIDO
		'VL_PRINCIPAL',
		2, /* DOUBLE */
		15,
		5,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL from TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
	
	/* BIND */
	
	insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT_COLS_DEF,
		ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		45, /* NR_MATRICULA */
		1,
	
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT_COLS_DEF,
		ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		30, /* NM_BENEFICIARIO */
		2,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT_COLS_DEF,
		ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		44, /* NM_TITULAR */
		3,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT_COLS_DEF,
		ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		32, /* NR_CPF */
		4,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT_COLS_DEF,
		ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		37, /* VL_PRINCIPAL */
		5,
		
		1,
		current_timestamp(),
		current_timestamp()		
	);	
	
	
	
	
	
	
	COMMIT;
	
	select 'Alterações executadas com sucesso.';
END
$$

call PROC_CREATE_HOC(); 
