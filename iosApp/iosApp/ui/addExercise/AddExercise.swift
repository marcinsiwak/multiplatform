import shared
import SwiftUI


struct AddExerciseScreen: UIViewControllerRepresentable {
    let id: String

    func makeUIViewController(context: Context) -> some UIViewController {
        AddExerciseScreenControllerKt.setup(id: id)
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
}
