#!/bin/bash
cd "/home/khoa/lib/apache-tomcat-7.0.57/bin"
#./catalina.sh stop 1 -force

pid=$(jps | grep Bootstrap |cut -d ' ' -f 1)

if [ "${pid}" ]; then
  echo "Kill Tomcat process id ${pid}" 	
  eval "kill ${pid}"
  sleep 3
fi

rm -rf "/home/khoa/lib/apache-tomcat-7.0.57/webapps/LocationCast.war"
rm -rf "/home/khoa/lib/apache-tomcat-7.0.57/webapps/LocationCast"
rm -rf "/home/khoa/lib/apache-tomcat-7.0.57/logs"
mkdir  "/home/khoa/lib/apache-tomcat-7.0.57/logs"
cd "/home/khoa/lib/apache-tomcat-7.0.57/bin"
./catalina.sh jpda start
sleep 3
cd "/home/khoa/git/locationcast"
mvn clean tomcat7:redeploy
