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

        stage("test") {
            steps {
                sh 'mvn surefire-report:report'
            }
        }

        stage('SonarQube Analysis') {

            environment {
                scannerHome = tool 'sonar'
            }
            steps {
                withSonarQubeEnv('sonar') {
                   //sh "${scannerHome}/bin/sonar-scanner"
                   sh """ /home/ubuntu/jenkins/tools/hudson.plugins.sonar.SonarRunnerInstallation/sonar/bin/sonar-scanner \
                            -Dsonar.projectKey=fox_soutenance-project \
                            -Dsonar.organization=fox \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.tests=src/test/java \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.jacoco.reportPaths=target/jacoco.exec \
                            -Dsonar.login=75ac4edd86b51e32bb64871bf13e4be9827cd7a8 """
                }
            }
        }

        stage("Quality Gate") {
            steps {
                script {
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure"
                        }
                    }
                }
            }
        }

        stage('Deploy to JFrog') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}