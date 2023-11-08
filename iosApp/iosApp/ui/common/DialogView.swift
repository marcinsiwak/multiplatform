import SwiftUI
import shared

struct DialogView<DialogContent: View>: ViewModifier {
    @Binding var isPresented: Bool // set this to show/hide the dialog
    let onDismiss: (() -> Void)?
    let dialogContent: DialogContent


  init(isPresented: Binding<Bool>,
       onDismiss: (() -> Void)?,
        @ViewBuilder dialogContent: () -> DialogContent) {
      _isPresented = isPresented
      self.onDismiss = onDismiss
     self.dialogContent = dialogContent()
  }

  func body(content: Content) -> some View {
   // wrap the view being modified in a ZStack and render dialog on top of it
    ZStack {
      content
      if isPresented {
        // the semi-transparent overlay
        Rectangle().foregroundColor(Color.black.opacity(0.6))
              .onTapGesture {
                  onDismiss?()
              }
        // the dialog content is in a ZStack to pad it from the edges
        // of the screen
        ZStack {
            dialogContent
            .background(
              RoundedRectangle(cornerRadius: Dimensions.button_corner)
                .foregroundColor(.gray)
            )
        }.padding(Dimensions.space_40)
      }
    }
  }
}

extension View {
  func showDialog<DialogContent: View>(
    isPresented: Binding<Bool>,
    onDismiss: (() -> Void)?,
    @ViewBuilder dialogContent: @escaping () -> DialogContent
  ) -> some View {
    self.modifier(DialogView(isPresented: isPresented, onDismiss: onDismiss, dialogContent: dialogContent))
  }
}
