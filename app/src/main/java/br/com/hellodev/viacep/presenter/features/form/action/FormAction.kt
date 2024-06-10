package br.com.hellodev.viacep.presenter.features.form.action

sealed class FormAction {
    data object SearchAddress : FormAction()
    data class UpdateSearch(val search: String) : FormAction()
}