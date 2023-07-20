import SwiftUI
import shared

struct VerifyEmailScreen: View {
    
    let viewModel = VerifyEmailDiHelper().getViewModel()

    
    var body: some View {
        
        VStack {
            Text(MR.strings().verify_title.desc().localized())
            Text(MR.strings().verify_description.desc().localized())
            
            Button(action: {
                viewModel.onOpenMailClicked()
            }) {
                Text(MR.strings().verify_open_mail.desc().localized())
            }
            Button(action: {
                viewModel.onResendMailClicked()
            }) {
                Text(MR.strings().verify_resend_mail.desc().localized())
            }
            Button(action: {
                viewModel.onLoginClicked()
            }) {
                Text(MR.strings().verify_login.desc().localized())
            }
        }
        .padding()
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
        .background(Color.black)
    }
}

struct VerifyEmailScreen_Previews: PreviewProvider {
    static var previews: some View {
        VerifyEmailScreen()
    }
}
