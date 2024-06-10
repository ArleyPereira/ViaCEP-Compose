package br.com.hellodev.viacep.domain.remote.model

data class Address(
    val zipcode: String? = null,
    val neighborhood: String? = null,
    val city: String? = null,
    val street: String? = null,
    val uf: String? = null
) {
    override fun toString(): String {
        return "$zipcode, $neighborhood, $city, $street, $uf"
    }
}