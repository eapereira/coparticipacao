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
	declare VAR_NM_SCRIPT_REQUIRED			varchar( 400 ) default '20180927-002-dml.sql';
	declare VAR_NM_SCRIPT					varchar( 400 ) default '20180928-002-dml-CARGILL.sql';
	
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
	
	declare VAR_ID_OPERADORA_SULAMERICA				bigint( 17 ) default 1;
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
	
	declare VAR_ID_COLUMN_01_NUM_LINHA					bigint( 17 );
	declare VAR_ID_COLUMN_02_CD_REG						bigint( 17 );
	declare VAR_ID_COLUMN_03_CD_PREF					bigint( 17 );
	declare VAR_ID_COLUMN_04_NR_MATRICULA_TITULAR		bigint( 17 );
	declare VAR_ID_COLUMN_05_NR_MATRICULA_DEPENDENTE	bigint( 17 );
	declare VAR_ID_COLUMN_06_NM_DEPENDENTE				bigint( 17 );
	declare VAR_ID_COLUMN_07_VL_PRINCIPAL				bigint( 17 );
	declare VAR_ID_COLUMN_08_CD_SETOR					bigint( 17 );
	declare VAR_ID_COLUMN_09_NR_LOCAL					bigint( 17 );
	declare VAR_ID_COLUMN_10_DT_NASCIMENTO				bigint( 17 );
	declare VAR_ID_COLUMN_11_EMPTY						bigint( 17 );
	declare VAR_ID_COLUMN_12_NR_CPF						bigint( 17 );
	declare VAR_ID_COLUMN_13_CD_EMPRESA					bigint( 17 );
	declare VAR_ID_COLUMN_14_DT_MOVTO					bigint( 17 );
    
    declare VAR_COL_VIEW_LENGTH_CD_EMPRESA								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_MATRICULA_EMPRESA					int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_CD_PLANO								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NR_CPF									int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_NM_TITULAR								int( 3 ) default 40;
	declare VAR_COL_VIEW_LENGTH_VL_PRINCIPAL							int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_DT_ADMISSAO								int( 3 ) default 20;
	declare VAR_COL_VIEW_LENGTH_TP_SINAL								int( 3 ) default 10;
	declare VAR_COL_VIEW_LENGTH_CD_VERBA								int( 3 ) default 20;

    declare VAR_COL_LABEL_CD_EMPRESA									varchar( 40 ) default 'EMPRESA';
	declare VAR_COL_LABEL_NR_MATRICULA_EMPRESA							varchar( 40 ) default 'MATRICULA EMPRESA';
	declare VAR_COL_LABEL_CD_PLANO										varchar( 40 ) default 'PLANO';
	declare VAR_COL_LABEL_NR_CPF										varchar( 40 ) default 'CPF';
	declare VAR_COL_LABEL_NM_TITULAR									varchar( 40 ) default 'NOME TÍTULAR';
	declare VAR_COL_LABEL_VL_PRINCIPAL									varchar( 40 ) default 'TOTAL';
	declare VAR_COL_LABEL_DT_ADMISSAO									varchar( 40 ) default 'DT ADMISSÃO';
	declare VAR_COL_LABEL_TP_SINAL										varchar( 40 ) default 'SINAL';
	declare VAR_COL_LABEL_CD_VERBA										varchar( 40 ) default 'COD VERBA';
	
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
	
	declare VAR_NM_CONTRATO_COPARTICIPACAO								varchar( 400 ) default 'Arquivo de Coparticipação';
	declare VAR_NM_CONTRATO_NAO_LOCALIZADO								varchar( 400 ) default 'Retorno de Não Localizados';
	declare VAR_NM_CONTRATO_MECSAS										varchar( 400 ) default 'Base de Ativos da Operadora';
	declare VAR_NM_CONTRATO_MECSAS2										varchar( 400 ) default 'Base de Ativos do Cliente';
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
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 142');
    select	ID into VAR_ID_EMPRESA
    from 	TB_EMPRESA
    where	CD_EMPRESA = 'CARGILL';
	
	call PROC_LOG_MESSAGE('LINHA - 147');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	/* VIEW-DESTINATION */

	call PROC_LOG_MESSAGE('LINHA - 152');
	select	ID into VAR_ID_VIEW_DESTINATION
	from 	TB_VIEW_DESTINATION
	where 	NM_VIEW = 'VW_PRN_CARGILL';
		
	call PROC_LOG_MESSAGE('LINHA - 157');
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 159');	
	set VAR_CD_ORDEM = 0;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'CD_CONTRATO';

	call PROC_LOG_MESSAGE('LINHA - 167');	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'NR_MATRICULA';

	call PROC_LOG_MESSAGE('LINHA - 183');	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'CD_PLANO';
	
	call PROC_LOG_MESSAGE('LINHA - 175');	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'CD_VERBA';

	call PROC_LOG_MESSAGE('LINHA - 191');	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'VL_DESCONTO';

	call PROC_LOG_MESSAGE('LINHA - 199');	
	set VAR_CD_ORDEM = VAR_CD_ORDEM + 1;
	
	update TB_VIEW_DESTINATION_COLS_DEF
	set		CD_ORDEM = VAR_CD_ORDEM
	where	ID_VIEW_DESTINATION	= VAR_ID_VIEW_DESTINATION
	and		NM_COLUMN			= 'TP_SINAL';
	
	call PROC_LOG_MESSAGE('LINHA - 207');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 225');
	select	ID into VAR_ID_CONTRATO
	from 	TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO	= '00192';
	
	select	ID into VAR_ID_ARQUIVO_INPUT
	from 	TB_ARQUIVO_INPUT
	where	ID_CONTRATO	= VAR_ID_CONTRATO;
	
	select	ID into VAR_ID_COLUMN_14_DT_MOVTO
	from	TB_ARQUIVO_INPUT_COLS_DEF
	where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
	and		NM_COLUMN			= 'COLUMN_14_DT_MOVTO';

	call PROC_LOG_MESSAGE('LINHA - 240');	
	update TB_ARQUIVO_INPUT_COLS_DEF
	set
		CD_FORMAT = 'ddMMyyyy'
	where ID = VAR_ID_COLUMN_14_DT_MOVTO;
	
	call PROC_LOG_MESSAGE('LINHA - 240');
	/*********************************************************************************************************************************************/
	call PROC_LOG_MESSAGE('LINHA - 225');
	select	ID into VAR_ID_CONTRATO
	from 	TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO	= '00196';
	
	select	ID into VAR_ID_ARQUIVO_INPUT
	from 	TB_ARQUIVO_INPUT
	where	ID_CONTRATO	= VAR_ID_CONTRATO;
	
	select	ID into VAR_ID_COLUMN_14_DT_MOVTO
	from	TB_ARQUIVO_INPUT_COLS_DEF
	where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
	and		NM_COLUMN			= 'COLUMN_14_DT_MOVTO';
	
	call PROC_LOG_MESSAGE('LINHA - 240');	
	update TB_ARQUIVO_INPUT_COLS_DEF
	set
		CD_FORMAT = 'ddMMyyyy'
	where ID = VAR_ID_COLUMN_14_DT_MOVTO;
	
	call PROC_LOG_MESSAGE('LINHA - 269');
	/*********************************************************************************************************************************************/	
	call PROC_LOG_MESSAGE('LINHA - 271');
	
	select	ID into VAR_ID_CONTRATO
	from 	TB_CONTRATO
	where 	ID_EMPRESA	= VAR_ID_EMPRESA
	and		CD_CONTRATO	= '00197';
	
	select	ID into VAR_ID_ARQUIVO_INPUT
	from 	TB_ARQUIVO_INPUT
	where	ID_CONTRATO	= VAR_ID_CONTRATO;
	
	select	ID into VAR_ID_COLUMN_14_DT_MOVTO
	from	TB_ARQUIVO_INPUT_COLS_DEF
	where	ID_ARQUIVO_INPUT	= VAR_ID_ARQUIVO_INPUT
	and		NM_COLUMN			= 'COLUMN_14_DT_MOVTO';
	
	call PROC_LOG_MESSAGE('LINHA - 287');	
	update TB_ARQUIVO_INPUT_COLS_DEF
	set
		CD_FORMAT = 'ddMMyyyy'
	where ID = VAR_ID_COLUMN_14_DT_MOVTO;
	
	call PROC_LOG_MESSAGE('LINHA - 293');
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_LOG_MESSAGE('LINHA - 297');	
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/	
	
	call PROC_UPDATE_SCRIPT( VAR_NM_SCRIPT );
	
	COMMIT;
	
	call PROC_LOG_MESSAGE( 'Alterações executadas com sucesso.' );
	call PROC_SHOW_LOG_MESSAGE();
END
$$

call PROC_CREATE_HOC(); 
