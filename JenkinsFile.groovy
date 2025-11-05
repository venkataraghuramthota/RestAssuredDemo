pipeline {
    agent any

    tools {
        jdk 'JDK23'          // Name of JDK configured in Jenkins (Manage Jenkins → Global Tool Configuration)
        maven 'TestMaven'       // Name of Maven tool configured in Jenkins
    }
    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
        REPORT_DIR = "target/cucumber-reports"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out source code...'
                git branch: 'main', url: 'https://github.com/venkataraghuramthota/RestAssuredDemo.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Maven project...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running  Cucumber  tests...'
                sh 'mvn clean test -Dcucumber.options="--plugin pretty --plugin html:target/cucumber-reports/cucumber.html --plugin json:target/cucumber-reports/cucumber.json"'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing Cucumber and TestNG reports...'
                publishHTML (target: [
                        reportDir: 'target/cucumber-reports',
                        reportFiles: 'cucumber.html',
                        reportName: 'Cucumber Test Report'
                ])

              //  junit 'target/surefire-reports/*.xml'  // TestNG/JUnit result files
            }
        }

      
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }

        success {
            echo '✅ Build and tests completed successfully!'
        }

        failure {
            echo '❌ Build or tests failed. Please check the reports in Jenkins.'
        }
    }
}
