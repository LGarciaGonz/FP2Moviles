package com.example.iniciosesionfp2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.iniciosesionfp2.models.Project

@Composable
fun ProjectDetailsScreen(navController: NavController, project : Project) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Nombre: ${project.nombre}",
            color = Color.Black
        )

        Text(
            text = "Descripción del proyecto: ${project.descripcion}",
            color = Color.Black
        )

        Text(
            text = "Centro responsable: ${project.centro}",
            color = Color.Black
        )

        Text(
            text = "Fecha de inicio: ${project.fechaIni}",
            color = Color.Black
        )

        Text(
            text = "Fecha de fin: ${project.fechaFin}",
            color = Color.Black
        )
    }
}