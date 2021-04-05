package com.vesam.quiz.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u0006\u0010\n\u001a\u00020\u000b\u001a\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010\"\u0011\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0011"}, d2 = {"appModule", "Lorg/koin/core/module/Module;", "getAppModule", "()Lorg/koin/core/module/Module;", "initNavController", "Landroidx/navigation/NavController;", "provideNetworkHelper", "Lcom/vesam/quiz/utils/network_helper/NetworkHelper;", "context", "Landroid/content/Context;", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "provideRetrofit", "Lcom/vesam/quiz/data/api/ApiService;", "okHttpClient", "toStringConverterFactory", "Lcom/vesam/quiz/utils/type_converters/ToStringConverterFactory;", "quiz_debug"})
public final class AppModuleKt {
    @org.jetbrains.annotations.NotNull()
    private static final org.koin.core.module.Module appModule = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final org.koin.core.module.Module getAppModule() {
        return null;
    }
    
    private static final androidx.navigation.NavController initNavController() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.utils.network_helper.NetworkHelper provideNetworkHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final okhttp3.OkHttpClient provideOkHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.data.api.ApiService provideRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.utils.type_converters.ToStringConverterFactory toStringConverterFactory) {
        return null;
    }
}