package br.com.hellodev.viacep.presenter.features.form.state

import br.com.hellodev.viacep.domain.remote.model.Address

data class FormState(
    val isLoading: Boolean = false,
    val search: String = "",
    val addresses: List<Address> = emptyList()
)
