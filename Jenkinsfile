pipeline {
    agent {
        docker {
            image 'maven:3.6.3-jdk-11-openj9'
            args '-u root'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                dir('SilaSDK') {
                    sh 'mvn clean install test'
                }
            }
        }
        stage('Sonar Analysis') {
            steps {
                dir('SilaSDK') {
                    withSonarQubeEnv('GekoSonar') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}