package com.example.userservice.repositories

import com.example.userservice.documents.User
import org.springframework.data.mongodb.core.ReactiveCollectionCallback
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.group
import org.springframework.data.mongodb.core.aggregation.Aggregation.match
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.CriteriaDefinition
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class UserRepositoryImpl(val reactiveMongoTemplate: ReactiveMongoTemplate) : UserRepository {

    override fun findAllUsersWithPhoneNumber(): Flux<User> {
        return reactiveMongoTemplate.find(Query.query(Criteria("phoneNumber").exists(true)),User::class.java)

    }

    override fun createUser(user: User): Mono<User> {
        return reactiveMongoTemplate.save(user)
    }

}