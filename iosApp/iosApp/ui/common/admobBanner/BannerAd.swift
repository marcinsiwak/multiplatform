import SwiftUI
import GoogleMobileAds

struct BannerAd: UIViewControllerRepresentable {
    private let bannerView = GADBannerView()
    // ca-app-pub-3676765754881099/5447473655
    private let adUnitID = "ca-app-pub-3940256099942544/2934735716"

    func makeUIViewController(context: Context) -> some UIViewController {
        let bannerViewController = BannerViewController()
        bannerView.adUnitID = adUnitID
        bannerView.rootViewController = bannerViewController
        bannerViewController.view.addSubview(bannerView)
        bannerViewController.view.frame = CGRect(origin: .zero, size: bannerView.adSize.size)
        return bannerViewController
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        let safeAreaInsets = (UIApplication.shared.connectedScenes.first as? UIWindowScene)?.keyWindow?.safeAreaInsets ?? .zero
        let frame = UIScreen.main.bounds.inset(by: safeAreaInsets)
        let adSize = GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(frame.width)
        
        bannerView.adSize = adSize
        bannerView.load(GADRequest())
    }
}
