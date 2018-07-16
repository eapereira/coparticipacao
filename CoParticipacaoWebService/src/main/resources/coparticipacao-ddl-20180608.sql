/*****************************************************************************************************************************************************/

/**
 * Edson - 08/06/2018
 */

drop view if exists VW_LANCAMENTO_TITULAR;
drop view if exists VW_LANCAMENTO_DETAIL_PRINCIPAL;
drop view if exists VW_LANCAMENTO_DETAIL_MATRICULA;
drop view if exists VW_LANCAMENTO_MUITO_FACIL_8CH5Y;
drop view if exists VW_LANCAMENTO_MUITO_FACIL_8CHE8;

drop table if exists TB_INPUT_BENEFICIARIO_BIND;
drop table if exists TB_INPUT_BENEFICIARIO;
drop table if exists TB_BENEFICIARIO_COLS_DEF;
drop table if exists TB_BENEFICIARIO;

drop table if exists TB_INPUT_DEPENDENTE_ISENTO;
drop table if exists TB_INPUT_TITULAR_ISENTO;
drop table if exists TB_INPUT_DEPENDENTE_ISENTO_COLS;
drop table if exists TB_INPUT_TITULAR_ISENTO_COLS;
drop table if exists TB_DEPENDENTE_ISENTO_COLS_DEF;
drop table if exists TB_TITULAR_ISENTO_COLS_DEF;
drop table if exists TB_DEPENDENTE_ISENTO;
drop table if exists TB_TITULAR_ISENTO;

drop table if exists TB_INPUT_DEPENDENTE;
drop table if exists TB_INPUT_TITULAR;
drop table if exists TB_INPUT_LANCAMENTO;
drop table if exists TB_DEPENDENTE_COLS_DEF; 
drop table if exists TB_TITULAR_COLS_DEF;
drop table if exists TB_LANCAMENTO_COLS_DEF;

drop table if exists TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO;
drop table if exists TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF;
drop table if exists TB_ARQUIVO_OUTPUT_DESCONHECIDO;
drop table if exists TB_DESCONHECIDO_DETAIL;
drop table if exists TB_DESCONHECIDO;

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

drop table if exists TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF;
drop table if exists TB_ARQUIVO_OUTPUT_SHEET;
drop table if exists TB_ARQUIVO_OUTPUT;
drop table if exists TB_VIEW_DESTINATION_COLS_DEF;
drop table if exists TB_VIEW_DESTINATION;
drop table if exists TB_ARQUIVO_INPUT_COLS_DEF;
drop table if exists TB_ARQUIVO_INPUT;

drop table if exists TB_CONTRATO;

drop table if exists TB_PARAMETER;

drop table if exists TB_EMPRESA;
drop table if exists TB_OPERADORA;
drop table if exists TB_USER;

/*****************************************************************************************************************************************************/

create table TB_USER(
	ID 				bigint( 17 ) auto_increment,
	NM_LOGIN		varchar( 60 ) not null,
	DESCR_NAME		varchar( 60 ) not null,
	DESCR_PASSWD	varchar( 200 ) not null,
	TP_STATUS		int( 3 ) not null, /* 0 = ativo, 1 = bloqueado */

	USER_CREATED	bigint( 17 ),
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,
	
	constraint PK_USER primary key( ID )
);

