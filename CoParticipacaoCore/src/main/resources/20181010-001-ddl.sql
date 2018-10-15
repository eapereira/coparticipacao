/**
 * edson - 10/10/2018
 */

alter table TB_CONTRATO
	add (
		ID_CONTRATO_PARENT bigint( 17 ) null,
		constraint FK_CONTRATO_04 foreign key ( ID_CONTRATO_PARENT ) references TB_CONTRATO( ID ));
		
