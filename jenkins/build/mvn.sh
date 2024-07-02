#!/bin/bash

echo "*******************************"
echo "***** Building Jar ************"
echo "*******************************"

docker run --rm  -v $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app  maven:3.8.3-openjdk-17 "$@"
