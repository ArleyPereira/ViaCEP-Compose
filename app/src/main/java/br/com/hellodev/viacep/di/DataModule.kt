package br.com.hellodev.viacep.di

import br.com.hellodev.viacep.data.remote.api.AddressAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesAddressAPI(
        retrofit: Retrofit
    ): AddressAPI {
        return retrofit.create(AddressAPI::class.java)
    }

}