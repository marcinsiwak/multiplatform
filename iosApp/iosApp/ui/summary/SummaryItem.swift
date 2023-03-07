import SwiftUI

struct SummaryItem: View {
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Turtle Rock")
                .font(.title)
            HStack {
                Text("Joshua Tree National Park")
                    .font(.subheadline)
                Spacer()
                Text("California")
                    .font(.subheadline)
            }
        }
        .background(
        Image(<#T##name: String##String#>))
        .padding()
    }
    
}

struct SummaryItem_Previews: PreviewProvider {
    static var previews: some View {
        SummaryItem()
    }
}
