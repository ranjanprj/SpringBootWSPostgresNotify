pipeline {
  agent any
  tools{
    maven "maven3"
    jdk "rhopenjdk8"
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install -DskipTests'
      }
    }

  }
}
