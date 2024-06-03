package br.com.hellodev.viacep.presenter.features.form.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.viacep.presenter.app.ui.components.textfield.DefaultTextField

@Composable
fun FormScreen() {
    FormContent()
}

@Composable
fun FormContent() {
    var textValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textValue,
            label = "digite seu CEP",
            placeholder = "00000-000",
            singleLine = true,
            charLimit = 8,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Preview
@Composable
private fun FormPreview() {
    FormContent()
}