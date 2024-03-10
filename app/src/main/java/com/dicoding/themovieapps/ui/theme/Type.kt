package com.dicoding.themovieapps.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.dicoding.themovieapps.R

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val fontName = GoogleFont("Poppin")
private val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = fontProvider)
)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamily,
        color = blackColor
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamily,
        color = brownColor
    )
)

val whiteTextStyle = TextStyle(fontFamily = fontFamily, color = whiteColor)
val blueTextStyle = TextStyle(fontFamily = fontFamily, color = blueColor)
val greenTextStyle = TextStyle(fontFamily = fontFamily, color = greenColor)
val yellowTextStyle = TextStyle(fontFamily = fontFamily, color = yellowColor)


var light: FontWeight = FontWeight.W300
var regular: FontWeight = FontWeight.W400
var medium: FontWeight = FontWeight.W500
var semiBold: FontWeight = FontWeight.W600
var bold: FontWeight = FontWeight.W700
var extraBold: FontWeight = FontWeight.W800
var black: FontWeight = FontWeight.W900