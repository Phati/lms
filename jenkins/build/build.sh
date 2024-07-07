!#bin/bash

echo "************************************"
echo "***** BUILDING DOCKER IMAGE ********"
echo "************************************"

cp target/$TARGET_JAR_NAME-$RELEASE_VERSION.jar jenkins/build/


cd jenkins/build/ && docker-compose -f docker-compose-build.yml build