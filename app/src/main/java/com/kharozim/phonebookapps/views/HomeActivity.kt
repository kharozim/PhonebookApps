package com.kharozim.phonebookapps.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kharozim.phonebookapps.R
import com.kharozim.phonebookapps.databinding.ActivityHomeBinding
import com.kharozim.phonebookapps.helper.Constant
import com.kharozim.phonebookapps.helper.PreferencesHelper
import com.kharozim.phonebookapps.models.ModelGetAllContact
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.response.ResponseGetAllContact
import com.kharozim.phonebookapps.views.adapter.ContactAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private lateinit var sharedPref : PreferencesHelper
    private val adapter by lazy { ContactAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {

            rvHome.adapter = adapter
            sharedPref = PreferencesHelper(this@HomeActivity)
            val token = sharedPref.getString(Constant.PREF_TOKEN)

            ApiClient.phoneBookService.getAllContact("Bearer $token")
                .enqueue(object : Callback<ResponseGetAllContact> {
                    override fun onResponse(
                        call: Call<ResponseGetAllContact>,
                        response: Response<ResponseGetAllContact>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                adapter.listContact = it.data.toMutableList()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseGetAllContact>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

            btLogout.setOnClickListener {
                sharedPref.clear()
                Toast.makeText(this@HomeActivity, "Keluar", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                finish()
            }

        }
    }
}