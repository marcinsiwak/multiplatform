import SwiftUI

struct ResultInputView: View {
    @Binding var value: String
    var backgroundColor: Color = Color.black.opacity(0)
    var hintText: String = ""
    var isError: Bool = false
    var onValueChange: (String) -> Void
    @FocusState.Binding var hasFocus: Bool

    var body: some View {
        VStack {
            TextField("", text: $value.onChange({ text in
                onValueChange(text)
                }))
            .placeholder(when: value.isEmpty, placeholder: {
                Text(hintText)
                    .foregroundColor(.gray)
            })
            .focused($hasFocus)
                    .foregroundColor(.white)
                    .padding(.horizontal, Dimensions.space_8)
                    .frame(height: Dimensions.space_40)
            
            Divider()
                .frame(height: Dimensions.space_1)
                .padding(.horizontal, Dimensions.space_32)
                .background(isError ? Color.red: Color.white)
            }
    }
}
