package br.com.hellodev.viacep.data.remote.api

import br.com.hellodev.viacep.data.remote.mapper.toDomain
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var addressAPI: AddressAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        addressAPI = retrofit.create(AddressAPI::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun should_return_AddressResponse_when_receiving_json_from_api() = runTest {
        // Given: JSON de exemplo (igual o ViaCEP retornaria)
        val mockedJson = """
            {
              "cep": "01001-000",
              "logradouro": "Praça da Sé",
              "complemento": "lado ímpar",
              "unidade": "",
              "bairro": "Sé",
              "localidade": "São Paulo",
              "uf": "SP",
              "estado": "São Paulo",
              "regiao": "Sudeste",
              "ibge": "3550308",
              "gia": "1004",
              "ddd": "11",
              "siafi": "7107"
            }
        """.trimIndent()

        // Enfileira a resposta que o servidor irá retornar
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedJson)
        )

        // When: chamada da API
        val addressResponse = addressAPI.getAddress("01001000")

        val address = addressResponse.toDomain()

        // Then
        assertEquals("Praça da Sé", address.street)
        assertEquals("Sé", address.neighborhood)
        assertEquals("São Paulo", address.city)
        assertEquals("SP", address.uf)

        // Verifica se o endpoint correto foi chamado
        val request = mockWebServer.takeRequest()
        assertEquals("/01001000/json", request.path)
        assertEquals("GET", request.method)
    }

}