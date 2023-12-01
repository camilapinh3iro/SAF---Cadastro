package br.senai.sp.jandira.cadastrousuario.utils

import android.util.Log
import retrofit2.Retrofit
import br.senai.sp.jandira.cadastrousuario.api.UserInterface
import br.senai.sp.jandira.cadastrousuario.model.User
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val BASE_URL = "http://10.107.144.19:3000"
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createUser(): UserInterface {

        return retrofit.create(UserInterface::class.java)
    }

}