import shared
import SwiftUI

struct SettingsScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        SettingsScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
