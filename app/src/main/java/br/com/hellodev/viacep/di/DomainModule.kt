package br.com.hellodev.viacep.di

import br.com.hellodev.viacep.data.remote.repository.AddressRemoteRepositoryImpl
import br.com.hellodev.viacep.domain.remote.repository.AddressRemoteRepository

abstract class DomainModule {

    abstract fun bindsAddressRemoteRepositoryImpl(
        addressRemoteRepositoryImpl: AddressRemoteRepositoryImpl
    ): AddressRemoteRepository

}