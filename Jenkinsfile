pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    stages {
        stage('Build project') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}