package com.kharozim.phonebookapps.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kharozim.phonebookapps.databinding.ActivityLoginBinding
import com.kharozim.phonebookapps.helper.Constant
import com.kharozim.phonebookapps.helper.PreferencesHelper
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.PhoneBookService
import com.kharozim.phonebookapps.remote.SignInRemoteRepository
import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.remote.response.ResponseSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity()
//    , SignInContract.View
{

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val sharePref by lazy { PreferencesHelper(this) }
    private val service: PhoneBookService by lazy { ApiClient.phoneBookService }
    private val repository: SignInRemoteRepository by lazy { SignInRemoteRepository(service) }
//    private val presenter : SignInPresenter by lazy { SignInPresenter(this,repository ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {

            btRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            btLogin.setOnClickListener {
//                presenter.getSignIn(BodySignIn(tiePassword.text.toString(), tieEmail.text.toString()))
//            }
                ApiClient.phoneBookService.getSignIn(BodySignIn(tiePassword.text.toString(),tieEmail.text.toString()))
                    .enqueue(
                    object : Callback<ResponseSignIn> {
                        override fun onResponse(
                            call: Call<ResponseSignIn>,
                            response: Response<ResponseSignIn>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    sharePref.put(Constant.PREF_TOKEN, it.data.token)
                                    sharePref.put(Constant.PREF_EMAIL, tieEmail.text.toString())
                                    sharePref.put(Constant.PREF_PASSWORD, tiePassword.text.toString())
                                    sharePref.put(Constant.PREF_IS_LOGIN, true)
                                    moveIntent()
                                    Toast.makeText(this@LoginActivity,"${it.data.name} berhasil login",Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(this@LoginActivity,"Email / Password anda salah",Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                            Log.e("TAG", "onFailure: ${t.message}")
                            Toast.makeText(this@LoginActivity, "${t.message}", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
            }
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (sharePref.getBoolean(Constant.PREF_IS_LOGIN)){
           moveIntent()
        }
    }

    private fun saveSession(token: String, email: String, password : String) {
        sharePref.put(Constant.PREF_TOKEN, token)
        sharePref.put(Constant.PREF_EMAIL, email)
        sharePref.put(Constant.PREF_PASSWORD, password)
        sharePref.put(Constant.PREF_IS_LOGIN, true)
    }


/*    override fun onSuccessSignIn(token : String) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
    }*/
}