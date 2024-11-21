import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adoyo.snoozeloo.ui.theme.SnoozelooTheme
import com.adoyo.snoozeloo.ui.theme.bluePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Alarms") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = bluePrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Alarm", tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Alarm,
                contentDescription = "Alarm Icon",
                modifier = Modifier.size(48.dp),
                tint = bluePrimary
            )

            Spacer(modifier = Modifier.height(16.dp)) // Spacing

            Text(
                text = "It's empty! Add the first alarm so you\ndon't miss an important moment!",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF0D0F19)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnoozelooTheme { AlarmScreen() }
}