/*****************************************************************************************************************************************************/

/**
 * Edson - 08/06/2018
 */

drop table if exists TB_SCRIPT;
drop table if exists TB_LOG;

drop table if exists TB_BENEFICIARIO_COLS;

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

/*****************************************************************************************************************************************************/

create table TB_USER(
	ID 				bigint( 17 ) auto_increment,
	NM_LOGIN		varchar( 60 ) not null,
	DESCR_NAME		varchar( 60 ) not null,
	CD_PASSWD		varchar( 200 ) not null,
	TP_STATUS		int( 3 ) not null, /* 0 = ativo, 1 = bloqueado */
	

	VERSION		bigint( 17 ) null,
 
	USER_CREATED	bigint( 17 ),
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,
	
	constraint PK_USER primary key( ID )
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
	NM_OPERADORA		varchar( 200 ) not null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_OPERADORA primary key( ID ),
	
	constraint UN_OPERADORA unique key( NM_OPERADORA ),
	
	constraint FK_OPERADORA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_OPERADORA_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_EMPRESA(
	ID 					bigint( 17 ) auto_increment,
	ID_OPERADORA		bigint( 17 ) not null,
	NM_EMPRESA			varchar( 200 ) not null,
	CD_EMPRESA			varchar( 200 ) not null,
	
	CD_AUTOMATIC_CREATE_BENEFICIARIO	int( 3 ) not null default 0,
	CD_INPUT_DIR 						varchar( 4000 ) not null,
	CD_OUTPUT_REPORT_DIR 				varchar( 4000 ) not null,
	
	TP_SAVE_MECSAS_DETAIL				int( 1 ) not null,
	TP_SAVE_BENEFICIARIO_DETAIL			int( 1 ) not null,
        
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

create table TB_PARAMETER(
	ID 					bigint( 17 ) auto_increment,
	
	ID_EMPRESA			bigint( 17 ) not null,
	NM_PARAMETER		varchar( 200 ) not null,
	VL_PARAMETER		varchar( 4000 ) not null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_PARAMETER primary key( ID ),
	
	constraint UN_PARAMETER unique key( ID_EMPRESA, NM_PARAMETER ),
	
	constraint FK_PARAMETER_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_PARAMETER_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_PARAMETER_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )	
);

create table TB_CONTRATO(
	ID 					bigint( 17 ) auto_increment,
	ID_EMPRESA			bigint( 17 ) not null,
	CD_CONTRATO			varchar( 60 ) not null,
	NM_CONTRATO			varchar( 60 ) not null,
	DESCR_CONTRATO		varchar( 400 ) not null,
	TP_USO				int( 3 ) not null,
	
	/* notifica o processo para ler todas as pastas de uma planilha de entrada */
	CD_SPREADSHEET_ALL_PAGES int( 3 ) null, 
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_CONTRATO primary key( ID ),
	
	constraint UN_CONTRATO unique key( ID_EMPRESA, CD_CONTRATO ),
	
	constraint FK_CONTRATO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_CONTRATO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_CONTRATO_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
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
	
	VERSION				bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,	
	
	constraint PK_ARQUIVO_OUTPUT primary key( ID ),
	
	constraint UN_ARQUIVO_OUTPUT unique key( ID_ARQUIVO_INPUT ),
	
	constraint FK_ARQUIVO_OUTPUT_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_ARQUIVO_OUTPUT_SHEET(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_OUTPUT	bigint( 17 ) not null,
	ID_VIEW_DESTINATION	bigint( 17 ) not null,
	NM_SHEET			varchar( 60 ) not null,
	
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

create table TB_ARQUIVO_EXECUCAO(
	ID 					bigint( 17 ) auto_increment,
	ID_CONTRATO			bigint( 17 ) not null,
	CD_MES				int( 3 ) not null,
	CD_ANO				int( 3 ) not null,
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
	constraint FK_ARQUIVO_EXECUCAO_03 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

/*****************************************************************************************************************************************************/

/**
 * Regra
 */
 
create table TB_REGRA (
	ID 					bigint( 17 ) auto_increment,
	NM_REGRA			varchar( 400 ) not null,
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
	ID_EMPRESA				bigint( 17 ) not null,
	NR_MATRICULA			bigint( 17 ) not null,
	NR_MATRICULA_EMPRESA	bigint( 17 ) null,
	NM_TITULAR				varchar( 200 ) CHARACTER SET latin1 COLLATE latin1_bin not null,
	NR_CPF					bigint( 17 ) not null,
	DT_NASCIMENTO			date null,
	DT_ADMISSAO				date null,
	DT_DEMISSAO 			date null,

	NM_LABEL				varchar( 400 ) null,
	NR_REF_CODE				bigint( 17 ) null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR primary key( ID ),
	
	constraint UN_TITULAR unique key( NR_CPF ),
	
	constraint FK_TITULAR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
);

create table TB_TITULAR_DETAIL(
	ID 							bigint( 17 ) auto_increment,
	ID_TITULAR					bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	VL_DOUBLE					numeric( 17, 2 ) null,
	VL_INT						int( 10 ) null,
	VL_DATE						date null,
	VL_LONG						bigint( 17 ) null,
	VL_STRING					varchar( 500 ),
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,		
	
	constraint PK_TITULAR_DETAIL primary key( ID ),
	
	constraint UN_TITULAR_DETAIL unique key( ID_TITULAR, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_TITULAR_DETAIL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_DETAIL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_DETAIL_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID ),
	constraint FK_TITULAR_DETAIL_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
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
	
	VERSION					bigint( 17 ) null,
 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_DEPENDENTE primary key( ID ),
	
	constraint UN_DEPENDENTE_01 unique key( ID_TITULAR, NM_DEPENDENTE ),
	constraint UN_DEPENDENTE_02 unique key( NM_DEPENDENTE, NR_CPF ),
	
	constraint FK_DEPENDENTE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID )
);

create table TB_DEPENDENTE_DETAIL(
	ID 							bigint( 17 ) auto_increment,
	ID_DEPENDENTE				bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	VL_DOUBLE					numeric( 17, 2 ) null,
	VL_INT						int( 10 ) null,
	VL_DATE						date null,
	VL_LONG						bigint( 17 ) null,
	VL_STRING					varchar( 500 ),
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,		
	
	constraint PK_DEPENDENTE_DETAIL primary key( ID ),
	
	constraint UN_DEPENDENTE_DETAIL unique key( ID_DEPENDENTE, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_DEPENDENTE_DETAIL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_DETAIL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_DETAIL_03 foreign key( ID_DEPENDENTE ) references TB_DEPENDENTE( ID ),
	constraint FK_DEPENDENTE_DETAIL_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);


create table TB_LANCAMENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_CONTRATO				bigint( 17 ) not null,
	ID_TITULAR				bigint( 17 ) not null,
	ID_DEPENDENTE			bigint( 17 ) null,
	CD_MES					int( 2 ) not null,
	CD_ANO					int( 5 ) not null,
	VL_PRINCIPAL			numeric( 17, 2 ) null,
	
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

create table TB_LANCAMENTO_DETAIL(
	ID 							bigint( 17 ) auto_increment,
	ID_LANCAMENTO				bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	VL_DOUBLE					numeric( 17, 2 ) null,
	VL_INT						int( 10 ) null,
	VL_DATE						date null,
	VL_LONG						bigint( 17 ) null,
	VL_STRING					varchar( 500 ),
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,		
	
	constraint PK_LANCAMENTO_DETAIL primary key( ID ),
	
	constraint UN_LANCAMENTO_DETAIL unique key( ID_LANCAMENTO, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_LANCAMENTO_DETAIL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_DETAIL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_DETAIL_03 foreign key( ID_LANCAMENTO ) references TB_LANCAMENTO( ID ),
	constraint FK_LANCAMENTO_DETAIL_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
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

/***********************************************************************************************************************/
/* Isento */

create table TB_TITULAR_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_TITULAR				bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* */

	CD_MES					int( 3 ) not null,
	CD_ANO					int( 3 ) not null,
	VL_ISENCAO				numeric( 17, 2 ) null,

	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR_ISENTO primary key( ID ),
	
	constraint UN_TITULAR_ISENTO unique key ( ID_TITULAR, CD_MES, CD_ANO ),
	
	constraint FK_TITULAR_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID )
);

create table TB_DEPENDENTE_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_DEPENDENTE			bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* 1 = GRAVIDA,  */

	CD_MES					int( 3 ) not null,
	CD_ANO					int( 3 ) not null,
	VL_ISENCAO				numeric( 17, 2 ) null,
	
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_DEPENDENTE_ISENTO primary key( ID ),
	
	constraint UN_DEPENDENTE_ISENTO unique key ( ID_DEPENDENTE, CD_MES, CD_ANO ),
	
	constraint FK_DEPENDENTE_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_03 foreign key( ID_DEPENDENTE ) references TB_DEPENDENTE( ID )
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
	CD_MES						int( 3 ) not null,
	CD_ANO						int( 3 ),
	
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
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_BENEFICIARIO_COLS primary key( ID ),
	
	constraint UN_BENEFICIARIO_COLS_01 unique key( CD_BENEFICIARIO_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_BENEFICIARIO_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
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

	ID_ISENTO_INPUT_SHEET		bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	CD_BENEFICIARIO_COLS_DEF	int( 3 ) not null,
	CD_ORDEM					int( 3 ) not null,
	
	VERSION						bigint( 17 ) null,
 
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_TB_ISENTO_INPUT_SHEET_COLS primary key( ID ),
	
	constraint UN_TB_ISENTO_INPUT_SHEET_COLS_01 unique key( ID_ISENTO_INPUT_SHEET, ID_ARQUIVO_INPUT_COLS_DEF, CD_BENEFICIARIO_COLS_DEF ),
	
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_03 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID ),
	constraint FK_TB_ISENTO_INPUT_SHEET_COLS_04 foreign key( ID_ISENTO_INPUT_SHEET ) references TB_ISENTO_INPUT_SHEET( ID )

);

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

drop procedure if exists PROC_LOG_MESSAGE;
drop procedure if exists PROC_SHOW_LOG_MESSAGE;
drop procedure if exists PROC_VALIDATE_SCRIPT;
drop procedure if exists PROC_UPDATE_SCRIPT;
drop procedure if exists PROC_CLEAR_COPARTICIPACAO;

drop function if exists FUNC_DOUBLE_TO_LONG;
drop function if exists FUNC_GET_MATRICULA_HOC;
drop function if exists FUNC_GET_CPF;

delimiter $$

create procedure PROC_LOG_MESSAGE(PARAM_MESSAGE varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	if PARAM_MESSAGE is null then
		set PARAM_MESSAGE = '';
	end if;
	
	insert into TB_LOG(
		MSG,
		DT_CREATED
	) values (
		PARAM_MESSAGE,
		current_timestamp()
	);
END
$$

delimiter $$

create procedure PROC_SHOW_LOG_MESSAGE( )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	select MSG from TB_LOG
	order by ID desc;
	
	delete from TB_LOG
	where ID >= 1;
END
$$

delimiter $$

create procedure PROC_VALIDATE_SCRIPT( 
	PARAM_NM_SCRIPT varchar( 400 ), 
	PARAM_NM_CURRENT_SCRIPT varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	declare VAR_LAST_SCRIPT varchar( 400 );
    declare VAR_ERROR_MSG varchar( 400 ) default null;
    declare ERROR_SCRIPT CONDITION FOR SQLSTATE '45000';
	
	select script.NM_LAST_SCRIPT into VAR_LAST_SCRIPT from TB_SCRIPT script
	where script.ID = (
		select max( script_last.ID ) from TB_SCRIPT script_last );

	if VAR_LAST_SCRIPT = PARAM_NM_CURRENT_SCRIPT then
		set VAR_ERROR_MSG = concat( 'O script [ ', PARAM_NM_CURRENT_SCRIPT, ' ] já foi executado.' );		
	elseif VAR_LAST_SCRIPT <> PARAM_NM_SCRIPT then
		set VAR_ERROR_MSG = concat( 'É necessário executar o script [ ', PARAM_NM_SCRIPT, ' ] antes.' );		
	end if;

	if VAR_ERROR_MSG is not null then
		call PROC_LOG_MESSAGE( VAR_ERROR_MSG );
    
		signal ERROR_SCRIPT
			set MESSAGE_TEXT = VAR_ERROR_MSG; 	
	end if;
	
END
$$

delimiter $$

create procedure PROC_UPDATE_SCRIPT( PARAM_NM_SCRIPT varchar( 400 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para fazer log das mesagens de erro:'
BEGIN
	
	insert into TB_SCRIPT(
		NM_LAST_SCRIPT,
		DT_CREATED
	) values (
		PARAM_NM_SCRIPT,
		current_timestamp()
	);
	
END
$$

delimiter $$

create procedure PROC_CLEAR_COPARTICIPACAO( PARAM_ID_EMPRESA bigint( 17 ))
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para apagar todos os registros de uma Empresa no Coparticipação:'
BEGIN
	START TRANSACTION;
	
	IF param_id_empresa IS NULL then
		delete from TB_LANCAMENTO_DETAIL
		WHERE ID > 0;
		
		delete from TB_LANCAMENTO
		WHERE ID > 0;
		
		delete from TB_DEPENDENTE_ISENTO
		WHERE ID > 0;
		
		delete from TB_DEPENDENTE_DETAIL
		WHERE ID > 0;
		
		delete from TB_DEPENDENTE
		WHERE ID > 0;
				
		delete from TB_TITULAR_ISENTO
		WHERE ID > 0;

		delete from TB_TITULAR_DETAIL
		where ID > 0;
		
		delete from TB_TITULAR
		where ID > 0;
		
		delete from TB_DESCONHECIDO
		WHERE ID > 0;
	else
		delete detail from TB_LANCAMENTO_DETAIL detail
		WHERE detail.ID_LANCAMENTO in (
			select lancamento.ID
			from 	TB_LANCAMENTO lancamento
			where 	lancamento.ID = detail.ID_LANCAMENTO
			and		lancamento.ID_CONTRATO in (
				select contrato.ID
				from TB_CONTRATO contrato
				where	contrato.ID_EMPRESA = PARAM_ID_EMPRESA
				and		contrato.ID = lancamento.ID_CONTRATO ));
		
		delete lancamento from TB_LANCAMENTO lancamento
		WHERE lancamento.ID_CONTRATO in (
			select contrato.ID
			from TB_CONTRATO contrato
			where	contrato.ID_EMPRESA = PARAM_ID_EMPRESA
			and		contrato.ID = lancamento.ID_CONTRATO );
		
		delete isento from TB_DEPENDENTE_ISENTO isento
		WHERE isento.ID_DEPENDENTE in (
			select	dependente.ID
			from 	TB_DEPENDENTE dependente
			where	dependente.ID =  isento.ID_DEPENDENTE
			and		dependente.ID_TITULAR in (
				select	titular.ID
				from 	TB_TITULAR titular
				where 	titular.ID = dependente.ID_TITULAR
				and		titular.ID_EMPRESA = PARAM_ID_EMPRESA
			)
		);
		
		delete isento from TB_TITULAR_ISENTO isento
		where isento.ID_TITULAR in (
			select	titular.ID
			from 	TB_TITULAR titular
			where 	titular.ID = isento.ID_TITULAR
			and		titular.ID_EMPRESA = PARAM_ID_EMPRESA			
		);
		
		delete detail from TB_DEPENDENTE_DETAIL detail
		where detail.ID_DEPENDENTE in (
			select	dependente.ID
			from 	TB_DEPENDENTE dependente
			where	dependente.ID =  detail.ID_DEPENDENTE
			and		dependente.ID_TITULAR in (
				select	titular.ID
				from 	TB_TITULAR titular
				where 	titular.ID = dependente.ID_TITULAR
				and		titular.ID_EMPRESA = PARAM_ID_EMPRESA
			)
		);
		
		delete dependente from TB_DEPENDENTE dependente
		where dependente.ID_TITULAR in (
			select	titular.ID
			from 	TB_TITULAR titular
			where 	titular.ID = dependente.ID_TITULAR
			and		titular.ID_EMPRESA = PARAM_ID_EMPRESA					
		);
		
		delete detail from TB_TITULAR_DETAIL detail
		where detail.ID_TITULAR in (
			select	titular.ID
			from 	TB_TITULAR titular
			where 	titular.ID = detail.ID_TITULAR
			and		titular.ID_EMPRESA = PARAM_ID_EMPRESA			
		);
		
		delete titular from TB_TITULAR titular
		where titular.ID_EMPRESA = PARAM_ID_EMPRESA;
				
		delete desconhecido from TB_DESCONHECIDO desconhecido
		where desconhecido.ID > 0;	
	end if;
	
	commit;
END
$$

delimiter $$

#Se precisar execute essa linha como o ROOT:
#set global log_bin_trust_function_creators = 1;

create function FUNC_DOUBLE_TO_LONG( PARAM_VALUE numeric( 17, 2 ))
returns bigint( 17 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Procedure para apagar todos os registros de uma Empresa no Coparticipação:'
BEGIN
	declare VAR_RESULT bigint( 17 ) default 0;
	
	if PARAM_VALUE  is not null then
		set VAR_RESULT = convert( replace( cast( PARAM_VALUE as char( 20 )), '.', '' ), signed integer );
	end if;
	
	return VAR_RESULT;
END
$$

delimiter $$

create function FUNC_GET_MATRICULA_HOC( PARAM_NR_MATRICULA bigint( 17 ), PARAM_CD_RDP int( 3 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 				varchar( 20 ) default '';
	declare VAR_NR_MATRICULA_BASE	bigint( 17 ) default 44400000000;
    declare VAR_NR_MATRICULA_HOC 	bigint( 17 ) default 0;
	
	if PARAM_NR_MATRICULA  is not null then
		set VAR_NR_MATRICULA_HOC = (( VAR_NR_MATRICULA_BASE + PARAM_NR_MATRICULA ) * 1000 );
		
		if PARAM_CD_RDP is not null then
			set VAR_NR_MATRICULA_HOC = VAR_NR_MATRICULA_HOC + PARAM_CD_RDP; 
		else
			set VAR_NR_MATRICULA_HOC = VAR_NR_MATRICULA_HOC + 1;
		end if;
		
		set VAR_RESULT = lpad(cast( VAR_NR_MATRICULA_HOC as char ), 15, '0' );
		
		#Deve-se somar ao campo RDP do MECSAS:
	end if;
	
	return VAR_RESULT;
END
$$

delimiter $$

create function FUNC_GET_CPF( PARAM_NR_CPF bigint( 17 ))
returns varchar( 20 )
LANGUAGE SQL
DETERMINISTIC
SQL SECURITY DEFINER
COMMENT 'Function para criar os números de matricula usados pelo Hospital Oswaldo Cruz:'
BEGIN
	declare VAR_RESULT 	varchar( 20 ) default '';
	
	if PARAM_NR_CPF is not null then
		set VAR_RESULT = lpad(cast( PARAM_NR_CPF as char ), 11, '0' );
		
		#Deve-se somar ao campo RDP do MECSAS:
	end if;
	
	return VAR_RESULT;
END
$$

#call PROC_CLEAR_COPARTICIPACAO( 1010 ); 
/*****************************************************************************************************************************************************/
