package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.ExerciseDTO

interface AddExerciseUseCase {
    suspend operator fun invoke(categoryId: String, name: String): ExerciseDTO?
}