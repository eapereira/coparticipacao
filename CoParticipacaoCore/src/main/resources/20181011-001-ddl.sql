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

alter table TB_TITULAR
	add constraint UN_TITULAR_01 unique key( ID_EMPRESA, NR_MATRICULA );
	
