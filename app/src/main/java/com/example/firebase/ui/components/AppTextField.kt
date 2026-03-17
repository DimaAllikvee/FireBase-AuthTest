package com.example.firebase.ui.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firebase.ui.theme.FieldBackground
import com.example.firebase.ui.theme.FieldBoarder
import com.example.firebase.ui.theme.FieldPlaceholder

object InputFieldTokens {
    val boarderRadius: Dp = 6.dp
    val boarderWidth: Dp = 1.dp
    val paddingHorizontal: Dp = 12.dp
    val paddingVertical: Dp = 4.dp
    val fieldHeight: Dp = 36.dp
}

@Composable

fun AppTextField (
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .height(InputFieldTokens.fieldHeight)
            .background(color = FieldBackground,
                shape = RoundedCornerShape(
            InputFieldTokens.boarderRadius)
                    )
            .border(
                width = InputFieldTokens.boarderWidth,
                color = FieldBoarder,
                shape = RoundedCornerShape(InputFieldTokens.boarderRadius)
            )
            .padding(
                horizontal = InputFieldTokens.paddingHorizontal,
                vertical = InputFieldTokens.paddingVertical
            )
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (isPassword){
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
           decorationBox = { innerTextField ->
               if (value.isEmpty()) {
                   Text(
                       text = placeholder,
                       color = FieldPlaceholder,
                   )
               }
               innerTextField()
           }
        )
    }
}