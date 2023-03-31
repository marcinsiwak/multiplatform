import SwiftUI

extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape( RoundedCorner(radius: radius, corners: corners) )
    }
}

struct RoundedCorner: Shape {

    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}

extension Binding {
    func onChange(_ handler: @escaping (Value) -> Void) -> Binding<Value> {
        Binding(
            get: { self.wrappedValue },
            set: { newValue in
                self.wrappedValue = newValue
                handler(newValue)
            }
        )
    }
}

struct Ripple: ViewModifier {
    // MARK: Lifecycle
    private let onClicked: () -> Void

    init(rippleColor: Color, onClicked: @escaping () -> Void) {
        self.color = rippleColor
        self.onClicked = onClicked
    }

    // MARK: Internal

    let color: Color

    @State private var scale: CGFloat = 0.5
    
    @State private var animationPosition: CGFloat = 0.0
    @State private var x: CGFloat = 0.0
    @State private var y: CGFloat = 0.0
    
    @State private var opacityFraction: CGFloat = 0.0
    
    let timeInterval: TimeInterval = 0.5
    
    func body(content: Content) -> some View {
        GeometryReader { geometry in
            ZStack {
                Rectangle()
                    .foregroundColor(.gray.opacity(0.05))
                Circle()
                    .foregroundColor(color)
                    .opacity(0.2*opacityFraction)
                    .scaleEffect(scale)
                    .offset(x: x, y: y)
                content
            }
            .onTapGesture(perform: { location in
                x = location.x-geometry.size.width/2
                y = location.y-geometry.size.height/2
                opacityFraction = 1.0
                withAnimation(.linear(duration: timeInterval)) {
                    scale = 3.0*(max(geometry.size.height, geometry.size.width)/min(geometry.size.height, geometry.size.width))
                    opacityFraction = 0.0
                    DispatchQueue.main.asyncAfter(deadline: .now() + timeInterval) {
                        scale = 1.0
                        opacityFraction = 0.0
                    }
                }
                onClicked()
            })
            .clipped()
        }
    }
}

extension View {
    func rippleEffect(rippleColor: Color = .accentColor.opacity(0.5), onClicked: @escaping () -> Void) -> some View {
        modifier(Ripple(rippleColor: rippleColor, onClicked: onClicked))
    }
}
