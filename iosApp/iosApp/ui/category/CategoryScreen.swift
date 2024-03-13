import shared
import SwiftUI

struct CategoryScreen: UIViewControllerRepresentable {
    let id: String

    func makeUIViewController(context: Context) -> some UIViewController {
        CategoryScreenControllerKt.setup(id: id)
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
