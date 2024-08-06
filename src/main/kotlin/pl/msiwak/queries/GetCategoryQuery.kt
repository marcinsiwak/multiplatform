package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO

interface GetCategoryQuery {
    suspend fun invoke(id: String, userId: String): CategoryDTO?
}