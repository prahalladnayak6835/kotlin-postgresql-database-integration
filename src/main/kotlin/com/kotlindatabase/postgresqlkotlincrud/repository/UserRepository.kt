package com.kotlindatabase.postgresqlkotlincrud.repository

import com.kotlindatabase.postgresqlkotlincrud.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
