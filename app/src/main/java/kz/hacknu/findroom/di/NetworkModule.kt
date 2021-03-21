package kz.hacknu.findroom.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import kz.hacknu.findroom.network.FindRoomService
import kz.hacknu.findroom.presentation.FindRoomApp
import kz.hacknu.findroom.presentation.FindRoomConstant
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
val networkModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
    single { createFindRoomService(get()) }
}

fun createFindRoomService(retrofit: Retrofit): FindRoomService {
    return retrofit.create(FindRoomService::class.java)
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(FindRoomConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun getOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Builder()
        .followRedirects(true)
        .followSslRedirects(true)
        .retryOnConnectionFailure(true)
        .cache(null)
        .addInterceptor(ChuckInterceptor(FindRoomApp.CONTEXT))
        .addInterceptor(interceptor)
        .addInterceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("Authorization", "Bearer ${FindRoomConstant.TOKEN}")
            it.proceed(requestBuilder.build())
        }
        .connectTimeout(20, SECONDS)
        .writeTimeout(20, SECONDS)
        .readTimeout(20, SECONDS)
        .build()
}