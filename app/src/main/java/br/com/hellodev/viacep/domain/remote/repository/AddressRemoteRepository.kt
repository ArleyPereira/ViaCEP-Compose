package br.com.hellodev.viacep.domain.remote.repository

import br.com.hellodev.viacep.data.remote.model.AddressResponse

interface AddressRemoteRepository {

    suspend fun getAddress(zipcode: String) : AddressResponse

}