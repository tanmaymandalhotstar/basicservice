# Basic Service Helloworld
## service deploy on kubernetes

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This Project aims to :

- Start a service
- Basic docker file to run as container
- Deploy the project on minikube kubernetes cluster
-
Prerequisites:
- Minikube and kubectl should be installed and running
- Docker Desktop
- Java 8


## Running the code

- Run the below command to give all pods admin privileges in the cluster
 ```sh
        kubectl create clusterrolebinding serviceaccounts-cluster-admin \
        --clusterrole=cluster-admin \
        --group=system:serviceaccounts
 ```

- Runner.sh file has required commands to build the docker image, load it to minikube, and deploy the service to kubernetes
- After running runner.sh you can check kubernetes dashboard to check your service deployed on a pod
- Run the below command to create a tunnel to the service in your cluster
```sh
        minikube service app1
```
- This should open a localhost site on your browser.
- To create a new job hit this in browser "localhost:port/add_job"
- Check the job status in kubernetes dashboard