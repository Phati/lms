!#bin/bash

echo "********************"
echo "** Deploy image ***"
echo "********************"

echo "** Logging in ***"
docker login -u phatijava -p $DOCKER_HUB_PASSWORD

echo "*** Pull docker image image ***"
docker pull phatijava/$IMAGE:$RELEASE_VERSION

echo "*** Checking Kubectl access ***"

kubectl get ns
helm upgrade -f $WORKSPACE/helm-charts/app/values/lms.yaml --set image.tag=$RELEASE_VERSION --install lms $WORKSPACE/helm-charts/app --namespace cs --create-namespace