/**
 * Edson - 29/09/2018
 */

alter table TB_EMPRESA
	add TP_REPORT_QUERY int( 3 ) null;
	
/**
 * 0 - QUERY_BY_CONTRATO		- Usar ID_CONTRATO nas queryes p/ criar as planilhas de saída;
 * 1 - QUERY_BY_PERIODO_ONLY	- Usar apenas mês e ano;
 */
