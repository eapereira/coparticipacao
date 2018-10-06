/**
 * Edson - 27/09/2018
 */

alter table TB_EMPRESA
	modify column CD_FAILURE_DIR varchar( 800 ) not null;

alter table TB_EMPRESA
	modify column CD_OUTPUT_DIR	varchar( 800 ) not null;
