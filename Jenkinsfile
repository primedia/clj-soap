node {
    deleteDir()
    checkout scm

    withEnv(["BUILD_BRANCH=${env.BRANCH_NAME}"]) {
      withCredentials([string(credentialsId: 'abd6130d-0618-4221-9a6e-d89e3aaf79eb', variable: 'BUILD_AUTH'),
                       usernamePassword(credentialsId: 'f933da2a-d0cd-40d6-a6db-5b26bf3b818d',
                                        usernameVariable: 'CLOJARS_USERNAME',
                                        passwordVariable: 'CLOJARS_PASSWORD')])
      {
        sh "make -f makefile.docker test"
        sh "make -f makefile.docker clojars_deploy"
      }
    }
}
