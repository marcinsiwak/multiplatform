import SwiftUI
import shared

struct RegisterScreen: View {
    
//    let authRepo: FirebaseAuthorization = FirebaseAuthorization()


    @State var username: String = ""
    @State var password: String = ""

     var body: some View {
         VStack(alignment: .leading) {
             Text("Username")
                 .font(.callout)
                 .bold()
             TextField("Enter username...", text: $username)
                 .textFieldStyle(RoundedBorderTextFieldStyle())

             Text("Password")
                 .font(.callout)
                 .bold()
             TextField("Enter password...", text: $password)
                 .textFieldStyle(RoundedBorderTextFieldStyle())


             Button("Register") {
                 Task {
                     do {
//                         try await authRepo.createNewUser(email: username, password: password)
                     } catch {
                         print(error)
                     }
                 }
             }.padding(Dimensions.space_32)
         }.padding()

     }
    
    struct RegisterScreen_Previews: PreviewProvider {
        static var previews: some View {
            RegisterScreen()
        }
    }
}
