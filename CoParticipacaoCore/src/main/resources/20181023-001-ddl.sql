/**
 * edson - 23/10/2018
 */

alter table TB_TITULAR
	drop column ID_EMPRESA,
    drop foreign key FK_TITULAR_03,
    drop index UN_TITULAR_01,
	add column ID_CONTRATO bigint( 17 ) not null,
	add constraint UN_TITULAR_01 unique key ( ID_CONTRATO, NR_MATRICULA ),
	add constraint FK_TITULAR_04 foreign key ( ID_CONTRATO ) references TB_CONTRATO( ID );
