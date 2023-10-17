package pl.msiwak.multiplatform.database.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.extension.asFlow

class ExercisesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun updateExercise(exercise: Exercise) {
        dbQuery.updateExercise(
            id = exercise.id,
            categoryId = exercise.categoryId,
            exerciseTitle = exercise.exerciseTitle,
            exerciseType = exercise.exerciseType,
            creationDate = exercise.creationDate
        )
    }

    fun updateExercises(exercises: List<Exercise>) {
        exercises.forEach {
            dbQuery.updateExercise(
                id = it.id,
                categoryId = it.categoryId,
                exerciseTitle = it.exerciseTitle,
                exerciseType = it.exerciseType,
                creationDate = it.creationDate
            )
        }
    }

    fun observeExercise(exerciseId: String): Flow<Exercise> {
        return dbQuery.selectFromExercise(exerciseId, ::mapExercise).asFlow()
            .map { it.executeAsOne() }
    }

    fun removeExercise(exercise: Exercise) {
//        val exercises = dbQuery.getCategoryExercises(exercise.categoryId).executeAsOne()
//        val newExercises = exercises.filter { it.id != exercise.id }
//
//        dbQuery.updateExercise(newExercises, exercise.categoryId)
    }

    private fun mapExercise(
        id: String,
        categoryId: String,
        exerciseTitle: String,
        exerciseType: ExerciseType,
        creationDate: LocalDateTime,
    ): Exercise {
        return Exercise(id, categoryId, exerciseTitle, emptyList(), exerciseType, creationDate)
    }
}