#!/bin/bash

echo "*******************************"
echo "***** Building Jar ************"
echo "*******************************"

echo "jar name --  $TARGET_JAR_NAME"
echo "jar version --  $RELEASE_VERSION-$BUILD_NUMBER"


docker run --rm  -v $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app  maven:3.8.3-openjdk-17 "$@"
