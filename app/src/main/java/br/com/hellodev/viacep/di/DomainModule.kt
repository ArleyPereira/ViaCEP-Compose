package br.com.hellodev.viacep.di

import br.com.hellodev.viacep.data.remote.repository.AddressRemoteRepositoryImpl
import br.com.hellodev.viacep.domain.remote.repository.AddressRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsAddressRemoteRepositoryImpl(
        addressRemoteRepositoryImpl: AddressRemoteRepositoryImpl
    ): AddressRemoteRepository

}