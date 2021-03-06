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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20181128-018-dml-TECHNIT-SAUDE-NAO-LOCALIZADO.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181201-002-dml-SPREAD-SAUDE.sql';
	
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
	declare VAR_USE_TYPE_EXTRA_FILE			int( 3 ) default 6;
	
	declare VAR_COL_VARCHAR					int( 3 ) default 3;
	declare VAR_COL_INT						int( 3 ) default 1;
	declare VAR_COL_DATE					int( 3 ) default 4;
	declare VAR_COL_LONG					int( 3 ) default 5;
	declare VAR_COL_DOUBLE					int( 3 ) default 2;
	
	declare VAR_ARQUIVO_TYPE_FLATFILE		int( 3 ) default 1;
	declare VAR_ARQUIVO_TYPE_CSV			int( 3 ) default 2;
	declare VAR_ARQUIVO_TYPE_SPREADSHEET	int( 3 ) default 3;
	
	declare VAR_ID_OPERADORA						bigint( 17 );
	DECLARE VAR_ID_USER 							bigint( 17 ) default 1;
	DECLARE VAR_ID_EMPRESA 							bigint( 17 );
	DECLARE VAR_ID_CONTRATO 						bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT					bigint( 17 );
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT_DESCONHECIDO				bigint( 17 );
		
	declare VAR_ID_REGRA												bigint( 17 );
	declare VAR_ID_REGRA_OPERATION										bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL									bigint( 17 );
	declare VAR_ID_REGRA_CONDITIONAL_OPERATION							bigint( 17 );
	declare VAR_ID_REGRA_TO_EXECUTE										bigint( 17 );
	
	declare VAR_ID_ISENTO_INPUT_SHEET									bigint( 17 );
	
	declare VAR_ID_ARQUIVO_OUTPUT										bigint( 17 );
    declare VAR_ID_VIEW_DESTINATION										bigint( 17 );
	
    declare VAR_COL_VIEW_LENGTH_NR_SUBFATURA							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_CERTIFICADO							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA					int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_FATOR_MODERADOR						int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_FATOR_MODERADOR_INSS					int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA_ESPECIAL					int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DESCR_PROFISSAO							int( 3 ) default 30;
	declare VAR_COL_VIEW_LENGTH_NR_CPF_TITULAR							int( 3 ) default 20;	
	declare VAR_COL_VIEW_LENGTH_DT_MOVIMENTO							int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_CD_CONTRATO								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_CD_EMPRESA								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_NUM_SEGURADOS							int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PROPORCAO							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_ALOCACAO								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_ALIQUOTA_INSS						int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_INSS									int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_VL_LIQUIDO_SINISTRO						int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_PLANO								int( 3 ) default 20;

    declare VAR_COL_LABEL_NR_SUBFATURA									varchar( 40 ) default 'SUBFATURA';
	declare VAR_COL_LABEL_NR_CERTIFICADO								varchar( 40 ) default 'CERTIFICADO';
	declare VAR_COL_LABEL_NR_MATRICULA									varchar( 40 ) default 'MATRICULA';
	declare VAR_COL_LABEL_NR_MATRICULA_EMPRESA							varchar( 40 ) default 'MATRICULA';
	declare VAR_COL_LABEL_NM_TITULAR									varchar( 40 ) default 'NOME TÍTULAR';
	declare VAR_COL_LABEL_NM_BENEFICIARIO								varchar( 40 ) default 'NOME BENEFICIÁRIO';
	declare VAR_COL_LABEL_VL_FATOR_MODERADOR							varchar( 40 ) default 'FATOR MODERADOR';
	declare VAR_COL_LABEL_VL_FATOR_MODERADOR_INSS						varchar( 40 ) default 'FATOR MODERADOR INSS';
	declare VAR_COL_LABEL_NR_MATRICULA_ESPECIAL							varchar( 40 ) default 'MATRICULA ESPECIAL';
	declare VAR_COL_LABEL_DESCR_PROFISSAO								varchar( 40 ) default 'CARGO';
	declare VAR_COL_LABEL_NR_CPF_TITULAR								varchar( 40 ) default 'CPF TÍTULAR';	
	declare VAR_COL_LABEL_DT_MOVIMENTO									varchar( 40 ) default 'DT MOVIMENTO';
	declare VAR_COL_LABEL_CD_CONTRATO									varchar( 40 ) default 'CONTRATO';
	declare VAR_COL_LABEL_CD_EMPRESA									varchar( 40 ) default 'EMPRESA';
	declare VAR_COL_LABEL_NUM_SEGURADOS									varchar( 40 ) default 'VIDAS';
	declare VAR_COL_LABEL_VL_PROPORCAO									varchar( 40 ) default 'PROPORÇÃO (%)';
	declare VAR_COL_LABEL_VL_ALOCACAO									varchar( 40 ) default 'VALOR ALOCAÇÃO';
	declare VAR_COL_LABEL_VL_ALIQUOTA_INSS								varchar( 40 ) default 'VALOR ALIQUOTA INSS';
	declare VAR_COL_LABEL_VL_INSS										varchar( 40 ) default 'VALOR INSS';
	declare VAR_COL_LABEL_VL_LIQUIDO_SINISTRO							varchar( 40 ) default 'VALOR LÍQUIDO DO SINISTRO';
	declare VAR_COL_LABEL_CD_PLANO										varchar( 40 ) default 'PLANO';
	
	declare VAR_COL_LANCAMENTO_ID_DEPENDENTE							bigint( 17 ) default 1;
	declare VAR_COL_LANCAMENTO_ID_CONTRATO								bigint( 17 ) default 2;
	declare VAR_COL_LANCAMENTO_CD_MES									bigint( 17 ) default 3;
	declare VAR_COL_LANCAMENTO_CD_ANO									bigint( 17 ) default 4;
	declare VAR_COL_LANCAMENTO_VL_PRINCIPAL								bigint( 17 ) default 5;	
	declare VAR_COL_LANCAMENTO_VL_REEMBOLSO								bigint( 17 ) default 13;
	declare VAR_COL_LANCAMENTO_VL_PARTICIPACAO							bigint( 17 ) default 14;

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
	declare VAR_CD_BENEFICIARIO_COLS_VL_FATOR_MODERADOR_INSS           	bigint( 17 ) default 109;
	declare VAR_CD_BENEFICIARIO_COLS_VL_ALIQUOTA_INSS      				bigint( 17 ) default 110;
	declare VAR_CD_BENEFICIARIO_COLS_VL_INSS              				bigint( 17 ) default 111;
	declare VAR_CD_BENEFICIARIO_COLS_VL_LIQUIDO_SINISTRO              	bigint( 17 ) default 112;
		
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'EX - Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'PC - Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'PS - Base de Ativos do Cliente';
	declare VAR_NM_CONTRATO_ISENTO										varchar( 400 ) default 'Base de Isenção';
	declare VAR_NM_CONTRATO_PRN											varchar( 400 ) default 'Arquivo PRN';
	
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
	call PROC_LOG_MESSAGE('LINHA - 142');
	select	ID into VAR_ID_OPERADORA
	from	TB_OPERADORA
	where	CD_OPERADORA = 'BRADESCO';
	
    call PROC_LOG_MESSAGE('LINHA - 147');
	insert into TB_EMPRESA (
		ID_OPERADORA,
		NM_EMPRESA,	
		CD_EMPRESA,
		CD_AUTOMATIC_CREATE_BENEFICIARIO,
		CD_ACCEPT_TITULAR_WITHOUT_CPF,
		CD_CREATE_BENEFICIARIO_FROM_MECSAS2,		
		CD_UPDATE_BENEFICIARIO_FROM_ISENTO,		
		CD_OUTPUT_REPORT_DIR,		
        CD_INPUT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        CD_GENERATE_OUTPUT_FILE_NOFATUCOPA,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,	
		CD_USE_JASPER_REPORTS,			
		TP_REPORT_QUERY,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA,
		'SPREAD-SAUDE',
		'073828',
        VAR_TRUE,		
        VAR_TRUE,		
        VAR_TRUE,		
        VAR_FALSE,
		
		'/coparticipacao/output-reports/bradesco/spread-saude/',
        '/coparticipacao/input/',
        '/coparticipacao/failure/',
		'/coparticipacao/output/',
		VAR_FALSE,
        VAR_FALSE,
        VAR_FALSE,
        VAR_TRUE,		
        0,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_EMPRESA from TB_EMPRESA;
	
	call PROC_LOG_MESSAGE('LINHA - 262');
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
		'073828',
	    '073828',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO
	from TB_CONTRATO;
	
	/***********************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 388');
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

	call PROC_LOG_MESSAGE('LINHA - 332');
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
	    'MECSAS2',
	    VAR_NM_CONTRATO_MECSAS2,
	    VAR_USE_TYPE_MECSAS2,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;	

	call PROC_LOG_MESSAGE('LINHA - 332');
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
	    VAR_FALSE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO from TB_CONTRATO;	
	
	call PROC_LOG_MESSAGE('LINHA - 356');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
	    CD_SPREADSHEET_ALL_PAGES,
	    TP_USE,
	    CD_ENABLED,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'NAO-LOCALIZADO',
	    'NAO-LOCALIZADO',
	    VAR_NM_CONTRATO_NAO_LOCALIZADO,
	    VAR_FALSE,
	    VAR_USE_TYPE_NAO_LOCALIZADO,
	    VAR_TRUE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);	
	
	call PROC_LOG_MESSAGE('LINHA - 380');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/		
	/* VIEW-DESTINATION */

	call PROC_LOG_MESSAGE('LINHA - 385');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_COPARTICIPACAO_SPREAD_SAUDE',
		'SPREAD',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;	
	set VAR_CD_ORDEM = 0;
		
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		'CD_CONTRATO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_SUBFATURA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_SUBFATURA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'NR_CERTIFICADO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_CERTIFICADO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CERTIFICADO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 433');
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
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'VL_FATOR_MODERADOR',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_FATOR_MODERADOR,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_FATOR_MODERADOR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 537');
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
		'NR_MATRICULA_ESPECIAL',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA_ESPECIAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA_ESPECIAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 563');
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
		'DESCR_PROFISSAO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_DESCR_PROFISSAO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_DESCR_PROFISSAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 797');
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 230');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_COPARTICIPACAO_RESUMO_SPREAD_SAUDE',
		'SPREAD',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 225');
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
		'CD_CONTRATO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_CD_CONTRATO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_CONTRATO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 225');
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
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_CD_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'NUM_SEGURADOS',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_NUM_SEGURADOS,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NUM_SEGURADOS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'VL_PROPORCAO',
		VAR_COL_INT,
		VAR_COL_VIEW_LENGTH_VL_PROPORCAO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_PROPORCAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 433');
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
		'VL_ALOCACAO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_VL_ALOCACAO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_ALOCACAO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
		
	call PROC_LOG_MESSAGE('LINHA - 797');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	/* NAO-LOCALIZADOS */
	
	call PROC_LOG_MESSAGE('LINHA - 801');
	insert into TB_VIEW_DESTINATION(
		NM_VIEW,
		NM_TITLE_LABEL,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		'VW_DESCONHECIDO_SPREAD_SAUDE',
		'Não Localizados',
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	
	
	select max( ID ) into VAR_ID_VIEW_DESTINATION from TB_VIEW_DESTINATION;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 735');
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
		'NR_SUBFATURA',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_SUBFATURA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_SUBFATURA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 761');
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
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 761');
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
		VAR_COL_VIEW_LENGTH_NM_BENEFICIARIO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NM_BENEFICIARIO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 787');
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
		VAR_COL_VIEW_LENGTH_NR_CPF_TITULAR,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CPF_TITULAR,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 735');
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_CERTIFICADO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);				
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 865');
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
		'CD_PLANO',
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_CD_PLANO,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_CD_PLANO,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 865');
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
		VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA_EMPRESA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
			
	call PROC_LOG_MESSAGE('LINHA - 950');
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 1003');
	
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 1, 'Spread Teleinformática', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 3, 'Spread Teleinformática', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 8, 'Spread Teleinformática (Petrobrás)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 17, 'Teleinformática', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 30, 'Spread Teleinformática (CEG)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 62, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 64, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 65, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 110, 'Spread Tecnologia em Sistemas', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 111, 'Spread Tecnologia em Sistemas', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 113, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 124, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 128, 'Spread Tecnologia em Sistemas', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 146, 'Spread Sistemas e Automação', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 149, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 150, 'Sistemas', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 162, 'Sistemas', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 180, 'Spread Sistemas e Automação (Petrobrás)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 186, 'NL', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 193, 'Spread Comercio de Equipamentos', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 202, 'Spread Sistemas de Automação Ltda.', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 300, 'Spread Tecnologia em Sistemas (Diretoria)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 851, 'Extensão Inativos (Demitidos)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	insert into TB_SUBFATURA( ID_EMPRESA, NR_SUBFATURA, NM_SUBFATURA, USER_CREATED, DT_CREATED, DT_ALTERED ) values ( VAR_ID_EMPRESA, 852, 'Extensão Inativos (Aposentados)', VAR_ID_USER,    current_timestamp(),	current_timestamp());
	
	call PROC_LOG_MESSAGE('LINHA - 950');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
