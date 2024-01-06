package pl.msiwak.multiplatform.network.exception

import pl.msiwak.multiplatform.commonObject.exception.ApiException

class InvalidAuthTokenException(httpCode: Int, httpMessage: String?) : ApiException(httpCode, httpMessage)
