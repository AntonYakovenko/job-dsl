import javaposse.jobdsl.dsl.jobs.FreeStyleJob

/**
 * This hack provides IDE support for job dsl in compile time
 */
static Closure explain(@DelegatesTo(FreeStyleJob) Closure cls) {
    return cls
}

job("test-test-${this.GIT_BRANCH}") {
    description("Lol")
}

job("shell-gradle-job-${this.GIT_BRANCH}") with(explain {
    description("Test job for shell and gradle")
    scm {
        github("AntonYakovenko/job-dsl", "master")
    }
    steps {
        shell("echo hello!")
    }
})
