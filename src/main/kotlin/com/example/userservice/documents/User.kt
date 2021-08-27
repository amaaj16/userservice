package com.example.userservice.documents

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User (
    @Id
    var id:String? = ObjectId().toHexString(),
    var username:String,
    var email:String,
    var address: String?,
    var fullName: String?,
    var phoneNumber : String?
) {

}