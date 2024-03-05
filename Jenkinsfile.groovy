pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn clean install"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }

        stage('Deploy') {
            steps {
                // เพิ่มขั้นตอน Deploy ตามความเหมาะสม
            }
        }
    }

    post {
        success {
            echo 'Automated Testing Passed! Ready for Deployment.'
            // เพิ่มขั้นตอนหลังจากทดสอบผ่าน
        }

        failure {
            echo 'Automated Testing Failed. Check the test results for more details.'
            // เพิ่มขั้นตอนหลังจากทดสอบไม่ผ่าน
        }
    }
}
