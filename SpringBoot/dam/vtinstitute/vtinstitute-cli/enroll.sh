#!/bin/bash
mvn exec:java -Dexec.mainClass="org.vtinstitute.Main" -Dexec.args="--enroll $1 $2 $3"