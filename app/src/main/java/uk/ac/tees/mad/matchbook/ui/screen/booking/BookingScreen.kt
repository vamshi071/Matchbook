package uk.ac.tees.mad.matchbook.ui.screen.booking

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.matchbook.R
import uk.ac.tees.mad.matchbook.ui.screen.detail.components.MatchItem
import uk.ac.tees.mad.matchbook.utils.NotificationHelper
import uk.ac.tees.mad.matchbook.utils.Routes
import kotlin.math.max

@Composable
fun BookingScreen(
    id: String,
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val match by viewModel.match.collectAsState()
    var ticketCount = remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    LaunchedEffect(id) {
        viewModel.fetchMatchData(id)
    }
    Scaffold(
        topBar = {
            Text(
                "Book Your Tickets",
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
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            match?.let { MatchItem(it, false) {} }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .align(Alignment.Start)
            ) {
                Text(
                    "Number of tickets:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp)
                )
                IconButton({
                    ticketCount.intValue = max(0, ticketCount.intValue - 1)
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_remove_24),
                        contentDescription = "minus"
                    )

                }
                Text(
                    ticketCount.intValue.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                IconButton(
                    {
                        ticketCount.intValue++
                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(
                            0xFF1565C0
                        )
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "plus",
                        tint = Color.White
                    )

                }
            }

            Button(
                {
                    viewModel.addTicket(ticketCount.intValue) { id1 ->
                        viewModel.vibrateOnConfirm()
                        navController.navigate("${Routes.CONFIRMATION_SCREEN}/$id1")
                        NotificationHelper.showNotification(context, "Ticket Booked",
                            match?.strEvent.toString()
                        )
                        Toast.makeText(
                            context,
                            "Ticket Booked SuccessFully with ticket id: $id1",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3188EC)),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End)
            ) {
                Text("Confirm Booking", color = Color.White)
            }
        }
    }
}