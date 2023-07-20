import SwiftUI
import shared

struct RegisterScreen: View {
    
    let viewModel = RegisterDiHelper().getViewModel()
    
    @ObservedObject private var state: ObservableState<RegisterState>
    
    init() {
        self.state = ObservableState<RegisterState>(value: viewModel.viewState.value as! RegisterState)
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<RegisterState>{ state in
            self.state.value = state
            
        }) { error in
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
             .background(Color.black)

     }
    
    struct RegisterScreen_Previews: PreviewProvider {
        static var previews: some View {
            RegisterScreen()
        }
    }
}
