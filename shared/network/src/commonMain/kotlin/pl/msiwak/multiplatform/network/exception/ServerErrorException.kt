package pl.msiwak.multiplatform.network.exception

import pl.msiwak.multiplatform.commonObject.exception.ApiException

class ServerErrorException(httpCode: Int, httpMessage: String?) : ApiException(httpCode, httpMessage)
