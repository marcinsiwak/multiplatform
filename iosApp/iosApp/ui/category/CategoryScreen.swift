import shared
import SwiftUI

struct CategoryScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        CategoryScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
