package com.kotlindatabase.postgresqlkotlincrud.entity

import jakarta.persistence.*

// Entity annotation marks this class as an entity to be mapped to a database table
@Entity
// Table annotation specifies the name of the table in the database
@Table(name = "users") // Specify a different table name if needed
// Data class to represent a User entity
data class User(
    // Id annotation marks this field as the primary key
    @Id
    // GeneratedValue annotation specifies the strategy for generating the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    // Val annotation makes this field immutable (read-only) after initialization
    val name: String,
    val email: String
)
