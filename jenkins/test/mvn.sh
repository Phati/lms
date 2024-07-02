#!/bin/bash

echo "*******************************"
echo "***** Running Unit Tests ******"
echo "*******************************"

echo $WORKSPACE
ls $WORKSPACE
id
docker run --rm  -v $WORKSPACE/:/app -v /root/.m2/:/root/.m2/ -w /app  maven:3.8.3-openjdk-17 "$@"
