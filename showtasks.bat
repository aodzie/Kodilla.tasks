call runcrud.bat
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot run file

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:openbrowser
start http://localhost:8080/crud/v1/tasks/getTasks