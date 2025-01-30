package pl.msiwak.multiplatform.commonObject.exception

class UnknownApiException(httpCode: Int, httpMessage: String?) : ApiException(httpCode, httpMessage)
