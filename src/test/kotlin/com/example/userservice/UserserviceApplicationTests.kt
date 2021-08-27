package com.example.userservice

import com.example.userservice.documents.User
import com.example.userservice.repositories.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@SpringBootTest
class UserserviceApplicationTests(@Autowired val userRepository: UserRepository) {


    @Test
    fun contextLoads() {
        Assertions.assertNotNull(userRepository)
    }

    @Test
    fun insert_user_testing(){
        var user : User = User(username = "prueba",email = "test@medium.com",address = "street 45 av. mil",fullName = "Alex Mark",phoneNumber = "5510321100",)
        var newUser : Mono<User> = userRepository.createUser(user)

        //Verificamos que el usuario que creamos arriba sea el mismo que se inserto
        StepVerifier
            .create(newUser)
            .assertNext {
                Assertions.assertEquals(user.username,it.username)
            }
    }

    @Test
    fun find_all_users_with_phone_number(){
        var users : Flux<User> = userRepository.findAllUsersWithPhoneNumber()
        //Verificamos que los usuarios que regresan no tenga su numero de telefono nulo
        StepVerifier
            .create(users)
            .assertNext {
                Assertions.assertNotNull(it.phoneNumber)
            }

    }


}
