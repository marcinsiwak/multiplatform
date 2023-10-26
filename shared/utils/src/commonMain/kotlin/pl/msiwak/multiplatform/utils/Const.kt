package pl.msiwak.multiplatform.utils

const val DATE_REGEX = "^([0-2][0-9]||3[0-1]).(0[0-9]||1[0-2]).([0-9][0-9])?[0-9][0-9]\$"
const val DATE_FORMAT = "dd.MM.yyyy"
const val DATE_TIME_FORMAT = "dd.MM.yyyy'T'HH:mm:ss"
const val DATE_TIME_WITH_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

const val NUMBER_REGEX_DOT = "[0-9]*\\.?[0-9]*"
const val NUMBER_REGEX_COMMA = "[0-9]*\\,?[0-9]*"

const val TIME_REGEX = "^(?:\\d{1,2}:)?(?:\\d{1,2}:)?(?:\\d{1,2})?(?:[.:]\\d{1,3})?\$"