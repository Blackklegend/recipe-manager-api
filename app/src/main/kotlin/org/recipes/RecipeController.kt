package org.recipes

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@Tag(name = "Recipes", description = "Operations for managing recipes")
@RestController
@RequestMapping("/recipes")
class RecipeController {

    private val idCounter = AtomicLong(1)
    private val recipes = mutableListOf<Recipe>()

    @GetMapping
    @Operation(summary = "Get all recipes", description = "Retrieve a list of all available recipes")
    @ApiResponse(responseCode = "200", description = "List of recipes retrieved successfully")
    fun getAll(): String = "teste" //List<Recipe> = recipes

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Retrieve a specific recipe by its ID")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Recipe found and returned"),
        ApiResponse(responseCode = "404", description = "Recipe not found")
    )
    fun getById(
        @Parameter(description = "Recipe ID")
        @PathVariable id: Long
    ): ResponseEntity<Recipe> {
        val recipe = recipes.find { it.id == id }
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(recipe)
    }

    @PostMapping
    @Operation(summary = "Create a new recipe", description = "Add a new recipe to the database")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "Recipe created successfully"),
        ApiResponse(responseCode = "400", description = "Invalid request body")
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody recipe: Recipe): Recipe {
        val newRecipe = recipe.copy(id = idCounter.getAndIncrement())
        recipes.add(newRecipe)
        return newRecipe
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a recipe", description = "Update an existing recipe by its ID")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Recipe updated successfully"),
        ApiResponse(responseCode = "404", description = "Recipe not found"),
        ApiResponse(responseCode = "400", description = "Invalid request body")
    )
    fun update(
        @Parameter(description = "Recipe ID")
        @PathVariable id: Long,
        @RequestBody recipe: Recipe
    ): ResponseEntity<Recipe> {
        val index = recipes.indexOfFirst { it.id == id }
        if (index == -1) return ResponseEntity.notFound().build()
        val updated = recipe.copy(id = id)
        recipes[index] = updated
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a recipe", description = "Remove a recipe from the database by its ID")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "Recipe deleted successfully"),
        ApiResponse(responseCode = "404", description = "Recipe not found")
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @Parameter(description = "Recipe ID")
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        val removed = recipes.removeIf { it.id == id }
        return if (removed) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}
