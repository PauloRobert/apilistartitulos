@echo off
SET DEVELOPMENT_HOME=C:\Eclipse_Novo\Workspace\

cd %DEVELOPMENT_HOME%\Api_Listar_Titulos\
call mvn clean install surefire-report:report
call mvn site -DgenerateReports=false

EXIT /B