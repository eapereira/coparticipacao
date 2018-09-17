/**
 * Edson - 12/09/2018
 */


alter table TB_ARQUIVO_OUTPUT
	modify TP_ARQUIVO int( 3 ) not null;
	
alter table TB_ARQUIVO_OUTPUT
	drop index UN_ARQUIVO_OUTPUT,
	add constraint UN_ARQUIVO_OUTPUT unique key( ID_ARQUIVO_INPUT, TP_ARQUIVO );
	
	