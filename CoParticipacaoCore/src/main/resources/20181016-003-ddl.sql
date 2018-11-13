/**
 * edson - 16/10/2018
 */

alter table TB_OPERADORA
	modify column CD_OPERADORA varchar( 60 ) not null;

alter table TB_BENEFICIARIO_COLS
	add ( 
		ID_ARQUIVO_INPUT_SHEET_COLS_DEF bigint( 17 ) null,
		constraint FK_BENEFICIARIO_COLS_03 foreign key ( ID_ARQUIVO_INPUT_SHEET_COLS_DEF ) references TB_ARQUIVO_INPUT_SHEET_COLS_DEF( ID )
);

alter table TB_BENEFICIARIO_COLS
	modify ID_ARQUIVO_INPUT_COLS_DEF	bigint( 17 ) null;

/*************************************************************************************************************************************/
	
alter table TB_LANCAMENTO
	add(
		VL_REEMBOLSO	numeric( 17, 2 ) null,
		VL_PARTICIPACAO	numeric( 17, 2 ) null
);

alter table TB_TITULAR
	add ( 
		VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
		VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
		DESCR_PROFISSAO			varchar( 80 ) null,
		NR_MATRICULA_ESPECIAL	bigint( 17 ) null,
		NR_SUBFATURA			varchar( 20 ) null
);

alter table TB_DEPENDENTE
	add ( 
		VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
		VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
		DESCR_PROFISSAO			varchar( 80 ) null,
		NR_MATRICULA_ESPECIAL	bigint( 17 ) null,
		NR_SUBFATURA			varchar( 20 ) null
);

alter table TB_DESCONHECIDO
	add ( 
		VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
		VL_FATOR_MODERADOR_INSS numeric( 17, 2 ) null,
		DESCR_PROFISSAO			varchar( 80 ) null,
		NR_MATRICULA_ESPECIAL	bigint( 17 ) null,
		NR_SUBFATURA			varchar( 20 ) null
);

/*************************************************************************************************************************************/
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
