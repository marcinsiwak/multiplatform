import SwiftUI

struct ResultInputView: View {
    @Binding var value: String
    var backgroundColor: Color = Color.black.opacity(0)
    var hintText: String = ""
    var isError: Bool = false
    var onValueChange: (String) -> Void

    var body: some View {
        VStack {
            TextField("", text: $value.onChange({ text in
                onValueChange(text)
                }))
                    .foregroundColor(.white)
                    .padding(.horizontal, 8)
                    .frame(height: 44)
            
            Divider()
                .frame(height: 1)
                .padding(.horizontal, 30)
                .background(isError ? Color.red: Color.white)
            }
    }
}
