pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'maven3'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Unit tests') {
            when {
                branch 'dev'
            }
            steps {
                sh 'mvn test'
            }
        }

        stage('Sonarqube Scan') {
            when {
                branch 'dev'
            }
            steps {
                withSonarQubeEnv('sonar') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=Mission -Dsonar.projectName=Mission -Dsonar.java.binaries=. '''
                }
            }
        }

        stage('Build project') {
            when {
                anyOf {
                    branch 'stage'
                    branch 'release'
                }
            }
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Nexus') {
            when {
                anyOf {
                    branch 'stage'
                    branch 'release'
                }
            }
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

        stage('Build Docker Image') {
            when {
                anyOf {
                    branch 'stage'
                    branch 'release'
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "docker build -t $DOCKER_USER/soutenance-project-demo:${BUILD_NUMBER} ."
                }
            }
        }

        stage('Run Docker Container') {
            when {
                branch 'stage'
            }
            steps {
                script {
                    sh 'docker rm -f soutenance-container || true'
                    sh "docker run -d --name soutenance-container $DOCKER_USER/soutenance-project-demo:${BUILD_NUMBER}"
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            when {
                branch 'release'
            }
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
