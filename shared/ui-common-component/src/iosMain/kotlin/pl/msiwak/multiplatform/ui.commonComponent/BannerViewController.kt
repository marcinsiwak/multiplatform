package pl.msiwak.multiplatform.ui.commonComponent

import cocoapods.GoogleMobileAds.GADBannerView
import cocoapods.GoogleMobileAds.GADBannerViewDelegateProtocol
import cocoapods.GoogleMobileAds.GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth
import cocoapods.GoogleMobileAds.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.Foundation.NSBundle
import platform.UIKit.UIEdgeInsetsInsetRect
import platform.UIKit.UIViewController

// ca-app-pub-3676765754881099/5447473655
private const val adUnitID = "ca-app-pub-3940256099942544/2435281174"

@OptIn(ExperimentalForeignApi::class)
class BannerViewController(
    nibName: String? = null,
    bundle: NSBundle? = null
) : UIViewController(nibName, bundle), GADBannerViewDelegateProtocol {

    @OptIn(ExperimentalForeignApi::class)
    override fun viewDidLoad() {
        super.viewDidLoad()

        val frame = UIEdgeInsetsInsetRect(view.frame, view.safeAreaInsets)
        val viewWidth = frame.useContents { this.size.width }
        val size = GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(viewWidth)
        val bannerView = GADBannerView(adSize = size)

        view.addSubview(bannerView)
        bannerView.setAdUnitID(adUnitID)
        bannerView.setRootViewController(this)
        bannerView.loadRequest(GADRequest())
        bannerView.delegate = this
    }

    override fun bannerViewDidReceiveAd(bannerView: GADBannerView) {
        view.addSubview(bannerView)
    }
}
