package pl.msiwak.multiplatform.network

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

@Serializable
data class AuthResponse(
    val federatedId: String,
    val providerId: String,
    val email: String,
    val emailVerified: Boolean,
    val firstName: String,
    val fullName: String,
    val lastName: String,
    val photoUrl: String,
    val localId: String,
    val displayName: String,
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String,
    val oauthIdToken: String,
    @Serializable(with = RawUserInfoSerializer::class)
    val rawUserInfo: RawUserInfo,
    val kind: String
)

@Serializable
data class RawUserInfo(
    val iss: String,
    val azp: String,
    val aud: String,
    val sub: String,
    val email: String,
    val email_verified: Boolean,
    val nbf: Long,
    val name: String,
    val picture: String,
    val given_name: String,
    val family_name: String,
    val iat: Long,
    val exp: Long,
    val jti: String
)

object RawUserInfoSerializer : KSerializer<RawUserInfo> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RawUserInfo", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: RawUserInfo) {
        val jsonString = Json.encodeToString(value)
        encoder.encodeString(jsonString)
    }

    override fun deserialize(decoder: Decoder): RawUserInfo {
        val jsonString = decoder.decodeString()
        return Json.decodeFromString(jsonString)
    }
}
