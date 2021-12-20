dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "YandexApi"
include(":app")
include(":domain")
include(":model")
include(":screendetail")
include(":screenfavourite")
include(":screenhistory")
include(":utils")
