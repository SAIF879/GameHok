package com.example.gamehok.presentation.ui.componenets


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeadingText(text: String, modifier: Modifier = Modifier ,color: Color = Color.White) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
        color = color
    )
}

@Composable
fun SubheadingText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
        color = Color.White,
    )
}

@Composable
fun BodyText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        color = Color.White,
    )
}

@Composable
fun CaptionText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
        modifier = modifier,
        color = Color.White,
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun TextStylesPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        HeadingText("This is a Heading")
        Spacer(modifier = Modifier.height(8.dp))
        SubheadingText("This is a Subheading")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("This is some body text that gives information.")
        Spacer(modifier = Modifier.height(8.dp))
        CaptionText("This is a small caption.")
    }
}

