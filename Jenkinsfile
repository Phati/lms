@Library('java_shared_library') _

pipeline{

    agent any

    environment {
        DOCKER_HUB_PASSWORD = credentials('registry-pass')
        CLUSTER_CA_CERT = credentials('minikube-ca-cert')
        CLUSTER_ENDPOINT = credentials('minikube-server-endpoint')
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
                withKubeConfig(caCertificate: 'LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURCakNDQWU2Z0F3SUJBZ0lCQVRBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwdGFXNXAKYTNWaVpVTkJNQjRYRFRJME1EWXdOVEU0TlRZeU5Gb1hEVE0wTURZd05ERTROVFl5TkZvd0ZURVRNQkVHQTFVRQpBeE1LYldsdWFXdDFZbVZEUVRDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTFJxCjhlT0FUU1F3SExCaWxSSmhlZ3VNQzUreGZla2F0LzE1MlFMTTVzaW9meFVzS3N1amI1ME40eEJnMGVzMnpNclYKdUhWYVhaSk5zdi95S0gvcDR5SlRtYmRld2tDUUVBZVM4Y1QrOVlEdmVGcSs5NTBhdGZwTG9reUZML3dUZ2VJZAp1WDZyNXNZMExCU2RTM1BlOFpvYW80ZWw4cWlhdXNhTi93ZHlNRFVWdkdPdUp6YzZEMmRiSXVZZmUyakZOY2ZsCnZrb25LbldKUFh1ZDdmT01sMys1bFN1b0tja2VNT1NtTnFyTktSNyt1dHdncXlBNGNQejhvK1NBK2ZXTWF6eEYKSGJHTzFJcFlWQ2VZQ1lhc0pPbFpoSGJ4TEhISnczcVZnWE03TGJiK3FSRzlSbFM1K3lQM1lURmNHV1lPZER0YwpOeTlxTTFYbFhKeS9DVDFCL3RNQ0F3RUFBYU5oTUY4d0RnWURWUjBQQVFIL0JBUURBZ0trTUIwR0ExVWRKUVFXCk1CUUdDQ3NHQVFVRkJ3TUNCZ2dyQmdFRkJRY0RBVEFQQmdOVkhSTUJBZjhFQlRBREFRSC9NQjBHQTFVZERnUVcKQkJUM0E4cUZjbnF6WnZhNGszeGxQTFlQZ3lTRHpqQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFzaEtMWURMSwo5bVM0UzczUmVOOHJ1OXFqaVFWM3hkcms5bjd3ODFiNU02MUk3MzZVVmcyT2hHQTR0Q3FiSEtiK0xVQUJwbzQ1Ck1qVE8ybnY1L2N0ZUJxQkJMRlNyWW16anZuMlk0K2pCN3EvNmx0Y09uemNCbXBOTUhmd2FWd20wS1FHbENkbFYKRzNTa2R1L3lrYzJhazhsb1BscnJ1ZGRuazU4aFpuRTlVckwyV3lnUXp6Sm9ldERIL2tjT3VRbnh0aUJPanJOSApRb2ZJaFd4MWhWQnYwU0FWSTJZN2VTRmt3SlRvNlZBWlJpc2hVUWxva1l6alBqWlk4RDNKVEdiYnByQVE0ZlMwCmQ4U2o1T0Q3OGZEOTJZUFVVTENDVW5TWkNLNTRRWGtoclV0M3A3eElKV1o1dzAzSEtCTklaWFkvUUUwNUc1NXkKSVZKNjJmY1VLUFdGN3c9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==', clusterName: 'minikube', contextName: 'minikube', credentialsId: 'minikube-sa-secret', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://127.0.0.1:52269') {
                    runDeployDockerImage()
                }
            }
        }

    }

}
