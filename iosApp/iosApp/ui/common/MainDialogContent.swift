import SwiftUI

struct MainDialogContent: View {
    let title: String
    let description: String
    let confirmButtonText: String
    let dismissButtonText: String
    let confirmButtonClicked: () -> Void
    let dismissButtonClicked: () -> Void
    
    var body: some View {
        VStack {
            Text(title)
                .foregroundColor(.onPrimary)
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                .padding()
            
                Text(description)
                    .foregroundColor(.onPrimary)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                    .padding()
            
            Button(action: {
                confirmButtonClicked()
            }, label: {
                Text(confirmButtonText)
                    .padding(Dimensions.space_16)
                    .foregroundColor(Color.colorPrimary)
                    .background(Color.colorTertiary)
                    .clipShape(RoundedCorner())
                    .frame(maxWidth: .infinity, alignment: .center)
            })
            .padding()
            
            Button(action: {
                dismissButtonClicked()
            }, label: {
                Text(dismissButtonText)
                    .padding(Dimensions.space_16)
                    .foregroundColor(Color.colorPrimary)
                    .background(Color.colorTertiary)
                    .clipShape(RoundedCorner())
                    .frame(maxWidth: .infinity, alignment: .center)
            })
            .padding()
        }
    }
}

struct MainDialogContent_Previews: PreviewProvider {
    static var previews: some View {
        MainDialogContent(
            title: "",
            description: "",
            confirmButtonText: "",
            dismissButtonText: "",
            confirmButtonClicked: {},
            dismissButtonClicked: {}
        )
    }
}
