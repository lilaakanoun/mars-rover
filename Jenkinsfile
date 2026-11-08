pipeline {
    agent any

    stages {
        // run the tests first: if one fails, the pipeline stops here
        stage('Test') {
            stages {
                // unit tests and end to end test of rover.jar
                stage('Core tests') {
                    steps {
                        echo 'Core tests'
                    }
                }

                // api tests
                stage('API tests') {
                    steps {
                        echo 'API tests'
                    }
                }
            }
        }

        // build the three parts of the project
        stage('Build') {
            stages {
                // rover.jar
                stage('Core') {
                    steps {
                        echo 'Core'
                    }
                }

                // rover-api.jar
                stage('API') {
                    steps {
                        echo 'API'
                    }
                }

                // angular app
                stage('Front') {
                    steps {
                        echo 'Front'
                    }
                }
            }
        }

        // api and front images: check the Dockerfiles syntax, build them, deploy them to Artifactory
        stage('Docker') {
            steps {
                echo 'Docker'
            }
        }

        // internal tools: code quality, security scan, artifact publishing
        stage('Internal tools') {
            stages {
                stage('Sonar') {
                    steps {
                        echo 'Sonar'
                    }
                }

                stage('Fortify') {
                    steps {
                        echo 'Fortify'
                    }
                }

                stage('Nexus') {
                    steps {
                        echo 'Nexus'
                    }
                }
            }
        }
    }
}
