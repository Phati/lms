#!/bin/bash

echo "*******************************"
echo "***** Building Jar ************"
echo "*******************************"

echo "jar name --  $TARGET_JAR_NAME-$RELEASE_VERSION-$BUILD_NUMBER"

docker run --rm  -v $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app  maven:3.8.3-openjdk-17 "$@" -Dbuild.number=${BUILD_NUMBER} -Djar.name=${TARGET_JAR_NAME} -Dapp.version=${RELEASE_VERSION}
