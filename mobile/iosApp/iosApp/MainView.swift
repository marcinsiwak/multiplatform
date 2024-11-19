import shared_mobile
import SwiftUI

struct MainView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainViewControllerKt.setup()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
