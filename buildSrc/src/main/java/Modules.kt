package pl.msiwak.multiplatform.dependencies

object Modules {
    private const val shared =":shared-mobile"
    const val commonResources = "$shared:commonResources"
    const val commonObject = "$shared:commonObject"
    const val database = "$shared:database"
    const val utils = "$shared:utils"
    const val auth = "$shared:auth"
    const val network = "$shared:network"
    const val data = "$shared:data"
    const val remoteConfig = "$shared:remoteConfig"
    const val domain = "$shared:domain"
    const val domainImpl = "$shared:domain-impl"
    const val navigator = "$shared:navigator"
    const val uiWelcome = "$shared:ui-welcome"
    const val uiAddCategory = "$shared:ui-addCategory"
    const val uiAddExercise = "$shared:ui-addExercise"
    const val uiCategory = "$shared:ui-category"
    const val uiDashboard = "$shared:ui-dashboard"
    const val uiForceUpdate = "$shared:ui-forceUpdate"
    const val uiLanguage = "$shared:ui-language"
    const val uiRegister = "$shared:ui-register"
    const val uiSettings = "$shared:ui-settings"
    const val uiSummary = "$shared:ui-summary"
    const val uiUnit = "$shared:ui-unit"
    const val uiVerifyEmail = "$shared:ui-verifyEmail"
    const val buildConfig = "$shared:buildConfig"
    const val notifications = "$shared:notifications"
    const val uiCommonComponent = "$shared:ui-common-component"
    const val uiTerms = "$shared:ui-terms"
}