create table TB_OPERADORA(
	ID 					bigint( 17 ) auto_increment,
	NM_OPERADORA		varchar( 200 ) not null,
	
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_OPERADORA primary key( ID ),
	
	constraint FK_OPERADORA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_OPERADORA_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_EMPRESA(
	ID 					bigint( 17 ) auto_increment,
	ID_OPERADORA		bigint( 17 ) not null,
	NM_EMPRESA			varchar( 200 ) not null,	
	
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EMPRESA primary key( ID ),
	
	constraint FK_EMPRESA_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EMPRESA_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EMPRESA_03 foreign key( ID_OPERADORA ) references TB_OPERADORA( ID )
);

create table TB_PARAMETER(
	ID 					bigint( 17 ) auto_increment,
	
	ID_EMPRESA			bigint( 17 ) not null,
	NM_PARAMETER		varchar( 200 ) not null,
	VL_PARAMETER		varchar( 4000 ) not null,
	
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
	NM_CONTRATO			varchar( 200 ) not null,
	
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
	
	CD_REGEXP_CONTRATO		int( 3 ) not null,
	CD_REGEXP_DIA			int( 3 ) null,
	CD_REGEXP_MES			int( 3 ) not null,
	CD_REGEXP_ANO			int( 3 ) not null,
	
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
	
	USER_CREATED	bigint( 17 ) not null,
	USER_ALTERED 	bigint( 17 ),
	DT_CREATED		timestamp not null,
	DT_ALTERED		timestamp not null,
	
	constraint PK_VIEW_DESTINATION primary key( ID ),
	
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

create table TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID 								bigint( 17 ) auto_increment,
	ID_ARQUIVO_OUTPUT_SHEET			bigint( 17 ) not null,
	ID_VIEW_DESTINATION_COLS_DEF 	bigint( 17 ) not null,
	
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,
	
	constraint PK_ARQUIVO_OUTPUT_SHEET_COLS_DEF primary key( ID ),
	
	constraint FK_ARQUIVO_OUTPUT_SHEET_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_COLS_DEF_03 foreign key( ID_ARQUIVO_OUTPUT_SHEET ) references TB_ARQUIVO_OUTPUT_SHEET( ID ),
	constraint FK_ARQUIVO_OUTPUT_SHEET_COLS_DEF_04 foreign key( ID_VIEW_DESTINATION_COLS_DEF ) references TB_VIEW_DESTINATION_COLS_DEF( ID )
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
	NM_TITULAR				varchar( 200 ) not null,
	NR_CPF					char( 11 ),
	DT_NASCIMENTO			date,
	DT_ADMISSAO				date,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR primary key( ID ),
	
	constraint FK_TITULAR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )
);

create table TB_DEPENDENTE(
	ID 						bigint( 17 ) auto_increment,
	ID_TITULAR				bigint( 17 ) not null,
	NR_MATRICULA			bigint( 17 ) not null,
	TP_DEPENDENTE			int( 3 ) not null, /* 0 = CONJUGE, 1 = FILHOS, 2 = PAIS */
	NM_DEPENDENTE			varchar( 200 ) not null,
	NR_CPF					char( 11 ),
	DT_NASCIMENTO			date,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_DEPENDENTE primary key( ID ),
	
	constraint FK_DEPENDENTE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID )
);

create table TB_LANCAMENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_CONTRATO				bigint( 17 ) not null,
	ID_TITULAR				bigint( 17 ) not null,
	ID_DEPENDENTE			bigint( 17 ) null,
	CD_MES					int( 2 ) not null,
	CD_ANO					int( 5 ) not null,
	
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
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,		
	
	constraint PK_LANCAMENTO_DETAIL primary key( ID ),
	
	constraint UN_LANCAMENTO_DETAIL unique key( ID_LANCAMENTO, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_LANCAMENTO_DETAIL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_DETAIL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_DETAIL_03 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);


create table TB_LANCAMENTO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_LANCAMENTO_COLS_DEF primary key( ID ),
	
	constraint UN_LANCAMENTO_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_LANCAMENTO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_LANCAMENTO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_TITULAR_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_TITULAR_COLS_DEF primary key( ID ),
	
	constraint UN_TITULAR_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_TITULAR_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_DEPENDENTE_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_DEPENDENTE_COLS_DEF primary key( ID ),
	
	constraint UN_DEPENDENTE_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_DEPENDENTE_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

/***********************************************************************************************************************/
/* Isento */

create table TB_TITULAR_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_TITULAR				bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* */
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_TITULAR_ISENTO primary key( ID ),
	
	constraint FK_TITULAR_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_TITULAR_ISENTO_03 foreign key( ID_TITULAR ) references TB_TITULAR( ID )
);

create table TB_DEPENDENTE_ISENTO(
	ID 						bigint( 17 ) auto_increment,
	ID_DEPENDENTE			bigint( 17 ) not null,
	TP_ISENTO				int( 3 ) not null, /* */
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,		
	
	constraint PK_DEPENDENTE_ISENTO primary key( ID ),
	
	constraint FK_DEPENDENTE_ISENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DEPENDENTE_ISENTO_03 foreign key( ID_DEPENDENTE ) references TB_DEPENDENTE( ID )
);

