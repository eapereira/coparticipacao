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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180703-002-dml-muito-facil-8CHE8.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180703-003-dml-muito-facil-MECSAS.sql';
	
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
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_COLUMN_01_NR_LINHA 					bigint( 17 );
	declare VAR_COLUMN_02_CD_CONTRATO 				bigint( 17 );
	declare VAR_COLUMN_03_NR_MATRICULA 				bigint( 17 );
	declare VAR_COLUMN_04_DF 						bigint( 17 );
	declare VAR_COLUMN_05_RDP 						bigint( 17 );
	declare VAR_COLUMN_06_LOCAL 					bigint( 17 );
	declare VAR_COLUMN_07_CATEGORIA	 				bigint( 17 );
	declare VAR_COLUMN_08_SETOR 					bigint( 17 );
	declare VAR_COLUMN_09_ES 						bigint( 17 );
	declare VAR_COLUMN_10_PLANO 					bigint( 17 );
	declare VAR_COLUMN_11_DT_ADESAO 				bigint( 17 );
	declare VAR_COLUMN_12_NR_CPF 					bigint( 17 );
	declare VAR_COLUMN_13_NM_BENEFICIARIO 			bigint( 17 );
	declare VAR_COLUMN_14_DT_NASCIMENTO 			bigint( 17 );
	declare VAR_COLUMN_15_SEXO 						bigint( 17 );
	declare VAR_COLUMN_16_PERMANENCIA 				bigint( 17 );
	declare VAR_COLUMN_17_GP 						bigint( 17 );
	declare VAR_COLUMN_18_DT_ADMISSAO 				bigint( 17 );
	declare VAR_COLUMN_19_DT_REF 					bigint( 17 );
	declare VAR_COLUMN_20_BANCO 					bigint( 17 );
	declare VAR_COLUMN_21_AGENCIA 					bigint( 17 );
	declare VAR_COLUMN_22_DG_AGENCIA 				bigint( 17 );
	declare VAR_COLUMN_23_CONTA_CORRENTE 			bigint( 17 );
	declare VAR_COLUMN_24_NR_CPF_CONTA_CORRENTE 	bigint( 17 );
	declare VAR_COLUMN_25_NM_TITULAR_CC 			bigint( 17 );
	declare VAR_COLUMN_26_CODCARDIF 				bigint( 17 );
	declare VAR_COLUMN_27_NR_CEP 					bigint( 17 );
	declare VAR_COLUMN_28_TP_LOGRADOURO 			bigint( 17 );
	declare VAR_COLUMN_29_LOGRADOURO 				bigint( 17 );
	declare VAR_COLUMN_30_NR_NUMERO 				bigint( 17 );
	declare VAR_COLUMN_31_COMP_LOGRADOURO 			bigint( 17 );
	declare VAR_COLUMN_32_BAIRRO	 				bigint( 17 );
	declare VAR_COLUMN_33_MUNICIPIO 				bigint( 17 );
	declare VAR_COLUMN_34_UF 						bigint( 17 );
	declare VAR_COLUMN_35_TEL_RESID 				bigint( 17 );
	declare VAR_COLUMN_36_TEL_COM 					bigint( 17 );
	declare VAR_COLUMN_37_TEL_CEL 					bigint( 17 );
	declare VAR_COLUMN_38_NM_MAE 					bigint( 17 );
	declare VAR_COLUMN_39_NR_RG 					bigint( 17 );
	declare VAR_COLUMN_40_ORGAO_EMISSOR 			bigint( 17 );
	declare VAR_COLUMN_41_PAIS_EMISSOR 				bigint( 17 );
	declare VAR_COLUMN_42_DT_EMISSAO 				bigint( 17 );
	declare VAR_COLUMN_43_ESTADO_RG 				bigint( 17 );
	declare VAR_COLUMN_44_NR_PIS 					bigint( 17 );
	declare VAR_COLUMN_45_CNS 						bigint( 17 );
	declare VAR_COLUMN_46_EMAIL 					bigint( 17 );
	declare VAR_COLUMN_47_GRAU_ESCOLARIDADE 		bigint( 17 );
	declare VAR_COLUMN_48_RENDA_FAMILIAR 			bigint( 17 );
	declare VAR_COLUMN_49_CD_PROFISSAO 				bigint( 17 );
	declare VAR_COLUMN_50_PAIS_ORIGEM 				bigint( 17 );
	declare VAR_COLUMN_51_DT_EXCLUSAO 				bigint( 17 );
	declare VAR_COLUMN_52_CD_MOVTO_EXCLUSAO 		bigint( 17 );
	declare VAR_COLUMN_53_CD_OPERACAO 				bigint( 17 );
	declare VAR_COLUMN_54_CD_EMPRESA_TRANSF 		bigint( 17 );
	declare VAR_COLUMN_55_MATRICULA_TRANSF 			bigint( 17 );
	declare VAR_COLUMN_56_LOCAL_TRANSF 				bigint( 17 );
	declare VAR_COLUMN_57_CART_TRANSF				bigint( 17 );
	declare VAR_COLUMN_58_PLANO_TRANSF 				bigint( 17 );
	declare VAR_COLUMN_59_MOT_EMISSAO 				bigint( 17 );
	declare VAR_COLUMN_60_CPF_NOVO_TITULAR 			bigint( 17 );
	declare VAR_COLUMN_61_QTDE_PERM_MESES 			bigint( 17 );
	declare VAR_COLUMN_62_RDP_NOVO_TITULAR 			bigint( 17 );
	declare VAR_COLUMN_63_DT_INICIO_TRANSF 			bigint( 17 );
	declare VAR_COLUMN_64_CD_STATUS 				bigint( 17 );
	declare VAR_COLUMN_65_CD_ERRO 					bigint( 17 );
	declare VAR_COLUMN_66_CD_DV 					bigint( 17 );
	declare VAR_COLUMN_67_BLOQ_EMPR_INADIMPLENCIA 	bigint( 17 );
	declare VAR_COLUMN_68_CPT 						bigint( 17 );
	declare VAR_COLUMN_69_CD_EMPR_TITULAR 			bigint( 17 );
	declare VAR_COLUMN_70_NR_MATRICULA_02 			bigint( 17 );
	declare VAR_COLUMN_71_DIF_MATRICULA_TITULAR 	bigint( 17 );
	declare VAR_COLUMN_72_NR_TITULO_ELEITOR 		bigint( 17 );
	declare VAR_COLUMN_73_NR_RIC 					bigint( 17 );
	declare VAR_COLUMN_74_NR_DECL_NASCIDO_VIVO 		bigint( 17 );
	declare VAR_COLUMN_75_CART_IDENTIFICACAO 		bigint( 17 );
	declare VAR_COLUMN_76_INDIC_SEGURADO_CONTRIB 	bigint( 17 );
	declare VAR_COLUMN_77_INDIC_COND_EXEMPREGADO	bigint( 17 );
	declare VAR_COLUMN_78_INDIC_PERM_PLANO 			bigint( 17 );
	declare VAR_COLUMN_79_QTDE_MESES_CONTRIB 		bigint( 17 );
	declare VAR_COLUMN_80_NM_COMPL_BENEFICIARIO 	bigint( 17 );
	declare VAR_COLUMN_81_INDIC_TITULAR_REMIDO 		bigint( 17 );
	declare VAR_COLUMN_82_EMAIL_SEGURADORA 			bigint( 17 );
	declare VAR_COLUMN_83_INDIC_PORTABILIDADE 		bigint( 17 );
	declare VAR_COLUMN_84_INDIC_PORTABILIDADE_02 	bigint( 17 );
	declare VAR_COLUMN_85_INDIC_CARENCIA 			bigint( 17 );
	declare VAR_COLUMN_86_CD_PRODUTO 				bigint( 17 );
	declare VAR_COLUMN_87_CD_PLANO_ANTERIOR_SAS 	bigint( 17 );
	declare VAR_COLUMN_88_CID01 					bigint( 17 );
	declare VAR_COLUMN_89_CID02 					bigint( 17 );
	declare VAR_COLUMN_90_CID03 					bigint( 17 );
	declare VAR_COLUMN_91_CID04 					bigint( 17 );
	declare VAR_COLUMN_92_CID05 					bigint( 17 );
	declare VAR_COLUMN_93_CID06 					bigint( 17 );
	declare VAR_COLUMN_94_CID07 					bigint( 17 );
	declare VAR_COLUMN_95_CID08 					bigint( 17 );
	declare VAR_COLUMN_96_CID09 					bigint( 17 );
	declare VAR_COLUMN_97_CID10 					bigint( 17 );
	declare VAR_COLUMN_98_IBGE 						bigint( 17 );
	declare VAR_COLUMN_99_CBO 						bigint( 17 );
	declare VAR_COLUMN_100_DIF_TRANSF 				bigint( 17 );

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
	declare VAR_ID_VIEW_DESTINATION_8CHE8								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_8CH5Y								bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;

	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;
	
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'Base de Ativos do Cliente';
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção';
	
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

    call PROC_LOG_MESSAGE('LINHA - 134');

    /**
    * Muito Fácil
    */
    call PROC_LOG_MESSAGE('LINHA - 139');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'MUITO-FACIL';
    	
	call PROC_LOG_MESSAGE('LINHA - 157');
    insert into TB_CONTRATO(
        ID_EMPRESA,
        CD_CONTRATO,
        NM_CONTRATO,	
        DESCR_CONTRATO,
        TP_USO,	
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_EMPRESA,
        'MECSAS',
        'MECSAS',
        VAR_NM_CONTRATO_MECSAS,
        VAR_USE_TYPE_MECSAS,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;    

    /*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 250');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'MECSAS';    
    
    call PROC_LOG_MESSAGE('LINHA - 255');
    /*****************************************************************************************************************************************************/
	/* MECSAS */
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
        '^(MUITO\\-FACIL)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(csv|CSV)',
        'Arquivo de carga de coparticipação',
        2, /* CSV */
        2, /* MECSAS */
        2,
        null, /* Não é usado para arquivo CSV */
                
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
    
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
        'NUM_LINHA',
        VAR_COL_INT,
        null,
        1,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_01_NR_LINHA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 314');
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
        'COD_EMP',
        VAR_COL_VARCHAR,
        null,
        2,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_02_CD_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 339');
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
        'MATRICULA',
        VAR_COL_LONG,
        null,
        3,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_03_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 362');
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
        'DF',
        VAR_COL_INT,
        null,
        4,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_04_DF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 385');
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
        'RDP',
        VAR_COL_INT,
        null,
        5,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_05_RDP from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 408');
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
        'LOCAL',
        VAR_COL_INT,
        null,
        6,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_06_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 431');
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
        'CATEGORIA',
        VAR_COL_INT,
        null,
        7,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_07_CATEGORIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 454');
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
        'SETOR',
        VAR_COL_VARCHAR,
        null,
        8,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_08_SETOR from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 477');
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
        'ES',
        VAR_COL_VARCHAR,
        null,
        9,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_09_ES from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 500');
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
        'PLANO',
        VAR_COL_INT,
        null,
        10,
                
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_10_PLANO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 523');
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
        'DATA_ADESAO',
        VAR_COL_DATE,
        null,
        11,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_11_DT_ADESAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 549');
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
        'CPF',
        VAR_COL_LONG,
        null,
        12,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_12_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 575');
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
        'NOME_BENEF',
        VAR_COL_VARCHAR,
        null,
        13,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_13_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 598');
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
        'DATA_NASC',
        VAR_COL_DATE,
        null,
        14,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_14_DT_NASCIMENTO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 624');
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
        'SEXO',
        VAR_COL_VARCHAR,
        null,
        15,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_15_SEXO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 647');
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
        'PERMANENCIA',
        VAR_COL_VARCHAR,
        null,
        16,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_16_PERMANENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 678');
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
        'GP',
        VAR_COL_INT,
        null,
        17,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_17_GP from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 692');
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
        'DATA_ADM',
        VAR_COL_DATE,
        null,
        18,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_18_DT_ADMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 719');
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
        'DATA_REF',
        VAR_COL_DATE,
        null,
        19,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_19_DT_REF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 744');
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
        'BANCO',
        VAR_COL_INT,
        null,
        20,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_20_BANCO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 768');
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
        'AGENCIA',
        VAR_COL_INT,
        null,
        21,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_21_AGENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 791');
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
        'DG_AGEN',
        VAR_COL_INT,
        null,
        22,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_22_DG_AGENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 814');
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
        'CONTA_CORRENTE',
        VAR_COL_VARCHAR,
        null,
        23,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_23_CONTA_CORRENTE from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 837');
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
        'CPF_CONTA_CORRENTE',
        VAR_COL_LONG,
        null,
        24,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_24_NR_CPF_CONTA_CORRENTE from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 863');
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
        'NOME_TITULAR_CC',
        VAR_COL_VARCHAR,
        null,
        25,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_25_NM_TITULAR_CC from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 886');
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
        'CODCARDIF',
        VAR_COL_VARCHAR,
        null,
        26,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_26_CODCARDIF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 909');
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
        'NUM_CEP',
        VAR_COL_INT,
        null,
        27,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_27_NR_CEP from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 932');
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
        'TIPO_LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        28,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_28_TP_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 955');
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
        'LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        29,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_29_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 978');
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
        'NUMERO',
        VAR_COL_INT,
        null,
        30,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_30_NR_NUMERO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1001');
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
        'COMP_LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        31,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_31_COMP_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1024');
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
        'BAIRRO',
        VAR_COL_VARCHAR,
        null,
        32,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_32_BAIRRO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1047');
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
        'MUNICIPIO',
        VAR_COL_VARCHAR,
        null,
        33,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_33_MUNICIPIO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1070');
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
        'UF',
        VAR_COL_VARCHAR,
        null,
        34,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_34_UF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1093');
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
        'TEL_RESID',
        VAR_COL_VARCHAR,
        null,
        35,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_35_TEL_RESID from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1115');
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
        'TEL_COM',
        VAR_COL_VARCHAR,
        null,
        36,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_36_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1138');
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
        'TEL_CEL',
        VAR_COL_VARCHAR,
        null,
        37,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_37_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1162');
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
        'NOMEDAMAEBENE',
        VAR_COL_VARCHAR,
        null,
        38,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_38_NM_MAE from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1185');
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
        'RG',
        VAR_COL_VARCHAR,
        null,
        39,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_39_NR_RG from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1208');
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
        'ORGAOEMISSORG',
        VAR_COL_VARCHAR,
        null,
        40,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_40_ORGAO_EMISSOR from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1231');
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
        'PAISEMISSORRG',
        VAR_COL_VARCHAR,
        null,
        41,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_41_PAIS_EMISSOR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'DATAEMISSAORG',
        VAR_COL_DATE,
        null,
        42,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_42_DT_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'ESTADORG',
        VAR_COL_VARCHAR,
        null,
        43,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_43_ESTADO_RG from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'PIS',
        VAR_COL_VARCHAR,
        null,
        44,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_44_NR_PIS from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CNS',
        VAR_COL_VARCHAR,
        null,
        45,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_45_CNS from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'E-MAIL',
        VAR_COL_VARCHAR,
        null,
        46,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_46_EMAIL from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'GRAUESCOLARIDADE',
        VAR_COL_INT,
        null,
        47,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_47_GRAU_ESCOLARIDADE from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1390');
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
        'RENDAFAMILIAR',
        VAR_COL_INT,
        null,
        48,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_48_RENDA_FAMILIAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CDPROFISSAO',
        VAR_COL_INT,
        null,
        49,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_49_CD_PROFISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CDPAISDEORIGEM',
        VAR_COL_INT,
        null,
        50,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_50_PAIS_ORIGEM from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'DATAEXCLUSAO',
        VAR_COL_DATE,
        null,
        51,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_51_DT_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CODMOVEXCLUSAO',
        VAR_COL_INT,
        null,
        52,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_52_CD_MOVTO_EXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CODOPERACAO',
        VAR_COL_INT,
        null,
        53,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_53_CD_OPERACAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CODEMPRESATRANSF',
        VAR_COL_INT,
        null,
        54,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_54_CD_EMPRESA_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'MATRICULATRANSF',
        VAR_COL_INT,
        null,
        55,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_55_MATRICULA_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1571');
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
        'LOCALTRANSF',
        VAR_COL_VARCHAR,
        null,
        56,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_56_LOCAL_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CATTRANSF',
        VAR_COL_INT,
        null,
        57,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_57_CART_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'PLANOTRANSF',
        VAR_COL_INT,
        null,
        58,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_58_PLANO_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'MOTREMISSAO',
        VAR_COL_VARCHAR,
        null,
        59,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_59_MOT_EMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1660');
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
        'CPFNOVOTITULAR',
        VAR_COL_LONG,
        null,
        60,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_60_CPF_NOVO_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'QTDPERMAMESES',
        VAR_COL_INT,
        null,
        61,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_61_QTDE_PERM_MESES from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'RDPNOVOTITULAR',
        VAR_COL_VARCHAR,
        null,
        62,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_62_RDP_NOVO_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'DTINICIOTRANSF',
        VAR_COL_DATE,
        null,
        63,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_63_DT_INICIO_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COD STATUS',
        VAR_COL_INT,
        null,
        64,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_64_CD_STATUS from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COD ERRO',
        VAR_COL_INT,
        null,
        65,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_65_CD_ERRO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1796');
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
        'COD DV',
        VAR_COL_INT,
        null,
        66,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_66_CD_DV from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'BLOQ EMPR INADIMPLENCIA',
        VAR_COL_VARCHAR,
        null,
        67,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_67_BLOQ_EMPR_INADIMPLENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CPT',
        VAR_COL_VARCHAR,
        null,
        68,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_68_CPT from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'COD EMPRESA TITULAR',
        VAR_COL_VARCHAR,
        null,
        69,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_69_CD_EMPR_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'MATRICULA 02',
        VAR_COL_LONG,
        null,
        70,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_70_NR_MATRICULA_02 from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1907');
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
        'DIFERENCIADOR DA MATRICULA DO TITULAR',
        VAR_COL_VARCHAR,
        null,
        71,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_71_DIF_MATRICULA_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'NUMERO DO TITULO DE ELEITOR',
        VAR_COL_VARCHAR,
        null,
        72,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_72_NR_TITULO_ELEITOR from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'NUMERO DO RIC',
        VAR_COL_VARCHAR,
        null,
        73,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_73_NR_RIC from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 1973');
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
        'NUMERO DA DECLARACAO DE NASCIDO VIVO',
        VAR_COL_VARCHAR,
        null,
        74,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_74_NR_DECL_NASCIDO_VIVO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CARTEIRA DE IDENTIFICACAO',
        VAR_COL_VARCHAR,
        null,
        75,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_75_CART_IDENTIFICACAO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'INDICADOR DE SEGURADO CONTRIBUTARIO',
        VAR_COL_VARCHAR,
        null,
        76,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_76_INDIC_SEGURADO_CONTRIB from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2041');
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
        'INDICADOR DOA CONDICAO DO EX-EMPREGADO',
        VAR_COL_VARCHAR,
        null,
        77,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_77_INDIC_COND_EXEMPREGADO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2064');
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
        'INDICADOR DE PERMANENCIA NO PLANO',
        VAR_COL_VARCHAR,
        null,
        78,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_78_INDIC_PERM_PLANO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'QUANTIDADE DE MESES DE CONTRIBUICAO',
        VAR_COL_VARCHAR,
        null,
        79,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_79_QTDE_MESES_CONTRIB from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2107');
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
        'NOME COMPLETO DO BENEFICIARIO',
        VAR_COL_VARCHAR,
        null,
        80,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_80_NM_COMPL_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'INDICADOR DE TITULAR REMIDO',
        VAR_COL_VARCHAR,
        null,
        81,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_81_INDIC_TITULAR_REMIDO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'EMAIL DA SEGURADORA',
        VAR_COL_VARCHAR,
        null,
        82,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_82_EMAIL_SEGURADORA from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'INDICADOR DE PORTABILIDADE 1',
        VAR_COL_VARCHAR,
        null,
        83,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_83_INDIC_PORTABILIDADE from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2196');
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
        'INDICADOR DE PORTABILIDADE 2',
        VAR_COL_VARCHAR,
        null,
        84,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_84_INDIC_PORTABILIDADE_02 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'INDICADOR DE CARENCIA',
        VAR_COL_VARCHAR,
        null,
        85,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_85_INDIC_CARENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CODIGO DO PRODUTO',
        VAR_COL_INT,
        null,
        86,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_86_CD_PRODUTO from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CODIGO DE IDENTIFICACAO DE PLANO ANTERIOR NA SAS',
        VAR_COL_VARCHAR,
        null,
        87,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_87_CD_PLANO_ANTERIOR_SAS from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID1',
        VAR_COL_VARCHAR,
        null,
        88,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_88_CID01 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID2',
        VAR_COL_VARCHAR,
        null,
        89,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_89_CID02 from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2329');
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
        'CID3',
        VAR_COL_VARCHAR,
        null,
        90,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_90_CID03 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID4',
        VAR_COL_VARCHAR,
        null,
        91,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_91_CID04 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID5',
        VAR_COL_VARCHAR,
        null,
        92,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_92_CID05 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID6',
        VAR_COL_VARCHAR,
        null,
        93,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_93_CID06 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID7',
        VAR_COL_VARCHAR,
        null,
        94,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_94_CID07 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID8',
        VAR_COL_VARCHAR,
        null,
        95,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_95_CID08 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CID9',
        VAR_COL_VARCHAR,
        null,
        96,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_96_CID09 from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2484');
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
        'CID10',
        VAR_COL_VARCHAR,
        null,
        97,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_97_CID10 from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'IBGE',
        VAR_COL_VARCHAR,
        null,
        98,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_98_IBGE from TB_ARQUIVO_INPUT_COLS_DEF;
    
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
        'CBO',
        VAR_COL_VARCHAR,
        null,
        99,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_99_CBO from TB_ARQUIVO_INPUT_COLS_DEF;
    
    call PROC_LOG_MESSAGE('LINHA - 2551');
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
        'DIF TRANSFERENCIA',
        VAR_COL_VARCHAR,
        null,
        100,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_100_DIF_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
    
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/    
	/* BENEFICIARIO */
    
    call PROC_LOG_MESSAGE( 'LINHA - 2527' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
        VAR_COLUMN_17_GP,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

	call PROC_LOG_MESSAGE( 'LINHA - 2543' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
        VAR_COLUMN_03_NR_MATRICULA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

	call PROC_LOG_MESSAGE( 'LINHA - 2575' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
        VAR_COLUMN_13_NM_BENEFICIARIO,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

	call PROC_LOG_MESSAGE( 'LINHA - 2607' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
        VAR_COLUMN_14_DT_NASCIMENTO,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
    call PROC_LOG_MESSAGE( 'LINHA - 2639' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
        VAR_COLUMN_12_NR_CPF,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    call PROC_LOG_MESSAGE( 'LINHA - 2671' );
    
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
