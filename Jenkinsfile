pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'building'
        sh 'mvn clean install'
      }
    }

  }
}