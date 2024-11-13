package pl.msiwak.multiplatform.utils.validators

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_COMMA
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_DOT

class ResultValidator {
    fun validate(
        result: String,
        amount: String,
        date: String,
        exerciseType: ExerciseType
    ): ResultValidationResponse? {
        if (result.isEmpty()) {
            return ResultValidationResponse.IncorrectResult
        }
        if (amount.isEmpty()) {
            return ResultValidationResponse.IncorrectAmount
        }
        if (date.isEmpty()) {
            return ResultValidationResponse.IncorrectDate
        }

        if (!isInputGymCorrect(result, exerciseType)) {
            return ResultValidationResponse.IncorrectResult
        }

        if (amount.isEmpty() && exerciseType == ExerciseType.RUNNING) {
            return ResultValidationResponse.IncorrectAmount
        }
        return null
    }

    private fun isInputGymCorrect(savedResult: String, exerciseType: ExerciseType): Boolean {
        return isCorrectRegex(savedResult, Regex(NUMBER_REGEX_DOT)) || isCorrectRegex(
            savedResult,
            Regex(NUMBER_REGEX_COMMA)
        ) && exerciseType == ExerciseType.GYM
    }

    private fun isCorrectRegex(input: String, regex: Regex): Boolean {
        return input.matches(regex)
    }

    sealed class ResultValidationResponse {
        data object IncorrectAmount : ResultValidationResponse()
        data object IncorrectResult : ResultValidationResponse()
        data object IncorrectDate : ResultValidationResponse()
    }
}
