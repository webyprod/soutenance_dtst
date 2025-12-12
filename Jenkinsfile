pipeline {
	agent any

	tools {
		jdk 'JDK17'
		maven 'maven3'
	}

	environment {
		SCANNER_HOME = tool 'sonar-scanner'
		REGISTRY = "http://54.160.225.182:8083"
        REPO = "soutenance-project"
        IMAGE = "${REGISTRY}/${REPO}/demo:latest"
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
				 /* #withSonarQubeEnv(credentialsId: 'sonarserverId') {} */
				 withSonarQubeEnv('sonar') { /*# sonar = nomServeurSonarQube*/
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
            /*sh "docker build -t $IMAGE ."*/
            sh "docker build -t 54.160.225.182:8083/soutenance-project/demo:${BUILD_NUMBER} ."
          }
        }
        stage('Push Docker Image to Nexus') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'nexus-docker-creds', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
              sh """
                echo $NEXUS_PASS | docker login 54.160.225.182:8083 -u $NEXUS_USER --password-stdin
                docker push 54.160.225.182:8083/soutenance-project/demo:${BUILD_NUMBER}
                 """
            }
          }
        }
	}
}