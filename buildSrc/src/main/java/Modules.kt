package pl.msiwak.multiplatform.dependencies

object Modules {
    const val sharedMobile =":shared-mobile"
    const val commonResources = "$sharedMobile:commonResources"
    const val commonObject = "$sharedMobile:commonObject"
    const val database = "$sharedMobile:database"
    const val utils = "$sharedMobile:utils"
    const val auth = "$sharedMobile:auth"
    const val network = "$sharedMobile:network"
    const val data = "$sharedMobile:data"
    const val remoteConfig = "$sharedMobile:remoteConfig"
    const val domain = "$sharedMobile:domain"
    const val domainImpl = "$sharedMobile:domain-impl"
    const val navigator = "$sharedMobile:navigator"
    const val uiWelcome = "$sharedMobile:ui-welcome"
    const val uiAddCategory = "$sharedMobile:ui-addCategory"
    const val uiAddExercise = "$sharedMobile:ui-addExercise"
    const val uiCategory = "$sharedMobile:ui-category"
    const val uiDashboard = "$sharedMobile:ui-dashboard"
    const val uiForceUpdate = "$sharedMobile:ui-forceUpdate"
    const val uiLanguage = "$sharedMobile:ui-language"
    const val uiRegister = "$sharedMobile:ui-register"
    const val uiSettings = "$sharedMobile:ui-settings"
    const val uiSummary = "$sharedMobile:ui-summary"
    const val uiUnit = "$sharedMobile:ui-unit"
    const val uiVerifyEmail = "$sharedMobile:ui-verifyEmail"
    const val buildConfig = "$sharedMobile:buildConfig"
    const val notifications = "$sharedMobile:notifications"
    const val uiCommonComponent = "$sharedMobile:ui-common-component"
    const val uiTerms = "$sharedMobile:ui-terms"
    const val databaseWasm = "$sharedMobile:database-wasm"
    const val store = "$sharedMobile:store"

    // Admin
    const val uiAdminPanel = "$sharedMobile:ui-admin-panel"

    // Shared
    const val shared = ":shared"
    const val sharedModel = "$shared:model"
}