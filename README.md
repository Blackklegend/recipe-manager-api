# Recipe Manager API

Backend REST API for Recipe Manager built with Kotlin and Spring Boot.

## ✨ Tech Stack

- **Language:** Kotlin
- **Framework:** Spring Boot
- **Database:** MongoDB
- **Build Tool:** Gradle
- **Testing:** JUnit, MockK
- **Documentation:** OpenAPI/Swagger

## 🔄 API Endpoints

| Method | Endpoint                         | Description             |
| ------ | -------------------------------- | ----------------------- |
| GET    | /api/recipes                     | Get all recipes         |
| GET    | /api/recipes/{id}                | Get recipe by ID        |
| GET    | /api/recipes/category/{category} | Get recipes by category |
| POST   | /api/recipes                     | Create new recipe       |
| PUT    | /api/recipes/{id}                | Update recipe           |
| DELETE | /api/recipes/{id}                | Delete recipe           |
| GET    | /api/categories                  | Get all categories      |

## 💁‍♂️ Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── org/recipes/
│   │       ├── controllers/    # API endpoints
│   │       ├── models/         # Data classes
│   │       ├── repositories/   # Database operations
│   │       └── services/       # Business logic
│   └── resources/
│       └── application.properties
└── test/
```

## 🗂️ Data Models

```kotlin
data class Recipe(
		@Id val id: String? = null,
		val name: String,
		val category: String,
		val prepTime: Int,
		val cookTime: Int,
		val difficulty: Difficulty,
		val servings: Int,
		val ingredients: List<Ingredient>,
		val instructions: List<String>,
		val isFavorite: Boolean = false
)

data class Ingredient(
		val name: String,
		val quantity: Double,
		val unit: String
)

enum class Difficulty {
		EASY, MEDIUM, HARD
}
```

## 🛠️ Setup

1. Clone the repository:
```bash
git clone https://github.com/blackklegend/recipe-manager-api.git
cd recipe-manager-api
```

2. Configure database in `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/recipedb
```

3. Run the application:
```bash
./gradlew bootRun
```

## 🧠 Testing

```bash
./gradlew test
```

## 📚 API Documentation

Once running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## 🚀 Deployment

1. Build the JAR:
```bash
./gradlew build
```

2. Run the JAR:
```bash
java -jar build/libs/recipe-manager-api.jar
```