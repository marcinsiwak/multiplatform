package pl.msiwak.multiplatform.commonObject.exception

class InvalidAuthTokenException(httpCode: Int, httpMessage: String?) :
    ApiException(httpCode, httpMessage)
