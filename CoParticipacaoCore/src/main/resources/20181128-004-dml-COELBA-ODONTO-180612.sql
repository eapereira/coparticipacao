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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20181128-003-dml-COELBA-ODONTO-MECSAS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181128-004-dml-COELBA-ODONTO-180612.sql';
	
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

	declare VAR_ID_SHEET01_COLUMN_001_TP_REGISTRO 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_002_DT_UTILIZACAO 			bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_003_CD_USUARIO 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_004_NM_BENEFICIARIO 			bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_005_NM_PRESTADOR 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_006_TP_MEIO_UTILIZADO 		bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_007_NR_CNPJ_PRESTADOR 		bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_008_NM_SERVICO 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_009_NR_SR 					bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_010_VL_ORIGINAL 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_011_VL_REEMBOLSO 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_012_VL_PARTICIPACAO 			bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_013_VL_TOTAL_COPARTICIPACAO 	bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_014_NR_DOCUMENTO 				bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_015_NR_PROCEDIMENTO 			bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_016_NR_SEQ_PROCEDIMENTO 		bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_017_NR_CERTIFICADO 			bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_018_NR_MATRICULA_ESPECIAL 	bigint( 17 );
	declare VAR_ID_SHEET01_COLUMN_019_SUBFATURA 				bigint( 17 );	
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO							bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	
	declare VAR_COL_VIEW_LENGTH_ID_TITULAR								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_ID_DEPENDENTE							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_DEPENDENTE							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;

	declare VAR_COL_LANCAMENTO_NR_MATRICULA_DEPENDENTE					bigint( 17 ) default 1;
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 ) default 2;
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 ) default 3;
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 ) default 4;
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 ) default 5;
	declare VAR_COL_LANCAMENTO_DT_MOVIMENTO								bigint( 17 ) default 6;
	declare VAR_COL_LANCAMENTO_TP_VALOR									bigint( 17 ) default 7;
	declare VAR_COL_LANCAMENTO_NR_MATRICULA_TITULAR						bigint( 17 ) default 8;
	declare VAR_COL_LANCAMENTO_NR_CPF									bigint( 17 ) default 9;	
	declare VAR_COL_LANCAMENTO_NM_BENEFICIARIO							bigint( 17 ) default 10;
	declare VAR_COL_LANCAMENTO_NM_TITULAR								bigint( 17 ) default 11;
	declare VAR_COL_LANCAMENTO_DT_NASCIMENTO							bigint( 17 ) default 12;
	declare VAR_COL_LANCAMENTO_VL_REEMBOLSO								bigint( 17 ) default 13;
	declare VAR_COL_LANCAMENTO_VL_PARTICIPACAO							bigint( 17 ) default 14;
	declare VAR_COL_LANCAMENTO_CD_USUARIO								bigint( 17 ) default 15;

	declare VAR_ID_LANCAMENTO_INPUT										bigint( 17 );
	declare VAR_ID_LANCAMENTO_INPUT_SHEET								bigint( 17 );
    
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
	declare VAR_CD_BENEFICIARIO_COLS_CD_CONTRATO   			           	bigint( 17 ) default 107;
	declare VAR_CD_BENEFICIARIO_COLS_NR_SUBFATURA  		            	bigint( 17 ) default 108;
	declare VAR_CD_BENEFICIARIO_COLS_VL_FATOR_MODERADOR_INSS           	bigint( 17 ) default 109;
	declare VAR_CD_BENEFICIARIO_COLS_VL_ALIQUOTA_INSS 	             	bigint( 17 ) default 110;
	declare VAR_CD_BENEFICIARIO_COLS_VL_INSS  			            	bigint( 17 ) default 111;
	declare VAR_CD_BENEFICIARIO_COLS_VL_LIQUIDO_SINISTRO              	bigint( 17 ) default 112;
	declare VAR_CD_BENEFICIARIO_COLS_IND_EVENTO 		             	bigint( 17 ) default 113;
	declare VAR_CD_BENEFICIARIO_COLS_CD_USUARIO         		     	bigint( 17 ) default 114;
	declare VAR_CD_BENEFICIARIO_COLS_NR_CERTIFICADO        		     	bigint( 17 ) default 115;
	
	declare VAR_TP_REGRA_SIMPLES											int( 3 )  default 1;
	declare VAR_TP_REGRA_CONDITIONAL										int( 3 )  default 2;
	
	declare VAR_TP_REGRA_OPERATION_ADD										int( 3 )  default 1;
	declare VAR_TP_REGRA_OPERATION_SUBSTRACT								int( 3 )  default 2;
	declare VAR_TP_REGRA_OPERATION_DIVIDE									int( 3 )  default 3;
	declare VAR_TP_REGRA_OPERATION_MULTIPLY									int( 3 )  default 4;
	declare VAR_TP_REGRA_OPERATION_EQUALS									int( 3 )  default 5;
	declare VAR_TP_REGRA_OPERATION_NOT_EQUALS								int( 3 )  default 6;
	
	declare VAR_CD_FORMAT_DDMMYY											varchar( 15 ) default 'dd/MM/yy';
	declare VAR_CD_FORMAT_DDMMYYYY											varchar( 15 ) default 'dd/MM/yyyy';
	
	declare CD_SHEET_TITULAR												int( 3 ) default 0;
	declare CD_SHEET_DEPENDENTE												int( 3 ) default 4;
	
	declare VAR_CD_RESTRICTED_VALUE											varchar( 10 ) default "2";
	declare VAR_CD_FORMAT_VL_ORIGINAL										varchar( 10 ) default "#,000.00";
	
	declare	VAR_ID_REGISTER												bigint( 17 );
	declare	VAR_CD_REGISTER_REG01										bigint( 17 ) default 1;
	declare	VAR_CD_REGISTER_REG02										bigint( 17 ) default 2;
	
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
    select	ID into VAR_ID_EMPRESA
    from 	TB_EMPRESA
    where 	CD_EMPRESA = '180612';
	
    call PROC_LOG_MESSAGE('LINHA - 242');
	select 	ID into VAR_ID_CONTRATO
	from 	TB_CONTRATO
	where	ID_EMPRESA	= VAR_ID_EMPRESA
	and 	CD_CONTRATO = '180612'; 

	/***********************************************************************************************************************/
	/***********************************************************************************************************************/		
	/* MECSAS */
	
	call PROC_LOG_MESSAGE('LINHA - 293');
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
		'^(180612)\\.(180612)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de carga de beneficiários',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_FATUCOPA,
		0,
		null,
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT from TB_ARQUIVO_INPUT;	

	call PROC_LOG_MESSAGE('LINHA - 321');
	insert into TB_ARQUIVO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		CD_SHEET,

		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED			
	) values (
		VAR_ID_ARQUIVO_INPUT,		
		CD_SHEET_TITULAR,
		
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
	
	call PROC_LOG_MESSAGE('LINHA - 343');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_RESTRICTED_VALUE,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_001_TP_REGISTRO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_RESTRICTED_VALUE,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_001_TP_REGISTRO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 369');
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
		'COLUMN_002_DT_UTILIZACAO',
		VAR_COL_DATE,
		null,
		VAR_CD_FORMAT_DDMMYYYY,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_002_DT_UTILIZACAO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 395');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_003_CD_USUARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_003_CD_USUARIO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 421');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_004_NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_004_NM_BENEFICIARIO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 447');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_005_NM_PRESTADOR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_005_NM_PRESTADOR
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 473');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_006_TP_MEIO_UTILIZADO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_006_TP_MEIO_UTILIZADO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 499');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_007_NR_CNPJ_PRESTADOR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_007_NR_CNPJ_PRESTADOR
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_008_NM_SERVICO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_008_NM_SERVICO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_009_NR_SR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_009_NR_SR
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
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
		'COLUMN_010_VL_ORIGINAL',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_FORMAT_VL_ORIGINAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_010_VL_ORIGINAL
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
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
		'COLUMN_011_VL_REEMBOLSO',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_FORMAT_VL_ORIGINAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_011_VL_REEMBOLSO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
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
		'COLUMN_012_VL_PARTICIPACAO',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_FORMAT_VL_ORIGINAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_012_VL_PARTICIPACAO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
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
		'COLUMN_013_VL_TOTAL_COPARTICIPACAO',
		VAR_COL_DOUBLE,
		null,
		VAR_CD_FORMAT_VL_ORIGINAL,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_013_VL_TOTAL_COPARTICIPACAO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_014_NR_DOCUMENTO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_014_NR_DOCUMENTO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_015_NR_PROCEDIMENTO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_015_NR_PROCEDIMENTO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_016_NR_SEQ_PROCEDIMENTO',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_016_NR_SEQ_PROCEDIMENTO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_017_NR_CERTIFICADO',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_017_NR_CERTIFICADO
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 302');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_018_NR_MATRICULA_ESPECIAL',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_018_NR_MATRICULA_ESPECIAL
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 783');
	insert into TB_REGISTER_COLUMN(
		ID_REGISTER,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_REGISTER,
		'COLUMN_019_SUBFATURA',
		VAR_COL_INT,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_SHEET01_COLUMN_019_SUBFATURA
	from TB_REGISTER_COLUMN; 
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 809');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* LANCAMENTO */
	
    call PROC_LOG_MESSAGE('LINHA - 814');
    insert into TB_LANCAMENTO_INPUT_SHEET (
        ID_ARQUIVO_INPUT_SHEET,
        
        USER_CREATED,
        DT_CREATED,
        DT_ALTERED ) values (
        VAR_ID_ARQUIVO_INPUT_SHEET,
        
        VAR_ID_USER,
        current_timestamp(),
        current_timestamp()	
    );
    
    select max( ID ) into VAR_ID_LANCAMENTO_INPUT_SHEET 
    from TB_LANCAMENTO_INPUT_SHEET;
	
	call PROC_LOG_MESSAGE('LINHA - 831');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED 
	) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_NR_MATRICULA_DEPENDENTE,
		VAR_ID_SHEET01_COLUMN_017_NR_CERTIFICADO,		
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);
			
	call PROC_LOG_MESSAGE('LINHA - 850');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_VL_PRINCIPAL,
		VAR_ID_SHEET01_COLUMN_010_VL_ORIGINAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 886');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_NM_BENEFICIARIO,
		VAR_ID_SHEET01_COLUMN_004_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 904');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_NR_MATRICULA_TITULAR,
		VAR_ID_SHEET01_COLUMN_017_NR_CERTIFICADO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 922');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_VL_REEMBOLSO,
		VAR_ID_SHEET01_COLUMN_011_VL_REEMBOLSO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 940');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_VL_PARTICIPACAO,
		VAR_ID_SHEET01_COLUMN_012_VL_PARTICIPACAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	

	call PROC_LOG_MESSAGE('LINHA - 984');
	insert into TB_LANCAMENTO_INPUT_SHEET_COLS (
		ID_LANCAMENTO_INPUT_SHEET,
		CD_LANCAMENTO_COLS_DEF,
		ID_REGISTER_COLUMN,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_LANCAMENTO_INPUT_SHEET,
		VAR_COL_LANCAMENTO_CD_USUARIO,
		VAR_ID_SHEET01_COLUMN_003_CD_USUARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()	
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 995');	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/		
	/* ARQUIVO_OUTPUT */
		
	/* FASTU-COPA.1 */	    
	call PROC_LOG_MESSAGE('LINHA - 1001');
	insert into TB_ARQUIVO_OUTPUT(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		DESCR_ARQUIVO,
		TP_ARQUIVO,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'COELBA(Odonto)-Bradesco(Saude)_Coparticipacao_({YYYY}{MM}).xlsx',
		'Arquivo de saída para a carga dos lançamentos FATU COPA',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT from TB_ARQUIVO_OUTPUT;
	
	/*****************************************************************************************************************************************************/
    call PROC_LOG_MESSAGE('LINHA - 1024');
	select ID into VAR_ID_VIEW_DESTINATION
	from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_COPARTICIPACAO_COELBA_ODONTO';
	
    call PROC_LOG_MESSAGE('LINHA - 1029');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION,
		'%s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 1047');
	/*****************************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 1049');
	select 	ID into VAR_ID_VIEW_DESTINATION
	from 	TB_VIEW_DESTINATION
    where 	NM_VIEW = 'VW_RESUMO_DETAIL_COELBA_ODONTO';

    call PROC_LOG_MESSAGE('LINHA - 1054');
	insert into TB_ARQUIVO_OUTPUT_SHEET(
		ID_ARQUIVO_OUTPUT,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT,
		VAR_ID_VIEW_DESTINATION,
		'RESUMO %s',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	call PROC_LOG_MESSAGE('LINHA - 1072');
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/			
	/* NAO-LOCALIZADOS */
	
    call PROC_LOG_MESSAGE('LINHA - 1102');
	select ID into VAR_ID_VIEW_DESTINATION
	from TB_VIEW_DESTINATION
    where NM_VIEW = 'VW_DESCONHECIDO_COELBA_ODONTO';
	
	call PROC_LOG_MESSAGE('LINHA - 1107');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
		ID_ARQUIVO_INPUT,
		NM_ARQUIVO_FORMAT,
		NM_DESCR_ARQUIVO,	
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT,
		'NAO-LOCALIZADO-COELBA-ODONTO-{YYYY}{MM}.xlsx',
		'Arquivo com os beneficiários não localizados pelo processo',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
		
	select max( ID ) into VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO from TB_ARQUIVO_OUTPUT_DESCONHECIDO;
		
	call PROC_LOG_MESSAGE('LINHA - 1044');
	insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_SHEET(
		ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		ID_VIEW_DESTINATION,
		NM_SHEET,
		CD_ORDEM,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO,
		VAR_ID_VIEW_DESTINATION,
		'%s',
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
					
	call PROC_LOG_MESSAGE('LINHA - 1064');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
