import SwiftUI

struct RunningInputPopup: View {
    let text: String
    let iconResId: String?
    let color: SwiftUI.Color
    
    var body: some View {
        
        HStack(alignment: .center, spacing: Dimensions.space_8) {
            Text(text)
                .foregroundColor(color)
                .padding(.vertical, Dimensions.space_8)
            
            Image(systemName: iconResId ?? "chevron.down")
                .foregroundColor(color)
                .opacity(iconResId != nil ? 1 : 0)
            
        }
    }
}
