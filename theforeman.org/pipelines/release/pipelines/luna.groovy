pipeline {
    agent none

    options {
        timestamps()
        timeout(time: 5, unit: 'HOURS')
        disableConcurrentBuilds()
        ansiColor('xterm')
    }

    stages {
        stage('Install tests and Upgrade tests') {
            agent any
            steps {
                script {
                    runCicoPipelines('luna', 'nightly', pipelines)
                }
            }
        }
    }
    post {
        failure {
            notifyDiscourse(env, 'Luna nightly pipeline failed:', currentBuild.description)
        }
    }
}
