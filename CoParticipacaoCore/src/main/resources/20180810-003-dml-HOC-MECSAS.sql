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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180810-002-dml-HOC-0444.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180810-003-dml-HOC-MECSAS.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
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
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA_SULAMERICA				bigint( 17 ) default 1;
	DECLARE VAR_ID_USER 							bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 							bigint( 17 );
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );	
	declare VAR_ID_ARQUIVO_INPUT_SHEET				bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );

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
	declare VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO              			bigint( 17 ) default 107;
	declare VAR_CD_BENEFICIARIO_COLS_NR_SUBFATURA              			bigint( 17 ) default 108;
		
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
	
	declare VAR_CD_SHEET												bigint( 17 ) default 0;	
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
	call PROC_LOG_MESSAGE('LINHA - 214');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = '0444';
	
    call PROC_LOG_MESSAGE('LINHA - 218');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = 'MECSAS'; 

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* MECSAS */
	
	call PROC_LOG_MESSAGE('LINHA - 238');
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
		'^(0444)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(csv|CSV)$',
		'Arquivo de carga de beneficiários',
		VAR_ARQUIVO_TYPE_CSV,
		VAR_USE_TYPE_MECSAS,
		3,
		null,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;	

	/*****************************************************************************************************************************************************/
	/* ISENTOS  */
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

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_01_PREFIXO_EMPRESA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_01_PREFIXO_EMPRESA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_02_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_02_EMPRESA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_03_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_03_NR_MATRICULA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_04_DV',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_04_DV from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_05_RDP',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_05_RDP from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_06_DC',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_06_DC from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_07_COD_LOCAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_07_COD_LOCAL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_08_CAT_FUNCIONAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_08_CAT_FUNCIONAL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_09_SETOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_09_SETOR from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_10_ESTADO_CIVIL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_10_ESTADO_CIVIL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_11_PLANO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_11_PLANO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_12_PLANO_AGREG',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_12_PLANO_AGREG from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_13_DT_INCLUSAO',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_13_DT_INCLUSAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_14_PLANO_COMPL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_14_PLANO_COMPL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_15_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_15_CPF from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_16_BANCO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_16_BANCO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_17_AGENCIA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_17_AGENCIA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_18_AGENCIA_DV',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_18_AGENCIA_DV from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_19_CONTA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_19_CONTA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_20',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_20 from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_21_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_21_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_22_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_22_DT_NASCIMENTO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_23_SEXO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_23_SEXO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_24_PERMANENCIA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_24_PERMANENCIA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_25_GRAU_PARENTESCO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_25_GRAU_PARENTESCO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_25_AGREGADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_26_AGREGADO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_27_DT_EXCLUSAO',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_27_DT_EXCLUSAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_28_CARENCIA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_28_CARENCIA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_29',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_29 from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_30_DT_REFERENCIA',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_30_DT_REFERENCIA from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_31_CD_ORGAO_LOTACAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_31_CD_ORCAO_LOTACAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_32_NM_ORGAO_LOCACAO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_32_NM_ORGAO_LOTACAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_33',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_33 from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_34_CD_MOTIVO_EXCLUSAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_34_CD_MOTIVO_EXCLUSAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_35_CEP',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_35_CEP from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_36_TP_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_36_TP_LOGRADOURO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_37_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_37_LOGRADOURO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_38_NUM_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_38_NUM_LOGRADOURO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_39_COMPL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_39_COMPL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_40_BAIRRO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_40_BAIRRO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_41_MUNICIPIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_41_MUNICIPIO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_42_UF',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_42_UF from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_43_DDD_TEL_RES',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_43_DDD_TEL_RES from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_44_TEL_RES',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_44_TEL_RES from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_45_DDD_TEL_COM',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_45_DDD_TEL_COM from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_46_TEL_COM',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_46_TEL_COM from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_47_DDD_TEL_CEL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_47_DDD_TEL_CEL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_48_TEL_CEL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_48_TEL_CEL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_49_NM_MAE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_49_NM_MAE from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_50_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_50_RG from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_51_CD_ORGAO_EXPEDIDOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_51_ORGAO_EXPEDIDOR from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_52_CD_PAI',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_52_CD_PAIS from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_53_DT_EMISSAO',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_53_DT_EMISSAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_54_UF_EMISSAO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_54_DT_EMISSAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_55_NUM_PIS_PASEP',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_55_NUM_PIS_PASEP from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_56_NUM_CNS',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_56_NUM_CNS from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_57_EMAIL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_57_EMAIL from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_58_CD_ESCOLARIDADE',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_58_CD_ESCOLARIDADE from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_59_CD_RENDA_FAMILIAR',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_59_CD_RENDA_FAMILIAR from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_60_CD_PROFISSAO_TIT',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_60_CD_PROFISSAO_TIT from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_61_CD_PAIS_ORIGEM',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_61_CD_PAIS_ORIGEM from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_62_CD_IDENTIFICADOR',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_62_CD_IDENTIFICADOR from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_63_CD_CPT',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_63_CD_CPT from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_64_ID_BLOQ_BENEF',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_64_ID_BLOQ_BENEF from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_65_EMPRESA_TIT_AGREGADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_65_EMPRESA_TIT_AGREGADO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_66_MATRICULA_TIT_AGREGADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_66_MATRICULA_TIT_AGREGADO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_67_DECL_NASC_VIVO',
		
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_67_DECL_NASC_VIVO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_68_TITULO_ELEITOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_68_TITULO_ELEITOR from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_69_NUM_RIC',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_69_NUM_RIC from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_70_ID_CONTRIBUTARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_70_ID_CONTRIBUTARIO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_71_QTDE_CONTRIB',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_71_QTDE_CONTRIB from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_72_ID_EX_EMPREGADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_72_ID_EX_EMPREGADO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_73_ID_MANTER_PLANO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_73_ID_MANTER_PLANO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_74_NM_BENEF_COMPLETO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_74_NM_BENEF_COMPLETO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_75_CD_OPERACAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_75_CD_OPERACAO from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
		ID_ARQUIVO_INPUT_SHEET,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'COLUMN_76',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_76 from TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* BENEFICIÁRIO */
	
    call PROC_LOG_MESSAGE('LINHA - 1848');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_03_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1879');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
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
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_15_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 4850');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_22_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 2158');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
		VAR_ID_COLUMN_25_GRAU_PARENTESCO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 2174');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_ID_COLUMN_13_DT_INCLUSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2190');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_LOCAL,
		VAR_ID_COLUMN_07_COD_LOCAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

 	call PROC_LOG_MESSAGE('LINHA - 1848');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_RDP,
		VAR_ID_COLUMN_05_RDP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 2206');
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_SHEET_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO,
        VAR_ID_COLUMN_02_EMPRESA,
        
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
