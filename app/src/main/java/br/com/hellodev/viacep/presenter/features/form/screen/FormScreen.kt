package br.com.hellodev.viacep.presenter.features.form.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.hellodev.viacep.domain.remote.model.Address
import br.com.hellodev.viacep.presenter.app.ui.components.card.AddressCard
import br.com.hellodev.viacep.presenter.app.ui.components.textfield.DefaultTextField
import br.com.hellodev.viacep.presenter.features.form.action.FormAction
import br.com.hellodev.viacep.presenter.features.form.state.FormState
import br.com.hellodev.viacep.presenter.features.form.viewmodel.FormViewModel

@Composable
fun FormScreen() {
    val viewModel: FormViewModel = viewModel()
    val state = viewModel.state.collectAsState().value

    FormContent(
        state = state,
        action = viewModel::action
    )
}

@Composable
fun FormContent(
    state: FormState,
    action: (FormAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end  = 16.dp, top = 16.dp),
            value = state.search,
            label = "digite seu CEP",
            placeholder = "00000-000",
            singleLine = true,
            charLimit = 8,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                action(FormAction.UpdateSearch(it))
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.addresses) { address ->
                AddressCard(address = address)
            }
        }
    }
}

@Preview
@Composable
private fun FormPreview() {
    FormContent(
        state = FormState(
            addresses = listOf(
                Address(
                    zipcode = "17805-048",
                    neighborhood = "Jardim Brasil",
                    street = "Rua Rio de Janeiro",
                    city = "Adamantina",
                    uf = "SP"
                ),
                Address(
                    zipcode = "17805-048",
                    neighborhood = "Jardim Brasil",
                    street = "Rua Rio de Janeiro",
                    city = "Adamantina",
                    uf = "SP"
                ),
                Address(
                    zipcode = "17805-048",
                    neighborhood = "Jardim Brasil",
                    street = "Rua Rio de Janeiro",
                    city = "Adamantina",
                    uf = "SP"
                ),
                Address(
                    zipcode = "17805-048",
                    neighborhood = "Jardim Brasil",
                    street = "Rua Rio de Janeiro",
                    city = "Adamantina",
                    uf = "SP"
                )
            )
        ),
        action = {}
    )
}