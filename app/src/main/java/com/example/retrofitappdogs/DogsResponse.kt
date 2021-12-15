package com.example.retrofitappdogs

import com.google.gson.annotations.SerializedName

data class DogsResponse (
    @SerializedName("status") val status:String,
    @SerializedName("message")val listaImagenes:List<String>
    )