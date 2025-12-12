pipeline {
	agent any

	tools {
		jdk 'JDK17'
		maven 'maven3'
	}

	environment {
		SCANNER_HOME = tool 'sonar-scanner'
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
	}
}