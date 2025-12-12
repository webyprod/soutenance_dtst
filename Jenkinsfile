pipeline {
	agent any

	tools {
		jdk 'JDK17'
		maven 'maven3'
	}

	environment {
		SCANNER_HOME = tool 'sonar-scanner'
		/*REGISTRY = "http://54.160.225.182:8083"
        REPO = "soutenance-project"
        IMAGE = "${REGISTRY}/${REPO}/demo:latest"*/

        AWS_REGION = 'us-east-1'
        ECR_REGISTRY = '828776881371.dkr.ecr.us-east-1.amazonaws.com'
        ECR_REPO = 'soutenance-project/demo'
        IMAGE_TAG = "${BUILD_NUMBER}"
	}

	stages {
		stage('Compile') {
			steps {
				sh 'mvn compile'
			}
		}
		stage('Unit tests') {
			steps {
				sh 'mvn test'
			}
		}
		stage('Sonarqube Scan') {
			steps {
				 withSonarQubeEnv('sonar') {
					sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=Mission -Dsonar.projectName=Mission -Dsonar.java.binaries=. '''
				 }
			}
		}
		stage('Build project') {
			steps {
				sh 'mvn package -DskipTests'
			}
		}
		stage('Deploy to Nexus') {
           steps {
             sh 'mvn deploy -DskipTests'
           }
        }
        stage('Build Docker Image') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
              sh "docker build -t $DOCKER_USER/soutenance-project-demo:${BUILD_NUMBER} ."
            }
          }
        }
        stage('Push Docker Image to Docker Hub') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
              sh """
                echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                docker push $DOCKER_USER/soutenance-project-demo:${BUILD_NUMBER}
              """
            }
          }
        }
	}
}