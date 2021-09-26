package com.servian.sampletest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email : String,

    @SerializedName("phone")
    val phone : String
):Serializable
