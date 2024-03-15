package com.example.iniciosesionfp2.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.iniciosesionfp2.navigation.AppScreens

class LoginScreenViewModel {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _passwd = MutableLiveData<String>()
    val passwd: LiveData<String> = _passwd

    fun onDataChanged(
        userUi: String,
        passwdUi: String
    ) {
        _user.value = userUi
        _passwd.value = passwdUi
    }


    fun comprobarAcceso(navController: NavController): String {

        // DECLARACIÓN DE VARIABLES -----------
        var numIntentos = 3
        var mensaje = ""

        // COMPROBAR SI COINCIDEN USUARIO Y CONTRASEÑA
        if (_user.value?.isNotEmpty() == true && _passwd.value?.isNotEmpty() == true) {
            if (_user.value == "admin" && _passwd.value == "admin") {

                mensaje = "Bienvenido a la aplicación"

                navController.navigate(AppScreens.HomeScreen.route)
            } else {
                numIntentos--

                if (numIntentos <= 0) {
                    mensaje = "Número de intentos superado."
                } else {
                    mensaje = "Usuario o contraseña incorrectos."
                }
            }
        } else {
            mensaje = "Los campos no deben estar vacíos."
        }
            return mensaje
        }
    }