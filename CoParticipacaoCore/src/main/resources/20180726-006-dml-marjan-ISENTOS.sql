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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180726-005-dml-marjan-8C7XY.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180726-006-dml-marjan-ISENTOS.sql';
	
	DECLARE VAR_CODE CHAR(5) DEFAULT '00000';
  	DECLARE VAR_MSG TEXT;
								
	declare VAR_FALSE						int( 3 ) default 0;			
	declare VAR_TRUE						int( 3 ) default 1;
	declare VAR_USE_TYPE_FATUCOPA			int( 3 ) default 1;
	declare VAR_USE_TYPE_MECSAS				int( 3 ) default 2;
	declare VAR_USE_TYPE_ISENTO				int( 3 ) default 3;
	
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
	
	declare VAR_ID_ARQUIVO_INPUT_MECSAS 			bigint( 17 );
	declare VAR_ID_ARQUIVO_INPUT_ISENTOS			bigint( 17 );
	declare VAR_ARQUIVO_INPUT_LAYOUT				bigint( 17 );
			
	declare VAR_MECSAS_NUM_LINHA				bigint( 17 );
	declare VAR_MECSAS_COD_EMP					bigint( 17 );
	declare VAR_MECSAS_MATRICULA				bigint( 17 );
	declare VAR_MECSAS_DF						bigint( 17 );
	declare VAR_MECSAS_RDP						bigint( 17 );
	declare VAR_MECSAS_LOCAL					bigint( 17 );
	declare VAR_MECSAS_CATEGORIA				bigint( 17 );
	declare VAR_MECSAS_SETOR					bigint( 17 );
	declare VAR_MECSAS_ES						bigint( 17 );
	declare VAR_MECSAS_PLANO					bigint( 17 );
	declare VAR_MECSAS_DATA_ADESAO				bigint( 17 );
	declare VAR_MECSAS_CPF						bigint( 17 );
	declare VAR_MECSAS_NOME_BENEF				bigint( 17 );
	declare VAR_MECSAS_DATA_NASC				bigint( 17 );
	declare VAR_MECSAS_SEXO						bigint( 17 );
	declare VAR_MECSAS_PERMANENCIA				bigint( 17 );
	declare VAR_MECSAS_GP						bigint( 17 );
	declare VAR_MECSAS_DATA_ADM					bigint( 17 );
	declare VAR_MECSAS_DATA_REF					bigint( 17 );
	declare VAR_MECSAS_BANCO					bigint( 17 );
	declare VAR_MECSAS_AGENDA					bigint( 17 );
	declare VAR_MECSAS_DG_AGEN					bigint( 17 );
	declare VAR_MECSAS_CONTA_CORRENTE			bigint( 17 );
	declare VAR_MECSAS_CPF_CONTA_CORRENTE		bigint( 17 );
	declare VAR_MECSAS_NOME_TITULAR_CC			bigint( 17 );
	declare VAR_MECSAS_CODCARDIF				bigint( 17 );
	declare VAR_MECSAS_NUM_CEP					bigint( 17 );
	declare VAR_MECSAS_TIPO_LOGRADOURO			bigint( 17 );
	declare VAR_MECSAS_LOGRADOURO				bigint( 17 );
	declare VAR_MECSAS_NUMERO					bigint( 17 );
	declare VAR_MECSAS_COMP_LOGRADOURO			bigint( 17 );
	declare VAR_MECSAS_BAIRRO					bigint( 17 );
	declare VAR_MECSAS_MUNICIPIO				bigint( 17 );
	declare VAR_MECSAS_UF						bigint( 17 );
	declare VAR_MECSAS_TEL_RESID				bigint( 17 );
	declare VAR_MECSAS_TEL_COM					bigint( 17 );
	declare VAR_MECSAS_TEL_CEL					bigint( 17 );
	declare VAR_MECSAS_NOMEDAMAEBENE			bigint( 17 );
	declare VAR_MECSAS_RG						bigint( 17 );
	declare VAR_MECSAS_ORGAOEMISSORG			bigint( 17 );
	declare VAR_MECSAS_PAISEMISSORRG			bigint( 17 );
	declare VAR_MECSAS_DATAEMISSAORG			bigint( 17 );
	declare VAR_MECSAS_ESTADORG					bigint( 17 );
	declare VAR_MECSAS_PIS						bigint( 17 );
	declare VAR_MECSAS_CNS						bigint( 17 );
	declare VAR_MECSAS_EMAIL					bigint( 17 );
	declare VAR_MECSAS_GRAUESCOLARIDADE			bigint( 17 );
	declare VAR_MECSAS_RENDAFAMILIAR			bigint( 17 );
	declare VAR_MECSAS_CDPROFISSAO				bigint( 17 );
	declare VAR_MECSAS_CDPAISDEORIGEM				bigint( 17 );
	declare VAR_MECSAS_DATAEXCLUSAO				bigint( 17 );
	declare VAR_MECSAS_CODMOVEXCLUSAO				bigint( 17 );
	declare VAR_MECSAS_CODOPERACAO				bigint( 17 );
	declare VAR_MECSAS_CODEMPRESATRANSF				bigint( 17 );
	declare VAR_MECSAS_MATRICULATRANSF				bigint( 17 );
	declare VAR_MECSAS_LOCALTRANSF				bigint( 17 );
	declare VAR_MECSAS_CATTRANSF				bigint( 17 );
	declare VAR_MECSAS_PLANOTRANSF				bigint( 17 );
	declare VAR_MECSAS_MOTREMISSAO				bigint( 17 );
	declare VAR_MECSAS_CPFNOVOTITULAR				bigint( 17 );
	declare VAR_MECSAS_QTDPERMAMESES				bigint( 17 );
	declare VAR_MECSAS_RDPNOVOTITULAR				bigint( 17 );
	declare VAR_MECSAS_DTINICIOTRANSF				bigint( 17 );
	declare VAR_MECSAS_COD_STATUS					bigint( 17 );
	declare VAR_MECSAS_COD_ERRO						bigint( 17 );
	declare VAR_MECSAS_COD_DV								bigint( 17 );
	declare VAR_MECSAS_BLOQ_EMPR_INADIMPLENCIA				bigint( 17 );
	declare VAR_MECSAS_CPT								bigint( 17 );
	declare VAR_MECSAS_COD_EMPRESA_TITULAR				bigint( 17 );
	declare VAR_MECSAS_MATRICULA_02						bigint( 17 );
	declare VAR_MECSAS_DIFERENCIADOR_MATRICULA_TITULAR				bigint( 17 );
	declare VAR_MECSAS_NR_TITULO_ELEITOR				bigint( 17 );
	declare VAR_MECSAS_NR_RIC							bigint( 17 );
	declare VAR_MECSAS_NR_DECL_NASCIDO_VIVO				bigint( 17 );
	declare VAR_MECSAS_CARTEIRA_IDENTIFICACAO				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_SEGURADO_CONTRIBUTARIO				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_CONDICAO_EX_EMPREGADO				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_PERM_PLANO				bigint( 17 );
	declare VAR_MECSAS_QTDE_MESES_CONTRIB				bigint( 17 );
	declare VAR_MECSAS_NM_COMPLETO_BENEF				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_TITULAR_REMIDO				bigint( 17 );
	declare VAR_MECSAS_EMAIL_SEGURADORA				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_PORTABILIDADE_01				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_PORTABILIDADE_2				bigint( 17 );
	declare VAR_MECSAS_INDICADOR_CARENCIA				bigint( 17 );
	declare VAR_MECSAS_CD_PRODUTO				bigint( 17 );
	declare VAR_MECSAS_CD_IDENT_PLANO_ANTERIOR_SAS				bigint( 17 );
	declare VAR_MECSAS_CI01				bigint( 17 );
	declare VAR_MECSAS_CI02				bigint( 17 );
	declare VAR_MECSAS_CI03				bigint( 17 );
	declare VAR_MECSAS_CI04				bigint( 17 );
	declare VAR_MECSAS_CI05				bigint( 17 );
	declare VAR_MECSAS_CI06				bigint( 17 );
	declare VAR_MECSAS_CI07				bigint( 17 );
	declare VAR_MECSAS_CI08				bigint( 17 );
	declare VAR_MECSAS_CI09				bigint( 17 );
	declare VAR_MECSAS_CI010				bigint( 17 );
	declare VAR_MECSAS_IBGE				bigint( 17 );
	declare VAR_MECSAS_CBO				bigint( 17 );
	declare VAR_MECSAS_DIF_TRANSF				bigint( 17 );
	
	declare VAR_ISENTOS_NM_CONTRATO				bigint( 17 );
	declare VAR_ISENTOS_NR_MATRICULA			bigint( 17 );
	declare VAR_ISENTOS_NR_MATRICULA_EMPRESA	bigint( 17 );
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
		
	declare VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO							bigint( 17 ) default 0; /* fake */
	
	declare VAR_CD_ISENTO_COLS_DEF_TP_ISENTO							bigint( 17 ) default 1;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA							bigint( 17 ) default 2;
	declare VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO						bigint( 17 ) default 3;
	declare VAR_CD_ISENTO_COLS_DEF_DT_NASCIMENTO						bigint( 17 ) default 4;
	declare VAR_CD_ISENTO_COLS_DEF_NR_CPF								bigint( 17 ) default 5;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_TITULAR					bigint( 17 ) default 6;
	declare VAR_CD_ISENTO_COLS_DEF_NM_TITULAR							bigint( 17 ) default 7;
	declare VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_EMPRESA					bigint( 17 ) default 9;

	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;
	
	declare VAR_CD_ORDEM												int( 3 ) default 0;

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
	
	#call PROC_VALIDATE_SCRIPT( VAR_NM_SCRIPT_REQUIRED, VAR_NM_SCRIPT );
	/***********************************************************************************************************************/		
    call PROC_LOG_MESSAGE('LINHA - 236');
	select ID into VAR_ID_EMPRESA from TB_EMPRESA
    where NM_EMPRESA = 'Marjan';
        
    call PROC_LOG_MESSAGE('LINHA - 123');
	select ID into VAR_ID_CONTRATO from TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO = 'ISENTO'; 
	
	/***********************************************************************************************************************/
	
	call PROC_LOG_MESSAGE('LINHA - 246');
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
		'^(MARJAN)\\.(ISENTO)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(xlsx|XLSX)$',
		'Arquivo de carga de isentos',
		VAR_ARQUIVO_TYPE_SPREADSHEET,
		VAR_USE_TYPE_ISENTO,
		1,
		null, /* Não é usado para arquivo CSV */
				
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ID_ARQUIVO_INPUT_ISENTOS from TB_ARQUIVO_INPUT;
	
	call PROC_LOG_MESSAGE('LINHA - 1379');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NM_CONTRATO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NM_CONTRATO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NR_MATRICULA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NR_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NM_BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NM_BENEFICIARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NR_CPF',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NR_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NR_MATRICULA_TITULAR',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NR_MATRICULA_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NM_TITULAR',
		VAR_COL_VARCHAR,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NM_TITULAR 
	from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;

	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		'NR_MATRICULA_EMPRESA',
		VAR_COL_LONG,
		null,
		VAR_CD_ORDEM,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_ISENTOS_NR_MATRICULA_EMPRESA 
	from TB_ARQUIVO_INPUT_COLS_DEF;
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	/*****************************************************************************************************************************************************/	
	/*****************************************************************************************************************************************************/
	/* BENEFICIARIO ISENTO */
	
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 4948');
	/* MECSAS */
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		1, /* GESTANTE */
		0,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;

	call PROC_LOG_MESSAGE('LINHA - 2883');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_CONTRATO,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 4967');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 4987');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*****************************************************************************************************************************************************/
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		1, /* GESTANTE.2 */
		1,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 2982');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_CONTRATO,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3002');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5042');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
		
	/*****************************************************************************************************************************************************/
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 2955');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		2, /* FILHOS MENORES DE 12 MESES */
		2,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;

	call PROC_LOG_MESSAGE('LINHA - 3041');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_CONTRATO,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3062');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA_TITULAR,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3082');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_TITULAR,
		VAR_CD_ISENTO_COLS_DEF_NM_TITULAR,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
		
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA_EMPRESA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA_EMPRESA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*****************************************************************************************************************************************************/
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 2955');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		3, /* ESTAGIÁRIO */
		3,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 3179');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_CONTRATO,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3199');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3218');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	/*****************************************************************************************************************************************************/
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5340');
	insert into TB_ISENTO_INPUT_SHEET(
		ID_ARQUIVO_INPUT,
		TP_ISENTO,
		CD_SHEET,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ARQUIVO_INPUT_ISENTOS,
		4, /* DIRETORIA */
		4,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()						
	);
	
	select max( ID ) into VAR_ID_ISENTO_INPUT_SHEET from TB_ISENTO_INPUT_SHEET;
	set VAR_CD_ORDEM = 0;
	
	call PROC_LOG_MESSAGE('LINHA - 3278');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_CONTRATO,
		VAR_CD_ISENTO_COLS_DEF_NM_CONTRATO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 3298');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_MATRICULA,
		VAR_CD_ISENTO_COLS_DEF_NR_MATRICULA,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5377');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NM_BENEFICIARIO,
		VAR_CD_ISENTO_COLS_DEF_NM_BENEFICIARIO,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);

	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 5396');
	insert into TB_ISENTO_INPUT_SHEET_COLS(
		ID_ISENTO_INPUT_SHEET,
		ID_ARQUIVO_INPUT_COLS_DEF,
		CD_BENEFICIARIO_COLS_DEF,
		CD_ORDEM,
		
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_ID_ISENTO_INPUT_SHEET,
		VAR_ISENTOS_NR_CPF,
		VAR_CD_ISENTO_COLS_DEF_NR_CPF,
		VAR_CD_ORDEM,

		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()				
	);
	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	call PROC_LOG_MESSAGE('LINHA - 976');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
		
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
