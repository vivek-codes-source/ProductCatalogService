pipeline {
    agent any

    stages {

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

        stage('Docker Build') {
            steps {
                sh 'docker build -t productcatalogserviceproxy:v2 .'
            }
        }

        stage('Deploy Local') {
            steps {
                sh '''
                    docker stop productcatalogserviceproxy || true
                    docker rm productcatalogserviceproxy || true

                    docker run -d \
                        --name productcatalogserviceproxy \
                        -p 8081:8081 \
                        productcatalogserviceproxy:v2
                '''
            }
        }
    }
}
