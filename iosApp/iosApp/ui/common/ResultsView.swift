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
                .foregroundColor(.white)
                .padding(16)
            Text(amount)
                .frame(maxWidth: .infinity)
                .foregroundColor(.white)
                .padding(16)
            Text(date)
                .frame(maxWidth: .infinity)
                .foregroundColor(.white)
                .padding(16)
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
