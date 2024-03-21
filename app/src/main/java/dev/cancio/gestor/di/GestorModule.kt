package dev.cancio.gestor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cancio.gestor.api.GestorApi
import dev.cancio.gestor.api.GestorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GestorModule {

    companion object {
        const val BASE_URL = "http://10.0.2.2:8000"
    }


    @Singleton
    @Provides
    @Named("Normal")
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideService(@Named("Normal") retrofit: Retrofit): GestorService = retrofit.create(GestorService::class.java)

    @Singleton
    @Provides
    fun providesApi(service: GestorService) = GestorApi(service)

}