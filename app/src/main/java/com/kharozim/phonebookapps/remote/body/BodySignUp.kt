package com.kharozim.phonebookapps.remote.body

import com.google.gson.annotations.SerializedName

data class BodySignUp(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)