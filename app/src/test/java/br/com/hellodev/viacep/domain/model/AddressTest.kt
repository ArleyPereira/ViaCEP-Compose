package br.com.hellodev.viacep.domain.model

import br.com.hellodev.viacep.domain.remote.model.Address
import org.junit.Assert.assertEquals
import org.junit.Test

class AddressTest {

    @Test
    fun address_data_class_should_be_correctly_created() {
        val address = Address(
            zipcode = "12345-678",
            street = "Rua Teste",
            neighborhood = "Centro",
            city = "Cidade Teste",
            uf = "TS"
        )

        assertEquals("12345-678", address.zipcode)
        assertEquals("Rua Teste", address.street)
        assertEquals("Centro", address.neighborhood)
        assertEquals("Cidade Teste", address.city)
        assertEquals("TS", address.uf)
    }

    @Test
    fun address_data_class_with_nulls_should_be_correctly_created() {
        val address = Address(
            zipcode = "12345-678",
            street = "Rua Teste",
            neighborhood = "Centro",
            city = "Cidade Teste",
            uf = null
        )

        assertEquals("12345-678", address.zipcode)
        assertEquals("Rua Teste", address.street)
        assertEquals("Centro", address.neighborhood)
        assertEquals("Cidade Teste", address.city)
        assertEquals(null, address.uf)
    }
}