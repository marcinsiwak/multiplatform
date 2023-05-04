import SwiftUI

struct InputView<TrailingIcon: View>: View {
    @Binding var value: String
    var backgroundColor: Color = Color.black
    var errorMessage: String? = nil
    var isPassword: Bool = false
    var hintText: String = ""
    var readOnly: Bool = false
    var errorsEnabled: Bool = false
    @ViewBuilder let trailingIcon: () -> TrailingIcon
    var onValueChange: (String) -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            TextField("", text: $value.onChange({ text in
                onValueChange(text)
            }))                .foregroundColor(.white)

                .padding()
                .background( ZStack{
                   backgroundColor
                        if value.count == 0 {
                            HStack {
                                Text(hintText)
                                .fontWeight(.medium)
                                    .foregroundColor(.gray)
                                    .padding(.horizontal)
                                Spacer()
                          }
                         .frame(maxWidth: .infinity)
                        }
                    })
                .cornerRadius(12)
                .overlay(RoundedRectangle(cornerRadius: 12).stroke(Color.gray, lineWidth: 1))
                .overlay(trailingIcon().padding(.trailing, 8).padding(.top, 12), alignment: .trailing)
                .disabled(readOnly)
                .foregroundColor(.white)
                .textContentType(isPassword ? .password : .none)
            
            if errorsEnabled {
                Text(errorMessage ?? "")
                    .foregroundColor(.red)
                    .opacity(errorMessage != nil ? 1 : 0)
                    .padding(.bottom, 8)
            }
        }
    }
}

//struct InputView_Previews: PreviewProvider {
//    static var previews: some View {
//        InputView()
//    }
//}
