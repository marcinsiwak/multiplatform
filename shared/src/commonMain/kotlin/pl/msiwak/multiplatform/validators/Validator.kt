package pl.msiwak.multiplatform.validators

class Validator {

    fun validateEmail(mail: String): Boolean {
        return mail.trim().matches(Regex(EMAIL_REGEX))
    }

    fun validatePassword(password: String): Boolean {
        return password.matches(Regex(PASSWORD_REGEX))
    }

    fun isCorrectPasswordLength(password: String): Boolean {
        return password.length >= PASSWORD_LENGTH
    }

    fun hasPasswordNumber(password: String): Boolean {
        return password.contains(Regex(PASSWORD_HAS_NUMBER_REGEX))
    }

    fun hasPasswordLetter(password: String): Boolean {
        return password.contains(Regex(PASSWORD_HAS_LETTER_REGEX))
    }

    fun hasPasswordCapitalLetter(password: String): Boolean {
        return password.contains(Regex(PASSWORD_HAS_CAPITAL_LETTER_REGEX))
    }

    fun hasPasswordSpecialCharacter(password: String): Boolean {
        return password.contains(Regex(PASSWORD_HAS_SPECIAL_CHARACTER_REGEX))
    }

    companion object {
        const val EMAIL_REGEX = "(?:[a-z\\d!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z\\d!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z\\d](?:[a-z\\d-]*[a-z\\d])?\\.)+[a-z\\d](?:[a-z\\d-]*[a-z\\d])?|\\[(?:(?:(2(5[0-5]|[0-4]\\d)|1\\d\\d|[1-9]?\\d))\\.){3}(?:(2(5[0-5]|[0-4]\\d)|1\\d\\d|[1-9]?\\d)|[a-z\\d-]*[a-z\\d]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}"
        const val PASSWORD_HAS_NUMBER_REGEX = "(?=.*[0-9])"
        const val PASSWORD_HAS_LETTER_REGEX = "(?=.*[a-z])"
        const val PASSWORD_HAS_CAPITAL_LETTER_REGEX = "(?=.*[A-Z])"
        const val PASSWORD_HAS_SPECIAL_CHARACTER_REGEX = "(?=.*[@#\$%!\\-_?&])(?=\\S+\$)"
        const val PASSWORD_LENGTH = 8
    }
}