import shared
import SwiftUI

struct RegisterScreen: View {
    let viewModel = RegisterDiHelper().getViewModel()
    
    @ObservedObject private var state: ObservableState<RegisterState>
    
    init() {
        // swiftlint:disable force_cast
        self.state = ObservableState<RegisterState>(value: viewModel.viewState.value as! RegisterState)
        // swiftlint:disable force_cast

        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<RegisterState> { state in
            self.state.value = state
        }) { _ in
            print("Error ocurred during state collection")
        }
    }

     var body: some View {
         VStack(alignment: .leading) {
             InputView(value: $state.value.login, trailingIcon: {}, onValueChange: { value in
                 viewModel.onLoginChanged(text: value)
             })
             InputView(value: $state.value.password, isPassword: true, trailingIcon: {}, onValueChange: { value in
                 viewModel.onPasswordChanged(text: value)
             })

             Button("Register") {
                 viewModel.onRegisterClicked()
             }.padding(Dimensions.space_32)
         }.padding()
             .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
             .background(Color.colorPrimary)
     }
    
    struct RegisterScreen_Previews: PreviewProvider {
        static var previews: some View {
            RegisterScreen()
        }
    }
}
