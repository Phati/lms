!#bin/bash

echo "********************"
echo "** Pushing image ***"
echo "********************"

echo "** Logging in ***"
docker login -u phatijava -p $DOCKER_HUB_PASSWORD

echo "*** Tagging image ***"
docker tag $IMAGE:$RELEASE_VERSION phatijava/$IMAGE:$RELEASE_VERSION

echo "*** Pushing image ***"
docker push phatijava/$IMAGE:$RELEASE_VERSION
