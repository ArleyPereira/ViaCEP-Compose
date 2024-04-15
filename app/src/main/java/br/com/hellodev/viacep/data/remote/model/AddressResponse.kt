package br.com.hellodev.viacep.data.remote.model

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("cep")
    val zipcode: String?,

    @SerializedName("bairro")
    val neighborhood: String?,

    @SerializedName("localidade")
    val city: String?,

    @SerializedName("logradouro")
    val street: String?,

    @SerializedName("uf")
    val uf: String?
)