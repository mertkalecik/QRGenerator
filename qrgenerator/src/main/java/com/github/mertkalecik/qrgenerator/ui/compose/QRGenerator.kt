package com.github.mertkalecik.qrgenerator.ui.compose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mertkalecik.qrgenerator.R
import com.github.mertkalecik.qrgenerator.utils.Constants.DEFAULT_PADDING
import com.github.mertkalecik.qrgenerator.utils.Constants.DEFAULT_QR_SIZE
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [rememberQrBitmapPainter] compose function creates a Bitmap painter for the QR code
 *  @param size -> You can change the QR code size.
 *  @param padding -> You can set encoding margin of the QR code.
 *  @param color -> you can change the QR code color.
 */
@Composable
fun rememberQrBitmapPainter(
    content: String,
    @DrawableRes qrPlaceHolder: Int = R.drawable.ic_qr_code_default,
    color: Color = Color.Black,
    size: Dp = DEFAULT_QR_SIZE.dp,
    padding: Dp = DEFAULT_PADDING.dp
): BitmapPainter {
    if (content.isBlank()) throw EmptyQrContentException

    val density = LocalDensity.current
    val sizePx = with(density) { size.roundToPx() }
    val paddingPx = with(density) { padding.roundToPx() }
    val resources = LocalContext.current.resources

    var bitmap by remember(content) {
        mutableStateOf<Bitmap>(BitmapFactory.decodeResource(resources, qrPlaceHolder))
    }

    LaunchedEffect(content) {
        launch(Dispatchers.IO) {
            val qrCodeWriter = QRCodeWriter()
            val encodeHints = mutableMapOf<EncodeHintType, Any>(EncodeHintType.MARGIN to paddingPx)

            val bitmapMatrix = try {
                qrCodeWriter.encode(
                    content,
                    BarcodeFormat.QR_CODE,
                    sizePx,
                    sizePx,
                    encodeHints
                )
            } catch (ex: WriterException) {
                throw ex
            }

            val matrixWidth = bitmapMatrix?.width ?: sizePx
            val matrixHeight = bitmapMatrix?.height ?: sizePx
            val localBitmap = Bitmap.createBitmap(
                matrixWidth,
                matrixHeight,
                Bitmap.Config.ARGB_8888,
            )

            bitmap = bitmapMatrix.updateMatrixColor(
                color = color.toArgb(),
                bitmap = localBitmap,
                matrixWidth = matrixWidth,
                matrixHeight = matrixHeight
            )
        }
    }

    return remember(bitmap) {
        val currentBitmap = bitmap
        BitmapPainter(currentBitmap.asImageBitmap())
    }
}

private fun BitMatrix.updateMatrixColor(
    @ColorInt color: Int,
    bitmap: Bitmap,
    matrixWidth: Int,
    matrixHeight: Int,
): Bitmap = with(bitmap) {
    for (x in 0 until matrixWidth) {
        for (y in 0 until matrixHeight) {
            val shouldColorPixel = get(x, y)
            val pixelColor = if (shouldColorPixel) color else Color.White.toArgb()

            bitmap.setPixel(x, y, pixelColor)
        }
    }

    bitmap
}

object EmptyQrContentException: Exception()