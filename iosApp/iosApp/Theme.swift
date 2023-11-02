import SwiftUI
import shared

extension SwiftUI.Color {
    static let colorPrimary = Color(MR.colors().colorPrimary.getUIColor())
    static let onPrimary = Color(MR.colors().colorOnPrimary.getUIColor())
    static let colorSecondary = Color(MR.colors().colorSecondary.getUIColor())
    static let onSecondary = Color(MR.colors().colorOnSecondary.getUIColor())
    static let colorTertiary = Color(MR.colors().colorTertiary.getUIColor())
    static let onTertiary = Color(MR.colors().colorOnTertiary.getUIColor())
    static let surface = Color(MR.colors().colorSurface.getUIColor())
    static let onSurface = Color(MR.colors().colorOnSurface.getUIColor())
    static let background = Color(MR.colors().background.getUIColor())
    static let error = Color(MR.colors().colorError.getUIColor())
    static let onError = Color(MR.colors().colorOnError.getUIColor())
    
    static let gray = Color(MR.colors().gray.getUIColor())
}
