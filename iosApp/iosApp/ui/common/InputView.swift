import SwiftUI

struct InputView<TrailingIcon: View>: View {
    @Binding var value: String
    var backgroundColor = Color.black
    var errorMessage: String? = nil
    var isPassword = false
    var hintText = ""
    var readOnly = false
    var errorsEnabled = false
    @ViewBuilder let trailingIcon: () -> TrailingIcon
    var onValueChange: (String) -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: Dimensions.space_8) {
            TextField(hintText, text: $value.onChange({ text in
                onValueChange(text)
            }))
                .padding()
                .background(backgroundColor)
                .cornerRadius(Dimensions.input_view_corner)
                .overlay(RoundedRectangle(cornerRadius: Dimensions.input_view_corner).stroke(Color.gray, lineWidth: 1))
                .overlay(trailingIcon().padding(.trailing, Dimensions.space_8).padding(.top, Dimensions.space_12), alignment: .trailing)
                .disabled(readOnly)
                .foregroundColor(.white)
                .textContentType(isPassword ? .password : .none)
            
            if errorsEnabled {
                Text(errorMessage ?? "")
                    .foregroundColor(.red)
                    .opacity(errorMessage != nil ? 1 : 0)
                    .padding(.bottom, Dimensions.space_8)
            }
        }
    }
}

// struct InputView_Previews: PreviewProvider {
//     static var previews: some View {
//         InputView()
//     }
// }
