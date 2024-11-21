package com.adoyo.snoozeloo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adoyo.snoozeloo.R
import com.adoyo.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmSettings(modifier: Modifier = Modifier) {
    var alarmVolume by remember { mutableFloatStateOf(0.7f) }
    var alarmName by remember { mutableStateOf("work") }
    var isVibrationEnabled by remember { mutableStateOf(true) }

    val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val selectedDays = remember { mutableStateOf(daysOfWeek) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface
            )
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel, contentDescription = "Cancel",

                    )
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {}
            ) {
                Text("Save")
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            TimeInput(
                hours = "07",
                minutes = "30",
                onHoursChange = {},
                onMinutesChange = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                "Alarm Name",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontWeight = FontWeight(600)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "Work",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontWeight = FontWeight(600)
                ),
                modifier = Modifier.alpha(0.5f)
            )
        }

        WeekSelector {  }

    }
}

@Composable
fun WeekSelector(
    modifier: Modifier = Modifier,
    selectedDays: Set<Int> = emptySet(),
    onDaySelected: (Int) -> Unit
) {
    val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Repeat",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                fontWeight = FontWeight(600)
            )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            daysOfWeek.forEachIndexed { index, day ->
                val isSelected = selectedDays.contains(index)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = day,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewWeekSelector() {
    SnoozelooTheme {
        WeekSelector(
            selectedDays = setOf(0, 1, 2, 3, 4, 5, 6),
            onDaySelected = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmSettings() {
    SnoozelooTheme {
        AlarmSettings()
    }

}