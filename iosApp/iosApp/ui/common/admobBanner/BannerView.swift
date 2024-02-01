import SwiftUI
import GoogleMobileAds

struct BannerView: View {
    @State var height: CGFloat = 0
    @State var width: CGFloat = 0


    var body: some View {
        BannerAd()
            .frame(height: height)
            .onAppear {
                setFrame()
            }
            .onReceive(NotificationCenter.default.publisher(for: UIDevice.orientationDidChangeNotification)) { _ in
                setFrame()
            }
    }
    
    func setFrame() {
        let safeAreaInsets = (UIApplication.shared.connectedScenes.first as? UIWindowScene)?.keyWindow?.safeAreaInsets ?? .zero
        let frame = UIScreen.main.bounds.inset(by: safeAreaInsets)
        let adSize = GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(frame.width)
        
        self.height = adSize.size.height
    }
}
