pipeline {
    agent any

    stages {

        stage('Setup Maven') {
            steps {
                sh '''
                apt-get update || true
                apt-get install -y maven || true
                mvn -v
                '''
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vivek-codes-source/ProductCatalogService'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
