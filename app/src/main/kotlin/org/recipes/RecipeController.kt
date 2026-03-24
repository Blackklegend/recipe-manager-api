package org.recipes

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/recipes")
class RecipeController {

    private val idCounter = AtomicLong(1)
    private val recipes = mutableListOf<Recipe>()

    @GetMapping
    fun getAll(): String = "teste" //List<Recipe> = recipes

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Recipe> {
        val recipe = recipes.find { it.id == id }
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(recipe)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody recipe: Recipe): Recipe {
        val newRecipe = recipe.copy(id = idCounter.getAndIncrement())
        recipes.add(newRecipe)
        return newRecipe
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val index = recipes.indexOfFirst { it.id == id }
        if (index == -1) return ResponseEntity.notFound().build()
        val updated = recipe.copy(id = id)
        recipes[index] = updated
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val removed = recipes.removeIf { it.id == id }
        return if (removed) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}
