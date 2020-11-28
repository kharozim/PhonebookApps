package com.kharozim.phonebookapps.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kharozim.phonebookapps.R
import com.kharozim.phonebookapps.databinding.ActivityAddContactBinding
import com.kharozim.phonebookapps.helper.Constant
import com.kharozim.phonebookapps.helper.PreferencesHelper
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.response.ResponseGetAllContact
import com.kharozim.phonebookapps.remote.response.ResponseSaveContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddContactActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddContactBinding.inflate(layoutInflater) }
    val sharePref by lazy { PreferencesHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {
            val token = sharePref.getString(Constant.PREF_TOKEN)
            btnAdd.setOnClickListener {
                if (tieName.text.toString().isNullOrEmpty() ||
                    tiePhone.text.toString().isNullOrEmpty()
                ) {
                    Toast.makeText(this@AddContactActivity, "nama dan number phone wajib diisi", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    ApiClient.phoneBookService.getSaveContact(
                        "Bearer $token",
                        tieName.text.toString(),
                        tiePhone.text.toString(),
                        tieJob.text.toString(),
                        tieEmail.text.toString()
                    )
                        .enqueue(object : Callback<ResponseSaveContact> {
                            override fun onResponse(
                                call: Call<ResponseSaveContact>,
                                response: Response<ResponseSaveContact>
                            ) {
                                if (response.isSuccessful) {
                                    response.body()?.let {
                                        Toast.makeText(
                                            this@AddContactActivity,
                                            "${it.data.name} berhasil ditambahkan",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(this@AddContactActivity, HomeActivity::class.java) )
                                        finish()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<ResponseSaveContact>, t: Throwable) {
                                Toast.makeText(
                                    this@AddContactActivity,
                                    t.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        })
                }
            }
        }
    }
}