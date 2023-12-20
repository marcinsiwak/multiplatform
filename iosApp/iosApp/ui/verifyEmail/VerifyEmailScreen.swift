import shared
import SwiftUI

struct VerifyEmailScreen: View {
    
    let viewModel = VerifyEmailDiHelper().getViewModel()

    
    var body: some View {
        
        VStack {
            Text(ResourcesHelper().getMyString(stringId: MR.strings().verify_title).localized())
            Text(ResourcesHelper().getMyString(stringId: MR.strings().verify_description).localized())
            
            Button(action: {
                viewModel.onOpenMailClicked()
            }) {
                Text(ResourcesHelper().getMyString(stringId: MR.strings().verify_open_mail).localized())
            }
            Button(action: {
                viewModel.onResendMailClicked()
            }) {
                Text(ResourcesHelper().getMyString(stringId: MR.strings().verify_resend_mail).localized())
            }
            Button(action: {
                viewModel.onLoginClicked()
            }) {
                Text(ResourcesHelper().getMyString(stringId: MR.strings().verify_login).localized())
            }
        }
        .padding()
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
        .background(Color.colorPrimary)
    }
}

struct VerifyEmailScreen_Previews: PreviewProvider {
    static var previews: some View {
        VerifyEmailScreen()
    }
}
