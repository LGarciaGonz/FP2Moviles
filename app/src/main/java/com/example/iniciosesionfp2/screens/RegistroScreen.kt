package com.example.iniciosesionfp2.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iniciosesionfp2.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController) {

    var user by rememberSaveable { mutableStateOf("") }
    var passwd by rememberSaveable { mutableStateOf("") }
    var passwd2 by rememberSaveable { mutableStateOf("") }
    var oculto by rememberSaveable { mutableStateOf(true) }
    var context = LocalContext.current
    val auth: FirebaseAuth = Firebase.auth

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Registro de nuevo usuario") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {

                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // ICONO UBICACIÓN -------------------------------------------------
            val image = painterResource(id = R.drawable.fp___factoria_de_proyectos)
            Image(
                painter = image,
                contentDescription = "icono ubicación",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(40.dp))

            // CAMPO CORREO ELECTRÓNICO ----------------
            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text(text = "Correo electrónico") })

            Spacer(modifier = Modifier.height(20.dp))

            // CAMPO CONTRASEÑA -------------------------------------------------
            OutlinedTextField(
                value = passwd,
                onValueChange = { passwd = it },
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

            Spacer(modifier = Modifier.height(20.dp))

            // CAMPO CONTRASEÑA -------------------------------------------------
            OutlinedTextField(
                value = passwd2,
                onValueChange = { passwd2 = it },
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

            Spacer(modifier = Modifier.height(20.dp))


            Button(onClick = {

                when {

                    // Campos en blanco = false
                    !validarCampos(
                        user,
                        passwd,
                        passwd2
                    ) -> mostrarMensajeError("Los campos no deben estar vacíos", context)

                    // email @ -> false
                    !validarEmail(user) -> mostrarMensajeError(
                        "El usuario debe ser un correo electrónico",
                        context
                    )

                    // Contraseñas diferentes
                    passwd != passwd2 -> mostrarMensajeError(
                        "Las contraseñas no coinciden",
                        context
                    )

                    // Longitud de contraseña
                    passwd.length < 9 || passwd2.length < 9 -> mostrarMensajeError(
                        "La contraseña debe tener al menos 9 caracteres",
                        context
                    )

                    else -> auth.createUserWithEmailAndPassword(user, passwd).addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Usuario creado correctamente.",
                                Toast.LENGTH_SHORT
                            ).show()

                            user = ""
                            passwd = ""
                            passwd2 = ""
                        }
                    }
                }

            }) {
                Text(text = "Registrar")
            }
        }
    }
}

// Función para comprobar que los campos no estén vacíos.
fun validarCampos(user: String, pass1: String, pass2: String): Boolean =
    user.isNotBlank() && pass1.isNotBlank() && pass2.isNotBlank()

// Función para comprobar que el usuario sea un correo electrónico.
fun validarEmail(email: String): Boolean = email.contains("@")

// Función para mostrar mensajes de error.
fun mostrarMensajeError(mensaje: String, contexto: Context) {
    Toast.makeText(contexto, ">>> $mensaje", Toast.LENGTH_SHORT).show()
}