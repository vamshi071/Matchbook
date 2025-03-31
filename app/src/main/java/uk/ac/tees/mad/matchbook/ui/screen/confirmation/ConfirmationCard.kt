package uk.ac.tees.mad.matchbook.ui.screen.confirmation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.matchbook.R

@Composable
fun ConfirmationCard() {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_check_circle_24),
                contentDescription = "Confirm",
                tint = Color(0xFF06D001),
                modifier = Modifier.padding(end = 12.dp).size(80.dp)
            )

            Text("Ticket Booked Successfully",
                color = Color(0xFF06D001), style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
        }
    }
}