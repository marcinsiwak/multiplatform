package pl.msiwak.queries

import pl.msiwak.dtos.ExerciseDTO

interface GetExerciseQuery {
    suspend fun invoke(id: String, userId: String): ExerciseDTO?
}