package br.senai.sp.jandira.cadastrousuario

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.cadastrousuario.methods.UserService
import br.senai.sp.jandira.cadastrousuario.model.ApiResponse
import br.senai.sp.jandira.cadastrousuario.utils.Retrofit
import br.senai.sp.jandira.cadastrousuario.utils.StorageUtil
import coil.compose.AsyncImage
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                // Your content here
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    var isPasswordVisible by remember { mutableStateOf(false) }

    val icon = if (isPasswordVisible)
        painterResource(id = R.drawable.visibility_24)
    else
        painterResource(id = R.drawable.visibility_off_24)

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
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
                .width(150.dp)
                .height(145.dp)
                .clickable {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }

        ) {

            AsyncImage(
                model = uri ?: R.drawable.image_profile_field,
                contentDescription = "field image profile",
                modifier = Modifier.size(140.dp),
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(Color(red = 183, green = 183, blue = 183))
            )

            DisposableEffect(uri) {
                if (uri != null) {
                    // Call your UploadPick function here when uri is updated
                    fun UploadPick(uri: Uri, context: Context) {
                        uri?.let {
                            StorageUtil.uploadToStorage(
                                uri = it,
                                context = context,
                                type = "image",
                                {
                                    url = it
                                })
                        }
                    }

                    UploadPick(uri!!, context)

                }

                onDispose { }
            }

            Image(
                painter = painterResource(id = R.drawable.camera_24),
                contentDescription = "field image profile",
                modifier = Modifier
                    .width(46.dp)
                    .height(32.dp)
                    .align(Alignment.BottomEnd),
                colorFilter = ColorFilter.tint(Color(red = 39, green = 142, blue = 52))
            )


        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange =
            { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Email")
            },
            placeholder = {
                Text(text = "Seu email")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(red = 39, green = 142, blue = 52),
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color(red = 39, green = 142, blue = 52),
                unfocusedLabelColor = Color.Black,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
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
            ),


            )


        Spacer(modifier = Modifier.height(15.dp))

        // Button to call cadastrarUsuario function
        Button(
            onClick = {
                val body = JsonObject().apply {
                    addProperty("login", email)
                    addProperty("senha", password)
                    addProperty("imagem", url)
                }

                Log.e("CADASTRO", "${body}")

                val call = Retrofit().createUser().createUser(body)

                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        Log.e("CADASTRO", "${response.body()}")

                        Toast.makeText(
                            context,
                            "${response.body()!!.mensagemStatus}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.i(
                            "ds2m",
                            "onFailure: ${t.message}"
                        )

                        Log.i(
                            "ds2m",
                            "onFailure: ${call}"
                        )
                    }

                })
            },
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

@Composable
fun showToast(message: String) {
    // Implementar lógica para exibir um toast (mensagem temporária) na interface do usuário
    // Você pode usar um componente personalizado ou exibir um Snackbar, por exemplo
    // Nota: Esta implementação é simplificada e pode precisar ser adaptada ao seu aplicativo
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // O tema pode ser omitido para a visualização
    MyApp()
}
