package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.BuildConfig
import hr.ferit.brunozoric.taskie.common.AUTH_INTERCEPTOR
import hr.ferit.brunozoric.taskie.common.BASE_URL
import hr.ferit.brunozoric.taskie.common.KEY_AUTHORIZATION
import hr.ferit.brunozoric.taskie.common.LOGGING_INTERCEPTOR
import hr.ferit.brunozoric.taskie.networking.AuthService
import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkingModule = module{

    //INTERCEPTORS
    single (named(LOGGING_INTERCEPTOR)){
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    single(named(AUTH_INTERCEPTOR)){
        Interceptor{
            val authentication = it.request().newBuilder()
                .addHeader(KEY_AUTHORIZATION, provideSharedPrefs().getUserToken())
                .build()
            it.proceed(authentication)
        }
    }

    //OKHTTPCLIENT
    single {
        OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG) {
                addInterceptor(get(named(LOGGING_INTERCEPTOR)))
                addInterceptor(get(named(AUTH_INTERCEPTOR)))

            }
            readTimeout(1L, TimeUnit.MINUTES)
            connectTimeout(1L,TimeUnit.MINUTES)

        }.build()
    }

    //RETROFIT
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(AuthService::class.java) }
}