
def call(Map config = [:]) {
    def imageName = config.imageName ?: 'psokev99/backend-modulo3'
    def dockerCredentialsId = config.dockerCredentialsId ?: 'dockerhub-credentials'

    pipeline {
        agent any

        options {
            timestamps()
        }

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Instalar dependencias') {
                steps {
                    sh 'npm ci'
                }
            }

            stage('Validar aplicacion') {
                steps {
                    sh 'node --check index.js'
                }
            }

            stage('Construir imagen Docker') {
                steps {
                    sh "docker build -t ${imageName}:${env.BUILD_NUMBER} ."
                }
            }

            stage('Publicar en Docker Hub') {
                steps {
                    withCredentials([
                        usernamePassword(
                            credentialsId: dockerCredentialsId,
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_TOKEN'
                        )
                    ]) {
                        sh '''
                            echo "$DOCKER_TOKEN" | docker login -u "$DOCKER_USER" --password-stdin
                        '''
                        sh "docker push ${imageName}:${env.BUILD_NUMBER}"
                    }
                }
            }
        }

        post {
            success {
                echo 'Pipeline ejecutado correctamente.'
            }
            failure {
                echo 'El pipeline ha fallado. Revisar los logs.'
            }
        }
    }
}
