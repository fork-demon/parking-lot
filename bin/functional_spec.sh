#!/bin/sh
file=./../functional_spec/fixtures/file_input.txt
dir=./../target
##jar file name
jar=parking-lot-1.0-SNAPSHOT.jar
java -jar $dir/$jar $file