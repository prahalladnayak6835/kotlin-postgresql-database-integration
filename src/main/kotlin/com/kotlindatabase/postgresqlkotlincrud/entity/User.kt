package com.kotlindatabase.postgresqlkotlincrud.entity

import jakarta.persistence.*

@Entity
@Table(name = "users") // Specify a different table name
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    val name: String,
    val email: String
)