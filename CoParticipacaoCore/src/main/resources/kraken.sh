############################################################################################################################
## Script para executar os scripts SQL no banco MySQL:
##
## Edson - 15/08/2018
############################################################################################################################
#!/bin/bash

#SCRIPTS_HOME="/home/eapereira/desenv/git-home/coparticipacao/CoParticipacaoCore/src/main/resources"

SCRIPTS_HOME=`pwd`

SQL_FILES=`ls ${SCRIPTS_HOME}/*.sql | sort`

MYSQL_CMD="mysql --defaults-file=mysql.properties"

echo $MYSQL_CMD
    
for sqlFile in $SQL_FILES
do
	CMD="${MYSQL_CMD} < ${sqlFile}"
	echo $CMD
	#$CMD
	
	mysql --defaults-file=mysql.properties < $sqlFile
	
	if [ $? -ne 0 ]; then
		echo "Deu erro!"
		break
	fi
done

if [ $? -eq 0 ]; then
	echo "Todos os scripts executados com SUCESSO!"
fi

exit 0
