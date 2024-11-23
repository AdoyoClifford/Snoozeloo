package com.adoyo.snoozeloo.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adoyo.snoozeloo.ui.theme.bluePrimary
import com.adoyo.snoozeloo.ui.theme.blueSecondary


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun AlarmCreationScreen() {
    var hours by remember { mutableStateOf("00") }
    var minutes by remember { mutableStateOf("00") }
    var alarmName by remember { mutableStateOf("Work") }
    val repeatDays = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    val selectedRepeatDays = remember { mutableStateOf(emptyList<String>()) }
    var ringtone by remember { mutableStateOf("Default") }
    var volume by remember { mutableFloatStateOf(0.5f) }
    var vibrate by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                actions = {
                    TextButton(onClick = { /* Handle save action */ }) {
                        Text("Save", color = bluePrimary) // Purple color
                    }
                },
                //  colors = TopAppBarColors(containerColor = Color.White),
                //    elevation = 0.dp, // No shadow

            )
        },
        containerColor = Color.White

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp
                ),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeInput(
                    hours = hours,
                    minutes = minutes,
                    onHoursChange = { hours = it },
                    onMinutesChange = { minutes = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            // Alarm Name
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Alarm Name")
                Spacer(Modifier.weight(1f))
                Text(alarmName)
            }
            Spacer(Modifier.height(16.dp))


            // Repeat Days
            Text("Repeat")
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                repeatDays.forEach { day ->


                    Button(
                        onClick = {
                            selectedRepeatDays.value = if (day in selectedRepeatDays.value) {
                                selectedRepeatDays.value - day
                            } else {
                                selectedRepeatDays.value + day
                            }
                        },
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (day in selectedRepeatDays.value) bluePrimary else Color.Transparent, // Purple color

                        )


                    ) {
                        Text(
                            day,
                            color = if (day in selectedRepeatDays.value) Color.White else bluePrimary,

                            )

                    }
                }

            }


            Spacer(Modifier.height(16.dp))


            // Alarm Ringtone
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Alarm ringtone")
                Text(ringtone) // Display selected ringtone
            }
            Spacer(Modifier.height(16.dp))


            // Alarm Volume
            Row(modifier = Modifier.fillMaxWidth()) {

                Text("Alarm volume")
                Spacer(Modifier.width(8.dp))
                LineSlider(
                    value = volume,
                    onValueChange = { volume = it },
                    valueRange = 0f..1f,
                    steps = 5,
                    thumbDisplay = { value -> "${(value * 100).toInt()}%" }
                )
            }

            // Vibrate
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Vibrate")
                Switch(
                    checked = vibrate, onCheckedChange = { vibrate = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = bluePrimary,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }

        }
    }

}

@Composable
fun LineSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    thumbDisplay: (Float) -> String = { "" },
    modifier: Modifier = Modifier
) {
    val animatedValue by animateFloatAsState(
        targetValue = value,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "animatedValue"
    )

    Slider(
        value = animatedValue,
        onValueChange = onValueChange,
        modifier = modifier,
        valueRange = valueRange,
        steps = steps
    )
}

@Preview
@Composable
fun LineSliderPreview() {
    LineSlider(
        value = 0.5f,
        onValueChange = {},
        valueRange = 0f..1f,
        steps = 0,
        thumbDisplay = { value -> "$value" }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmCreationScreen() {
    AlarmCreationScreen()
}