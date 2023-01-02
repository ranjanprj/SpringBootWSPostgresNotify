pipeline {
  agent any
  tools{
    maven "maven3"
    jdk "rhopenjdk8"
  }
  parameters{
    choice(name "environment" , choices:["DEV","QA","PROD"])
  }
  stages {
    stage('build') {
      steps {
        sh "which java"
        sh "which mvn"
        sh 'mvn clean install -DskipTests'
      }
    }

  }
}
