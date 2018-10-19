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
		DESCR_PROFISSAO			varchar( 80 ) null,
		NR_MATRICULA_ESPECIAL	bigint( 17 ) null
);

alter table TB_DEPENDENTE
	add ( 
		VL_FATOR_MODERADOR 		numeric( 17, 2 ) null,
		DESCR_PROFISSAO			varchar( 80 ) null,
		NR_MATRICULA_ESPECIAL	bigint( 17 ) null
);
