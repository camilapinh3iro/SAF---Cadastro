package br.senai.sp.jandira.cadastrousuario.gui

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.cadastrousuario.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {

    val name = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }


    var isPasswordVisible by remember { mutableStateOf(false) }

    val icon = if (isPasswordVisible)
        painterResource(id = R.drawable.visibility_24)
    else
        painterResource(id = R.drawable.visibility_off_24)

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {
            var uri = it
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 15.dp, top = 25.dp, end = 15.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {


        Text(
            text = "Cadastre-se agora!",
            modifier = Modifier.padding(bottom = 25.dp),
            color = Color(red = 39, green = 142, blue = 52),
            fontSize = 33.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .width(160.dp)
                .height(165.dp)
                .clickable {
                    singlePhotoPicker
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_profile_field),
                contentDescription = "field image profile",
                modifier = Modifier.size(140.dp),
                alignment = Alignment.Center
            )


        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            placeholder = {
                Text(text = "Seu nome")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(red = 39, green = 142, blue = 52),
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color(red = 39, green = 142, blue = 52),
                unfocusedLabelColor = Color.Black,
            )

            )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Senha")
            },
            placeholder = {
                Text(text = "Sua senha")
            },
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(painter = icon, contentDescription = "Visibility icon")
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(red = 39, green = 142, blue = 52),
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color(red = 39, green = 142, blue = 52),
                unfocusedLabelColor = Color.Black,
            )

        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(red = 39, green = 142, blue = 52)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Cadastrar",
                fontSize = 17.sp
            )
        }


    }


}


@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()

}