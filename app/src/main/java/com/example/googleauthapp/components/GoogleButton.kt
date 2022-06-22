package com.example.googleauthapp.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.googleauthapp.R
import com.example.googleauthapp.ui.theme.LoadingBlue
import com.example.googleauthapp.ui.theme.Shapes

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: Int = R.drawable.ic_google_logo,
    shapes: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    borderStroke: Dp =1.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = LoadingBlue,
    onClick: () -> Unit


) {

    var buttontext by remember {
        mutableStateOf(primaryText)
    }

    LaunchedEffect(key1 = loadingState) {
        buttontext = if (loadingState) secondaryText else primaryText

    }

    Surface(
        modifier = modifier.clickable(enabled = !loadingState) {
            onClick()
        }, shape = shapes,
        border = BorderStroke(borderStroke, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = modifier
                .padding(
                    12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(painter = painterResource(id = icon), contentDescription = "Google Icon", tint = Color.Unspecified)
            Spacer(modifier = modifier.width(8.dp))
            Text(text = buttontext)
            if (loadingState){
                Spacer(modifier = Modifier.width(8.dp))
                CircularProgressIndicator(
                    modifier = modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )

            }




        }

    }


}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {

    }
}