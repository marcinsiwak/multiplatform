import SwiftUI
import shared

struct OfflineBanner: View {
    let onSignInUpClicked: () -> Void

    var body: some View {
        VStack {
            Spacer()
            HStack {
                Text(MR.strings().offline_mode_title.desc().localized())
                    .padding(8)
                    .foregroundColor(Color.white)
                
                Spacer()
                                
                Button(action: onSignInUpClicked) {
                    HStack {
                        Text(MR.strings().offline_mode_sing_up_in.desc().localized())
                            .foregroundColor(Color.white)
                            .padding(8)
                        
                        Image(uiImage: MR.images().ic_arrow_right.toUIImage()!)
                            .foregroundColor(Color.white)
                    }
                }
            }
            .background(Color.secondary)
            .cornerRadius(4)
            .onTapGesture {
                onSignInUpClicked()
            }
        }
    }
}

struct OfflineBanner_Previews: PreviewProvider {
    static var previews: some View {
        OfflineBanner(onSignInUpClicked: {})
    }
}
