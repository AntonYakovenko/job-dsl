import javaposse.jobdsl.dsl.jobs.FreeStyleJob

final String GIT_BRANCH = this.GIT_BRANCH.replaceFirst("origin", "").replaceAll("/", "-")

/**
 * This hack provides IDE support for job dsl in compile time
 */
static Closure explain(@DelegatesTo(FreeStyleJob) Closure cls) {
    return cls
}

job("shell-gradle-job-${GIT_BRANCH}") with(explain {
    description("Test job for shell and gradle")
    wrappers {
        timestamps()
    }
    scm {
        git {
            remote {
                url(this.GIT_URL)
                credentials("ssh-github-key-seed")
            }
            branch(this.GIT_BRANCH)
        }
    }
    steps {
        batchFile("echo hello!")
    }
})
