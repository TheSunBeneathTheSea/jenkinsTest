def dockerRepository = "slowlight50"
def dockerImageName = "myspringbootapp"

pipeline {
    agent any

    stages {
        stage('Checkout'){
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-key',
                                 usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    script {
                        sh """
                            chmod u+x ./gradlew
                            ./gradlew clean build -Pdocker.repository=${dockerRepository} \
                                                  -Pdocker.repository.username=${USERNAME} \
                                                  -Pdocker.repository.password=${PASSWORD} \
                                                  -Pdocker.image.name=${dockerImageName} \
                                                  -Pdocker.image.tag=${currentBuild.number}
                        """
                    }
                }
            }
        }
        stage('Publish') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-key',
                                 usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    script {
                        sh """
                            ./gradlew jib -Pdocker.repository=${dockerRepository} \
                                          -Pdocker.repository.username=${USERNAME} \
                                          -Pdocker.repository.password=${PASSWORD} \
                                          -Pdocker.image.name=${dockerImageName} \
                                          -Pdocker.image.tag=${currentBuild.number}
                        """
                    }
                }
            }
        }
    }
}