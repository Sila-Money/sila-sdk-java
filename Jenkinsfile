pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-openj9'
                    args '-u root'
                }
            }
            steps {
                sh 'mvn --version'
                dir('SilaSDK') {
                    withSonarQubeEnv('GekoSonar') {
                        sh 'mvn clean install sonar:sonar'
                    }
                }
            }
        }
        stage("Quality Gate") {
            agent any
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}