package br.senai.sp.jandira.cadastrousuario.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tbl_usuarios")
data class User(
    @PrimaryKey(autoGenerate = true)
    val codUsuario: Int = 0,
    val login: String,
    val senha: String,
    val imagem: String
)
