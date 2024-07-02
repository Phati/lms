@Library('java_shared_library') _

pipeline{

    agent any

    environment {
        DOCKER_HUB_PASSWORD = credentials('registry-pass')
    }

    stages{

        stage('Pipeline Setup') {
            steps {
                sh "echo ****************PIPELINE SETUP STAGE****************"
//              export the environment variables. Here we call the loadEnv() function from the java_shared_library
//              which is a groovy script. The function reads the .env file from the root dir of this project
//              and splits the file content by new line, the each line is split by = and stored as key and
//              value in Jenkins Environment
                loadEnv()
            }
        }

        stage('Unit Tests'){
            steps{
                sh "echo ****************UNIT TESTING STAGE****************"
//                 run mvn test from a maven docker container, mapping the working dir of the container
//                 to the root dir of this project where POM.xml is there , this is written in a file located
//                 at ./jenkins/test/mvn.sh and the file is executed as script from the shared library function below
                runJavaUnitTests()
            }
        }

        stage('Build'){
            steps{
                sh "echo ****************BUILD STAGE****************"
                runBuildJarFile()
            }
        }

        stage('Containerize'){
            steps{
                sh "echo ****************CONTAINERIZE STAGE****************"
                runDockerBuild()
            }
        }

        stage('Push'){
            steps{
                sh "echo ****************PUSH STAGE****************"
                runDockerPush()
            }
        }

        stage('Deploy'){
            steps{
                sh 'echo ****************DEPLOY STAGE****************'
                sh 'echo $CLUSTER_CA_CERT'
                sh 'echo $CLUSTER_ENDPOINT'
                withKubeConfig(caCertificate: '', clusterName: 'minikube', contextName: 'minikube', credentialsId: 'minikube-sa-secret', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://127.0.0.1:52269') {
                    runDeployDockerImage()
                }
            }
        }

    }

}
