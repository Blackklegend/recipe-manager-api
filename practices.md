Based on my analysis of your Kotlin Spring Boot project, here are the best practices you're missing:

🔴 High Priority Issues:
Missing Null-Safety Annotations

Add Spring Framework nullability annotations (@Nullable, @NotNull) to method parameters and returns in RecipeController
Without these, Spring doesn't know if a parameter can be null, causing potential runtime errors
No Validation

Add @Valid annotation to @RequestBody parameters
Add constraints like @NotBlank, @NotEmpty to your Recipe data class fields
Missing: jakarta.validation:jakarta.validation-api dependency
Architecture Issues

Business logic (recipes list, idCounter) is in the controller—should use Service/Repository pattern
No repository layer or Spring Data abstractions
Controller should only handle HTTP concerns
Error Handling

No @ExceptionHandler or global error handling
No logging
Hard to debug failures
🟡 Medium Priority Issues:
Type Safety in Responses

getAll() returns hardcoded "teste" but should return List<Recipe>
Response type hints are missing; use proper generic types
Missing Kotlin Idioms

Response building could use ResponseEntity.notFound<Recipe>().build()
Consider using .takeIf() or .let() for more idiomatic code
Empty Test Suite

Only skeleton test file; add unit and integration tests
🟢