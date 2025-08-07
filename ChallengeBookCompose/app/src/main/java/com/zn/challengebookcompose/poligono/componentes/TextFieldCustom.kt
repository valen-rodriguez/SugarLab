package com.zn.challengebookcompose.poligono.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.gray
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean,
    label: String,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        placeholder = { Text(label) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = card_background_color,
            unfocusedIndicatorColor = white,
            unfocusedLabelColor = white,
            unfocusedTextColor = white,
            focusedContainerColor = card_background_color,
            focusedIndicatorColor = white,
            focusedLabelColor = white,
            focusedTextColor = white,
            unfocusedPlaceholderColor = gray,
            focusedPlaceholderColor = gray,
            disabledContainerColor = card_background_color,
            disabledLabelColor = gray,
            disabledTextColor = gray,
            disabledPlaceholderColor = gray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}