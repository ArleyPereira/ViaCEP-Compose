package br.com.hellodev.viacep.data.remote.repository

import br.com.hellodev.viacep.data.remote.api.AddressAPI
import br.com.hellodev.viacep.data.remote.model.AddressResponse
import br.com.hellodev.viacep.domain.remote.repository.AddressRemoteRepository
import javax.inject.Inject

class AddressRemoteRepositoryImpl @Inject constructor(
    private val addressAPI: AddressAPI
) : AddressRemoteRepository {

    override suspend fun getAddress(zipcode: String): AddressResponse {
        return addressAPI.getAddress(zipcode)
    }

}