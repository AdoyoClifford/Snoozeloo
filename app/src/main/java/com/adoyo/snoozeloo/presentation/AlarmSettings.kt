package com.adoyo.snoozeloo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adoyo.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmSettings(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
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

        Box(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@Composable
fun TimeInput(
    hours: String,
    minutes: String,
    onHoursChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = hours,
            onValueChange = { newValue ->
                if (newValue.length <= 2 && newValue.all { it.isDigit() }) {
                    val numericValue = newValue.toIntOrNull() ?: 0
                    if (numericValue in 0..23 || newValue.isEmpty()) {
                        onHoursChange(newValue.padStart(2, '0'))
                    }
                }
            },
            textStyle = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .width(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(
            text = ":",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                //  color = MaterialTheme.colorScheme.onSurface
            )
        )

        BasicTextField(
            value = minutes,
            onValueChange = { newvalue ->
                if (newvalue.length <= 2 && newvalue.all { it.isDigit() }) {
                    val numericValue = newvalue.toIntOrNull() ?: 0
                    if (numericValue in 0..59 || newvalue.isEmpty()) {
                        onMinutesChange(newvalue.padStart(2, '0'))
                    }

                }
            },
            textStyle = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .width(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    innerTextField()
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

    }
}


@PreviewLightDark
@Composable
fun TimeInputPreview() {
    var hours by remember { mutableStateOf("00") }
    var minutes by remember { mutableStateOf("00") }
    SnoozelooTheme {
        TimeInput(
            hours = hours,
            minutes = minutes,
            onHoursChange = {hours = it},
            onMinutesChange = {minutes = it},
            modifier = Modifier.padding(16.dp)
        )
    }
}



//@Preview
//@Composable
//fun PreviewAlarmSettings() {
//    SnoozelooTheme {
//        AlarmSettings()
//    }
//
//}