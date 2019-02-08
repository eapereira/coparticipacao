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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180730-009-dml-marjan-NAO-LOCALIZADOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180805-001-dml-abbvie.sql';

	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
  	
  	declare VAR_CD_ORDEM					int( 3 ) default 0; 
								
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
	
	declare VAR_TP_REPORT_QUERY_BY_CONTRATO_AND_PERIODO		int( 3 ) default 0;
	declare VAR_TP_REPORT_QUERY_BY_PERIODO_ONLY				int( 3 ) default 1;
	declare VAR_TP_REPORT_QUERY_BY_CD_CONTRATO				int( 3 ) default 2;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA		bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 					bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 					bigint( 17 );
	DECLARE VAR_ID_CONTRATO 				bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

	declare VAR_ID_COLUMN_01						bigint( 17 );
	declare VAR_ID_COLUMN_02						bigint( 17 );
	declare VAR_ID_COLUMN_03						bigint( 17 );
	declare VAR_ID_COLUMN_04						bigint( 17 );
	declare VAR_ID_COLUMN_05						bigint( 17 );
	declare VAR_ID_COLUMN_06						bigint( 17 );
	declare VAR_ID_COLUMN_07_NM_BENEFICIARIO		bigint( 17 );
	declare VAR_ID_COLUMN_08						bigint( 17 );
	declare VAR_ID_COLUMN_09_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_10_MES					bigint( 17 );
	declare VAR_ID_COLUMN_11						bigint( 17 );
	declare VAR_ID_COLUMN_12_ANO					bigint( 17 );
	declare VAR_ID_COLUMN_13_POSITIVO_NEGATIVO	bigint( 17 );
	declare VAR_ID_COLUMN_14_VL_PRINCIPAL			bigint( 17 );
	declare VAR_ID_COLUMN_15_CONTRATO				bigint( 17 );
	declare VAR_ID_COLUMN_16_DT_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_17						bigint( 17 );
	declare VAR_ID_COLUMN_18_NM_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_19_NR_MATRICULA			bigint( 17 );
	declare VAR_ID_COLUMN_20						bigint( 17 );


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
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_EMPRESA			bigint( 17 ) default 10;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 12;
	
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_DF              	 		bigint( 17 ) default 13;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_RDP            	 			bigint( 17 ) default 14;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_LOCAL			 			bigint( 17 ) default 15;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_CATEGORIA         			bigint( 17 ) default 16;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_SETOR          	 			bigint( 17 ) default 17;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_ES                   			bigint( 17 ) default 18;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_PLANO             			bigint( 17 ) default 19;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_INCLUSAO       	 			bigint( 17 ) default 20;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_SEXO           	 			bigint( 17 ) default 21;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_PERMANENCIA          			bigint( 17 ) default 22;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_REF               			bigint( 17 ) default 23;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_BANCO                			bigint( 17 ) default 24;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_AGENCIA              			bigint( 17 ) default 25;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_AGENCIA_DG           			bigint( 17 ) default 26;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CONTA_CORRENTE       			bigint( 17 ) default 27;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_CC         	 			bigint( 17 ) default 28;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR_CC        			bigint( 17 ) default 29;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_CARDIF     		 			bigint( 17 ) default 30;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CEP               			bigint( 17 ) default 31;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_LOGRADOURO        			bigint( 17 ) default 32;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LOGRADOURO        			bigint( 17 ) default 33;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NUM_LOGRADOURO    	 			bigint( 17 ) default 34;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_COMPL                			bigint( 17 ) default 35;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BAIRRO         	 			bigint( 17 ) default 36;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_MUNICIPIO      	 			bigint( 17 ) default 37;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_UF                   			bigint( 17 ) default 38;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TEL_RESIDENCIAL      			bigint( 17 ) default 39;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TEL_COMERCIAL        			bigint( 17 ) default 40;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TEL_CELULAR          			bigint( 17 ) default 41;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_MAE               			bigint( 17 ) default 42;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_RG                			bigint( 17 ) default 43;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_ORGAO_EMISSAO_RG  			bigint( 17 ) default 44;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_PAIS_RG           			bigint( 17 ) default 45;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_EMISSAO_RG        			bigint( 17 ) default 46;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_UF_RG                			bigint( 17 ) default 47;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_PIS                  			bigint( 17 ) default 48;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CNS                  			bigint( 17 ) default 49;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_EMAIL                			bigint( 17 ) default 50;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_GRAU_ESCOLARIDADE			bigint( 17 ) default 51;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_VL_RENDA_FAMILIAR      		bigint( 17 ) default 52;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_PROFISSAO        			bigint( 17 ) default 53;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_PAIS_ORIGEM      			bigint( 17 ) default 54;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_EXCLUSAO             		bigint( 17 ) default 55;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_MOTIVO_EXCLUSAO      		bigint( 17 ) default 56;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_OPERACAO             		bigint( 17 ) default 57;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_EMPRESA_TITULAR_TRANSF    	bigint( 17 ) default 58;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_TRANSF     		bigint( 17 ) default 59;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_LOCAL_TRANSF         		bigint( 17 ) default 60;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_CATEGORIA_TRANSF     		bigint( 17 ) default 61;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_PLANO_TRANSF         		bigint( 17 ) default 62;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_MOTIVO_REMISSAO         		bigint( 17 ) default 63;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_NOVO_TITULAR     		bigint( 17 ) default 64;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_QTDE_PERM_MESES         		bigint( 17 ) default 65;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_RDP_NOVO_TITULAR        		bigint( 17 ) default 66;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_INICIO_TRANSF        		bigint( 17 ) default 67;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_STATUS               		bigint( 17 ) default 68;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_ERROR                		bigint( 17 ) default 69;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_DV                   		bigint( 17 ) default 70;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_BLOQ_EMPRESA_INADIMPLENCIA   	bigint( 17 ) default 71;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CPT                           	bigint( 17 ) default 72;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_EMPRESA_TITULAR           	bigint( 17 ) default 73;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DIF_MATRICULA_TITULAR        	bigint( 17 ) default 74;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_TITULO_ELEITOR             	bigint( 17 ) default 75;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_RIC                       	bigint( 17 ) default 76;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CERTIDAO_NASCIMENTO        	bigint( 17 ) default 77;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CARTEIRA_IDENT            	bigint( 17 ) default 78;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_SEGURADO_CONTRIBUTARIO    	bigint( 17 ) default 79;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_COND_EX_EMPREGADO        	bigint( 17 ) default 80;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_PERM_PLANO                	bigint( 17 ) default 81;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_QTDE_MESES_CONTRIB           	bigint( 17 ) default 82;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO_COMPLETO     	bigint( 17 ) default 83;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_TITULAR_REMIDO          	bigint( 17 ) default 84;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_EMAIL_SEGURADORA             	bigint( 17 ) default 85;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_PORTABILIDADE_01         	bigint( 17 ) default 86;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_PORTABILIDADE_02        	bigint( 17 ) default 87;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IND_CARENCIA                	bigint( 17 ) default 88;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_PRODUTO                  	bigint( 17 ) default 89;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CD_IND_PLANO_ANTERIOR_SAS    	bigint( 17 ) default 90;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID01                        	bigint( 17 ) default 91;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID02                         	bigint( 17 ) default 92;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID03                        	bigint( 17 ) default 93;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID04                         	bigint( 17 ) default 94;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID05                        	bigint( 17 ) default 95;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID06                        	bigint( 17 ) default 96;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID07                        	bigint( 17 ) default 97;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID08                         	bigint( 17 ) default 98;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID09                         	bigint( 17 ) default 99;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CID10                         	bigint( 17 ) default 100;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_IBGE                         	bigint( 17 ) default 101;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_CBO                          	bigint( 17 ) default 102;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DIF_TRANSF                    	bigint( 17 ) default 103;		
	
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE 							bigint( 17 ) default 30;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR 								bigint( 17 ) default 30;
	declare VAR_COL_VIEW_LENGTH_NR_UPI 									bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL 							bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_COMPETENCIA 							bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_EMPRESA 								bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_ADMISSAO 							bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_EVENTO 								bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_INICIAL 								bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_FINAL 								bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_QTDE_TOTAL 								bigint( 17 ) default 20;
	declare VAR_COL_VIEW_LENGTH_QTDE_REALIZADA 							bigint( 17 ) default 20;	
	
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
	call PROC_LOG_MESSAGE('LINHA - 147');
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,
		CD_EMPRESA,
		CD_AUTOMATIC_CREATE_BENEFICIARIO,
		CD_OUTPUT_REPORT_DIR,
        CD_INPUT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,		
		TP_REPORT_QUERY,
		CD_SEARCH_BENEFICIARIO_MATRICULA_EMPRESA,		
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA_SULAMERICA, /* Sulamerica */
		'Abbvie',
		'ABBVIE',
		VAR_FALSE,
		'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/abbvie/',
        '/home/eapereira/desenv/work/coparticipacao/input/',
        '/home/eapereira/desenv/work/coparticipacao/failure/',
        '/home/eapereira/desenv/work/coparticipacao/output/',
        VAR_FALSE,
        VAR_FALSE,		
        VAR_TP_REPORT_QUERY_BY_CD_CONTRATO,
        VAR_TRUE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;

	call PROC_LOG_MESSAGE('LINHA - 169');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8B1LR',
	    '8B1LR',
	    VAR_NM_CONTRATO_COPARTICIPACAO,	    
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 169');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'8B1LP',
	    '8B1LP',
	    VAR_NM_CONTRATO_COPARTICIPACAO,	    
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
	call PROC_LOG_MESSAGE('LINHA - 191');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    
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
	
	call PROC_LOG_MESSAGE('LINHA - 191');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'MECSAS2',
	    'MECSAS.2',
	    VAR_NM_CONTRATO_MECSAS2,
	    VAR_USE_TYPE_MECSAS2,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
		
	call PROC_LOG_MESSAGE('LINHA - 213');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    CD_SPREADSHEET_ALL_PAGES,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'ISENTO',
	    'ISENTO',
	    VAR_NM_CONTRATO_ISENTO,
	    VAR_USE_TYPE_ISENTO,
	    VAR_TRUE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 255');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    TP_USE,
	    CD_SPREADSHEET_ALL_PAGES,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'NAO-LOCALIZADO',
	    'NAO-LOCALIZADO',
	    VAR_NM_CONTRATO_NAO_LOCALIZADO,
	    VAR_USE_TYPE_NAO_LOCALIZADO,
	    VAR_TRUE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	call PROC_LOG_MESSAGE('LINHA - 250');
	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 254');
	
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_COPARTICIPACAO_ABBVIE',
		'copart',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION,
		'NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		1,
		'NOME BENEFICIÁRIO',
		
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
		VAR_ID_VIEW_DESTINATION,
		'NM_TITULAR',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NOME TÍTULAR',
		
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
		VAR_ID_VIEW_DESTINATION,
		'NR_UPI',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_UPI,
		3,
		'UPI',
		
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
		VAR_ID_VIEW_DESTINATION,
		'COMPETENCIA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		4,
		'COMPETÊNCIA',
		
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
		VAR_ID_VIEW_DESTINATION,
		'VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
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
		'VW_ISENTOS_ABBVIE',
		'isentos',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION,
		'NM_DEPENDENTE',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_DEPENDENTE,
		1,
		'NOME BENEFICIÁRIO',
		
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
		VAR_ID_VIEW_DESTINATION,
		'NM_TITULAR',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NM_TITULAR,
		2,
		'NOME TÍTULAR',
		
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
		VAR_ID_VIEW_DESTINATION,
		'NR_UPI',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_UPI,
		3,
		'UPI',
		
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
		VAR_ID_VIEW_DESTINATION,
		'COMPETENCIA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_CD_COMPETENCIA,
		4,
		'COMPETÊNCIA',
		
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
		VAR_ID_VIEW_DESTINATION,
		'VL_PRINCIPAL',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		5,
		'VALOR PRINCIPAL',
		
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
		'VW_DELLOITE_ABBVIE',
		'delloite',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	
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
		VAR_ID_VIEW_DESTINATION,
		'CD_EMPRESA',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_CD_EMPRESA,
		1,
		'COD EMPRESA',
		
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
		VAR_ID_VIEW_DESTINATION,
		'NR_UPI',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_UPI,
		2,
		'UPI',
		
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
		VAR_ID_VIEW_DESTINATION,
		'DT_ADMISSAO',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_DT_ADMISSAO,
		3,
		'DATA ID FUNCIONÁRIO',
		
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
		VAR_ID_VIEW_DESTINATION,
		'CD_EVENTO',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_CD_EVENTO,
		4,
		'COD EVENTO',
		
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
		VAR_ID_VIEW_DESTINATION,
		'DT_INICIAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_DT_INICIAL,
		5,
		'DATA INICIAL',
		
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
		VAR_ID_VIEW_DESTINATION,
		'DT_FINAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_DT_FINAL,
		6,
		'DATA FINAL',
		
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
		VAR_ID_VIEW_DESTINATION,
		'VL_PRINCIPAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_VL_PRINCIPAL,
		7,
		'VALOR EVENTO',
		
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
		VAR_ID_VIEW_DESTINATION,
		'QTDE_TOTAL',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_QTDE_TOTAL,
		8,
		'QTDE TOTAL',
		
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
		VAR_ID_VIEW_DESTINATION,
		'QTDE_REALIZADA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_QTDE_REALIZADA,
		9,
		'QTDE REALIZADA',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	/*********************************************************************************************************************************************/
	/* NAO LOCALIZADOS */
	
    call PROC_LOG_MESSAGE('LINHA - 752');
    insert into TB_VIEW_DESTINATION(
        NM_VIEW,
        NM_TITLE_LABEL,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        'VW_DESCONHECIDO_ABBVIE',
        'NAO-LOCALIZADOS',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
    
    set VAR_CD_ORDEM = 0;
    
    call PROC_LOG_MESSAGE('LINHA - 752');
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
        VAR_ID_VIEW_DESTINATION,
        'NR_MATRICULA',
        VAR_COL_LONG,
        20,
        VAR_CD_ORDEM,
        'MATRICULA',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    call PROC_LOG_MESSAGE('LINHA - 752');    
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
        VAR_ID_VIEW_DESTINATION,
        'NM_BENEFICIARIO',
        VAR_COL_VARCHAR,
        40,
        VAR_CD_ORDEM,
        'NOME USUÁRIO',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 310');
	insert into TB_VIEW_DESTINATION_COLS_DEF(
        ID_VIEW_DESTINATION,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        NM_COL_TITLE_LABEL,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_VIEW_DESTINATION,
        'NM_TITULAR',
        VAR_COL_VARCHAR,
        40,
        VAR_CD_ORDEM,
        'TÍTULAR',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
        
    call PROC_LOG_MESSAGE('LINHA - 752');
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
        VAR_ID_VIEW_DESTINATION,
        'NR_CPF',
        VAR_COL_LONG,
        20,
        VAR_CD_ORDEM,
        'CPF',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    call PROC_LOG_MESSAGE('LINHA - 752');            
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
        VAR_ID_VIEW_DESTINATION,
        'DT_ADMISSAO',
        VAR_COL_DATE,
        20,
        VAR_CD_ORDEM,
        'DATA ADMISSAO',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
    
    call PROC_LOG_MESSAGE('LINHA - 752');                
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
        VAR_ID_VIEW_DESTINATION,
        'NR_MATRICULA_EMPRESA',
        VAR_COL_LONG,
        20,
        VAR_CD_ORDEM,
        'UPI',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );    
    
    call PROC_LOG_MESSAGE('LINHA - 955');                	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
