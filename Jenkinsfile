#!groovy
// vim: ft=groovy
// These 2 need to be authorized using jenkins script approval
// http://your.jenkins.host/scriptApproval/
import groovy.json.JsonOutput
import java.util.Optional
import java.util.LinkedHashMap

/********** Global variables **********/

//Load environment variables
  globalrepo = "${env.globalrepo}"
  globalrepo_credentials_id = "${env.globalrepo_credentials_id}"
  globalrepoBranch = "master"
  apx_node = "${env.apx_node_label}"
  projVerRepo = "${env.proj_ver_repo}"
  projVerRepoBucket = "${env.proj_ver_repo_bucket}"
  jenkinsProjVerCredentials = "${env.jenkins_proj_ver_credentials}"
  jenkinsSshCredentials = "${env.jenkins_ssh_credentials}"
  jenkinsArtifactoryCredentials = "${env.jenkins_artifactory_credentials}"
  artifactoryServer = "${env.artifactory_server}"
  componentPaaSId = "insuranceroyal-lib12"
  appPaaSId = "pisd"
  proyectPaaSId = "pe.pisd.app-id-26197.dsg"

/********** Project variables **********/

def uuaa_project = "pisd"
def repoParams = [
        "component" : "r2artifact",
        "repoName" : "insuranceroyal-lib12",
        "repoBranch" : "${env.BRANCH_NAME}",
        "projectVersion" : "${env.BRANCH_NAME}",
        "uuaa" : "${uuaa_project}",
        "slave_label" : "apx",
        "extra_properties" : null,
        "apxrepoBranch" : "master",
        "resource_type": "du-lib"
]

/********** CI Flow **********/

stage("Checkout Global Library") {
  fileLoader.withGit(globalrepo, globalrepoBranch, globalrepo_credentials_id, apx_node) {
    apx_ci = fileLoader.load('src/apx/ci/ci');
    apx_ci.apx_ci_flow(repoParams)
  }
}