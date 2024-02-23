import shared
import SwiftUI

struct RegisterScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        RegisterScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
