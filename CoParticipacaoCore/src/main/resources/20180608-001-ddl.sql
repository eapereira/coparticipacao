/*****************************************************************************************************************************************************/

/**
 * Edson - 08/06/2018
 */

drop table if exists TB_LANCAMENTO_INPUT_SHEET_COLS;
drop table if exists TB_LANCAMENTO_INPUT_SHEET;
 
drop table if exists TB_SUBFATURA;
drop table if exists TB_REPORT;

drop table if exists TB_SCRIPT;
drop table if exists TB_LOG;

drop table if exists TB_BENEFICIARIO_COLS;

drop table if exists TB_ARQUIVO_INPUT_SHEET_COLS_DEF;
drop table if exists TB_ARQUIVO_INPUT_SHEET;

drop table if exists TB_ISENTO_INPUT_SHEET_COLS;
drop table if exists TB_ISENTO_INPUT_SHEET;
drop table if exists TB_BENEFICIARIO_ISENTO_COLS;
drop table if exists TB_BENEFICIARIO_ISENTO;

drop table if exists TB_DEPENDENTE_ISENTO;
drop table if exists TB_TITULAR_ISENTO;
drop table if exists TB_INPUT_DEPENDENTE_ISENTO_COLS;
drop table if exists TB_INPUT_TITULAR_ISENTO_COLS;
drop table if exists TB_INPUT_DEPENDENTE_ISENTO;
drop table if exists TB_INPUT_TITULAR_ISENTO;
drop table if exists TB_DEPENDENTE_ISENTO_COLS_DEF;
drop table if exists TB_TITULAR_ISENTO_COLS_DEF;

drop table if exists TB_LANCAMENTO_INPUT_COLS;
drop table if exists TB_LANCAMENTO_INPUT;

drop table if exists TB_MECSAS_COLS;

drop table if exists TB_INPUT_DEPENDENTE;
drop table if exists TB_INPUT_TITULAR;
drop table if exists TB_INPUT_LANCAMENTO;
drop table if exists TB_DEPENDENTE_COLS_DEF; 
drop table if exists TB_TITULAR_COLS_DEF;
drop table if exists TB_LANCAMENTO_COLS_DEF;

drop table if exists TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO;
drop table if exists TB_ARQUIVO_OUTPUT_DESCONHECIDO_SHEET;
drop table if exists TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
drop table if exists TB_ARQUIVO_OUTPUT_DESCONHECIDO;
drop table if exists TB_DESCONHECIDO_DETAIL;
drop table if exists TB_DESCONHECIDO;

drop table if exists TB_DEPENDENTE_DETAIL;
drop table if exists TB_TITULAR_DETAIL;
drop table if exists TB_LANCAMENTO_DETAIL;
drop table if exists TB_LANCAMENTO;
drop table if exists TB_DEPENDENTE;
drop table if exists TB_TITULAR;

drop table if exists TB_REGRA_CONDITIONAL_RESULT;
drop table if exists TB_REGRA_CONDITIONAL_VALOR;
drop table if exists TB_REGRA_CONDITIONAL_FIELD;
drop table if exists TB_REGRA_CONDITIONAL_OPERATION;
drop table if exists TB_REGRA_CONDITIONAL;

drop table if exists TB_REGRA_RESULT;
drop table if exists TB_REGRA_VALOR;
drop table if exists TB_REGRA_FIELD;
drop table if exists TB_REGRA_OPERATION;
drop table if exists TB_REGRA;

drop table if exists TB_ARQUIVO_EXECUCAO;
drop table if exists TB_EXECUCAO;
drop table if exists TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF;
drop table if exists TB_ARQUIVO_OUTPUT_SHEET;
drop table if exists TB_ARQUIVO_OUTPUT;
drop table if exists TB_VIEW_DESTINATION_COLS_DEF;
drop table if exists TB_VIEW_DESTINATION;
drop table if exists TB_ARQUIVO_INPUT_COLS_DEF;
drop table if exists TB_ARQUIVO_INPUT;

drop table if exists TB_ARQUIVO_INPUT_LAYOUT_COLS_DEF;
drop table if exists TB_ARQUIVO_INPUT_LAYOUT;

drop table if exists TB_CONTRATO;

drop table if exists TB_PARAMETER;

drop table if exists TB_EXTERNAL_PROCESS;

drop table if exists TB_EMPRESA;
drop table if exists TB_OPERADORA;

drop table if exists TB_USER_ROLE;
drop table if exists TB_ROLE;
drop table if exists TB_USER;


drop view if exists VW_TITULAR_RESUMO;
drop view if exists VW_DEPENDENTE_RESUMO;
/*****************************************************************************************************************************************************/

create table TB_USER(
	ID 				bigint( 17 ) auto_increment,
	NM_LOGIN		varchar( 60 ) not null,
	DESCR_NAME		varchar( 60 ) not null,
	CD_PASSWD		varchar( 200 ) not null,
	TP_STATUS		int( 3 ) not null, /* 0 = ativo, 1 = bloqueado */
	

	VERSION			bigint( 17 ) null,
 
	USER_CREATED	bigint( 17 ),
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,
	
	constraint PK_USER primary key( ID ),
	
	constraint UN_USER unique key ( NM_LOGIN )	
);

