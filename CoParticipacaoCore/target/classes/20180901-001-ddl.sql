/*****************************************************************************************************************************************************/
/**
 * Edson - 01/09/2018
 */

create table TB_EXTERNAL_PROCESS(
	ID 							bigint( 17 ) auto_increment,
	ID_EMPRESA					bigint( 17 ) not null,
	
	CD_REGEXP_ARQUIVO_INPUT		varchar( 200 ) not null,
	CD_REGEXP_ARQUIVO_OUTPUT	varchar( 200 ) not null,
	        
	VERSION		bigint( 17 ) null,
	 
	USER_CREATED		bigint( 17 ) not null,
	USER_ALTERED 		bigint( 17 ),
	DT_CREATED			timestamp not null,
	DT_ALTERED			timestamp not null,
	
	constraint PK_EXTERNAL_PROCESS primary key( ID ),
	
	constraint UN_EXTERNAL_PROCESS unique key( ID_EMPRESA ),
	
	constraint FK_EXTERNAL_PROCESS_01 foreign key( USER_CREATED ) references TB_USER( ID ),
	constraint FK_EXTERNAL_PROCESS_02 foreign key( USER_ALTERED ) references TB_USER( ID ),
	constraint FK_EXTERNAL_PROCESS_03 foreign key( ID_EMPRESA ) references TB_EMPRESA( ID )	
);
