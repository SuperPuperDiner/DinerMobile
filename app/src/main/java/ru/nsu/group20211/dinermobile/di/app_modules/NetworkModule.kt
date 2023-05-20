package ru.nsu.group20211.dinermobile.di.app_modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class BaseUrl

@Qualifier
annotation class JsonClient

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://tinkoff-android-spring-2023.zulipchat.com"
    }

    @Provides
    @Singleton
    @JsonClient
    fun provideJsonClient(okHttpClient: OkHttpClient, @BaseUrl url: String): Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }






}