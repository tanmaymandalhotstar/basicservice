kubectl delete deploy myapp
docker image rm helloworld
docker build . -f DockerFile -t helloworld
minikube image rm helloworld
minikube image load helloworld
kubectl apply -f deployment.yaml

