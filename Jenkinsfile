@Library('java_shared_library') _

pipeline{

    agent any

    environment {
        DOCKER_HUB_PASSWORD = credentials('registry-pass')
        SONAR_SERVER = 'sonarqube_server_installation'
        SONAR_TOKEN = credentials('sonar-creds')
        scannerHome = tool 'sonar-tool'
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


stage('Check & Create SonarQube Project') {
            steps {


                sh "echo ***************Create SonarQube Project"
                   sh " echo sonar host ${SONAR_HOST_URL}"
                  sh "  echo token ${SONAR_TOKEN}"
                   sh " echo project name ${SONAR_PROJECT_NAME}"

                
                script {
                    
                    def projectExists = sh(script: """
                        curl -s -u ${SONAR_TOKEN}: ${SONAR_HOST_URL}/api/projects/search?projects=${SONAR_PROJECT_NAME} | jq '.components | length'
                    """, returnStdout: true).trim()
                   echo "go on ${projectExists}"
                    if (projectExists == "0") {
                        echo "Project does not exist, creating in SonarQube..."
                        sh """
                            curl -X POST -u ${SONAR_TOKEN}: "${SONAR_HOST_URL}/api/projects/create" \
                                -d "name=${SONAR_PROJECT_NAME}" \
                                -d "project=${SONAR_PROJECT_NAME}"
                        """
                    } else {
                        echo "Project already exists in SonarQube."
                    }
                }
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

        stage('SonarQube Scan') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube_server_installation') {
                        sh """
                            ${scannerHome}/bin/sonar-scanner \
                                -Dsonar.projectKey=${SONAR_PROJECT_NAME} \
                                -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                                -Dsonar.host.url=${SONAR_HOST_URL} \
                                -Dsonar.login=${SONAR_TOKEN} \
                                -Dsonar.java.binaries=target/classes
                        """
                    }
                }
            }
        }

        stage('Wait for SonarQube Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
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

                withKubeConfig(caCertificate: '', clusterName: env.CLUSTER_NAME, contextName: env.CLUSTER_CONTEXT_NAME, credentialsId: env.CLUSTER_CREDENTIALS_ID, namespace: '', restrictKubeConfigAccess: false, serverUrl: env.CLUSTER_ENDPOINT) {
                    runDeployDockerImage()
                }
            }
        }

    }

}
