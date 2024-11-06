pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "nuvilab"
include(":app")
include(":network")
include(":data")
include(":database")
include(":domain")
include(":feat:main")
include(":feat:core")
include(":domain:company")
include(":feat:work")
include(":domain:operation")
include(":utils")
include(":utils:security")
include(":design")
