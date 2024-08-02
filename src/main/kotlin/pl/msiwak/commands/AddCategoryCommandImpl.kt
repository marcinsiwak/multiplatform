package pl.msiwak.commands

import pl.msiwak.entities.CategoryEntity
import pl.msiwak.repositories.CategoryRepository

class AddCategoryCommandImpl(private val categoryRepository: CategoryRepository) : AddCategoryCommand {
    override suspend fun invoke(userId: String, name: String, exerciseType: String) {
        val category = CategoryEntity(userId = userId, name = name, exerciseType = exerciseType)
        categoryRepository.addCategory(category)
    }
}
