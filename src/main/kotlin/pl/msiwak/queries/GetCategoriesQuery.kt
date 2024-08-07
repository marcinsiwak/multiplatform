package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO

interface GetCategoriesQuery {
    suspend fun invoke(): List<CategoryDTO>
}