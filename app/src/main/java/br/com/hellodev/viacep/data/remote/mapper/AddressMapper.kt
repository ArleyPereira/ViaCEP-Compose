package br.com.hellodev.viacep.data.remote.mapper

import br.com.hellodev.viacep.data.remote.model.AddressResponse
import br.com.hellodev.viacep.domain.remote.model.Address

fun AddressResponse.toDomain(): Address {
    return Address(
        zipcode = zipcode,
        neighborhood = neighborhood,
        city = city,
        street = street,
        uf = uf
    )
}