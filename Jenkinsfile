pipeline {
  agent any
  tools{
    maven "maven3"
    jdk "orajdk8"
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
    }

  }
}
