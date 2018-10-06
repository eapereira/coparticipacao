/**
 * Edson - 27/09/2018
 */

alter table TB_CONTRATO
	change column TP_USO TP_USE int( 3 ) not null;
		
/**********************************************************************************************************************************************************/
alter table TB_EMPRESA
add (
	CD_FAILURE_DIR	varchar( 800 ) null,
	CD_OUTPUT_DIR	varchar( 800 ) null
);