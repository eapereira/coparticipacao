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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180726-006-dml-marjan-ISENTOS.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180730-007-dml-marjan-MECSAS.sql';
	
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
	
	declare VAR_ID_ARQUIVO_INPUT 			bigint( 17 );
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
	
	declare VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO				bigint( 17 ) default 1;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO				bigint( 17 ) default 3;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO					bigint( 17 ) default 4;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF							bigint( 17 ) default 5;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO					bigint( 17 ) default 6;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_NM_LABEL						bigint( 17 ) default 7;
	declare VAR_CD_BENEFICIARIO_COLS_DEF_REF_CODE						bigint( 17 ) default 8;

	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA					bigint( 17 ) default 2;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_DEPENDENTE					bigint( 17 ) default 4;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NM_TITULAR						bigint( 17 ) default 3;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_CPF_DEPENDENTE				bigint( 17 ) default 8;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_NR_MATRICULA_TITULAR			bigint( 17 ) default 1;
	declare VAR_CD_DESCONHECIDO_COLS_DEF_VL_PRINCIPAL					bigint( 17 ) default 9;

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
    where NM_EMPRESA = 'Marjan';
        
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
		'^(MARJAN)\\.(MECSAS)\\.([0-9]{4})([0-9]{2})\\.([0-9]{3})\\.(CSV|csv)$',
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
		VAR_ID_ARQUIVO_INPUT,
		'NUM_LINHA',
		VAR_COL_INT,
		null,
		1,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NUM_LINHA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1401');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COD_EMP',
		VAR_COL_VARCHAR,
		null,
		2,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COD_EMP from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1423');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'MATRICULA',
		VAR_COL_LONG,
		null,
		3,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_MATRICULA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1445');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DF',
		VAR_COL_INT,
		null,
		4,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'RDP',
		VAR_COL_INT,
		null,
		5,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_RDP from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'LOCAL',
		VAR_COL_INT,
		null,
		6,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_LOCAL from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CATEGORIA',
		VAR_COL_INT,
		null,
		7,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CATEGORIA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'SETOR',
		VAR_COL_VARCHAR,
		null,
		8,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_SETOR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'ES',
		VAR_COL_VARCHAR,
		null,
		9,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_ES from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1607');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'PLANO',
		VAR_COL_VARCHAR,
		null,
		10,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_PLANO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATA_ADESAO',
		VAR_COL_DATE,
		null,
		11,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATA_ADESAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CPF',
		VAR_COL_LONG,
		null,
		12,
		'#0',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CPF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NOME_BENEF',
		VAR_COL_VARCHAR,
		null,
		13,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NOME_BENEF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATA_NASC',
		VAR_COL_DATE,
		null,
		14,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATA_NASC from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1725');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'SEXO',
		VAR_COL_VARCHAR,
		null,
		15,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_SEXO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'PERMANENCIA',
		VAR_COL_VARCHAR,
		null,
		16,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_PERMANENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'GP',
		VAR_COL_INT,
		null,
		17,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_GP from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATA_ADM',
		4, /* VARCHAR */
		null,
		18,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATA_ADM from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATA_REF',
		VAR_COL_DATE,
		null,
		19,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATA_REF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1839');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'BANCO',
		VAR_COL_INT,
		null,
		20,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_BANCO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'AGENCIA',
		VAR_COL_INT,
		null,
		21,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_AGENDA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DG_AGEN',
		VAR_COL_INT,
		null,
		22,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DG_AGEN from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CONTA_CORRENTE',
		VAR_COL_VARCHAR,
		null,
		23,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CONTA_CORRENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CPF_CONTA_CORRENTE',
		VAR_COL_LONG,
		null,
		24,
		'#0',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CPF_CONTA_CORRENTE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 1949');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NOME_TITULAR_CC',
		VAR_COL_VARCHAR,
		null,
		25,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NOME_TITULAR_CC from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODCARDIF',
		VAR_COL_VARCHAR,
		null,
		26,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CODCARDIF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NUM_CEP',
		1, /* VARCHAR */
		null,
		27,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NUM_CEP from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'TIPO_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		28,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_TIPO_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		29,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NUMERO',
		VAR_COL_INT,
		null,
		30,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NUMERO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COMP_LOGRADOURO',
		VAR_COL_VARCHAR,
		null,
		31,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COMP_LOGRADOURO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'BAIRRO',
		VAR_COL_VARCHAR,
		null,
		32,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_BAIRRO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 2118');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'MUNICIPIO',
		VAR_COL_VARCHAR,
		null,
		33,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_MUNICIPIO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'UF',
		VAR_COL_VARCHAR,
		null,
		34,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_UF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'TEL_RESID',
		VAR_COL_VARCHAR,
		null,
		35,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_TEL_RESID from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'TEL_COM',
		VAR_COL_VARCHAR,
		null,
		36,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_TEL_COM from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'TEL_CEL',
		VAR_COL_VARCHAR,
		null,
		37,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_TEL_CEL from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NOMEDAMAEBENE',
		VAR_COL_VARCHAR,
		null,
		38,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NOMEDAMAEBENE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'RG',
		VAR_COL_VARCHAR,
		null,
		39,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_RG from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'ORGAOEMISSORG',
		VAR_COL_VARCHAR,
		null,
		40,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_ORGAOEMISSORG from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'PAISEMISSORRG',
		VAR_COL_VARCHAR,
		null,
		41,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_PAISEMISSORRG from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 2389');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATAEMISSAORG',
		VAR_COL_DATE,
		null,
		42,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATAEMISSAORG from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'ESTADORG',
		VAR_COL_VARCHAR,
		null,
		43,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_ESTADORG from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'PIS',
		VAR_COL_VARCHAR,
		null,
		44,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_PIS from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CNS',
		VAR_COL_VARCHAR,
		null,
		45,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CNS from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'E-MAIL',
		VAR_COL_VARCHAR,
		null,
		46,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_EMAIL from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'GRAUESCOLARIDADE',
		VAR_COL_INT,
		null,
		47,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_GRAUESCOLARIDADE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'RENDAFAMILIAR',
		VAR_COL_INT,
		null,
		48,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_RENDAFAMILIAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CDPROFISSAO',
		VAR_COL_INT,
		null,
		49,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CDPROFISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CDPAISDEORIGEM',
		VAR_COL_INT,
		null,
		50,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CDPAISDEORIGEM from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DATAEXCLUSAO',
		VAR_COL_DATE,
		null,
		51,
		'yyyyMMdd',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DATAEXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 2530');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODMOVEXCLUSAO',
		VAR_COL_INT,
		null,
		52,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CODMOVEXCLUSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODOPERACAO',
		VAR_COL_INT,
		null,
		53,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CODOPERACAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODEMPRESATRANSF',
		VAR_COL_INT,
		null,
		54,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CODEMPRESATRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'MATRICULATRANSF',
		VAR_COL_INT,
		null,
		55,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_MATRICULATRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'LOCALTRANSF',
		VAR_COL_VARCHAR,
		null,
		56,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_LOCALTRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CATTRANSF',
		VAR_COL_INT,
		null,
		57,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CATTRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'PLANOTRANSF',
		VAR_COL_INT,
		null,
		58,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_PLANOTRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'MOTREMISSAO',
		VAR_COL_VARCHAR,
		null,
		59,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_MOTREMISSAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		CD_FORMAT,
		
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CPFNOVOTITULAR',
		VAR_COL_LONG,
		null,
		60,
		'#0',
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CPFNOVOTITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'QTDPERMAMESES',
		VAR_COL_INT,
		null,
		61,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_QTDPERMAMESES from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'RDPNOVOTITULAR',
		VAR_COL_VARCHAR,
		null,
		62,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_RDPNOVOTITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 2768');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DTINICIOTRANSF',
		VAR_COL_DATE,
		null,
		63,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DTINICIOTRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COD STATUS',
		VAR_COL_INT,
		null,
		64,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COD_STATUS from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COD ERRO',
		VAR_COL_INT,
		null,
		65,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COD_ERRO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COD DV',
		VAR_COL_INT,
		null,
		66,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COD_DV from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'BLOQ EMPR INADIMPLENCIA',
		VAR_COL_VARCHAR,
		null,
		67,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_BLOQ_EMPR_INADIMPLENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CPT',
		VAR_COL_VARCHAR,
		null,
		68,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CPT from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'COD EMPRESA TITULAR',
		VAR_COL_VARCHAR,
		null,
		69,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_COD_EMPRESA_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'MATRICULA 02',
		VAR_COL_LONG,
		null,
		70,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_MATRICULA_02 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DIFERENCIADOR DA MATRICULA DO TITULAR',
		VAR_COL_VARCHAR,
		null,
		71,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_DIFERENCIADOR_MATRICULA_TITULAR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NUMERO DO TITULO DE ELEITOR',
		VAR_COL_VARCHAR,
		null,
		72,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NR_TITULO_ELEITOR from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 2979');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NUMERO DO RIC',
		VAR_COL_VARCHAR,
		null,
		73,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NR_RIC from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NUMERO DA DECLARACAO DE NASCIDO VIVO',
		VAR_COL_VARCHAR,
		null,
		74,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NR_DECL_NASCIDO_VIVO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CARTEIRA DE IDENTIFICACAO',
		VAR_COL_VARCHAR,
		null,
		75,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CARTEIRA_IDENTIFICACAO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE SEGURADO CONTRIBUTARIO',
		VAR_COL_VARCHAR,
		null,
		76,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_SEGURADO_CONTRIBUTARIO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DOA CONDICAO DO EX-EMPREGADO',
		VAR_COL_VARCHAR,
		null,
		77,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_CONDICAO_EX_EMPREGADO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE PERMANENCIA NO PLANO',
		VAR_COL_VARCHAR,
		null,
		78,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_PERM_PLANO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'QUANTIDADE DE MESES DE CONTRIBUICAO',
		VAR_COL_VARCHAR,
		null,
		79,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_QTDE_MESES_CONTRIB from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'NOME COMPLETO DO BENEFICIARIO',
		VAR_COL_VARCHAR,
		null,
		80,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_NM_COMPLETO_BENEF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE TITULAR REMIDO',
		VAR_COL_VARCHAR,
		null,
		81,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_TITULAR_REMIDO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'EMAIL DA SEGURADORA',
		VAR_COL_VARCHAR,
		null,
		82,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_EMAIL_SEGURADORA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE PORTABILIDADE 1',
		VAR_COL_VARCHAR,
		null,
		83,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_PORTABILIDADE_01 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE PORTABILIDADE 2',
		VAR_COL_VARCHAR,
		null,
		84,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_PORTABILIDADE_2 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 3232');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'INDICADOR DE CARENCIA',
		VAR_COL_VARCHAR,
		null,
		85,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_INDICADOR_CARENCIA from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODIGO DO PRODUTO',
		VAR_COL_INT,
		null,
		86,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CD_PRODUTO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CODIGO DE IDENTIFICACAO DE PLANO ANTERIOR NA SAS',
		VAR_COL_VARCHAR,
		null,
		87,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CD_IDENT_PLANO_ANTERIOR_SAS from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID1',
		VAR_COL_VARCHAR,
		null,
		88,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI01 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID2',
		VAR_COL_VARCHAR,
		null,
		89,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI02 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID3',
		VAR_COL_VARCHAR,
		null,
		90,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI03 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID4',
		VAR_COL_VARCHAR,
		null,
		91,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI04 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID5',
		VAR_COL_VARCHAR,
		null,
		92,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI05 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID6',
		VAR_COL_VARCHAR,
		null,
		93,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI06 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID7',
		VAR_COL_VARCHAR,
		null,
		94,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI07 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	call PROC_LOG_MESSAGE('LINHA - 3443');
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID8',
		VAR_COL_VARCHAR,
		null,
		95,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI08 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID9',
		VAR_COL_VARCHAR,
		null,
		96,
		
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI09 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CID10',
		VAR_COL_VARCHAR,
		null,
		97,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CI010 from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'IBGE',
		VAR_COL_VARCHAR,
		null,
		98,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_IBGE from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'CBO',
		VAR_COL_VARCHAR,
		null,
		99,
		1,
		current_timestamp(),
		current_timestamp()
	);
	
	select max( ID ) into VAR_MECSAS_CBO from TB_ARQUIVO_INPUT_COLS_DEF;
	
	insert into TB_ARQUIVO_INPUT_COLS_DEF(
		ID_ARQUIVO_INPUT,
		NM_COLUMN,
		CD_TYPE,
		VL_LENGTH,
		CD_ORDEM,
		USER_CREATED, 
		DT_CREATED,
		DT_ALTERED ) values (	
		VAR_ID_ARQUIVO_INPUT,
		'DIF TRANSFERENCIA',
		VAR_COL_VARCHAR,
		null,
		100,
		1,
		current_timestamp(),
		current_timestamp()
	);	
	
	select max( ID ) into VAR_MECSAS_DIF_TRANSF from TB_ARQUIVO_INPUT_COLS_DEF;
	
	/*****************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************/
	/* Beneficiário */
				
	/* MECSAS */
	call PROC_LOG_MESSAGE('LINHA - 4867');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_MATRICULA,
		VAR_MECSAS_MATRICULA,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NM_BENEFICIARIO,
		VAR_MECSAS_NOME_BENEF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_NR_CPF,
		VAR_MECSAS_CPF,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
	
	call PROC_LOG_MESSAGE('LINHA - 4913');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_NASCIMENTO,
		VAR_MECSAS_DATA_NASC,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);
			
	call PROC_LOG_MESSAGE('LINHA - 4929');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_TP_BENEFICIARIO,
		VAR_MECSAS_GP,
		
		VAR_ID_USER,
		current_timestamp(),
		current_timestamp()		
	);	

	call PROC_LOG_MESSAGE('LINHA - 2521');
	insert into TB_BENEFICIARIO_COLS(
		CD_BENEFICIARIO_COLS_DEF,
		ID_ARQUIVO_INPUT_COLS_DEF,
	
		USER_CREATED,
		DT_CREATED,
		DT_ALTERED ) values (
		VAR_CD_BENEFICIARIO_COLS_DEF_DT_ADMISSAO,
		VAR_MECSAS_DATA_ADESAO,
		
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
