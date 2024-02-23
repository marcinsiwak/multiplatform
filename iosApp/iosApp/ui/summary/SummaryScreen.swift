import shared
import SwiftUI

struct SummaryScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        SummaryScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
