package uk.ac.tees.mad.matchbook.ui.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.ui.theme.MatchBookTheme

@Composable
fun LeagueItem(
    league: League,
    color:Color,
    onClick:()->Unit
) {
    Card(onClick = {onClick()},
        colors = CardDefaults.cardColors(
            containerColor = color
        )
        ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            Text(league.strLeague, style = MaterialTheme.typography.titleLarge)
            Text(league.strSport)
        }
    }
}