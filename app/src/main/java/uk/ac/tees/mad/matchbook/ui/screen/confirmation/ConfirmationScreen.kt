package uk.ac.tees.mad.matchbook.ui.screen.confirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.matchbook.utils.Routes

@Composable
fun ConfirmationScreen(
    id: Long, navController: NavController,
    viewModel: ConfirmViewModel = hiltViewModel()
) {
    val ticket by viewModel.ticket.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadTicket(id)
    }
    Scaffold(
        topBar = {
            Text(
                "Ticket Confirm",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            ticket?.let { EventTicket(it) }
            Spacer(Modifier.height(16.dp))
            ConfirmationCard()
            Button(
                {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(0)
                    }
                },
                colors = ButtonDefaults.buttonColors(),
                modifier = Modifier.padding(8.dp)
            ) {
                Text("OK")
            }
        }
    }
}