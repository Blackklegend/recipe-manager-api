package org.recipes

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Represents a recipe with all its details")
data class Recipe(
    @Schema(description = "Unique identifier for the recipe", example = "1")
    val id: Long,

    @Schema(description = "Name of the recipe", example = "Chocolate Cake")
    val name: String,

    @Schema(description = "Detailed description of the recipe", example = "A delicious homemade chocolate cake")
    val description: String,

    @Schema(description = "List of ingredients required", example = "[\"flour\", \"sugar\", \"eggs\", \"cocoa powder\"]")
    val ingredients: List<String>,

    @Schema(description = "Step-by-step cooking instructions", example = "Mix dry ingredients, add wet ingredients...")
    val instructions: String,
)
