import SwiftUI

struct ResultInputView: View {
    @State var value: String = ""
    var initValue: String
    var backgroundColor: Color = Color.black.opacity(0)
    var hintText: String = ""
    var isError: Bool = false
    var onValueChange: (String) -> Void
    @FocusState var hasFocus: Bool
    
    init(initValue: String, hintText: String, isError: Bool, onValueChange: @escaping (String) -> Void, hasFocus: Bool) {
        self.initValue = initValue
        self.hintText = hintText
        self.isError = isError
        self.onValueChange = onValueChange
        self.hasFocus = hasFocus
        self.value = initValue
        
        print("STATE33:", initValue)

    }

    var body: some View {
        VStack {
            TextField(
                hintText,
                text: $value.onChange { text in
                    onValueChange(text)
                }
            )
            .multilineTextAlignment(.center)
            .focused($hasFocus)
            .foregroundColor(.white)
            .frame(height: Dimensions.space_40)
            .onChange(of: initValue, perform: { text in
                value = text
            })
            
            Divider()
                .frame(height: Dimensions.space_1)
                .padding(.horizontal, Dimensions.space_32)
                .background(isError ? Color.red: Color.white)
            }
    }
}
