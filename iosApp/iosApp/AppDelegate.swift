import Foundation
import shared_frontend
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate, PermissionListener {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
            AppKt.doInitKoin()
            AppKt.doInitFirebase()
            AppKt.doInitMobileAds()
            
            PermissionsHelper().setListener(listener: self)
            return true
        }

    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey: Any] = [:]) -> Bool {
        return AppKt.doInitGIDSingIn(url: url)
    }
    
    func isPermissionGranted(permission: AppPermission) -> Bool {
        return PermissionsHandler().checkIsPermissionGranted(permission: permission)
    }
    
    func requestPermission(permission: AppPermission, callback: PermissionResultCallback) {
        PermissionsHandler().handlePermission(permission: permission, callback: callback)
    }
}
