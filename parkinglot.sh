#!/bin/sh

arg1=$1
dir=target

##jar file name
jar=parking-lot-1.0-SNAPSHOT.jar

mvn clean install

if [ -z "$1" ] ; then
        java -jar $dir/$jar
        exit 1
else
	java -jar $dir/$jar $arg1
fi