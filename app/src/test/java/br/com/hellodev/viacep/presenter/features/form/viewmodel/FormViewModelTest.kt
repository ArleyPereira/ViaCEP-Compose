package br.com.hellodev.viacep.presenter.features.form.viewmodel

import app.cash.turbine.test
import br.com.hellodev.viacep.domain.remote.model.Address
import br.com.hellodev.viacep.domain.remote.usecase.GetAddressUseCase
import br.com.hellodev.viacep.presenter.features.form.action.FormAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FormViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getAddressUseCase: GetAddressUseCase
    private lateinit var viewModel: FormViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getAddressUseCase = mock<GetAddressUseCase>()
        viewModel = FormViewModel(getAddressUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_update_search_field_when_receiving_FormAction_updateSearch() = runTest {
        // Given
        val search = "12345678"
        val address = Address(
            "12345678",
            "Rua Teste",
            "Centro",
            "Cidade",
            "TS")

        whenever(getAddressUseCase(search)).thenReturn(address)

        // When
        viewModel.action(FormAction.UpdateSearch(search))

        // Then
        viewModel.state.test {
            val state = awaitItem()
            assertEquals(search, state.search)
        }
    }

    @Test
    fun should_add_address_to_list_after_searchAddress_with_valid_search() = runTest {
        // Given
        val search = "12345678"
        val address = Address(
            "12345678",
            "Rua Teste",
            "Centro",
            "Cidade",
            "TS")

        whenever(getAddressUseCase(search)).thenReturn(address)

        // When
        viewModel.action(FormAction.UpdateSearch(search))
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertEquals(listOf(address), state.addresses)
    }

    @Test
    fun should_not_fetch_address_if_search_has_less_than_8_characters() = runTest {
        val shortSearch = "123"

        // When
        viewModel.action(FormAction.UpdateSearch(shortSearch))
        advanceUntilIdle()

        // Then
        verify(getAddressUseCase, never()).invoke(any())
        assertTrue(viewModel.state.value.addresses.isEmpty())
    }

    @Test
    fun should_fetch_address_when_cep_has_8_digits() = runTest {
        // Given
        val cep = "01001000"
        val address = Address("Praça da Sé", "São Paulo", "SP", cep)

        whenever(getAddressUseCase(cep)).thenReturn(address)

        // When
        viewModel.action(FormAction.UpdateSearch(cep))
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertEquals(cep, state.search)
        assertTrue(state.addresses.contains(address))

        verify(getAddressUseCase).invoke(cep)
    }

    @Test
    fun should_not_fetch_address_if_cep_is_incomplete() = runTest {
        // When
        viewModel.action(FormAction.UpdateSearch("12345"))
        advanceUntilIdle()

        // Then
        verify(getAddressUseCase, never()).invoke(any())
        assertEquals(0, viewModel.state.value.addresses.size)
    }

}