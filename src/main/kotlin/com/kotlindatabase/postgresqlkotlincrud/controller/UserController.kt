package com.kotlindatabase.postgresqlkotlincrud.controller

import com.kotlindatabase.postgresqlkotlincrud.entity.User
import com.kotlindatabase.postgresqlkotlincrud.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    /**
     * Get all users from the database.
     * @return List of users
     */
    @GetMapping("/get")
    fun getAllUsers(): Any {
        logger.info("Fetching all users")
        return try {
            val users = userRepository.findAll()
            if (users.isEmpty()) {
                ResponseEntity.status(HttpStatus.NO_CONTENT)
                return "No user found"
            } else {
                ResponseEntity.ok(users)
            }
        } catch (e: Exception) {
            logger.error("Error fetching all users", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    /**
     * Create a new user.
     * @param user User object to be created
     * @return Created user object
     */
    @PostMapping("/add")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        logger.info("Creating user: $user")
        return try {
            val createdUser = userRepository.save(user)
            ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
        } catch (e: Exception) {
            logger.error("Error creating user: $user", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Get a user by their ID.
     * @param id User ID
     * @return User object with the specified ID
     * @throws EntityNotFoundException if user with the specified ID is not found
     */
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any> {
        logger.info("Fetching user with id: $id")
        return try {
            val user = userRepository.findById(id)
                .orElseThrow { EntityNotFoundException("User with id $id not found") }
            ResponseEntity.ok(user)
        } catch (e: EntityNotFoundException) {
            logger.error("User with id $id not found", e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id $id not found")
        } catch (e: Exception) {
            logger.error("Error fetching user with id: $id", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Update a user with the specified ID.
     * @param id User ID
     * @param user Updated user object
     * @return Updated user object
     * @throws EntityNotFoundException if user with the specified ID is not found
     */
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<Any> {
        logger.info("Updating user with id: $id")
        return try {
            if (!userRepository.existsById(id)) {
                throw EntityNotFoundException("User with id $id not found")
            }
            user.id = id
            val updatedUser = userRepository.save(user)
            ResponseEntity.ok(updatedUser)
        } catch (e: EntityNotFoundException) {
            logger.error("User with id $id not found", e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id $id not found")
        } catch (e: Exception) {
            logger.error("Error updating user with id: $id", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Delete a user with the specified ID.
     * @param id User ID
     * @return Success message
     * @throws EntityNotFoundException if user with the specified ID is not found
     */
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        logger.info("Deleting user with id: $id")
        return try {
            if (!userRepository.existsById(id)) {
                throw EntityNotFoundException("User with id $id not found")
            }
            userRepository.deleteById(id)
            ResponseEntity.ok("User with id $id has been deleted")
        } catch (e: EntityNotFoundException) {
            logger.error("User with id $id not found", e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id $id not found")
        } catch (e: Exception) {
            logger.error("Error deleting user with id: $id", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}