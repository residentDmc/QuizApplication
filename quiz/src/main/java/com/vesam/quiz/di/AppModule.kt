package com.vesam.quiz.di

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.api.ApiHelper
import com.vesam.quiz.data.api.ApiHelperImpl
import com.vesam.quiz.data.api.ApiService
import com.vesam.quiz.utils.type_converters.ToStringConverterFactory
import com.vesam.quiz.utils.application.AppQuiz.Companion.activity
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.APPLICATION_JSON_HEADER
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.CONTENT_TYPE_HEADER
import com.vesam.quiz.utils.image.ZoomOutSlideTransformer
import com.vesam.quiz.utils.manager.GridLayoutCountManager
import com.vesam.quiz.utils.manager.KeyboardManager
import com.vesam.quiz.utils.music_manager.BeatBox
import com.vesam.quiz.utils.network_helper.NetworkHelper
import com.vesam.quiz.utils.tools.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    single { initNavController() }
    single { provideRetrofit(get(),get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> { return@single ApiHelperImpl(get()) }
    single { return@single ThrowableTools(get(),get()) }
    single { return@single Gson() }
    single { return@single ToastTools() }
    single { return@single GlideTools(get(), get()) }
    single { return@single HandelErrorTools() }
    single { return@single GridLayoutCountManager(get()) }
    single { return@single ToStringConverterFactory() }
    single { return@single KeyboardManager() }
    single { return@single NetworkTools() }
    single { return@single BottomSheetDialog(activity) }
    single { return@single ZoomOutSlideTransformer() }
    single { return@single BeatBox(get(),get()) }
    single { return@single MediaPlayer() }
    single { provideOkHttpClient() }
}

private fun initNavController() =
    Navigation.findNavController(activity, R.id.my_nav_fragment)

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

fun provideOkHttpClient(): OkHttpClient {
    val i = Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON_HEADER)
        val request: Request = requestBuilder.build()
        val response = chain.proceed(request)
        response
    }
    return OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).addInterceptor(i)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, toStringConverterFactory: ToStringConverterFactory): ApiService =
    Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(toStringConverterFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
