package com.github.mertkalecik.qrgenerator.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mertkalecik.qrgenerator.sample.ui.theme.QRGeneratorTheme
import com.github.mertkalecik.qrgenerator.ui.compose.rememberQrBitmapPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRGeneratorTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Color.Black)

                MKSampleQRCode(qrContent = "https://github.com/mertkalecik")
            }
        }
    }
}

@Composable
fun MKSampleQRCode(qrContent: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 16.dp
            ),
            text = "Welcome To QR Code Sample App",
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

        Image(
            modifier = Modifier.padding(top = 32.dp),
            painter = rememberQrBitmapPainter(
                content = qrContent,
                color = Color.Red,
                size = 250.dp
            ),
            contentDescription = "QR Test",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 16.dp
            ),
            text = "This QR Code sample application stands for how to use the qr code bitmap" +
                    "painter while working with the Jetpack compose UI",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MKSampleQRCodePreview() {
    QRGeneratorTheme {
        MKSampleQRCode(qrContent = "https://github.com/mertkalecik")
    }
}