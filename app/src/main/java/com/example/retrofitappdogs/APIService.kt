package com.example.retrofitappdogs

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    //https://dog.ceo/api/breed/akita/images
    @GET
    suspend fun obtenerPerrosPorRaza(@Url url:String):Response<DogsResponse>

    //https://dog.ceo/api/breed/boxer/images/random
    @GET
    suspend fun obtenerImagenRamdonPorRaza(@Url url:String):Response<DogsResponse>




}