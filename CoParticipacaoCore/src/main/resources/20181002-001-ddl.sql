/**
 * Edson - 02/10/2018
 */

alter table TB_USER
	add constraint UN_USER unique key ( NM_LOGIN );
