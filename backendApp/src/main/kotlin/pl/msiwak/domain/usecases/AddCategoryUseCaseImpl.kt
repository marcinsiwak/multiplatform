package pl.msiwak.domain.usecases

import org.jetbrains.exposed.sql.not
import pl.msiwak.infrastructure.entities.CategoryEntity
import pl.msiwak.infrastructure.repositories.ExerciseRepository
import pl.msiwak.infrastructure.repositories.NotificationRepository
import pl.msiwak.infrastructure.repositories.UserRepository
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory

class AddCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiCategoryMapper: ApiCategoryMapper,
    private val notificationRepository: NotificationRepository,
    private val userRepository: UserRepository
) : pl.msiwak.domain.usecases.AddCategoryUseCase {
    override suspend operator fun invoke(name: String, exerciseType: String, userId: String): ApiCategory {
        val category = CategoryEntity(
            userId = userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
        val user = userRepository.getUser(userId)
        user?.deviceToken?.let { notificationRepository.sendPushNotification(it, "Category added", "Hey") }
        return apiCategoryMapper(category)
    }
}
