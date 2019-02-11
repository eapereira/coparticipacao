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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180918-001-dml-CARGILL.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180919-001-dml-CARGILL-MECSAS.sql';
	
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
	declare VAR_DT_FORMAT					varchar( 40 ) default 'yyyyMMdd';
	declare VAR_DT_FORMAT_YYYYMMDD			varchar( 40 ) default 'yyyyMMdd';
	
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
	declare VAR_CD_BENEFICIARIO_COLS_VL_FATOR_MODERADOR_INSS           	bigint( 17 ) default 109;
	declare VAR_CD_BENEFICIARIO_COLS_VL_ALIQUOTA_INSS      				bigint( 17 ) default 110;
	declare VAR_CD_BENEFICIARIO_COLS_VL_INSS              				bigint( 17 ) default 111;
	declare VAR_CD_BENEFICIARIO_COLS_VL_LIQUIDO_SINISTRO              	bigint( 17 ) default 112;
		
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
	
	declare VAR_CD_SHEET												bigint( 17 ) default 0;
	declare	VAR_ID_REGISTER												bigint( 17 );
	declare	VAR_CD_REGISTER_REG01										bigint( 17 ) default 0;
	
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
	call PROC_LOG_MESSAGE('LINHA - 238');
    select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = 'CARGILL';
	
    call PROC_LOG_MESSAGE('LINHA - 242');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = 'MECSAS'; 

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* MECSAS */
	
	call PROC_LOG_MESSAGE('LINHA - 251');
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
		'^(CARGILL)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(csv|CSV)$',
		'Arquivo de carga de beneficiários',
		VAR_ARQUIVO_TYPE_CSV,
		VAR_USE_TYPE_MECSAS,
		2,
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
		VAR_ID_ARQUIVO_INPUT_SHEET,
		'REG_01',		
		VAR_CD_REGISTER_REG01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	select max( ID ) into VAR_ID_REGISTER
	from TB_REGISTER; 
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 279');
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
		'COLUMN_001_NUM_LINHA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_001_NUM_LINHA from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 304');
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
		'COLUMN_002_CD_EMPRESA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_002_CD_EMPRESA from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 329');
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
		'COLUMN_003_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_003_NR_MATRICULA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 354');
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
		'COLUMN_004_DF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_004_DF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 379');
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
		'COLUMN_005_RDP',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_005_RDP from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 404');
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
		'COLUMN_006_NR_LOCAL',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_006_NR_LOCAL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 429');
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
		'COLUMN_007_CD_CATEGORIA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_007_CD_CATEGORIA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_008_SETOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_008_SETOR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 479');
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
		'COLUMN_009_ES',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_009_ES from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 504');
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
		'COLUMN_010_CD_PLANO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_010_CD_PLANO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 529');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_011_DT_ADESAO',
		VAR_COL_DATE,
		null,
		'yyyyMMdd',
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_011_DT_ADESAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 556');
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
		'COLUMN_012_NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_012_NR_CPF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 581');
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
		'COLUMN_013_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_013_NM_BENEFICIARIO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 606');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_014_DT_NASCIMENTO',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_014_DT_NASCIMENTO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 633');
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
		'COLUMN_015_SEXO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_015_SEXO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 658');
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
		'COLUMN_016_PERMANENCIA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_016_PERMANENCIA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 683');
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
		'COLUMN_017_GRAU_PARENTESCO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_017_GRAU_PARENTESCO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 251');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_018_DT_ADMISSAO',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT_YYYYMMDD,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_018_DT_ADMISSAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 736');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_019_DT_REF',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_019_DT_REF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 763');
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
		'COLUMN_020_BANCO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_020_BANCO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 788');
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
		'COLUMN_021_AGENCIA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_021_AGENCIA
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 813');
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
		'COLUMN_022_AGENCIA_DG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_022_AGENCIA_DG from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 838');
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
		'COLUMN_023_CONTA_CORRENTE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_023_CONTA_CORRENTE from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_024_CPF_CC',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_024_CPF_CC from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 888');
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
		'COLUMN_025_NM_TITULAR_CC',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_025_NM_TITULAR_CC from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 913');
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
		'COLUMN_026_CD_CARDIRF',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_026_CD_CARDIRF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 938');
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
		'COLUMN_027_NR_CEP',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_027_NR_CEP from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 963');
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
		'COLUMN_028_TP_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_028_TP_LOGRADOURO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 988');
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
		'COLUMN_029_NM_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_029_NM_LOGRADOURO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1013');
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
		'COLUMN_030_NUM',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_030_NUM from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1038');
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
		'COLUMN_031_COMPL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_031_COMPL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1063');
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
		'COLUMN_032_BAIRRO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_032_BAIRRO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1088');
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
		'COLUMN_033_MUNICIPIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_033_MUNICIPIO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1113');
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
		'COLUMN_034_UF',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_034_UF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_035_TEL_RESIDENCIAL',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_035_TEL_RESIDENCIAL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1163');
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
		'COLUMN_036_TEL_COMERCIAL',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_036_TEL_COMERCIAL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1188');
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
		'COLUMN_037_TEL_CELULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_037_TEL_CELULAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1213');
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
		'COLUMN_038_NM_MAE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_038_NM_MAE
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1238');
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
		'COLUMN_039_NR_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_039_NR_RG
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1263');
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
		'COLUMN_040_ORGAO_EMISSOR_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_040_ORGAO_EMISSOR_RG from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1288');
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
		'COLUMN_041_PAIS_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_041_PAIS_RG from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1313');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_042_DT_EMISSAO_RG',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_042_DT_EMISSAO_RG from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1340');
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
		'COLUMN_043_UF_RG',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_043_UF_RG from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1365');
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
		'COLUMN_044_PIS',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_044_PIS from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
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
		'COLUMN_045_CNS',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_045_CNS from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1415');
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
		'COLUMN_046_EMAIL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_046_EMAIL from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1440');
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
		'COLUMN_047_GRAU-ESCOLARIDADE',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_047_GRAU_ESCOLARIDADE from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1465');
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
		'COLUMN_048_RENDA_FAMILIAR',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_048_RENDA_FAMILIAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1499');
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
		'COLUMN_049_CD_PROFISSAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_049_CD_PROFISSAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1515');
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
		'COLUMN_050_CD_PAIS_ORIGEM',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_050_CD_PAIS_ORIGEM from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1540');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_051_DT_EXCLUSAO',
		VAR_COL_DATE,
		null,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_051_DT_EXCLUSAO 
    from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1568');
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
		'COLUMN_052_CD_MOTIVO_EXCLUSAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_052_CD_MOTIVO_EXCLUSAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1593');
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
		'COLUMN_053_CD_OPERACAO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_053_CD_OPERACAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1618');
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
		'COLUMN_054_CD_EMPRESA_TRANSF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_054_CD_EMPRESA_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1643');
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
		'COLUMN_055_NR_MATRICULA_TRANSF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_055_NR_MATRICULA_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1668');
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
		'COLUMN_056_NR_LOCAL_TRANSF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_056_NR_LOCAL_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1693');
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
		'COLUMN_057_NR_CATEGORIA_TRANSF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_057_NR_CATEGORIA_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_058_CD_PLANO_TRANSF',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_058_CD_PLANO_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_059_MOTIVO_REMISSAO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_059_MOTIVO_REMISSAO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_060_NR_CPF_NOVO_TITULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_060_NR_CPF_NOVO_TITULAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

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
		'COLUMN_061_QTDE_PERM_MESES',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_061_QTDE_PERM_MESES from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1814');
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
		'COLUMN_062_RDP_NOVO_TITULAR',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_062_RDP_NOVO_TITULAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1839');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_FORMAT,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_063_DT_INICIO_TRANSF',
		VAR_COL_VARCHAR,
		null,
		VAR_DT_FORMAT,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_063_DT_INICIO_TRANSF from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1886');
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
		'COLUMN_064_CD_STATUS',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_064_CD_STATUS
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1891');
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
		'COLUMN_065_CD_ERROR',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_065_CD_ERROR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 1916');
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
		'COLUMN_066_CD_DV',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_066_CD_DV from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1941');
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
		'COLUMN_067_BLOQ_EMPRESA_INADIMPLENCIA',
		
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_067_BLOQ_EMPRESA_INADIMPLENCIA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1967');
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
		'COLUMN_068_CPT',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_068_CPT from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 1992');
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
		'COLUMN_069_CD_EMPRESA_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_069_CD_EMPRESA_TITULAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2017');
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
		'COLUMN_70_NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_070_NR_MATRICULA from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2042');
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
		'COLUMN_071_DIF_MATRICULA_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_071_DIF_MATRICULA_TITULAR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2067');
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
		'COLUMN_072_NR_TITULO_ELEITOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_072_NR_TITULO_ELEITOR from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2092');
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
		'COLUMN_073_NR_RIC',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_073_NR_RIC from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2117');
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
		'COLUMN_074_NR_CERTIDAO_NASCIMENTO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_074_NR_CERTIDAO_NASCIMENTO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2142');
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
		'COLUMN_075_NR_CARTEIRA_IDENT',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_075_NR_CARTEIRA_IDENT from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2167');
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
		'COLUMN_076_IND_SEGURADO_CONTRIBUTARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_076_IND_SEGURADO_CONTRIBUTARIO from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2192');
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
		'COLUMN_077_IND_COND_EX_EMPREGADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_077_IND_COND_EX_EMPREGADO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2218');
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
		'COLUMN_078_IND_PERM_PLANO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_078_IND_PERM_PLANO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2244');
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
		'COLUMN_079_QTDE_MESES_CONTRIB',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_079_QTDE_MESES_CONTRIB 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2270');
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
		'COLUMN_080_NM_BENEFICIARIO_COMPLETO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_080_NM_BENEFICIARIO_COMPLETO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2298');
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
		'COLUMN_081_IND_TITULAR_REMIDO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_081_IND_TITULAR_REMIDO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2322');
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
		'COLUMN_082_EMAIL_SEGURADORA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_082_EMAIL_SEGURADORA 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2348');
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
		'COLUMN_083_IND_PORTABILIDADE_01',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_083_IND_PORTABILIDADE_01 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2374');
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
		'COLUMN_084_IND_PORTABILIDADE_02',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_084_IND_PORTABILIDADE_02 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2400');
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
		'COLUMN_085_IND_CARENCIA',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_085_IND_CARENCIA 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2426');
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
		'COLUMN_086_CD_PRODUTO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_086_CD_PRODUTO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2452');
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
		'COLUMN_087_CD_IND_PRODUTO_ANTERIOR_SAS',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_087_CD_IND_PRODUTO_ANTERIOR_SAS 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2478');
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
		'COLUMN_088_CID01',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_088_CID01 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2504');
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
		'COLUMN_089_CID02',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_089_CID02 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2530');
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
		'COLUMN_090_CID03',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_090_CID03 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 2556');
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
		'COLUMN_091_CID04',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_091_CID04 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2582');
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
		'COLUMN_092_CID05',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_092_CID05 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2608');
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
		'COLUMN_093_CID06',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_093_CID06 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2634');
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
		'COLUMN_094_CID07',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_094_CID07 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2660');
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
		'COLUMN_095_CID08',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_095_CID08 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2686');
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
		'COLUMN_096_CID09',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_096_CID09 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 2738');
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
		'COLUMN_097_CID10',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_097_CID10 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2764');
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
		'COLUMN_098_IBGE',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_098_IBGE 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2790');
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
		'COLUMN_099_CBO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_099_CBO 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2816');
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
		'COLUMN_100_DIF_TRANSF',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_100_DIF_TRANSF 
	from TB_REGISTER_COLUMN;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 2842');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* BENEFICIÁRIO */
	
    call PROC_LOG_MESSAGE('LINHA - 2847');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_003_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 2863');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_013_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4834');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_012_NR_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 4850');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_014_DT_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 2158');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
		VAR_ID_COLUMN_017_GRAU_PARENTESCO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);

	call PROC_LOG_MESSAGE('LINHA - 2827');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_ID_COLUMN_018_DT_ADMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2843');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_PLANO,
		VAR_ID_COLUMN_010_CD_PLANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
		
	call PROC_LOG_MESSAGE('LINHA - 2859');
	
