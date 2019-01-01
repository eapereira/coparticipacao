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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20181128-012-dml-COELBA-SAUDE-NAO-LOCALIZADO.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20181128-014-dml-TECHNIT-SAUDE.sql';
	
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

    declare VAR_COL_LABEL_NR_SUBFATURA									varchar( 40 ) default 'SUBFATURA';
	declare VAR_COL_LABEL_NR_CERTIFICADO								varchar( 40 ) default 'CERTIFICADO';
	declare VAR_COL_LABEL_NR_MATRICULA									varchar( 40 ) default 'MATRICULA';
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
		CD_OUTPUT_REPORT_DIR,		
        CD_INPUT_DIR,
        CD_FAILURE_DIR,
        CD_OUTPUT_DIR,
        CD_CREATE_BENEFICIARIO_FRON_MECSAS2,
        CD_GENERATE_OUTPUT_FILE_NOFATUCOPA,
        CD_ACCEPT_TITULAR_WITHOUT_CPF,
        TP_SAVE_MECSAS_DETAIL,
		TP_SAVE_BENEFICIARIO_DETAIL,	
		CD_USE_JASPER_REPORTS,			
		TP_REPORT_QUERY,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_OPERADORA,
		'TECHINT-SAUDE',
		'180831',
		VAR_FALSE,
		'/coparticipacao/output-reports/bradesco/techint-saude/',
        '/coparticipacao/input/',
        '/coparticipacao/failure/',
		'/coparticipacao/output/',
		VAR_TRUE,
		VAR_FALSE,
		VAR_TRUE,
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
        CD_DISPLAY_OUTPUT_RESULT,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'180831',
	    '180831',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    VAR_FALSE,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_CONTRATO
	from TB_CONTRATO;

	call PROC_LOG_MESSAGE('LINHA - 235');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
        TP_USE,
        ID_CONTRATO_PARENT,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'SEDE',
	    'SEDE',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    VAR_ID_CONTRATO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 259');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
        TP_USE,
        ID_CONTRATO_PARENT,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'TENOVA',
	    'TENOVA',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    VAR_ID_CONTRATO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);

	call PROC_LOG_MESSAGE('LINHA - 283');
	insert into TB_CONTRATO(
		ID_EMPRESA,
		CD_CONTRATO,	
	    NM_CONTRATO,
	    DESCR_CONTRATO,
        TP_USE,
        ID_CONTRATO_PARENT,
	    
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
	    VAR_ID_EMPRESA,
		'TERNIUM',
	    'TERNIUM',
	    VAR_NM_CONTRATO_COPARTICIPACAO,
	    VAR_USE_TYPE_FATUCOPA,
	    VAR_ID_CONTRATO,
	    
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
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
	    VAR_FALSE,
	    
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
		'VW_COPARTICIPACAO_TECHNIT_SAUDE',
		'TECHNIT',
		
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
		'VW_COPARTICIPACAO_RESUMO_TECHNIT_SAUDE',
		'TECHNIT',
		
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
		'VW_DESCONHECIDO_TECHNIT_SAUDE',
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

	call PROC_LOG_MESSAGE('LINHA - 813');
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

	call PROC_LOG_MESSAGE('LINHA - 839');
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
		'NR_MATRICULA_ESPECIAL',
		VAR_COL_LONG,
		VAR_COL_VIEW_LENGTH_NR_MATRICULA_ESPECIAL,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_MATRICULA_ESPECIAL,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 891');
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
		'VL_FATOR_MODERADOR_INSS',
		VAR_COL_DOUBLE,
		VAR_COL_VIEW_LENGTH_VL_FATOR_MODERADOR_INSS,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_VL_FATOR_MODERADOR_INSS,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	call PROC_LOG_MESSAGE('LINHA - 918');
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
		VAR_COL_VARCHAR,
		VAR_COL_VIEW_LENGTH_NR_SUBFATURA,
		VAR_CD_ORDEM,
		VAR_COL_LABEL_NR_SUBFATURA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);					

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	call PROC_LOG_MESSAGE('LINHA - 918');
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
