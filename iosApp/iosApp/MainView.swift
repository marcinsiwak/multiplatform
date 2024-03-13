import shared
import SwiftUI

struct MainView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
    
        let contentView = ContentView()
        let hostingController = UIHostingController(rootView: contentView)
        
        return MainViewControllerKt.setup(view: hostingController.view)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
