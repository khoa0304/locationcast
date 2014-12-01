@echo ON
cd "C:\Programming\lib\apache-tomcat-7.0.53\bin"
CALL shutdown.bat
timeout /t 2
del "C:\Programming\lib\apache-tomcat-7.0.53\webapps\LocationCast.war"
rd C:\Programming\lib\apache-tomcat-7.0.53\webapps\LocationCast /s /q
rd /s /q C:\Programming\lib\apache-tomcat-7.0.53\logs
cd "C:\Programming\lib\apache-tomcat-7.0.53\bin"
CALL startup.bat
timeout /t 1
cd "C:\Users\Khoa\git\locationcast"
mvn clean tomcat7:redeploy