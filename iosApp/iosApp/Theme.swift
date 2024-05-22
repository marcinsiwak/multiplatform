import shared
import SwiftUI

extension SwiftUI.Color {
    static let colorPrimary = Color.p(SR.colors().colorPrimary.getUIColor())
    static let onPrimary = Color(SR.colors().colorOnPrimary.getUIColor())
    static let colorSecondary = Color(SR.colors().colorSecondary.getUIColor())
    static let onSecondary = Color(SR.colors().colorOnSecondary.getUIColor())
    static let colorTertiary = Color(SR.colors().colorTertiary.getUIColor())
    static let onTertiary = Color(SR.colors().colorOnTertiary.getUIColor())
    static let surface = Color(SR.colors().colorSurface.getUIColor())
    static let onSurface = Color(SR.colors().colorOnSurface.getUIColor())
    static let background = Color(SR.colors().background.getUIColor())
    static let error = Color(SR.colors().colorError.getUIColor())
    static let onError = Color(SR.colors().colorOnError.getUIColor())
    
    static let gray = Color(SR.colors().gray.getUIColor())
}
