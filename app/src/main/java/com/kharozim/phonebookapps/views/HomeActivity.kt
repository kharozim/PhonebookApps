package com.kharozim.phonebookapps.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kharozim.phonebookapps.databinding.ActivityHomeBinding
import com.kharozim.phonebookapps.helper.Constant
import com.kharozim.phonebookapps.helper.PreferencesHelper
import com.kharozim.phonebookapps.remote.ApiClient
import com.kharozim.phonebookapps.remote.response.ResponseGetAllContact
import com.kharozim.phonebookapps.remote.response.ResponseGetDelete
import com.kharozim.phonebookapps.views.adapter.ContactAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), ContactAdapter.ListenerContact {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val sharedPref by lazy { PreferencesHelper(this) }
    private val adapter by lazy { ContactAdapter(this, this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {


            btLogout.setOnClickListener {
                sharedPref.clear()
                Toast.makeText(this@HomeActivity, "Keluar", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                finish()
            }

            btAdd.setOnClickListener {
                startActivity(Intent(this@HomeActivity, AddContactActivity::class.java))
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.rvHome.adapter = adapter
        binding.pbLoading.visibility = View.VISIBLE
        val token = sharedPref.getString(Constant.PREF_TOKEN)

        ApiClient.phoneBookService.getAllContact("Bearer $token")
            .enqueue(object : Callback<ResponseGetAllContact> {
                override fun onResponse(
                    call: Call<ResponseGetAllContact>,
                    response: Response<ResponseGetAllContact>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            binding.pbLoading.visibility = View.GONE
                            adapter.listContact = it.data.toMutableList()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseGetAllContact>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }

    override fun onDelete(id: Int) {
        val token = sharedPref.getString(Constant.PREF_TOKEN)
        ApiClient.phoneBookService.getDeleteById("Bearer $token",id).enqueue(object :
            Callback<ResponseGetDelete> {
            override fun onResponse(
                call: Call<ResponseGetDelete>,
                response: Response<ResponseGetDelete>
            ) {
                if (response.isSuccessful) {
                    adapter.deleteContactById(id)
                    Toast.makeText(this@HomeActivity, response.body()?.data, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseGetDelete>, t: Throwable) {
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}