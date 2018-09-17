rem ############################################################################################################################
rem ## Script para executar os scripts SQL no banco MySQL:
rem ##
rem ## Edson - 15/08/2018
rem ############################################################################################################################
@echo off

set SCRIPTS_HOME="/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoCore/src/main/resources"

set SQL_FILES=`dir %SCRIPTS_HOME%/*.sql | sort`

set MYSQL_CMD="mysql --defaults-file=mysql.properties"

echo %MYSQL_CMD%
    
for %%sqlFile in %SQL_FILES%
do {
	CMD="%MYSQL_CMD% < %sqlFile%"
	echo $CMD
	rem $CMD
	
	mysql --defaults-file=mysql.properties < %sqlFile%
	
	if not %ERRORLEVEL% == 0 {
		echo "Deu erro!"
		break
	}
}

if %ERRORLEVEL% == 0 {
	echo "Todos os scripts executados com SUCESSO!"
}

exit 0
