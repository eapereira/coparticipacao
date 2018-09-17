/**
 * Edson - 16/09/2018
 */

alter table TB_TITULAR_ISENTO
	add ID_CONTRATO	bigint( 17 ) not null,
	drop index UN_TITULAR_ISENTO,
	add constraint UN_TITULAR_ISENTO unique key ( ID_TITULAR, CD_MES, CD_ANO, TP_ISENTO ),
	add constraint FK_TITULAR_ISENTO_04 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID );
	
alter table TB_DEPENDENTE_ISENTO
	add ID_CONTRATO	bigint( 17 ) not null,
	drop index UN_DEPENDENTE_ISENTO,
	add constraint UN_DEPENDENTE_ISENTO unique key ( ID_DEPENDENTE, CD_MES, CD_ANO, TP_ISENTO ),	
	add constraint FK_DEPENDENTE_ISENTO_04 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID );
