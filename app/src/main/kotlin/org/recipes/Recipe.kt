package org.recipes

data class Recipe(
    val id: Long,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: String,
)
