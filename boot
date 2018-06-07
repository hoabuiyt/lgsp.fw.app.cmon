#!/bin/sh
mvn clean install && mvn spring-boot:run -f ./cmon-web/pom.xml
