package br.com.hellodev.viacep.domain.remote.usecase

import br.com.hellodev.viacep.data.remote.mapper.toDomain
import br.com.hellodev.viacep.domain.remote.model.Address
import br.com.hellodev.viacep.domain.remote.repository.AddressRemoteRepository
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val repository: AddressRemoteRepository
) {

    suspend operator fun invoke(zipcode: String): Address {
        return repository.getAddress(zipcode).toDomain()
    }

}