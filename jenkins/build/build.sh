!#bin/bash

echo "************************************"
echo "***** BUILDING DOCKER IMAGE ********"
echo "************************************"

echo "removing"
ls -la jenkins/build/*.jar

rm -rf jenkins/build/*.jar

echo "copying target/$TARGET_JAR_NAME-$RELEASE_VERSION-$BUILD_NUMBER.jar"
cp target/$TARGET_JAR_NAME-$RELEASE_VERSION-$BUILD_NUMBER.jar jenkins/build/

docker images --format "{{.Repository}}:{{.ID}}" | awk -F: '$1 ~ /$IMAGE/ {print $2}' | xargs -r docker rmi

cd jenkins/build/ && docker-compose -f docker-compose-build.yml build --no-cache