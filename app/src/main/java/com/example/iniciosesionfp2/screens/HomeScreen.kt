package com.example.iniciosesionfp2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iniciosesionfp2.data.allProjects
import com.example.iniciosesionfp2.models.ProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // Variables locales.
    var showMenu by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "FP2 - Factoría de Proyectos") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(android.graphics.Color.parseColor("#ec5f28")),
                titleContentColor = androidx.compose.ui.graphics.Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            })
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Texto informativo.
            Text(
                text = "Explora los proyectos disponibles.",
                modifier = Modifier.padding(25.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                allProjects.forEach { project ->
                    item {
                        ProjectCard(project = project, navController)
                    }
                }
            }
        }
    }
}


