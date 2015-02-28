
#!/bin/sh

startMongoDB(){

	echo -e  ">>>>>> Start mongodb service\n\n"
	cd /home/khoa/lib/mongodb
	sh ./mongostart.sh & 

}


startZookeeper(){

	echo ">>>>>> Start ZooKeeper Service\n\n"
	cd /home/khoa/lib/kafka_2.11-0.8.2-beta
	sh ./zookeeperstart.sh &
        sleep 3
}

startKafka(){

	echo ">>>>>> Start Kafka Broker Service\n\n"
	cd /home/khoa/lib/kafka_2.11-0.8.2-beta
	sh ./kafkastart.sh &
        sleep 3

}

startTomcat(){

        echo ">>>>>> Start Tomcat Service\n\n"
	cd /home/khoa/git/locationcast
	sh ./redeployLocationCast.sh &
        sleep 5
        cd /home/khoa/git/locationcast
        jps

}


startMongoDB
startZookeeper
startKafka
startTomcat



echo "======== Finished starting all services\n\n"
