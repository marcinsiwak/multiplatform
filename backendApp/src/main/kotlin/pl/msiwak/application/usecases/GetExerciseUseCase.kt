package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.ExerciseDTO

interface GetExerciseUseCase {
    suspend operator fun invoke(id: String): ExerciseDTO?
}