package pl.msiwak.multiplatform.commonObject

data class FormattedResultData(
    var result: String = "",
    var amount: String = "",
    val date: String = "",
    val isResultError: Boolean = false,
    val isAmountError: Boolean = false,
    val isDateError: Boolean = false,
)