import SwiftUI
import shared

struct TextWithDrawableView: View {
    let text: String
    let iconResId: String?
    let color: SwiftUI.Color
    
    var body: some View {
        
        HStack(alignment: .center, spacing: 4) {
            Text(text)
                .foregroundColor(color)
                .padding(.vertical, 8)
            
            Image(systemName: iconResId ?? "chevron.down")
                .foregroundColor(color)
                .opacity(iconResId != nil ? 1 : 0)
            
        }
    }
}
