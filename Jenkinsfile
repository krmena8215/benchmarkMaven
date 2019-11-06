def version, PROYECTO = "dev-lobos-pod", SERVICE = "spring-boot", BRANCH_PROJECT = "master",
CREDENTIALS_ID = "0b93e6f9-2f68-433f-8ef6-85ed16264194",
URL_GIT = "https://github.com/krmena8215/benchmarkMaven.git",
KUBERNETES_SERVICE_HOST = "https://10.133.96.54:443",
TOKEN_KUBERNETES= "eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZXYtbG9ib3MtcG9kIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtdG9rZW4tMndna2YiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjRjZGYxYjEyLWY0MjMtMTFlOS1hYzA1LTAwNTA1Njg2OTNkNiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZXYtbG9ib3MtcG9kOmplbmtpbnMifQ.dFjPKX7VO39RHDL0c8fp3VtSR7KRQ-d4_sNPAtR5AyDqj0KYiDP8XMiMimFZKJMFG22fYnLYqdHdXZt0jpC0CPan1u5Yem3aOVHlJz8HR2auJAsq3lGtK3LdIFCkDpl-m9DcfC3jgnZvsulMO335SMh2wNy_FCaq9w6-qbEAiZkRG6l7hkIzhXyYye3rcC6esa0iL_GEjsiBT3URjb7wQ9KEna--UhbTSDC8TMuZTlA_qqpC9YIEwbeaWvtgYAgb-QMHnZuqQJq0cH1oUUrmgkK-y-P8khuDKgVwk-7QhgkxLPaZZZrhUUymoe_7HvBrajhyGI4FqPvE_oaRk2IR1w"
    pipeline
      {
       agent any
       tools {
          maven 'maven362'
          jdk 'java9'
          oc 'ocp1'
        }
        stages
        {

                stage('Build App') {
                  steps
                   {
                    git branch: "${BRANCH_PROJECT}",
                    url: "${URL_GIT}",
                    credentialsId: "${CREDENTIALS_ID}"

                    sh "mvn install -DskipTests=true"
                  }
                }

          stage('Create Builder Image') {
            when {
              expression {
                openshift.withCluster('ocp1') {
                  openshift.withProject(PROYECTO) {
                    return !openshift.selector("bc", SERVICE).exists();
                  }
                }

              }
            }
            steps {
              script {
                openshift.withCluster('ocp1') {
                  openshift.withProject(PROYECTO) {
                    openshift.newBuild("--name=${ SERVICE }", "--image-stream=openjdk-11-rhel8:latest","--binary=true", "strategy=docker")
                  }
                }
              }
            }
          }

          stage('Build Image') {
            steps {
              script {
                openshift.withCluster('ocp1') {
                  openshift.withProject(PROYECTO) {
                    openshift.selector("bc", SERVICE).startBuild("--from-dir=.","--follow")
                  }
                }
              }
            }
          }

          stage('Create and deploy dev') {
            when {
              expression {
                openshift.withCluster('ocp1') {
                  openshift.withProject(PROYECTO) {
                    return !openshift.selector('dc', SERVICE).exists()
                  }
                }
              }
            }
            steps {
              script {
                openshift.withCluster('ocp1') {
                  openshift.withProject(PROYECTO) {
                    def app = openshift.newApp("${ SERVICE }:latest")
                    app.narrow("svc").expose();

                  }
                }
              }
            }
          }
        }
      }
