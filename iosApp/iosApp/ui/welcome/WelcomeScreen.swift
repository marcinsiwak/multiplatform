import shared
import SwiftUI
// import GoogleSignIn

struct WelcomeScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        WelcomeScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
