package com.kharozim.phonebookapps.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kharozim.phonebookapps.databinding.ActivityRegisterBinding
import com.kharozim.phonebookapps.helper.Constant
import com.kharozim.phonebookapps.helper.PreferencesHelper
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.body.BodySignUp
import com.kharozim.phonebookapps.remote.response.ResponseSignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val sharePrefer by lazy { PreferencesHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {

            btnLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
            btnRegister.setOnClickListener {
                ApiClient.phoneBookService.getSignUp(
                    BodySignUp(
                        tieName.text.toString(),
                        tieEmail.text.toString(),
                        tiePassword.text.toString()
                    )
                ).enqueue(object :
                    Callback<ResponseSignUp> {
                    override fun onResponse(
                        call: Call<ResponseSignUp>,
                        response: Response<ResponseSignUp>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                sharePrefer.put(Constant.PREF_EMAIL, tieEmail.text.toString())
                                sharePrefer.put(Constant.PREF_PASSWORD, tiePassword.text.toString())
                                sharePrefer.put(Constant.PREF_USERNAME, tieName.text.toString())
                                sharePrefer.put(Constant.PREF_TOKEN, it.data)
                                sharePrefer.put(Constant.PREF_IS_LOGIN, true)
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "akun ${tieName.text.toString()} berhasil dibuat",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        HomeActivity::class.java
                                    )
                                )
                            }
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Data tidak valid",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharePrefer.getBoolean(Constant.PREF_IS_LOGIN) == true) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}