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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20181023-004-dml-ABBVIE.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181023-005-dml-CARGILL.sql';
	
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS2			int( 3 ) default 2;	
	declare VAR_USE_TYPE_NAO_LOCALIZADO		int( 3 ) default 3;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 4;
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 5;
	declare VAR_USE_TYPE_EXTRA_FILE			int( 3 ) default 6;
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_CD_ORDEM					int( 3 ) default 0;
	declare VAR_DT_FORMAT					varchar( 40 ) default 'dd/MM/yy';
	
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


	declare VAR_ID_COLUMN_001_NUM_LINHA					bigint( 17 );
	declare VAR_ID_COLUMN_002_CD_EMPRESA				bigint( 17 );
	declare VAR_ID_COLUMN_003_NR_MATRICULA				bigint( 17 );
	declare VAR_ID_COLUMN_004_DF						bigint( 17 );
	declare VAR_ID_COLUMN_005_RDP						bigint( 17 );
	declare VAR_ID_COLUMN_006_NR_LOCAL					bigint( 17 );
	declare VAR_ID_COLUMN_007_CD_CATEGORIA				bigint( 17 );
	declare VAR_ID_COLUMN_008_SETOR						bigint( 17 );
	declare VAR_ID_COLUMN_009_ES						bigint( 17 );
	declare VAR_ID_COLUMN_010_CD_PLANO					bigint( 17 );
	declare VAR_ID_COLUMN_011_DT_ADESAO					bigint( 17 );
	declare VAR_ID_COLUMN_012_NR_CPF					bigint( 17 );
	declare VAR_ID_COLUMN_013_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_ID_COLUMN_014_DT_NASCIMENTO				bigint( 17 );
	declare VAR_ID_COLUMN_015_SEXO						bigint( 17 );
	declare VAR_ID_COLUMN_016_PERMANENCIA				bigint( 17 );
	declare VAR_ID_COLUMN_017_GRAU_PARENTESCO			bigint( 17 );
	declare VAR_ID_COLUMN_018_DT_ADMISSAO				bigint( 17 );
	declare VAR_ID_COLUMN_019_DT_REF					bigint( 17 );
	declare VAR_ID_COLUMN_020_BANCO						bigint( 17 );
	declare VAR_ID_COLUMN_021_AGENCIA					bigint( 17 );
	declare VAR_ID_COLUMN_022_AGENCIA_DG				bigint( 17 );
	declare VAR_ID_COLUMN_023_CONTA_CORRENTE			bigint( 17 );
	declare VAR_ID_COLUMN_024_CPF_CC					bigint( 17 );
	declare VAR_ID_COLUMN_025_NM_TITULAR_CC				bigint( 17 );
	declare VAR_ID_COLUMN_026_CD_CARDIRF				bigint( 17 );
	declare VAR_ID_COLUMN_027_NR_CEP					bigint( 17 );
	declare VAR_ID_COLUMN_028_TP_LOGRADOURO				bigint( 17 );
	declare VAR_ID_COLUMN_029_NM_LOGRADOURO				bigint( 17 );
	declare VAR_ID_COLUMN_030_NUM						bigint( 17 );
	declare VAR_ID_COLUMN_031_COMPL						bigint( 17 );
	declare VAR_ID_COLUMN_032_BAIRRO					bigint( 17 );
	declare VAR_ID_COLUMN_033_MUNICIPIO					bigint( 17 );
	declare VAR_ID_COLUMN_034_UF						bigint( 17 );
	declare VAR_ID_COLUMN_035_TEL_RESIDENCIAL			bigint( 17 );
	declare VAR_ID_COLUMN_036_TEL_COMERCIAL				bigint( 17 );
	declare VAR_ID_COLUMN_037_TEL_CELULAR				bigint( 17 );
	declare VAR_ID_COLUMN_038_NM_MAE					bigint( 17 );
	declare VAR_ID_COLUMN_039_NR_RG						bigint( 17 );
	declare VAR_ID_COLUMN_040_ORGAO_EMISSOR_RG			bigint( 17 );
	declare VAR_ID_COLUMN_041_PAIS_RG					bigint( 17 );
	declare VAR_ID_COLUMN_042_DT_EMISSAO_RG				bigint( 17 );
	declare VAR_ID_COLUMN_043_UF_RG						bigint( 17 );
	declare VAR_ID_COLUMN_044_PIS						bigint( 17 );
	declare VAR_ID_COLUMN_045_CNS						bigint( 17 );
	declare VAR_ID_COLUMN_046_EMAIL						bigint( 17 );
	declare VAR_ID_COLUMN_047_GRAU_ESCOLARIDADE			bigint( 17 );
	declare VAR_ID_COLUMN_048_RENDA_FAMILIAR			bigint( 17 );
	declare VAR_ID_COLUMN_049_CD_PROFISSAO				bigint( 17 );
	declare VAR_ID_COLUMN_050_CD_PAIS_ORIGEM			bigint( 17 );
	declare VAR_ID_COLUMN_051_DT_EXCLUSAO				bigint( 17 );
	declare VAR_ID_COLUMN_052_CD_MOTIVO_EXCLUSAO		bigint( 17 );
	declare VAR_ID_COLUMN_053_CD_OPERACAO				bigint( 17 );
	declare VAR_ID_COLUMN_054_CD_EMPRESA_TRANSF			bigint( 17 );
	declare VAR_ID_COLUMN_055_NR_MATRICULA_TRANSF		bigint( 17 );
	declare VAR_ID_COLUMN_056_NR_LOCAL_TRANSF			bigint( 17 );
	declare VAR_ID_COLUMN_057_NR_CATEGORIA_TRANSF		bigint( 17 );
	declare VAR_ID_COLUMN_058_CD_PLANO_TRANSF			bigint( 17 );
	declare VAR_ID_COLUMN_059_MOTIVO_REMISSAO			bigint( 17 );
	declare VAR_ID_COLUMN_060_NR_CPF_NOVO_TITULAR		bigint( 17 );
	declare VAR_ID_COLUMN_061_QTDE_PERM_MESES			bigint( 17 );
	declare VAR_ID_COLUMN_062_RDP_NOVO_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_063_DT_INICIO_TRANSF			bigint( 17 );
	declare VAR_ID_COLUMN_064_CD_STATUS					bigint( 17 );
	declare VAR_ID_COLUMN_065_CD_ERROR					bigint( 17 );
	declare VAR_ID_COLUMN_066_CD_DV							bigint( 17 );
	declare VAR_ID_COLUMN_067_BLOQ_EMPRESA_INADIMPLENCIA	bigint( 17 );
	declare VAR_ID_COLUMN_068_CPT							bigint( 17 );
	declare VAR_ID_COLUMN_069_CD_EMPRESA_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_070_NR_MATRICULA					bigint( 17 );
	declare VAR_ID_COLUMN_071_DIF_MATRICULA_TITULAR			bigint( 17 );
	declare VAR_ID_COLUMN_072_NR_TITULO_ELEITOR				bigint( 17 );
	declare VAR_ID_COLUMN_073_NR_RIC						bigint( 17 );
	declare VAR_ID_COLUMN_074_NR_CERTIDAO_NASCIMENTO		bigint( 17 );
	declare VAR_ID_COLUMN_075_NR_CARTEIRA_IDENT				bigint( 17 );
	declare VAR_ID_COLUMN_076_IND_SEGURADO_CONTRIBUTARIO	bigint( 17 );
	declare VAR_ID_COLUMN_077_IND_COND_EX_EMPREGADO			bigint( 17 );
	declare VAR_ID_COLUMN_078_IND_PERM_PLANO				bigint( 17 );
	declare VAR_ID_COLUMN_079_QTDE_MESES_CONTRIB			bigint( 17 );
	declare VAR_ID_COLUMN_080_NM_BENEFICIARIO_COMPLETO		bigint( 17 );
	declare VAR_ID_COLUMN_081_IND_TITULAR_REMIDO			bigint( 17 );
	declare VAR_ID_COLUMN_082_EMAIL_SEGURADORA				bigint( 17 );
	declare VAR_ID_COLUMN_083_IND_PORTABILIDADE_01			bigint( 17 );
	declare VAR_ID_COLUMN_084_IND_PORTABILIDADE_02			bigint( 17 );
	declare VAR_ID_COLUMN_085_IND_CARENCIA					bigint( 17 );
	declare VAR_ID_COLUMN_086_CD_PRODUTO					bigint( 17 );
	declare VAR_ID_COLUMN_087_CD_IND_PRODUTO_ANTERIOR_SAS	bigint( 17 );
	declare VAR_ID_COLUMN_088_CID01							bigint( 17 );
	declare VAR_ID_COLUMN_089_CID02							bigint( 17 );
	declare VAR_ID_COLUMN_090_CID03							bigint( 17 );
	declare VAR_ID_COLUMN_091_CID04							bigint( 17 );
	declare VAR_ID_COLUMN_092_CID05							bigint( 17 );
	declare VAR_ID_COLUMN_093_CID06							bigint( 17 );
	declare VAR_ID_COLUMN_094_CID07							bigint( 17 );
	declare VAR_ID_COLUMN_095_CID08							bigint( 17 );
	declare VAR_ID_COLUMN_096_CID09							bigint( 17 );
	declare VAR_ID_COLUMN_097_CID10							bigint( 17 );
	declare VAR_ID_COLUMN_098_IBGE							bigint( 17 );
	declare VAR_ID_COLUMN_099_CBO							bigint( 17 );
	declare VAR_ID_COLUMN_100_DIF_TRANSF					bigint( 17 );	
	
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
	declare VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO     		         	bigint( 17 ) default 107;
	
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
	
	declare VAR_CD_FORMAT_DDMMYY											varchar( 15 ) default 'dd/MMM/yy';
	declare VAR_CD_FORMAT_DDMMYYYY											varchar( 15 ) default 'dd/MMM/yyyy';
	
	declare CD_SHEET_TITULAR												int( 3 ) default 0;
	declare CD_SHEET_DEPENDENTE												int( 3 ) default 4;
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
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 254');
	set VAR_ID_ARQUIVO_INPUT = FUNC_FIND_ARQUIVO_INPUT( 'CARGILL','MECSAS');
	
	call PROC_LOG_MESSAGE('LINHA - 257');
	select 	ID into VAR_ID_COLUMN_002_CD_EMPRESA
	from 	TB_ARQUIVO_INPUT_COLS_DEF
	where	ID_ARQUIVO_INPUT 	= VAR_ID_ARQUIVO_INPUT
	and		NM_COLUMN			= 'COLUMN_002_CD_EMPRESA';

	call PROC_LOG_MESSAGE('LINHA - 257');
	select 	ID into VAR_ID_COLUMN_010_CD_PLANO
	from 	TB_ARQUIVO_INPUT_COLS_DEF
	where	ID_ARQUIVO_INPUT 	= VAR_ID_ARQUIVO_INPUT
	and		NM_COLUMN			= 'COLUMN_010_CD_PLANO';
	
	call PROC_LOG_MESSAGE('LINHA - 1458');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* BENEFICIÁRIO TITULAR */
	
	call PROC_LOG_MESSAGE( 'LINHA - 2527' );
	update TB_ARQUIVO_INPUT_COLS_DEF
	set
			CD_TYPE	= VAR_COL_VARCHAR
	where	ID		= VAR_ID_COLUMN_010_CD_PLANO;	
	
    call PROC_LOG_MESSAGE( 'LINHA - 2527' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_ARQUIVO_INPUT_COLS_DEF,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO,
        VAR_ID_COLUMN_002_CD_EMPRESA,
        
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
