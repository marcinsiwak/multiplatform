import shared
import SwiftUI

struct AddCategoryScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        AddCategoryScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
