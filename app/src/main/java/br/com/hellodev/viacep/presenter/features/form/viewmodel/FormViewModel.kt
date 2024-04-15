package br.com.hellodev.viacep.presenter.features.form.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.viacep.domain.remote.usecase.GetAddressUseCase
import br.com.hellodev.viacep.presenter.features.form.action.FormAction
import br.com.hellodev.viacep.presenter.features.form.state.FormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormViewModel(
    private val getAddressUseCase: GetAddressUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    var state: StateFlow<FormState> = _state

    init {

    }

    fun action(action: FormAction) {

    }

}