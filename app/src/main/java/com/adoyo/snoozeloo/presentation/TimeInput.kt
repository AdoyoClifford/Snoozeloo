package com.adoyo.snoozeloo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adoyo.snoozeloo.R
import com.adoyo.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun TimeInput(
    hours: String,
    minutes: String,
    onHoursChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.surface
        ).clip(RoundedCornerShape(12.dp)).padding(horizontal = 24.dp),
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
            singleLine = true,
            modifier = Modifier
                .width(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp),
            textStyle = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            ),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

        )

        Text(
            text = ":",
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        BasicTextField(
            value = minutes,
            onValueChange = { newValue ->
                if (newValue.length <= 2 && newValue.all { it.isDigit() }) {
                    val numericValue = newValue.toIntOrNull() ?: 0
                    if (numericValue in 0..59 || newValue.isEmpty()) {
                        onMinutesChange(newValue.padStart(2, '0'))
                    }
                }

            },
            singleLine = true,
            modifier = Modifier
                .width(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp),
            textStyle = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            ),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimeInputPreview() {
    var hours by remember { mutableStateOf("00") }
    var minutes by remember { mutableStateOf("00") }

    MaterialTheme {
        SnoozelooTheme {
            TimeInput(
                hours = hours,
                minutes = minutes,
                onHoursChange = { hours = it },
                onMinutesChange = { minutes = it },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
