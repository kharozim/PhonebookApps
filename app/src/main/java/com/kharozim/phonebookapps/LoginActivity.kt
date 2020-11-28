package com.kharozim.phonebookapps

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kharozim.phonebookapps.databinding.ActivityLoginBinding
import com.kharozim.phonebookapps.presenters.SignInPresenter
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.PhoneBookService
import com.kharozim.phonebookapps.remote.SignInRemoteRepository
import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.views.SignInContract

class LoginActivity : AppCompatActivity(), SignInContract.View {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val service : PhoneBookService by lazy { ApiClient.phoneBookService }
    private val repository : SignInRemoteRepository by lazy { SignInRemoteRepository(service) }
    private val presenter : SignInPresenter by lazy { SignInPresenter(this,repository ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {



            btLogin.setOnClickListener {
                presenter.getSignIn(BodySignIn(tiePassword.text.toString(), tieEmail.text.toString()))
            }

               /* ApiClient.phoneBookService.getSignIn(BodySignIn(tiePassword.text.toString(),tieEmail.text.toString())).enqueue(
                    object : Callback<ResponseSignIn> {
                        override fun onResponse(
                            call: Call<ResponseSignIn>,
                            response: Response<ResponseSignIn>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    Toast.makeText(this@LoginActivity, "login token : ${it.data.token}", Toast.LENGTH_SHORT).show()
//                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                }
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                            Log.e("TAG", "onFailure: ${t.message}",)
                        }

                    })
            }*/
        }

    }

    override fun onSuccessSignIn(token : String) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
    }
}