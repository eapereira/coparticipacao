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
	DECLARE VAR_ID_CONTRATO_FATUCOPA 		bigint( 17 );
	DECLARE VAR_ID_CONTRATO 			bigint( 17 );
	
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z8			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C5Z9			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XY			bigint( 17 );
	declare VAR_ID_CONTRATO_FATUCOPA_8C7XX			bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z8		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C5Z9		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XY		bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_FATUCOPA_8C7XX		bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT 					bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_SHEET 				bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
			
	declare VAR_ID_COLUMN_NUM_LINHA				bigint( 17 );
	declare VAR_ID_COLUMN_COD_EMP					bigint( 17 );
	declare VAR_ID_COLUMN_MATRICULA				bigint( 17 );
	declare VAR_ID_COLUMN_DF						bigint( 17 );
	declare VAR_ID_COLUMN_RDP						bigint( 17 );
	declare VAR_ID_COLUMN_LOCAL					bigint( 17 );
	declare VAR_ID_COLUMN_CATEGORIA				bigint( 17 );
	declare VAR_ID_COLUMN_SETOR					bigint( 17 );
	declare VAR_ID_COLUMN_ES						bigint( 17 );
	declare VAR_ID_COLUMN_PLANO					bigint( 17 );
	declare VAR_ID_COLUMN_DATA_ADESAO				bigint( 17 );
	declare VAR_ID_COLUMN_CPF						bigint( 17 );
	declare VAR_ID_COLUMN_NOME_BENEF				bigint( 17 );
	declare VAR_ID_COLUMN_DATA_NASC				bigint( 17 );
	declare VAR_ID_COLUMN_SEXO						bigint( 17 );
	declare VAR_ID_COLUMN_PERMANENCIA				bigint( 17 );
	declare VAR_ID_COLUMN_GP						bigint( 17 );
	declare VAR_ID_COLUMN_DATA_ADM					bigint( 17 );
	declare VAR_ID_COLUMN_DATA_REF					bigint( 17 );
	declare VAR_ID_COLUMN_BANCO					bigint( 17 );
	declare VAR_ID_COLUMN_AGENDA					bigint( 17 );
	declare VAR_ID_COLUMN_DG_AGEN					bigint( 17 );
	declare VAR_ID_COLUMN_CONTA_CORRENTE			bigint( 17 );
	declare VAR_ID_COLUMN_CPF_CONTA_CORRENTE		bigint( 17 );
	declare VAR_ID_COLUMN_NOME_TITULAR_CC			bigint( 17 );
	declare VAR_ID_COLUMN_CODCARDIF				bigint( 17 );
	declare VAR_ID_COLUMN_NUM_CEP					bigint( 17 );
	declare VAR_ID_COLUMN_TIPO_LOGRADOURO			bigint( 17 );
	declare VAR_ID_COLUMN_LOGRADOURO				bigint( 17 );
	declare VAR_ID_COLUMN_NUMERO					bigint( 17 );
	declare VAR_ID_COLUMN_COMP_LOGRADOURO			bigint( 17 );
	declare VAR_ID_COLUMN_BAIRRO					bigint( 17 );
	declare VAR_ID_COLUMN_MUNICIPIO				bigint( 17 );
	declare VAR_ID_COLUMN_UF						bigint( 17 );
	declare VAR_ID_COLUMN_TEL_RESID				bigint( 17 );
	declare VAR_ID_COLUMN_TEL_COM					bigint( 17 );
	declare VAR_ID_COLUMN_TEL_CEL					bigint( 17 );
	declare VAR_ID_COLUMN_NOMEDAMAEBENE			bigint( 17 );
	declare VAR_ID_COLUMN_RG						bigint( 17 );
	declare VAR_ID_COLUMN_ORGAOEMISSORG			bigint( 17 );
	declare VAR_ID_COLUMN_PAISEMISSORRG			bigint( 17 );
	declare VAR_ID_COLUMN_DATAEMISSAORG			bigint( 17 );
	declare VAR_ID_COLUMN_ESTADORG					bigint( 17 );
	declare VAR_ID_COLUMN_PIS						bigint( 17 );
	declare VAR_ID_COLUMN_CNS						bigint( 17 );
	declare VAR_ID_COLUMN_EMAIL					bigint( 17 );
	declare VAR_ID_COLUMN_GRAUESCOLARIDADE			bigint( 17 );
	declare VAR_ID_COLUMN_RENDAFAMILIAR			bigint( 17 );
	declare VAR_ID_COLUMN_CDPROFISSAO				bigint( 17 );
	declare VAR_ID_COLUMN_CDPAISDEORIGEM				bigint( 17 );
	declare VAR_ID_COLUMN_DATAEXCLUSAO				bigint( 17 );
	declare VAR_ID_COLUMN_CODMOVEXCLUSAO				bigint( 17 );
	declare VAR_ID_COLUMN_CODOPERACAO				bigint( 17 );
	declare VAR_ID_COLUMN_CODEMPRESATRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_MATRICULATRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_LOCALTRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_CATTRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_PLANOTRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_MOTREMISSAO				bigint( 17 );
	declare VAR_ID_COLUMN_CPFNOVOTITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_QTDPERMAMESES				bigint( 17 );
	declare VAR_ID_COLUMN_RDPNOVOTITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_DTINICIOTRANSF				bigint( 17 );
	declare VAR_ID_COLUMN_COD_STATUS					bigint( 17 );
	declare VAR_ID_COLUMN_COD_ERRO						bigint( 17 );
	declare VAR_ID_COLUMN_COD_DV								bigint( 17 );
	declare VAR_ID_COLUMN_BLOQ_EMPR_INADIMPLENCIA				bigint( 17 );
	declare VAR_ID_COLUMN_CPT								bigint( 17 );
	declare VAR_ID_COLUMN_COD_EMPRESA_TITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_MATRICULA_02						bigint( 17 );
	declare VAR_ID_COLUMN_DIFERENCIADOR_MATRICULA_TITULAR				bigint( 17 );
	declare VAR_ID_COLUMN_NR_TITULO_ELEITOR				bigint( 17 );
	declare VAR_ID_COLUMN_NR_RIC							bigint( 17 );
	declare VAR_ID_COLUMN_NR_DECL_NASCIDO_VIVO				bigint( 17 );
	declare VAR_ID_COLUMN_CARTEIRA_IDENTIFICACAO				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_SEGURADO_CONTRIBUTARIO				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_CONDICAO_EX_EMPREGADO				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_PERM_PLANO				bigint( 17 );
	declare VAR_ID_COLUMN_QTDE_MESES_CONTRIB				bigint( 17 );
	declare VAR_ID_COLUMN_NM_COMPLETO_BENEF				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_TITULAR_REMIDO				bigint( 17 );
	declare VAR_ID_COLUMN_EMAIL_SEGURADORA				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_PORTABILIDADE_01				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_PORTABILIDADE_2				bigint( 17 );
	declare VAR_ID_COLUMN_INDICADOR_CARENCIA				bigint( 17 );
	declare VAR_ID_COLUMN_CD_PRODUTO				bigint( 17 );
	declare VAR_ID_COLUMN_CD_IDENT_PLANO_ANTERIOR_SAS				bigint( 17 );
	declare VAR_ID_COLUMN_CI01				bigint( 17 );
	declare VAR_ID_COLUMN_CI02				bigint( 17 );
	declare VAR_ID_COLUMN_CI03				bigint( 17 );
	declare VAR_ID_COLUMN_CI04				bigint( 17 );
	declare VAR_ID_COLUMN_CI05				bigint( 17 );
	declare VAR_ID_COLUMN_CI06				bigint( 17 );
	declare VAR_ID_COLUMN_CI07				bigint( 17 );
	declare VAR_ID_COLUMN_CI08				bigint( 17 );
	declare VAR_ID_COLUMN_CI09				bigint( 17 );
	declare VAR_ID_COLUMN_CI010				bigint( 17 );
	declare VAR_ID_COLUMN_IBGE				bigint( 17 );
	declare VAR_ID_COLUMN_CBO				bigint( 17 );
	declare VAR_ID_COLUMN_DIF_TRANSF				bigint( 17 );
	
	declare VAR_ISENTOS_NM_CONTRATO				bigint( 17 );
	declare VAR_ISENTOS_NR_MATRICULA			bigint( 17 );
	declare VAR_ISENTOS_NM_BENEFICIARIO			bigint( 17 );
	declare VAR_ISENTOS_NR_CPF					bigint( 17 );
	declare VAR_ISENTOS_NR_MATRICULA_TITULAR	bigint( 17 );
	declare VAR_ISENTOS_NM_TITULAR				bigint( 17 );

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
	declare VAR_ID_VIEW_DESTINATION_COPART								bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_GESTANTE							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_ESTAGIARIO							bigint( 17 );
	declare VAR_ID_VIEW_DESTINATION_FILHOS								bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	
	declare VAR_COL_LANCAMENTO_ID_DEPENDENTE							bigint( 17 );
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 );
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 );
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 );
		
	declare CD_BENEFICIARIO_COLS_DEF_NM_CONTRATO						bigint( 17 ) default 0;
	

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

	declare VAR_CD_SHEET												int( 3 ) default 0;
	declare VAR_CD_ORDEM												int( 3 ) default 0;
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
    call PROC_LOG_MESSAGE('LINHA - 236');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where CD_EMPRESA = 'ABBVIE';
        
    call PROC_LOG_MESSAGE('LINHA - 123');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = 'MECSAS'; 
	
	/***********************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 1341');
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
		'^(ABBVIE)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(CSV|csv)$',
		'Arquivo de carga de coparticipação',
		VAR_ARQUIVO_TYPE_CSV,
		VAR_USE_TYPE_MECSAS,
		2,
		null, /* Não é usado para arquivo CSV */
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;
	
	/*****************************************************************************************************************************************************/
	/* MECSAS  */
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
	
	call PROC_LOG_MESSAGE('LINHA - 1379');
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
	
	select max( ID ) into VAR_ID_COLUMN_NUM_LINHA from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1401');
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
	
	select max( ID ) into VAR_ID_COLUMN_COD_EMP from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1423');
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_MATRICULA from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1445');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_RDP from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_LOCAL from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CATEGORIA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_SETOR from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_ES from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1607');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_PLANO from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATA_ADESAO from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CPF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NOME_BENEF from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATA_NASC from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1725');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_SEXO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_PERMANENCIA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_GP from TB_REGISTER_COLUMN;
	
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
		4, /* VARCHAR */
		null,
		18,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATA_ADM from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATA_REF from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1839');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_BANCO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_AGENDA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DG_AGEN from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CONTA_CORRENTE from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CPF_CONTA_CORRENTE from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 1949');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NOME_TITULAR_CC from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CODCARDIF from TB_REGISTER_COLUMN;
	
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
		1, /* VARCHAR */
		null,
		27,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NUM_CEP from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_TIPO_LOGRADOURO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_LOGRADOURO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NUMERO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_COMP_LOGRADOURO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_BAIRRO from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 2118');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_MUNICIPIO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_UF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_TEL_RESID from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_TEL_COM from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_TEL_CEL from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NOMEDAMAEBENE from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_RG from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_ORGAOEMISSORG from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_PAISEMISSORRG from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 2389');
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATAEMISSAORG from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_ESTADORG from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_PIS from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CNS from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_EMAIL from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_GRAUESCOLARIDADE from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_RENDAFAMILIAR from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CDPROFISSAO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CDPAISDEORIGEM from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DATAEXCLUSAO from TB_REGISTER_COLUMN;
	
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
		'CODMOVEXCLUSAO',
		VAR_COL_INT,
		null,
		52,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CODMOVEXCLUSAO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CODOPERACAO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CODEMPRESATRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_MATRICULATRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_LOCALTRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CATTRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_PLANOTRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_MOTREMISSAO from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CPFNOVOTITULAR from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_QTDPERMAMESES from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_RDPNOVOTITULAR from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 2768');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DTINICIOTRANSF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_COD_STATUS from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_COD_ERRO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_COD_DV from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_BLOQ_EMPR_INADIMPLENCIA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CPT from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_COD_EMPRESA_TITULAR from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_MATRICULA_02 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_DIFERENCIADOR_MATRICULA_TITULAR from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NR_TITULO_ELEITOR from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 2979');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NR_RIC from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NR_DECL_NASCIDO_VIVO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CARTEIRA_IDENTIFICACAO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_SEGURADO_CONTRIBUTARIO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_CONDICAO_EX_EMPREGADO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_PERM_PLANO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_QTDE_MESES_CONTRIB from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_NM_COMPLETO_BENEF from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_TITULAR_REMIDO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_EMAIL_SEGURADORA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_PORTABILIDADE_01 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_PORTABILIDADE_2 from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 3232');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_INDICADOR_CARENCIA from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CD_PRODUTO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CD_IDENT_PLANO_ANTERIOR_SAS from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI01 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI02 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI03 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI04 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI05 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI06 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI07 from TB_REGISTER_COLUMN;
	
	call PROC_LOG_MESSAGE('LINHA - 3443');
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI08 from TB_REGISTER_COLUMN;
	
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
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI09 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CI010 from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_IBGE from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_COLUMN_CBO from TB_REGISTER_COLUMN;
	
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
		1,
		current_timestamp(),
		current_timestamp()
	);	
	
	select max( ID ) into VAR_ID_COLUMN_DIF_TRANSF from TB_REGISTER_COLUMN;
	
	/*****************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************/
	/* Beneficiário */
				
	/* MECSAS */
	call PROC_LOG_MESSAGE('LINHA - 4867');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_ID_COLUMN_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_ID_COLUMN_NOME_BENEF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_ID_COLUMN_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4913');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_ID_COLUMN_DATA_NASC,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
			
	call PROC_LOG_MESSAGE('LINHA - 4929');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
		VAR_ID_COLUMN_GP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	call PROC_LOG_MESSAGE('LINHA - 2521');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_REGISTER_COLUMN,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_ID_COLUMN_DATA_ADESAO,
		
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
        VAR_ID_COLUMN_COD_EMP,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()		
    );
    
	call PROC_LOG_MESSAGE('LINHA - 2537');    	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
