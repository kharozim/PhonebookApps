package com.kharozim.phonebookapps.remote

import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.remote.response.ResponseSignIn
import retrofit2.Call

class SignInRemoteRepository(private val service: PhoneBookService) {

    fun onSignIn(bodySignIn: BodySignIn) : Call<ResponseSignIn>{
        return service.getSignIn(bodySignIn)
    }
}