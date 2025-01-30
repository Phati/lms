!#bin/bash

echo "********************"
echo "** Deploy image ***"
echo "********************"


echo "*** Checking Kubectl access ***"
kubectl get ns
helm upgrade --debug --dry-run -f $WORKSPACE/helm-charts/app/values/lms.yaml --set image.repository=$DOCKER_HUB_USER/$IMAGE --set image.tag=$RELEASE_VERSION --install lms $WORKSPACE/helm-charts/app --namespace cs --create-namespace
helm upgrade -f $WORKSPACE/helm-charts/app/values/lms.yaml --set image.repository=$DOCKER_HUB_USER/$IMAGE --set image.tag=$RELEASE_VERSION --install lms $WORKSPACE/helm-charts/app --namespace cs --create-namespace