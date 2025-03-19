package uk.ac.tees.mad.matchbook.ui.screen.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uk.ac.tees.mad.matchbook.R
import uk.ac.tees.mad.matchbook.model.Match

@Composable
fun MatchItem(
    match: Match,
    showButton: Boolean,
    onClick:()-> Unit
) {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
                .width(290.dp)
                .height(400.dp)
        ) {
            AsyncImage(
                model = match.strLeagueBadge,
                contentDescription = "league_thumb",
                modifier = Modifier.size(80.dp)
            )
            Text(match.strLeague,
                color = MaterialTheme.colorScheme.primary
                )
            Text(match.strSport, style = MaterialTheme.typography.bodySmall,
                color = Color.Gray)
            Spacer(Modifier.height(8.dp))
            Text(match.strHomeTeam,
                fontWeight = FontWeight.Bold)
            Text("v/s")
            Text(match.strAwayTeam,
                fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Row(modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "location"
                )
                Text("${match.strVenue}, ${match.strCountry}")
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "date"
                )
                Text(match.dateEvent, style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_access_time_24),
                    contentDescription = "time"
                )
                Text(match.strTime,style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.align(Alignment.Start)) {
                Text("Status: ", style = MaterialTheme.typography.bodyMedium)
                Text(match.strStatus, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.weight(1f))
            if (showButton) {
                TextButton({onClick()},
                    modifier = Modifier.align(Alignment.End)) {
                    Text("Book Ticket")
                }
            }
        }
    }
}