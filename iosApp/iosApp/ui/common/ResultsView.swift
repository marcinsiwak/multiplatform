import SwiftUI

struct ResultView: View {
    let result: String
    let amount: String
    let date: String
    let onResultLongClick: () -> Void
    
    var body: some View {
        HStack(spacing: 0) {
            Text(result)
                .frame(maxWidth: .infinity)
                .padding(16)
                .background(Color.gray)
            Text(amount)
                .frame(maxWidth: .infinity)
                .padding(16)
                .background(Color.gray)
            Text(date)
                .frame(maxWidth: .infinity)
                .padding(16)
                .background(Color.gray)
        }
        .contentShape(Rectangle())
        .onTapGesture {
            // handle tap
        }
        .onLongPressGesture {
            onResultLongClick()
        }
    }
}
