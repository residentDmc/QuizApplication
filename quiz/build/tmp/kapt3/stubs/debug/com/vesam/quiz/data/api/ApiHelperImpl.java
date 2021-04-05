package com.vesam.quiz.data.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J)\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJI\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0013j\b\u0012\u0004\u0012\u00020\u000b`\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/vesam/quiz/data/api/ApiHelperImpl;", "Lcom/vesam/quiz/data/api/ApiHelper;", "apiService", "Lcom/vesam/quiz/data/api/ApiService;", "(Lcom/vesam/quiz/data/api/ApiService;)V", "initGetQuizResult", "", "userUuid", "", "userApiToken", "quizId", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initQuizDetail", "initQuizList", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initSetQuizResult", "periodTime", "userAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/lang/String;IILjava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quiz_debug"})
public final class ApiHelperImpl implements com.vesam.quiz.data.api.ApiHelper {
    private final com.vesam.quiz.data.api.ApiService apiService = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object initQuizList(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object initQuizDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object initSetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, int periodTime, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> userAnswer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p5) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object initGetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3) {
        return null;
    }
    
    public ApiHelperImpl(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.api.ApiService apiService) {
        super();
    }
}