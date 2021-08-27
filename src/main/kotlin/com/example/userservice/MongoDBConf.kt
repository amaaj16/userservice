package com.example.userservice

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@Configuration
@EnableReactiveMongoRepositories
class MongoDBConf {

    @Bean
    fun mongoDBClientProd(env: Environment): MongoClient {
        val url = env.getProperty("db.uri")?.let { ConnectionString(it) }
        val mongoSettings = url?.let { MongoClientSettings.builder().applyConnectionString(it).build() }
        return MongoClients.create(mongoSettings)
    }

    @Bean
    fun reactiveMongoTemplate(mongoDBClient: MongoClient) : ReactiveMongoTemplate {
        return ReactiveMongoTemplate(mongoDBClient, "Shop")
    }




}