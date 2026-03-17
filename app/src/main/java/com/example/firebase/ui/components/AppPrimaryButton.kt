package com.example.firebase.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firebase.ui.theme.PrimaryButtonBackground
import com.example.firebase.ui.theme.PrimaryButtonText

object  PrimaryButtonTokens {
    val buttonHeigh: Dp = 36.dp
    val boarderRadius: Dp = 6.dp
}

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(PrimaryButtonTokens.buttonHeigh),
        shape = RoundedCornerShape(PrimaryButtonTokens.boarderRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryButtonBackground,
            contentColor = PrimaryButtonText
        )
    ) {
        Text(text=text)
    }
}