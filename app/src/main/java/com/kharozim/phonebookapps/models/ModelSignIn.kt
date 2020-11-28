package com.kharozim.phonebookapps.models

import com.google.gson.annotations.SerializedName


data class ModelSignIn(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)