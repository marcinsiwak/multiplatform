import SwiftUI

struct ListItemView: View {
    let title: String
    
    init(title: String) {
        self.title = title
    }
    
    var body: some View {
        VStack(spacing: 0){
            HStack{
                Text(title).foregroundColor(.white)
                Spacer()
                Image(systemName: "chevron.compact.right").foregroundColor(.white).padding(.horizontal, 8)
            }.padding(.top, 8)
                .padding(.horizontal, 8)
                .rippleEffect(rippleColor: .gray)
                
            Divider().background(.white)
        }
    }
}

struct ListItemView_Previews: PreviewProvider {
    static var previews: some View {
        ListItemView(title: "")
    }
}
