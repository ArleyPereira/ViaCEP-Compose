package br.com.hellodev.viacep.data.repository

import br.com.hellodev.viacep.data.remote.api.AddressAPI
import br.com.hellodev.viacep.data.remote.model.AddressResponse
import br.com.hellodev.viacep.data.remote.repository.AddressRemoteRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AddressRemoteRepositoryImplTest {

    private val addressAPI = mock<AddressAPI>()
    private lateinit var repository: AddressRemoteRepositoryImpl

    @Before
    fun setUp() {
        repository = AddressRemoteRepositoryImpl(addressAPI)
    }

    @Test
    fun should_return_AddressResponse_when_calling_getAddress() = runTest {
        // Given
        val zipcode = "01001000"
        val expectedResponse = AddressResponse(
            zipcode = "01001000",
            street = "Praça da Sé",
            neighborhood = "Sé",
            city = "São Paulo",
            uf = "SP"
        )

        whenever(addressAPI.getAddress(zipcode)).thenReturn(expectedResponse)

        // When
        val result = repository.getAddress(zipcode)

        // Then
        assertEquals(expectedResponse, result)
        verify(addressAPI).getAddress(zipcode)
    }
}