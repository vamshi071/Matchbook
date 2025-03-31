package uk.ac.tees.mad.matchbook.ui.screen.history

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.matchbook.utils.Routes

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val ticketList by viewModel.ticketList.collectAsState()
    Scaffold(
        topBar = {
            Text(
                "My Tickets",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
            )
        }
    ) { paddingValues->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(ticketList) { ticket->
                SmallTicket(ticket) {
                    navController.navigate("${Routes.CONFIRMATION_SCREEN}/${ticket.id}")
                }
            }
        }
    }
}