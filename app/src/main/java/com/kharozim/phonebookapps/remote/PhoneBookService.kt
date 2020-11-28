package com.kharozim.phonebookapps.remote

import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.remote.body.BodySignUp
import com.kharozim.phonebookapps.remote.response.ResponseGetAllContact
import com.kharozim.phonebookapps.remote.response.ResponseSignIn
import com.kharozim.phonebookapps.remote.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.*

interface PhoneBookService {

    @POST("api/v1/signin")
    fun getSignIn(@Body bodySignIn: BodySignIn): Call<ResponseSignIn>

    @POST("api/v1/signup")
    fun getSignUp(@Body bodySignUp: BodySignUp): Call<ResponseSignUp>

    @POST("api/v1/contacts")
    fun getSaveContact(
        @Header("Authorization")
        token: String,
        @Field("phone")
        phone: String,
        @Field("job")
        job: String,
        @Field("company")
        company: String,
        @Field("email")
        email: String
    ): Call<ResponseSignUp>

    @GET("api/v1/contacts")
    fun getAllContact(
        @Header("Authorization")
        token: String
    ): Call<ResponseGetAllContact>


    @DELETE("/api/v1/contacts/{id}")
    fun getDeleteById(
        @Header("Authorization")
        token: String,
        @Path("id")
        id : Int
    ) : Call<ResponseGetAllContact>

}