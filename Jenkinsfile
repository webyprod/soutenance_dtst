pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    environment {
        PATH = "/opt/apache-maven-3.9.11/bin:$PATH"
    }

    stages {
        stage('Build project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {

            environment {
                scannerHome = tool 'sonar'
            }
                    steps {
                        withSonarQubeEnv('sonar') {
                            sh "${scannerHome}/bin/sonar-scanner"
                        }
                    }
                }
    }
}