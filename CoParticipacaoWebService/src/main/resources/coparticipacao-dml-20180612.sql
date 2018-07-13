/*****************************************************************************************************************************************************/

/**
 * Edson - 12/06/2018
 * DML
 */

insert into TB_USER ( 
	NM_LOGIN, 
	DESCR_NAME, 
	DESCR_PASSWD, 
	TP_STATUS, 
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (
	'admin',
	'Administrador do Sistema',
	'admin',
	1, /* ATIVO */
	null,
	current_timestamp(),
	current_timestamp()
);

update 	TB_USER
set 	USER_CREATED = 1
where 	ID = 1;

insert into TB_OPERADORA (
	NM_OPERADORA,	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	'Sulamerica',
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_EMPRESA (
	NM_EMPRESA,	
	ID_OPERADORA,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	'Muito Fácil Arrecadação e Recebimento Ltda.',
	1, /* Sulamerica */
	1,
	current_timestamp(),
	current_timestamp()
);

/*****************************************************************************************************************************************************/

/**
 * Muito Fácil
 */

insert into TB_CONTRATO(
	ID_EMPRESA,
	CD_CONTRATO,	
    NM_CONTRATO,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    1,
	'8CH5Y',
    'Contrato Muito Fácil',
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_CONTRATO(
	ID_EMPRESA,
    CD_CONTRATO,
	NM_CONTRATO,	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    1,
	'8CHE8',
    'Contrato Muito Fácil',
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_CONTRATO(
	ID_EMPRESA,
    CD_CONTRATO,
	NM_CONTRATO,	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    1,
	'111813',
    'Contrato MECSAS Muito Fácil',
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT(
	ID_CONTRATO,
	NM_ARQUIVO_REGEXP,
	DESCR_ARQUIVO,	
	TP_ARQUIVO,
	TP_USE,
	NUM_SKIP_LINES,
	NUM_DEFAULT_LINE_LENGTH,
	
	CD_REGEXP_CONTRATO,
	CD_REGEXP_DIA,
	CD_REGEXP_MES,
	CD_REGEXP_ANO,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    1,
	'^(8CH5Y)FATUCOPA\\.([0-9]{4})([0-9]{2})([0-9]{1,})(F)\\.(txt|TXT)',
	'Arquivo de carga de coparticipação',
	0, /* FLAT_FILE */
	1, /* FATUCOPA */
	1,
	182,
	
	1, /* REGEXP_CONTRATO */
	4, /* REGEXP_DIA */
	3, /* REGEXP_MES */
	2, /* REGEXP_ANO */
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_01',
	1, /* INT */
	2,
	1,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_02',
	1, /* INT */
	1,
	2,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_03',
	1, /* INT */
	4,
	3,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_04',
	1, /* INT */
	8,
	4,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_05',
	3, /* VARCHAR */
	2,
	5,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_06',
	1, /* INT */
	1,
	6,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_07_NOME_COMPLETO_DEPENDENTE',
	3, /* VARCHAR */
	32,
	7,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_08',
	3, /* VARCHAR */
	6,
	8,
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_09_CPF',
	5, /* LONG */
	12,
	9,
	'#0',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_10_MES',
	1, /* INT */
	2,
	10,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_11',
	3, /* VARCHAR */
	1,
	11,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_12_ANO',
	1, /* INT */
	4,
	12,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_13_POSITIVO_NEGATIVO',
	3, /* VARCHAR */
	1,
	13,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_14_VALOR_PRINCIPAL',
	2, /* DOUBLE */
	15,
	14,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_15_NR_CONTRATO',
	3, /* VARCHAR */
	5,
	15,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_16_DATA_NASCIMENTO',
	4, /* DATE */
	10,
	16,
	'dd/MM/yyyy',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_17',
	3, /* VARCHAR */
	4,
	17,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_18',
	3, /* VARCHAR */
	8,
	18,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_19',
	3, /* VARCHAR */
	2,
	19,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_20',
	3, /* VARCHAR */
	1,
	20,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_21_NOME_COMPLETO_TITULAR',
	3, /* VARCHAR */
	32,
	21,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_22_MATRICULA',
	5, /* LONG */
	8,
	22,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	1,
	'COLUMN_23',
	3, /* VARCHAR */
	21,
	23,
	1,
	current_timestamp(),
	current_timestamp()
);

/********************************************************************************************************************/
/* FATU-COPA - 8CHE8*/

insert into TB_ARQUIVO_INPUT(
	ID_CONTRATO,
	NM_ARQUIVO_REGEXP,
	DESCR_ARQUIVO,	
	TP_ARQUIVO,
	TP_USE,
	NUM_SKIP_LINES,
	NUM_DEFAULT_LINE_LENGTH,
	
	CD_REGEXP_CONTRATO,
	CD_REGEXP_DIA,
	CD_REGEXP_MES,
	CD_REGEXP_ANO,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    2,
	'^(8CHE8)FATUCOPA\\.([0-9]{4})([0-9]{2})([0-9]{1,})(F)\\.(txt|TXT)',
	'Arquivo de carga de coparticipação',
	0, /* FLAT_FILE */
	1, /* FATUCOPA */
	1,
	182,
	
	1, /* REGEXP_CONTRATO */
	4, /* REGEXP_DIA */
	3, /* REGEXP_MES */
	2, /* REGEXP_ANO */
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_01',
	1, /* INT */
	2,
	1,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_02',
	1, /* INT */
	1,
	2,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_03',
	1, /* INT */
	4,
	3,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_04',
	1, /* INT */
	8,
	4,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_05',
	3, /* VARCHAR */
	2,
	5,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_06',
	1, /* INT */
	1,
	6,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_07_NOME_COMPLETO_DEPENDENTE',
	3, /* VARCHAR */
	32,
	7,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_08',
	3, /* VARCHAR */
	6,
	8,
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_09_CPF',
	5, /* LONG */
	12,
	9,
	'#0',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_10_MES',
	1, /* INT */
	2,
	10,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_11',
	3, /* VARCHAR */
	1,
	11,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_12_ANO',
	1, /* INT */
	4,
	12,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_13_POSITIVO_NEGATIVO',
	3, /* VARCHAR */
	1,
	13,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_14_VALOR_PRINCIPAL',
	2, /* DOUBLE */
	15,
	14,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_15_NR_CONTRATO',
	3, /* VARCHAR */
	5,
	15,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_16_DATA_NASCIMENTO',
	4, /* DATE */
	10,
	16,
	'dd/MM/yyyy',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_17',
	3, /* VARCHAR */
	4,
	17,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_18',
	3, /* VARCHAR */
	8,
	18,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_19',
	3, /* VARCHAR */
	2,
	19,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_20',
	3, /* VARCHAR */
	1,
	20,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_21_NOME_COMPLETO_TITULAR',
	3, /* VARCHAR */
	32,
	21,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_22_MATRICULA',
	5, /* LONG */
	8,
	22,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	2,
	'COLUMN_23',
	3, /* VARCHAR */
	21,
	23,
	1,
	current_timestamp(),
	current_timestamp()
);

/*****************************************************************************************************************************************************/

insert into TB_ARQUIVO_INPUT(
	ID_CONTRATO,
	NM_ARQUIVO_REGEXP,
	DESCR_ARQUIVO,	
	TP_ARQUIVO,
	TP_USE,
	NUM_SKIP_LINES,
	NUM_DEFAULT_LINE_LENGTH,
	
	CD_REGEXP_CONTRATO,
	CD_REGEXP_MES,
	CD_REGEXP_ANO,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
    3,
	'^(MECSAS)\\-(EXPORT\\-DADOS)\\-(1781)\\-([0-9]{4})(([0-9]{2})([0-9]{2})\\-([0-9]{6}))\\.(csv|CSV)',
	'Arquivo de carga de coparticipação',
	1, /* CSV */
	2, /* MECSAS */
	2,
	null, /* Não é usado para arquivo CSV */
	
	8, /* REGEXP_CONTRATO */
	6, /* REGEXP_MES */
	4, /* REGEXP_ANO */
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUM_LINHA',
	1, /* INT */
	null,
	1,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COD_EMP',
	3, /* VARCHAR */
	null,
	2,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'MATRICULA',
	5, /* LONG */
	null,
	3,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DF',
	1, /* INT */
	null,
	4,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'RDP',
	1, /* INT */
	null,
	5,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'LOCAL',
	1, /* INT */
	null,
	6,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CATEGORIA',
	1, /* INT */
	null,
	7,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'SETOR',
	3, /* VARCHAR */
	null,
	8,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'ES',
	3, /* VARCHAR */
	null,
	9,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'PLANO',
	1, /* INT */
	null,
	10,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATA_ADESAO',
	4, /* DATE */
	null,
	11,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CPF',
	5, /* LONG */
	null,
	12,
	'#0',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NOME_BENEF',
	3, /* VARCHAR */
	null,
	13,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATA_NASC',
	4, /* DATE */
	null,
	14,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'SEXO',
	3, /* VARCHAR */
	null,
	15,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'PERMANENCIA',
	3, /* VARCHAR */
	null,
	16,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'GP',
	1, /* INT */
	null,
	17,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATA_ADM',
	4, /* VARCHAR */
	null,
	18,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATA_REF',
	4, /* DATE */
	null,
	19,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'BANCO',
	1, /* INT */
	null,
	20,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'AGENCIA',
	1, /* INT */
	null,
	21,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DG_AGEN',
	1, /* INT */
	null,
	22,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CONTA_CORRENTE',
	3, /* VARCHAR */
	null,
	23,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CPF_CONTA_CORRENTE',
	5, /* LONG */
	null,
	24,
	'#0',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NOME_TITULAR_CC',
	3, /* VARCHAR */
	null,
	25,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODCARDIF',
	3, /* VARCHAR */
	null,
	26,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUM_CEP',
	1, /* VARCHAR */
	null,
	27,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'TIPO_LOGRADOURO',
	3, /* VARCHAR */
	null,
	28,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'LOGRADOURO',
	3, /* VARCHAR */
	null,
	29,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUMERO',
	1, /* INT */
	null,
	30,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COMP_LOGRADOURO',
	3, /* VARCHAR */
	null,
	31,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'BAIRRO',
	3, /* VARCHAR */
	null,
	32,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'MUNICIPIO',
	3, /* VARCHAR */
	null,
	33,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'UF',
	3, /* VARCHAR */
	null,
	34,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'TEL_RESID',
	3, /* VARCHAR */
	null,
	35,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'TEL_COM',
	3, /* VARCHAR */
	null,
	36,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'TEL_CEL',
	3, /* VARCHAR */
	null,
	37,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NOMEDAMAEBENE',
	3, /* VARCHAR */
	null,
	38,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'RG',
	3, /* VARCHAR */
	null,
	39,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'ORGAOEMISSORG',
	3, /* VARCHAR */
	null,
	40,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'PAISEMISSORRG',
	3, /* VARCHAR */
	null,
	41,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATAEMISSAORG',
	4, /* DATE */
	null,
	42,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'ESTADORG',
	3, /* VARCHAR */
	null,
	43,
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'PIS',
	3, /* VARCHAR */
	null,
	44,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CNS',
	3, /* VARCHAR */
	null,
	45,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'E-MAIL',
	3, /* VARCHAR */
	null,
	46,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'GRAUESCOLARIDADE',
	1, /* INT */
	null,
	47,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'RENDAFAMILIAR',
	1, /* INT */
	null,
	48,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CDPROFISSAO',
	1, /* INT */
	null,
	49,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CDPAISDEORIGEM',
	1, /* INT */
	null,
	50,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DATAEXCLUSAO',
	4, /* DATE */
	null,
	51,
	'yyyyMMdd',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODMOVEXCLUSAO',
	1, /* INT */
	null,
	52,
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODOPERACAO',
	1, /* INT */
	null,
	53,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODEMPRESATRANSF',
	1, /* INT */
	null,
	54,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'MATRICULATRANSF',
	1, /* INT */
	null,
	55,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'LOCALTRANSF',
	3, /* VARCHAR */
	null,
	56,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CATTRANSF',
	1, /* INT */
	null,
	57,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'PLANOTRANSF',
	1, /* INT */
	null,
	58,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'MOTREMISSAO',
	3, /* VARCHAR */
	null,
	59,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	CD_FORMAT,
	
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CPFNOVOTITULAR',
	5, /* LONG */
	null,
	60,
	'#0',
	
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'QTDPERMAMESES',
	1, /* INT */
	null,
	61,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'RDPNOVOTITULAR',
	3, /* VARCHAR */
	null,
	62,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DTINICIOTRANSF',
	4, /* DATE */
	null,
	63,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COD STATUS',
	1, /* INT */
	null,
	64,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COD ERRO',
	1, /* INT */
	null,
	65,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COD DV',
	1, /* INT */
	null,
	66,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'BLOQ EMPR INADIMPLENCIA',
	3, /* VARCHAR */
	null,
	67,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CPT',
	3, /* VARCHAR */
	null,
	68,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'COD EMPRESA TITULAR',
	3, /* VARCHAR */
	null,
	69,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'MATRICULA 02',
	5, /* LONG */
	null,
	70,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DIFERENCIADOR DA MATRICULA DO TITULAR',
	3, /* VARCHAR */
	null,
	71,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUMERO DO TITULO DE ELEITOR',
	3, /* VARCHAR */
	null,
	72,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUMERO DO RIC',
	3, /* VARCHAR */
	null,
	73,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NUMERO DA DECLARACAO DE NASCIDO VIVO',
	3, /* VARCHAR */
	null,
	74,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CARTEIRA DE IDENTIFICACAO',
	3, /* VARCHAR */
	null,
	75,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE SEGURADO CONTRIBUTARIO',
	3, /* VARCHAR */
	null,
	76,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DOA CONDICAO DO EX-EMPREGADO',
	3, /* VARCHAR */
	null,
	77,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE PERMANENCIA NO PLANO',
	3, /* VARCHAR */
	null,
	78,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'QUANTIDADE DE MESES DE CONTRIBUICAO',
	3, /* VARCHAR */
	null,
	79,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'NOME COMPLETO DO BENEFICIARIO',
	3, /* VARCHAR */
	null,
	80,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE TITULAR REMIDO',
	3, /* VARCHAR */
	null,
	81,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'EMAIL DA SEGURADORA',
	3, /* VARCHAR */
	null,
	82,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE PORTABILIDADE 1',
	3, /* VARCHAR */
	null,
	83,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE PORTABILIDADE 2',
	3, /* VARCHAR */
	null,
	84,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'INDICADOR DE CARENCIA',
	3, /* VARCHAR */
	null,
	85,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODIGO DO PRODUTO',
	1, /* INT */
	null,
	86,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CODIGO DE IDENTIFICACAO DE PLANO ANTERIOR NA SAS',
	3, /* VARCHAR */
	null,
	87,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID1',
	3, /* VARCHAR */
	null,
	88,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID2',
	3, /* VARCHAR */
	null,
	89,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID3',
	3, /* VARCHAR */
	null,
	90,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID4',
	3, /* VARCHAR */
	null,
	91,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID5',
	3, /* VARCHAR */
	null,
	92,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID6',
	3, /* VARCHAR */
	null,
	93,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID7',
	3, /* VARCHAR */
	null,
	94,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID8',
	3, /* VARCHAR */
	null,
	95,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID9',
	3, /* VARCHAR */
	null,
	96,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CID10',
	3, /* VARCHAR */
	null,
	97,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'IBGE',
	3, /* VARCHAR */
	null,
	98,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'CBO',
	3, /* VARCHAR */
	null,
	99,
	1,
	current_timestamp(),
	current_timestamp()
);

insert into TB_ARQUIVO_INPUT_COLS_DEF(
	ID_ARQUIVO_INPUT,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	USER_CREATED, 
	DT_CREATED,
	DT_ALTERED ) values (	
	3,
	'DIF TRANSFERENCIA',
	3, /* VARCHAR */
	null,
	100,
	1,
	current_timestamp(),
	current_timestamp()
);

commit;

/*****************************************************************************************************************************************************/

insert into TB_TITULAR_ISENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_TITULAR',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_ISENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_ISENTO',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_ISENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_DEPENDENTE',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_ISENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_ISENTO',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_TITULAR',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_DEPENDENTE',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'ID_CONTRATO',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'CD_MES',
	1, /* INT */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'CD_ANO',
	1, /* INT */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_LANCAMENTO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'VL_PRINCIPAL',
	2, /* DOUBLE */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NM_MATRICULA',
	5, /* LONG */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NM_TITULAR',
	3, /* VARCHAR */
	17,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NR_CPF',
	5, /* LONG */
	11,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'DT_NASCIMENTO',
	4, /* DATE */
	10,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_TITULAR_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'DT_ADMISSAO',
	4, /* DATE */
	10,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'TP_DEPENDENTE',
	1, /* INT */
	3,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NM_DEPENDENTE',
	3, /* VARCHAR */
	3,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NR_CPF',
	5, /* LONG */
	3,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'DT_NASCIMENTO',
	4, /* DATE */
	10,
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_DEPENDENTE_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (	
	'NR_MATRICULA',
	5, /* LONG */
	15,
	
	1,
	current_timestamp(),
	current_timestamp()	
);

commit;

/*****************************************************************************************************************************************************/

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* ID_BENEFICIARIO */
	9, /* NR_CPF */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* ID_CONTRATO */
	15, /* NR_CONTRATO */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* CD_MES */
	10, /* NR_MES */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	5, /* CD_ANO */
	12, /* NR_ANO */
	1,
	current_timestamp(),
	current_timestamp()	
);

/*****************************************************************************************/
/* FATU-COPA.2 */

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* ID_BENEFICIARIO */
	32, /* NR_CPF */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* ID_CONTRATO */
	38, /* NR_CONTRATO */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* CD_MES */
	33, /* NR_MES */
	1,
	current_timestamp(),
	current_timestamp()	
);

insert into TB_INPUT_LANCAMENTO (
	ID_LANCAMENTO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	5, /* CD_ANO */
	35, /* NR_ANO */
	1,
	current_timestamp(),
	current_timestamp()	
);

/*****************************************************************************************************************************************************/

/* MECSAS */

insert into TB_INPUT_TITULAR(
	ID_TITULAR_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	49, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_TITULAR(
	ID_TITULAR_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	59, /* NM_TITULAR */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_TITULAR(
	ID_TITULAR_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3,
	58, /* NR_CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_TITULAR(
	ID_TITULAR_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4,
	60, /* DT_NASCIMENTO */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_TITULAR(
	ID_TITULAR_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	5,
	64, /* DT_ADMISSAO */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_DEPENDENTE(
	ID_DEPENDENTE_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* TP_DEPENDENTE */
	63, /* GP */
	1,
	current_timestamp(),
	current_timestamp()		
	
);

insert into TB_INPUT_DEPENDENTE(
	ID_DEPENDENTE_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NM_DEPENDENTE */
	59, /* NOME_BENEF */
	1,
	current_timestamp(),
	current_timestamp()		
	
);

insert into TB_INPUT_DEPENDENTE(
	ID_DEPENDENTE_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* NR_CPF */
	58, /* CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
	
);

insert into TB_INPUT_DEPENDENTE(
	ID_DEPENDENTE_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* DT_NASCIMENTO */
	60, /* DATA_NASC */
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_DEPENDENTE(
	ID_DEPENDENTE_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	5, /* MATRICULA */
	49, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/
/* Regra */

insert into TB_REGRA(
	NM_REGRA,
	TP_REGRA,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para Valor do Arquivo FATUCOPA dividido por 100',
	1, /* SIMPLES */
	0,
	1, /* FATO-COPA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_OPERATION(
	ID_REGRA,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	4, /* DIVIDIR */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_FIELD(
	ID_REGRA_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	14, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_VALOR(
	ID_REGRA_OPERATION,
	VL_REGRA_VALOR,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	100.0, /* 100 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_RESULT(
	ID_REGRA,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	14, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);
	
/* Regra Condicional */

insert into TB_REGRA(
	NM_REGRA,
	TP_REGRA,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para multiplicar o VL_PRINCIPAL por ( -1 ) se o valor da coluna COLUMN_13_POSITIVO_NEGATIVO for igual a ( - )',
	2, /* CONDICIONAL */
	0,
	1, /* FATO-COPA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_OPERATION(
	ID_REGRA,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	3, /* MULTIPLICAR */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_FIELD(
	ID_REGRA_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	14, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_VALOR(
	ID_REGRA_OPERATION,
	VL_REGRA_VALOR,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	-1.0, /* -1 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_RESULT(
	ID_REGRA,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	14, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL(
	NM_REGRA_CONDITIONAL,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para mudar o sinal do campo VL_PRINCIPAL dependendo do valor (+/-) do campo VL_NEGATIVO_POSITIVO',
	0,
	1, /* FATO-COPA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_OPERATION(
	ID_REGRA_CONDITIONAL,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	5, /* EQUALS */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_FIELD(
	ID_REGRA_CONDITIONAL_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	13, /* COLUMN_13_POSITIVO_NEGATIVO */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_VALOR(
	ID_REGRA_CONDITIONAL_OPERATION,
	VL_STRING,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	'-', /* VALOR */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_CONDITIONAL_RESULT(
	ID_REGRA_CONDITIONAL,
	ID_REGRA_EXECUTION,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	2, /* REGRA TO EXECUTE */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


/* FATU-COPA.2 */

insert into TB_REGRA(
	NM_REGRA,
	TP_REGRA,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para Valor do Arquivo FATUCOPA.2 dividido por 100',
	1, /* SIMPLES */
	0,
	2, /* FATO-COPA.2 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_OPERATION(
	ID_REGRA,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3,
	4, /* DIVIDIR */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_FIELD(
	ID_REGRA_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3,
	37, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_VALOR(
	ID_REGRA_OPERATION,
	VL_REGRA_VALOR,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3,
	100.0, /* 100 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_RESULT(
	ID_REGRA,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3,
	37, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);
	
/* Regra Condicional */

insert into TB_REGRA(
	NM_REGRA,
	TP_REGRA,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para FATO-COPA.2 multiplicar o VL_PRINCIPAL por ( -1 ) se o valor da coluna COLUMN_13_POSITIVO_NEGATIVO for igual a ( - )',
	2, /* CONDICIONAL */
	0,
	2, /* FATO-COPA.2 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_OPERATION(
	ID_REGRA,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4,
	3, /* MULTIPLICAR */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_FIELD(
	ID_REGRA_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4,
	37, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_VALOR(
	ID_REGRA_OPERATION,
	VL_REGRA_VALOR,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4,
	-1.0, /* -1 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_RESULT(
	ID_REGRA,
	ID_ARQUIVO_INPUT_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4,
	37, /* Vl_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL(
	NM_REGRA_CONDITIONAL,
	CD_ORDEM,
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'Regra para FATU-COPA.2 mudar o sinal do campo VL_PRINCIPAL dependendo do valor (+/-) do campo VL_NEGATIVO_POSITIVO',
	0,
	1, /* FATO-COPA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_OPERATION(
	ID_REGRA_CONDITIONAL,
	TP_OPERATION,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	5, /* EQUALS */
	0,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_FIELD(
	ID_REGRA_CONDITIONAL_OPERATION,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	36, /* COLUMN_13_POSITIVO_NEGATIVO */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_REGRA_CONDITIONAL_VALOR(
	ID_REGRA_CONDITIONAL_OPERATION,
	VL_STRING,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	'-', /* VALOR */
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_REGRA_CONDITIONAL_RESULT(
	ID_REGRA_CONDITIONAL,
	ID_REGRA_EXECUTION,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	2, /* REGRA TO EXECUTE */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

commit;

/*****************************************************************************************************************************************************/

insert into TB_PARAMETER(
	ID_EMPRESA,
	NM_PARAMETER,
	VL_PARAMETER,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* MUITO FACIL */
	'outputReportDir',
	'/home/eapereira/desenv/work/coparticipacao/output-reports/sulamerica/muito-facil/',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

commit;

/*****************************************************************************************************************************************************/

/* FATU-COPA.1 */

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT,
	NM_ARQUIVO_FORMAT,
	NM_DESCR_ARQUIVO,	
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATUCOPA - MUITO FACIL */
	'Não localizados PAG FACIL (Saúde) - Coparticipação {MM}_{YYYY}.xlsx',
	'Arquivo com os beneficiários não localizados pelo processo',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* NAO-ENCONTRADO FATUCOPA */
	'NR_MATRICULA',
	5, /* LONG  */
	8,
	1,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* NAO-ENCONTRADO FATUCOPA */
	'NM_BENEFICIARIO',
	3, /*VARCHAR  */
	32,
	2,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* NAO-ENCONTRADO FATUCOPA */
	'NM_TITULAR',
	3, /*VARCHAR  */
	32,
	3,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* NAO-ENCONTRADO FATUCOPA */
	'NR_CPF',
	5, /* LONG */
	12,
	4,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* NAO-ENCONTRADO FATUCOPA */
	'VL_PRINCIPAL',
	2, /* DOUBLE */
	15,
	5,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/* BIND */

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	22, /* NR_MATRICULA */
	1,

	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	7, /* NM_BENEFICIARIO */
	2,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	21, /* NM_TITULAR */
	3,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	9, /* NR_CPF */
	4,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	14, /* VL_PRINCIPAL */
	5,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

/* FATU-COPA.2 */

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT,
	NM_ARQUIVO_FORMAT,
	NM_DESCR_ARQUIVO,	
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATUCOPA.2 - MUITO FACIL */
	'Não localizados PAG FACIL (Saúde) - Coparticipação {MM}_{YYYY}.xlsx',
	'Arquivo com os beneficiários não localizados pelo processo',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NAO-ENCONTRADO FATUCOPA */
	'NR_MATRICULA',
	5, /* LONG  */
	8,
	1,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NAO-ENCONTRADO FATUCOPA */
	'NM_BENEFICIARIO',
	3, /*VARCHAR  */
	32,
	2,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NAO-ENCONTRADO FATUCOPA */
	'NM_TITULAR',
	3, /*VARCHAR  */
	32,
	3,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NAO-ENCONTRADO FATUCOPA */
	'NR_CPF',
	5, /* LONG */
	12,
	4,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF(
	ID_ARQUIVO_OUTPUT_DESCONHECIDO,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* NAO-ENCONTRADO FATUCOPA */
	'VL_PRINCIPAL',
	2, /* DOUBLE */
	15,
	5,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/* BIND */

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	45, /* NR_MATRICULA */
	1,

	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	30, /* NM_BENEFICIARIO */
	2,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	44, /* NM_TITULAR */
	3,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	32, /* NR_CPF */
	4,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_INPUT_OUTPUT_DESCONHECIDO(
	ID_ARQUIVO_INPUT_COLS_DEF,
	ID_ARQUIVO_OUTPUT_DESCONHECIDO_COLS_DEF,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	37, /* VL_PRINCIPAL */
	5,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

commit;

insert into TB_VIEW_DESTINATION(
	NM_VIEW,
	NM_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'VW_LANCAMENTO_MUITO_FACIL_8CH5Y',
	'8CH5Y',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* MUITO-FACIL */
	'NM_TITULAR',
	3, /* VARCHAR */
	32,
	1,
	'Títular',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* MUITO-FACIL */
	'NR_CPF',
	5, /* LONG */
	12,
	2,
	'CPF',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* MUITO-FACIL */
	'NR_MATRICULA',
	5, /* LONG */
	8,
	3,
	'Matricula',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* MUITO-FACIL */
	'VL_PRINCIPAL',
	2, /* DOUBLE */
	15,
	4,
	'Valor Principal',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/* FASTU-COPA.2 */

insert into TB_VIEW_DESTINATION(
	NM_VIEW,
	NM_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'VW_LANCAMENTO_MUITO_FACIL_8CHE8',
	'8CHE8',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* MUITO-FACIL */
	'NM_TITULAR',
	3, /* VARCHAR */
	32,
	1,
	'Títular',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* MUITO-FACIL */
	'NR_CPF',
	5, /* LONG */
	12,
	2,
	'CPF',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* MUITO-FACIL */
	'NR_MATRICULA',
	5, /* LONG */
	8,
	3,
	'Matricula',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_VIEW_DESTINATION_COLS_DEF(
	ID_VIEW_DESTINATION	,
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,
	CD_ORDEM,
	NM_COL_TITLE_LABEL,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* MUITO-FACIL */
	'VL_PRINCIPAL',
	2, /* DOUBLE */
	15,
	4,
	'Valor Principal',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

/* FASTU-COPA.1 */

insert into TB_ARQUIVO_OUTPUT(
	ID_ARQUIVO_INPUT,
	NM_ARQUIVO_FORMAT,
	DESCR_ARQUIVO,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	'Muito Facil (Pag Facil)_SAS_(Saude)_CoParticipacao_({YYYY}_{MM}).xlsx',
	'Arquivo de saída para a carga dos lançamentos FATU COPA',
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_ARQUIVO_OUTPUT_SHEET(
	ID_ARQUIVO_OUTPUT,
	ID_VIEW_DESTINATION,
	NM_SHEET,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	1, /* VW_LANCAMNETO_FATU_COPA */
	'Lançamentos',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	1, /* NM_TITULAR */ 
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	2, /* NR_CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	3, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	4, /* VL_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET(
	ID_ARQUIVO_OUTPUT,
	ID_VIEW_DESTINATION,
	NM_SHEET,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATU-COPA */
	2, /* VW_LANCAMNETO_FATU_COPA */
	'Lançamentos',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	1, /* NM_TITULAR */ 
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	2, /* NR_CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	3, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	4, /* VL_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

/* FASTU-COPA.2 */

insert into TB_ARQUIVO_OUTPUT(
	ID_ARQUIVO_INPUT,
	NM_ARQUIVO_FORMAT,
	DESCR_ARQUIVO,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	'Muito Facil (Pag Facil)_SAS_(Saude)_CoParticipacao_({YYYY}_{MM}).xlsx',
	'Arquivo de saída para a carga dos lançamentos FATU COPA',
	
	1,
	current_timestamp(),
	current_timestamp()		
);


insert into TB_ARQUIVO_OUTPUT_SHEET(
	ID_ARQUIVO_OUTPUT,
	ID_VIEW_DESTINATION,
	NM_SHEET,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	1, /* VW_LANCAMNETO_FATU_COPA */
	'Lançamentos',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* FATU-COPA */
	1, /* NM_TITULAR */ 
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* FATU-COPA */
	2, /* NR_CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* FATU-COPA */
	3, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	3, /* FATU-COPA */
	4, /* VL_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET(
	ID_ARQUIVO_OUTPUT,
	ID_VIEW_DESTINATION,
	NM_SHEET,
	
	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATU-COPA */
	2, /* VW_LANCAMNETO_FATU_COPA */
	'Lançamentos',
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* FATU-COPA */
	1, /* NM_TITULAR */ 
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* FATU-COPA */
	2, /* NR_CPF */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* FATU-COPA */
	3, /* NR_MATRICULA */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_ARQUIVO_OUTPUT_SHEET_COLS_DEF(
	ID_ARQUIVO_OUTPUT_SHEET,
	ID_VIEW_DESTINATION_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	4, /* FATU-COPA */
	4, /* VL_PRINCIPAL */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/*****************************************************************************************************************************************************/

commit;

/*****************************************************************************************************************************************************/

insert into TB_BENEFICIARIO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,	

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'NR_MATRICULA',
	5, /* LONG */
	17,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_BENEFICIARIO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,	

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'NM_TITULAR',
	3, /* VARCHAR */
	200,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_BENEFICIARIO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,	

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'NR_CPF',
	3, /* VARCHAR */
	11,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_BENEFICIARIO_COLS_DEF(
	NM_COLUMN,
	CD_TYPE,
	VL_LENGTH,	

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	'NM_BENEFICIARIO',
	3, /* VARCHAR */
	200,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/* FATO-COPA.1 */

insert into TB_INPUT_BENEFICIARIO(
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1, /* FATO-COPA.1 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	1, /* NR_MATRICULA */
	22,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	2, /* NM_BENEFICIARIO */
	21,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	3, /* NR_CPF */
	9,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	1,
	4, /* NM_BENEFICIARIO */
	7,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

/* FATO-COPA.2 */

insert into TB_INPUT_BENEFICIARIO(
	ID_ARQUIVO_INPUT,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2, /* FATO-COPA.2 */
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	1, /* NR_MATRICULA */
	45,
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	2, /* NM_BENEFICIARIO */
	44,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	3, /* NR_CPF */
	32,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

insert into TB_INPUT_BENEFICIARIO_BIND(
	ID_INPUT_BENEFICIARIO,
	ID_BENEFICIARIO_COLS_DEF,
	ID_ARQUIVO_INPUT_COLS_DEF,

	USER_CREATED,
	DT_CREATED,
	DT_ALTERED ) values (
	2,
	4, /* NM_BENEFICIARIO */
	30,
	
	1,
	current_timestamp(),
	current_timestamp()		
);