create table TB_ROLE(
	ID 					bigint( 17 ) auto_increment,
	NM_ROLE				varchar( 200 ) not null,
	
	VERSION				bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_ROLE primary key( ID ),
	
	constraint UN_ROLE unique key( NM_ROLE ),
	
	constraint FK_ROLE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ROLE_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_USER_ROLE(
	ID 					bigint( 17 ) auto_increment,
	ID_USER				bigint( 17 ) not null,
	ID_ROLE				bigint( 17 ) not null,	
	
	VERSION				bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_ROLE primary key( ID ),
	
	constraint UN_USER_ROLE unique key( ID_USER, ID_ROLE ),
	
	constraint FK_USER_ROLE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_USER_ROLE_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_USER_ROLE_03 foreign key( ID_USER ) references TB_USER( ID ),
	constraint FK_USER_ROLE_04 foreign key( ID_ROLE ) references TB_ROLE( ID )
);

create table TB_OPERADORA(
	ID 					bigint( 17 ) auto_increment,
	NM_OPERADORA		varchar( 80 ) not null,
	CD_OPERADORA 		varchar( 20 ) null,
	CD_ENABLED			int( 1 ) not null default 1,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_OPERADORA primary key( ID ),
	
	constraint UN_OPERADORA_01 unique key( NM_OPERADORA ),
	constraint UN_OPERADORA_02 unique key( CD_OPERADORA ),
	
	constraint FK_OPERADORA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_OPERADORA_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_EMPRESA(
	ID 					bigint( 17 ) auto_increment,
	ID_OPERADORA		bigint( 17 ) not null,
	NM_EMPRESA			varchar( 200 ) not null,
	CD_EMPRESA 			varchar( 40 ) not null,
	
	TP_EXTERNAL_PROCESS 					int( 1 ) not null default 0,
	
	/**
	 * 0 - QUERY_BY_CONTRATO		- Usar ID_CONTRATO nas queryes p/ criar as planilhas de saída;
	 * 1 - QUERY_BY_PERIODO_ONLY	- Usar apenas mês e ano;
	 */
	TP_REPORT_QUERY int( 3 ) null,
	
	CD_AUTOMATIC_CREATE_BENEFICIARIO		int( 3 ) not null default 0,
	CD_INPUT_DIR 							varchar( 800 ) not null,
	CD_OUTPUT_REPORT_DIR 					varchar( 800 ) not null,
	CD_AUTOMATIC_CREATE_TITULAR				int( 3 ) not null default 0, /* se o Títular tiver CPF, MATRICULA e NOME preenchidos, pode insirir: */
	CD_SEARCH_DEPENDENTES_NONAME			int( 3 ) not null default 0, /* procura Dependetes apenas por CPF e MATRICULA: */
	CD_ACCEPT_TITULAR_WITHOUT_CPF			int( 3 ) not null default 0, /* permite gravar um títular com o CPF zerado: */
	CD_GENERATE_OUTPUT_FILE_NOFATUCOPA		int( 3 ) not null default 0, /* informa que a empresa não usa arquivo FATUCOPA e que o arquivo de saída deve ser gerado mesmo assim: */
	CD_CREATE_BENEFICIARIO_FRON_MECSAS2		int( 3 ) not null default 0, /* informa ao processo se devem ser criados os títulares usando o arquivo de Base de Ativos da Empresa: */
	CD_USE_JASPER_REPORTS					int( 3 ) not null default 0, /* informa se a Empresa utiliza para criar os relatórios o JasperReports */
	CD_UPDATE_BENEFICIARIO_FROM_FATUCOPA	int( 3 ) not null default 0, /* informa se o processo pode atualizar os dados do beneficiário com os valores do arquivo de coparticipação: */
	
	CD_FAILURE_DIR	varchar( 800 ) not null,
	CD_OUTPUT_DIR	varchar( 800 ) not null,
		
	CD_ENABLED								int( 1 ) not null default 1,
	
	TP_SAVE_MECSAS_DETAIL					int( 1 ) not null,
	TP_SAVE_BENEFICIARIO_DETAIL				int( 1 ) not null,
        
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EMPRESA primary key( ID ),
	
	constraint UN_EMPRESA unique key( NM_EMPRESA ),
	
	constraint FK_EMPRESA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EMPRESA_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EMPRESA_03 foreign key( ID_OPERADORA ) references TB_OPERADORA( ID )
);

create table TB_CONTRATO(
	ID 					bigint( 17 ) auto_increment,
	ID_EMPRESA			bigint( 17 ) not null,
	CD_CONTRATO			varchar( 60 ) not null,
	NM_CONTRATO			varchar( 60 ) not null,
	DESCR_CONTRATO		varchar( 400 ) not null,
	TP_USE				int( 3 ) not null,

	ID_CONTRATO_PARENT bigint( 17 ) null,
			
	/* notifica o processo para ler todas as pastas de uma planilha de entrada */
	CD_SPREADSHEET_ALL_PAGES	int( 3 ) null, 
	CD_DISPLAY_OUTPUT_RESULT 	int( 1 ) not null default 1,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_CONTRATO primary key( ID ),
	
	constraint UN_CONTRATO unique key( ID_EMPRESA, CD_CONTRATO ),
	
	constraint FK_CONTRATO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_CONTRATO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_CONTRATO_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID ),
	constraint FK_CONTRATO_04 foreign key ( ID_CONTRATO_PARENT ) references TB_CONTRATO( ID )
);

/*****************************************************************************************************************************************************/

create table TB_ARQUIVO_INPUT(
	ID 						bigint( 17 ) auto_increment,
	ID_CONTRATO				bigint( 17 ) not null,
	NM_ARQUIVO_REGEXP		varchar( 200 ) not null,
	DESCR_ARQUIVO			varchar( 200 ) not null,
	TP_ARQUIVO				int( 3 ) not null, /* 0 = FLAT_FILE, 1, CSV */
	TP_USE					int( 3 ) not null, /* 0 = FATUCOPA, 1 = MECSAS */
	NUM_SKIP_LINES			int( 3 ) not null,
	NUM_DEFAULT_LINE_LENGTH	int( 3 ) null,
			
	VERSION					bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,	
	
	constraint PK_ARQUIVO_INPUT primary key( ID ),
	
	constraint UN_ARQUIVO_INPUT unique key( ID_CONTRATO ),
	
	constraint FK_ARQUIVO_INPUT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_03 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_ARQUIVO_INPUT_COLS_DEF(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT	bigint( 17 ) not null,
	
	NM_COLUMN			varchar( 60 ) not null,
	CD_TYPE				int( 3 ) not null, 	/* 0 = INT, 1 = VARCHAR, 2 = DATE, 3, DATETIME, 4 = DOUBLE, 5 = CLOB */
	VL_LENGTH			int( 5 ) null, 		/* arquivos CSV nao precisa de tamanho de coluna */
	CD_ORDEM			int( 3 ) not null,
	CD_FORMAT			varchar( 200 ) null, /* Para usar com datas e números */
	CD_LOCALE_PATTERN	varchar( 200 ) null, /* Para usar com datas e números */
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_ARQUIVO_INPUT_COLS_DEF primary key( ID ),
	
	constraint UN_ARQUIVO_INPUT_COLS_DEF_01 unique key( ID_ARQUIVO_INPUT, NM_COLUMN ),
	
	constraint FK_ARQUIVO_INPUT_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_COLS_DEF_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_VIEW_DESTINATION(
	ID 				bigint( 17 ) auto_increment,
	NM_VIEW			varchar( 200 ) not null,
	NM_TITLE_LABEL	varchar( 200 ) null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED	bigint( 17 ) not null,
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,
	
	constraint PK_VIEW_DESTINATION primary key( ID ),
	
	constraint UN_VIEW_DESTINATION unique key( NM_VIEW ),
	
	constraint FK_VIEW_DESTINATION_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_VIEW_DESTINATION_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_VIEW_DESTINATION_COLS_DEF(
	ID 							bigint( 17 ) auto_increment,
	ID_VIEW_DESTINATION			bigint( 17 ) not null,
	
	NM_COLUMN					varchar( 60 ) not null,
	CD_TYPE						int( 3 ) not null, 	/* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH					int( 5 ) null, 		/* arquivos CSV nao precisa de tamanho de coluna */
	CD_ORDEM					int( 3 ) not null,
	CD_FORMAT					varchar( 200 ) null, /* Para usar com datas e números */
	NM_COL_TITLE_LABEL			varchar( 200 ) null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_VIEW_DESTINATION_COLS_DEF primary key( ID ),
	
	constraint UN_VIEW_DESTINATION_COLS_DEF_01 unique key( ID_VIEW_DESTINATION, NM_COLUMN ),
	
	constraint FK_VIEW_DESTINATION_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_VIEW_DESTINATION_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_VIEW_DESTINATION_COLS_DEF_03 foreign key( ID_VIEW_DESTINATION ) references TB_VIEW_DESTINATION( ID )
);

create table TB_ARQUIVO_OUTPUT(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT	bigint( 17 ) not null,
	NM_ARQUIVO_FORMAT	varchar( 200 ) not null, /* pode colocar {CC} = contrato, {DD} para dia, {MM} para mês e {YYYY} para ano */
	DESCR_ARQUIVO		varchar( 200 ) not null,
	
	TP_ARQUIVO int( 3 ) not null,
	
	VERSION				bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,	
	
	constraint PK_ARQUIVO_OUTPUT primary key( ID ),
		
	constraint UN_ARQUIVO_OUTPUT unique key( ID_ARQUIVO_INPUT, TP_ARQUIVO ),
	
	constraint FK_ARQUIVO_OUTPUT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_ARQUIVO_OUTPUT_SHEET(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_OUTPUT	bigint( 17 ) not null,
	ID_VIEW_DESTINATION	bigint( 17 ) not null,
	NM_SHEET			varchar( 60 ) not null,
	
	CD_ORDEM int( 3 ) null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_ARQUIVO_OUTPUT_SHEET primary key( ID ),
	
	constraint UN_ARQUIVO_OUTPUT_SHEET unique key( ID_ARQUIVO_OUTPUT, ID_VIEW_DESTINATION ),
	
	constraint FK_ARQUIVO_OUTPUT_SHEET_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_03 foreign key( ID_ARQUIVO_OUTPUT ) references TB_ARQUIVO_OUTPUT( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_04 foreign key( ID_VIEW_DESTINATION ) references TB_VIEW_DESTINATION( ID )
);

create table TB_EXECUCAO(
	ID					bigint( 17 ) auto_increment,
	ID_EMPRESA			bigint( 17 ) not null,
	TP_STATUS			int( 3 ) not null,
	
	VERSION				bigint( 17 ) null,	
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EXECUCAO primary key( ID ),
	
	constraint FK_EXECUCAO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EXECUCAO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EXECUCAO_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
);

create table TB_ARQUIVO_EXECUCAO(
	ID 					bigint( 17 ) auto_increment,
	ID_EXECUCAO 		bigint( 17 ) not null,
	ID_CONTRATO			bigint( 17 ) not null,
	CD_MES				int( 2 ) not null,
	CD_ANO				int( 4 ) not null,
	TP_STATUS			int( 3 ) not null,
	
	NM_ARQUIVO_INPUT	varchar( 400 ) not null,
	NM_ARQUIVO_OUTPUT	varchar( 400 ) null,
	DT_STARTED			timestamp null,
	DT_FINNISHED		timestamp null,
	DESCR_ERROR_MESSAGE	TEXT null,
	
	
	VERSION				bigint( 17 ) null,
 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_ARQUIVO_EXECUCAO primary key( ID ),
	
	constraint UN_ARQUIVO_EXECUCAO unique key( ID_CONTRATO, CD_MES, CD_ANO ),
	
	constraint FK_ARQUIVO_EXECUCAO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_EXECUCAO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_EXECUCAO_03 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID ),
	constraint FK_ARQUIVO_EXECUCAO_04 foreign key( ID_EXECUCAO ) references TB_EXECUCAO( ID )
);

/*****************************************************************************************************************************************************/

/**
 * Regra
 */
 
create table TB_REGRA (
	ID 					bigint( 17 ) auto_increment,
	NM_REGRA 			varchar( 40 ) not null,
	DESCR_REGRA 		varchar( 400 ) null,
	TP_REGRA			int( 3 ) not null, /* 1 = SIMPLES; 2 = CONDICIONAL */
	CD_ORDEM			int( 3 ) not null,
	ID_ARQUIVO_INPUT 	bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,	
	
	constraint PK_REGRA primary key( ID ),
	
	constraint UN_REGRA unique key( NM_REGRA ),
	
	constraint FK_REGRA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_REGRA_OPERATION(
	ID 				bigint( 17 ) auto_increment,
	ID_REGRA 		bigint( 17 ) not null,
	TP_OPERATION	int( 3 ) not null, /* 1 = SOMAR, 2 = SUBTRAIR = 3 = MULTIPLICAR, 4 = DIVIDIR */
	CD_ORDEM		int( 3 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED	bigint( 17 ) not null,
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,	
	
	constraint PK_REGRA_OPERATION primary key( ID ),
	
	constraint FK_REGRA_OPERATION_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_OPERATION_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_OPERATION_03 foreign key( ID_REGRA ) references TB_REGRA( ID )
);

create table TB_REGRA_FIELD(
	ID 							bigint( 17 ) auto_increment,
	ID_REGRA_OPERATION			bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,

	VERSION		bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,	
	
	constraint PK_REGRA_FIELD primary key( ID ),
	
	constraint FK_REGRA_FIELD_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_FIELD_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_FIELD_03 foreign key( ID_REGRA_OPERATION ) references TB_REGRA_OPERATION( ID ),
	constraint FK_REGRA_FIELD_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_REGRA_VALOR(
	ID 						bigint( 17 ) auto_increment,
	ID_REGRA_OPERATION		bigint( 17 ) not null,
	VL_REGRA_VALOR			numeric( 17, 2 ) not null,

	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,	
	
	constraint PK_REGRA_VALOR primary key( ID ),
	
	constraint FK_REGRA_VALOR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_VALOR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_VALOR_03 foreign key( ID_REGRA_OPERATION ) references TB_REGRA_OPERATION( ID )
);

create table TB_REGRA_RESULT(
	ID 							bigint( 17 ) auto_increment,
	ID_REGRA					bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,

	VERSION		bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,	
	
	constraint PK_REGRA_RESULT primary key( ID ),
	
	constraint UN_REGRA_RESULT unique key(  ID_REGRA, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_REGRA_RESULT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_RESULT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_RESULT_03 foreign key( ID_REGRA ) references TB_REGRA( ID ),
	constraint FK_REGRA_RESULT_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_REGRA_CONDITIONAL (
	ID 						bigint( 17 ) auto_increment,
	NM_REGRA_CONDITIONAL	varchar( 400 ) not null,
	CD_ORDEM				int( 3 ) not null,
	ID_ARQUIVO_INPUT 		bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,	
	
	constraint PK_REGRA_CONDITIONAL primary key( ID ),
	
	constraint UN_REGRA_CONDITIONAL unique key( NM_REGRA_CONDITIONAL ),
	
	constraint FK_REGRA_CONDITIONAL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_REGRA_CONDITIONAL_OPERATION(
	ID 						bigint( 17 ) auto_increment,
	ID_REGRA_CONDITIONAL 	bigint( 17 ) not null,
	TP_OPERATION			int( 3 ) not null, /* 5 = EQUALS, 6 = NOT_EQUALS */
	CD_ORDEM				int( 3 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED	bigint( 17 ) not null,
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,	
	
	constraint PK_REGRA_CONDITIONAL_OPERATION primary key( ID ),
	
	constraint FK_REGRA_CONDITIONAL_OPERATION_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_OPERATION_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_OPERATION_03 foreign key( ID_REGRA_CONDITIONAL ) references TB_REGRA_CONDITIONAL( ID )
);

create table TB_REGRA_CONDITIONAL_FIELD(
	ID 								bigint( 17 ) auto_increment,
	ID_REGRA_CONDITIONAL_OPERATION	bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF		bigint( 17 ) not null,

	VERSION		bigint( 17 ) null,
 
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,	
	
	constraint PK_REGRA_CONDITIONAL_FIELD primary key( ID ),
	
	constraint FK_REGRA_CONDITIONAL_FIELD_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_FIELD_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_FIELD_03 foreign key( ID_REGRA_CONDITIONAL_OPERATION ) references TB_REGRA_CONDITIONAL_OPERATION( ID ),
	constraint FK_REGRA_CONDITIONAL_FIELD_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_REGRA_CONDITIONAL_VALOR(
	ID 									bigint( 17 ) auto_increment,
	ID_REGRA_CONDITIONAL_OPERATION		bigint( 17 ) not null,

	VL_DOUBLE							numeric( 17, 2 ) null,
	VL_INT								int( 10 ) null,
	VL_DATE								date null,
	VL_LONG								bigint( 17 ) null,
	VL_STRING							varchar( 500 ),
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED						bigint( 17 ) not null,
	USER_ALTERED 						bigint( 17 ),
	DT_CREATED							timestamp not null,
	DT_ALTERED							timestamp not null,	
	
	constraint PK_REGRA_CONDITIONAL_VALOR primary key( ID ),
	
	constraint FK_REGRA_CONDITIONAL_VALOR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_VALOR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_VALOR_03 foreign key( ID_REGRA_CONDITIONAL_OPERATION ) references TB_REGRA_CONDITIONAL_OPERATION( ID )
);

create table TB_REGRA_CONDITIONAL_RESULT(
	ID 							bigint( 17 ) auto_increment,
	ID_REGRA_CONDITIONAL		bigint( 17 ) not null,
	ID_REGRA_EXECUTION       	bigint( 17 ) not null,

	VERSION		bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,	
	
	constraint PK_REGRA_CONDITIONAL_RESULT primary key( ID ),
	
	constraint UN_REGRA_CONDITIONAL_RESULT unique key( ID_REGRA_CONDITIONAL, ID_REGRA_EXECUTION ),
	
	constraint FK_REGRA_CONDITIONAL_RESULT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_RESULT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REGRA_CONDITIONAL_RESULT_03 foreign key( ID_REGRA_CONDITIONAL ) references TB_REGRA_CONDITIONAL( ID ),
	constraint FK_REGRA_CONDITIONAL_RESULT_04 foreign key( ID_REGRA_EXECUTION ) references TB_REGRA( ID )
);

/*****************************************************************************************************************************************************/

/**
 * Lançamento:
 */
 
create table TB_TITULAR(
	ID 						bigint( 17 ) auto_increment,
	ID_CONTRATO 			bigint( 17 ) not null,
	NR_MATRICULA			bigint( 17 ) not null,
	NR_MATRICULA_EMPRESA	bigint( 17 ) null,
	NM_TITULAR				varchar( 200 ) CHARACTER SET latin1 COLLATE latin1_bin not null,
	NR_CPF					bigint( 17 ) not null,
	DT_NASCIMENTO			date null,
	DT_ADMISSAO				date null,
	DT_DEMISSAO 			date null,

	NM_LABEL				varchar( 400 ) null,
	NR_REF_CODE				bigint( 17 ) null,
	
    NR_DF              	int( 3 ) null,
    NR_RDP            	int( 3 ) null,
    NR_LOCAL			int( 3 ) null,
    CD_CATEGORIA        bigint( 17 ) null,
    NM_SETOR          	varchar( 60 ) null,
    ES                  varchar( 10 ) null,
    CD_PLANO            varchar( 20 ) null,
    DT_INCLUSAO       	date null,
    TP_SEXO           	char( 1 ) null,
    PERMANENCIA         varchar( 10 ) null,
    DT_REF              date null,
    BANCO               int( 3 ),
    AGENCIA             varchar( 60 ) null,
    AGENCIA_DG          varchar( 5 ) null,
    CONTA_CORRENTE      varchar( 60 ) null,
    NR_CPF_CC         	bigint( 17 ) null,
    NM_TITULAR_CC       varchar( 200 ) null,
    CD_CARDIF     		varchar( 40 ) null,
    NR_CEP              int( 8 ),
    TP_LOGRADOURO       varchar( 40 ) null,
    NM_LOGRADOURO       varchar( 200 ) null,
    NUM_LOGRADOURO    	varchar( 10 ) null,
    COMPL               varchar( 20 ) null,
    NM_BAIRRO         	varchar( 60 ) null,
    NM_MUNICIPIO      	varchar( 80 ) null,
    UF                  char( 2 ) null,
    TEL_RESIDENCIAL     	bigint( 17 ) null,
    TEL_COMERCIAL       	bigint( 17 ) null,
    TEL_CELULAR         	bigint( 17 ) null,
    NM_MAE              	varchar( 200 ) null,
    NR_RG               	varchar( 16 ) null,
    NM_ORGAO_EMISSAO_RG    	varchar( 60 ) null,
    NM_PAIS_RG             	varchar( 80 ) null,
    DT_EMISSAO_RG       	date null,
    UF_RG               	char( 2 ) null,
    PIS                 	varchar( 80 ) null,
    CNS                 	varchar( 60 ) null,
    EMAIL               	varchar( 80 ) null,
    TP_GRAU_ESCOLARIDADE			int ( 3 ) null,
    VL_RENDA_FAMILIAR      			numeric( 17, 2 ) null,
    CD_PROFISSAO        			int( 3 ) null,
    CD_PAIS_ORIGEM      			int( 3 ) null,
    DT_EXCLUSAO             		date null,
    CD_MOTIVO_EXCLUSAO      		varchar( 60 ) null,
    CD_OPERACAO             		int( 3 ) null,
    CD_EMPRESA_TITULAR_TRANSF       int( 3 ) null,
    NR_MATRICULA_TRANSF     		bigint( 17 ) null,
    NR_LOCAL_TRANSF         		int( 3 ) null,
    CD_CATEGORIA_TRANSF     		bigint( 17 ) null,
    CD_PLANO_TRANSF         		int( 5 ) null,
    MOTIVO_REMISSAO         		varchar( 60 ) null,
    NR_CPF_NOVO_TITULAR     		bigint( 17 ) null,
    QTDE_PERM_MESES         		int( 3 ) null,
    RDP_NOVO_TITULAR        		int( 3 ) null,
    DT_INICIO_TRANSF        		date null,
    CD_STATUS               		int( 3 ) null,
    CD_ERROR                		int( 3 ) null,
    CD_DV                   		int( 3 ) null,
    BLOQ_EMPRESA_INADIMPLENCIA      varchar( 60 ) null,
    CPT                             varchar( 60 ) null,
    CD_EMPRESA_TITULAR              int( 5 ) null,
    DIF_MATRICULA_TITULAR           varchar( 60 ) null,
    NR_TITULO_ELEITOR               varchar( 80 ) null,
    NR_RIC                          varchar( 60 ) null,
    NR_CERTIDAO_NASCIMENTO          varchar( 200 ) null,
    NR_CARTEIRA_IDENT               varchar( 60 ) null,
    IND_SEGURADO_CONTRIBUTARIO      varchar( 60 ) null,
    IND_COND_EX_EMPREGADO           varchar( 60 ) null,
    IND_PERM_PLANO                  varchar( 60 ) null,
    QTDE_MESES_CONTRIB              int( 3 ) null,
    NM_BENEFICIARIO_COMPLETO        varchar( 200 ) null,
    IND_TITULAR_REMIDO              varchar( 60 ) null,
    EMAIL_SEGURADORA                varchar( 80 ) null,
    IND_PORTABILIDADE_01            varchar( 60 ) null,
    IND_PORTABILIDADE_02            varchar( 60 ) null,
    IND_CARENCIA                    varchar( 60 ) null,
    CD_PRODUTO                      int( 5 ) null,
    CD_IND_PLANO_ANTERIOR_SAS     	varchar( 60 ) null,
    CID01                           varchar( 10 ) null,
    CID02                           varchar( 10 ) null,
    CID03                           varchar( 10 ) null,
    CID04                           varchar( 10 ) null,
    CID05                           varchar( 10 ) null,
    CID06                           varchar( 10 ) null,
    CID07                           varchar( 10 ) null,
    CID08                           varchar( 10 ) null,
    CID09                           varchar( 10 ) null,
    CID10                           varchar( 10 ) null,
    IBGE                            varchar( 10 ) null,
    CBO                             varchar( 10 ) null,
    DIF_TRANSF                      varchar( 10 ) null,

	VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
	VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
	DESCR_PROFISSAO			varchar( 80 ) null,
	NR_MATRICULA_ESPECIAL	varchar( 20 ) null,
	NR_SUBFATURA			int( 10 ) null,
	VL_INSS					numeric( 17, 2 ) null,
	VL_ALIQUOTA_INSS		numeric( 17, 2 ) null,
	VL_LIQUIDO_SINISTRO		numeric( 17, 2 ) null,
	IND_EVENTO				int( 5 ) null,
	CD_USUARIO				varchar( 20 ) null,
	NR_CERTIFICADO			bigint( 17 ) null,
    	
	VERSION					bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR primary key( ID ),
	
	constraint UN_TITULAR_01 unique key ( ID_CONTRATO, NR_MATRICULA ),
	
	constraint FK_TITULAR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_03 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_DEPENDENTE(
	ID 						bigint( 17 ) auto_increment,
	ID_TITULAR				bigint( 17 ) not null,
	NR_MATRICULA			bigint( 17 ) not null,
	NR_MATRICULA_EMPRESA	bigint( 17 ) null,
	TP_DEPENDENTE			int( 3 ) not null, /* 0 = CONJUGE, 1 = FILHOS, 2 = PAIS */
	NM_DEPENDENTE			varchar( 200 ) CHARACTER SET latin1 COLLATE latin1_bin not null,
	NR_CPF					bigint( 17 ) null,
	DT_NASCIMENTO			date,
	
	NM_LABEL				varchar( 400 ) null,
	NR_REF_CODE				bigint( 17 ) null,
	
    NR_DF              	int( 3 ) null,
    NR_RDP            	int( 3 ) null,
    NR_LOCAL			int( 3 ) null,
    CD_CATEGORIA        bigint( 17 ) null,
    NM_SETOR          	varchar( 60 ) null,
    ES                  varchar( 10 ) null,
    CD_PLANO            varchar( 20 ) null,
    DT_INCLUSAO       	date null,
    TP_SEXO           	char( 1 ) null,
    PERMANENCIA         varchar( 10 ) null,
    DT_REF              date null,
    BANCO               int( 3 ),
    AGENCIA             varchar( 60 ) null,
    AGENCIA_DG          varchar( 5 ) null,
    CONTA_CORRENTE      varchar( 60 ) null,
    NR_CPF_CC         	bigint( 17 ) null,
    NM_TITULAR_CC       varchar( 200 ) null,
    CD_CARDIF     		varchar( 40 ) null,
    NR_CEP              int( 8 ),
    TP_LOGRADOURO       varchar( 40 ) null,
    NM_LOGRADOURO       varchar( 200 ) null,
    NUM_LOGRADOURO    	varchar( 10 ) null,
    COMPL               varchar( 20 ) null,
    NM_BAIRRO         	varchar( 60 ) null,
    NM_MUNICIPIO      	varchar( 80 ) null,
    UF                  char( 2 ) null,
    TEL_RESIDENCIAL     	bigint( 17 ) null,
    TEL_COMERCIAL       	bigint( 17 ) null,
    TEL_CELULAR         	bigint( 17 ) null,
    NM_MAE              	varchar( 200 ) null,
    NR_RG               	varchar( 16 ) null,
    NM_ORGAO_EMISSAO_RG    	varchar( 60 ) null,
    NM_PAIS_RG             	varchar( 80 ) null,
    DT_EMISSAO_RG       	date null,
    UF_RG               	char( 2 ) null,
    PIS                 	varchar( 80 ) null,
    CNS                 	varchar( 60 ) null,
    EMAIL               	varchar( 80 ) null,
    TP_GRAU_ESCOLARIDADE			int ( 3 ) null,
    VL_RENDA_FAMILIAR      			numeric( 17, 2 ) null,
    CD_PROFISSAO        			int( 3 ) null,
    CD_PAIS_ORIGEM      			int( 3 ) null,
    DT_EXCLUSAO             		date null,
    CD_MOTIVO_EXCLUSAO      		varchar( 60 ) null,
    CD_OPERACAO             		int( 3 ) null,
    CD_EMPRESA_TITULAR_TRANSF       int( 3 ) null,
    NR_MATRICULA_TRANSF     		bigint( 17 ) null,
    NR_LOCAL_TRANSF         		int( 3 ) null,
    CD_CATEGORIA_TRANSF     		bigint( 17 ) null,
    CD_PLANO_TRANSF         		int( 5 ) null,
    MOTIVO_REMISSAO         		varchar( 60 ) null,
    NR_CPF_NOVO_TITULAR     		bigint( 17 ) null,
    QTDE_PERM_MESES         		int( 3 ) null,
    RDP_NOVO_TITULAR        		int( 3 ) null,
    DT_INICIO_TRANSF        		date null,
    CD_STATUS               		int( 3 ) null,
    CD_ERROR                		int( 3 ) null,
    CD_DV                   		int( 3 ) null,
    BLOQ_EMPRESA_INADIMPLENCIA      varchar( 60 ) null,
    CPT                             varchar( 60 ) null,
    CD_EMPRESA_TITULAR              int( 5 ) null,
    DIF_MATRICULA_TITULAR           varchar( 60 ) null,
    NR_TITULO_ELEITOR               varchar( 80 ) null,
    NR_RIC                          varchar( 60 ) null,
    NR_CERTIDAO_NASCIMENTO          varchar( 200 ) null,
    NR_CARTEIRA_IDENT               varchar( 60 ) null,
    IND_SEGURADO_CONTRIBUTARIO      varchar( 60 ) null,
    IND_COND_EX_EMPREGADO           varchar( 60 ) null,
    IND_PERM_PLANO                  varchar( 60 ) null,
    QTDE_MESES_CONTRIB              int( 3 ) null,
    NM_BENEFICIARIO_COMPLETO        varchar( 200 ) null,
    IND_TITULAR_REMIDO              varchar( 60 ) null,
    EMAIL_SEGURADORA                varchar( 80 ) null,
    IND_PORTABILIDADE_01            varchar( 60 ) null,
    IND_PORTABILIDADE_02            varchar( 60 ) null,
    IND_CARENCIA                    varchar( 60 ) null,
    CD_PRODUTO                      int( 5 ) null,
    CD_IND_PLANO_ANTERIOR_SAS     	varchar( 60 ) null,
    CID01                           varchar( 10 ) null,
    CID02                           varchar( 10 ) null,
    CID03                           varchar( 10 ) null,
    CID04                           varchar( 10 ) null,
    CID05                           varchar( 10 ) null,
    CID06                           varchar( 10 ) null,
    CID07                           varchar( 10 ) null,
    CID08                           varchar( 10 ) null,
    CID09                           varchar( 10 ) null,
    CID10                           varchar( 10 ) null,
    IBGE                            varchar( 10 ) null,
    CBO                             varchar( 10 ) null,
    DIF_TRANSF                      varchar( 10 ) null,
    	
	VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
	VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
	DESCR_PROFISSAO			varchar( 80 ) null,
	NR_MATRICULA_ESPECIAL	varchar( 20 ) null,
	NR_SUBFATURA			int( 10 ) null,
	VL_INSS					numeric( 17, 2 ) null,
	VL_ALIQUOTA_INSS		numeric( 17, 2 ) null,
	VL_LIQUIDO_SINISTRO		numeric( 17, 2 ) null,
	IND_EVENTO				int( 5 ) null,
	CD_USUARIO				varchar( 20 ) null,
	NR_CERTIFICADO			bigint( 17 ) null,
    	
	VERSION					bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_DEPENDENTE primary key( ID ),
	
	constraint UN_DEPENDENTE_01 unique key( ID_TITULAR, NM_DEPENDENTE ),
	constraint UN_DEPENDENTE_02 unique key( ID_TITULAR, NM_DEPENDENTE, NR_CPF ),
	
	constraint FK_DEPENDENTE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID )
);

/**************************************************************************************************************************************************/
	
create table TB_ARQUIVO_INPUT_SHEET(
	ID 							bigint( 17 ) auto_increment,
	
	ID_ARQUIVO_INPUT			bigint( 17 ) not null,		
	CD_SHEET					int( 3 ) not null,
	ID_CONTRATO					bigint( 17 ) null, /* Se não existir o CD_CONTRATO dentro do arquivo, podesse chumbar um padrão para todos os registros */
	
	VERSION						bigint( 17 ) null,
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_ARQUIVO_INPUT_SHEET primary key( ID ),
	
	constraint UN_ARQUIVO_INPUT_SHEET_01 unique key( ID_ARQUIVO_INPUT, CD_SHEET ),
	
	constraint FK_ARQUIVO_INPUT_SHEET_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_SHEET_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_SHEET_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID ),
	constraint FK_ARQUIVO_INPUT_SHEET_04 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_ARQUIVO_INPUT_SHEET_COLS_DEF(
	ID 							bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT_SHEET		bigint( 17 ) not null,
	
	NM_COLUMN					varchar( 60 ) not null,
	CD_TYPE						int( 3 ) not null, 	/* 0 = INT, 1 = VARCHAR, 2 = DATE, 3, DATETIME, 4 = DOUBLE, 5 = CLOB */
	VL_LENGTH					int( 5 ) null, 		/* arquivos CSV nao precisa de tamanho de coluna */
	CD_ORDEM					int( 3 ) not null,
	CD_FORMAT					varchar( 200 ) null, /* Para usar com datas e números */
	CD_LOCALE_PATTERN			varchar( 200 ) null, /* Para usar com datas e números */
	CD_RESTRICTED_VALUE			varchar( 40 ) null,
	
	VERSION						bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_ARQUIVO_INPUT_SHEET_COLS_DEF primary key( ID ),
	
	constraint UN_ARQUIVO_INPUT_SHEET_COLS_DEF_01 unique key( ID_ARQUIVO_INPUT_SHEET, NM_COLUMN ),
	
	constraint FK_ARQUIVO_INPUT_SHEET_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_SHEET_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_SHEET_COLS_DEF_03 foreign key( ID_ARQUIVO_INPUT_SHEET ) references TB_ARQUIVO_INPUT_SHEET( ID )
);	

/**************************************************************************************************************************************************/

create table TB_LANCAMENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_CONTRATO				bigint( 17 ) not null,
	ID_TITULAR				bigint( 17 ) not null,
	ID_DEPENDENTE			bigint( 17 ) null,
	CD_MES					int( 2 ) not null,
	CD_ANO					int( 4 ) not null,
	VL_PRINCIPAL			numeric( 17, 2 ) null,
	
	VL_REEMBOLSO			numeric( 17, 2 ) null,
	VL_PARTICIPACAO			numeric( 17, 2 ) null,	
	DT_UTILIZACAO			date null,
	DESCR_UTILIZACAO		varchar( 200 ) null,
			
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_LANCAMENTO primary key( ID ),
	
	constraint FK_LANCAMENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID ),
	constraint FK_LANCAMENTO_04 foreign key( ID_DEPENDENTE ) references TB_DEPENDENTE( ID ),
	constraint FK_LANCAMENTO_05 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_LANCAMENTO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_COLS_DEF primary key( ID ),
	
	constraint UN_LANCAMENTO_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_LANCAMENTO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_LANCAMENTO_INPUT_SHEET(
	ID 						bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT_SHEET	bigint( 17 ) not null,
	
	VERSION					bigint( 17 ) null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_INPUT_SHEET primary key( ID ),
	
	constraint UN_INPUT_LANCAMNETO_01 unique key( ID_ARQUIVO_INPUT_SHEET ),
	
	constraint FK_LANCAMENTO_INPUT_SHEET_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_SHEET_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_SHEET_03 foreign key( ID_ARQUIVO_INPUT_SHEET ) references TB_ARQUIVO_INPUT_SHEET( ID )
);

create table TB_LANCAMENTO_INPUT_SHEET_COLS(
	ID 								bigint( 17 ) auto_increment,
	ID_LANCAMENTO_INPUT_SHEET		bigint( 17 ) not null,
	CD_LANCAMENTO_COLS_DEF			int( 3 ) not null,
	ID_ARQUIVO_INPUT_SHEET_COLS_DEF	bigint( 17 ) not null,
	
	VERSION					bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_INPUT_SHEET_COLS primary key( ID ),
	
	constraint UN_INPUT_LANCAMNETO_COLS_01 unique key( CD_LANCAMENTO_COLS_DEF, ID_ARQUIVO_INPUT_SHEET_COLS_DEF ),
	
	constraint FK_LANCAMENTO_INPUT_SHEET_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_SHEET_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_SHEET_COLS_03 foreign key( ID_LANCAMENTO_INPUT_SHEET ) references TB_LANCAMENTO_INPUT_SHEET( ID ),
	constraint FK_LANCAMENTO_INPUT_SHEET_COLS_04 foreign key( ID_ARQUIVO_INPUT_SHEET_COLS_DEF ) references TB_ARQUIVO_INPUT_SHEET_COLS_DEF( ID )
);

create table TB_SUBFATURA(
	ID 						bigint( 17 ) auto_increment,
	ID_EMPRESA				bigint( 17 ) not null,
	NR_SUBFATURA			int( 5 ) not null,
	NM_SUBFATURA			varchar( 80 ) not null,

	VERSION					bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_SUBFATURA primary key( ID ),
	
	constraint UN_SUBFATURA_01 unique key( ID_EMPRESA, NR_SUBFATURA ),
	
	constraint FK_SUBFATURA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_SUBFATURA_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_SUBFATURA_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )	
);

/***********************************************************************************************************************/
/* Isento */

create table TB_TITULAR_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_TITULAR				bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* */
	ID_CONTRATO				bigint( 17 ) not null,

	CD_MES					int( 2 ) not null,
	CD_ANO					int( 4 ) not null,
	VL_ISENCAO				numeric( 17, 2 ) null,
	
	DT_INICIO				date null,
	DT_FIM					date null,

	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR_ISENTO primary key( ID ),
	
	constraint UN_TITULAR_ISENTO unique key ( ID_TITULAR, CD_MES, CD_ANO, TP_ISENTO ),
	
	constraint FK_TITULAR_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID ),
	constraint FK_TITULAR_ISENTO_04 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_DEPENDENTE_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_DEPENDENTE			bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* 1 = GRAVIDA,  */
	ID_CONTRATO				bigint( 17 ) not null,

	CD_MES					int( 2 ) not null,
	CD_ANO					int( 4 ) not null,
	VL_ISENCAO				numeric( 17, 2 ) null,
	
	DT_INICIO				date null,
	DT_FIM					date null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
		
	constraint PK_DEPENDENTE_ISENTO primary key( ID ),
	
	constraint UN_DEPENDENTE_ISENTO unique key ( ID_DEPENDENTE, CD_MES, CD_ANO, TP_ISENTO ),	
	
	constraint FK_DEPENDENTE_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_03 foreign key( ID_DEPENDENTE ) references TB_DEPENDENTE( ID ),
	constraint FK_DEPENDENTE_ISENTO_04 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_TITULAR_ISENTO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_TITULAR_ISENTO_COLS_DEF primary key( ID ),
	
	constraint UN_TITULAR_ISENTO_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_TITULAR_ISENTO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_DEPENDENTE_ISENTO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_DEPENDENTE_ISENTO_COLS_DEF primary key( ID ),
	
	constraint UN_DEPENDENTE_ISENTO_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_DEPENDENTE_ISENTO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_INPUT_TITULAR_ISENTO(
	ID 								bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT				bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,
	
	constraint PK_INPUT_TITULAR_ISENTO primary key( ID ),
	
	constraint FK_INPUT_TITULAR_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_ISENTO_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
	
);

create table TB_INPUT_DEPENDENTE_ISENTO(
	ID 								bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT				bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,
	
	constraint PK_INPUT_DEPENDENTE_ISENTO primary key( ID ),
	
	constraint FK_INPUT_DEPENDENTE_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
	
);

create table TB_INPUT_TITULAR_ISENTO_COLS(
	ID 							bigint( 17 ) auto_increment,
    ID_INPUT_TITULAR_ISENTO		bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) null,
	ID_TITULAR_ISENTO_COLS_DEF	bigint( 17 ) not null,
	TP_ISENTO					int( 3 ) null,
		
	VERSION		bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,

	constraint PK_INPUT_TITULAR_ISENTO_COLS primary key( ID ),
    
    constraint UN_INPUT_TITULAR_ISENTO_COLS unique key( ID_INPUT_TITULAR_ISENTO, ID_ARQUIVO_INPUT_COLS_DEF, ID_TITULAR_ISENTO_COLS_DEF ),

	constraint FK_INPUT_TITULAR_ISENTO_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_ISENTO_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_ISENTO_COLS_03 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID ),
	constraint FK_INPUT_TITULAR_ISENTO_COLS_04 foreign key( ID_TITULAR_ISENTO_COLS_DEF ) references TB_TITULAR_ISENTO_COLS_DEF( ID )
);

create table TB_INPUT_DEPENDENTE_ISENTO_COLS(
	ID 								bigint( 17 ) auto_increment,
	ID_INPUT_DEPENDENTE_ISENTO		bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF		bigint( 17 ) null,
	ID_DEPENDENTE_ISENTO_COLS_DEF	bigint( 17 ) not null,
	TP_ISENTO						int( 3 ) null,
		
	VERSION		bigint( 17 ) null,
 
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,

	constraint PK_INPUT_DEPENDENTE_ISENTO_COLS primary key( ID ),
	
	constraint UN_INPUT_DEPENDENTE_ISENTO_COLS unique key( ID_INPUT_DEPENDENTE_ISENTO, ID_ARQUIVO_INPUT_COLS_DEF, ID_DEPENDENTE_ISENTO_COLS_DEF ),

	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_03 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_04 foreign key( ID_DEPENDENTE_ISENTO_COLS_DEF ) references TB_DEPENDENTE_ISENTO_COLS_DEF( ID ),
	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_05 foreign key( ID_INPUT_DEPENDENTE_ISENTO ) references TB_INPUT_DEPENDENTE_ISENTO( ID )
);

/***********************************************************************************************************************/

create table TB_LANCAMENTO_INPUT(
	ID 						bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT		bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
	
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_INPUT primary key( ID ),
	
	constraint UN_INPUT_LANCAMNETO_01 unique key( ID_ARQUIVO_INPUT ),
	
	constraint FK_LANCAMENTO_INPUT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_LANCAMENTO_INPUT_COLS(
	ID 							bigint( 17 ) auto_increment,
	ID_LANCAMENTO_INPUT			bigint( 17 ) not null,
	CD_LANCAMENTO_COLS_DEF		int( 3 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	VERSION		bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_INPUT_COLS primary key( ID ),
	
	constraint UN_INPUT_LANCAMNETO_COLS_01 unique key( CD_LANCAMENTO_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_LANCAMENTO_INPUT_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_INPUT_COLS_03 foreign key( ID_LANCAMENTO_INPUT ) references TB_LANCAMENTO_INPUT( ID ),
	constraint FK_LANCAMENTO_INPUT_COLS_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_DESCONHECIDO(
	ID 							bigint( 17 ) auto_increment,
	ID_CONTRATO					bigint( 17 ) not null,
	CD_MES						int( 2 ) not null,
	CD_ANO						int( 4 ),
	
	NM_BENEFICIARIO				varchar( 200 ) null,
	NM_TITULAR					varchar( 200 ) null,
	NR_MATRICULA				bigint( 17 ) null,
	NR_CPF						bigint( 17 ) null,
	DT_NASCIMENTO				date,
		
	VL_PRINCIPAL				numeric( 17, 2 ) null,
	DT_ADMISSAO					date,
	NM_LABEL					varchar( 400 ) null,
	NR_REF_CODE					bigint( 17 ) null,		
	
	NR_MATRICULA_EMPRESA		bigint( 17 ) null,
	
    NR_DF              	int( 3 ) null,
    NR_RDP            	int( 3 ) null,
    NR_LOCAL			int( 3 ) null,
    CD_CATEGORIA        bigint( 17 ) null,
    NM_SETOR          	varchar( 60 ) null,
    ES                  varchar( 10 ) null,
    CD_PLANO            varchar( 20 ) null,
    DT_INCLUSAO       	date null,
    TP_SEXO           	char( 1 ) null,
    PERMANENCIA         varchar( 10 ) null,
    DT_REF              date null,
    BANCO               int( 3 ),
    AGENCIA             varchar( 60 ) null,
    AGENCIA_DG          varchar( 5 ) null,
    CONTA_CORRENTE      varchar( 60 ) null,
    NR_CPF_CC         	bigint( 17 ) null,
    NM_TITULAR_CC       varchar( 200 ) null,
    CD_CARDIF     		varchar( 40 ) null,
    NR_CEP              int( 8 ),
    TP_LOGRADOURO       varchar( 40 ) null,
    NM_LOGRADOURO       varchar( 200 ) null,
    NUM_LOGRADOURO    	varchar( 10 ) null,
    COMPL               varchar( 20 ) null,
    NM_BAIRRO         	varchar( 60 ) null,
    NM_MUNICIPIO      	varchar( 80 ) null,
    UF                  char( 2 ) null,
    TEL_RESIDENCIAL     	bigint( 17 ) null,
    TEL_COMERCIAL       	bigint( 17 ) null,
    TEL_CELULAR         	bigint( 17 ) null,
    NM_MAE              	varchar( 200 ) null,
    NR_RG               	varchar( 16 ) null,
    NM_ORGAO_EMISSAO_RG    	varchar( 60 ) null,
    NM_PAIS_RG             	varchar( 80 ) null,
    DT_EMISSAO_RG       	date null,
    UF_RG               	char( 2 ) null,
    PIS                 	varchar( 80 ) null,
    CNS                 	varchar( 60 ) null,
    EMAIL               	varchar( 80 ) null,
    TP_GRAU_ESCOLARIDADE			int ( 3 ) null,
    VL_RENDA_FAMILIAR      			numeric( 17, 2 ) null,
    CD_PROFISSAO        			int( 3 ) null,
    CD_PAIS_ORIGEM      			int( 3 ) null,
    DT_EXCLUSAO             		date null,
    CD_MOTIVO_EXCLUSAO      		varchar( 60 ) null,
    CD_OPERACAO             		int( 3 ) null,
    CD_EMPRESA_TITULAR_TRANSF       int( 3 ) null,
    NR_MATRICULA_TRANSF     		bigint( 17 ) null,
    NR_LOCAL_TRANSF         		int( 3 ) null,
    CD_CATEGORIA_TRANSF     		bigint( 17 ) null,
    CD_PLANO_TRANSF         		int( 5 ) null,
    MOTIVO_REMISSAO         		varchar( 60 ) null,
    NR_CPF_NOVO_TITULAR     		bigint( 17 ) null,
    QTDE_PERM_MESES         		int( 3 ) null,
    RDP_NOVO_TITULAR        		int( 3 ) null,
    DT_INICIO_TRANSF        		date null,
    CD_STATUS               		int( 3 ) null,
    CD_ERROR                		int( 3 ) null,
    CD_DV                   		int( 3 ) null,
    BLOQ_EMPRESA_INADIMPLENCIA      varchar( 60 ) null,
    CPT                             varchar( 60 ) null,
    CD_EMPRESA_TITULAR              int( 5 ) null,
    DIF_MATRICULA_TITULAR           varchar( 60 ) null,
    NR_TITULO_ELEITOR               varchar( 80 ) null,
    NR_RIC                          varchar( 60 ) null,
    NR_CERTIDAO_NASCIMENTO          varchar( 200 ) null,
    NR_CARTEIRA_IDENT               varchar( 60 ) null,
    IND_SEGURADO_CONTRIBUTARIO      varchar( 60 ) null,
    IND_COND_EX_EMPREGADO           varchar( 60 ) null,
    IND_PERM_PLANO                  varchar( 60 ) null,
    QTDE_MESES_CONTRIB              int( 3 ) null,
    NM_BENEFICIARIO_COMPLETO        varchar( 200 ) null,
    IND_TITULAR_REMIDO              varchar( 60 ) null,
    EMAIL_SEGURADORA                varchar( 80 ) null,
    IND_PORTABILIDADE_01            varchar( 60 ) null,
    IND_PORTABILIDADE_02            varchar( 60 ) null,
    IND_CARENCIA                    varchar( 60 ) null,
    CD_PRODUTO                      int( 5 ) null,
    CD_IND_PLANO_ANTERIOR_SAS     	varchar( 60 ) null,
    CID01                           varchar( 10 ) null,
    CID02                           varchar( 10 ) null,
    CID03                           varchar( 10 ) null,
    CID04                           varchar( 10 ) null,
    CID05                           varchar( 10 ) null,
    CID06                           varchar( 10 ) null,
    CID07                           varchar( 10 ) null,
    CID08                           varchar( 10 ) null,
    CID09                           varchar( 10 ) null,
    CID10                           varchar( 10 ) null,
    IBGE                            varchar( 10 ) null,
    CBO                             varchar( 10 ) null,
    DIF_TRANSF                      varchar( 10 ) null,    
    	
	VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
	VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
	DESCR_PROFISSAO			varchar( 80 ) null,
	NR_MATRICULA_ESPECIAL	varchar( 20 ) null,
	NR_SUBFATURA			int( 10 ) null,
	VL_INSS					numeric( 17, 2 ) null,
	VL_ALIQUOTA_INSS		numeric( 17, 2 ) null,
	VL_LIQUIDO_SINISTRO		numeric( 17, 2 ) null,
	IND_EVENTO				int( 5 ) null,
	CD_USUARIO				varchar( 20 ) null,
	NR_CERTIFICADO			bigint( 17 ) null,
	DT_UTILIZACAO			date null,
	DESCR_UTILIZACAO		varchar( 200 ) null,
    	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_DESCONHECIDO primary key( ID ),
		
	constraint FK_DESCONHECIDO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_03 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_ARQUIVO_OUTPUT_DESCONHECIDO(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT	bigint( 17 ) not null,
	
	NM_ARQUIVO_FORMAT	varchar( 200 ) not null, /* pode colocar {CC} = contrato, {DD} para dia, {MM} para mês e {YYYY} para ano */
	NM_DESCR_ARQUIVO	varchar( 200 ) not null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,	
	
	constraint PK_ARQUIVO_OUTPUT_DESCONHECIDO primary key( ID ),
	
	constraint UN_ARQUIVO_OUTPUT_DESCONHECIDO unique key( ID_ARQUIVO_INPUT ),
	
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_ARQUIVO_OUTPUT_DESCONHECIDO_SHEET(
	ID 								bigint( 17 ) auto_increment,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO	bigint( 17 ) not null,

	ID_VIEW_DESTINATION				bigint( 17 ) not null,
	NM_SHEET						varchar( 60 ) not null,
	CD_ORDEM						int( 3 ) not null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,
	
	constraint PK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF primary key( ID ),
	
	constraint UN_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF unique key( ID_ARQUIVO_OUTPUT_DESCONHECIDO, ID_VIEW_DESTINATION ),
		
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),	
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_03 foreign key( ID_ARQUIVO_OUTPUT_DESCONHECIDO ) references TB_ARQUIVO_OUTPUT_DESCONHECIDO( ID ),
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_04 foreign key( ID_VIEW_DESTINATION ) references TB_VIEW_DESTINATION( ID )
);

/**
 * 1 - TP_BENEFICIARIO
 * 2 - NR_MATRICULA_TITULAR
 * 3 - NR_MATRICULA_DEPENDENTE
 * 4 - NM_BENEFICIARIO_TITULAR
 * 5 - NM_BENEFICIARIO_DEPENDENTE
 * 6 - DT_NASCIMENTO
 * 7 - NR_CPF_TITULAR
 * 8 - NR_CPF_DEPENDENTE 
 */
create table TB_BENEFICIARIO_COLS(
	ID 							bigint( 17 ) auto_increment,	
	CD_BENEFICIARIO_COLS_DEF	int( 3 ) not null,
	ID_ARQUIVO_INPUT_SHEET_COLS_DEF bigint( 17 ) null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) null,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_BENEFICIARIO_COLS primary key( ID ),
	
	constraint UN_BENEFICIARIO_COLS_01 unique key( CD_BENEFICIARIO_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_BENEFICIARIO_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_COLS_03 foreign key ( ID_ARQUIVO_INPUT_SHEET_COLS_DEF ) references TB_ARQUIVO_INPUT_SHEET_COLS_DEF( ID ),
	constraint FK_BENEFICIARIO_COLS_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

/*****************************************************************************************************************************************************/

create table TB_BENEFICIARIO_ISENTO(
	ID 							bigint( 17 ) auto_increment,	

	NR_MATRICULA				bigint( 17 ) not null,
	TP_ISENTO					int( 3 ) not null, /* 0 = CONJUGE, 1 = FILHOS, 2 = PAIS */
	NM_DEPENDENTE				varchar( 200 ) not null,
	NR_CPF						char( 11 ),
	DT_NASCIMENTO				date,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_BENEFICIARIO_ISENTO primary key( ID ),
	
	constraint UN_BENEFICIARIO_ISENTO_01 unique key( NR_MATRICULA ),
	constraint UN_BENEFICIARIO_ISENTO_02 unique key( NR_CPF ),
	
	constraint FK_BENEFICIARIO_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID )

);

create table TB_ISENTO_INPUT_SHEET(
	ID 							bigint( 17 ) auto_increment,
	
	ID_ARQUIVO_INPUT			bigint( 17 ) not null,	
	TP_ISENTO					int( 3 ) null,
	
	CD_SHEET					int( 3 ) not null,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_ISENTO_INPUT_SHEET primary key( ID ),
	
	constraint UN_ISENTO_INPUT_SHEET_01 unique key( ID_ARQUIVO_INPUT, TP_ISENTO, CD_SHEET ),
	
	constraint FK_ISENTO_INPUT_SHEET_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ISENTO_INPUT_SHEET_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ISENTO_INPUT_SHEET_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
			
);

create table TB_ISENTO_INPUT_SHEET_COLS(
	ID 							bigint( 17 ) auto_increment,	

	ID_ISENTO_INPUT_SHEET			bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_SHEET_COLS_DEF	bigint( 17 ) not null,
	CD_BENEFICIARIO_COLS_DEF		int( 3 ) not null,
	CD_ORDEM						int( 3 ) not null,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_TB_ISENTO_INPUT_SHEET_COLS primary key( ID ),
	
	constraint UN_TB_ISENTO_INPUT_SHEET_COLS_01 unique key( ID_ISENTO_INPUT_SHEET, ID_ARQUIVO_INPUT_COLS_DEF, CD_BENEFICIARIO_COLS_DEF ),
	
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_03 foreign key( ID_ARQUIVO_INPUT_SHEET_COLS_DEF ) references TB_ARQUIVO_INPUT_SHEET_COLS_DEF( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_04 foreign key( ID_ISENTO_INPUT_SHEET ) references TB_ISENTO_INPUT_SHEET( ID )

);

create table TB_EXTERNAL_PROCESS(
	ID 							bigint( 17 ) auto_increment,
	ID_EMPRESA					bigint( 17 ) not null,
	
	CD_REGEXP_ARQUIVO_INPUT		varchar( 200 ) not null,
	CD_REGEXP_ARQUIVO_OUTPUT	varchar( 200 ) not null,
	        
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EXTERNAL_PROCESS primary key( ID ),
	
	constraint UN_EXTERNAL_PROCESS unique key( ID_EMPRESA ),
	
	constraint FK_EXTERNAL_PROCESS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EXTERNAL_PROCESS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EXTERNAL_PROCESS_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )	
);

/**
 * edson - 11/10/2018
 */

create table TB_REPORT(
	ID 					bigint( 17 ) auto_increment,	
	NM_REPORT			varchar( 60 ) not null,
	DESCR_REPORT		varchar( 200 ) not null,
	NM_OUTPUT_FORMAT	varchar( 200 ) not null,
	
	ID_EMPRESA			bigint( 17 ) not null,
        
	VERSION				bigint( 17 ) null,	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_REPORT primary key( ID ),
	
	constraint UN_REPORT unique key( NM_REPORT ),
	
	constraint FK_REPORT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_REPORT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_REPORT_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
 
);

/*****************************************************************************************************************************************************/

/**
 * Edson - 25/09/2018
 */

create view VW_TITULAR_RESUMO as
select
	titular.ID,
	titular.NR_MATRICULA,
	titular.NR_CPF,
    titular.ID_CONTRATO,
	empresa.ID ID_EMPRESA,    
	titular.NM_TITULAR,
	
	titular.VERSION,
	titular.USER_CREATED,
	titular.USER_ALTERED,
	titular.DT_CREATED,
	titular.DT_ALTERED
from TB_TITULAR titular
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA;

create view VW_DEPENDENTE_RESUMO as
select
	dependente.ID,
	dependente.NR_MATRICULA,
	dependente.NR_CPF,
	empresa.ID ID_EMPRESA,
	dependente.NM_DEPENDENTE,

	dependente.VERSION,
	dependente.USER_CREATED,
	dependente.USER_ALTERED,
	dependente.DT_CREATED,
	dependente.DT_ALTERED	
from TB_DEPENDENTE dependente
	join TB_TITULAR titular on
		titular.ID = dependente.ID_TITULAR
	join TB_CONTRATO contrato on
		contrato.ID = titular.ID_CONTRATO
	join TB_EMPRESA empresa on
		empresa.ID = contrato.ID_EMPRESA;
	
/*****************************************************************************************************************************************************/
/*****************************************************************************************************************************************************/

create index NDX_LANCAMENTO_01 on TB_LANCAMENTO( CD_MES, CD_ANO );
create index NDX_LANCAMENTO_02 on TB_LANCAMENTO( ID_DEPENDENTE );
create index NDX_LANCAMENTO_03 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_DEPENDENTE );
create index NDX_LANCAMENTO_04 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_TITULAR );
create index NDX_LANCAMENTO_05 on TB_LANCAMENTO( CD_MES, CD_ANO, ID_CONTRATO);

create index NDX_DEPENDENTE_01 on TB_DEPENDENTE( ID_TITULAR );
create index NDX_DEPENDENTE_02 on TB_DEPENDENTE( NM_DEPENDENTE );
create index NDX_DEPENDENTE_03 on TB_DEPENDENTE( NR_MATRICULA );
create index NDX_DEPENDENTE_04 on TB_DEPENDENTE( ID_TITULAR, NR_MATRICULA );
create index NDX_DEPENDENTE_05 on TB_DEPENDENTE( NR_CPF );
create index NDX_DEPENDENTE_06 on TB_DEPENDENTE( ID_TITULAR, ID );

create index NDX_DESCONHECIDO_01 on TB_DESCONHECIDO( ID_CONTRATO, CD_MES, CD_ANO );
create index NDX_DESCONHECIDO_02 on TB_DESCONHECIDO( CD_MES, CD_ANO );

create index NDX_CONTRATO_01 ON TB_CONTRATO( CD_CONTRATO );

create index NDX_TITULAR_01 ON TB_CONTRATO( ID_EMPRESA );

/*****************************************************************************************************************************************************/
/*****************************************************************************************************************************************************/

create table TB_LOG(
	ID			bigint( 17 ) auto_increment,
	MSG			TEXT not null,
	DT_CREATED	timestamp not null,
	
	constraint PK_LOG primary key( ID ) 
);

create table TB_SCRIPT(
	ID				bigint( 17 ) auto_increment,
	NM_LAST_SCRIPT	varchar( 400 ) not null,
	DT_CREATED	timestamp not null,
	
	constraint PK_LOG primary key( ID ) 
);

/*****************************************************************************************************************************************************/
/*****************************************************************************************************************************************************/
