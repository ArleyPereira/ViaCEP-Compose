package br.com.hellodev.viacep.presenter.features.form.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.viacep.domain.remote.usecase.GetAddressUseCase
import br.com.hellodev.viacep.presenter.features.form.action.FormAction
import br.com.hellodev.viacep.presenter.features.form.state.FormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    var state: StateFlow<FormState> = _state

    init {

    }

    fun action(action: FormAction) {

    }

}