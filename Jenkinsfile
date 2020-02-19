pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                docker { image 'maven:3.6.3-jdk-11-openj9' }
            }
            steps {
                sh 'mvn --version'
                sh 'cd SilaSDK'
                sh 'pwd'
                sh 'mvn clean install'
            }
        }
    }
}