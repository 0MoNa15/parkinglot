pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Mac'
  }


  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3')) // Guarda últimas 3 compilaciones
 		disableConcurrentBuilds()
  }


  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK8_Mac' //Preinstalada en la Configuración del Master
  }


  //Aquí comienzan los “items” del Pipeline
  stages{
    stage('Checkout') {
      steps{
      	// Conexión al repositorio
        echo "------------>Checkout<------------"
        checkout([
    			$class: 'GitSCM', 
    			branches: [[name: '*/main']], 
    			doGenerateSubmoduleConfigurations: false, 
    			extensions: [], 
    			gitTool: 'Default', 
    			submoduleCfg: [], 
    			userRemoteConfigs: [[
    				credentialsId: 'GitHub_Zorayda', 
    				url:'https://github.com/Zorayda/parkinglot.git'
    			]]
    		])
      }
    }


    stage('Build') {
      steps {
        echo "------------>Build<------------"
        sh 'chmod +x ./gradlew'
        sh './gradlew build -x test'
      }
    }


    stage('Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
        sh './gradlew clean'
        sh './gradlew test'
        sh './gradlew tasks'
        sh './gradlew jacocoTestReport'
      }
    }


     stage('Static Code Analysis') {
        steps{
            echo "------------>Static Code Analysis<------------"
            withSonarQubeEnv('Sonar'){
                sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
            }
        }
      }
  }


  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
      mail (to: 'zorayda.gutierrez@ceiba.com.co',subject: "SUCCESS Pipeline:${currentBuild.fullDisplayName}",body: "Hi Ceiba <3")
      junit '**/test-results/testDebugUnitTest/*.xml'

    }
    failure {
      echo 'This will run only if failed'
      mail (to: 'zorayda.gutierrez@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}