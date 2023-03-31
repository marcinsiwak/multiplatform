import SwiftUI

struct ListItemView: View {
    let title: String
    
    private let onClicked: () -> Void
    
    init(title: String, onClicked: @escaping () -> Void) {
        self.title = title
        self.onClicked = onClicked
    }
    
    var body: some View {
        VStack(spacing: 0){
            HStack{
                Text(title).foregroundColor(.white)
                Spacer()
                Image(systemName: "chevron.compact.right").foregroundColor(.white).padding(.horizontal, 8)
            }.padding(.top, 8)
                .padding(.horizontal, 8)
                .rippleEffect(rippleColor: .gray, onClicked: {
                    onClicked()
                })
                
            Divider().background(.white)
        }
    }
}

struct ListItemView_Previews: PreviewProvider {
    static var previews: some View {
        ListItemView(title: "", onClicked: {})
    }
}