call PROC_LOG_MESSAGE('LINHA - 957');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_DF,
		VAR_ID_COLUMN_004_DF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 973');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_RDP,
		VAR_ID_COLUMN_005_RDP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 989');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_LOCAL,
		VAR_ID_COLUMN_006_NR_LOCAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1005');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_CATEGORIA,
		VAR_ID_COLUMN_007_CD_CATEGORIA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1021');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_SETOR,
		VAR_ID_COLUMN_008_SETOR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 3112');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_ES,
		VAR_ID_COLUMN_009_ES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 3128');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_INCLUSAO,
		VAR_ID_COLUMN_011_DT_ADESAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1085');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_SEXO,
		VAR_ID_COLUMN_015_SEXO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1101');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_PERMANENCIA,
		VAR_ID_COLUMN_016_PERMANENCIA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1117');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_REF,
		VAR_ID_COLUMN_019_DT_REF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1133');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_BANCO,
		VAR_ID_COLUMN_020_BANCO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1148');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_AGENCIA,
		VAR_ID_COLUMN_021_AGENCIA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1165');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_AGENCIA_DG,
		VAR_ID_COLUMN_022_AGENCIA_DG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1181');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CONTA_CORRENTE,
		VAR_ID_COLUMN_023_CONTA_CORRENTE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1197');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_CC,
		VAR_ID_COLUMN_024_CPF_CC,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1213');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_TITULAR_CC,
		VAR_ID_COLUMN_025_NM_TITULAR_CC,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1229');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_CARDIF,
		VAR_ID_COLUMN_026_CD_CARDIRF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1245');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CEP,
		VAR_ID_COLUMN_027_NR_CEP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1261');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_LOGRADOURO,
		VAR_ID_COLUMN_028_TP_LOGRADOURO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1277');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_LOGRADOURO,
		VAR_ID_COLUMN_029_NM_LOGRADOURO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1293');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NUM_LOGRADOURO,
		VAR_ID_COLUMN_030_NUM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1309');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_COMPL,
		VAR_ID_COLUMN_031_COMPL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1325');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BAIRRO,
		VAR_ID_COLUMN_032_BAIRRO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1341');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_MUNICIPIO,
		VAR_ID_COLUMN_033_MUNICIPIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1357');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_UF,
		VAR_ID_COLUMN_034_UF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1373');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TEL_RESIDENCIAL,
		VAR_ID_COLUMN_035_TEL_RESIDENCIAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1389');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TEL_COMERCIAL,
		VAR_ID_COLUMN_036_TEL_COMERCIAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1405');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TEL_CELULAR,
		VAR_ID_COLUMN_037_TEL_CELULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1421');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_MAE,
		VAR_ID_COLUMN_038_NM_MAE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1437');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_RG,
		VAR_ID_COLUMN_039_NR_RG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1453');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_ORGAO_EMISSAO_RG,
		VAR_ID_COLUMN_040_ORGAO_EMISSOR_RG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1469');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_PAIS_RG,
		VAR_ID_COLUMN_041_PAIS_RG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1485');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_EMISSAO_RG,
		VAR_ID_COLUMN_042_DT_EMISSAO_RG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1501');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_UF_RG,
		VAR_ID_COLUMN_043_UF_RG,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1517');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_PIS,
		VAR_ID_COLUMN_044_PIS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1533');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CNS,
		VAR_ID_COLUMN_045_CNS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1549');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_EMAIL,
		VAR_ID_COLUMN_046_EMAIL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1565');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_GRAU_ESCOLARIDADE,
		VAR_ID_COLUMN_047_GRAU_ESCOLARIDADE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1581');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_VL_RENDA_FAMILIAR,
		VAR_ID_COLUMN_048_RENDA_FAMILIAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1597');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_PROFISSAO,
		VAR_ID_COLUMN_049_CD_PROFISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1613');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_PAIS_ORIGEM,
		VAR_ID_COLUMN_050_CD_PAIS_ORIGEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1629');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_EXCLUSAO,
		VAR_ID_COLUMN_051_DT_EXCLUSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1645');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_MOTIVO_EXCLUSAO,
		VAR_ID_COLUMN_052_CD_MOTIVO_EXCLUSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1661');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_OPERACAO,
		VAR_ID_COLUMN_053_CD_OPERACAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1677');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_EMPRESA_TITULAR_TRANSF,
		VAR_ID_COLUMN_054_CD_EMPRESA_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1693');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA_TRANSF,
		VAR_ID_COLUMN_055_NR_MATRICULA_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1709');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_LOCAL_TRANSF,
		VAR_ID_COLUMN_056_NR_LOCAL_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1725');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_CATEGORIA_TRANSF,
		VAR_ID_COLUMN_057_NR_CATEGORIA_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1741');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_PLANO_TRANSF,
		VAR_ID_COLUMN_058_CD_PLANO_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1757');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_MOTIVO_REMISSAO,
		VAR_ID_COLUMN_059_MOTIVO_REMISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1773');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF_NOVO_TITULAR,
		VAR_ID_COLUMN_060_NR_CPF_NOVO_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1789');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_QTDE_PERM_MESES,
		VAR_ID_COLUMN_061_QTDE_PERM_MESES,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1805');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_RDP_NOVO_TITULAR,
		VAR_ID_COLUMN_062_RDP_NOVO_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1821');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_INICIO_TRANSF,
		VAR_ID_COLUMN_063_DT_INICIO_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1837');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_STATUS,
		VAR_ID_COLUMN_064_CD_STATUS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1853');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_ERROR,
		VAR_ID_COLUMN_065_CD_ERROR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1869');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_DV,
		VAR_ID_COLUMN_066_CD_DV,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1885');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_BLOQ_EMPRESA_INADIMPLENCIA,
		VAR_ID_COLUMN_067_BLOQ_EMPRESA_INADIMPLENCIA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1901');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CPT,
		VAR_ID_COLUMN_068_CPT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1917');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_EMPRESA_TITULAR,
		VAR_ID_COLUMN_069_CD_EMPRESA_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1933');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DIF_MATRICULA_TITULAR,
		VAR_ID_COLUMN_071_DIF_MATRICULA_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1949');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_TITULO_ELEITOR,
		VAR_ID_COLUMN_072_NR_TITULO_ELEITOR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1965');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_RIC,
		VAR_ID_COLUMN_073_NR_RIC,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 1981');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CERTIDAO_NASCIMENTO,
		VAR_ID_COLUMN_074_NR_CERTIDAO_NASCIMENTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 1997');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CARTEIRA_IDENT,
		VAR_ID_COLUMN_075_NR_CARTEIRA_IDENT,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2013');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_SEGURADO_CONTRIBUTARIO,
		VAR_ID_COLUMN_076_IND_SEGURADO_CONTRIBUTARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2829');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_COND_EX_EMPREGADO,
		VAR_ID_COLUMN_077_IND_COND_EX_EMPREGADO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2845');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_PERM_PLANO,
		VAR_ID_COLUMN_078_IND_PERM_PLANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2861');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_QTDE_MESES_CONTRIB,
		VAR_ID_COLUMN_079_QTDE_MESES_CONTRIB,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2877');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO_COMPLETO,
		VAR_ID_COLUMN_080_NM_BENEFICIARIO_COMPLETO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2893');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_TITULAR_REMIDO,
		VAR_ID_COLUMN_081_IND_TITULAR_REMIDO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2109');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_EMAIL_SEGURADORA,
		VAR_ID_COLUMN_082_EMAIL_SEGURADORA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2125');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_PORTABILIDADE_01,
		VAR_ID_COLUMN_083_IND_PORTABILIDADE_01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2141');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_PORTABILIDADE_02,
		VAR_ID_COLUMN_084_IND_PORTABILIDADE_02,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2157');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IND_CARENCIA,
		VAR_ID_COLUMN_085_IND_CARENCIA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2173');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_PRODUTO,
		VAR_ID_COLUMN_086_CD_PRODUTO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2189');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CD_IND_PLANO_ANTERIOR_SAS,
		VAR_ID_COLUMN_087_CD_IND_PRODUTO_ANTERIOR_SAS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2205');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID01,
		VAR_ID_COLUMN_088_CID01,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2221');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID02,
		VAR_ID_COLUMN_089_CID02,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2237');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID03,
		VAR_ID_COLUMN_090_CID03,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2253');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID04,
		VAR_ID_COLUMN_091_CID04,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2269');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID05,
		VAR_ID_COLUMN_092_CID05,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2285');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID06,
		VAR_ID_COLUMN_093_CID06,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2301');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID07,
		VAR_ID_COLUMN_094_CID07,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2317');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID08,
		VAR_ID_COLUMN_095_CID08,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2333');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID09,
		VAR_ID_COLUMN_096_CID09,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	call PROC_LOG_MESSAGE('LINHA - 2349');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CID10,
		VAR_ID_COLUMN_097_CID10,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2365');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_IBGE,
		VAR_ID_COLUMN_098_IBGE,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
	
	call PROC_LOG_MESSAGE('LINHA - 2381');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_CBO,
		VAR_ID_COLUMN_099_CBO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		

	call PROC_LOG_MESSAGE('LINHA - 2397');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DIF_TRANSF,
		VAR_ID_COLUMN_100_DIF_TRANSF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);		
		
	call PROC_LOG_MESSAGE( 'LINHA - 2527' );
    insert into TB_BENEFICIARIO_COLS(
        CD_BENEFICIARIO_COLS_DEF,
        ID_REGISTER_COLUMN,

        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO,
        VAR_ID_COLUMN_002_CD_EMPRESA,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );

	call PROC_LOG_MESSAGE('LINHA - 4391');	    	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
