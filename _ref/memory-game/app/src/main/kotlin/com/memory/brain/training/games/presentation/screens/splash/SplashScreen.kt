package com.memory.brain.training.games.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        delay(2000) // Show splash for 2 seconds
        onNavigateToMain()
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // App logo/icon would go here
            Text(
                text = "Memory Brain Training",
                style = MaterialTheme.typography.headlineLarge
            )
            
            CircularProgressIndicator()
        }
    }
}