create table TB_TITULAR_ISENTO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
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
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	ID_TITULAR_ISENTO_COLS_DEF	bigint( 17 ) not null,
		
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
	ID_ARQUIVO_INPUT_COLS_DEF		bigint( 17 ) not null,
	ID_DEPENDENTE_ISENTO_COLS_DEF	bigint( 17 ) not null,
		
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
	constraint FK_INPUT_DEPENDENTE_ISENTO_COLS_05 foreign key( ID_INPUT_DEPENDENTE_ISENTO ) references TB_DEPENDENTE_ISENTO( ID )
);

/***********************************************************************************************************************/

create table TB_INPUT_LANCAMENTO(
	ID 							bigint( 17 ) auto_increment,
	ID_LANCAMENTO_COLS_DEF		bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_INPUT_LANCAMENTO primary key( ID ),
	
	constraint UN_INPUT_LANCAMNETO_01 unique key( ID_LANCAMENTO_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_INPUT_LANCAMENTO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_LANCAMENTO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_LANCAMENTO_03 foreign key( ID_LANCAMENTO_COLS_DEF ) references TB_LANCAMENTO_COLS_DEF( ID ),
	constraint FK_INPUT_LANCAMENTO_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_INPUT_TITULAR(
	ID 							bigint( 17 ) auto_increment,
	ID_TITULAR_COLS_DEF			bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_INPUT_TITULAR primary key( ID ),
	
	constraint UN_INPUT_TITULAR_01 unique key( ID_TITULAR_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_INPUT_TITULAR_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_TITULAR_03 foreign key( ID_TITULAR_COLS_DEF ) references TB_TITULAR_COLS_DEF( ID ),
	constraint FK_INPUT_TITULAR_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_INPUT_DEPENDENTE(
	ID 							bigint( 17 ) auto_increment,
	ID_DEPENDENTE_COLS_DEF		bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_INPUT_DEPENDENTE primary key( ID ),
	
	constraint UN_INPUT_DEPENDENTE_01 unique key( ID_DEPENDENTE_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_INPUT_DEPENDENTE_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_DEPENDENTE_03 foreign key( ID_DEPENDENTE_COLS_DEF ) references TB_DEPENDENTE_COLS_DEF( ID ),
	constraint FK_INPUT_DEPENDENTE_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_DESCONHECIDO(
	ID 							bigint( 17 ) auto_increment,
	ID_CONTRATO					bigint( 17 ) not null,
	CD_MES						int( 3 ) not null,
	CD_ANO						int( 3 ),
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_DESCONHECIDO primary key( ID ),
		
	constraint FK_DESCONHECIDO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_03 foreign key( ID_CONTRATO ) references TB_CONTRATO( ID )
);

create table TB_DESCONHECIDO_DETAIL(
	ID 							bigint( 17 ) auto_increment,
	ID_DESCONHECIDO				bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	VL_DOUBLE					numeric( 17, 2 ) null,
	VL_INT						int( 10 ) null,
	VL_DATE						date null,
	VL_LONG						bigint( 17 ) null,
	VL_STRING					varchar( 500 ),
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,		
	
	constraint PK_DESCONHECIDO_DETAIL primary key( ID ),
	
	constraint UN_DESCONHECIDO_DETAIL unique key( ID_DESCONHECIDO, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_DESCONHECIDO_DETAIL_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_DETAIL_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_DESCONHECIDO_DETAIL_03 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID )
);

create table TB_ARQUIVO_OUTPUT_DESCONHECIDO(
	ID 					bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT	bigint( 17 ) not null,
	
	NM_ARQUIVO_FORMAT	varchar( 200 ) not null, /* pode colocar {CC} = contrato, {DD} para dia, {MM} para mês e {YYYY} para ano */
	NM_DESCR_ARQUIVO	varchar( 200 ) not null,
	
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

create table TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID 								bigint( 17 ) auto_increment,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO	bigint( 17 ) not null,

	NM_COLUMN						varchar( 60 ) not null,
	CD_TYPE							int( 3 ) not null, 	/* 0 = INT, 1 = VARCHAR, 2 = DATE, 3, DATETIME, 4 = DOUBLE, 5 = CLOB */
	VL_LENGTH						int( 5 ) null, 		/* arquivos CSV nao precisa de tamanho de coluna */
	CD_ORDEM						int( 3 ) not null,
	CD_FORMAT						varchar( 200 ) null, /* Para usar com datas e números */

	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,
	
	constraint PK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF primary key( ID ),
		
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID ),	
	constraint FK_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF_03 foreign key( ID_ARQUIVO_OUTPUT_DESCONHECIDO ) references TB_ARQUIVO_OUTPUT_DESCONHECIDO( ID )
);

create table TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID 										bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT_COLS_DEF				bigint( 17 ) not null,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF	bigint( 17 ) not null,
		
	USER_CREATED							bigint( 17 ) not null,
	USER_ALTERED 							bigint( 17 ),
	DT_CREATED								timestamp not null,
	DT_ALTERED								timestamp not null,	
	
	constraint PK_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO primary key( ID ),
	
	constraint UN_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO unique key( ID_ARQUIVO_INPUT_COLS_DEF, ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF ),
	
	constraint FK_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID ),
	constraint FK_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO_05 foreign key( ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF ) references TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF( ID )
);

create table TB_BENEFICIARIO(
	ID 								bigint( 17 ) auto_increment,
	NR_MATRICULA					bigint( 17 ) null,
	NM_TITULAR						varchar( 200 ) null,
	NR_CPF							char( 11 ) null,	
	NM_BENEFICIARIO					varchar( 200 ) null,
	
	USER_CREATED					bigint( 17 ) not null,
	USER_ALTERED 					bigint( 17 ),
	DT_CREATED						timestamp not null,
	DT_ALTERED						timestamp not null,

	constraint PK_BENEFICIARIO primary key( ID ),
	
	constraint FK_BENEFICIARIO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_02 foreign key( USER_ALTERED ) references TB_USER( ID )
	
);

create table TB_BENEFICIARIO_COLS_DEF(
	ID 						bigint( 17 ) auto_increment,
	NM_COLUMN				varchar( 60 ),
	CD_TYPE					int( 3 ) not null, /* 1 = INT, 2 = DOUBLE, 3 = VARCHAR, 4 = DATE, 5 = LONG */
	VL_LENGTH				int( 5 ) not null,
	
	USER_CREATED			bigint( 17 ) not null,
	USER_ALTERED 			bigint( 17 ),
	DT_CREATED				timestamp not null,
	DT_ALTERED				timestamp not null,
	
	constraint PK_BENEFICIARIO_COLS_DEF primary key( ID ),
	
	constraint UN_BENEFICIARIO_COLS_DEF_01 unique key( NM_COLUMN ),
	
	constraint FK_BENEFICIARIO_COLS_DEF_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_BENEFICIARIO_COLS_DEF_02 foreign key( USER_ALTERED ) references TB_USER( ID )
);

create table TB_INPUT_BENEFICIARIO(
	ID 							bigint( 17 ) auto_increment,
	ID_ARQUIVO_INPUT			bigint( 17 ) not null,
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_INPUT_BENEFICIARIO primary key( ID ),
	
	constraint UN_INPUT_BENEFICIARIO_01 unique key( ID_ARQUIVO_INPUT ),
	
	constraint FK_INPUT_BENEFICIARIO_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_BENEFICIARIO_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_BENEFICIARIO_03 foreign key( ID_ARQUIVO_INPUT ) references TB_ARQUIVO_INPUT( ID )
);

create table TB_INPUT_BENEFICIARIO_BIND(
	ID 							bigint( 17 ) auto_increment,
	ID_INPUT_BENEFICIARIO		bigint( 17 ) not null,
	
	ID_BENEFICIARIO_COLS_DEF	bigint( 17 ) not null,
	ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) not null,
	
	USER_CREATED				bigint( 17 ) not null,
	USER_ALTERED 				bigint( 17 ),
	DT_CREATED					timestamp not null,
	DT_ALTERED					timestamp not null,
	
	constraint PK_INPUT_BENEFICIARIO_BIND primary key( ID ),
	
	constraint UN_INPUT_BENEFICIARIO_BIND_01 unique key( ID_INPUT_BENEFICIARIO, ID_BENEFICIARIO_COLS_DEF, ID_ARQUIVO_INPUT_COLS_DEF ),
	
	constraint FK_INPUT_BENEFICIARIO_BIND_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_INPUT_BENEFICIARIO_BIND_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_INPUT_BENEFICIARIO_BIND_03 foreign key( ID_BENEFICIARIO_COLS_DEF ) references TB_BENEFICIARIO_COLS_DEF( ID ),
	constraint FK_INPUT_BENEFICIARIO_BIND_04 foreign key( ID_ARQUIVO_INPUT_COLS_DEF ) references TB_ARQUIVO_INPUT_COLS_DEF( ID ),
	constraint FK_INPUT_BENEFICIARIO_BIND_05 foreign key( ID_INPUT_BENEFICIARIO ) references TB_INPUT_BENEFICIARIO( ID )
);

/*****************************************************************************************************************************************************/

create view VW_LANCAMENTO_TITULAR as
select 
	lancamento.ID,
	titular.ID_EMPRESA,
	lancamento.ID_CONTRATO,
	lancamento.CD_MES,
	lancamento.CD_ANO,	
    titular.NM_TITULAR,
    LPAD( titular.NR_CPF, 11, '0') NR_CPF
from TB_LANCAMENTO lancamento,
	TB_TITULAR titular
where lancamento.ID_TITULAR = titular.id;
    
create view VW_LANCAMENTO_DETAIL_PRINCIPAL as
select
	detail.ID_LANCAMENTO ID,
	sum( detail.VL_DOUBLE ) VL_PRINCIPAL
from TB_LANCAMENTO_DETAIL detail
where detail.ID_ARQUIVO_INPUT_COLS_DEF in ( 14, 37 )
group by detail.ID_LANCAMENTO;

create view VW_LANCAMENTO_DETAIL_MATRICULA as
select
	detail.ID_LANCAMENTO ID,
	detail.VL_LONG NR_MATRICULA
from TB_LANCAMENTO_DETAIL detail
where detail.ID_ARQUIVO_INPUT_COLS_DEF in ( 22, 45 )
group by detail.ID_LANCAMENTO, detail.VL_LONG;

create view VW_LANCAMENTO_MUITO_FACIL_8CH5Y as
select
	lanc_titular.ID_CONTRATO,
	lanc_titular.CD_MES,
	lanc_titular.CD_ANO,
	lanc_titular.NM_TITULAR,
	lanc_titular.NR_CPF,
	lanc_matricula.NR_MATRICULA,    
	sum( lanc_principal.VL_PRINCIPAL ) VL_PRINCIPAL
from VW_LANCAMENTO_TITULAR lanc_titular,
	VW_LANCAMENTO_DETAIL_PRINCIPAL lanc_principal,
	VW_LANCAMENTO_DETAIL_MATRICULA lanc_matricula
where	lanc_titular.ID 			= lanc_principal.ID
and 	lanc_titular.ID 			= lanc_matricula.ID
and		lanc_titular.ID_CONTRATO 	= 1
group by 	lanc_titular.ID,
	lanc_titular.ID_EMPRESA,
	lanc_titular.CD_MES,
	lanc_titular.CD_ANO,
	lanc_titular.NM_TITULAR,
	lanc_titular.NR_CPF,
	lanc_matricula.NR_MATRICULA
order by lanc_titular.NM_TITULAR;

create view VW_LANCAMENTO_MUITO_FACIL_8CHE8 as
select
	lanc_titular.ID_CONTRATO,
	lanc_titular.CD_MES,
	lanc_titular.CD_ANO,
	lanc_titular.NM_TITULAR,
	lanc_titular.NR_CPF,
	lanc_matricula.NR_MATRICULA,    
	sum( lanc_principal.VL_PRINCIPAL ) VL_PRINCIPAL
from VW_LANCAMENTO_TITULAR lanc_titular,
	VW_LANCAMENTO_DETAIL_PRINCIPAL lanc_principal,
	VW_LANCAMENTO_DETAIL_MATRICULA lanc_matricula
where	lanc_titular.ID 			= lanc_principal.ID
and 	lanc_titular.ID 			= lanc_matricula.ID
and		lanc_titular.ID_CONTRATO 	= 2
group by 	lanc_titular.ID,
	lanc_titular.ID_CONTRATO,
	lanc_titular.CD_MES,
	lanc_titular.CD_ANO,
	lanc_titular.NM_TITULAR,
	lanc_titular.NR_CPF,
	lanc_matricula.NR_MATRICULA
order by lanc_titular.NM_TITULAR;

/*****************************************************************************************************************************************************/

/*****************************************************************************************************************************************************/

