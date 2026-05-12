package com.memory.brain.training.games.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.memory.brain.training.games.domain.model.GameInfo

@Composable
fun GameCard(
    game: GameInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Game thumbnail
            Image(
                painter = painterResource(id = game.thumbnailResId),
                contentDescription = stringResource(id = game.nameResId),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Game name
            Text(
                text = stringResource(id = game.nameResId),
                style = MaterialTheme.typography.titleSmall
            )
            
            // Lock indicator if needed
            if (game.hasLock) {
                Text(
                    text = "Unlock at level ${game.unlockLevel}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
