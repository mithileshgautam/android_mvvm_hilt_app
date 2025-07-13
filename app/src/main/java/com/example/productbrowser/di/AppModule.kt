package com.example.productbrowser.di

import com.example.productbrowser.database.AppDatabase
import com.example.productbrowser.database.ProductDao
import com.example.productbrowser.network.FakeStoreApi
import com.example.productbrowser.network.FakeStoreApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide Retrofit API
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFakeStoreApi(retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
}
