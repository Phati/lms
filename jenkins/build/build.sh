!#bin/bash

echo "************************************"
echo "***** BUILDING DOCKER IMAGE ********"
echo "************************************"

echo "removing"
ls -la jenkins/build/*.jar

rm -rf jenkins/build/*.jar

echo "copying target/$TARGET_JAR_NAME-$RELEASE_VERSION.jar"
cp target/$TARGET_JAR_NAME-$RELEASE_VERSION.jar jenkins/build/


cd jenkins/build/ && docker-compose -f docker-compose-build.yml build