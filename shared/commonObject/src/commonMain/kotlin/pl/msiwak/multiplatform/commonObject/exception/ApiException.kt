package pl.msiwak.multiplatform.commonObject.exception

abstract class ApiException(val httpCode: Int, val httpMessage: String?) : Exception(httpMessage)
