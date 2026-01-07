package pl.msiwak.multiplatform.ui.commonComponent

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo
import platform.AuthenticationServices.ASAuthorization
import platform.AuthenticationServices.ASAuthorizationAppleIDCredential
import platform.AuthenticationServices.ASAuthorizationAppleIDProvider
import platform.AuthenticationServices.ASAuthorizationController
import platform.AuthenticationServices.ASAuthorizationControllerDelegateProtocol
import platform.AuthenticationServices.ASAuthorizationControllerPresentationContextProvidingProtocol
import platform.AuthenticationServices.ASAuthorizationScopeEmail
import platform.AuthenticationServices.ASAuthorizationScopeFullName
import platform.AuthenticationServices.ASPresentationAnchor
import platform.CoreCrypto.CC_SHA256
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.Foundation.NSError
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.darwin.NSObject
import kotlin.random.Random

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AppleSignInManager() :
    NSObject(),
    ASAuthorizationControllerDelegateProtocol,
    ASAuthorizationControllerPresentationContextProvidingProtocol {

    private var callback: ((String, String) -> Unit)? = null
    private var currentNonce: String? = null

    fun signIn(callback: (String, String) -> Unit) {
        this.callback = callback
        currentNonce = randomNonceString()

        val provider = ASAuthorizationAppleIDProvider()
        val request = provider.createRequest()
        request.requestedScopes = listOf(
            ASAuthorizationScopeFullName,
            ASAuthorizationScopeEmail
        )
        request.nonce = sha256(currentNonce!!)

        val controller = ASAuthorizationController(listOf(request))
        controller.delegate = this
        controller.presentationContextProvider = this
        controller.performRequests()
    }

    @OptIn(BetaInteropApi::class)
    override fun authorizationController(
        controller: ASAuthorizationController,
        didCompleteWithAuthorization: ASAuthorization
    ) {
        val credential = didCompleteWithAuthorization.credential
                as? ASAuthorizationAppleIDCredential

        val tokenData = credential?.identityToken
        val tokenString = tokenData?.let {
            NSString.create(it, NSUTF8StringEncoding).toString()
        }

        val nonce = currentNonce

        if (tokenString == null || nonce == null) {
            throw Exception("Unable to fetch Apple ID token")
        }

        callback?.invoke(tokenString, nonce)
    }

    override fun authorizationController(
        controller: ASAuthorizationController,
        didCompleteWithError: NSError
    ) {
        throw Exception(didCompleteWithError.localizedDescription)
    }

    override fun presentationAnchorForAuthorizationController(
        controller: ASAuthorizationController
    ): ASPresentationAnchor {
        return UIApplication.sharedApplication.keyWindow
            ?: UIApplication.sharedApplication.windows.first() as UIWindow
    }

    private fun sha256(input: String): String {
        val data = input.encodeToByteArray()
        val hash = UByteArray(CC_SHA256_DIGEST_LENGTH)

        memScoped {
            CC_SHA256(
                data.refTo(0),
                data.size.convert(),
                hash.refTo(0)
            )
        }

        return hash.joinToString("") { it.toString(16).padStart(2, '0') }
    }

    private fun randomNonceString(length: Int = 32): String {
        val charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz-._"
        return (1..length)
            .map { charset[Random.nextInt(charset.length)] }
            .joinToString("")
    }
}