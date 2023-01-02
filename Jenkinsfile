pipeline {
  agent any
  tools{
    maven "maven3"
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
    }

  }
}
