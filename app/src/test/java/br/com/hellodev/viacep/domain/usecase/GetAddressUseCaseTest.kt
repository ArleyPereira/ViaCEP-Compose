package br.com.hellodev.viacep.domain.usecase

import br.com.hellodev.viacep.data.remote.mapper.toDomain
import br.com.hellodev.viacep.data.remote.model.AddressResponse
import br.com.hellodev.viacep.domain.remote.repository.AddressRemoteRepository
import br.com.hellodev.viacep.domain.remote.usecase.GetAddressUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAddressUseCaseTest {

    private val repository = mock<AddressRemoteRepository>()
    private lateinit var getAddressUseCase: GetAddressUseCase

    @Before
    fun setup() {
        getAddressUseCase = GetAddressUseCase(repository)
    }

    @Test
    fun should_return_valid_address_when_calling_use_case() = runTest {
        // Given
        val zipcode = "12345-678"
        val addressResponse = AddressResponse(
            zipcode = "12345-678",
            street = "Rua Teste",
            neighborhood = "Centro",
            city = "Cidade Teste",
            uf = "TS"
        )
        val address = addressResponse.toDomain()

        whenever(repository.getAddress(zipcode)).thenReturn(addressResponse)

        // When
        val result = getAddressUseCase(zipcode)

        // Then
        assertEquals(address, result)
        verify(repository).getAddress(zipcode)
    }

}