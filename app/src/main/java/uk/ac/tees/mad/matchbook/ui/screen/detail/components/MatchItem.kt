package uk.ac.tees.mad.matchbook.ui.screen.detail.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.matchbook.R
import uk.ac.tees.mad.matchbook.ui.theme.MatchBookTheme

@Composable
fun MatchItem() {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
                .width(290.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.sample_image),
                contentDescription = "league_thumb",
                modifier = Modifier.size(80.dp)
            )
            Text("Indian Premiere League",
                color = MaterialTheme.colorScheme.primary
                )
            Text("Cricket", style = MaterialTheme.typography.bodySmall,
                color = Color.Gray)
            Spacer(Modifier.height(8.dp))
            Text("Chennai Super King",
                fontWeight = FontWeight.Bold)
            Text("v/s")
            Text("Mumbai Indians",
                fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Row(modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "location"
                )
                Text("Hyderabad Stadium, ")
                Text("India")
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "date"
                )
                Text(" 2025-04-05", style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_access_time_24),
                    contentDescription = "time"
                )
                Text("14:00:00",style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.align(Alignment.Start)) {
                Text("Status: ", style = MaterialTheme.typography.bodyMedium)
                Text("Not Started", style = MaterialTheme.typography.bodyMedium)
            }

            TextButton({},
                modifier = Modifier.align(Alignment.End)) {
                Text("Book Ticket")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchItemPrev() {
    MatchBookTheme {
        MatchItem()
    }
}