package com.kotlindatabase.postgresqlkotlincrud.repository

import com.kotlindatabase.postgresqlkotlincrud.entity.User
import org.springframework.data.jpa.repository.JpaRepository

// UserRepository interface extends JpaRepository to perform CRUD operations on the User entity
interface UserRepository : JpaRepository<User, Long>
