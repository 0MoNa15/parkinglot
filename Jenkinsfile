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
        echo "------------>Build1<------------"
        sh 'gradlew --b clean compileJava'
      }
    }
    

    stage('Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
        echo "------------>Cleaning<------------"
        //sh './gradlew --b ./build.gradlew clean'
        sh 'chmod +x ./gradlew'
        sh './gradlew --b ./build.gradlew clean'


        echo "------------>test only<------------"
        //sh 'gradlew --b ./build.gradlew test'
        sh './gradlew --b ./build.gradlew test'
      }
    }


    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
 			//sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
        }
      }
    }

    stage('Build') {
      steps {
        echo "------------>Build2<------------"
        //Construir sin tarea test que se ejecutó previamente
        //sh 'gradlew --b ./build.gradlew build -x test'
        sh './gradlew --b ./build.gradlew build -x test'
      }
    }  
  }


  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
      junit 'build/test-results/test/*.xml' //→ RUTA DE TUS ARCHIVOS .XML
      mail (to: 'zorayda.gutierrez@ceiba.com.co',subject: "SUCCESS Pipeline:${currentBuild.fullDisplayName}",body: "Hi Ceiba <3 ${env.BUILD_URL}")
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
