package br.senai.sp.jandira.cadastrousuario.api

import br.senai.sp.jandira.cadastrousuario.model.ApiResponse
import br.senai.sp.jandira.cadastrousuario.model.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInterface {

    @POST("usuario/cadastrarUsuario")
    fun createUser(@Body body: JsonObject): Call<ApiResponse>

}