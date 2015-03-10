#!/bin/sh


pid=$(jps | grep Kafka |cut -d ' ' -f 1)

if [ "${pid}" ]; then
  echo "Kill Kafka process id ${pid}" 	
  eval "kill -9 ${pid}"
  sleep 2
fi


pid=$(jps | grep QuorumPeerMain |cut -d ' ' -f 1)

if [ "${pid}" ]; then
  echo "Kill Zookeeper process id ${pid}" 	
  eval "kill -9 ${pid}"
  sleep 2
fi

pid=$(jps | grep Bootstrap |cut -d ' ' -f 1)

if [ "${pid}" ]; then
  echo "Kill Tomcat process id ${pid}" 	
  eval "kill -9 ${pid}"
  sleep 3
fi

jps
