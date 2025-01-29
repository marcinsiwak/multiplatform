package pl.msiwak.multiplatform.commonObject.exception

class ClientErrorException(httpCode: Int, httpMessage: String?) :
    ApiException(httpCode, httpMessage)
