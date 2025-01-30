package pl.msiwak.multiplatform.commonObject.exception

class ServerErrorException(httpCode: Int, httpMessage: String?) :
    ApiException(httpCode, httpMessage)
