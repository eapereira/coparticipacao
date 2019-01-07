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
	
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_SHEET				bigint( 17 );
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
	declare VAR_CD_BENEFICIARIO_COLS_DESCR_PROFISSAO                 	bigint( 17 ) default 104;
	declare VAR_CD_BENEFICIARIO_COLS_NR_MATRICULA_ESPECIAL           	bigint( 17 ) default 105;
	declare VAR_CD_BENEFICIARIO_COLS_VL_FATOR_MODERADOR              	bigint( 17 ) default 106;
	declare VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO     		         	bigint( 17 ) default 107;
		
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
	
	declare	VAR_CD_SHEET												int( 3 ) default 0;
    declare	VAR_CD_ORDEM												int( 3 ) default 0;
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
    
	call PROC_LOG_MESSAGE('LINHA - 291');
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
        'NUM_LINHA',
        VAR_COL_INT,
        null,
        1,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_01_NR_LINHA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 314');
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
        'COD_EMP',
        VAR_COL_VARCHAR,
        null,
        2,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_02_CD_CONTRATO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 339');
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
        'MATRICULA',
        VAR_COL_LONG,
        null,
        3,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_03_NR_MATRICULA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 362');
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
        'DF',
        VAR_COL_INT,
        null,
        4,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_04_DF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 385');
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
        'RDP',
        VAR_COL_INT,
        null,
        5,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_05_RDP from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 408');
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
        'LOCAL',
        VAR_COL_INT,
        null,
        6,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_06_LOCAL from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 431');
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
        'CATEGORIA',
        VAR_COL_INT,
        null,
        7,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_07_CATEGORIA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 454');
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
        'SETOR',
        VAR_COL_VARCHAR,
        null,
        8,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_08_SETOR from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 477');
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
        'ES',
        VAR_COL_VARCHAR,
        null,
        9,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_09_ES from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 500');
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
        'PLANO',
        VAR_COL_VARCHAR,
        null,
        10,
                
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_10_PLANO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 523');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATA_ADESAO',
        VAR_COL_DATE,
        null,
        11,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_11_DT_ADESAO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 549');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'CPF',
        VAR_COL_LONG,
        null,
        12,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_12_NR_CPF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 575');
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
        'NOME_BENEF',
        VAR_COL_VARCHAR,
        null,
        13,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_13_NM_BENEFICIARIO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 598');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATA_NASC',
        VAR_COL_DATE,
        null,
        14,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_14_DT_NASCIMENTO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 624');
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
        'SEXO',
        VAR_COL_VARCHAR,
        null,
        15,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_15_SEXO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 647');
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
        'PERMANENCIA',
        VAR_COL_VARCHAR,
        null,
        16,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_16_PERMANENCIA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 678');
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
        'GP',
        VAR_COL_INT,
        null,
        17,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_17_GP from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 692');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATA_ADM',
        VAR_COL_DATE,
        null,
        18,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_18_DT_ADMISSAO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 719');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATA_REF',
        VAR_COL_DATE,
        null,
        19,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_19_DT_REF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 744');
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
        'BANCO',
        VAR_COL_INT,
        null,
        20,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_20_BANCO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 768');
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
        'AGENCIA',
        VAR_COL_INT,
        null,
        21,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_21_AGENCIA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 791');
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
        'DG_AGEN',
        VAR_COL_INT,
        null,
        22,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_22_DG_AGENCIA from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 814');
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
        'CONTA_CORRENTE',
        VAR_COL_VARCHAR,
        null,
        23,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_23_CONTA_CORRENTE from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 837');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'CPF_CONTA_CORRENTE',
        VAR_COL_LONG,
        null,
        24,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_24_NR_CPF_CONTA_CORRENTE from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 863');
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
        'NOME_TITULAR_CC',
        VAR_COL_VARCHAR,
        null,
        25,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_25_NM_TITULAR_CC from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 886');
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
        'CODCARDIF',
        VAR_COL_VARCHAR,
        null,
        26,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_26_CODCARDIF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 909');
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
        'NUM_CEP',
        VAR_COL_INT,
        null,
        27,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_27_NR_CEP from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 932');
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
        'TIPO_LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        28,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_28_TP_LOGRADOURO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 955');
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
        'LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        29,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_29_LOGRADOURO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 978');
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
        'NUMERO',
        VAR_COL_INT,
        null,
        30,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_30_NR_NUMERO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1001');
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
        'COMP_LOGRADOURO',
        VAR_COL_VARCHAR,
        null,
        31,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_31_COMP_LOGRADOURO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1024');
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
        'BAIRRO',
        VAR_COL_VARCHAR,
        null,
        32,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_32_BAIRRO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1047');
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
        'MUNICIPIO',
        VAR_COL_VARCHAR,
        null,
        33,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_33_MUNICIPIO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1070');
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
        'UF',
        VAR_COL_VARCHAR,
        null,
        34,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_34_UF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1093');
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
        'TEL_RESID',
        VAR_COL_VARCHAR,
        null,
        35,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_35_TEL_RESID from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1115');
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
        'TEL_COM',
        VAR_COL_VARCHAR,
        null,
        36,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_36_TEL_COM from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1138');
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
        'TEL_CEL',
        VAR_COL_VARCHAR,
        null,
        37,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_37_TEL_CEL from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1162');
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
        'NOMEDAMAEBENE',
        VAR_COL_VARCHAR,
        null,
        38,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_38_NM_MAE from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1185');
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
        'RG',
        VAR_COL_VARCHAR,
        null,
        39,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_39_NR_RG from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1208');
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
        'ORGAOEMISSORG',
        VAR_COL_VARCHAR,
        null,
        40,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_40_ORGAO_EMISSOR from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1231');
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
        'PAISEMISSORRG',
        VAR_COL_VARCHAR,
        null,
        41,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_41_PAIS_EMISSOR from TB_REGISTER_COLUMN;
    
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATAEMISSAORG',
        VAR_COL_DATE,
        null,
        42,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_42_DT_EMISSAO from TB_REGISTER_COLUMN;
    
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
        'ESTADORG',
        VAR_COL_VARCHAR,
        null,
        43,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_43_ESTADO_RG from TB_REGISTER_COLUMN;
    
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
        'PIS',
        VAR_COL_VARCHAR,
        null,
        44,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_44_NR_PIS from TB_REGISTER_COLUMN;
    
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
        'CNS',
        VAR_COL_VARCHAR,
        null,
        45,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_45_CNS from TB_REGISTER_COLUMN;
    
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
        'E-MAIL',
        VAR_COL_VARCHAR,
        null,
        46,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_46_EMAIL from TB_REGISTER_COLUMN;
    
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
        'GRAUESCOLARIDADE',
        VAR_COL_INT,
        null,
        47,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_47_GRAU_ESCOLARIDADE from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1390');
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
        'RENDAFAMILIAR',
        VAR_COL_INT,
        null,
        48,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_48_RENDA_FAMILIAR from TB_REGISTER_COLUMN;
    
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
        'CDPROFISSAO',
        VAR_COL_INT,
        null,
        49,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_49_CD_PROFISSAO from TB_REGISTER_COLUMN;
    
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
        'CDPAISDEORIGEM',
        VAR_COL_INT,
        null,
        50,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_50_PAIS_ORIGEM from TB_REGISTER_COLUMN;
    
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'DATAEXCLUSAO',
        VAR_COL_DATE,
        null,
        51,
        'yyyyMMdd',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_51_DT_EXCLUSAO from TB_REGISTER_COLUMN;
    
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
        'CODMOVEXCLUSAO',
        VAR_COL_INT,
        null,
        52,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_52_CD_MOVTO_EXCLUSAO from TB_REGISTER_COLUMN;
    
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
        'CODOPERACAO',
        VAR_COL_INT,
        null,
        53,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_53_CD_OPERACAO from TB_REGISTER_COLUMN;
    
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
        'CODEMPRESATRANSF',
        VAR_COL_INT,
        null,
        54,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_54_CD_EMPRESA_TRANSF from TB_REGISTER_COLUMN;
    
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
        'MATRICULATRANSF',
        VAR_COL_INT,
        null,
        55,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_55_MATRICULA_TRANSF from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1571');
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
        'LOCALTRANSF',
        VAR_COL_VARCHAR,
        null,
        56,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_56_LOCAL_TRANSF from TB_REGISTER_COLUMN;
    
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
        'CATTRANSF',
        VAR_COL_INT,
        null,
        57,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_57_CART_TRANSF from TB_REGISTER_COLUMN;
    
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
        'PLANOTRANSF',
        VAR_COL_INT,
        null,
        58,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_58_PLANO_TRANSF from TB_REGISTER_COLUMN;
    
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
        'MOTREMISSAO',
        VAR_COL_VARCHAR,
        null,
        59,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_59_MOT_EMISSAO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1660');
    insert into TB_REGISTER_COLUMN(
        ID_REGISTER,
        NM_COLUMN,
        CD_TYPE,
        VL_LENGTH,
        CD_ORDEM,
        CD_FORMAT,
        
        USER_CREATED, 
        DT_CREATED,
        DT_ALTERED ) values (	
        VAR_ID_REGISTER,
        'CPFNOVOTITULAR',
        VAR_COL_LONG,
        null,
        60,
        '#0',
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_60_CPF_NOVO_TITULAR from TB_REGISTER_COLUMN;
    
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
        'QTDPERMAMESES',
        VAR_COL_INT,
        null,
        61,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_61_QTDE_PERM_MESES from TB_REGISTER_COLUMN;
    
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
        'RDPNOVOTITULAR',
        VAR_COL_VARCHAR,
        null,
        62,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_62_RDP_NOVO_TITULAR from TB_REGISTER_COLUMN;
    
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
        'DTINICIOTRANSF',
        VAR_COL_DATE,
        null,
        63,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_63_DT_INICIO_TRANSF from TB_REGISTER_COLUMN;
    
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
        'COD STATUS',
        VAR_COL_INT,
        null,
        64,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_64_CD_STATUS from TB_REGISTER_COLUMN;
    
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
        'COD ERRO',
        VAR_COL_INT,
        null,
        65,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_65_CD_ERRO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1796');
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
        'COD DV',
        VAR_COL_INT,
        null,
        66,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_66_CD_DV from TB_REGISTER_COLUMN;
    
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
        'BLOQ EMPR INADIMPLENCIA',
        VAR_COL_VARCHAR,
        null,
        67,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_67_BLOQ_EMPR_INADIMPLENCIA from TB_REGISTER_COLUMN;
    
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
        'CPT',
        VAR_COL_VARCHAR,
        null,
        68,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_68_CPT from TB_REGISTER_COLUMN;
    
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
        'COD EMPRESA TITULAR',
        VAR_COL_VARCHAR,
        null,
        69,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_69_CD_EMPR_TITULAR from TB_REGISTER_COLUMN;
    
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
        'MATRICULA 02',
        VAR_COL_LONG,
        null,
        70,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_70_NR_MATRICULA_02 from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1907');
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
        'DIFERENCIADOR DA MATRICULA DO TITULAR',
        VAR_COL_VARCHAR,
        null,
        71,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_71_DIF_MATRICULA_TITULAR from TB_REGISTER_COLUMN;
    
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
        'NUMERO DO TITULO DE ELEITOR',
        VAR_COL_VARCHAR,
        null,
        72,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_72_NR_TITULO_ELEITOR from TB_REGISTER_COLUMN;
    
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
        'NUMERO DO RIC',
        VAR_COL_VARCHAR,
        null,
        73,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_73_NR_RIC from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 1973');
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
        'NUMERO DA DECLARACAO DE NASCIDO VIVO',
        VAR_COL_VARCHAR,
        null,
        74,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_74_NR_DECL_NASCIDO_VIVO from TB_REGISTER_COLUMN;
    
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
        'CARTEIRA DE IDENTIFICACAO',
        VAR_COL_VARCHAR,
        null,
        75,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_75_CART_IDENTIFICACAO from TB_REGISTER_COLUMN;
    
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
        'INDICADOR DE SEGURADO CONTRIBUTARIO',
        VAR_COL_VARCHAR,
        null,
        76,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_76_INDIC_SEGURADO_CONTRIB from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2041');
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
        'INDICADOR DOA CONDICAO DO EX-EMPREGADO',
        VAR_COL_VARCHAR,
        null,
        77,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_77_INDIC_COND_EXEMPREGADO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2064');
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
        'INDICADOR DE PERMANENCIA NO PLANO',
        VAR_COL_VARCHAR,
        null,
        78,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_78_INDIC_PERM_PLANO from TB_REGISTER_COLUMN;
    
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
        'QUANTIDADE DE MESES DE CONTRIBUICAO',
        VAR_COL_VARCHAR,
        null,
        79,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_79_QTDE_MESES_CONTRIB from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2107');
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
        'NOME COMPLETO DO BENEFICIARIO',
        VAR_COL_VARCHAR,
        null,
        80,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_80_NM_COMPL_BENEFICIARIO from TB_REGISTER_COLUMN;
    
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
        'INDICADOR DE TITULAR REMIDO',
        VAR_COL_VARCHAR,
        null,
        81,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_81_INDIC_TITULAR_REMIDO from TB_REGISTER_COLUMN;
    
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
        'EMAIL DA SEGURADORA',
        VAR_COL_VARCHAR,
        null,
        82,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_82_EMAIL_SEGURADORA from TB_REGISTER_COLUMN;
    
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
        'INDICADOR DE PORTABILIDADE 1',
        VAR_COL_VARCHAR,
        null,
        83,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_83_INDIC_PORTABILIDADE from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2196');
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
        'INDICADOR DE PORTABILIDADE 2',
        VAR_COL_VARCHAR,
        null,
        84,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_84_INDIC_PORTABILIDADE_02 from TB_REGISTER_COLUMN;
    
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
        'INDICADOR DE CARENCIA',
        VAR_COL_VARCHAR,
        null,
        85,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_85_INDIC_CARENCIA from TB_REGISTER_COLUMN;
    
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
        'CODIGO DO PRODUTO',
        VAR_COL_INT,
        null,
        86,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_86_CD_PRODUTO from TB_REGISTER_COLUMN;
    
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
        'CODIGO DE IDENTIFICACAO DE PLANO ANTERIOR NA SAS',
        VAR_COL_VARCHAR,
        null,
        87,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_87_CD_PLANO_ANTERIOR_SAS from TB_REGISTER_COLUMN;
    
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
        'CID1',
        VAR_COL_VARCHAR,
        null,
        88,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_88_CID01 from TB_REGISTER_COLUMN;
    
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
        'CID2',
        VAR_COL_VARCHAR,
        null,
        89,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_89_CID02 from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2329');
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
        'CID3',
        VAR_COL_VARCHAR,
        null,
        90,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_90_CID03 from TB_REGISTER_COLUMN;
    
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
        'CID4',
        VAR_COL_VARCHAR,
        null,
        91,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_91_CID04 from TB_REGISTER_COLUMN;
    
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
        'CID5',
        VAR_COL_VARCHAR,
        null,
        92,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_92_CID05 from TB_REGISTER_COLUMN;
    
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
        'CID6',
        VAR_COL_VARCHAR,
        null,
        93,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_93_CID06 from TB_REGISTER_COLUMN;
    
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
        'CID7',
        VAR_COL_VARCHAR,
        null,
        94,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_94_CID07 from TB_REGISTER_COLUMN;
    
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
        'CID8',
        VAR_COL_VARCHAR,
        null,
        95,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_95_CID08 from TB_REGISTER_COLUMN;
    
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
        'CID9',
        VAR_COL_VARCHAR,
        null,
        96,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_96_CID09 from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2484');
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
        'CID10',
        VAR_COL_VARCHAR,
        null,
        97,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_97_CID10 from TB_REGISTER_COLUMN;
    
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
        'IBGE',
        VAR_COL_VARCHAR,
        null,
        98,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_98_IBGE from TB_REGISTER_COLUMN;
    
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
        'CBO',
        VAR_COL_VARCHAR,
        null,
        99,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_99_CBO from TB_REGISTER_COLUMN;
    
    call PROC_LOG_MESSAGE('LINHA - 2551');
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
        'DIF TRANSFERENCIA',
        VAR_COL_VARCHAR,
        null,
        100,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()
    );

    select max( ID ) into VAR_COLUMN_100_DIF_TRANSF from TB_REGISTER_COLUMN;
    
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/    
	/* BENEFICIARIO */
    
    call PROC_LOG_MESSAGE( 'LINHA - 2527' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

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
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

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
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

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
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

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
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

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
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO,
        VAR_COLUMN_02_CD_CONTRATO,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
		
	call PROC_LOG_MESSAGE('LINHA - 1559');    
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 

