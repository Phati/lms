!#bin/bash

echo "********************"
echo "** Pushing image ***"
echo "********************"

echo "** Logging in ***"
docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASSWORD

echo "*** Tagging image ***"
docker tag $IMAGE:$RELEASE_VERSION $DOCKER_HUB_USER/$IMAGE:$RELEASE_VERSION

echo "*** Pushing image ***"
docker push $DOCKER_HUB_USER/$IMAGE:$RELEASE_VERSION

echo "*** Delete image from local ***"
docker rmi $IMAGE:$RELEASE_VERSION
docker rmi $DOCKER_HUB_USER/$IMAGE:$RELEASE_VERSION
