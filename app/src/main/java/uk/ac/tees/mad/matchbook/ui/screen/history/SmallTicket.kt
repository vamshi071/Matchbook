package uk.ac.tees.mad.matchbook.ui.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.matchbook.model.Ticket
import uk.ac.tees.mad.matchbook.ui.screen.confirmation.DashedDivider

@Composable
fun SmallTicket(ticket: Ticket, onClick:()-> Unit) {
    Card(
        onClick = {onClick()},
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ticket.eventName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = ticket.eventType,
                fontSize = 12.sp,
                color = Color.Gray
            )
            DashedDivider()
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Location", color = Color.Gray, modifier = Modifier.weight(2f))
                Text("Date",color = Color.Gray, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(ticket.location, modifier = Modifier.weight(2f))
                Text(ticket.date, modifier = Modifier.weight(1f))
            }
        }
    }
}