/**
 * Edson - 16/09/2018
 */

alter table TB_REGRA
	add DESCR_REGRA varchar( 400 ) null,
	modify NM_REGRA varchar( 400 ) null;
	
alter table TB_REGRA
	drop index UN_REGRA,
	add constraint UN_REGRA unique key( NM_REGRA );
	
