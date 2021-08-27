package com.example.userservice.repositories

import com.example.userservice.documents.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface UserRepository {
    fun findAllUsersWithPhoneNumber(): Flux<User>

    fun createUser(user:User): Mono<User>
}