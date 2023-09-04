package pl.msiwak.multiplatform.core.data.common

data class FormattedResultData(
    val result: String = "",
    val amount: String = "",
    val date: String = "",
    val isResultError: Boolean = false,
    val isAmountError: Boolean = false,
    val isDateError: Boolean = false,
)