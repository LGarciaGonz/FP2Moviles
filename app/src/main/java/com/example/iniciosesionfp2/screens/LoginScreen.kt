package com.example.iniciosesionfp2.screens

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iniciosesionfp2.R
import com.example.iniciosesionfp2.navigation.AppScreens
import com.example.iniciosesionfp2.viewmodels.LoginScreenViewModel

@Composable
fun LoginScreen(navController: NavController, loginScreenViewModel: LoginScreenViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {

        // DECLARACIONES -------------------------------------------------
        val usuario: String by loginScreenViewModel.user.observeAsState(initial = "")
        val contrasena: String by loginScreenViewModel.passwd.observeAsState(initial = "")
        var oculto by rememberSaveable { mutableStateOf(true) }
        val context = LocalContext.current

        // TEXTO PARA BOTÓN CLICKABLE ----------
        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Registrarse")
            }
        }

        // TEXTO PARA BOTÓN CLICKABLE ----------
        val annotatedString2 = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Acceso como invitado")
            }
        }


        // -------------------------------------------------------------------------------------------------------

        Column(
            // ALINEAR EL CONTENIDO EN LA PANTALLA --------------
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ICONO USUARIO -------------------------------------------------
            val image = painterResource(id = R.drawable.fp___factoria_de_proyectos)
            Image(
                painter = image,
                contentDescription = "fondo",
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )

            // CAMPO USUARIO -------------------------------------------------
            OutlinedTextField(
                value = usuario,
                onValueChange = { loginScreenViewModel.onDataChanged(it, contrasena) },
                singleLine = true,
                label = { Text(text = "Usuario") }
            )

            // CAMPO CONTRASEÑA -------------------------------------------------
            OutlinedTextField(
                value = contrasena,
                onValueChange = { loginScreenViewModel.onDataChanged(usuario, it) },
                label = { Text(text = "Contraseña") },
                modifier = Modifier
                    .padding(top = 20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),            // Establecer campo como contraseña
                singleLine = true,                                                                  // No permitir que el usuario introduzca más de una línea
                visualTransformation =
                if (oculto) {                                                                       // Comprobar si se pulsa el icono
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },

                // Mostrar icono Ojo
                trailingIcon = {
                    IconButton(onClick = { oculto = !oculto }) {
                        val vector = painterResource(
                            R.drawable.iconoojo
                        )

                        Icon(painter = vector, contentDescription = "description")
                    }
                }
            )

            // BOTÓN INICIAR SESIÓN -------------------------------------------------
            Button(
                modifier = Modifier
                    .padding(top = 20.dp),
                onClick = {

                    var mensaje =
                        loginScreenViewModel.comprobarAcceso(navController)       // Recoger el mensaje retornado en la función.

                    Toast.makeText(context, mensaje, Toast.LENGTH_LONG)
                        .show()              // Mostrar mensaje.
                },
                // Cambiar el color del botón.
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#ec5f28"))
                )
            ) {

                Text(text = "Iniciar Sesión")
            }

            Spacer(modifier = Modifier.height(20.dp))                                       // Separación entre botones.

            // TEXTO CLICKABLE PARA REGISTRARSE -----------------------
            ClickableText(
                text = annotatedString,
                onClick = { navController.navigate(AppScreens.RegistroScreen.route) }
            )

            Spacer(modifier = Modifier.padding(5.dp))

            // TEXTO CLICKABLE PARA REGISTRARSE -----------------------
            ClickableText(
                text = annotatedString2,
                onClick = { navController.navigate(AppScreens.HomeScreen.route) })

        }
    }
}