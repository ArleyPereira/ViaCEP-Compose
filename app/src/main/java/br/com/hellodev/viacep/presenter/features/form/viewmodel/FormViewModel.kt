package br.com.hellodev.viacep.presenter.features.form.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.viacep.domain.remote.usecase.GetAddressUseCase
import br.com.hellodev.viacep.presenter.features.form.action.FormAction
import br.com.hellodev.viacep.presenter.features.form.state.FormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        when (action) {
            FormAction.SearchAddress -> {
                searchAddress()
            }

            is FormAction.UpdateSearch -> {
                _state.update { currentState ->
                    currentState.copy(search = action.search)
                }

                searchAddress()
            }
        }
    }

    private fun searchAddress() {
        viewModelScope.launch {
            if (_state.value.search.length == 8) {
                val addressResponse = getAddressUseCase(_state.value.search)
                val currentAddresses = _state.value.addresses.toMutableList().apply {
                    add(addressResponse)
                }
                _state.update { currentState ->
                    currentState.copy(addresses = currentAddresses)
                }
            }
        }
    }

}