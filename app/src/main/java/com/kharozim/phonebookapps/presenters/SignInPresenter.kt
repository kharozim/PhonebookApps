package com.kharozim.phonebookapps.presenters

import com.kharozim.phonebookapps.remote.SignInRemoteRepository
import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.remote.response.ResponseSignIn
import com.kharozim.phonebookapps.views.SignInContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class SignInPresenter(
    private val view: SignInContract.View, private val repository: SignInRemoteRepository
) : SignInContract.Presenter {

    private val executorService by lazy { Executors.newFixedThreadPool(4) }

    override fun getSignIn(bodySignIn: BodySignIn) {
        repository.onSignIn(bodySignIn).enqueue(object : Callback<ResponseSignIn>{
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful){
                    // panggil view
                    view.onSuccessSignIn(response.body()!!.data.token)
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}