import shared
import SwiftUI


struct AddExerciseScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        AddExerciseScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
