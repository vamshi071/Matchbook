package uk.ac.tees.mad.matchbook.ui.screen.home

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.ac.tees.mad.matchbook.R
import uk.ac.tees.mad.matchbook.ui.screen.home.components.LeagueItem
import uk.ac.tees.mad.matchbook.utils.Constants.getColor
import uk.ac.tees.mad.matchbook.utils.Routes

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val leagues by viewModel.leagueList.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("Permission", "Notification permission granted")
            } else {
                Log.e("Permission", "Notification permission denied")
            }
        }

    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(top = 30.dp, bottom = 12.dp,start = 16.dp, end = 16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "MatchBook",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton({
                        navController.navigate(Routes.HISTORY_SCREEN)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ticket),
                            contentDescription = "ticket",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = {searchQuery.value = it},
                    leadingIcon = { Icon(
                        Icons.Default.Search,
                        contentDescription = "Search"
                    ) },
                    placeholder = { Text("Search..") },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            itemsIndexed(leagues.filter {
                it.strLeague.contains(searchQuery.value, true)
            }){ idx,league->
                LeagueItem(league, getColor(idx%6)) {
                    navController.navigate("${Routes.DETAIL_SCREEN}/${league.idLeague}")
                }
            }
        }
    }
}