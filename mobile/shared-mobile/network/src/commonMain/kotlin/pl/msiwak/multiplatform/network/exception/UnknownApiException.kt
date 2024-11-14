package pl.msiwak.multiplatform.network.exception

import pl.msiwak.multiplatform.commonObject.exception.ApiException

class UnknownApiException(httpCode: Int, httpMessage: String?) : ApiException(httpCode, httpMessage)
