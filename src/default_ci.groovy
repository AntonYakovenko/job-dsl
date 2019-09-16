import javaposse.jobdsl.dsl.jobs.FreeStyleJob

final String GIT_BRANCH = this.GIT_BRANCH.replaceFirst("origin", "").replaceAll("/", "-")

/**
 * This hack provides IDE support for job dsl in compile time
 */
static Closure explain(@DelegatesTo(FreeStyleJob) Closure cls) {
    return cls
}

job("test-test-${GIT_BRANCH}") {
    description("Lol")
}

job("shell-gradle-job-${GIT_BRANCH}") with(explain {
    description("Test job for shell and gradle")
    scm {
        github("AntonYakovenko/job-dsl", "master")
    }
    steps {
        shell("echo hello!")
    }
})
