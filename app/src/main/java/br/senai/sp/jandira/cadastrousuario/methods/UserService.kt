package br.senai.sp.jandira.cadastrousuario.methods

import android.util.Log
import br.senai.sp.jandira.cadastrousuario.model.User

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.cadastrousuario.utils.Retrofit
import kotlinx.coroutines.launch

class UserService : ViewModel() {

    fun cadastrarUsuario(email: String, password: String, image: String) {
        val retrofit = Retrofit()

        Log.e("Email", "Email: ${email}")
        Log.e("Senha", "Senha: ${password}")
        Log.e("Image", "Image: ${image}")

        viewModelScope.launch {
            try {
                val user = User(login = email, senha = password, imagem = image)

                Log.e("User", "User: ${user}")
//                val newUser = retrofit.createUser(user)
            } catch (e: Exception) {
                Log.e("ERRINHO", "Erro: ${e.message}", e)
            }
        }
    }
}