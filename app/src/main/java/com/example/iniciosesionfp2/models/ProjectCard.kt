package com.example.iniciosesionfp2.models

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iniciosesionfp2.navigation.AppScreens

@Composable
fun ProjectCard(project: Project, navController: NavController) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navController.navigate("${AppScreens.ProjectDetailsScreen.route}/${project}") },
    ) {
        ListProjects(project = project)
    }
}

@Composable
fun ListProjects(project: Project) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Nombre: ${project.nombre}",
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