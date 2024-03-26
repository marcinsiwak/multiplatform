import shared
import SwiftUI

struct VerifyEmailScreen: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> some UIViewController {
        VerifyEmailScreenControllerKt.setup()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}

//struct VerifyEmailScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        VerifyEmailScreen()
//    }
//}
